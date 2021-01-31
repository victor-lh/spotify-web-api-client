package com.victorlh.spotify.apiclient.services.artists;

import com.fasterxml.jackson.core.type.TypeReference;
import com.neovisionaries.i18n.CountryCode;
import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.models.lists.ListArtistsObject;
import com.victorlh.spotify.apiclient.models.lists.ListTracksObject;
import com.victorlh.spotify.apiclient.models.objects.ArtistObject;
import com.victorlh.spotify.apiclient.models.objects.SimplifiedAlbumObject;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import com.victorlh.spotify.apiclient.services.AbstractApiService;
import com.victorlh.spotify.apiclient.services.artists.models.ArtistAlbumsRequest;
import com.victorlh.spotify.apiclient.services.artists.models.ArtistTopTracksRequest;
import com.victorlh.spotify.apiclient.services.artists.models.MultipleArtistsRequest;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ArtistApiService extends AbstractApiService {

	private static final String ARTISTS_PATH = "artists";
	private static final String TOP_TRACKS_PATH = "top-tracks";
	private static final String RELATED_ARTISTS_PATH = "related-artists";
	private static final String ALBUMS_PATH = "albums";

	@Builder
	public ArtistApiService(SpotifyApiClient spotifyApiClient) {
		super(spotifyApiClient);
	}

	public ListArtistsObject getMultipleArtists(MultipleArtistsRequest request) throws SpotifyGeneralApiException {
		log.trace("Call ArtistApiService#getMultipleArtists: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ARTISTS_PATH);
		addIdsToUriBuilder(uriBuilder, request.getIds());
		URI uri = getUri(uriBuilder);
		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(ListArtistsObject.class);
	}

	public ArtistObject getArtist(String artisId) throws SpotifyGeneralApiException {
		log.trace("Call ArtistApiService#getArtist: {}", artisId);
		if (StringUtils.isEmpty(artisId)) {
			throw new IllegalArgumentException();
		}

		URI uri = getUri(ARTISTS_PATH, artisId);
		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(ArtistObject.class);
	}

	public ListTracksObject getArtistTopTracks(ArtistTopTracksRequest request) throws SpotifyGeneralApiException {
		log.trace("Call ArtistApiService#getArtistTopTracks: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		String artistId = request.getArtistId();
		if (StringUtils.isEmpty(artistId)) {
			throw new IllegalArgumentException();
		}

		CountryCode market = request.getMarket();
		if(market == null) {
			throw new IllegalArgumentException();
		}
		URIBuilder uriBuilder = getUriBuilder(ARTISTS_PATH, artistId, TOP_TRACKS_PATH);
		uriBuilder = uriBuilder.addParameter("market", market.getAlpha2());
		URI uri = getUri(uriBuilder);
		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(ListTracksObject.class);
	}

	public ListArtistsObject getRelatedArtists(String artistId) throws SpotifyGeneralApiException {
		log.trace("Call ArtistApiService#getRelatedArtists: {}", artistId);
		if (StringUtils.isEmpty(artistId)) {
			throw new IllegalArgumentException();
		}

		URI uri = getUri(ARTISTS_PATH, artistId, RELATED_ARTISTS_PATH);
		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(ListArtistsObject.class);
	}

	public PagingObject<SimplifiedAlbumObject> getAlbums(ArtistAlbumsRequest request) throws SpotifyGeneralApiException {
		log.trace("Call ArtistApiService#getAlbums: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		String artistId = request.getArtistId();
		if (StringUtils.isEmpty(artistId)) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(ARTISTS_PATH, artistId, ALBUMS_PATH);
		addLimitToUriBuilder(uriBuilder, request.getLimit());
		addMarketToUriBuilder(uriBuilder, request.getMarket());
		List<ArtistAlbumsRequest.AlbumTypes> types = request.getTypes();
		if (types != null && !types.isEmpty()) {
			String typesCollect = types.stream().map(Enum::name).collect(Collectors.joining(","));
			uriBuilder = uriBuilder.addParameter("include_groups", typesCollect);
		}
		URI uri = getUri(uriBuilder);
		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<SimplifiedAlbumObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

	public PagingObject<SimplifiedAlbumObject> getAlbums(String paginationUrl) throws SpotifyGeneralApiException {
		log.trace("Call ArtistApiService#getAlbums: {}", paginationUrl);
		if (StringUtils.isEmpty(paginationUrl)) {
			throw new IllegalArgumentException();
		}

		URI uri = URI.create(paginationUrl);
		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<SimplifiedAlbumObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}
}
