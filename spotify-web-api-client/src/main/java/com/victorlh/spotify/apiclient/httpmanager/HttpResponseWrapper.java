package com.victorlh.spotify.apiclient.httpmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;

import java.io.IOException;
import java.io.InputStream;

@Getter
@ToString
@RequiredArgsConstructor
@Slf4j
public class HttpResponseWrapper {

	private final int status;
	private final String message;
	@Getter(AccessLevel.NONE)
	@ToString.Exclude
	private final HttpEntity entity;

	public <T> T parseResponse(Class<T> tClass) {
		try {
			if (entity == null) {
				return null;
			}
			if (entity.getContentLength() == 0) {
				return null;
			}
			InputStream content = entity.getContent();
			if (content == null) {
				return null;
			}

			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(content, tClass);
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public boolean isError() {
		return status >= 400;
	}
}