package com.victorlh.spotify.apiclient.exceptions;

import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.httpmanager.exceptions.SpotifyApiException;
import com.victorlh.spotify.apiclient.models.errors.ErrorObject;

public class SpotifyGeneralApiException extends SpotifyApiException {

    public SpotifyGeneralApiException(HttpResponseWrapper response) {
        super(response);
    }

    public ErrorObject getErrorBody() {
        return this.getResponseBody(ErrorObject.class);
    }
}
