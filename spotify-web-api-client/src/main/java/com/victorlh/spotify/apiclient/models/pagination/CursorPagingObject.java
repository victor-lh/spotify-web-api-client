package com.victorlh.spotify.apiclient.models.pagination;

import com.victorlh.spotify.apiclient.models.objects.CursorObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursorPagingObject<T> {

	private CursorObject cursors;
	private String href;
	private Integer limit;
	private String next;
	private Integer total;
	private List<T> items;
}
