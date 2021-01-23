package com.victorlh.spotify.apiclient.httpmanager.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegularErrorObject {

    private Integer status;
    private String message;
}
