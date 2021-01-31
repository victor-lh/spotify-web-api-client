package com.victorlh.spotify.spotifyapiclienttest.services.impl;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.credentials.Credentials;
import com.victorlh.spotify.apiclient.httpmanager.exceptions.SpotifyApiException;
import com.victorlh.spotify.apiclient.services.credentials.CredentialsApiService;
import com.victorlh.spotify.apiclient.services.credentials.models.AuthorizationScope;
import com.victorlh.spotify.apiclient.services.credentials.models.AuthorizeUrlRequest;
import com.victorlh.spotify.apiclient.services.credentials.models.CredentialsRequest;
import com.victorlh.spotify.spotifyapiclienttest.exceptions.SpotifyApiExceptionRuntime;
import com.victorlh.spotify.spotifyapiclienttest.services.CredentialsService;
import com.victorlh.spotify.spotifyapiclienttest.CredentialsWrapper;
import com.victorlh.spotify.spotifyapiclienttest.services.SpotifyClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;

@Service
@Slf4j
public class CredentialsServiceImpl implements CredentialsService {

	@Value("${spotify.redirectUrl}")
	private String redirectUrl;

	private final CredentialsWrapper credentialsWrapper;
	private final SpotifyClientService spotifyClientService;

	public CredentialsServiceImpl(CredentialsWrapper credentialsWrapper, SpotifyClientService spotifyClientService) {
		this.credentialsWrapper = credentialsWrapper;
		this.spotifyClientService = spotifyClientService;
	}

	@Override
	public URI getUriAuthorization() {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		CredentialsApiService credentialService = spotifyApiClient.getCredentialService();
		AuthorizeUrlRequest authorizeUrlRequest = AuthorizeUrlRequest.builder()
				.scopes(Arrays.asList(AuthorizationScope.values()))
				.redirectUri(redirectUrl)
				.build();

		return credentialService.authorizeUrl(authorizeUrlRequest);
	}

	@Override
	public Credentials changeCodeByCredentials(String code) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		CredentialsApiService credentialService = spotifyApiClient.getCredentialService();

		CredentialsRequest credentialsRequest = CredentialsRequest.builder()
				.code(code)
				.redirectUri(redirectUrl)
				.build();

		Credentials credentials;
		try {
			credentials = credentialService.requestToken(credentialsRequest);
		} catch (SpotifyApiException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new SpotifyApiExceptionRuntime(e);
		}
		credentialsWrapper.setCredentials(credentials);
		return credentials;
	}

	@Override
	public Credentials refreshToken() {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		CredentialsApiService credentialService = spotifyApiClient.getCredentialService();
		Credentials credentials;
		try {
			credentials = credentialService.refreshToken();
		} catch (SpotifyApiException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new SpotifyApiExceptionRuntime(e);
		}
		credentialsWrapper.setCredentials(credentials);
		return credentials;
	}
}
