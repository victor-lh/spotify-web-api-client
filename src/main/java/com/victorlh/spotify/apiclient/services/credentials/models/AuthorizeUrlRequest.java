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

	private String redirectUri;
	private String state;
	private Boolean showDialog;
	@Singular
	private List<AuthorizationScope> scopes;
}
