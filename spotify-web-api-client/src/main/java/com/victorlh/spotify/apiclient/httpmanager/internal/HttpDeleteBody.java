package com.victorlh.spotify.apiclient.httpmanager.internal;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

public class HttpDeleteBody extends HttpEntityEnclosingRequestBase {

	public final static String METHOD_NAME = "DELETE";

	public HttpDeleteBody() {
		super();
	}

	public HttpDeleteBody(final URI uri) {
		super();
		setURI(uri);
	}

	/**
	 * @throws IllegalArgumentException if the uri is invalid.
	 */
	public HttpDeleteBody(final String uri) {
		super();
		setURI(URI.create(uri));
	}

	@Override
	public String getMethod() {
		return METHOD_NAME;
	}
}
