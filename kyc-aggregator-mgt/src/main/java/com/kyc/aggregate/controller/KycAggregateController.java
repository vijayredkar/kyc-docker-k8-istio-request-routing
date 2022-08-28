package com.kyc.aggregate.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyc.aggregate.exception.KycAggregateException;
import com.kyc.aggregate.repo.model.KycRequest;
import com.kyc.aggregate.repo.model.KycResponse;
import com.kyc.aggregate.service.KycAggregateService;
import com.kyc.aggregate.util.Constants;
import com.kyc.aggregate.util.ErrorResponse;
import com.kyc.aggregate.util.ErrorsUtil;

@RestController
@RequestMapping(value = "/kyc/v1/")
public class KycAggregateController {

	Log LOGGER = LogFactory.getLog(KycAggregateController.class);
	
	@Autowired
	KycAggregateService kycAggrSvc;
	
	@Autowired
	ObjectMapper mapper;
	
		@GetMapping(path = "/customer")
		public ResponseEntity<String> getKyc(@RequestParam(required = false, name = "type") String type ,
											@RequestParam(required = false, name = "headerValue") String headerValue ,
											@RequestParam(required = false, name = "creditCheck") String creditCheck ) 
											throws JsonProcessingException
		{
			KycResponse kycResponse = null;
					
			LOGGER.info("\n----****## Kyc Aggrregator Mgt initiated with type :" + type);
			String response = null;
			
			try 
			{		
				KycRequest kycRequest = new KycRequest();
				kycResponse = kycAggrSvc.processKyc(kycRequest, type, headerValue, creditCheck );
			}
			catch (KycAggregateException e) 
			{
				ErrorResponse errResponse = ErrorsUtil.constructErrorResponse(e.getMessage());
				
				response = mapper.writeValueAsString(errResponse);
				LOGGER.error(Constants.ERROR_MSG_KYC_PROCESSING_OPER_FAILED + " : " +e);
				return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			LOGGER.info("\n---- Kyc Aggrregator Mgt completed");
			LOGGER.info(response + "  "+kycResponse.toString());
			
			response = mapper.writeValueAsString(kycResponse);
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
		
		@GetMapping(path = "/simpleTrafficTLS")
		public ResponseEntity<String> testSimpleTLSInvoke(@RequestParam(required = false, name = "type") String type ) throws JsonProcessingException
		{
			KycResponse kycResponse = null;
					
			LOGGER.info("---- Kyc Aggrregator Mgt initiated testSimpleTLSInvoke :" + type);
			String response = null;
			
			try 
			{		
				LOGGER.info("---- Kyc Aggrregator Mgt simpleTrafficTLS succeeded");
				response = "Kyc Aggrregator Mgt simpleTrafficTLS succeeded";
			}
			catch (Exception e) 
			{
				
				LOGGER.error("**** Kyc Aggrregator Mgt simpleTrafficTLS failed : "  + e);
				response = "Kyc Aggrregator Mgt simpleTrafficTLS failed";
				
				return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
}


