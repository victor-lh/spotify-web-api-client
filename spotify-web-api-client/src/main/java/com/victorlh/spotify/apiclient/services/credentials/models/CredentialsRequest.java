package com.victorlh.spotify.apiclient.services.credentials.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CredentialsRequest {

	private final String code;
	private final String redirectUri;
}
