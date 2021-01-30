package com.victorlh.spotify.apiclient.models.pagination;

import com.victorlh.spotify.apiclient.models.objects.ArtistObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListArtistsCursorPagingObject {

	private CursorPagingObject<ArtistObject> artists;
}
