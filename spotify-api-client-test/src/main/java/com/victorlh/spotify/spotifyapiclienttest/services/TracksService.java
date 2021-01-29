package com.victorlh.spotify.spotifyapiclienttest.services;

import com.victorlh.spotify.apiclient.models.objects.AudioFeaturesObject;
import com.victorlh.spotify.apiclient.models.lists.ListAudiosFeaturesObject;
import com.victorlh.spotify.apiclient.models.lists.ListTracksObject;
import com.victorlh.spotify.apiclient.models.objects.TrackObject;
import com.victorlh.spotify.apiclient.services.tracks.models.AudioFeaturesMultipleTracksRequest;
import com.victorlh.spotify.apiclient.services.tracks.models.AudioFeaturesTrackRequest;
import com.victorlh.spotify.apiclient.services.tracks.models.MultipleTracksRequest;
import com.victorlh.spotify.apiclient.services.tracks.models.TrackRequest;

public interface TracksService {

	ListTracksObject getMultipleTracks(MultipleTracksRequest request);

	TrackObject getTrack(TrackRequest request);

	ListAudiosFeaturesObject getAudioFeaturesMultipleTracks(AudioFeaturesMultipleTracksRequest request);

	AudioFeaturesObject getAudioFeaturesTrack(AudioFeaturesTrackRequest request);
}
