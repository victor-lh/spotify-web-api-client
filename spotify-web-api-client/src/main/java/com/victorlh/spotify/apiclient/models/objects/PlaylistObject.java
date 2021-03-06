package com.victorlh.spotify.apiclient.models.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistObject {

	private Boolean collaborative;
	private String description;
	@JsonProperty("external_urls")
	private ExternalUrlObject externalUrls;
	private FollowersObject followers;
	private String href;
	private String id;
	private List<ImageObject> images;
	private String name;
	private PublicUserObject owner;
	@JsonProperty("public")
	private Boolean isPublic;
	@JsonProperty("snapshot_id")
	private String snapshotId;
	private PagingObject<PlaylistTrackObject> tracks;
	private String type;
	private String uri;
	@JsonProperty("primary_color")
	private JsonNode primaryColor;


}
