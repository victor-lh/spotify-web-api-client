package com.victorlh.spotify.apiclient.httpmanager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;

import java.io.IOException;
import java.io.InputStream;

@Getter
@ToString
@Slf4j
public class HttpResponseWrapper {

	private final int status;
	private final String message;
	@Getter(AccessLevel.NONE)
	@ToString.Exclude
	private final HttpEntityWrapper entity;

	public HttpResponseWrapper(int status, String message, HttpEntity entity) {
		this.status = status;
		this.message = message;
		this.entity = new HttpEntityWrapper(entity);
	}

	public String responseBodyString() {
		byte[] data = entity.getData();
		return new String(data);
	}

	public <T> T parseResponse(Class<T> tClass) {
		byte[] data = entity.getData();
		if (data.length == 0) {
			return null;
		}
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(data, tClass);
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public boolean isError() {
		return status >= 400;
	}

	public <T> PagingObject<T> parseResponse(TypeReference<PagingObject<T>> typeReference) {
		byte[] data = entity.getData();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(data, typeReference);
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@RequiredArgsConstructor
	@Getter
	private static final class HttpEntityWrapper {

		private final HttpEntity entity;
		private byte[] data;

		public synchronized byte[] getData() {
			if (data == null) {
				try {
					InputStream bodyIS = getBodyIS();
					if (bodyIS == null) {
						data = new byte[0];
					} else {
						data = IOUtils.toByteArray(bodyIS);
					}
				} catch (IOException e) {
					log.error(e.getLocalizedMessage(), e);
					throw new RuntimeException(e);
				}
			}
			return data;
		}

		private InputStream getBodyIS() throws IOException {
			if (entity != null) {
				return entity.getContent();
			}
			return null;
		}
	}
}
