package com.victorlh.spotify.apiclient.models.errors;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthErrorObject {

	private String error;
	@JsonProperty("error_description")
	private String errorDescription;
}
