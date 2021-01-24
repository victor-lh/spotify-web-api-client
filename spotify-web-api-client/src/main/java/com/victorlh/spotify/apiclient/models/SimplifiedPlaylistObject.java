package com.victorlh.spotify.apiclient.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimplifiedPlaylistObject {

	private Boolean collaborative;
	private String description;
	@JsonProperty("external_urls")
	private ExternalUrlObject externalUrls;
	private String href;
	private String id;
	private List<ImageObject> images;
	private String name;
	private PublicUserObject owner;
	@JsonProperty("public")
	private Boolean isPublic;
	@JsonProperty("snapshot_id")
	private String snapshotId;
	private PlaylistTracksRefObject tracks;
	private String type;
	private String uri;

}
