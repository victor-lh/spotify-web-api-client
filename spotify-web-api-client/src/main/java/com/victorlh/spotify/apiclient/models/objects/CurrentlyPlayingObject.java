package com.victorlh.spotify.apiclient.models.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.victorlh.spotify.apiclient.models.IPlayableItem;
import com.victorlh.spotify.apiclient.models.deserializer.PlayableItemDeserializer;
import com.victorlh.spotify.apiclient.models.enums.CurrentlyPlayingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentlyPlayingObject {

	private ContextObject context;
	@JsonProperty("currently_playing_type")
	private CurrentlyPlayingType currentlyPlayingType;
	@JsonProperty("is_playing")
	private Boolean isPlaying;
	@JsonDeserialize(using = PlayableItemDeserializer.class)
	private IPlayableItem item;
	@JsonProperty("progress_ms")
	private Integer progressMs;
	private Long timestamp;

}
