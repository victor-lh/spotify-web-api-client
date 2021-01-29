package com.victorlh.spotify.apiclient.models.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.victorlh.spotify.apiclient.models.IPlayableItem;
import com.victorlh.spotify.apiclient.models.enums.PlayableType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpisodeObject implements IPlayableItem {

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
	private SimplifiedShowObject show;
	private PlayableType type;
	private String uri;

}
