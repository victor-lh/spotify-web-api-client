package com.victorlh.spotify.apiclient.services.albums.models;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
public class AlbumRequest {

	@NonNull
	private final String albumId;
	private final CountryCode market;
}
