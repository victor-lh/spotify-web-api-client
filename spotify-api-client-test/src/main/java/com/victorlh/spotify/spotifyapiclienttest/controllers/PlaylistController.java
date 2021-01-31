package com.victorlh.spotify.spotifyapiclienttest.controllers;

import com.neovisionaries.i18n.CountryCode;
import com.victorlh.spotify.apiclient.models.enums.PlayableType;
import com.victorlh.spotify.apiclient.models.objects.*;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import com.victorlh.spotify.apiclient.services.playlist.models.*;
import com.victorlh.spotify.spotifyapiclienttest.dto.AddItemsPlaylistRequestDTO;
import com.victorlh.spotify.spotifyapiclienttest.dto.PlaylistDataRequestDTO;
import com.victorlh.spotify.spotifyapiclienttest.dto.RemoveItemsPlaylistRequestDTO;
import com.victorlh.spotify.spotifyapiclienttest.dto.ReorderReplaceItemsPlaylistRequestDTO;
import com.victorlh.spotify.spotifyapiclienttest.services.PlaylistService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PlaylistController {

	private final PlaylistService playlistService;

	@Autowired
	public PlaylistController(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}

	@GetMapping("/me/playlists")
	PagingObject<SimplifiedPlaylistObject> getCurrentUserPlaylist(@RequestParam(value = "next", required = false) String next,
	                                                              @RequestParam(value = "limit", required = false) Integer limit) {

		if (StringUtils.isNotEmpty(next)) {
			return playlistService.getPlaylistPagination(next);
		}
		return playlistService.getCurrentUserPlaylist(limit);

	}

	@GetMapping("/users/{userId}/playlists")
	PagingObject<SimplifiedPlaylistObject> getUserPlaylist(@RequestParam(value = "next", required = false) String next,
	                                                       @RequestParam(value = "limit", required = false) Integer limit, @PathVariable String userId) {
		if (StringUtils.isNotEmpty(next)) {
			return playlistService.getPlaylistPagination(next);
		}
		return playlistService.getUserPlaylist(userId, limit);
	}

	@GetMapping("/playlists/{playlistId}")
	PlaylistObject getUserPlaylist(@PathVariable String playlistId,
	                               @RequestParam(value = "market", required = false) String market,
	                               @RequestParam(value = "fields", required = false) String fields,
	                               @RequestParam(value = "additional_types", required = false) List<PlayableType> additionalTypes) {
		GetPlaylistRequest.GetPlaylistRequestBuilder builder = GetPlaylistRequest.builder().fields(fields).market(StringUtils.isEmpty(market) ? null : CountryCode.getByAlpha2Code(market));
		if (additionalTypes != null) {
			builder = builder.additionalTypes(additionalTypes);
		}
		GetPlaylistRequest request = builder.build();
		return playlistService.getPlaylist(playlistId, request);
	}

	@GetMapping("/playlists/{playlistId}/tracks")
	PagingObject<PlaylistTrackObject> getUserPlaylist(@PathVariable String playlistId,
	                                                  @RequestParam(value = "next", required = false) String next,
	                                                  @RequestParam(value = "market", required = false) String market,
	                                                  @RequestParam(value = "fields", required = false) String fields,
	                                                  @RequestParam(value = "additional_types", required = false) List<PlayableType> additionalTypes,
	                                                  @RequestParam(value = "limit", required = false) Integer limit) {
		if (StringUtils.isNotEmpty(next)) {
			return playlistService.getPlaylistItems(next);
		}

		GetPlaylistItemsRequest.GetPlaylistItemsRequestBuilder builder = GetPlaylistItemsRequest.builder().limit(limit).fields(fields).market(StringUtils.isEmpty(market) ? null : CountryCode.getByAlpha2Code(market));
		if (additionalTypes != null) {
			builder = builder.additionalTypes(additionalTypes);
		}
		GetPlaylistItemsRequest request = builder.build();
		return playlistService.getPlaylistItems(playlistId, request);
	}

	@GetMapping("/playlists/{playlistId}/images")
	List<ImageObject> getPlaylistImages(@PathVariable String playlistId) {
		return playlistService.getPlaylistImages(playlistId);
	}

	@PostMapping("/users/{userId}/playlists")
	PlaylistObject getPlaylistImages(@PathVariable String userId, @RequestBody PlaylistDataRequestDTO dto) {
		PlaylistDataRequest request = PlaylistDataRequest.builder().publicPlaylist(dto.getPublicPlaylist()).collaborative(dto.getCollaborative()).description(dto.getDescription()).name(dto.getName()).build();
		return playlistService.createPlaylist(userId, request);
	}

	@PutMapping("/playlists/{playlistId}")
	void editPlaylistData(@PathVariable String playlistId, @RequestBody PlaylistDataRequestDTO dto) {
		PlaylistDataRequest request = PlaylistDataRequest.builder().publicPlaylist(dto.getPublicPlaylist()).collaborative(dto.getCollaborative()).description(dto.getDescription()).name(dto.getName()).build();
		playlistService.editPlaylistData(playlistId, request);
	}

	@PostMapping("/playlists/{playlistId}/tracks")
	PlaylistSnapshotObject addItemsPlaylist(@PathVariable String playlistId, @RequestBody AddItemsPlaylistRequestDTO dto) {
		AddItemsPlaylistRequest request = AddItemsPlaylistRequest.builder().position(dto.getPosition()).uris(dto.getUris()).build();
		return playlistService.addItemsPlaylist(playlistId, request);
	}

	@DeleteMapping("/playlists/{playlistId}/tracks")
	PlaylistSnapshotObject removeItemsPlaylist(@PathVariable String playlistId, @RequestBody RemoveItemsPlaylistRequestDTO dto) {
		List<RemoveItemsPlaylistRequest.TrackDelete> trackList = dto.getTracks().stream().map(t -> RemoveItemsPlaylistRequest.TrackDelete.builder().uri(t.getUri()).position(t.getPosition()).build()).collect(Collectors.toList());
		RemoveItemsPlaylistRequest request = RemoveItemsPlaylistRequest.builder().snapshotId(dto.getSnapshotId()).tracks(trackList).build();
		return playlistService.removeItemsPlaylist(playlistId, request);
	}

	@PutMapping("/playlists/{playlistId}/tracks")
	PlaylistSnapshotObject reorderReplaceItemsPlaylist(@PathVariable String playlistId, @RequestBody ReorderReplaceItemsPlaylistRequestDTO dto) {
		ReorderReplaceItemsPlaylistRequest.ReorderReplaceItemsPlaylistRequestBuilder builder = ReorderReplaceItemsPlaylistRequest.builder().insertBefore(dto.getInsertBefore()).rangeLength(dto.getRangeLength()).rangeStart(dto.getRangeStart());
		List<String> uris = dto.getUris();
		if (uris != null && !uris.isEmpty()) {
			builder = builder.uris(uris);
		}
		ReorderReplaceItemsPlaylistRequest request = builder.build();
		return playlistService.reorderReplaceItemsPlaylist(playlistId, request);
	}
}
