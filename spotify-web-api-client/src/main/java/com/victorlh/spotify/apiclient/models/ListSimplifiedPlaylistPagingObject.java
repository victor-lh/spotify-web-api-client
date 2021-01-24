package com.victorlh.spotify.apiclient.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListSimplifiedPlaylistPagingObject {

	private String message;
	private PagingObject<SimplifiedAlbumObject> playlists;
}
