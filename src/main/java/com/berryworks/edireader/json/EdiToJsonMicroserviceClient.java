package com.berryworks.edireader.json;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;

public class EdiToJsonMicroserviceClient {
    private static final int MILLISECONDS_TIMEOUT = 60 * 1000;

    private final String server;
    private final String contextPath;
    private final String service;
    private final String url;

    public EdiToJsonMicroserviceClient(String server, String contextPath, String service) {
        this.server = server;
        this.contextPath = contextPath;
        this.service = service;
        this.url = "https://" + server + "/" + contextPath + "/" + service;
    }

    public String getServer() {
        return server;
    }

    public String getContextPath() {
        return contextPath;
    }

    public String getService() {
        return service;
    }

    public HttpResponse execute(String ediText) throws IOException {
        Request request = Request.Post(url).bodyString(ediText, ContentType.DEFAULT_TEXT);
        return request.execute().returnResponse();
    }

}

