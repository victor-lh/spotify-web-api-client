package com.victorlh.spotify.apiclient.services.userprofile;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.credentials.TokenApiCredentials;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.httpmanager.HttpManager;
import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.httpmanager.exceptions.SpotifyApiException;
import com.victorlh.spotify.apiclient.models.PrivateUserObject;
import com.victorlh.spotify.apiclient.models.PublicUserObject;
import lombok.Builder;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Builder
public class UserProfileApiService {

    private static final String DEFAULT_API_URI = "https://api.spotify.com";
    private static final String API_VERSION = "v1";
    private static final String ME_API_PATH = "me";
    private static final String USERS_API_PATH = "users";


    @NonNull
    private final SpotifyApiClient spotifyApiClient;
    @Builder.Default
    private final String apiUri = DEFAULT_API_URI;

    public PrivateUserObject getMeProfile() throws SpotifyGeneralApiException {
        log.trace("Call UserProfileApiService#getMeProfile");
        URI uri;
        try {
            URIBuilder uriBuilder = new URIBuilder(apiUri).setPathSegments(API_VERSION, ME_API_PATH);
            uri = uriBuilder.build();
            log.debug("Generada Me profile URL: {}", uri);
        } catch (URISyntaxException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e);
        }

        TokenApiCredentials tokenApiCredentials = spotifyApiClient.getTokenApiCredentials();
        HttpManager httpManger = HttpManager.createJsonHttpManger(tokenApiCredentials);
        HttpResponseWrapper response;
        try {
            response = httpManger.doGet(uri);
            log.debug("Request Me profile: {}", response.toString());
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e);
        } catch (SpotifyApiException e) {
            throw new SpotifyGeneralApiException(e.getResponse());
        }

        return response.parseResponse(PrivateUserObject.class);
    }

    public PublicUserObject getUserProfile(String userId) throws SpotifyGeneralApiException {
        log.trace("Call UserProfileApiService#getUserProfile: {}", userId);
        URI uri;
        try {
            URIBuilder uriBuilder = new URIBuilder(apiUri).setPathSegments(API_VERSION, USERS_API_PATH, userId);
            uri = uriBuilder.build();
            log.debug("Generada User profile URL: {}", uri);
        } catch (URISyntaxException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e);
        }

        TokenApiCredentials tokenApiCredentials = spotifyApiClient.getTokenApiCredentials();
        HttpManager httpManger = HttpManager.createJsonHttpManger(tokenApiCredentials);
        HttpResponseWrapper response;
        try {
            response = httpManger.doGet(uri);
            log.debug("Request User profile: {}", response.toString());
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e);
        } catch (SpotifyApiException e) {
            throw new SpotifyGeneralApiException(e.getResponse());
        }

        return response.parseResponse(PublicUserObject.class);
    }
}
