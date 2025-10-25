package com.Xposindia.service;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xposindia.constant.Constant;
import com.Xposindia.entities.Customer;
import com.Xposindia.expections.BizException;
import com.Xposindia.helper.CommonHelper;
import com.Xposindia.helper.CustomerHelper;
import com.Xposindia.object.request.CustomerRequestObject;
import com.Xposindia.object.request.Request;

@Service
public class CustomerService {

	@Autowired
	private CustomerHelper customerHelper;
	
	@Autowired
	private CommonHelper commonHelper;

	

	public CustomerRequestObject generateLoginOtp(Request<CustomerRequestObject> customerRequestObject) throws Exception {
		CustomerRequestObject customerRequest = customerRequestObject.getPayload();
		customerHelper.validateCustomerRequest(customerRequest);

		String otp = StringUtils.substring(RandomStringUtils.random(64, false, true), 0, 6);
		customerRequest.setCustomerOtp(otp);
		
		Customer existCustomer = customerHelper.getCustomerDetailsByMobileNo(customerRequest.getCustomerMobile());
		if (existCustomer == null) {
			
			Customer customer = customerHelper.getcustomerOtpByReqObj(customerRequest);
			customer = customerHelper.saveCustomer(customer);

			customerRequest.setRespCode(Constant.SUCCESS_CODE);
			customerRequest.setRespMesg(Constant.REGISTERED_SUCCESS);
		} else {
			
			existCustomer = customerHelper.getUpdatedCustomerOtpByReqObj(customerRequest, existCustomer);
			existCustomer = customerHelper.updateCustomer(existCustomer);
			
			customerRequest.setRespCode(Constant.SUCCESS_CODE);
			customerRequest.setRespMesg(Constant.UPDATED_SUCCESS);
		}
		
		String parameter = commonHelper.forCustomerLoginOtp(customerRequest);
		String response = commonHelper.interaktApi(parameter);
		
		System.out.println("Response : "+response);
		
		return customerRequest;
	}
	
	public CustomerRequestObject verifyLoginOtp(Request<CustomerRequestObject> customerRequestObject) throws BizException {
		CustomerRequestObject customerRequest = customerRequestObject.getPayload();
		customerHelper.validateCustomerRequest(customerRequest);
		
		Customer existCustomer = customerHelper.getCustomerDetailsByMobileNo(customerRequest.getCustomerMobile());
		if (existCustomer != null && existCustomer.getCustomerOtpStatus().equalsIgnoreCase("INIT")) {
			
			long lastTime = existCustomer.getUpdatedAt().getTime();
			long currentTime = new Date().getTime();
			long diffTime = currentTime - lastTime;
			if(diffTime <= 300000) {   
				
				if(existCustomer.getCustomerOtp().equalsIgnoreCase(customerRequest.getCustomerOtp())) {
					existCustomer.setCustomerOtpStatus("VERIFIED");
					existCustomer = customerHelper.updateCustomer(existCustomer);
					
					customerRequest.setRespCode(Constant.SUCCESS_CODE);
					customerRequest.setRespMesg("OTP Match Successfully");
				}else {
					//wrong otp
					customerRequest.setRespCode(Constant.BAD_REQUEST_CODE);
					customerRequest.setRespMesg("OTP Not Match");
				}
			}else {
				//pass expired
				customerRequest.setRespCode(Constant.BAD_REQUEST_CODE);
				customerRequest.setRespMesg("OTP Expired");
			}
			
		}else {
			//invalid
			customerRequest.setRespCode(Constant.BAD_REQUEST_CODE);
			customerRequest.setRespMesg("OTP Is Invalied");
		}
		return customerRequest;
	}
	
	
	public CustomerRequestObject customerRegistration(Request<CustomerRequestObject> customerRequestObject)
			throws BizException, Exception {
		CustomerRequestObject customerRequest = customerRequestObject.getPayload();
		customerHelper.validateCustomerRequest(customerRequest);

		Customer existCustomer = customerHelper.getCustomerDetailsByMobileNo(customerRequest.getCustomerMobile());
		if (existCustomer == null) {

//			Customer customer = customerHelper.getcustomerDetailsByObj(customerRequest);
//			customer = customerHelper.saveCustomer(customer);

			customerRequest.setRespCode(Constant.SUCCESS_CODE);
			customerRequest.setRespMesg(Constant.REGISTERED_SUCCESS);
		} else {
			customerRequest.setRespCode(Constant.FAILED_REQUEST_CODE);
			customerRequest.setRespMesg(Constant.MOBILE_EXIST);
		}
		return customerRequest;
	}


	public CustomerRequestObject customerLogin(Request<CustomerRequestObject> customerRequestObject) 
			throws BizException, Exception {
		CustomerRequestObject customerRequest = customerRequestObject.getPayload();
		customerHelper.validateCustomerRequest(customerRequest);
		
		Customer existsCustomer = customerHelper.getCustomerLoginDetails(customerRequest.getCustomerMobile(), customerRequest.getPassword());
		
		if(existsCustomer != null) {		
			customerRequest.setCustomerName(existsCustomer.getCustomerName());
//			customerRequest.setMobile(existsCustomer.getMobile());
			
			customerRequest.setRespCode(Constant.SUCCESS_CODE);
			customerRequest.setRespMesg("Login Successfull");
		}else {
			customerRequest.setRespCode(Constant.FAILED_REQUEST_CODE);
			customerRequest.setRespMesg("User Not Exists");
		}
		return customerRequest;
	}


	

}