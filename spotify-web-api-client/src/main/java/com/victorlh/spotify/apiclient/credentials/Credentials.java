package com.victorlh.spotify.apiclient.credentials;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Principal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Credentials implements Principal {

	private String accessToken;
	private String tokenType;
	private String scope;
	private Integer expiresIn;
	private String refreshToken;

	@Override
	public String getName() {
		return getAccessToken();
	}
}
