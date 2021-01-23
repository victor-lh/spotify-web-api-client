package com.victorlh.spotify.spotifyapiclienttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class SpotifyApiClientTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpotifyApiClientTestApplication.class, args);
	}

	@Bean
	@Scope("singleton")
	public CredentialsWrapper credentialsWrapper() {
		return new CredentialsWrapper();
	}
}
