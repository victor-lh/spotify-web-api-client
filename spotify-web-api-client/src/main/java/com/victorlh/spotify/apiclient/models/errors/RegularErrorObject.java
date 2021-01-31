package com.victorlh.spotify.apiclient.models.errors;

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
