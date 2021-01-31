package com.victorlh.spotify.apiclient.models.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceObject {

	private String id;
	@JsonProperty("is_active")
	private Boolean isActive;
	@JsonProperty("is_private_session")
	private Boolean isPrivateSession;
	@JsonProperty("is_restricted")
	private Boolean isRestricted;
	private String name;
	private String type;
	@JsonProperty("volume_percent")
	private Integer volumePercent;
}
