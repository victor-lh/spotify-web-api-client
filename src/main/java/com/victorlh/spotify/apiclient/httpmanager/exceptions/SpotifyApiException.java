package com.victorlh.spotify.apiclient.httpmanager.exceptions;

import com.victorlh.spotify.apiclient.httpmanager.ErrorObject;
import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;

import java.io.IOException;

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

	public Object getResponseBody() {
		return response.parseResponse(ErrorObject.class);
	}

	@Override
	public String getLocalizedMessage() {
		return response.toString();
	}

}
