package com.victorlh.spotify.apiclient.services.browse.models;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoryRequest {

	private final String categoryId;
	private final CountryCode country;
	private final String locale;

}
