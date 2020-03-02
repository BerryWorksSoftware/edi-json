package com.berryworks.edireader.json.driver;

import com.berryworks.edireader.json.fromedi.EdiToJson;

import java.io.*;

public class EdiToJsonDriver implements Runnable {
    private File ediFile, jsonFile;
    private boolean summarize, annotate, format, recover;

    public EdiToJsonDriver() {
        // Set defaults for options
        summarize = false;
        annotate = false;
        format = true;
        recover = false;
    }

    public void setInputFile(File ediFile) {
        this.ediFile = ediFile;
    }

    public void setOutputFile(File jsonFile) {
        this.jsonFile = jsonFile;
    }

    @Override
    public void run() {
        final EdiToJson ediToJson = new EdiToJson();
        ediToJson.setFormatting(format);
        ediToJson.setAnnotated(annotate);
        ediToJson.setSummarize(summarize);
//        ediToJson.setRecover(recover);
        try (Reader reader = new BufferedReader(ediFile == null ? new InputStreamReader(System.in) : new FileReader(ediFile));
             Writer writer = new BufferedWriter(jsonFile == null ? new OutputStreamWriter(System.out) : new FileWriter(jsonFile))) {
            ediToJson.asJson(reader, writer);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {

        final EdiToJsonDriver driver = new EdiToJsonDriver();

        // Args beginning with "--" are treated as options.
        // The first arg not beginning with "--" is the name of the input file.
        // The next arg not beginning with "--" is the name of the output file.
        boolean establishedInputFile = false;
        for (String arg : args) {
            if (arg.startsWith("--")) {
                // --option=value
                final String[] split = arg.split("=");
                if (split.length != 2) {
                    badArgs();
                    return;
                }
                String optionName = split[0];
                String yesOrNo = split[1];
                switch (optionName) {
                    case "--summarize":
                        driver.setSummarize("yes".equalsIgnoreCase(yesOrNo));
                        break;
                    case "--annotate":
                        driver.setAnnotate("yes".equalsIgnoreCase(yesOrNo));
                        break;
                    case "--format":
                        driver.setFormat("yes".equalsIgnoreCase(yesOrNo));
                        break;
                    case "--recover":
                        driver.setRecover("yes".equalsIgnoreCase(yesOrNo));
                        break;
                    default:
                        badArgs();
                        return;
                }
            } else if ("help".equals(arg) && args.length == 1) {
                logUsage();
                return;
            } else {
                // inputFileName or outputFileName
                if (establishedInputFile) {
                    driver.setOutputFile(new File(arg));
                } else {
                    driver.setInputFile(new File(arg));
                    establishedInputFile = true;
                }
            }
        }

        try {
            driver.run();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void badArgs() {
        System.err.println("Invalid command line arguments");
        logUsage();
    }

    private static void logUsage() {
        log();
        log("Usage Summary");
        log("=============");
        log();
        log("Read EDI from an input file, write JSON to an output file");
        log("  java -jar <jarFileName>  <ediInputFile>  <jsonOutputFile>  <options>");
        log();
        log("Read EDI from an input file, write JSON to stdout");
        log("  java -jar <jarFileName>  <ediInputFile>  <options>");
        log();
        log("Read EDI from stdin, write JSON to stdout");
        log("  java -jar <jarFileName>  <options>");
        log();
        log("Display this usage summary");
        log("  java -jar <jarFileName>  help");
        log();
        log();
        log("options");
        log("  ", "--summarize={yes|no}", ":", "if yes, omit segment-level detail (default is no)");
        log("  ", "--annotate={yes|no}", ":", "if yes, include descriptive \"annotations\" (default is no)");
        log("  ", "--format={yes|no}", ":", "if yes, format JSON output (default is yes)");
        log("  ", "--recover={yes|no}", ":", "if yes, ignore any recoverable EDI errors (default is no)");
        log();
    }

    private static void log(Object... items) {
        StringBuilder sb = new StringBuilder();
        for (Object item : items) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(item.toString());
        }
        System.out.println(sb.toString());
    }

    private void setSummarize(boolean summarize) {
        this.summarize = summarize;
    }

    private void setAnnotate(boolean annotate) {
        this.annotate = annotate;
    }

    private void setFormat(boolean format) {
        this.format = format;
    }

    public void setRecover(boolean recover) {
        this.recover = recover;
    }
}
