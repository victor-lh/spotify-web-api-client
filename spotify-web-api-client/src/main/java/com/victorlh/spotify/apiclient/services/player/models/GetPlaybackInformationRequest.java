package com.victorlh.spotify.apiclient.services.player.models;

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
public class GetPlaybackInformationRequest {

	private final CountryCode market;
	@Singular
	private final List<PlayableType> additionalTypes;
}
