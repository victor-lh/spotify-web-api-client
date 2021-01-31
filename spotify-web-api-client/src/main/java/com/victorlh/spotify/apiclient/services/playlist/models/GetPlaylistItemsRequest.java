package com.victorlh.spotify.apiclient.services.playlist.models;

import com.neovisionaries.i18n.CountryCode;
import com.victorlh.spotify.apiclient.models.enums.PlayableType;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class GetPlaylistItemsRequest {

	private final String playlistId;
	private final CountryCode market;
	//TODO - Modificar por un builder de query
	private final String fields;
	@Singular
	private final List<PlayableType> additionalTypes;
	private final Integer limit;
}
