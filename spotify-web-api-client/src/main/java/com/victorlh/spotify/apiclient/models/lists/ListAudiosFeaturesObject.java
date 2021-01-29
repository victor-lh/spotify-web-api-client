package com.victorlh.spotify.apiclient.models.lists;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.victorlh.spotify.apiclient.models.objects.AudioFeaturesObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListAudiosFeaturesObject {

	@JsonProperty("audio_features")
	private List<AudioFeaturesObject> audioFeatures;
}
