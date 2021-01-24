package com.victorlh.spotify.apiclient.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationsObject {

	private List<RecommendationSeedObject> seeds;
	private List<SimplifiedTrackObject> tracks;

}
