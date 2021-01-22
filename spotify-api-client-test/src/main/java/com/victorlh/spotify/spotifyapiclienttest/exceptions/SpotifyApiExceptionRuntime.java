package com.victorlh.spotify.spotifyapiclienttest.exceptions;

import com.victorlh.spotify.apiclient.httpmanager.exceptions.SpotifyApiException;
import lombok.Getter;

@Getter
public class SpotifyApiExceptionRuntime extends RuntimeException {

	private final SpotifyApiException spotifyApiException;

	public SpotifyApiExceptionRuntime(SpotifyApiException spotifyApiException) {
		this.spotifyApiException = spotifyApiException;
	}
}
