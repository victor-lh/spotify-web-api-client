package com.victorlh.spotify.apiclient.models.errors;

import com.victorlh.spotify.apiclient.models.enums.PlayerErrorReason;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerErrorObject {

	private String message;
	private PlayerErrorReason reason;
	private Integer status;
}
