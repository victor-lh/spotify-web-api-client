package com.victorlh.spotify.spotifyapiclienttest.controllers;

import com.neovisionaries.i18n.CountryCode;
import com.victorlh.spotify.apiclient.models.objects.AudioFeaturesObject;
import com.victorlh.spotify.apiclient.models.lists.ListAudiosFeaturesObject;
import com.victorlh.spotify.apiclient.models.lists.ListTracksObject;
import com.victorlh.spotify.apiclient.models.objects.TrackObject;
import com.victorlh.spotify.apiclient.services.tracks.models.AudioFeaturesMultipleTracksRequest;
import com.victorlh.spotify.apiclient.services.tracks.models.AudioFeaturesTrackRequest;
import com.victorlh.spotify.apiclient.services.tracks.models.MultipleTracksRequest;
import com.victorlh.spotify.apiclient.services.tracks.models.TrackRequest;
import com.victorlh.spotify.spotifyapiclienttest.services.TracksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TracksController {

	private final TracksService tracksService;

	@Autowired
	public TracksController(TracksService tracksService) {
		this.tracksService = tracksService;
	}

	@GetMapping({"/tracks", "/tracks/"})
	public ListTracksObject getListTracks(@RequestParam("ids") List<String> ids, @RequestParam(value = "market", required = false) String market) {
		MultipleTracksRequest request = MultipleTracksRequest.builder().ids(ids).market(market != null ? CountryCode.getByAlpha2Code(market) : null).build();
		return this.tracksService.getMultipleTracks(request);
	}

	@GetMapping("/tracks/{id}")
	public TrackObject getTrack(@PathVariable("id") String showId, @RequestParam(value = "market", required = false) String market) {
		TrackRequest request = TrackRequest.builder().id(showId).market(market != null ? CountryCode.getByAlpha2Code(market) : null).build();
		return this.tracksService.getTrack(request);
	}

	@GetMapping({"/audio-features", "/audio-features/"})
	public ListAudiosFeaturesObject getAudioFeaturesMultipleTracks(@RequestParam("ids") List<String> ids) {
		AudioFeaturesMultipleTracksRequest request = AudioFeaturesMultipleTracksRequest.builder().ids(ids).build();
		return this.tracksService.getAudioFeaturesMultipleTracks(request);
	}

	@GetMapping("/audio-features/{id}")
	public AudioFeaturesObject getAudioFeaturesTrack(@PathVariable("id") String showId) {
		AudioFeaturesTrackRequest request = AudioFeaturesTrackRequest.builder().id(showId).build();
		return this.tracksService.getAudioFeaturesTrack(request);
	}

}
