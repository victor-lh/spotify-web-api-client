package com.victorlh.spotify.spotifyapiclienttest.services;

import com.victorlh.spotify.apiclient.models.PrivateUserObject;
import com.victorlh.spotify.apiclient.models.PublicUserObject;

public interface UserProfileService {

    PrivateUserObject getMeUserProfile();

    PublicUserObject getUserProfile(String userId);
}
