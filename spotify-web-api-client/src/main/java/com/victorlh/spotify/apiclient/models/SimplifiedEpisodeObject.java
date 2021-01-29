package com.victorlh.spotify.apiclient.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimplifiedEpisodeObject {

	@JsonProperty("audio_preview_url")
	private String audioPreviewUrl;
	private String description;
	@JsonProperty("duration_ms")
	private Integer durationMs;
	private Boolean explicit;
	@JsonProperty("external_urls")
	private ExternalUrlObject externalUrls;
	private String href;
	private String id;
	private List<ImageObject> images;
	@JsonProperty("is_externally_hosted")
	private Boolean isExternallyHosted;
	@JsonProperty("is_playable")
	private Boolean isPlayable;
	@Deprecated
	private String language;
	private List<String> languages;
	private String name;
	@JsonProperty("release_date")
	private String releaseDate;
	@JsonProperty("release_date_precision")
	private String releaseDatePrecision;
	@JsonProperty("resume_point")
	private ResumePointObject resumePoint;
	private String type;
	private String uri;

}
