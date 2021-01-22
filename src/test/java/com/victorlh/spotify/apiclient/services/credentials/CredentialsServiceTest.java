package com.victorlh.spotify.apiclient.services.credentials;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.services.credentials.models.AuthorizationScope;
import com.victorlh.spotify.apiclient.services.credentials.models.AuthorizeUrlRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CredentialsServiceTest {

	private static final String API_CLIENT_ID = "123456789";
	private static final String URI_ESPERADA = "https://accounts.spotify.com/authorize?client_id=123456789&response_type=code&redirect_uri=http%3A%2F%2Fvictorlh.com%2Fspotify&state=asd&scope=app-remote-control+streaming&show_dialog=false";

	private SpotifyApiClient spotifyApiClient;

	@BeforeEach
	void setUp() {
		spotifyApiClient = SpotifyApiClient.builder()
				.apiClientId(API_CLIENT_ID)
				.build();
	}

	@Test
	void test_valida_construccion_url_authorizacion_dado_parametros() {
		CredentialsService credentialsService = CredentialsService.builder()
				.spotifyApiClient(spotifyApiClient)
				.build();
		AuthorizeUrlRequest authorizeUrlRequest = AuthorizeUrlRequest.builder()
				.redirectUri("http://victorlh.com/spotify")
				.showDialog(false)
				.state("asd")
				.scope(AuthorizationScope.APP_REMOTE_CONTROL)
				.scope(AuthorizationScope.STREAMING)
				.build();

		URI uri = credentialsService.authorizeUrl(authorizeUrlRequest);

		assertEquals(URI_ESPERADA, uri.toString(), "Las url deben ser iguales");
	}
}