package com.victorlh.spotify.apiclient.httpmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victorlh.spotify.apiclient.credentials.SpotifyApiCredentials;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;

@Slf4j
class ImageJpegHttpManager extends AbstractHttpManager {

	private final ObjectMapper objectMapper = new ObjectMapper();

	ImageJpegHttpManager(SpotifyApiCredentials credentials) {
		super(credentials);
	}

	@Override
	protected void addBodyEntity(HttpEntityEnclosingRequestBase httpRequest, Object data) {
		if (data instanceof byte[]) {
			ByteArrayEntity entity = new ByteArrayEntity((byte[]) data);
			httpRequest.setEntity(entity);
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	protected String getContentType() {
		return ContentType.IMAGE_JPEG.toString();
	}
}
