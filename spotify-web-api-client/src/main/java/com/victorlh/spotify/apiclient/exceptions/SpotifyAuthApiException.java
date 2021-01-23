package com.victorlh.spotify.apiclient.exceptions;

import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.httpmanager.exceptions.SpotifyApiException;
import com.victorlh.spotify.apiclient.httpmanager.models.AuthErrorObject;

public class SpotifyAuthApiException extends SpotifyApiException {

    public SpotifyAuthApiException(HttpResponseWrapper response) {
        super(response);
    }

    public AuthErrorObject getAuthErrorBody() {
        return this.getResponseBody(AuthErrorObject.class);
    }
}
