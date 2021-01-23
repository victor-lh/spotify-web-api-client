package com.victorlh.spotify.apiclient.httpmanager.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorObject {

    private RegularErrorObject error;
}
