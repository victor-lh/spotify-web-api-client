package com.victorlh.spotify.apiclient.services.credentials;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.services.credentials.models.AuthorizationScope;
import com.victorlh.spotify.apiclient.services.credentials.models.AuthorizeUrlRequest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CredentialsService {

	private static final String DEFAULT_AUTH_URI = "https://accounts.spotify.com";
	private static final String AUTHORIZE_PATH = "authorize";

	@NonNull
	private final SpotifyApiClient spotifyApiClient;
	private final String authUri;

	public CredentialsService(SpotifyApiClient spotifyApiClient) {
		this(spotifyApiClient, DEFAULT_AUTH_URI);
	}

	public URI authorizeUrl(AuthorizeUrlRequest authorizeUrlRequest) {
		log.trace("Call CredentialsService#authorizeUrl: {}", authorizeUrlRequest);

		try {
			URIBuilder uriBuilder = new URIBuilder(authUri).setPathSegments(AUTHORIZE_PATH);

			uriBuilder = addClientId(uriBuilder);
			uriBuilder = addResponseType(uriBuilder, "code");

			URI uri = uriBuilder.build();

			log.debug("Generada Authorize URL: {}", uri);
			return uri;
		} catch (URISyntaxException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		}

	}

	private URIBuilder addClientId(URIBuilder uriBuilder) {
		String apiClientId = spotifyApiClient.getApiClientId();
		if (StringUtils.isBlank(apiClientId)) {
			throw new IllegalArgumentException("apiClientId is required");
		}
		return uriBuilder.addParameter("client_id", apiClientId);
	}

	private URIBuilder addResponseType(URIBuilder uriBuilder, String responseType) {
		if (StringUtils.isNotBlank(responseType)) {
			return uriBuilder.addParameter("response_type", responseType);
		}
		return uriBuilder;
	}

	private URIBuilder addRedirectUri(URIBuilder uriBuilder, String redirecUri) {
		if (StringUtils.isNotBlank(redirecUri)) {
			return uriBuilder.addParameter("redirect_uri", redirecUri);
		}
		return uriBuilder;
	}

	private URIBuilder addState(URIBuilder uriBuilder, String state) {
		if (StringUtils.isNotBlank(state)) {
			return uriBuilder.addParameter("state", state);
		}
		return uriBuilder;
	}

	private URIBuilder addShowDialog(URIBuilder uriBuilder, Boolean showDialog) {
		if (showDialog != null) {
			return uriBuilder.addParameter("show_dialog", showDialog.toString());
		}
		return uriBuilder;
	}
	
	private URIBuilder addScope(URIBuilder uriBuilder, List<AuthorizationScope> scopes) {
//		if (scopes != null && !scopes.isEmpty()) {
//			return uriBuilder.addParameter("show_dialog", showDialog.toString());
//		}
		return uriBuilder;
	}
}
