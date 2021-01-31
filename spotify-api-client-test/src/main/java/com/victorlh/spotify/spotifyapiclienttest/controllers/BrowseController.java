package com.victorlh.spotify.spotifyapiclienttest.controllers;

import com.neovisionaries.i18n.CountryCode;
import com.victorlh.spotify.apiclient.models.lists.ListGenresObject;
import com.victorlh.spotify.apiclient.models.objects.CategoryObject;
import com.victorlh.spotify.apiclient.models.objects.RecommendationsObject;
import com.victorlh.spotify.apiclient.models.pagination.ListCategoriesPagingObject;
import com.victorlh.spotify.apiclient.models.pagination.ListSimplifiedAlbumsPagingObject;
import com.victorlh.spotify.apiclient.models.pagination.ListSimplifiedPlaylistPagingObject;
import com.victorlh.spotify.apiclient.services.browse.models.*;
import com.victorlh.spotify.spotifyapiclienttest.services.BrowseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class BrowseController {

	private final BrowseService browseService;

	public BrowseController(BrowseService browseService) {
		this.browseService = browseService;
	}

	@GetMapping("/browse/new-releases")
	public ListSimplifiedAlbumsPagingObject getNewReleases(@RequestParam(value = "country", required = false) String country,
	                                                       @RequestParam(value = "limit", required = false) Integer limit) {
		NewReleasesRequest request = NewReleasesRequest.builder()
				.country(country != null ? CountryCode.valueOf(country) : null)
				.limit(limit)
				.build();
		return browseService.getNewReleases(request);
	}

	@GetMapping("/browse/featured-playlists")
	public ListSimplifiedPlaylistPagingObject getAllFeaturedPlaylist(@RequestParam(value = "country", required = false) String country,
	                                                                 @RequestParam(value = "locale", required = false) String locale,
	                                                                 @RequestParam(value = "timestamp", required = false) String timestamp,
	                                                                 @RequestParam(value = "limit", required = false) Integer limit) {

		Date date = null;
		if (StringUtils.isNotEmpty(timestamp)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			try {
				date = sdf.parse(timestamp);
			} catch (ParseException ignored) {
			}
		}

		FeaturedPlaylistsRequest request = FeaturedPlaylistsRequest.builder()
				.country(country != null ? CountryCode.valueOf(country) : null)
				.limit(limit)
				.locale(locale)
				.timestamp(date)
				.build();
		return browseService.getAllFeaturedPlaylists(request);
	}

	@GetMapping("/browse/categories")
	public ListCategoriesPagingObject getAllCategories(@RequestParam(value = "country", required = false) String country,
	                                                   @RequestParam(value = "locale", required = false) String locale,
	                                                   @RequestParam(value = "limit", required = false) Integer limit) {

		AllCategoriesRequest request = AllCategoriesRequest.builder()
				.country(country != null ? CountryCode.valueOf(country) : null)
				.limit(limit)
				.locale(locale)
				.build();
		return browseService.getAllCategories(request);
	}

	@GetMapping("/browse/categories/{id}")
	public CategoryObject getCategory(@PathVariable("id") String categoryId,
	                                  @RequestParam(value = "country", required = false) String country,
	                                  @RequestParam(value = "locale", required = false) String locale) {

		CategoryRequest request = CategoryRequest.builder()
				.categoryId(categoryId)
				.country(country != null ? CountryCode.valueOf(country) : null)
				.locale(locale)
				.build();
		return browseService.getCategory(request);
	}

	@GetMapping("/browse/categories/{id}/playlists")
	public ListSimplifiedPlaylistPagingObject getCategoryPlaylist(@PathVariable("id") String categoryId,
	                                                              @RequestParam(value = "country", required = false) String country,
	                                                              @RequestParam(value = "limit", required = false) Integer limit) {

		CategoryPlaylistsRequest request = CategoryPlaylistsRequest.builder()
				.categoryId(categoryId)
				.country(country != null ? CountryCode.valueOf(country) : null)
				.limit(limit)
				.build();
		return browseService.getCategoryPlaylists(request);
	}

	@GetMapping("/recommendations/available-genre-seeds")
	public ListGenresObject getRecommendationGenres() {
		return browseService.getRecommendationGenres();
	}

	@GetMapping("/recommendations")
	public RecommendationsObject getRecommendation(@RequestParam(value = "market", required = false) String market,
	                                               @RequestParam(value = "limit", required = false) Integer limit) {
		RecommendationsRequest build = RecommendationsRequest.builder()
				.limit(limit)
				.market(market != null ? CountryCode.valueOf(market): null)
				.seedGenre("j-pop")
				.build();
		return browseService.getRecommendations(build);
	}
}
