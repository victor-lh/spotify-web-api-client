package com.victorlh.spotify.apiclient.models.lists;

import com.victorlh.spotify.apiclient.models.objects.ArtistObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListArtistsObject {

	private List<ArtistObject> artists;
}
