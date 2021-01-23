package com.victorlh.spotify.apiclient.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExplicitContentSettingsObject {

    @JsonProperty("filter_enabled")
    private Boolean filterEnabled;

    @JsonProperty("filter_locked")
    private Boolean filterLocked;

}
