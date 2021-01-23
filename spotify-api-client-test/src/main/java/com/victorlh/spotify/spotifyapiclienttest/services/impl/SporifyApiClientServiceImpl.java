package com.victorlh.spotify.spotifyapiclienttest.services.impl;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.spotifyapiclienttest.CredentialsWrapper;
import com.victorlh.spotify.spotifyapiclienttest.services.SpotifyClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SporifyApiClientServiceImpl implements SpotifyClientService {

	@Value("${spotify.clientId}")
	private String clientId;

	@Value("${spotify.clientSecret}")
	private String clientSecret;

	private final CredentialsWrapper credentialsWrapper;

	public SporifyApiClientServiceImpl(CredentialsWrapper credentialsWrapper) {
		this.credentialsWrapper = credentialsWrapper;
	}

	@Override
	public SpotifyApiClient getSpotifyApiClient() {
		return SpotifyApiClient.builder()
				.apiClientId(clientId)
				.apiClientSecret(clientSecret)
				.credentials(credentialsWrapper.getCredentials())
				.build();
	}
}
