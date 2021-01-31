package com.victorlh.spotify.spotifyapiclienttest.services.impl;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.models.lists.ListGenresObject;
import com.victorlh.spotify.apiclient.models.objects.CategoryObject;
import com.victorlh.spotify.apiclient.models.objects.RecommendationsObject;
import com.victorlh.spotify.apiclient.models.pagination.ListCategoriesPagingObject;
import com.victorlh.spotify.apiclient.models.pagination.ListSimplifiedAlbumsPagingObject;
import com.victorlh.spotify.apiclient.models.pagination.ListSimplifiedPlaylistPagingObject;
import com.victorlh.spotify.apiclient.services.browse.BrowseApiService;
import com.victorlh.spotify.apiclient.services.browse.models.*;
import com.victorlh.spotify.spotifyapiclienttest.exceptions.SpotifyApiExceptionRuntime;
import com.victorlh.spotify.spotifyapiclienttest.services.BrowseService;
import com.victorlh.spotify.spotifyapiclienttest.services.SpotifyClientService;
import org.springframework.stereotype.Service;

@Service
public class BrowseServiceImpl implements BrowseService {

	private final SpotifyClientService spotifyClientService;

	public BrowseServiceImpl(SpotifyClientService spotifyClientService) {
		this.spotifyClientService = spotifyClientService;
	}

	@Override
	public ListSimplifiedAlbumsPagingObject getNewReleases(NewReleasesRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		BrowseApiService browseApiService = spotifyApiClient.getBrowseApiService();
		try {
			return browseApiService.getNewReleases(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public ListSimplifiedPlaylistPagingObject getAllFeaturedPlaylists(FeaturedPlaylistsRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		BrowseApiService browseApiService = spotifyApiClient.getBrowseApiService();
		try {
			return browseApiService.getAllFeaturedPlaylists(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public ListCategoriesPagingObject getAllCategories(AllCategoriesRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		BrowseApiService browseApiService = spotifyApiClient.getBrowseApiService();
		try {
			return browseApiService.getAllCategories(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public CategoryObject getCategory(CategoryRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		BrowseApiService browseApiService = spotifyApiClient.getBrowseApiService();
		try {
			return browseApiService.getCategory(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public ListSimplifiedPlaylistPagingObject getCategoryPlaylists(CategoryPlaylistsRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		BrowseApiService browseApiService = spotifyApiClient.getBrowseApiService();
		try {
			return browseApiService.getCategoryPlaylists(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public RecommendationsObject getRecommendations(RecommendationsRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		BrowseApiService browseApiService = spotifyApiClient.getBrowseApiService();
		try {
			return browseApiService.getRecommendations(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public ListGenresObject getRecommendationGenres() {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		BrowseApiService browseApiService = spotifyApiClient.getBrowseApiService();
		try {
			return browseApiService.getRecommendationGenres();
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}
}
