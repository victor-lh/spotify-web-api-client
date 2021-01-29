package com.victorlh.spotify.apiclient.models.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.victorlh.spotify.apiclient.models.IPlayableItem;
import com.victorlh.spotify.apiclient.models.objects.CurrentlyPlayingContextObject;

import java.io.IOException;

public class CurrentlyPlayingContextObjectDeserializer extends AbstractPlayableItemDeserializer<CurrentlyPlayingContextObject> {

	public CurrentlyPlayingContextObjectDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public CurrentlyPlayingContextObject deserialize(JsonParser jp, DeserializationContext ctx) throws IOException, JsonProcessingException {
		ObjectCodec codec = jp.getCodec();
		JsonNode node = codec.readTree(jp);
		CurrentlyPlayingContextObject object = codec.treeToValue(node, CurrentlyPlayingContextObject.class);

		JsonNode track = node.get("item");
		IPlayableItem item = getPlayable(jp, track);
		object.setItem(item);

		return object;
	}
}