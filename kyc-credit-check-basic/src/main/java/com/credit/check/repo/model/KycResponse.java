package com.credit.check.repo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
public class KycResponse {

	private String type = "BASIC";
	private boolean blackListed;
	private boolean backgroundCheckPass;
}
