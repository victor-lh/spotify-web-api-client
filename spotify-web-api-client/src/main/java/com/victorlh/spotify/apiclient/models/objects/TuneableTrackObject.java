package com.victorlh.spotify.apiclient.models.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.victorlh.spotify.apiclient.models.enums.TrackMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TuneableTrackObject {

	private Double acousticness;
	private Double danceability;
	@JsonProperty("duration_ms")
	private Integer durationMs;
	private Double energy;
	private Double instrumentalness;
	@JsonProperty("key")
	private Integer pitchClass;
	private Double liveness;
	private Double loudness;
	private TrackMode mode;
	private Double popularity;
	private Double speechiness;
	private Double tempo;
	@JsonProperty("time_signature")
	private Integer timeSignature;
	private Double valence;

}
