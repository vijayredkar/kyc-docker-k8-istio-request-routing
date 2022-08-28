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

	private String type = "ADVANCED";
	private boolean blackListed;
	private boolean restrictedNational;
	private boolean suspiciousActivity;
	private boolean interpolConductBad;
	private boolean militaryTrained;
	private boolean employmentBad;
	private boolean creditUsageBad;
	private boolean backgroundCheckPass;
}
