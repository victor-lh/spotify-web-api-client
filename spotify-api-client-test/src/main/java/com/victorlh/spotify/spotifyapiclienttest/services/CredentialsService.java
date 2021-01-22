package com.victorlh.spotify.spotifyapiclienttest.services;

import com.victorlh.spotify.apiclient.credentials.Credentials;

import java.net.URI;

public interface CredentialsService {

	URI getUriAuthorization();

	Credentials changeCodeByCredentials(String code);
}
