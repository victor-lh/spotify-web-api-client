package com.victorlh.spotify.apiclient.services.browse;

import com.neovisionaries.i18n.CountryCode;
import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.models.lists.ListGenresObject;
import com.victorlh.spotify.apiclient.models.objects.CategoryObject;
import com.victorlh.spotify.apiclient.models.objects.RecommendationsObject;
import com.victorlh.spotify.apiclient.models.pagination.ListCategoriesPagingObject;
import com.victorlh.spotify.apiclient.models.pagination.ListSimplifiedAlbumsPagingObject;
import com.victorlh.spotify.apiclient.models.pagination.ListSimplifiedPlaylistPagingObject;
import com.victorlh.spotify.apiclient.services.AbstractApiService;
import com.victorlh.spotify.apiclient.services.browse.models.*;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class BrowseApiService extends AbstractApiService {

	private static final String BROWSE_PATH = "browse";
	private static final String NEW_RELEASES_PATH = "new-releases";
	private static final String ALL_FEATURED_PLAYLISTS_PATH = "featured-playlists";
	private static final String CATEGORIES_PATH = "categories";
	private static final String PLAYLISTS_PATH = "playlists";
	private static final String RECOMMENDATIONS_PATH = "recommendations";
	private static final String GENRES_SEEDS_PATH = "available-genre-seeds";

	@Builder
	public BrowseApiService(SpotifyApiClient spotifyApiClient) {
		super(spotifyApiClient);
	}

	public ListSimplifiedAlbumsPagingObject getNewReleases(NewReleasesRequest request) throws SpotifyGeneralApiException {
		log.trace("Call BrowseApiService#getNewReleases: {}", request);
		assert request != null;

		URIBuilder uriBuilder = getUriBuilder(BROWSE_PATH, NEW_RELEASES_PATH);
		addLimitToUriBuilder(uriBuilder, request.getLimit());
		addCountryToUriBuilder(uriBuilder, request.getCountry());
		URI uri = getUri(uriBuilder);
		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(ListSimplifiedAlbumsPagingObject.class);
	}

	public ListSimplifiedPlaylistPagingObject getAllFeaturedPlaylists(FeaturedPlaylistsRequest request) throws SpotifyGeneralApiException {
		log.trace("Call BrowseApiService#getAllFeaturedPlaylists: {}", request);
		assert request != null;

		URIBuilder uriBuilder = getUriBuilder(BROWSE_PATH, ALL_FEATURED_PLAYLISTS_PATH);
		addLimitToUriBuilder(uriBuilder, request.getLimit());
		addCountryToUriBuilder(uriBuilder, request.getCountry());
		addLocaleToUriBuilder(uriBuilder, request.getLocale());
		Date timestamp = request.getTimestamp();
		if (timestamp != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			String format = sdf.format(timestamp);
			uriBuilder = uriBuilder.addParameter("timestamp", format);
		}

		URI uri = getUri(uriBuilder);
		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(ListSimplifiedPlaylistPagingObject.class);
	}

	public ListCategoriesPagingObject getAllCategories(AllCategoriesRequest request) throws SpotifyGeneralApiException {
		log.trace("Call BrowseApiService#getAllCategories: {}", request);
		assert request != null;

		URIBuilder uriBuilder = getUriBuilder(BROWSE_PATH, CATEGORIES_PATH);
		addLimitToUriBuilder(uriBuilder, request.getLimit());
		addCountryToUriBuilder(uriBuilder, request.getCountry());
		addLocaleToUriBuilder(uriBuilder, request.getLocale());

		URI uri = getUri(uriBuilder);
		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(ListCategoriesPagingObject.class);
	}

	public CategoryObject getCategory(CategoryRequest request) throws SpotifyGeneralApiException {
		log.trace("Call BrowseApiService#getCategory: {}", request);
		assert request != null;

		String categoryId = request.getCategoryId();
		assert StringUtils.isNotEmpty(categoryId);

		URIBuilder uriBuilder = getUriBuilder(BROWSE_PATH, CATEGORIES_PATH, categoryId);
		addCountryToUriBuilder(uriBuilder, request.getCountry());
		addLocaleToUriBuilder(uriBuilder, request.getLocale());

		URI uri = getUri(uriBuilder);
		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(CategoryObject.class);
	}

	public ListSimplifiedPlaylistPagingObject getCategoryPlaylists(CategoryPlaylistsRequest request) throws SpotifyGeneralApiException {
		log.trace("Call BrowseApiService#getCategoryPlaylists: {}", request);
		assert request != null;

		String categoryId = request.getCategoryId();
		assert StringUtils.isNotEmpty(categoryId);

		URIBuilder uriBuilder = getUriBuilder(BROWSE_PATH, CATEGORIES_PATH, categoryId, PLAYLISTS_PATH);
		addCountryToUriBuilder(uriBuilder, request.getCountry());
		addLimitToUriBuilder(uriBuilder, request.getLimit());

		URI uri = getUri(uriBuilder);
		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(ListSimplifiedPlaylistPagingObject.class);
	}

	public RecommendationsObject getRecommendations(RecommendationsRequest request) throws SpotifyGeneralApiException {
		log.trace("Call BrowseApiService#getRecommendations: {}", request);
		assert request != null;

		URIBuilder uriBuilder = getUriBuilder(RECOMMENDATIONS_PATH);
		uriBuilder.addParameters(request.getParams());

		URI uri = getUri(uriBuilder);
		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(RecommendationsObject.class);
	}

	public ListGenresObject getRecommendationGenres() throws SpotifyGeneralApiException {
		log.trace("Call BrowseApiService#getRecommendationGenres");

		URI uri = getUri(RECOMMENDATIONS_PATH, GENRES_SEEDS_PATH);
		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(ListGenresObject.class);
	}

	private void addCountryToUriBuilder(URIBuilder uriBuilder, CountryCode country) {
		if (country != null) {
			uriBuilder.addParameter("country", country.getAlpha2());
		}
	}

	private void addLocaleToUriBuilder(URIBuilder uriBuilder, String locale) {
		if (StringUtils.isNotEmpty(locale)) {
			uriBuilder.addParameter("locale", locale);
		}
	}
}
