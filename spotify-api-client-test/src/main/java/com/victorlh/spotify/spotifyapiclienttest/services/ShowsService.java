package com.victorlh.spotify.spotifyapiclienttest.services;

import com.victorlh.spotify.apiclient.models.ListSimplifiedShowObject;
import com.victorlh.spotify.apiclient.models.PagingObject;
import com.victorlh.spotify.apiclient.models.ShowObject;
import com.victorlh.spotify.apiclient.models.SimplifiedEpisodeObject;
import com.victorlh.spotify.apiclient.services.shows.models.MultipleShowsRequest;
import com.victorlh.spotify.apiclient.services.shows.models.ShowEpisodesRequest;
import com.victorlh.spotify.apiclient.services.shows.models.ShowRequest;

public interface ShowsService {

	ListSimplifiedShowObject getListShows(MultipleShowsRequest request);

	ShowObject getShow(ShowRequest request);

	PagingObject<SimplifiedEpisodeObject> getShowEpisodes(ShowEpisodesRequest request);

	PagingObject<SimplifiedEpisodeObject> getShowEpisodes(String paginationUrl);
}
