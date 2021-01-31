package com.victorlh.spotify.apiclient.httpmanager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victorlh.spotify.apiclient.credentials.SpotifyApiCredentials;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

@Slf4j
class JsonHttpManager extends AbstractHttpManager {

	private final ObjectMapper objectMapper = new ObjectMapper();

	JsonHttpManager(SpotifyApiCredentials credentials) {
		super(credentials);
	}

	@Override
	protected void addBodyEntity(HttpEntityEnclosingRequestBase httpRequest, Object body) {
		try {
			String json = objectMapper.writeValueAsString(body);
			if(log.isTraceEnabled()) {
				log.trace("Request Body: {}", json);
			}
			StringEntity stringEntity = new StringEntity(json);
			httpRequest.setEntity(stringEntity);
		} catch (JsonProcessingException | UnsupportedEncodingException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Override
	protected String getContentType() {
		return ContentType.APPLICATION_JSON.toString();
	}
}
