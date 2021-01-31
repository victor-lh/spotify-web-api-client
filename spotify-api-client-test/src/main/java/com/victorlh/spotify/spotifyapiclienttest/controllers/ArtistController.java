package com.victorlh.spotify.spotifyapiclienttest.controllers;

import com.neovisionaries.i18n.CountryCode;
import com.victorlh.spotify.apiclient.models.lists.ListArtistsObject;
import com.victorlh.spotify.apiclient.models.lists.ListTracksObject;
import com.victorlh.spotify.apiclient.models.objects.ArtistObject;
import com.victorlh.spotify.apiclient.models.objects.SimplifiedAlbumObject;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import com.victorlh.spotify.apiclient.services.artists.models.ArtistAlbumsRequest;
import com.victorlh.spotify.apiclient.services.artists.models.ArtistTopTracksRequest;
import com.victorlh.spotify.apiclient.services.artists.models.MultipleArtistsRequest;
import com.victorlh.spotify.spotifyapiclienttest.services.ArtistService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artists")
public class ArtistController {

	private final ArtistService artistService;

	public ArtistController(ArtistService artistService) {
		this.artistService = artistService;
	}

	@GetMapping({"", "/"})
	public ListArtistsObject getMultipleArtists(@RequestParam("ids") List<String> ids) {
		MultipleArtistsRequest request = MultipleArtistsRequest.builder().ids(ids).build();
		return artistService.getMultipleArtist(request);
	}

	@GetMapping("/{id}")
	public ArtistObject getArtist(@PathVariable("id") String artistId) {
		return artistService.getArtist(artistId);
	}

	@GetMapping("/{id}/top-tracks")
	public ListTracksObject getTopTracks(@PathVariable("id") String artistId, @RequestParam(value = "market", required = false) String market) {
		ArtistTopTracksRequest.ArtistTopTracksRequestBuilder builder = ArtistTopTracksRequest.builder().artistId(artistId);
		if (StringUtils.isNotEmpty(market)) {
			builder = builder.market(CountryCode.getByCode(market));
		}
		ArtistTopTracksRequest request = builder.build();
		return artistService.getTopTracks(request);
	}

	@GetMapping("/{id}/related-artist")
	public ListArtistsObject getTopTracks(@PathVariable("id") String artistId) {
		return artistService.getRelatedArtists(artistId);
	}

	@GetMapping("/{id}/albums")
	public PagingObject<SimplifiedAlbumObject> getAlbums(@PathVariable("id") String artistId,
	                                                     @RequestParam(value = "next", required = false) String next,
	                                                     @RequestParam(value = "market", required = false) String market,
	                                                     @RequestParam(value = "limit", required = false) Integer limit,
	                                                     @RequestParam(value = "include_groups", required = false) List<String> includeGroups) {

		if (StringUtils.isNotEmpty(next)) {
			return artistService.getAlbums(next);
		}

		if (includeGroups == null) {
			includeGroups = new ArrayList<>();
		}
		List<ArtistAlbumsRequest.AlbumTypes> listTypes = includeGroups.stream().map(ArtistAlbumsRequest.AlbumTypes::valueOf).collect(Collectors.toList());
		ArtistAlbumsRequest.ArtistAlbumsRequestBuilder builder = ArtistAlbumsRequest.builder().artistId(artistId).limit(limit).types(listTypes);
		if (StringUtils.isNotEmpty(market)) {
			builder = builder.market(CountryCode.getByCode(market));
		}
		ArtistAlbumsRequest request = builder.build();
		return artistService.getAlbums(request);
	}

}
