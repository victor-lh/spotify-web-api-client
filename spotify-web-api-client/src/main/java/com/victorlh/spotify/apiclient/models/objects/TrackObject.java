package com.victorlh.spotify.apiclient.models.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.victorlh.spotify.apiclient.models.IPlayableItem;
import com.victorlh.spotify.apiclient.models.enums.PlayableType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackObject implements IPlayableItem {

	private SimplifiedAlbumObject album;
	private List<SimplifiedArtistObject> artists;
	@JsonProperty("available_markets")
	private List<String> availableMarkets;
	@JsonProperty("disc_number")
	private Integer discNumber;
	@JsonProperty("duration_ms")
	private Integer durationMs;
	private Boolean explicit;
	@JsonProperty("external_ids")
	private ExternalIdObject externalId;
	@JsonProperty("external_urls")
	private ExternalUrlObject externalUrls;
	private String href;
	private String id;
	@JsonProperty("is_local")
	private Boolean isLocal;
	@JsonProperty("is_playable")
	private Boolean isPlayable;
	@JsonProperty("linked_from")
	private LinkedTrackObject linkedFrom;
	private String name;
	private Integer popularity;
	@JsonProperty("preview_url")
	private String previewUrl;
	private TrackRestrictionObject restrictions;
	@JsonProperty("track_number")
	private Integer trackNumber;
	private PlayableType type;
	private String uri;
	@JsonProperty(defaultValue = "false")
	private Boolean episode;
	@JsonProperty(defaultValue = "true")
	private Boolean track;

}
