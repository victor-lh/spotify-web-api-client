package com.victorlh.spotify.apiclient.models.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.victorlh.spotify.apiclient.models.objects.ExternalUrlObject;
import com.victorlh.spotify.apiclient.models.objects.FollowersObject;
import com.victorlh.spotify.apiclient.models.objects.ImageObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicUserObject {

    private String id;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("external_urls")
    private ExternalUrlObject externalUrls;
    private FollowersObject followers;
    private String href;
    private List<ImageObject> images;
    private String type;
    private String uri;
}
