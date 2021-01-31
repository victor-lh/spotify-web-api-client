package com.victorlh.spotify.apiclient.services.search;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.httpmanager.HttpResponseWrapper;
import com.victorlh.spotify.apiclient.models.objects.SearchObject;
import com.victorlh.spotify.apiclient.services.AbstractApiService;
import com.victorlh.spotify.apiclient.services.search.models.SearchRequest;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.util.stream.Collectors;

@Slf4j
public class SearchApiService extends AbstractApiService {

	private static final String SEARCH_PATH = "search";

	@Builder
	public SearchApiService(SpotifyApiClient spotifyApiClient) {
		super(spotifyApiClient);
	}

	public SearchObject search(SearchRequest request) throws SpotifyGeneralApiException {
		log.trace("Call SearchApiService#search: {}", request);
		if (request == null) {
			throw new IllegalArgumentException();
		}

		URIBuilder uriBuilder = getUriBuilder(SEARCH_PATH);
		addLimitToUriBuilder(uriBuilder, request.getLimit());
		addMarketToUriBuilder(uriBuilder, request.getMarket());
		uriBuilder.addParameter("q", request.getQuery());
		uriBuilder.addParameter("type", request.getTypes().stream().map(Enum::name).collect(Collectors.joining(",")));
		Boolean includeExternal = request.getIncludeExternal();
		if(includeExternal != null && includeExternal) {
			uriBuilder.addParameter("include_external", "audio");
		}
		URI uri = getUri(uriBuilder);

		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(SearchObject.class);
	}

	public SearchObject search(String paginationUrl) throws SpotifyGeneralApiException {
		log.trace("Call SearchApiService#search: {}", paginationUrl);
		if (StringUtils.isEmpty(paginationUrl)) {
			throw new IllegalArgumentException();
		}

		URI uri = URI.create(paginationUrl);

		HttpResponseWrapper response = doGet(uri);
		return response.parseResponse(SearchObject.class);
	}

}
