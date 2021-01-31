package com.victorlh.spotify.apiclient.models.objects;

import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchObject {

	private PagingObject<SimplifiedAlbumObject> albums;
	private PagingObject<ArtistObject> artists;
	private PagingObject<SimplifiedPlaylistObject> playlists;
	private PagingObject<SimplifiedTrackObject> tracks;
	private PagingObject<SimplifiedShowObject> shows;
	private PagingObject<SimplifiedEpisodeObject> episodes;
}
