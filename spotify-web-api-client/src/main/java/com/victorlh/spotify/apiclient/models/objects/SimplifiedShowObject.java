package com.victorlh.spotify.apiclient.models.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.victorlh.spotify.apiclient.models.objects.CopyrightObject;
import com.victorlh.spotify.apiclient.models.objects.ExternalUrlObject;
import com.victorlh.spotify.apiclient.models.objects.ImageObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimplifiedShowObject {

	@JsonProperty("available_markets")
	private List<String> availableMarkets;
	private List<CopyrightObject> copyrights;
	private String description;
	private Boolean explicit;
	@JsonProperty("external_urls")
	private ExternalUrlObject externalUrls;
	private String href;
	private String id;
	private List<ImageObject> images;
	@JsonProperty("is_externally_hosted")
	private Boolean isExternallyHosted;
	private List<String> languages;
	@JsonProperty("media_type")
	private String mediaTypes;
	private String name;
	private String publisher;
	@JsonProperty("total_episodes")
	private Integer totalEpisodes;
	private String type;
	private String uri;

}
