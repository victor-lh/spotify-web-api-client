package com.victorlh.spotify.apiclient.models.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContextObject {

	@JsonProperty("external_urls")
	private ExternalUrlObject externalUrls;
	private String href;
	private String type;
	private String uri;
}
