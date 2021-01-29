package com.victorlh.spotify.apiclient.models.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.victorlh.spotify.apiclient.models.IPlayableItem;
import com.victorlh.spotify.apiclient.models.enums.PlayableType;
import com.victorlh.spotify.apiclient.models.objects.EpisodeObject;
import com.victorlh.spotify.apiclient.models.objects.TrackObject;
import org.apache.commons.lang3.StringUtils;

abstract class AbstractPlayableItemDeserializer<T> extends StdDeserializer<T> {

	protected AbstractPlayableItemDeserializer(Class<?> vc) {
		super(vc);
	}

	protected IPlayableItem getPlayable(JsonParser jp, JsonNode node) throws JsonProcessingException {
		ObjectCodec codec = jp.getCodec();
		JsonNode track = node.get("track");
		if (track != null) {
			String type = (track.get("type")).asText();
			if (StringUtils.equals(PlayableType.track.name(), type)) {
				return codec.treeToValue(track, TrackObject.class);
			}
			if (StringUtils.equals(PlayableType.episode.name(), type)) {
				return codec.treeToValue(track, EpisodeObject.class);
			}
		}
		return null;
	}
}
