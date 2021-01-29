package com.victorlh.spotify.apiclient.models.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.victorlh.spotify.apiclient.models.IPlayableItem;
import com.victorlh.spotify.apiclient.models.objects.PlaylistTrackObject;

import java.io.IOException;

public class PlaylistTrackObjectDeserializer extends AbstractPlayableItemDeserializer<PlaylistTrackObject> {

	public PlaylistTrackObjectDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public PlaylistTrackObject deserialize(JsonParser jp, DeserializationContext ctx) throws IOException, JsonProcessingException {
		ObjectCodec codec = jp.getCodec();
		JsonNode node = codec.readTree(jp);
		PlaylistTrackObject object = codec.treeToValue(node, PlaylistTrackObject.class);

		JsonNode track = node.get("track");
		IPlayableItem item = getPlayable(jp, track);
		object.setTrack(item);

		return object;
	}
}