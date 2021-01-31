package com.victorlh.spotify.apiclient.services.artists.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
public class MultipleArtistsRequest {

	@NonNull
	@Singular
	private final List<String> ids;
}
