package com.victorlh.spotify.apiclient.credentials;

import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Principal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credentials implements Principal {

	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("token_type")
	private String tokenType;
	private String scope;
	@JsonProperty("expires_in")
	private Integer expiresIn;
	@JsonProperty("refresh_token")
	private String refreshToken;

	@Override
	public String getName() {
		return getAccessToken();
	}
}
