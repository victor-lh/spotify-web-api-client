package com.victorlh.spotify.apiclient.models.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingObject<T> {

	private String href;
	private Integer limit;
	private String next;
	private Integer offset;
	private String previous;
	private Integer total;
	private List<T> items;
}
