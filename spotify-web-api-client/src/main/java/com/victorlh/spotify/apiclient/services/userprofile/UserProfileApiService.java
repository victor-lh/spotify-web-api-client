package com.victorlh.spotify.apiclient.services.userprofile;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.models.objects.PrivateUserObject;
import com.victorlh.spotify.apiclient.models.objects.PublicUserObject;
import com.victorlh.spotify.apiclient.services.AbstractApiService;
import lombok.Builder;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;

@Slf4j
public class UserProfileApiService extends AbstractApiService {

	private static final String ME_API_PATH = "me";
	private static final String USERS_API_PATH = "users";

	@Builder
	private UserProfileApiService(@NonNull SpotifyApiClient spotifyApiClient) {
		super(spotifyApiClient);
	}

	public PrivateUserObject getMeProfile() throws SpotifyGeneralApiException {
		log.trace("Call UserProfileApiService#getMeProfile");

		URI uri = getUri(ME_API_PATH);
		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(PrivateUserObject.class);
	}

	public PublicUserObject getUserProfile(String userId) throws SpotifyGeneralApiException {
		log.trace("Call UserProfileApiService#getUserProfile: {}", userId);
		URI uri = getUri(USERS_API_PATH, userId);
		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(PublicUserObject.class);
	}
}
