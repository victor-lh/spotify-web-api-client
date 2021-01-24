package com.victorlh.spotify.apiclient.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryObject {

	private String href;
	private List<ImageObject> icons;
	private String id;
	private String name;

}
