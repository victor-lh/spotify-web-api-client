package com.victorlh.spotify.spotifyapiclienttest;

import com.victorlh.spotify.apiclient.exceptions.SpotifyAuthApiException;
import com.victorlh.spotify.apiclient.exceptions.SpotifyGeneralApiException;
import com.victorlh.spotify.apiclient.httpmanager.exceptions.SpotifyApiException;
import com.victorlh.spotify.apiclient.httpmanager.models.AuthErrorObject;
import com.victorlh.spotify.apiclient.httpmanager.models.ErrorObject;
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
        if (spotifyApiException instanceof SpotifyAuthApiException) {
            return tratarErrorAuth((SpotifyAuthApiException) spotifyApiException);
        } else if (spotifyApiException instanceof SpotifyGeneralApiException) {
            return tratarErrorGeneral((SpotifyGeneralApiException) spotifyApiException);
        } else {
            int statusCode = spotifyApiException.getStatusCode();
            String responseMessage = spotifyApiException.getResponseMessage();

            ErrorDTO errorDTO = new ErrorDTO(statusCode, responseMessage, null);
            return new ResponseEntity<>(errorDTO, HttpStatus.valueOf(statusCode));
        }
    }

    private ResponseEntity<ErrorDTO> tratarErrorAuth(SpotifyAuthApiException e) {
        int statusCode = e.getStatusCode();
        String responseMessage = e.getResponseMessage();
        AuthErrorObject responseBody = e.getAuthErrorBody();

        ErrorDTO errorDTO = new ErrorDTO(statusCode, responseMessage, responseBody);
        return new ResponseEntity<>(errorDTO, HttpStatus.valueOf(statusCode));
    }

    private ResponseEntity<ErrorDTO> tratarErrorGeneral(SpotifyGeneralApiException e) {
        int statusCode = e.getStatusCode();
        String responseMessage = e.getResponseMessage();
        ErrorObject responseBody = e.getErrorBody();

        ErrorDTO errorDTO = new ErrorDTO(statusCode, responseMessage, responseBody);
        return new ResponseEntity<>(errorDTO, HttpStatus.valueOf(statusCode));
    }

}