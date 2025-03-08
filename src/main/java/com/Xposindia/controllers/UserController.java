package com.Xposindia.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.Xposindia.constant.Constant;
import com.Xposindia.entities.BookingDetails;
import com.Xposindia.entities.Users;
import com.Xposindia.expections.BizException;
import com.Xposindia.helper.BookingHelper;
import com.Xposindia.helper.CommonHelper;
import com.Xposindia.helper.InvoiceHelper;
import com.Xposindia.helper.UserHelper;
import com.Xposindia.object.request.Request;
import com.Xposindia.object.request.UserRequestObject;
import com.Xposindia.object.response.GenricResponse;
import com.Xposindia.object.response.Response;
import com.Xposindia.service.RozarpayPgService;
import com.Xposindia.service.UserService;


@CrossOrigin(origins = "*")
@RestController
public class UserController 
{	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserHelper userHelper;
	
	@Autowired
	private InvoiceHelper invoiceHelper;
	
	@Autowired
	private BookingHelper bookingHelper;
	
	@Autowired
	private RozarpayPgService rozarser;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private CommonHelper commonHelper;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String test() throws Exception {
		
//		String hi = commonHelper.getJsonString();
		
//		String pocResp = commonHelper.interaktApi(hi);
		
//		System.out.println("response : "+pocResp);
		
		BookingDetails bookingDetails= new BookingDetails();
		
		bookingDetails.setVehicleType("CAR");
		bookingDetails.setCustomerName("Vivek Bharti");
		bookingDetails.setCustomerMobile("8800689752");
		
//		String hi = commonHelper.getJsonString(bookingDetails);
		
//		System.out.println("Response : "+hi);
		
		
//		String hhi = commonHelper.forAdditonalServices(null);

		return "Great..! it's Working...";
	}
	
	
	
	@RequestMapping(path = "sendNotification", method = RequestMethod.POST)
	public Response<UserRequestObject>sendNotification(@RequestBody Request<UserRequestObject> userRequestObject, HttpServletRequest request)
	{
		GenricResponse<UserRequestObject> responseObj = new GenricResponse<UserRequestObject>();
		try {
			UserRequestObject responce =  userService.sendNotification(userRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	/**
	 * New User Registration 
	 **/
	@RequestMapping(path = "userRegistration", method = RequestMethod.POST)
	public Response<UserRequestObject>userRegistration(@RequestBody Request<UserRequestObject> userRequestObject, HttpServletRequest request)
	{
		GenricResponse<UserRequestObject> responseObj = new GenricResponse<UserRequestObject>();
		try {
			String serverPath = userHelper.getServerPath(request);
			userRequestObject.getPayload().setUrlPath(serverPath);

			UserRequestObject responce =  userService.userRegistration(userRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	/**
	 * Update User
	 **/
	@RequestMapping(path = "updateUser", method = RequestMethod.POST)
	public Response<UserRequestObject>updateUser(@RequestBody Request<UserRequestObject> userRequestObject, HttpServletRequest request)
	{
		GenricResponse<UserRequestObject> responseObj = new GenricResponse<UserRequestObject>();
		try {
			String serverPath = userHelper.getServerPath(request);
			userRequestObject.getPayload().setUrlPath(serverPath);
			
			UserRequestObject responce =  userService.updateUser(userRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	/**
	 * Active / Inactive User Status 
	 **/
	@RequestMapping(path = "ActivInactiveUserStatus", method = RequestMethod.POST)
	public Response<UserRequestObject>chaneUserStatus(@RequestBody Request<UserRequestObject> userRequestObject)
	{
		GenricResponse<UserRequestObject> responseObj = new GenricResponse<UserRequestObject>();
		try {
			UserRequestObject responce =  userService.chaneUserStatus(userRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
 			e.printStackTrace();
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }

	
	@RequestMapping(path = "doLogin", method = RequestMethod.POST)
	public Response<UserRequestObject>doLogin(@RequestBody Request<UserRequestObject> userRequestObject, HttpServletRequest request)
	{
		GenricResponse<UserRequestObject> responseObj = new GenricResponse<UserRequestObject>();
		try {
			String serverPath = userHelper.getServerPath(request);
			userRequestObject.getPayload().setUserUrl(serverPath+"/"+Constant.projectName+"/images/");
			userRequestObject.getPayload().setVehicleUrl(serverPath+"/"+Constant.projectName+"/images/");
			
			UserRequestObject responce =  userService.doLogin(userRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {e.printStackTrace();
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "changePassword", method = RequestMethod.POST)
	public Response<UserRequestObject> changePassword(@RequestBody Request<UserRequestObject> userRequestObject) {
		GenricResponse<UserRequestObject> responseObj = new GenricResponse<UserRequestObject>();
		try {
			UserRequestObject responce = userService.changePassword(userRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		} catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE, e.getMessage());
		} catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "getAllUser", method = RequestMethod.POST)
	public Response<Users> getAllUser(@RequestBody Request<UserRequestObject> userRequestObject) {
		GenricResponse<Users> response = new GenricResponse<Users>();
		try {
			List<Users> userList = userService.getAllUser(userRequestObject);
			return response.createListResponse(userList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "getAllAdmin", method = RequestMethod.POST)
	public Response<Users> getAllAdmin(@RequestBody Request<UserRequestObject> userRequestObject) {
		GenricResponse<Users> response = new GenricResponse<Users>();
		try {
			List<Users> userList = userService.getAllAdmin(userRequestObject);
			return response.createListResponse(userList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "getAllSaleUser", method = RequestMethod.POST)
	public Response<Users> getAllSaleUser(@RequestBody Request<UserRequestObject> userRequestObject) {
		GenricResponse<Users> response = new GenricResponse<Users>();
		try {
			List<Users> userList = userService.getAllSaleUser(userRequestObject);
			return response.createListResponse(userList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "getAllUserExceptVendor", method = RequestMethod.POST)
	public Response<Users> getAllUserExceptVendor(@RequestBody Request<UserRequestObject> userRequestObject) {
		GenricResponse<Users> response = new GenricResponse<Users>();
		try {
			List<Users> userList = userService.getAllUserExceptVendor(userRequestObject);
			return response.createListResponse(userList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "getAllVendorUser", method = RequestMethod.POST)
	public Response<Users> getAllVendorUser(@RequestBody Request<UserRequestObject> userRequestObject) {
		GenricResponse<Users> response = new GenricResponse<Users>();
		try {
			List<Users> userList = userService.getAllVendorUser(userRequestObject);
			return response.createListResponse(userList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "getAllDeliveryAgent", method = RequestMethod.POST)
	public Response<Users> getAllDeliveryAgent(@RequestBody Request<UserRequestObject> userRequestObject) {
		GenricResponse<Users> response = new GenricResponse<Users>();
		try {
			List<Users> userList = userService.getAllDeliveryAgent(userRequestObject);
			return response.createListResponse(userList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	
	
	@RequestMapping(path = "getUserByUserId", method = RequestMethod.POST)
	public Response<Users> getUserByUserId(@RequestBody Request<UserRequestObject> userRequestObject) {
		GenricResponse<Users> response = new GenricResponse<Users>();
		try {
			List<Users> userList = userService.getUserByUserId(userRequestObject);
			return response.createListResponse(userList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "getTotalUser", method = RequestMethod.POST)
	public Response<Users> getTotalUser(@RequestBody Request<UserRequestObject> userRequestObject) {
		GenricResponse<Users> response = new GenricResponse<Users>();
		try {
			List<Users> userList = userService.getTotalUser(userRequestObject);
			return response.createListResponse(userList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "getTotalPendingUser", method = RequestMethod.POST)
	public Response<Users> getTotalPendingUser(@RequestBody Request<UserRequestObject> userRequestObject) {
		GenricResponse<Users> response = new GenricResponse<Users>();
		try {
			List<Users> userList = userService.getTotalPendingUser(userRequestObject);
			return response.createListResponse(userList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/userImages/{imageName}", method = RequestMethod.GET)
	public HttpEntity<byte[]> getOrder(@PathVariable String imageName, HttpServletRequest request) throws Exception {

//		userHelper.getServerPath(request);
		byte[] image;

		String basePath = userHelper.getPathToUploadFile("USER");
		String file = basePath + File.separator + imageName;

		try {
			image = org.apache.commons.io.FileUtils.readFileToByteArray(new File(file));
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
			headers.setContentLength(image.length);
			return new HttpEntity<byte[]>(image, headers);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
} 

