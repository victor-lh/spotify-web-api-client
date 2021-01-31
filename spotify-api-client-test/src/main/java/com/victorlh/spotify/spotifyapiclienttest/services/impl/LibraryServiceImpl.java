package com.victorlh.spotify.spotifyapiclienttest.services.impl;

import com.victorlh.spotify.apiclient.SpotifyApiClient;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.models.objects.SavedAlbumObject;
import com.victorlh.spotify.apiclient.models.objects.SavedShowObject;
import com.victorlh.spotify.apiclient.models.objects.SavedTrackObject;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import com.victorlh.spotify.apiclient.services.library.LibraryApiService;
import com.victorlh.spotify.apiclient.services.library.models.GetUserSavedAlbumsRequest;
import com.victorlh.spotify.apiclient.services.library.models.GetUserSavedShowsRequest;
import com.victorlh.spotify.apiclient.services.library.models.GetUserSavedTracksRequest;
import com.victorlh.spotify.spotifyapiclienttest.exceptions.SpotifyApiExceptionRuntime;
import com.victorlh.spotify.spotifyapiclienttest.services.LibraryService;
import com.victorlh.spotify.spotifyapiclienttest.services.SpotifyClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LibraryServiceImpl implements LibraryService {

	private final SpotifyClientService spotifyClientService;

	@Autowired
	public LibraryServiceImpl(SpotifyClientService spotifyClientService) {
		this.spotifyClientService = spotifyClientService;
	}

	@Override
	public PagingObject<SavedAlbumObject> getUserSavedAlbums(GetUserSavedAlbumsRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		LibraryApiService libraryApiService = spotifyApiClient.getLibraryApiService();
		try {
			return libraryApiService.getUserSavedAlbums(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PagingObject<SavedAlbumObject> getUserSavedAlbums(String paginationUrl) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		LibraryApiService libraryApiService = spotifyApiClient.getLibraryApiService();
		try {
			return libraryApiService.getUserSavedAlbums(paginationUrl);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public void saveAlbumsCurrentUser(List<String> ids) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		LibraryApiService libraryApiService = spotifyApiClient.getLibraryApiService();
		try {
			libraryApiService.saveAlbumsCurrentUser(ids);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public void removeAlbumsCurrentUser(List<String> ids) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		LibraryApiService libraryApiService = spotifyApiClient.getLibraryApiService();
		try {
			libraryApiService.removeAlbumsCurrentUser(ids);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public Map<String, Boolean> checkUserSavedAlbums(List<String> ids) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		LibraryApiService libraryApiService = spotifyApiClient.getLibraryApiService();
		try {
			return libraryApiService.checkUserSavedAlbums(ids);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PagingObject<SavedTrackObject> getUserSavedTracks(GetUserSavedTracksRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		LibraryApiService libraryApiService = spotifyApiClient.getLibraryApiService();
		try {
			return libraryApiService.getUserSavedTracks(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PagingObject<SavedTrackObject> getUserSavedTracks(String paginationUrl) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		LibraryApiService libraryApiService = spotifyApiClient.getLibraryApiService();
		try {
			return libraryApiService.getUserSavedTracks(paginationUrl);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public void saveTracksCurrentUser(List<String> ids) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		LibraryApiService libraryApiService = spotifyApiClient.getLibraryApiService();
		try {
			libraryApiService.saveTracksCurrentUser(ids);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public void removeTracksCurrentUser(List<String> ids) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		LibraryApiService libraryApiService = spotifyApiClient.getLibraryApiService();
		try {
			libraryApiService.removeTracksCurrentUser(ids);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public Map<String, Boolean> checkUserSavedTracks(List<String> ids) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		LibraryApiService libraryApiService = spotifyApiClient.getLibraryApiService();
		try {
			return libraryApiService.checkUserSavedTracks(ids);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PagingObject<SavedShowObject> getUserSavedShows(GetUserSavedShowsRequest request) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		LibraryApiService libraryApiService = spotifyApiClient.getLibraryApiService();
		try {
			return libraryApiService.getUserSavedShows(request);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public PagingObject<SavedShowObject> getUserSavedShows(String paginationUrl) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		LibraryApiService libraryApiService = spotifyApiClient.getLibraryApiService();
		try {
			return libraryApiService.getUserSavedShows(paginationUrl);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public void saveShowsCurrentUser(List<String> ids) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		LibraryApiService libraryApiService = spotifyApiClient.getLibraryApiService();
		try {
			libraryApiService.saveShowsCurrentUser(ids);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public void removeShowsCurrentUser(List<String> ids) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		LibraryApiService libraryApiService = spotifyApiClient.getLibraryApiService();
		try {
			libraryApiService.removeShowsCurrentUser(ids);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}

	@Override
	public Map<String, Boolean> checkUserSavedShows(List<String> ids) {
		SpotifyApiClient spotifyApiClient = spotifyClientService.getSpotifyApiClient();
		LibraryApiService libraryApiService = spotifyApiClient.getLibraryApiService();
		try {
			return libraryApiService.checkUserSavedShows(ids);
		} catch (SpotifyGeneralApiException e) {
			throw new SpotifyApiExceptionRuntime(e);
		}
	}
}
