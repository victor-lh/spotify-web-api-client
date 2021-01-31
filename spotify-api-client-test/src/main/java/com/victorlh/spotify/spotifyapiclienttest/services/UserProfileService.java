package com.victorlh.spotify.spotifyapiclienttest.services;

import com.victorlh.spotify.apiclient.models.objects.PrivateUserObject;
import com.victorlh.spotify.apiclient.models.objects.PublicUserObject;

public interface UserProfileService {

    PrivateUserObject getMeUserProfile();

    PublicUserObject getUserProfile(String userId);
}
