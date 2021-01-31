package com.victorlh.spotify.apiclient;

import com.victorlh.spotify.apiclient.credentials.ClientApiCredentials;
import com.victorlh.spotify.apiclient.credentials.ClientApiPrincipal;
import com.victorlh.spotify.apiclient.credentials.Credentials;
import com.victorlh.spotify.apiclient.credentials.TokenApiCredentials;
import com.victorlh.spotify.apiclient.services.albums.AlbumsApiService;
import com.victorlh.spotify.apiclient.services.artists.ArtistApiService;
import com.victorlh.spotify.apiclient.services.browse.BrowseApiService;
import com.victorlh.spotify.apiclient.services.credentials.CredentialsApiService;
import com.victorlh.spotify.apiclient.services.episodes.EpisodesApiService;
import com.victorlh.spotify.apiclient.services.follow.FollowApiService;
import com.victorlh.spotify.apiclient.services.library.LibraryApiService;
import com.victorlh.spotify.apiclient.services.personalization.PersonalizationApiService;
import com.victorlh.spotify.apiclient.services.player.PlayerApiService;
import com.victorlh.spotify.apiclient.services.playlist.PlaylistApiService;
import com.victorlh.spotify.apiclient.services.search.SearchApiService;
import com.victorlh.spotify.apiclient.services.shows.ShowsApiService;
import com.victorlh.spotify.apiclient.services.tracks.TracksApiService;
import com.victorlh.spotify.apiclient.services.userprofile.UserProfileApiService;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class SpotifyApiClient {

	@NonNull
	private final String apiClientId;
	private final String apiClientSecret;
	private final Credentials credentials;

	public ClientApiCredentials getClientApiCredentials() {
		ClientApiPrincipal clientApiPrincipal = new ClientApiPrincipal(apiClientId, apiClientSecret);
		return new ClientApiCredentials(clientApiPrincipal);
	}

	public TokenApiCredentials getTokenApiCredentials() {
		return new TokenApiCredentials(credentials);
	}


	public CredentialsApiService getCredentialService() {
		return CredentialsApiService.builder().spotifyApiClient(this).build();
	}

	public UserProfileApiService getUserProfileApiService() {
		return UserProfileApiService.builder().spotifyApiClient(this).build();
	}

	public AlbumsApiService getAlbumsApiService() {
		return AlbumsApiService.builder().spotifyApiClient(this).build();
	}

	public ArtistApiService getArtistApiService() {
		return ArtistApiService.builder().spotifyApiClient(this).build();
	}

	public BrowseApiService getBrowseApiService() {
		return BrowseApiService.builder().spotifyApiClient(this).build();
	}

	public EpisodesApiService getEpisodesApiService() {
		return EpisodesApiService.builder().spotifyApiClient(this).build();
	}

	public PersonalizationApiService getPersonalizationApiService() {
		return PersonalizationApiService.builder().spotifyApiClient(this).build();
	}

	public ShowsApiService getShowsApiService() {
		return ShowsApiService.builder().spotifyApiClient(this).build();
	}

	public TracksApiService getTracksApiService() {
		return TracksApiService.builder().spotifyApiClient(this).build();
	}

	public FollowApiService getFollowApiService() {
		return FollowApiService.builder().spotifyApiClient(this).build();
	}

	public LibraryApiService getLibraryApiService() {
		return LibraryApiService.builder().spotifyApiClient(this).build();
	}

	public PlayerApiService getPlayerApiService() {
		return PlayerApiService.builder().spotifyApiClient(this).build();
	}

	public PlaylistApiService getPlaylistApiService() {
		return PlaylistApiService.builder().spotifyApiClient(this).build();
	}

	public SearchApiService getSearchApiService() {
		return SearchApiService.builder().spotifyApiClient(this).build();
	}
}
