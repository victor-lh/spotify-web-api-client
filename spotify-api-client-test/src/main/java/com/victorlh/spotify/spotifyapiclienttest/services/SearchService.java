package com.victorlh.spotify.spotifyapiclienttest.services;


import com.victorlh.spotify.apiclient.models.objects.SearchObject;
import com.victorlh.spotify.apiclient.services.search.models.SearchRequest;

public interface SearchService {

	SearchObject search(SearchRequest request);

	SearchObject search(String paginationUrl);
}
