package com.victorlh.spotify.apiclient.models.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimplifiedAlbumObject {

	@JsonProperty("album_group")
	private String albumGroup;
	@JsonProperty("album_type")
	private String albumType;
	private List<SimplifiedArtistObject> artists;
	@JsonProperty("available_markets")
	private List<String> availableMarkets;
	@JsonProperty("external_urls")
	private ExternalUrlObject externalUrls;
	private String href;
	private String id;
	private List<ImageObject> images;
	private String name;
	@JsonProperty("release_date")
	private String releaseDate;
	@JsonProperty("release_date_precision")
	private String releaseDatePrecision;
	@JsonProperty("total_tracks")
	private Integer totalTracks;
	private AlbumRestrictionObject restrictions;
	private String type;
	private String uri;

}
