package com.victorlh.spotify.spotifyapiclienttest.services;

import com.victorlh.spotify.apiclient.models.lists.ListArtistsObject;
import com.victorlh.spotify.apiclient.models.lists.ListTracksObject;
import com.victorlh.spotify.apiclient.models.objects.ArtistObject;
import com.victorlh.spotify.apiclient.models.objects.SimplifiedAlbumObject;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import com.victorlh.spotify.apiclient.services.artists.models.ArtistAlbumsRequest;
import com.victorlh.spotify.apiclient.services.artists.models.ArtistTopTracksRequest;
import com.victorlh.spotify.apiclient.services.artists.models.MultipleArtistsRequest;

public interface ArtistService {

	ListArtistsObject getMultipleArtist(MultipleArtistsRequest request);

	ArtistObject getArtist(String artistId);

	ListTracksObject getTopTracks(ArtistTopTracksRequest request);

	ListArtistsObject getRelatedArtists(String artistId);

	PagingObject<SimplifiedAlbumObject> getAlbums(ArtistAlbumsRequest request);

	PagingObject<SimplifiedAlbumObject> getAlbums(String paginationUrl);
}
