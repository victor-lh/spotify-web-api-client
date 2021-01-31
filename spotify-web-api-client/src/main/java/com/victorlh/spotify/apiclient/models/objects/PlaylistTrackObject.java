package com.victorlh.spotify.apiclient.models.objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.victorlh.spotify.apiclient.models.IPlayableItem;
import com.victorlh.spotify.apiclient.models.deserializer.PlayableItemDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistTrackObject {

	@JsonProperty("added_at")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date addedAt;
	@JsonProperty("added_by")
	private PublicUserObject addedBy;
	@JsonProperty(value = "is_local")
	private Boolean isLocal;
	@JsonDeserialize(using = PlayableItemDeserializer.class)
	private IPlayableItem track;
	@JsonProperty("primary_color")
	private JsonNode primaryColor;
	@JsonProperty("video_thumbnail")
	private VideoThumbnail videoThumbnail;


}
