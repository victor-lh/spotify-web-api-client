package com.victorlh.spotify.apiclient.models;

import com.victorlh.spotify.apiclient.models.enums.PlayableType;
import com.victorlh.spotify.apiclient.models.objects.ExternalUrlObject;

public interface IPlayableItem {

	Integer getDurationMs();

	Boolean getExplicit();

	ExternalUrlObject getExternalUrls();

	String getHref();

	String getId();

	Boolean getIsPlayable();

	String getName();

	PlayableType getType();

	String getUri();

}
