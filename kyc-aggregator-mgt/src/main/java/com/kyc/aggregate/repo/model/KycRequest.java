package com.kyc.aggregate.repo.model;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
public class KycRequest {
	
	private String firstName;
	private String lastName;
	private Date dateOfBirth;	
	private String gender;
	private String maritalStatus;
	private String citizenship;
	private String currentResidenceCountry;
	
	private String passportNumber;
	private Date passportExpiryDate;
	private String passportCountryOfIssue;
}
