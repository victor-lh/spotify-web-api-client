package com.victorlh.spotify.apiclient.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimplifiedArtistObject {

	@JsonProperty("external_urls")
	private ExternalUrlObject externalUrls;
	private String href;
	private String id;
	private String name;
	private String type;
	private String uri;

}
