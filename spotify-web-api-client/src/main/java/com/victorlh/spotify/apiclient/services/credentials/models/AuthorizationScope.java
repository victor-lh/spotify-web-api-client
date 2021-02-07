package com.victorlh.spotify.apiclient.services.credentials.models;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
public enum AuthorizationScope {

	UGC_IMAGE_UPLOAD("ugc-image-upload"),
	USER_READ_RECENTLY_PLAYED("user-read-recently-played"),
	USER_TOP_READ("user-top-read"),
	USER_READ_PLAYBACK_POSITION("user-read-playback-position"),
	USER_READ_PLAYBACK_STATE("user-read-playback-state"),
	USER_MODIFY_PLAYBACK_STATE("user-modify-playback-state"),
	USER_READ_CURRENTLY_PLAYING("user-read-currently-playing"),
	APP_REMOTE_CONTROL("app-remote-control"),
	STREAMING("streaming"),
	PLAYLIST_MODIFY_PUBLIC("playlist-modify-public"),
	PLAYLIST_MODIFY_PRIVATE("playlist-modify-private"),
	PLAYLIST_READ_PRIVATE("playlist-read-private"),
	PLAYLIST_READ_COLLABORATIVE("playlist-read-collaborative"),
	USER_FOLLOW_MODIFY("user-follow-modify"),
	USER_FOLLOW_READ("user-follow-read"),
	USER_LIBRARY_MODIFY("user-library-modify"),
	USER_LIBRARY_READ("user-library-read"),
	USER_READ_EMAIL("user-read-email"),
	USER_READ_PRIVATE("user-read-private");

	public final String scope;

	public static AuthorizationScope getScope(String scope) {
		AuthorizationScope[] values = values();
		for (AuthorizationScope v : values) {
			if(StringUtils.equals(v.name(), scope) || StringUtils.equals(v.scope, scope)) {
				return v;
			}
		}
		return null;
	}
}
