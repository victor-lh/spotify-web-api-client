package com.victorlh.spotify.spotifyapiclienttest.controllers;

import com.neovisionaries.i18n.CountryCode;
import com.victorlh.spotify.apiclient.models.AlbumObject;
import com.victorlh.spotify.apiclient.models.ListAlbumsObjetc;
import com.victorlh.spotify.apiclient.models.PagingObject;
import com.victorlh.spotify.apiclient.models.SimplifiedTrackObject;
import com.victorlh.spotify.apiclient.services.albums.models.AlbumRequest;
import com.victorlh.spotify.apiclient.services.albums.models.AlbumTracksRequest;
import com.victorlh.spotify.apiclient.services.albums.models.MultipleAlbumsRequest;
import com.victorlh.spotify.spotifyapiclienttest.services.AlbumService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsController {

	private final AlbumService albumService;

	public AlbumsController(AlbumService albumService) {
		this.albumService = albumService;
	}

	@GetMapping({"", "/"})
	public ListAlbumsObjetc getListAlbums(@RequestParam("ids") List<String> ids, @RequestParam(value = "market", required = false) String market) {
		MultipleAlbumsRequest.MultipleAlbumsRequestBuilder builder = MultipleAlbumsRequest.builder()
				.ids(ids);
		if (StringUtils.isNotEmpty(market)) {
			builder = builder.market(CountryCode.getByCode(market));
		}
		return albumService.getAlbumsList(builder.build());
	}

	@GetMapping({"/{id}"})
	public AlbumObject getListAlbums(@PathVariable("id") String albumId, @RequestParam(value = "market", required = false) String market) {
		AlbumRequest.AlbumRequestBuilder builder = AlbumRequest.builder().albumId(albumId);
		if (StringUtils.isNotEmpty(market)) {
			builder = builder.market(CountryCode.getByCode(market));
		}
		return albumService.getAlbum(builder.build());
	}

	@GetMapping({"/{id}/tracks"})
	public PagingObject<SimplifiedTrackObject> getTracks(@PathVariable("id") String albumId, @RequestParam(value = "next", required = false) String next, @RequestParam(value = "market", required = false) String market, @RequestParam(value = "limit", required = false) Integer limit) {
		if (StringUtils.isNotEmpty(next)) {
			return albumService.getAlbumTracks(next);
		}

		AlbumTracksRequest.AlbumTracksRequestBuilder builder = AlbumTracksRequest.builder().albumId(albumId).limit(limit);
		if (StringUtils.isNotEmpty(market)) {
			builder = builder.market(CountryCode.getByCode(market));
		}

		return albumService.getAlbumTracks(builder.build());
	}
}
