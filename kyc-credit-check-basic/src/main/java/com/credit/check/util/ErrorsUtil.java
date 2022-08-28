package com.credit.check.util;

public class ErrorsUtil {

	  public static ErrorResponse constructErrorResponse(String errMsg)
	  {
		  ErrorResponse errorResponse = new ErrorResponse();
		  
		  errorResponse.setErrorMessage(errMsg);
		  errorResponse.setErrorCode(Constants.ERROR_CODE_KYC_CREDIT_CHECK_PROCESSING);
		  errorResponse.setType(Constants.ERROR_TYPE_KYC_CREDIT_CHECK_PROCESSING);
		  
		  return errorResponse;
	  }
}
