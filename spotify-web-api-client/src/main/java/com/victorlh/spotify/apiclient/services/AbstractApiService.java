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

@Slf4j
@RequiredArgsConstructor
public class AbstractApiService {

	protected final SpotifyApiClient spotifyApiClient;
	protected final String url;

	public URI getUri(String... pathSegments) {
		try {
			URIBuilder uriBuilder = new URIBuilder(url).setPathSegments(pathSegments);
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
