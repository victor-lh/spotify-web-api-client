package com.victorlh.spotify.spotifyapiclienttest.dto;

import com.victorlh.spotify.apiclient.httpmanager.models.AuthErrorObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {

	private int status;
	private String message;
	private Object error;
}
