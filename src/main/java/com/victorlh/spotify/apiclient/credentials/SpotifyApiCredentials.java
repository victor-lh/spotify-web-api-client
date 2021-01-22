package com.victorlh.spotify.apiclient.credentials;

import org.apache.http.auth.Credentials;

public interface SpotifyApiCredentials extends Credentials {

	String authorizationHttpHeader();

}
