package com.victorlh.spotify.spotifyapiclienttest.controllers;

import com.neovisionaries.i18n.CountryCode;
import com.victorlh.spotify.apiclient.models.ListSimplifiedShowObject;
import com.victorlh.spotify.apiclient.models.PagingObject;
import com.victorlh.spotify.apiclient.models.ShowObject;
import com.victorlh.spotify.apiclient.models.SimplifiedEpisodeObject;
import com.victorlh.spotify.apiclient.services.shows.models.ListShowsRequest;
import com.victorlh.spotify.apiclient.services.shows.models.ShowEpisodesRequest;
import com.victorlh.spotify.apiclient.services.shows.models.ShowRequest;
import com.victorlh.spotify.spotifyapiclienttest.services.ShowsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowsController {

	private final ShowsService showsService;

	@Autowired
	public ShowsController(ShowsService showsService) {
		this.showsService = showsService;
	}

	@GetMapping({"", "/"})
	public ListSimplifiedShowObject getListShows(@RequestParam("ids") List<String> ids, @RequestParam(value = "market", required = false) String market) {
		ListShowsRequest request = ListShowsRequest.builder().ids(ids).market(market != null ? CountryCode.getByAlpha2Code(market) : null).build();
		return this.showsService.getListShows(request);
	}

	@GetMapping("/{id}")
	public ShowObject getShow(@PathVariable("id") String showId, @RequestParam(value = "market", required = false) String market) {
		ShowRequest request = ShowRequest.builder().id(showId).market(market != null ? CountryCode.getByAlpha2Code(market) : null).build();
		return this.showsService.getShow(request);
	}

	@GetMapping("/{id}/episodes")
	public PagingObject<SimplifiedEpisodeObject> getShowEpisodes(@PathVariable("id") String showId,
	                                                             @RequestParam(value = "next", required = false) String next,
	                                                             @RequestParam(value = "market", required = false) String market,
	                                                             @RequestParam(value = "limit", required = false) Integer limit) {
		if(StringUtils.isNotEmpty(next)) {
			return this.showsService.getShowEpisodes(next);
		}
		ShowEpisodesRequest request = ShowEpisodesRequest.builder()
				.id(showId)
				.market(market != null ? CountryCode.getByAlpha2Code(market) : null)
				.limit(limit)
				.build();
		return this.showsService.getShowEpisodes(request);
	}
}
