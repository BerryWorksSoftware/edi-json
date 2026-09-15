package com.berryworks.edireader.framework.cli;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.yaml.snakeyaml.Yaml;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class EdiToJsonCliTest {
    @TempDir
    Path temp;

    @ParameterizedTest
    @ValueSource(strings = {"x12-824.edi", "x12-850.edi", "x12-837.edi", "edifact-invoic.edi", "hl7-adt.hl7"})
    void convertsSamplesAndOverwritesOutput(String sample) throws Exception {
        Path output = temp.resolve("output with spaces.json");
        Files.writeString(output, "old output that must be replaced");
        Result result = run(Path.of("samples", sample).toAbsolutePath().toString(), output.toString());
        assertEquals(0, result.status(), result.error());
        assertEquals("", result.stdout());
        assertEquals("", result.error());
        String json = Files.readString(output);
        assertTrue(json.strip().startsWith("{"), json);
        assertTrue(json.strip().endsWith("}"), json);
        assertTrue(json.contains("\n"), "Output should be formatted");
        // JSON is also valid YAML; use the Framework's existing YAML dependency.
        Object parsed = new Yaml().load(json);
        assertInstanceOf(Map.class, parsed);
        Map<?, ?> root = (Map<?, ?>) parsed;
        List<?> interchanges = assertInstanceOf(List.class, root.get("interchanges"));
        assertFalse(interchanges.isEmpty());
        String expectedValue = switch (sample) {
            case "x12-824.edi" -> "FFA.ABCDEF.123456";
            case "x12-850.edi" -> "DEMO-PO-001";
            case "x12-837.edi" -> "CLAIM0004";
            case "edifact-invoic.edi" -> "342459";
            case "hl7-adt.hl7" -> "DEMO-PATIENT-001";
            default -> throw new IllegalArgumentException(sample);
        };
        assertTrue(json.contains(expectedValue), "Conversion should retain sample data");
        assertFalse(json.contains("old output"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"--help", "-h"})
    void showsHelpSuccessfully(String option) throws Exception {
        Result result = run(option);
        assertEquals(0, result.status());
        assertTrue(result.stdout().contains("Usage: java -jar edi-to-json.jar"));
        assertTrue(result.stdout().contains("UTF-8"));
        assertTrue(result.stdout().contains("overwritten"));
        assertEquals("", result.error());
    }

    @Test
    void rejectsWrongArgumentCounts() throws Exception {
        for (String[] args : List.of(new String[0], new String[]{"input.edi"},
                new String[]{"input.edi", "output.json", "extra"})) {
            Result result = run(args);
            assertEquals(2, result.status());
            assertTrue(result.error().contains("Usage:"));
            assertEquals("", result.stdout());
        }
    }

    @Test
    void reportsMissingInputWithoutTouchingOutput() throws Exception {
        Path output = temp.resolve("output.json");
        Files.writeString(output, "keep me");
        Result result = run(temp.resolve("missing.edi").toString(), output.toString());
        assertEquals(1, result.status());
        assertTrue(result.error().contains("EDI to JSON failed:"));
        assertEquals("keep me", Files.readString(output));
    }

    @Test
    void reportsMalformedEdi() throws Exception {
        Path input = temp.resolve("invalid.edi");
        Files.writeString(input, "This is not EDI.");
        Result result = run(input.toString(), temp.resolve("output.json").toString());
        assertEquals(1, result.status());
        assertTrue(result.error().contains("EDI to JSON failed:"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"same", "normalized", "symbolic", "hard"})
    void protectsInputIncludingAliases(String kind) throws Exception {
        Path input = temp.resolve("input.edi");
        Files.copy(Path.of("samples/x12-824.edi"), input);
        byte[] original = Files.readAllBytes(input);
        Path output = switch (kind) {
            case "normalized" -> temp.resolve(".").resolve("input.edi");
            case "symbolic" -> Files.createSymbolicLink(temp.resolve("symbolic.edi"), input);
            case "hard" -> Files.createLink(temp.resolve("hard.edi"), input);
            default -> input;
        };
        Result result = run(input.toString(), output.toString());
        assertEquals(1, result.status());
        assertTrue(result.error().contains("must be different files"));
        assertArrayEquals(original, Files.readAllBytes(input));
    }

    private Result run(String... args) throws Exception {
        List<String> command = new ArrayList<>(List.of(
                Path.of(System.getProperty("java.home"), "bin", "java").toString()));
        String jar = System.getProperty("cli.jar");
        if (jar == null) {
            command.addAll(List.of("-cp", System.getProperty("java.class.path"), EdiToJsonCli.class.getName()));
        } else {
            Path standalone = temp.resolve("edi-to-json.jar");
            Files.copy(Path.of(jar), standalone, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            command.addAll(List.of("-jar", standalone.toString()));
        }
        command.addAll(List.of(args));
        Path stdout = Files.createTempFile(temp, "stdout", ".txt");
        Path stderr = Files.createTempFile(temp, "stderr", ".txt");
        Process process = new ProcessBuilder(command).directory(temp.toFile())
                .redirectOutput(stdout.toFile()).redirectError(stderr.toFile()).start();
        try {
            assertTrue(process.waitFor(30, TimeUnit.SECONDS), "CLI timed out");
            return new Result(process.exitValue(), Files.readString(stdout), Files.readString(stderr));
        } finally {
            process.destroyForcibly();
        }
    }

    private record Result(int status, String stdout, String error) {}
}
