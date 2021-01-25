package com.victorlh.spotify.spotifyapiclienttest.services;

import com.victorlh.spotify.apiclient.models.ArtistObject;
import com.victorlh.spotify.apiclient.models.PagingObject;
import com.victorlh.spotify.apiclient.models.TrackObject;
import com.victorlh.spotify.apiclient.services.personalization.models.GetUserTopRequest;

public interface PersonalizationService {

	PagingObject<TrackObject> getUserTopTracks(GetUserTopRequest request);

	PagingObject<TrackObject> getUserTopTracks(String paginationUri);

	PagingObject<ArtistObject> getUserTopArtist(GetUserTopRequest request);

	PagingObject<ArtistObject> getUserTopArtist(String paginationUri);
}
