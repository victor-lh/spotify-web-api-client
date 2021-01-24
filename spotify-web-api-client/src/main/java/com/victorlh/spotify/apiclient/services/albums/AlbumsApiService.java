package com.victorlh.spotify.apiclient.services.albums;

import com.fasterxml.jackson.core.type.TypeReference;
import com.neovisionaries.i18n.CountryCode;
import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.exceptions.SpotifyWebApiClientException;
import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.models.AlbumObject;
import com.victorlh.spotify.apiclient.models.ListAlbumsObject;
import com.victorlh.spotify.apiclient.models.PagingObject;
import com.victorlh.spotify.apiclient.models.SimplifiedTrackObject;
import com.victorlh.spotify.apiclient.services.AbstractApiService;
import com.victorlh.spotify.apiclient.services.albums.models.AlbumRequest;
import com.victorlh.spotify.apiclient.services.albums.models.AlbumTracksRequest;
import com.victorlh.spotify.apiclient.services.albums.models.MultipleAlbumsRequest;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
public class AlbumsApiService extends AbstractApiService {

	private static final String ALBUMS_PATH = "albums";
	private static final String TRACKS_PATH = "tracks";

	@Builder
	public AlbumsApiService(SpotifyApiClient spotifyApiClient) {
		super(spotifyApiClient);
	}

	public ListAlbumsObject getMultipleAlbums(MultipleAlbumsRequest multipleAlbumsRequest) throws SpotifyGeneralApiException {
		log.trace("Call AlbumsApiService#getMultipleAlbums: {}", multipleAlbumsRequest);
		assert multipleAlbumsRequest != null;

		List<String> ids = multipleAlbumsRequest.getIds();
		CountryCode market = multipleAlbumsRequest.getMarket();

		if (ids == null || ids.isEmpty()) {
			throw new SpotifyWebApiClientException("Ids list is required");
		}
		if (ids.size() > 20) {
			throw new SpotifyWebApiClientException("Ids list is maximum 20 ids");
		}

		String idsCollect = String.join(",", ids);
		URIBuilder uriBuilder = getUriBuilder(ALBUMS_PATH);
		uriBuilder.addParameter("ids", idsCollect);
		if (market != null) {
			uriBuilder.addParameter("market", market.getAlpha2());
		}
		URI uri = getUri(uriBuilder);
		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(ListAlbumsObject.class);
	}

	public AlbumObject getAlbum(AlbumRequest albumRequest) throws SpotifyGeneralApiException {
		log.trace("Call AlbumsApiService#getAlbum: {}", albumRequest);
		if (albumRequest == null) {
			throw new IllegalArgumentException();
		}

		String albumId = albumRequest.getAlbumId();
		CountryCode market = albumRequest.getMarket();
		if (StringUtils.isEmpty(albumId)) {
			throw new SpotifyWebApiClientException("AlbumId is required");
		}

		URIBuilder uriBuilder = getUriBuilder(ALBUMS_PATH, albumId);
		if (market != null) {
			uriBuilder.addParameter("market", market.getAlpha2());
		}
		URI uri = getUri(uriBuilder);
		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(AlbumObject.class);
	}

	public PagingObject<SimplifiedTrackObject> getAlbumTracks(AlbumTracksRequest albumTracksRequest) throws SpotifyGeneralApiException {
		log.trace("Call AlbumsApiService#getAlbumTracks: {}", albumTracksRequest);
		if (albumTracksRequest == null) {
			throw new IllegalArgumentException();
		}

		String albumId = albumTracksRequest.getAlbumId();
		if (StringUtils.isEmpty(albumId)) {
			throw new SpotifyWebApiClientException("AlbumId is required");
		}

		URIBuilder uriBuilder = getUriBuilder(ALBUMS_PATH, albumId, TRACKS_PATH);

		CountryCode market = albumTracksRequest.getMarket();
		if (market != null) {
			uriBuilder.addParameter("market", market.getAlpha2());
		}

		Integer limit = albumTracksRequest.getLimit();
		if (limit != null) {
			if (limit < 1) {
				throw new SpotifyWebApiClientException("Limit minimum 1");
			} else if (limit > 50) {
				throw new SpotifyWebApiClientException("Limit maximum 50");
			}
			uriBuilder.addParameter("limit", limit.toString());
		}

		URI uri = getUri(uriBuilder);
		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<SimplifiedTrackObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

	public PagingObject<SimplifiedTrackObject> getAlbumTracks(String paginationUrl) throws SpotifyGeneralApiException {
		log.trace("Call AlbumsApiService#getAlbumTracks: {}", paginationUrl);
		if (StringUtils.isEmpty(paginationUrl)) {
			throw new SpotifyWebApiClientException("next is required");
		}

		URI uri = URI.create(paginationUrl);
		HttpResponseWrapper response = doGet(uri);
		TypeReference<PagingObject<SimplifiedTrackObject>> typeReference = new TypeReference<>() {
		};
		return response.parseResponse(typeReference);
	}

}
