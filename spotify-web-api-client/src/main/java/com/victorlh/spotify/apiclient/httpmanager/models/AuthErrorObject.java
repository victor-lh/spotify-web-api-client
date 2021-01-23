package com.victorlh.spotify.apiclient.httpmanager.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
public class AuthErrorObject {

	private String error;
	@JsonProperty("error_description")
	private String errorDescription;
}
