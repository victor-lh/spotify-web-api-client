package com.victorlh.spotify.apiclient.services.personalization.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class GetUserTopRequest {

	private final String timeRange;
	private final Integer limit;

}
