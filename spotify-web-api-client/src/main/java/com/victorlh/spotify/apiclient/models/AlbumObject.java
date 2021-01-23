package com.victorlh.spotify.apiclient.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumObject {

	@JsonProperty("album_type")
	private String albumType;
	private List<ArtistObject> artists;
	@JsonProperty("available_markets")
	private List<String> availableMarkets;
	private List<CopyrightObject> copyrights;
	@JsonProperty("external_ids")
	private ExternalIdObject externalIds;
	@JsonProperty("external_urls")
	private ExternalUrlObject externalUrls;
	private List<String> genres;
	private String href;
	private String id;
	private List<ImageObject> images;
	private String label;
	private String name;
	private Integer popularity;
	@JsonProperty("release_date")
	private String releaseDate;
	@JsonProperty("release_date_precision")
	private String releaseDatePrecision;
	private AlbumRestrictionObject restrictions;
	private PagingObject<SimplifiedTrackObject> tracks;
	@JsonProperty("total_tracks")
	private Integer totalTracks;
	private String type;
	private String uri;
}
