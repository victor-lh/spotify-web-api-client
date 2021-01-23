package com.victorlh.spotify.spotifyapiclienttest.services;

import com.victorlh.spotify.apiclient.models.AlbumObject;
import com.victorlh.spotify.apiclient.models.ListAlbumsObjetc;
import com.victorlh.spotify.apiclient.models.PagingObject;
import com.victorlh.spotify.apiclient.models.SimplifiedTrackObject;
import com.victorlh.spotify.apiclient.services.albums.models.AlbumRequest;
import com.victorlh.spotify.apiclient.services.albums.models.AlbumTracksRequest;
import com.victorlh.spotify.apiclient.services.albums.models.MultipleAlbumsRequest;

public interface AlbumService {

	ListAlbumsObjetc getAlbumsList(MultipleAlbumsRequest multipleAlbumsRequest);

	AlbumObject getAlbum(AlbumRequest albumRequest);

	PagingObject<SimplifiedTrackObject> getAlbumTracks(AlbumTracksRequest albumTracksRequest);

	PagingObject<SimplifiedTrackObject> getAlbumTracks(String next);

}
