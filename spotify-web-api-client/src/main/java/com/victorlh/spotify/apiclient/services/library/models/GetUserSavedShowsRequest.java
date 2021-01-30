package com.victorlh.spotify.apiclient.services.library.models;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class GetUserSavedShowsRequest {

	private final Integer limit;
}
