package com.victorlh.spotify.apiclient.models.objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayHistoryObject {

	private ContextObject context;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@JsonProperty("played_at")
	private Date playedAt;
	private SimplifiedTrackObject track;
}
