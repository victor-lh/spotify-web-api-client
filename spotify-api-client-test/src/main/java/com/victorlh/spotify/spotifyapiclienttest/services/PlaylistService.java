package com.victorlh.spotify.spotifyapiclienttest.services;

import com.victorlh.spotify.apiclient.models.objects.*;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import com.victorlh.spotify.apiclient.services.playlist.models.*;

import java.util.List;

public interface PlaylistService {

	PagingObject<SimplifiedPlaylistObject> getCurrentUserPlaylist(Integer limit);

	PagingObject<SimplifiedPlaylistObject> getUserPlaylist(String userId, Integer limit);

	PagingObject<SimplifiedPlaylistObject> getPlaylistPagination(String paginationUrl);

	PlaylistObject getPlaylist(String playlistId, GetPlaylistRequest request);

	PagingObject<PlaylistTrackObject> getPlaylistItems(String playlistId, GetPlaylistItemsRequest request);

	PagingObject<PlaylistTrackObject> getPlaylistItems(String paginationUrl);

	List<ImageObject> getPlaylistImages(String playlistId);

	PlaylistObject createPlaylist(String userId, PlaylistDataRequest request);

	void editPlaylistData(String playlistId, PlaylistDataRequest request);

	PlaylistSnapshotObject addItemsPlaylist(String playlistId, AddItemsPlaylistRequest request);

	PlaylistSnapshotObject removeItemsPlaylist(String playlistId, RemoveItemsPlaylistRequest request);

	PlaylistSnapshotObject reorderReplaceItemsPlaylist(String playlistId, ReorderReplaceItemsPlaylistRequest request);
}
