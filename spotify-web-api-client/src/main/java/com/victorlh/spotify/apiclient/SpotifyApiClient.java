package com.victorlh.spotify.apiclient;

import com.victorlh.spotify.apiclient.credentials.ClientApiCredentials;
import com.victorlh.spotify.apiclient.credentials.ClientApiPrincipal;
import com.victorlh.spotify.apiclient.credentials.Credentials;
import com.victorlh.spotify.apiclient.credentials.TokenApiCredentials;
import com.victorlh.spotify.apiclient.services.credentials.CredentialsApiService;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class SpotifyApiClient {

    @NonNull
    private final String apiClientId;
    private final String apiClientSecret;
    private final Credentials credentials;

    public ClientApiCredentials getClientApiCredentials() {
        ClientApiPrincipal clientApiPrincipal = new ClientApiPrincipal(apiClientId, apiClientSecret);
        return new ClientApiCredentials(clientApiPrincipal);
    }

    public TokenApiCredentials getTokenApiCredentials() {
        return new TokenApiCredentials(credentials);
    }


    public CredentialsApiService getCredentialService() {
        return CredentialsApiService.builder().spotifyApiClient(this).build();
    }
}
