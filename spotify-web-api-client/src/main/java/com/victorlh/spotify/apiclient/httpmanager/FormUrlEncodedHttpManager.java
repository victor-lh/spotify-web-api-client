package com.victorlh.spotify.apiclient.httpmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victorlh.spotify.apiclient.credentials.SpotifyApiCredentials;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
class FormUrlEncodedHttpManager extends AbstractHttpManager {

	private final ObjectMapper objectMapper = new ObjectMapper();

	FormUrlEncodedHttpManager(SpotifyApiCredentials credentials) {
		super(credentials);
	}

	@Override
	protected void addBodyEntity(HttpEntityEnclosingRequestBase httpRquest, Object body) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = objectMapper.convertValue(body, Map.class);

		List<NameValuePair> parameters = map.entrySet().stream().map(this::transformPropertie).collect(Collectors.toList());
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters);
			httpRquest.setEntity(entity);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getLocalizedMessage(), e);
			throw new RuntimeException(e);
		}
	}

	private NameValuePair transformPropertie(Map.Entry<String, Object> entry) {
		Object value = entry.getValue();
		String val = value == null ? null : String.valueOf(value);
		return new BasicNameValuePair(entry.getKey(), val);
	}

	@Override
	protected String getContentType() {
		return ContentType.APPLICATION_FORM_URLENCODED.toString();
	}
}
