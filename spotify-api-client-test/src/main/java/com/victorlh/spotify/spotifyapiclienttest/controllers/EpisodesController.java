package com.victorlh.spotify.spotifyapiclienttest.controllers;

import com.neovisionaries.i18n.CountryCode;
import com.victorlh.spotify.apiclient.models.EpisodeObject;
import com.victorlh.spotify.apiclient.models.ListEpisodesObject;
import com.victorlh.spotify.apiclient.services.episodes.models.EpisodeRequest;
import com.victorlh.spotify.apiclient.services.episodes.models.MultipleEpisodesRequest;
import com.victorlh.spotify.spotifyapiclienttest.services.EpisodesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/episodes")
public class EpisodesController {

	private final EpisodesService episodesService;

	public EpisodesController(EpisodesService episodesService) {
		this.episodesService = episodesService;
	}

	@GetMapping({"", "/"})
	public ListEpisodesObject getListEpisodes(@RequestParam("ids") List<String> ids, @RequestParam(value = "market", required = false) String market) {
		MultipleEpisodesRequest request = MultipleEpisodesRequest.builder()
				.ids(ids)
				.market(market != null ? CountryCode.getByCode(market) : null)
				.build();
		return episodesService.getListEpisodes(request);
	}

	@GetMapping({"/{id}"})
	public EpisodeObject getEpisode(@PathVariable("id") String episodeId, @RequestParam(value = "market", required = false) String market) {
		EpisodeRequest request = EpisodeRequest.builder()
				.id(episodeId)
				.market(market != null ? CountryCode.getByCode(market) : null)
				.build();
		return episodesService.getEpisode(request);
	}
}
