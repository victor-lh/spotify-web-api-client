package com.victorlh.spotify.apiclient.models.pagination;

import com.victorlh.spotify.apiclient.models.objects.CategoryObject;
import com.victorlh.spotify.apiclient.models.pagination.PagingObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCategoriesPagingObject {

	private PagingObject<CategoryObject> categories;
}
