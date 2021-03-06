package com.victorlh.spotify.apiclient.models.objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.victorlh.spotify.apiclient.models.objects.AlbumObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavedAlbumObject {

	@JsonProperty("added_at")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date addedAt;
	private AlbumObject album;
}
