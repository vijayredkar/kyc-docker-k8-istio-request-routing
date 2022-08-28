package com.credit.check.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.credit.check.exception.KycCreditCheckException;
import com.credit.check.repo.model.KycResponse;
import com.credit.check.util.Constants;

@Service
public class KycCreditCheckService {
	
	Log LOGGER = LogFactory.getLog(KycCreditCheckService.class);

	public KycResponse processKycCreditCheck() throws KycCreditCheckException  
	{
		KycResponse kycResponse = new KycResponse();
		
		try 
		{	
			kycResponse.setBlackListed(false);			
		    kycResponse.setBackgroundCheckPass(true);
		    
		    return kycResponse;
		     
		} 
		catch (Exception e) 
		{
			LOGGER.error(Constants.ERROR_MSG_KYC_CREDIT_CHECK_PROCESSING_OPER_FAILED + " : " +e.getMessage());
			throw new KycCreditCheckException(Constants.ERROR_MSG_KYC_CREDIT_CHECK_PROCESSING_OPER_FAILED +" - "+ e.getMessage());
		}
	}	
	
}
