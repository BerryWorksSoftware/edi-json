package com.berryworks.edireader.json;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.berryworks.edireader.json.ResourceUtil.getResourceAsString;
import static org.junit.Assert.assertEquals;

/**
 * Illustrates and tests the EDI to JSON transformation deployed as a microservice
 * via Amazon's AWS Lambda.
 *
 * The particular service referenced in this test is simply an example and may not be
 * consistently available (hence the @Ignore). If you are interested in BerryWorks hosting
 * a service of this type on your behalf, with appropriate authentication and security,
 * please contact json@canabrook.org.
 */
public class EdiToJsonMicroserviceClientTest {
    private static final String SERVER = "kxfqaddyyc.execute-api.us-east-2.amazonaws.com";
    private static final String CONTEXT_PATH = "Preview/berryworks";
    private static final String SERVICE = "edi-to-json";
    private EdiToJsonMicroserviceClient client;
    private HttpResponse response;

    @Ignore
    @Test
    public void canExecuteWithEdiString() throws IOException {
        // Setup
        client = new EdiToJsonMicroserviceClient(SERVER, CONTEXT_PATH, SERVICE);
        String ediText = getResourceAsString("824.edi");

        // Call the microservice
        response = client.execute(ediText);

        // Confirm response
        assertEquals(200, response.getStatusLine().getStatusCode());
        final HttpEntity entity = response.getEntity();
        assertEquals("application/json", entity.getContentType().getValue());
        final String actual = asString(entity.getContent());
        final String expected = getResourceAsString("824.json");
        assertEquals(expected, actual);
    }

    @Ignore
    @Test
    public void canExecuteWithEdifact() throws IOException {
        // Setup
        client = new EdiToJsonMicroserviceClient(SERVER, CONTEXT_PATH, SERVICE);
        String ediText = getResourceAsString("INVOIC.edi");

        // Call the microservice
        response = client.execute(ediText);

        // Confirm response
        assertEquals(200, response.getStatusLine().getStatusCode());
        final HttpEntity entity = response.getEntity();
        assertEquals("application/json", entity.getContentType().getValue());
        final String actual = asString(entity.getContent());
        final String expected = getResourceAsString("INVOIC.json");
        assertEquals(expected, actual);
    }

    private String asString(InputStream inputStream) throws IOException {
        StringBuilder result = new StringBuilder();
        char[] buffer = new char[1000];
        try (InputStreamReader isr = new InputStreamReader(inputStream)) {
            int n;
            while ((n = isr.read(buffer)) > -1) {
                result.append(buffer, 0, n);
            }
        }
        return result.toString();
    }
}
