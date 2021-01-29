package com.victorlh.spotify.apiclient.models.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumePointObject {

	@JsonProperty("fully_played")
	private Boolean fullyPlayed;
	@JsonProperty("resume_position_ms")
	private Integer resumePositionMs;

}
