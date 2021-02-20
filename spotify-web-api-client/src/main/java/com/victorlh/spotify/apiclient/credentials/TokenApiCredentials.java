package com.victorlh.spotify.apiclient.credentials;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.security.Principal;

@RequiredArgsConstructor
@Getter
public class TokenApiCredentials implements SpotifyApiCredentials {

	private final Credentials credentials;

	@Override
	public String authorizationHttpHeader() {
		String tokenType = credentials.getTokenType();
		if(StringUtils.isEmpty(tokenType)) {
			tokenType = "Bearer";
		}
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
