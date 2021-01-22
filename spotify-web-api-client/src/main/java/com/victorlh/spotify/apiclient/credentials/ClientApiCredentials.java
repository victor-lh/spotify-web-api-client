package com.victorlh.spotify.apiclient.credentials;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Base64;

@RequiredArgsConstructor
@Getter
public class ClientApiCredentials implements SpotifyApiCredentials {

	private final ClientApiPrincipal clientApiPrincipal;

	@Override
	public String authorizationHttpHeader() {
		String authPlain = clientApiPrincipal.getClientId() + ":" + clientApiPrincipal.getClientSecret();
		String auth = Base64.getEncoder().encodeToString(authPlain.getBytes(StandardCharsets.UTF_8));
		return "Basic " + auth;
	}

	@Override
	public Principal getUserPrincipal() {
		return clientApiPrincipal;
	}

	@Override
	public String getPassword() {
		return clientApiPrincipal.getClientSecret();
	}
}
