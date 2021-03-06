package com.victorlh.spotify.apiclient.models.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.victorlh.spotify.apiclient.models.objects.ExplicitContentSettingsObject;
import com.victorlh.spotify.apiclient.models.objects.ExternalUrlObject;
import com.victorlh.spotify.apiclient.models.objects.FollowersObject;
import com.victorlh.spotify.apiclient.models.objects.ImageObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivateUserObject {

    private String id;
    private String country;
    @JsonProperty("display_name")
    private String displayName;
    private String email;
    @JsonProperty("explicit_content")
    private ExplicitContentSettingsObject explicitContent;
    @JsonProperty("external_urls")
    private ExternalUrlObject externalUrls;
    private FollowersObject followers;
    private String href;
    private List<ImageObject> images;
    private String product;
    private String type;
    private String uri;
}
