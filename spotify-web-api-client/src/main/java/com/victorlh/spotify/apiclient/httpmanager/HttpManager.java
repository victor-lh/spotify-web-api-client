package com.victorlh.spotify.apiclient.httpmanager;

import com.victorlh.spotify.apiclient.credentials.SpotifyApiCredentials;
import com.victorlh.spotify.apiclient.httpmanager.exceptions.SpotifyApiException;

import java.io.IOException;
import java.net.URI;

public interface HttpManager {

	HttpResponseWrapper doGet(URI uri) throws IOException, SpotifyApiException;

	HttpResponseWrapper doPost(URI uri, Object body) throws IOException, SpotifyApiException;

	HttpResponseWrapper doPut(URI uri, Object body) throws IOException, SpotifyApiException;

	HttpResponseWrapper doDelete(URI uri) throws IOException, SpotifyApiException;

	static HttpManager createJsonHttpManger(SpotifyApiCredentials credentials) {
		return new JsonHttpManager(credentials);
	}

	static HttpManager createFormUrlEncodedHttpManger(SpotifyApiCredentials credentials) {
		return new FormUrlEncodedHttpManager(credentials);
	}
}
