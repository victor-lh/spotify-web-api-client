package com.victorlh.spotify.spotifyapiclienttest;

import com.victorlh.spotify.apiclient.httpmanager.ErrorObject;
import com.victorlh.spotify.apiclient.httpmanager.exceptions.SpotifyApiException;
import com.victorlh.spotify.spotifyapiclienttest.dto.ErrorDTO;
import com.victorlh.spotify.spotifyapiclienttest.exceptions.SpotifyApiExceptionRuntime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerExceptions {

	@ExceptionHandler({SpotifyApiExceptionRuntime.class})
	public ResponseEntity<ErrorDTO> handleException(SpotifyApiExceptionRuntime e) {
		SpotifyApiException spotifyApiException = e.getSpotifyApiException();
		int statusCode = spotifyApiException.getStatusCode();
		String responseMessage = spotifyApiException.getResponseMessage();
		ErrorObject responseBody = spotifyApiException.getResponseBody();

		ErrorDTO errorDTO = new ErrorDTO(statusCode, responseMessage, responseBody);
		return new ResponseEntity<>(errorDTO, HttpStatus.valueOf(statusCode));
	}
}