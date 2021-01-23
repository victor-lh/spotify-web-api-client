package com.victorlh.spotify.apiclient.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistObject {

	@JsonProperty("external_urls")
	private ExternalUrlObject externalUrls;
	private FollowersObject followersObject;
	private List<String> genres;
	private String href;
	private String id;
	private List<ImageObject> images;
	private String name;
	private Integer popularity;
	private String type;
	private String uri;

}
