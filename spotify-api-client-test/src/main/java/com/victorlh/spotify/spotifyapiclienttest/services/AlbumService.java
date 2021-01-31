package com.victorlh.spotify.spotifyapiclienttest.services;

import com.victorlh.spotify.apiclient.models.objects.AlbumObject;
import com.victorlh.spotify.apiclient.models.lists.ListAlbumsObject;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import com.victorlh.spotify.apiclient.models.objects.SimplifiedTrackObject;
import com.victorlh.spotify.apiclient.services.albums.models.AlbumRequest;
import com.victorlh.spotify.apiclient.services.albums.models.AlbumTracksRequest;
import com.victorlh.spotify.apiclient.services.albums.models.MultipleAlbumsRequest;

public interface AlbumService {

	ListAlbumsObject getAlbumsList(MultipleAlbumsRequest multipleAlbumsRequest);

	AlbumObject getAlbum(AlbumRequest albumRequest);

	PagingObject<SimplifiedTrackObject> getAlbumTracks(AlbumTracksRequest albumTracksRequest);

	PagingObject<SimplifiedTrackObject> getAlbumTracks(String next);

}
