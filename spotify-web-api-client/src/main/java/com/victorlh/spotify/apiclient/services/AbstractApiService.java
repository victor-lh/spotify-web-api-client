package com.victorlh.spotify.apiclient.services;

import com.neovisionaries.i18n.CountryCode;
import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.credentials.TokenApiCredentials;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.exceptions.SpotifyWebApiClientException;
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

	protected URIBuilder getUriBuilder(String... pathSegments) {
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

	protected URI getUri(String... pathSegments) {
		URIBuilder uriBuilder = getUriBuilder(pathSegments);
		return getUri(uriBuilder);
	}

	protected URI getUri(URIBuilder uriBuilder) {
		try {
			return uriBuilder.build();
		} catch (URISyntaxException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		}
	}

	protected HttpResponseWrapper doGet(URI uri) throws SpotifyGeneralApiException {
		TokenApiCredentials tokenApiCredentials = spotifyApiClient.getTokenApiCredentials();
		HttpManager httpManger = HttpManager.createJsonHttpManger(tokenApiCredentials);
		try {
			HttpResponseWrapper response = httpManger.doGet(uri);
			if (log.isDebugEnabled()) {
				log.debug("Request URI: {}, status: {}, message: {}, data: {}", uri, response.getStatus(), response.getMessage(), response.responseBodyString());
			}
			return response;
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		} catch (SpotifyApiException e) {
			throw new SpotifyGeneralApiException(e.getResponse());
		}
	}

	protected HttpResponseWrapper doDelete(URI uri) throws SpotifyGeneralApiException {
		TokenApiCredentials tokenApiCredentials = spotifyApiClient.getTokenApiCredentials();
		HttpManager httpManger = HttpManager.createJsonHttpManger(tokenApiCredentials);
		try {
			HttpResponseWrapper response = httpManger.doDelete(uri);
			if (log.isDebugEnabled()) {
				log.debug("Request URI: {}, status: {}, message: {}, data: {}", uri, response.getStatus(), response.getMessage(), response.responseBodyString());
			}
			return response;
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		} catch (SpotifyApiException e) {
			throw new SpotifyGeneralApiException(e.getResponse());
		}
	}

	protected HttpResponseWrapper doPost(URI uri, Object data) throws SpotifyGeneralApiException {
		TokenApiCredentials tokenApiCredentials = spotifyApiClient.getTokenApiCredentials();
		HttpManager httpManger = HttpManager.createJsonHttpManger(tokenApiCredentials);
		try {
			HttpResponseWrapper response = httpManger.doPost(uri, data);
			if (log.isDebugEnabled()) {
				log.debug("Request URI: {}, status: {}, message: {}, data: {}", uri, response.getStatus(), response.getMessage(), response.responseBodyString());
			}
			return response;
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		} catch (SpotifyApiException e) {
			throw new SpotifyGeneralApiException(e.getResponse());
		}
	}

	protected HttpResponseWrapper doPut(URI uri, Object data) throws SpotifyGeneralApiException {
		TokenApiCredentials tokenApiCredentials = spotifyApiClient.getTokenApiCredentials();
		HttpManager httpManger = HttpManager.createJsonHttpManger(tokenApiCredentials);
		try {
			HttpResponseWrapper response = httpManger.doPut(uri, data);
			if (log.isDebugEnabled()) {
				log.debug("Request URI: {}, status: {}, message: {}, data: {}", uri, response.getStatus(), response.getMessage(), response.responseBodyString());
			}
			return response;
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		} catch (SpotifyApiException e) {
			throw new SpotifyGeneralApiException(e.getResponse());
		}
	}

	protected void addLimitToUriBuilder(URIBuilder uriBuilder, Integer limit) {
		addLimitToUriBuilder(uriBuilder,limit, 50);
	}

	protected void addLimitToUriBuilder(URIBuilder uriBuilder, Integer limit, Integer max) {
		if(limit != null) {
			if (limit < 1) {
				throw new SpotifyWebApiClientException("Limit minimum 1");
			} else if (limit > 50) {
				throw new SpotifyWebApiClientException("Limit maximum " + max);
			}
			uriBuilder.addParameter("limit", limit.toString());
		}
	}

	protected void addMarketToUriBuilder(URIBuilder uriBuilder, CountryCode market) {
		if(market != null) {
			uriBuilder.addParameter("market", market.getAlpha2());
		}
	}
}
