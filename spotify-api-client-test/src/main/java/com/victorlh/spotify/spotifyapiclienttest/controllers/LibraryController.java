package com.victorlh.spotify.spotifyapiclienttest.controllers;

import com.neovisionaries.i18n.CountryCode;
import com.victorlh.spotify.apiclient.models.objects.SavedAlbumObject;
import com.victorlh.spotify.apiclient.models.objects.SavedShowObject;
import com.victorlh.spotify.apiclient.models.objects.SavedTrackObject;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import com.victorlh.spotify.apiclient.services.library.models.GetUserSavedAlbumsRequest;
import com.victorlh.spotify.apiclient.services.library.models.GetUserSavedShowsRequest;
import com.victorlh.spotify.apiclient.services.library.models.GetUserSavedTracksRequest;
import com.victorlh.spotify.spotifyapiclienttest.services.LibraryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/me")
public class LibraryController {

	private final LibraryService libraryService;

	@Autowired
	public LibraryController(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	@GetMapping("/albums")
	PagingObject<SavedAlbumObject> getUserSavedAlbums(@RequestParam(value = "next", required = false) String next,
	                                                  @RequestParam(value = "limit", required = false) Integer limit,
	                                                  @RequestParam(value = "market", required = false) String market) {
		if (StringUtils.isNotBlank(next)) {
			return libraryService.getUserSavedAlbums(next);
		}

		GetUserSavedAlbumsRequest request = GetUserSavedAlbumsRequest.builder().limit(limit).market(market == null ? null : CountryCode.getByAlpha2Code(market)).build();
		return libraryService.getUserSavedAlbums(request);
	}

	@PutMapping("/albums")
	void saveAlbumsCurrentUser(@RequestParam(value = "ids") List<String> ids) {
		libraryService.saveAlbumsCurrentUser(ids);
	}

	@DeleteMapping("/albums")
	void removeAlbumsCurrentUser(@RequestParam(value = "ids") List<String> ids) {
		libraryService.removeAlbumsCurrentUser(ids);
	}

	@GetMapping("/albums/contains")
	Map<String, Boolean> checkUserSavedAlbums(@RequestParam(value = "ids") List<String> ids) {
		return libraryService.checkUserSavedAlbums(ids);
	}

	@GetMapping("/tracks")
	PagingObject<SavedTrackObject> getUserSavedTracks(@RequestParam(value = "next", required = false) String next,
	                                                  @RequestParam(value = "limit", required = false) Integer limit,
	                                                  @RequestParam(value = "market", required = false) String market) {
		if (StringUtils.isNotBlank(next)) {
			return libraryService.getUserSavedTracks(next);
		}

		GetUserSavedTracksRequest request = GetUserSavedTracksRequest.builder().limit(limit).market(market == null ? null : CountryCode.getByAlpha2Code(market)).build();
		return libraryService.getUserSavedTracks(request);
	}

	@PutMapping("/tracks")
	void saveTracksCurrentUser(@RequestParam(value = "ids") List<String> ids) {
		libraryService.saveTracksCurrentUser(ids);
	}

	@DeleteMapping("/tracks")
	void removeTracksCurrentUser(@RequestParam(value = "ids") List<String> ids) {
		libraryService.removeTracksCurrentUser(ids);
	}

	@GetMapping("/tracks/contains")
	Map<String, Boolean> checkUserSavedTracks(@RequestParam(value = "ids") List<String> ids) {
		return libraryService.checkUserSavedTracks(ids);
	}

	@GetMapping("/shows")
	PagingObject<SavedShowObject> getUserSavedShows(@RequestParam(value = "next", required = false) String next,
	                                                @RequestParam(value = "limit", required = false) Integer limit,
	                                                @RequestParam(value = "market", required = false) String market) {
		if (StringUtils.isNotBlank(next)) {
			return libraryService.getUserSavedShows(next);
		}

		GetUserSavedShowsRequest request = GetUserSavedShowsRequest.builder().limit(limit).build();
		return libraryService.getUserSavedShows(request);
	}

	@PutMapping("/shows")
	void saveShowsCurrentUser(@RequestParam(value = "ids") List<String> ids) {
		libraryService.saveShowsCurrentUser(ids);
	}

	@DeleteMapping("/shows")
	void removeShowsCurrentUser(@RequestParam(value = "ids") List<String> ids) {
		libraryService.removeShowsCurrentUser(ids);
	}

	@GetMapping("/shows/contains")
	Map<String, Boolean> checkUserSavedShows(@RequestParam(value = "ids") List<String> ids) {
		return libraryService.checkUserSavedShows(ids);
	}
}
