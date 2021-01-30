package com.victorlh.spotify.spotifyapiclienttest.controllers;

import com.victorlh.spotify.apiclient.models.pagination.ListArtistsCursorPagingObject;
import com.victorlh.spotify.apiclient.services.follow.models.*;
import com.victorlh.spotify.spotifyapiclienttest.services.FollowService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class FollowController {

	private final FollowService followService;

	@Autowired
	public FollowController(FollowService followService) {
		this.followService = followService;
	}

	@PutMapping("/playlists/{id}/followers")
	void followPlaylist(@PathVariable("id") String playlistId, @RequestParam(value = "public", required = false) Boolean isPublic) {
		FollowPlaylistRequest request = FollowPlaylistRequest.builder().playlistId(playlistId).publicPlaylist(isPublic).build();
		followService.followPlaylist(request);
	}

	@DeleteMapping("/playlists/{id}/followers")
	void unfollowPlaylist(@PathVariable("id") String playlistId) {
		followService.unfollowPlaylist(playlistId);
	}

	@GetMapping("/playlists/{id}/followers/contains")
	Map<String, Boolean> checkUsersFollowPlaylist(@PathVariable("id") String playlistId, @RequestParam("ids") List<String> ids) {
		CheckUsersFollowPlaylistRequest request = CheckUsersFollowPlaylistRequest.builder().playlistId(playlistId).ids(ids).build();
		return followService.checkUsersFollowPlaylist(request);
	}

	@GetMapping("/me/following")
	ListArtistsCursorPagingObject getUserFollowedArtists(@RequestParam(value = "next", required = false) String next,
	                                                     @RequestParam(value = "limit", required = false) Integer limit) {
		if (StringUtils.isNotBlank(next)) {
			return followService.getUserFollowedArtists(next);
		}
		UserFollowedArtistsRequest request = UserFollowedArtistsRequest.builder().limit(limit).build();
		return followService.getUserFollowedArtists(request);
	}

	@PutMapping("/me/following")
	void followArtistsOrUsers(@RequestParam("ids") List<String> ids, @RequestParam("type") String type) {
		FollowArtistsOrUsersRequest.FollowType followType = FollowArtistsOrUsersRequest.FollowType.valueOf(type);
		FollowArtistsOrUsersRequest request = FollowArtistsOrUsersRequest.builder().ids(ids).type(followType).build();
		followService.followArtistsOrUsers(request);
	}

	@DeleteMapping("/me/following")
	void unfollowArtistsOrUsers(@RequestParam("ids") List<String> ids, @RequestParam("type") String type) {
		UnfollowArtistsOrUsersRequest.FollowType followType = UnfollowArtistsOrUsersRequest.FollowType.valueOf(type);
		UnfollowArtistsOrUsersRequest request = UnfollowArtistsOrUsersRequest.builder().ids(ids).type(followType).build();
		followService.unfollowArtistsOrUsers(request);
	}

	@GetMapping("/me/following/contains")
	Map<String, Boolean> getFollowingStateArtistOrUsers(@RequestParam("ids") List<String> ids, @RequestParam("type") String type) {
		StatusFollowingArtistsOrUsersRequest.FollowType followType = StatusFollowingArtistsOrUsersRequest.FollowType.valueOf(type);
		StatusFollowingArtistsOrUsersRequest request = StatusFollowingArtistsOrUsersRequest.builder().ids(ids).type(followType).build();
		return followService.getFollowingStateArtistOrUsers(request);
	}
}
