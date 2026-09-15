package com.berryworks.edireader.framework.cli;

import com.berryworks.edireader.json.fromedi.EdiToJson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/** Converts UTF-8 EDI to formatted JSON. Existing output is overwritten;
 * a failed conversion may leave partial output. */
public class EdiToJsonCli {
    private static final String USAGE = """
            Usage: java -jar edi-to-json.jar <input.edi> <output.json>
                   java -jar edi-to-json.jar --help

            Convert an EDI file to JSON. Both files use UTF-8.
            Output is formatted; annotations and error recovery are disabled.
            Existing output is overwritten. Failed conversion may leave partial output.
            Input and output must be different files.

            -h, --help  Show this help (when used alone).
            Exit codes: 0 success, 1 conversion/I/O failure, 2 invalid arguments.
            """;

    public static void main(String[] args) {
        if (args.length == 1 && ("--help".equals(args[0]) || "-h".equals(args[0]))) {
            System.out.print(USAGE);
            return;
        }
        if (args.length != 2) {
            System.err.print(USAGE);
            System.exit(2);
        }

        try {
            convert(Path.of(args[0]), Path.of(args[1]));
        } catch (Exception e) {
            System.err.println("EDI to JSON failed: " +
                    (e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage()));
            System.exit(1);
        }
    }

    private static void convert(Path input, Path output) throws Exception {
        // isSameFile also detects symbolic links and hard links to the input.
        if (Files.exists(output) && Files.isSameFile(input, output)) {
            throw new IOException("Input and output must be different files.");
        }

        EdiToJson converter = new EdiToJson();
        converter.setFormatting(true);
        converter.setAnnotated(false);
        // Recovery is disabled by default: malformed EDI should fail.
        try (var reader = Files.newBufferedReader(input, StandardCharsets.UTF_8);
             var writer = Files.newBufferedWriter(output, StandardCharsets.UTF_8)) {
            converter.asJson(reader, writer);
        }
    }
}
