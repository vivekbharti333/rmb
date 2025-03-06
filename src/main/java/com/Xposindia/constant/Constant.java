package com.Xposindia.constant;

public class Constant {
	
	 /** Document path **/
//	 public static final String docLocation = "D:\\PDF\\VehicleImages";
	 public static final String docLocation = "/opt/tomcat-8/webapps/PaymentReceipt";
	 
	 public static final String userPicPath = "/user";	
	 public static final String proofPicPath = "/proof";
	 public static final String VehicleImage = "/VehicleImage";
	 public static final String AadhharImage = "/AadhharImage";
	 public static final String receipt = "/receipt";
	 public static final String VehicleNumberImage = "/VehicleNumberImage";
	 public static final String defaultPath = "/Default";
	 
	 public static final String projectName = "vehicle";

	 /** Response Message **/
	 public static final String INACTIVE = "User is Inactive";
	 public static final String NOT_VERIFIED = "User Not Verified";
	 public static final String EXIST_USER = "User Already registered";
	 public static final String NOT_EXIST_USER = "User Not Exists";
	
	 public static final String MOBILE_EXIST = "Mobile Number Already Link With Other Account";
	 
	 /** Response code **/
	 public static final int SUCCESS_CODE = 200;
	 public static final int NO_CONTENT_CODE = 204;
	 public static final int FAILED_REQUEST_CODE = 400;
	 public static final int BAD_REQUEST_CODE = 401;
	 public static final int INTERNAL_SERVER_ERR = 500;
	
	 /* Response Message */ 
	 public static final String INVALID_REQUEST = "Invalid Request";
	 public static final String INVALID_LOGIN = "Invalid Login Details";
	 
	 public static final String LOGIN_SUCCESS = "Login Successfully";
	 public static final String LOGOUT_SUCCESS = "Logout Successfully";
	 public static final String ALREADY_LOGIN = "Alread Login in Other Devices ";
	 
	 public static final String USER_EXIST = "User ID Already Exists";
	 public static final String USER_NOT_EXIST = "User Not Exists";
	 public static final String USER_NOT_VERIFIED = "User Not Verified";
	 public static final String USER_INACTIVE = "You are Inactive. Wait for Approval.";
	 
	 public static final String CUSTOMER_EXIST = "Customer Already Exists";
	 public static final String CUSTOMER_NOT_EXIST = "Customer Not Exists";
	
	 
	 public static final String MOBILE_NO_EXIST = "Mobile Number Not Exists";
	 public static final String MOBILE_NOT_REGISTERED = "Mobile Number Not Registered";
	 
	 public static final String REGISTERED_SUCCESS = "Registered Successfully";
	 public static final String UPDATED_SUCCESS = "Updated Successfully";
	 public static final String DATA_NOT_FOUND ="Data Not Available";
	 
	 
	 public static final String OTP_NOT_MATCH ="OTP Not Matched";
	 public static final String OTP_EXPIRE ="OTP Expired";
	 
	 public static final String OTP_VERIFIED_SUCCESS ="OTP Verified Successfully";
	 public static final String OTP_SEND_SUCCESS ="OTP Send Successfully";
	 public static final String OTP_SEND_FAILED ="OTP Send Failed";
}
