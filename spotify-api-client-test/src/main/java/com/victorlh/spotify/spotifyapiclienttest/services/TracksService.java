package com.victorlh.spotify.spotifyapiclienttest.services;

import com.victorlh.spotify.apiclient.models.AudioFeaturesObject;
import com.victorlh.spotify.apiclient.models.ListAudioFeaturesObject;
import com.victorlh.spotify.apiclient.models.ListTracksObject;
import com.victorlh.spotify.apiclient.models.TrackObject;
import com.victorlh.spotify.apiclient.services.tracks.models.AudioFeaturesMultipleTracksRequest;
import com.victorlh.spotify.apiclient.services.tracks.models.AudioFeaturesTrackRequest;
import com.victorlh.spotify.apiclient.services.tracks.models.MultipleTracksRequest;
import com.victorlh.spotify.apiclient.services.tracks.models.TrackRequest;

public interface TracksService {

	ListTracksObject getMultipleTracks(MultipleTracksRequest request);

	TrackObject getTrack(TrackRequest request);

	ListAudioFeaturesObject getAudioFeaturesMultipleTracks(AudioFeaturesMultipleTracksRequest request);

	AudioFeaturesObject getAudioFeaturesTrack(AudioFeaturesTrackRequest request);
}
