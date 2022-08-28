package com.kyc.aggregate.repo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
public class KycResponse {

	private String type;
	private boolean blackListed;
	private boolean restrictedNational;
	private boolean suspiciousActivity;
	private boolean interpolConductBad;
	private boolean militaryTrained;
	private boolean employmentBad;
	private boolean creditUsageBad;
	private boolean backgroundCheckPass;
}
