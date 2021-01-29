package com.victorlh.spotify.apiclient.models.lists;

import com.victorlh.spotify.apiclient.models.objects.SimplifiedShowObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListSimplifiedShowsObject {

	private List<SimplifiedShowObject> shows;
}
