package com.victorlh.spotify.spotifyapiclienttest.services.impl;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.models.objects.PrivateUserObject;
import com.victorlh.spotify.apiclient.models.objects.PublicUserObject;
import com.victorlh.spotify.apiclient.services.userprofile.UserProfileApiService;
import com.victorlh.spotify.spotifyapiclienttest.exceptions.SpotifyApiExceptionRuntime;
import com.victorlh.spotify.spotifyapiclienttest.services.SpotifyClientService;
import com.victorlh.spotify.spotifyapiclienttest.services.UserProfileService;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final SpotifyClientService spotifyClientService;

    public UserProfileServiceImpl(SpotifyClientService spotifyClientService) {
        this.spotifyClientService = spotifyClientService;
    }

    @Override
    public PrivateUserObject getMeUserProfile() {
        SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
        UserProfileApiService userProfileApiService = spotifyApiClient.getUserProfileApiService();
        try {
            return userProfileApiService.getMeProfile();
        } catch (SpotifyGeneralApiException e) {
            throw new SpotifyApiExceptionRuntime(e);
        }
    }

    @Override
    public PublicUserObject getUserProfile(String userId) {
        SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
        UserProfileApiService userProfileApiService = spotifyApiClient.getUserProfileApiService();
        try {
            return userProfileApiService.getUserProfile(userId);
        } catch (SpotifyGeneralApiException e) {
            throw new SpotifyApiExceptionRuntime(e);
        }
    }
}
