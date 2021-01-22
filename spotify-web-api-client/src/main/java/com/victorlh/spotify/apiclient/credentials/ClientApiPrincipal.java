package com.victorlh.spotify.apiclient.credentials;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Principal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientApiPrincipal implements Principal {

	private String clientId;
	private String clientSecret;

	@Override
	public String getName() {
		return getClientId();
	}
}
