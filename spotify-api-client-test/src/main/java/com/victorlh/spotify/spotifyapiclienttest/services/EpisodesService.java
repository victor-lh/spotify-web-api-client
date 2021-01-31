package com.victorlh.spotify.spotifyapiclienttest.services;

import com.victorlh.spotify.apiclient.models.objects.EpisodeObject;
import com.victorlh.spotify.apiclient.models.lists.ListEpisodesObject;
import com.victorlh.spotify.apiclient.services.episodes.models.EpisodeRequest;
import com.victorlh.spotify.apiclient.services.episodes.models.MultipleEpisodesRequest;

public interface EpisodesService {

	ListEpisodesObject getListEpisodes(MultipleEpisodesRequest request);

	EpisodeObject getEpisode(EpisodeRequest request);
}
