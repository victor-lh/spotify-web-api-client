package com.victorlh.spotify.spotifyapiclienttest.controllers;

import com.victorlh.spotify.apiclient.credentials.Credentials;
import com.victorlh.spotify.spotifyapiclienttest.services.CredentialsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CredentialsController {

	private final CredentialsService credentialsService;

	public CredentialsController(CredentialsService credentialsService) {
		this.credentialsService = credentialsService;
	}

	@GetMapping("/authorization")
	public String getAuthorizationUrl() {
		return credentialsService.getUriAuthorization().toString();
	}

	@GetMapping("/redirect")
	public Credentials redirectListener(@RequestParam("code") String code) {
		return credentialsService.changeCodeByCredentials(code);
	}
}
