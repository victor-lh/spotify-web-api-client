package com.victorlh.spotify.apiclient.models.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisallowsObject {

	@JsonProperty("interrupting_playback")
	private Boolean interruptingPlayback;
	private Boolean pausing;
	private Boolean resuming;
	private Boolean seeking;
	@JsonProperty("skipping_next")
	private Boolean skippingNext;
	@JsonProperty("skipping_prev")
	private Boolean skippingPrev;
	@JsonProperty("toggling_repeat_context")
	private Boolean togglingRepeatContext;
	@JsonProperty("toggling_repeat_track")
	private Boolean togglingRepeatTrack;
	@JsonProperty("toggling_shuffle")
	private Boolean togglingShuffle;
	@JsonProperty("transferring_playback")
	private Boolean transferringPlayback;
}
