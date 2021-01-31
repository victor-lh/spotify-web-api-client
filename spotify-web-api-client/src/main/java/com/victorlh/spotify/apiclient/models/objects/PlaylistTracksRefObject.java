package com.victorlh.spotify.apiclient.models.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistTracksRefObject {

	private String href;
	private Integer total;

}
