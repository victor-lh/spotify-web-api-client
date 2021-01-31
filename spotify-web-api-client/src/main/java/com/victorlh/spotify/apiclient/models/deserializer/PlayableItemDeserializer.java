package com.victorlh.spotify.apiclient.models.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.victorlh.spotify.apiclient.models.IPlayableItem;
import com.victorlh.spotify.apiclient.models.enums.PlayableType;
import com.victorlh.spotify.apiclient.models.objects.EpisodeObject;
import com.victorlh.spotify.apiclient.models.objects.TrackObject;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class PlayableItemDeserializer extends StdDeserializer<IPlayableItem> {

	public PlayableItemDeserializer() {
		this(null);
	}

	public PlayableItemDeserializer(Class<?> vc) {
		super(vc);
	}

	protected IPlayableItem getPlayable(JsonParser jp, JsonNode node) throws JsonProcessingException {
		ObjectCodec codec = jp.getCodec();
		JsonNode typeNode = node.get("type");
		String type = typeNode == null ? null : typeNode.asText();
		if (type != null) {
			if (StringUtils.equals(PlayableType.track.name(), type)) {
				return codec.treeToValue(node, TrackObject.class);
			}
			if (StringUtils.equals(PlayableType.episode.name(), type)) {
				return codec.treeToValue(node, EpisodeObject.class);
			}
		} else {
			return getPlayableByError(jp, node);
		}
		return null;
	}

	protected IPlayableItem getPlayableByError(JsonParser jp, JsonNode node) throws JsonProcessingException {
		ObjectCodec codec = jp.getCodec();
		JsonProcessingException error;
		try {
			return codec.treeToValue(node, TrackObject.class);
		} catch (JsonProcessingException e) {
			error = e;
		}
		try {
			return codec.treeToValue(node, EpisodeObject.class);
		} catch (JsonProcessingException ignored) {
		}
		throw error;

	}

	@Override
	public IPlayableItem deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
		ObjectCodec codec = jp.getCodec();
		JsonNode node = codec.readTree(jp);
		return getPlayable(jp, node);
	}
}
