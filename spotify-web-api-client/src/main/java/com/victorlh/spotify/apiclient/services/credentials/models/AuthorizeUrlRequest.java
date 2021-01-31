package com.victorlh.spotify.apiclient.services.credentials.models;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AuthorizeUrlRequest {

	private final String redirectUri;
	private final String state;
	private final Boolean showDialog;
	@Singular
	private final List<AuthorizationScope> scopes;
}
