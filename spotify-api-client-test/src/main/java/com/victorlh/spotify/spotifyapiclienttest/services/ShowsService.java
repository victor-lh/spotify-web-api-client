package com.victorlh.spotify.spotifyapiclienttest.services;

import com.victorlh.spotify.apiclient.models.lists.ListSimplifiedShowsObject;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import com.victorlh.spotify.apiclient.models.objects.ShowObject;
import com.victorlh.spotify.apiclient.models.objects.SimplifiedEpisodeObject;
import com.victorlh.spotify.apiclient.services.shows.models.MultipleShowsRequest;
import com.victorlh.spotify.apiclient.services.shows.models.ShowEpisodesRequest;
import com.victorlh.spotify.apiclient.services.shows.models.ShowRequest;

public interface ShowsService {

	ListSimplifiedShowsObject getListShows(MultipleShowsRequest request);

	ShowObject getShow(ShowRequest request);

	PagingObject<SimplifiedEpisodeObject> getShowEpisodes(ShowEpisodesRequest request);

	PagingObject<SimplifiedEpisodeObject> getShowEpisodes(String paginationUrl);
}
