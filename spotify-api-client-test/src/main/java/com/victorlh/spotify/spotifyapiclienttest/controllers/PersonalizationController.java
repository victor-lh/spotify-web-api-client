package com.victorlh.spotify.spotifyapiclienttest.controllers;


import com.victorlh.spotify.apiclient.models.ArtistObject;
import com.victorlh.spotify.apiclient.models.PagingObject;
import com.victorlh.spotify.apiclient.models.TrackObject;
import com.victorlh.spotify.apiclient.services.personalization.models.GetUserTopRequest;
import com.victorlh.spotify.spotifyapiclienttest.services.PersonalizationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonalizationController {

	private final PersonalizationService personalizationService;

	public PersonalizationController(PersonalizationService personalizationService) {
		this.personalizationService = personalizationService;
	}

	@GetMapping("/me/top/tracks")
	public PagingObject<TrackObject> getTopTracks(
			@RequestParam(value = "next", required = false) String next,
			@RequestParam(value = "time_range", required = false) String timeRange,
			@RequestParam(value = "limit", required = false) Integer limit) {

		if (StringUtils.isNotEmpty(next)) {
			return personalizationService.getUserTopTracks(next);
		}

		GetUserTopRequest request = GetUserTopRequest.builder().timeRange(timeRange).limit(limit).build();
		return personalizationService.getUserTopTracks(request);
	}

	@GetMapping("/me/top/artists")
	public PagingObject<ArtistObject> getTopArtists(
			@RequestParam(value = "next", required = false) String next,
			@RequestParam(value = "time_range", required = false) String timeRange,
			@RequestParam(value = "limit", required = false) Integer limit) {

		if (StringUtils.isNotEmpty(next)) {
			return personalizationService.getUserTopArtist(next);
		}

		GetUserTopRequest request = GetUserTopRequest.builder().timeRange(timeRange).limit(limit).build();
		return personalizationService.getUserTopArtist(request);
	}

}
