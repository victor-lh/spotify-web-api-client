package com.victorlh.spotify.apiclient.httpmanager;

import com.victorlh.spotify.apiclient.credentials.SpotifyApiCredentials;
import com.victorlh.spotify.apiclient.httpmanager.exceptions.SpotifyApiException;
import com.victorlh.spotify.apiclient.httpmanager.internal.HttpDeleteBody;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public abstract class AbstractHttpManager implements HttpManager {

	private final SpotifyApiCredentials credentials;

	@Override
	public HttpResponseWrapper doGet(URI uri) throws IOException, SpotifyApiException {
		HttpClient httpClient = createHttpClient();
		HttpGet httpGet = new HttpGet(uri);
		addAuthorizationHeader(httpGet);

		HttpResponse httpResponse = httpClient.execute(httpGet);
		return parserResponse(httpResponse);
	}

	@Override
	public HttpResponseWrapper doPost(URI uri, Object body) throws IOException, SpotifyApiException {
		HttpClient httpClient = createHttpClient();
		HttpPost httpPost = new HttpPost(uri);
		addAuthorizationHeader(httpPost);
		httpPost.addHeader(HttpHeaders.CONTENT_TYPE, getContentType());
		addBodyEntity(httpPost, body);

		HttpResponse httpResponse = httpClient.execute(httpPost);
		return parserResponse(httpResponse);
	}

	@Override
	public HttpResponseWrapper doPut(URI uri, Object body) throws IOException, SpotifyApiException {
		HttpClient httpClient = createHttpClient();
		HttpPut httpPut = new HttpPut(uri);
		addAuthorizationHeader(httpPut);
		httpPut.addHeader(HttpHeaders.CONTENT_TYPE, getContentType());
		addBodyEntity(httpPut, body);

		HttpResponse httpResponse = httpClient.execute(httpPut);
		return parserResponse(httpResponse);
	}

	@Override
	public HttpResponseWrapper doDelete(URI uri) throws IOException, SpotifyApiException {
		HttpClient httpClient = createHttpClient();
		HttpDelete httpDelete = new HttpDelete(uri);
		addAuthorizationHeader(httpDelete);

		HttpResponse httpResponse = httpClient.execute(httpDelete);
		return parserResponse(httpResponse);
	}

	@Override
	public HttpResponseWrapper doDelete(URI uri, Object body) throws IOException, SpotifyApiException {
		HttpClient httpClient = createHttpClient();
		HttpDeleteBody httpDelete = new HttpDeleteBody(uri);
		addAuthorizationHeader(httpDelete);
		httpDelete.addHeader(HttpHeaders.CONTENT_TYPE, getContentType());
		addBodyEntity(httpDelete, body);

		HttpResponse httpResponse = httpClient.execute(httpDelete);
		return parserResponse(httpResponse);
	}

	protected HttpClient createHttpClient() {
		return HttpClients.createDefault();
	}

	protected void addAuthorizationHeader(HttpRequest httpRequest) {
		if (credentials != null && credentials.getUserPrincipal() != null) {
			String value = credentials.authorizationHttpHeader();
			httpRequest.addHeader(HttpHeaders.AUTHORIZATION, value);
		}
	}

	protected HttpResponseWrapper parserResponse(HttpResponse httpResponse) throws SpotifyApiException {
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		String message = httpResponse.getStatusLine().getReasonPhrase();
		HttpEntity entity = httpResponse.getEntity();
		HttpResponseWrapper httpResponseWrapper = new HttpResponseWrapper(statusCode, message, entity);
		if (httpResponseWrapper.isError()) {
			throw new SpotifyApiException(httpResponseWrapper);
		}
		return httpResponseWrapper;
	}

	protected abstract void addBodyEntity(HttpEntityEnclosingRequestBase httpRequest, Object body);

	protected abstract String getContentType();

}
