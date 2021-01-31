package com.victorlh.spotify.spotifyapiclienttest.dto;

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
