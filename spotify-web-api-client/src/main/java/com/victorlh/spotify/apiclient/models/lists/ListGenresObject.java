package com.victorlh.spotify.apiclient.models.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListGenresObject {

	private List<String> genres;
}
