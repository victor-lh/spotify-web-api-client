package com.victorlh.spotify.apiclient.models.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.victorlh.spotify.apiclient.models.IPlayableItem;
import com.victorlh.spotify.apiclient.models.objects.CurrentlyPlayingObject;

import java.io.IOException;

public class CurrentlyPlayingObjectDeserializer extends AbstractPlayableItemDeserializer<CurrentlyPlayingObject> {

	public CurrentlyPlayingObjectDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public CurrentlyPlayingObject deserialize(JsonParser jp, DeserializationContext ctx) throws IOException, JsonProcessingException {
		ObjectCodec codec = jp.getCodec();
		JsonNode node = codec.readTree(jp);
		CurrentlyPlayingObject object = codec.treeToValue(node, CurrentlyPlayingObject.class);

		JsonNode track = node.get("item");
		IPlayableItem item = getPlayable(jp, track);
		object.setItem(item);

		return object;
	}
}