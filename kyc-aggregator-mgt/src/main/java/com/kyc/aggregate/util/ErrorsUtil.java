package com.kyc.aggregate.util;

public class ErrorsUtil {

	  public static ErrorResponse constructErrorResponse(String errMsg)
	  {
		  ErrorResponse errorResponse = new ErrorResponse();
		  
		  errorResponse.setErrorMessage(errMsg);
		  errorResponse.setErrorCode(Constants.ERROR_CODE_KYC_PROCESSING);
		  errorResponse.setType(Constants.ERROR_TYPE_KYC_PROCESSING);
		  
		  return errorResponse;
	  }
}
