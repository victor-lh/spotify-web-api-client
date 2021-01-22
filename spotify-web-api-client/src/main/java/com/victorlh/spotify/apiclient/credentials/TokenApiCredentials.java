package com.victorlh.spotify.apiclient.credentials;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.security.Principal;

@RequiredArgsConstructor
@Getter
public class TokenApiCredentials implements SpotifyApiCredentials {

	private final Credentials credentials;

	@Override
	public String authorizationHttpHeader() {
		String tokenType = credentials.getTokenType();
		String accessToken = credentials.getAccessToken();
		return tokenType + " " + accessToken;
	}

	@Override
	public Principal getUserPrincipal() {
		return credentials;
	}

	@Override
	public String getPassword() {
		return credentials.getAccessToken();
	}
}
