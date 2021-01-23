package com.victorlh.spotify.apiclient.services;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.credentials.TokenApiCredentials;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.httpmanager.HttpManager;
import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.httpmanager.exceptions.SpotifyApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class AbstractApiService {

	private static final String DEFAULT_API_URI = "https://api.spotify.com";
	private static final String API_VERSION = "v1";

	protected final SpotifyApiClient spotifyApiClient;

	public URIBuilder getUriBuilder(String... pathSegments) {
		ArrayList<String> paths = new ArrayList<>();
		paths.add(API_VERSION);
		paths.addAll(Arrays.asList(pathSegments));
		try {
			return new URIBuilder(DEFAULT_API_URI).setPathSegments(paths);
		} catch (URISyntaxException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public URI getUri(String... pathSegments) {
		URIBuilder uriBuilder = getUriBuilder(pathSegments);
		return getUri(uriBuilder);
	}

	public URI getUri(URIBuilder uriBuilder) {
		try {
			return uriBuilder.build();
		} catch (URISyntaxException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public HttpResponseWrapper doGet(URI uri) throws SpotifyGeneralApiException {
		TokenApiCredentials tokenApiCredentials = spotifyApiClient.getTokenApiCredentials();
		HttpManager httpManger = HttpManager.createJsonHttpManger(tokenApiCredentials);
		try {
			return httpManger.doGet(uri);
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		} catch (SpotifyApiException e) {
			throw new SpotifyGeneralApiException(e.getResponse());
		}
	}

	public HttpResponseWrapper doDelete(URI uri) throws SpotifyGeneralApiException {
		TokenApiCredentials tokenApiCredentials = spotifyApiClient.getTokenApiCredentials();
		HttpManager httpManger = HttpManager.createJsonHttpManger(tokenApiCredentials);
		try {
			return httpManger.doDelete(uri);
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		} catch (SpotifyApiException e) {
			throw new SpotifyGeneralApiException(e.getResponse());
		}
	}

	public HttpResponseWrapper doPost(URI uri, Object data) throws SpotifyGeneralApiException {
		TokenApiCredentials tokenApiCredentials = spotifyApiClient.getTokenApiCredentials();
		HttpManager httpManger = HttpManager.createJsonHttpManger(tokenApiCredentials);
		try {
			return httpManger.doPost(uri, data);
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		} catch (SpotifyApiException e) {
			throw new SpotifyGeneralApiException(e.getResponse());
		}
	}

	public HttpResponseWrapper doPut(URI uri, Object data) throws SpotifyGeneralApiException {
		TokenApiCredentials tokenApiCredentials = spotifyApiClient.getTokenApiCredentials();
		HttpManager httpManger = HttpManager.createJsonHttpManger(tokenApiCredentials);
		try {
			return httpManger.doPut(uri, data);
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		} catch (SpotifyApiException e) {
			throw new SpotifyGeneralApiException(e.getResponse());
		}
	}
}
