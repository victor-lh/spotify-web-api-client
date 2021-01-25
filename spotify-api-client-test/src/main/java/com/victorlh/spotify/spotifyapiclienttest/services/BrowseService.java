package com.victorlh.spotify.spotifyapiclienttest.services;

import com.victorlh.spotify.apiclient.models.*;
import com.victorlh.spotify.apiclient.services.browse.models.*;

public interface BrowseService {

	ListSimplifiedAlbumsPagingObject getNewReleases(NewReleasesRequest request);

	ListSimplifiedPlaylistPagingObject getAllFeaturedPlaylists(FeaturedPlaylistsRequest request);

	ListCategoriesPagingObject getAllCategories(AllCategoriesRequest request);

	CategoryObject getCategory(CategoryRequest request);

	ListSimplifiedPlaylistPagingObject getCategoryPlaylists(CategoryPlaylistsRequest request);

	RecommendationsObject getRecommendations(RecommendationsRequest request);

	ListGenresObject getRecommendationGenres();
}
