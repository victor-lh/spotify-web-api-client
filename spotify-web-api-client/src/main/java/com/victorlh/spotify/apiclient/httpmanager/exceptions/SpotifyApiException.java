package com.victorlh.spotify.apiclient.httpmanager.exceptions;

import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;

public class SpotifyApiException extends Exception {

    private final HttpResponseWrapper response;

    public SpotifyApiException(HttpResponseWrapper response) {
        super(response.getMessage());
        this.response = response;
    }

    public int getStatusCode() {
        return response.getStatus();
    }

    public String getResponseMessage() {
        return response.getMessage();
    }

    public <T> T getResponseBody(Class<T> tClass) {
        return response.parseResponse(tClass);
    }

    @Override
    public String getLocalizedMessage() {
        return response.toString();
    }

    public HttpResponseWrapper getResponse() {
        return response;
    }
}
