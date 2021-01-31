package com.victorlh.spotify.spotifyapiclienttest.services;

import com.victorlh.spotify.apiclient.models.objects.SavedAlbumObject;
import com.victorlh.spotify.apiclient.models.objects.SavedShowObject;
import com.victorlh.spotify.apiclient.models.objects.SavedTrackObject;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import com.victorlh.spotify.apiclient.services.library.models.GetUserSavedAlbumsRequest;
import com.victorlh.spotify.apiclient.services.library.models.GetUserSavedShowsRequest;
import com.victorlh.spotify.apiclient.services.library.models.GetUserSavedTracksRequest;

import java.util.List;
import java.util.Map;

public interface LibraryService {


	PagingObject<SavedAlbumObject> getUserSavedAlbums(GetUserSavedAlbumsRequest request);

	PagingObject<SavedAlbumObject> getUserSavedAlbums(String paginationUrl);

	void saveAlbumsCurrentUser(List<String> ids);

	void removeAlbumsCurrentUser(List<String> ids);

	Map<String, Boolean> checkUserSavedAlbums(List<String> ids);

	PagingObject<SavedTrackObject> getUserSavedTracks(GetUserSavedTracksRequest request);

	PagingObject<SavedTrackObject> getUserSavedTracks(String paginationUrl);

	void saveTracksCurrentUser(List<String> ids);

	void removeTracksCurrentUser(List<String> ids);

	Map<String, Boolean> checkUserSavedTracks(List<String> ids);

	PagingObject<SavedShowObject> getUserSavedShows(GetUserSavedShowsRequest request);

	PagingObject<SavedShowObject> getUserSavedShows(String paginationUrl);

	void saveShowsCurrentUser(List<String> ids);

	void removeShowsCurrentUser(List<String> ids);

	Map<String, Boolean> checkUserSavedShows(List<String> ids);
}
