package com.victorlh.spotify.apiclient.models.pagination;

import com.victorlh.spotify.apiclient.models.objects.SimplifiedPlaylistObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListSimplifiedPlaylistPagingObject {

	private String message;
	private PagingObject<SimplifiedPlaylistObject> playlists;
}
