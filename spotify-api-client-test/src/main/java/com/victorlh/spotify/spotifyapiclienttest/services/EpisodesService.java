package com.victorlh.spotify.spotifyapiclienttest.services;

import com.victorlh.spotify.apiclient.models.EpisodeObject;
import com.victorlh.spotify.apiclient.models.ListEpisodesObject;
import com.victorlh.spotify.apiclient.services.episodes.models.EpisodeRequest;
import com.victorlh.spotify.apiclient.services.episodes.models.MultipleEpisodesRequest;

public interface EpisodesService {

	ListEpisodesObject getListEpisodes(MultipleEpisodesRequest request);

	EpisodeObject getEpisode(EpisodeRequest request);
}
