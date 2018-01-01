package com.berryworks.edireader.json;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ResourceUtil {

    public static String getResourceAsString(String resourceName) throws IOException {
        InputStream resourceAsStream = ResourceUtil.class.getClassLoader().getResourceAsStream(resourceName);
        if (resourceAsStream == null) {
            throw new RuntimeException("Cannot load resource " + resourceName);
        }
        byte[] bytes = getBytes(resourceAsStream);
        return StandardCharsets.UTF_8.decode(ByteBuffer.wrap(bytes)).toString();
    }

    private static byte[] getBytes(InputStream is) throws IOException {

        int len;
        int size = 1024;
        byte[] buf;

        if (is instanceof ByteArrayInputStream) {
            size = is.available();
            buf = new byte[size];
            len = is.read(buf, 0, size);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            buf = new byte[size];
            while ((len = is.read(buf, 0, size)) != -1)
                bos.write(buf, 0, len);
            buf = bos.toByteArray();
        }
        return buf;
    }

}
