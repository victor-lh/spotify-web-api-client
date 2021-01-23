package com.victorlh.spotify.apiclient.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalIdObject {

	private String ean;
	private String isrc;
	private String upc;

}
