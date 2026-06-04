package com.berryworks.edireader.json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    public static String fileToString(String filename) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(filename));
        return StandardCharsets.UTF_8.decode(ByteBuffer.wrap(encoded)).toString();
    }

    public static void stringToFile(String data, String filename) throws IOException {
        try (Writer writer = new FileWriter(filename)) {
            writer.write(data);
        }
    }

    public static byte[] fileToByteArray(File file) throws IOException {
        return Files.readAllBytes(Paths.get(file.getPath()));
    }

    public static File locateDirectory(String directoryName) {

        File directory = new File(directoryName);
        if (directory.exists() && directory.isDirectory()) {
            return directory;
        }

        directory = new File("../" + directoryName);
        if (directory.exists() && directory.isDirectory()) {
            return directory;
        }

        throw new RuntimeException("Cannot locate directory " + directoryName);
    }

}
