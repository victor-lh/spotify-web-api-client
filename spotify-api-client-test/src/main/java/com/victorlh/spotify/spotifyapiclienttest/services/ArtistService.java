package com.victorlh.spotify.spotifyapiclienttest.services;

import com.victorlh.spotify.apiclient.models.*;
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
