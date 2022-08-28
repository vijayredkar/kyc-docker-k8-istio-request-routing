package com.kyc.aggregate.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kyc.aggregate.exception.KycAggregateException;
import com.kyc.aggregate.repo.model.KycRequest;
import com.kyc.aggregate.repo.model.KycResponse;
import com.kyc.aggregate.util.Constants;

@Service
public class KycAggregateService {
	
	Log LOGGER = LogFactory.getLog(KycAggregateService.class);
	
	//@Value("${credit-check-generic-istio-url}")
	String crdChkUnspecifiedIstioUrl;
	
	//@Value("${credit-check-basic-istio-url}")
	String crdChkBasicIstioUrl;
		
	//@Value("${credit-check-advanced-istio-url}")
	String crdChkAdvancedIstioUrl;
	
	@Value("${istio-gateway-port}")
	String istioGatewayPort;
	
	@Value("${istio-base-url}")
	String istioBaseUrl;
	
	@Value("${credit-check-basic-uri}")
	String creditCheckBasicUri;
	
	@Value("${credit-check-advanced-uri}")
	String creditCheckAdvancedUri;
	
	@Value("${credit-check-unspecified-uri}")
	String creditCheckUnspecifiedUri;
	
	@Value("${credit-check-report-uri}")
	String creditCheckReportUri;
	
	
	
	
	
	
	//---mTLS
	String crdChkBasicIstioMtlsUrl;
	String crdChkAdvancedIstioMtlsUrl;
	
	
	@Value("${istio-base-mtls-url}")
	String istioBaseMtlsUrl;	
	@Value("${istio-base-credit-check-basic-mtls-url}")
	String istioBaseCredChkBasicMtlsUrl;	
	@Value("${istio-base-credit-check-advanced-mtls-url}")
	String istioBaseCredChkAdvancedMtlsUrl;
	
	
	
	@Value("${istio-gateway-credit-check-basic-mtls-port}")
	String istioGatewayCreditCheckBasicMtlsPort;
	
	@Value("${istio-gateway-credit-check-advanced-mtls-port}")
	String istioGatewayCreditCheckAdvancedMtlsPort;
	//---mTLS

	public KycResponse processKyc(KycRequest kycReq, String type, String headerValue, String creditCheck) throws KycAggregateException  
	{
		KycResponse kycResponse = new KycResponse();
		
		
		
		/*
		urlList.add("http://127.0.0.1:31746/credit-check/basic");
	    urlList.add("http://192.168.49.2:31746/credit-check/basic");
	    urlList.add("http://kyc-credit-check-basic:8080");
	    urlList.add("http://kyc-credit-check-basic:8080/basic");
	    urlList.add("http://kyc-credit-check-basic/basic");
	    urlList.add("http://127.0.0.1:8080/credit-check/basic");
	    */
		
		try 
		{	
		    if(Constants.CREDIT_CHECK_TYPE_BASIC_REPORT.equals(type))//request routing
		    {
		    	crdChkBasicIstioUrl = istioBaseUrl + istioGatewayPort + creditCheckBasicUri;
		    	LOGGER.info("\n----  Istio redirection - credit check basic - url : "+crdChkBasicIstioUrl);
		    	kycResponse = invokeService(kycReq, crdChkBasicIstioUrl);
		    }
		    
		    else if(Constants.CREDIT_CHECK_TYPE_ADVANCED_REPORT.equals(type))//request routing
		    {
		    	crdChkAdvancedIstioUrl = istioBaseUrl + istioGatewayPort + creditCheckAdvancedUri;
		    	LOGGER.info("\n----  Istio redirection - credit check advanced - url : "+crdChkAdvancedIstioUrl);
		    	kycResponse = invokeService(kycReq, crdChkAdvancedIstioUrl);	
		    }
		    
		    else if(Constants.CREDIT_CHECK_TYPE_BASIC_MTLS_REPORT.equals(type))//mTLS
		    {		    	
		    	//--tests
		    	
		    	List<String> urlList = new ArrayList<String>();
			    		    	
		    	try
			    {
			    	//String url  = "http://127.0.0.1:8080/credit-check/basic";	
			    	String url  = "http://kyc-credit-check-basic.basic:8080/credit-check/basic";
			    	System.out.println("##### vijay url "+url);
			    	
			    	kycResponse = invokeService(kycReq, url);
			    	System.out.println("##### vijay kycResponse "+kycResponse);
			    }
			    catch(Exception e)
			    {
			    	//works - Istio invocation - preferred
			    	//LOGGER.error("\n----************* url invocation failed. Continue to test next. Error: "+ e);
			    	LOGGER.error("\n----************* Error e: "+ e);
			    	LOGGER.error("\n----************* Error getMessage: "+ e.getMessage());
			    	LOGGER.error("\n----************* Error getCause: "+ e.getCause());
			    	LOGGER.error("\n----************* Error getStackTrace: "+ e.getStackTrace());			    	
			    	e.printStackTrace();
			    	throw e;
			    }
		    	
		    	//--tests
		    	
		    }
		    else if(Constants.CREDIT_CHECK_TYPE_ADVANCED_MTLS_REPORT.equals(type))//mTLS
		    {
		    	//crdChkAdvancedIstioMtlsUrl = istioBaseMtlsUrl + istioGatewayCreditCheckAdvancedMtlsPort + creditCheckAdvancedUri;
		    	
		    	/*
		    	crdChkAdvancedIstioMtlsUrl = istioBaseCredChkAdvancedMtlsUrl + istioGatewayCreditCheckAdvancedMtlsPort + creditCheckAdvancedUri;
		    	
		    	LOGGER.info("\n----  Istio redirection - credit check advanced mTLS - url : "+crdChkAdvancedIstioMtlsUrl);
		    	kycResponse = invokeService(kycReq, crdChkAdvancedIstioMtlsUrl);
		    	*/
		    	
		    	
		    	
		    	
		    	//--tests
		    	/*
		    	crdChkBasicIstioMtlsUrl = "http://127.0.0.1:31746/credit-check/basic";		    	
		    	LOGGER.info("\n----  Istio redirection - credit check basic mTLS - url : "+crdChkBasicIstioMtlsUrl);
		    	kycResponse = invokeService(kycReq, crdChkBasicIstioMtlsUrl);	
		    	LOGGER.info("\n----  Istio redirection - credit check basic mTLS - kycResponse : "+kycResponse);
		    	
		    	
		    	crdChkBasicIstioMtlsUrl = "http://192.168.49.2:31746/credit-check/basic";		    	
		    	LOGGER.info("\n----  Istio redirection - credit check basic mTLS - url : "+crdChkBasicIstioMtlsUrl);
		    	kycResponse = invokeService(kycReq, crdChkBasicIstioMtlsUrl);	
		    	LOGGER.info("\n----  Istio redirection - credit check basic mTLS - kycResponse : "+kycResponse);
		    	
		    	
		    	crdChkBasicIstioMtlsUrl = "http://kyc-credit-check-basic:8080";		    	
		    	LOGGER.info("\n----  Istio redirection - credit check basic mTLS - url : "+crdChkBasicIstioMtlsUrl);
		    	kycResponse = invokeService(kycReq, crdChkBasicIstioMtlsUrl);	
		    	LOGGER.info("\n----  Istio redirection - credit check basic mTLS - kycResponse : "+kycResponse);
		    	
		    	
		    	crdChkBasicIstioMtlsUrl = "http://kyc-credit-check-basic:8080/basic";		    	
		    	LOGGER.info("\n----  Istio redirection - credit check basic mTLS - url : "+crdChkBasicIstioMtlsUrl);
		    	kycResponse = invokeService(kycReq, crdChkBasicIstioMtlsUrl);	
		    	LOGGER.info("\n----  Istio redirection - credit check basic mTLS - kycResponse : "+kycResponse);
		    	
		    	
		    	crdChkBasicIstioMtlsUrl = "http://kyc-credit-check-basic/basic";		    	
		    	LOGGER.info("\n----  Istio redirection - credit check basic mTLS - url : "+crdChkBasicIstioMtlsUrl);
		    	kycResponse = invokeService(kycReq, crdChkBasicIstioMtlsUrl);	
		    	LOGGER.info("\n----  Istio redirection - credit check basic mTLS - kycResponse : "+kycResponse);
		    	
		    	
		    	crdChkBasicIstioMtlsUrl = "http://127.0.0.1:8080/credit-check/basic";		    	
		    	LOGGER.info("\n----  Istio redirection - credit check basic mTLS - url : "+crdChkBasicIstioMtlsUrl);
		    	kycResponse = invokeService(kycReq, crdChkBasicIstioMtlsUrl);	
		    	LOGGER.info("\n----  Istio redirection - credit check basic mTLS - kycResponse : "+kycResponse);
		    	*/
		    	
		    	/*
		    	List<String> urlList = new ArrayList<String>();
			    
			    urlList.add("http://127.0.0.1:31746/credit-check/basic");
			    urlList.add("http://192.168.49.2:31746/credit-check/basic");
			    urlList.add("http://kyc-credit-check-basic:8080");
			    urlList.add("http://kyc-credit-check-basic:8080/basic");
			    urlList.add("http://kyc-credit-check-basic/basic");
			    urlList.add("http://127.0.0.1:8080/credit-check/basic");//--
			    
			   
				    for(String url : urlList)
				    {
				    	try
					    {
					    	LOGGER.info("\n----************* url : "+ url);	
					    	
					    	//response = restTemplate.postForEntity(url, kycReq, KycResponse.class);
							//kycResponse = response.getBody();
							
							kycResponse = invokeService(kycReq, url);
							
							System.out.println("##### vijay kycResponse "+kycResponse);
							LOGGER.info("\n----************* kycResponse : "+ kycResponse);
						    }
					    catch(Exception e)
					    {
					    	LOGGER.error("\n----************* url invocation failed. Continue to test next. Error: "+ e);
					    }
				    }//end for
				   */
		    	
		    	try
			    {
		    		
		    		/*-------------  invokes the Microservice endpoint directly. Used when direct internal POD-POD communication is needed ----------------*/	
			    	//				 http://kyc-credit-check-advanced.advanced:8080/credit-check/advanced
			    		
		    		/*-------------  invokes the Gateway-VirtualService-Routing-DestinationRule-Microservice endpoint. Used when Gateway/VS rules are to be applied ----------------*/
			    	//				 http://192.168.49.2:31291/credit-check/advanced
			    	
		    		
		    		
			    	//String url  = "http://127.0.0.1:8080/credit-check/advanced";    //works - Istio invocation
		    		String url  = "http://kyc-credit-check-advanced.advanced:8080/credit-check/advanced"; //works - Istio invocation - preferred
			    		
			    	System.out.println("##### vijay url "+url);
			    	
			    	kycResponse = invokeService(kycReq, url);
			    	System.out.println("##### vijay kycResponse "+kycResponse);
			    }
			    catch(Exception e)
			    {//works - Istio invocation - preferred
			    	//LOGGER.error("\n----************* url invocation failed. Continue to test next. Error: "+ e);
			    	LOGGER.error("\n----************* Error e: "+ e);
			    	LOGGER.error("\n----************* Error getMessage: "+ e.getMessage());
			    	LOGGER.error("\n----************* Error getCause: "+ e.getCause());
			    	LOGGER.error("\n----************* Error getStackTrace: "+ e.getStackTrace());			    	
			    	e.printStackTrace();
			    	throw e;
			    }
		    	
		    	//--tests
		    }
		    else if(Constants.CREDIT_CHECK_HEADER_REDIRECT.equals(type))//request routing w/ header values
		    {	
		    	try
			    {
		    	
		    	/*-------------  invokes the Microservice endpoint directly. Used when direct internal POD-POD communication is needed ----------------*/	
		    	//				 http://kyc-credit-check-advanced.advanced:8080/credit-check/advanced
		    		
	    		/*-------------  invokes the Gateway-VirtualService-Routing-DestinationRule-Microservice endpoint. Used when Gateway/VS rules are to be applied ----------------*/
		    	//				 http://192.168.49.2:31291/credit-check/advanced
		    				    		
		    		
		    		String url  =  istioBaseUrl + istioGatewayPort + "/credit-check/" + creditCheck;
			    	LOGGER.info("\n----  Istio redirection W/ HEADER 456 - credit check - url : "+url);
		    		
		    		
		    		
			    		
			    	System.out.println("##### vijay url "+url);
			    	
			    	kycResponse = invokeServiceHdrRedirect(kycReq, url, headerValue);
			    	System.out.println("##### vijay kycResponse "+kycResponse);
			    }
			    catch(Exception e)
			    {//works - Istio invocation - preferred
			    	//LOGGER.error("\n----************* url invocation failed. Continue to test next. Error: "+ e);
			    	LOGGER.error("\n----************* Error e: "+ e);
			    	LOGGER.error("\n----************* Error getMessage: "+ e.getMessage());
			    	LOGGER.error("\n----************* Error getCause: "+ e.getCause());
			    	LOGGER.error("\n----************* Error getStackTrace: "+ e.getStackTrace());			    	
			    	e.printStackTrace();
			    	throw e;
			    }
		    	
		    	//--tests
		    }
		    else
		    {
		    	String crdChkReportIstioUrl = istioBaseUrl + istioGatewayPort + creditCheckReportUri;
		    	LOGGER.info("\n----  Istio weight distribution between credit check basic & advanced - url : "+crdChkReportIstioUrl);
		    	kycResponse = invokeService(kycReq, crdChkReportIstioUrl);
		    }
		    /*else
		    {
		    	crdChkUnspecifiedIstioUrl = istioBaseUrl + istioGatewayPort + creditCheckUnspecifiedUri;
		    	LOGGER.info("\n----  Istio weight distribution between credit check basic & advanced - url : "+crdChkUnspecifiedIstioUrl);
		    	kycResponse = invokeService(kycReq, crdChkUnspecifiedIstioUrl);
		    }*///end else
		    
		     kycResponse.setBackgroundCheckPass(true);
		     return kycResponse;
		     
		} 
		catch (Exception e) 
		{
			LOGGER.error(Constants.ERROR_MSG_KYC_PROCESSING_OPER_FAILED + " : " +e);
			throw new KycAggregateException(Constants.ERROR_MSG_KYC_PROCESSING_OPER_FAILED +" - "+ e.getMessage());
		}
	}

	private KycResponse invokeService(KycRequest kycReq, String url) 
	{
		RestTemplate restTemplate = new RestTemplate();	
		KycResponse kycResponse;
		ResponseEntity<KycResponse> response;
		
		LOGGER.info("\n----************* url : "+ url);	
		
		//response = restTemplate.postForEntity(url, kycReq, KycResponse.class);
		response = restTemplate.getForEntity(url, KycResponse.class);
		
		System.out.println("##### response "+response);
		kycResponse = response.getBody();
		System.out.println("##### kycResponse "+kycResponse);
		
		
		LOGGER.info("\n----************* kycResponse : "+ kycResponse);
		return kycResponse;
	}
	
	private KycResponse invokeServiceHdrRedirect(KycRequest kycReq, String url, String headerValue) 
	{
		RestTemplate restTemplate = new RestTemplate();	
		KycResponse kycResponse;
		ResponseEntity<KycResponse> response;
		
		
		//created custom header which will be used by the Virtual Service rules for request routing 
		HttpHeaders headers = new HttpHeaders();
		headers.set("end-user", headerValue);
		
		
		HttpEntity entityReq = new HttpEntity(kycReq, headers);

		
		LOGGER.info("\n----************* url with header : "+ url);
		LOGGER.info("\n----************* headers : "+ headers.toString());
		
		
		//response = restTemplate.postForEntity(url, kycReq, KycResponse.class);
		//response = restTemplate.getForEntity(url, KycResponse.class);
		response = restTemplate.exchange(url, HttpMethod.GET, entityReq, KycResponse.class);
		
		
		System.out.println("##### response "+response);
		kycResponse = response.getBody();
		System.out.println("##### kycResponse "+kycResponse);
		
		
		LOGGER.info("\n----************* kycResponse : "+ kycResponse);
		return kycResponse;
	}
	
}
