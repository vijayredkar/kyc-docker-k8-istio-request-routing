package com.credit.check.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credit.check.exception.KycCreditCheckException;
import com.credit.check.repo.model.KycRequest;
import com.credit.check.repo.model.KycResponse;
import com.credit.check.service.KycCreditCheckService;
import com.credit.check.util.Constants;
import com.credit.check.util.ErrorResponse;
import com.credit.check.util.ErrorsUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/credit-check/")
public class KycCreditCheckController {

	Log LOGGER = LogFactory.getLog(KycCreditCheckController.class);
	
	@Autowired
	KycCreditCheckService kycAggrSvc;
	
	@Autowired
	ObjectMapper mapper;
		
	@GetMapping(path = "/basic") 
	public ResponseEntity<String> creditCheckRoutingTest1() throws JsonProcessingException
	{		
		LOGGER.info("\n---- POST Kyc Credit Check Basic creditCheckRoutingTest1 initiated");
		
		ResponseEntity<String> response = processCreditCheck();
		
		LOGGER.info("\n---- Kyc Credit Check Basic creditCheckRoutingTest1 completed");		
		return response;
	}
	
	@GetMapping(path = "/report") 
	public ResponseEntity<String> creditCheckRoutingTest2() throws JsonProcessingException
	{		
		LOGGER.info("\n---- GET Kyc Credit Check Basic creditCheckRoutingTest2 initiated");
		
		ResponseEntity<String> response = processCreditCheck();
		
		LOGGER.info("\n---- Kyc Credit Check Basic creditCheckRoutingTest2 completed");		
		return response;
	}
	
	private ResponseEntity<String> processCreditCheck() throws JsonProcessingException
	{		
		KycResponse kycResponse = null;						
		String response = null;
		
		try 
		{		
		 kycResponse = kycAggrSvc.processKycCreditCheck();
		}
		catch (KycCreditCheckException e) 
		{
			ErrorResponse errResponse = ErrorsUtil.constructErrorResponse(e.getMessage());
			
			response = mapper.writeValueAsString(errResponse);
			LOGGER.error(Constants.ERROR_MSG_KYC_CREDIT_CHECK_PROCESSING_OPER_FAILED + " : " +e.getMessage());
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
			LOGGER.info(response + "  "+kycResponse.toString());
			
			response = mapper.writeValueAsString(kycResponse);					
			return new ResponseEntity<String>(response, HttpStatus.OK);
	}	
}


