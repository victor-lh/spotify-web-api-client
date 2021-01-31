package com.victorlh.spotify.apiclient.models.pagination;

import com.victorlh.spotify.apiclient.models.objects.SimplifiedAlbumObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListSimplifiedAlbumsPagingObject {

	private PagingObject<SimplifiedAlbumObject> albums;
}
