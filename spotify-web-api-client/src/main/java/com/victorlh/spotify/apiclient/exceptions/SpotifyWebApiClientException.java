package com.victorlh.spotify.apiclient.exceptions;

public class SpotifyWebApiClientException extends RuntimeException {

	public SpotifyWebApiClientException() {
	}

	public SpotifyWebApiClientException(String message) {
		super(message);
	}

	public SpotifyWebApiClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public SpotifyWebApiClientException(Throwable cause) {
		super(cause);
	}
}
