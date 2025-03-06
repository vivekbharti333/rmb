package com.Xposindia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.Xposindia.constant.Constant;
import com.Xposindia.expections.BizException;
import com.Xposindia.object.request.CustomerRequestObject;
import com.Xposindia.object.request.Request;
import com.Xposindia.object.response.GenricResponse;
import com.Xposindia.object.response.Response;
import com.Xposindia.service.CustomerService;


@CrossOrigin(origins = "*")
@RestController
public class CustomerController 
{	
	@Autowired
	private CustomerService customerService;
	
	

	@RequestMapping(path = "generateLoginOtp", method = RequestMethod.POST)
	public Response<CustomerRequestObject>generateLoginOtp(@RequestBody Request<CustomerRequestObject> customerRequestObject)
	{
		GenricResponse<CustomerRequestObject> responseObj = new GenricResponse<CustomerRequestObject>();
		try {
			CustomerRequestObject responce =  customerService.generateLoginOtp(customerRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	
	
	@RequestMapping(path = "verifyLoginOtp", method = RequestMethod.POST)
	public Response<CustomerRequestObject>verifyLoginOtp(@RequestBody Request<CustomerRequestObject> customerRequestObject)
	{
		GenricResponse<CustomerRequestObject> responseObj = new GenricResponse<CustomerRequestObject>();
		try {
			CustomerRequestObject responce =  customerService.verifyLoginOtp(customerRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	
	
	@RequestMapping(path = "customerRegistration", method = RequestMethod.POST)
	public Response<CustomerRequestObject>customerRegistration(@RequestBody Request<CustomerRequestObject> customerRequestObject)
	{
		GenricResponse<CustomerRequestObject> responseObj = new GenricResponse<CustomerRequestObject>();
		try {
			CustomerRequestObject responce =  customerService.customerRegistration(customerRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	
	@RequestMapping(path = "customerLogin", method = RequestMethod.POST)
	public Response<CustomerRequestObject>customerLogin(@RequestBody Request<CustomerRequestObject> customerRequestObject)
	{
		GenricResponse<CustomerRequestObject> responseObj = new GenricResponse<CustomerRequestObject>();
		try {
			CustomerRequestObject responce =  customerService.customerLogin(customerRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	

	
} 

