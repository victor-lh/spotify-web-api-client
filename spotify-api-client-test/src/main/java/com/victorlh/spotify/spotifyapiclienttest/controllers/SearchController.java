package com.victorlh.spotify.spotifyapiclienttest.controllers;

import com.victorlh.spotify.apiclient.models.enums.SpotifyType;
import com.victorlh.spotify.apiclient.models.objects.SearchObject;
import com.victorlh.spotify.apiclient.services.search.models.SearchRequest;
import com.victorlh.spotify.spotifyapiclienttest.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

	private final SearchService searchService;

	@Autowired
	public SearchController(SearchService searchService) {
		this.searchService = searchService;
	}

	@GetMapping("/search")
	SearchObject search(@RequestParam("type") List<SpotifyType> types,
	                    @RequestParam("q") String query) {
		SearchRequest request = SearchRequest.builder().types(types).query(query).build();
		return searchService.search(request);
	}
}
