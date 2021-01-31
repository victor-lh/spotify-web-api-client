package com.victorlh.spotify.spotifyapiclienttest.controllers;

import com.victorlh.spotify.apiclient.models.objects.PrivateUserObject;
import com.victorlh.spotify.apiclient.models.objects.PublicUserObject;
import com.victorlh.spotify.spotifyapiclienttest.services.UserProfileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/me")
    private PrivateUserObject getMeUserProfile() {
        return userProfileService.getMeUserProfile();
    }

    @GetMapping("/users/{userId}")
    private PublicUserObject getUserProfile(@PathVariable("userId") String userId) {
        return userProfileService.getUserProfile(userId);
    }
}
