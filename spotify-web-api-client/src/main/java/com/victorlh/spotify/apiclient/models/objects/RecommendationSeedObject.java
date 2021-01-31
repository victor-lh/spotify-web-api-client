package com.victorlh.spotify.apiclient.models.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationSeedObject {

	private Integer afterFilteringSize;
	private Integer afterRelinkingSize;
	private String href;
	private String id;
	private Integer initialPoolSize;
	private String type;

}
