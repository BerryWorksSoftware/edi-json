package com.berryworks.edireader.json.commandline;

import com.berryworks.edireader.json.fromedi.EdiToJson;

import java.io.*;

public class EdiToJsonCli implements Runnable {
    private File ediFile, jsonFile;
    private boolean annotate, format, recover;

    public EdiToJsonCli() {
        // Set defaults for options
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
        if (recover) {
            ediToJson.setRecover();
        }
        try (Reader reader = new BufferedReader(new FileReader(ediFile));
             Writer writer = new BufferedWriter(new FileWriter(jsonFile))) {
            ediToJson.asJson(reader, writer);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {

        final EdiToJsonCli driver = new EdiToJsonCli();

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
        log("""
                
                Usage Summary
                =============
                
                Read EDI from an input file, write JSON to an output file
                  java -jar <jarFileName>  <ediInputFile>  <jsonOutputFile>  <options>
                
                Read EDI from an input file, write JSON to stdout
                  java -jar <jarFileName>  <ediInputFile>  <options>
                
                Read EDI from stdin, write JSON to stdout
                  java -jar <jarFileName>  <options>
                
                Display this usage summary
                  java -jar <jarFileName>  help
                
                
                options
                   --annotate={yes|no} : if yes, include descriptive "annotations" (default is no)
                   --format={yes|no} : if yes, format JSON output (default is yes)
                   --recover={yes|no} : if yes, ignore any recoverable EDI errors (default is no)
                
                """);
    }

    private static void log(Object... items) {
        StringBuilder sb = new StringBuilder();
        for (Object item : items) {
            if (!sb.isEmpty()) {
                sb.append(" ");
            }
            sb.append(item.toString());
        }
        System.out.println(sb);
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
