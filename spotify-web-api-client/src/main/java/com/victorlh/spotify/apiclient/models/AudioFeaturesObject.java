package com.victorlh.spotify.apiclient.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.victorlh.spotify.apiclient.models.enums.TrackMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AudioFeaturesObject {

	private Double acousticness;
	@JsonProperty("analysis_url")
	private String analysisUrl;
	private Double danceability;
	@JsonProperty("duration_ms")
	private Integer durationMs;
	private Double energy;
	private String id;
	private Double instrumentalness;
	@JsonProperty("key")
	private Integer pitchClass;
	private Double liveness;
	private Double loudness;
	private TrackMode mode;
	private Double speechiness;
	private Double tempo;
	@JsonProperty("time_signature")
	private Integer timeSignature;
	@JsonProperty("track_href")
	private String trackHref;
	private String type;
	private String uri;
	private Double valence;
}
