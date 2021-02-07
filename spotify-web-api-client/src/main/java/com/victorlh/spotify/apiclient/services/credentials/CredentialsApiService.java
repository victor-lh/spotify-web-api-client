package com.victorlh.spotify.apiclient.services.credentials;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.credentials.ClientApiCredentials;
import com.victorlh.spotify.apiclient.credentials.Credentials;
import com.victorlh.spotify.apiclient.exceptions.SpotifyAuthApiException;
import com.victorlh.spotify.apiclient.exceptions.SpotifyWebApiClientException;
import com.victorlh.spotify.apiclient.httpmanager.HttpManager;
import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.httpmanager.exceptions.SpotifyApiException;
import com.victorlh.spotify.apiclient.services.credentials.models.AuthorizationScope;
import com.victorlh.spotify.apiclient.services.credentials.models.AuthorizeUrlRequest;
import com.victorlh.spotify.apiclient.services.credentials.models.CredentialsRequest;
import lombok.Builder;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Builder
public class CredentialsApiService {

    private static final String DEFAULT_AUTH_URI = "https://accounts.spotify.com";
    private static final String AUTHORIZE_PATH = "authorize";
    private static final String API_PATH = "api";
    private static final String TOKEN_PATH = "token";

    @NonNull
    private final SpotifyApiClient spotifyApiClient;
    @Builder.Default
    private final String authUri = DEFAULT_AUTH_URI;


    public URI authorizeUrl(AuthorizeUrlRequest authorizeUrlRequest) {
        log.trace("Call CredentialsService#authorizeUrl: {}", authorizeUrlRequest);

        try {
            URIBuilder uriBuilder = new URIBuilder(authUri).setPathSegments(AUTHORIZE_PATH);

            addClientId(uriBuilder);
            addResponseType(uriBuilder, "code");
            addRedirectUri(uriBuilder, authorizeUrlRequest.getRedirectUri());
            addState(uriBuilder, authorizeUrlRequest.getState());
            addScope(uriBuilder, authorizeUrlRequest.getScopes());
            addShowDialog(uriBuilder, authorizeUrlRequest.getShowDialog());

            URI uri = uriBuilder.build();

            log.debug("Generada Authorize URL: {}", uri);
            return uri;
        } catch (URISyntaxException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public Credentials requestToken(CredentialsRequest credentialsRequest) throws SpotifyAuthApiException {
        log.trace("Call CredentialsService#requestToken: {}", credentialsRequest);

        HashMap<String, String> data = new HashMap<>();
        data.put("grant_type", "authorization_code");
        data.put("code", credentialsRequest.getCode());
        data.put("redirect_uri", credentialsRequest.getRedirectUri());

        URI uri;
        try {
            URIBuilder uriBuilder = new URIBuilder(authUri).setPathSegments(API_PATH, TOKEN_PATH);
            uri = uriBuilder.build();
            log.debug("Generada Get Token URL: {}", uri);
        } catch (URISyntaxException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e);
        }

        ClientApiCredentials clientApiCredentials = spotifyApiClient.getClientApiCredentials();
        HttpManager httpManger = HttpManager.createFormUrlEncodedHttpManger(clientApiCredentials);
        HttpResponseWrapper response;
        try {
            response = httpManger.doPost(uri, data);
            log.debug("Request token response: {}", response.toString());
            log.trace("Spotify token: {}", response.responseBodyString());
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e);
        } catch (SpotifyApiException e) {
            throw new SpotifyAuthApiException(e.getResponse());
        }

        return response.parseResponse(Credentials.class);
    }

    public Credentials refreshToken() throws SpotifyAuthApiException {
        Credentials credentials = this.spotifyApiClient.getCredentials();
        log.trace("Call CredentialsService#refreshToken: {}", credentials);

        //Validation
        if (credentials == null || StringUtils.isEmpty(credentials.getRefreshToken())) {
            throw new SpotifyWebApiClientException("refresh_token not found");
        }

        String refreshToken = credentials.getRefreshToken();

        HashMap<String, String> data = new HashMap<>();
        data.put("grant_type", "refresh_token");
        data.put("refresh_token", refreshToken);

        URI uri;
        try {
            URIBuilder uriBuilder = new URIBuilder(authUri).setPathSegments(API_PATH, TOKEN_PATH);
            uri = uriBuilder.build();
            log.debug("Generada refresh_token URL: {}", uri);
        } catch (URISyntaxException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e);
        }

        ClientApiCredentials clientApiCredentials = spotifyApiClient.getClientApiCredentials();
        HttpManager httpManger = HttpManager.createFormUrlEncodedHttpManger(clientApiCredentials);
        HttpResponseWrapper response;
        try {
            response = httpManger.doPost(uri, data);
            log.debug("Request token by refresh_token response: {}", response.toString());
            log.trace("Spotify token by refresh_token: {}", response.responseBodyString());
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e);
        } catch (SpotifyApiException e) {
			throw new SpotifyAuthApiException(e.getResponse());
		}

        return response.parseResponse(Credentials.class);
    }

    private void addClientId(URIBuilder uriBuilder) {
        String apiClientId = spotifyApiClient.getApiClientId();
        if (StringUtils.isBlank(apiClientId)) {
            throw new IllegalArgumentException("apiClientId is required");
        }
        uriBuilder.addParameter("client_id", apiClientId);
    }

    private void addResponseType(URIBuilder uriBuilder, String responseType) {
        if (StringUtils.isNotBlank(responseType)) {
            uriBuilder.addParameter("response_type", responseType);
        }
    }

    private void addRedirectUri(URIBuilder uriBuilder, String redirectUri) {
        if (StringUtils.isNotBlank(redirectUri)) {
            uriBuilder.addParameter("redirect_uri", redirectUri);
        }
    }

    private void addState(URIBuilder uriBuilder, String state) {
        if (StringUtils.isNotBlank(state)) {
            uriBuilder.addParameter("state", state);
        }
    }

    private void addShowDialog(URIBuilder uriBuilder, Boolean showDialog) {
        if (showDialog != null) {
            uriBuilder.addParameter("show_dialog", showDialog.toString());
        }
    }

    private void addScope(final URIBuilder uriBuilder, List<AuthorizationScope> scopes) {
        if (scopes != null && !scopes.isEmpty()) {
            final StringBuilder sb = new StringBuilder();
            scopes.forEach(s -> sb.append(s.scope).append(" "));
            String scopesUrl = sb.toString().trim();
            uriBuilder.addParameter("scope", scopesUrl);
        }
    }
}
