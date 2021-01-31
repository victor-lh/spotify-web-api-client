package com.victorlh.spotify.apiclient.services.tracks.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class AudioFeaturesMultipleTracksRequest {

	@Singular
	private final List<String> ids;
}
