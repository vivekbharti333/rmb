package com.Xposindia.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xposindia.constant.Constant;
import com.Xposindia.entities.BookingDetails;
import com.Xposindia.entities.Users;
import com.Xposindia.entities.VehicleDetails;
import com.Xposindia.enums.Status;
import com.Xposindia.expections.BizException;
import com.Xposindia.helper.BookingHelper;
import com.Xposindia.helper.CommonHelper;
import com.Xposindia.helper.UserHelper;
import com.Xposindia.helper.VehicleHelper;
import com.Xposindia.object.request.Request;
import com.Xposindia.object.request.UserRequestObject;

@Service
public class UserService {

	@Autowired
	private UserHelper userHelper;

	@Autowired
	HttpServletRequest request;

	@Autowired
	private VehicleHelper vehicleHelper;
	
	@Autowired
	private BookingHelper bookingHelper;
	
	@Autowired
	private CommonHelper commonHelper; 
	
	public UserRequestObject sendNotification(Request<UserRequestObject> userRequestObject) 
		throws BizException, Exception {
			UserRequestObject userRequest = userRequestObject.getPayload();
			userHelper.validateUserRequest(userRequest);
		
//			String parameter = commonHelper.unauthorizedLogin(userDetails, userRequest.getIpAddress());
//			commonHelper.interaktApi(parameter);
			
			userRequest.setRespCode(Constant.BAD_REQUEST_CODE);
			userRequest.setRespMesg("Unauthorized Access");
			return userRequest;
//		}

//		return null;
	}

	/**
	 * New User Registration API
	 **/
	public UserRequestObject userRegistration(Request<UserRequestObject> userRequestObject)
			throws BizException, Exception {
		UserRequestObject userRequest = userRequestObject.getPayload();
		userHelper.validateUserRequest(userRequest);

		Users existUsers = userHelper.getUserDetailsByUserId(userRequest.getMobileNo());
		if (existUsers == null) {

//			if (userRequest.getUserImage() != null && !userRequest.getUserImage().isEmpty()) {
//				String basePath = userHelper.getPathToUploadFile("VehicleImage");
//				String fileName = userRequest.getMobileNo().replaceAll(" ", "") + "A";
//				String finalFileName = basePath + File.separator + fileName;
//				String serverPath = fileName;
//				finalFileName = userHelper.uploadPhotos(userRequest.getUserImage(), finalFileName);
//				userRequest.setUserImage(serverPath);
//			}

//			if (userRequest.getAadhaarImage() != null && !userRequest.getAadhaarImage().isEmpty()) {
//				String basePath = userHelper.getPathToUploadFile("VehicleImage");
//				String fileName = userRequest.getAadhaarNumber().replaceAll(" ", "") + "aadhaar";
//				String finalFileName = basePath + File.separator + fileName;
//				String serverPath =  fileName;
//				finalFileName = userHelper.uploadPhotos(userRequest.getAadhaarImage(), finalFileName);
//				userRequest.setAadhaarImage(serverPath);
//			}

//			if (userRequest.getPanImage() != null && !userRequest.getPanImage().isEmpty()) {
//				String basePath = userHelper.getPathToUploadFile("VehicleImage");
//				String fileName = userRequest.getPanNumber().replaceAll(" ", "") + "Pan";
//				String finalFileName = basePath + File.separator + fileName;
//				String serverPath = fileName;
//				finalFileName = userHelper.uploadPhotos(userRequest.getPanImage(), finalFileName);
//				userRequest.setPanImage(serverPath);
//			}

			Users userDetails = userHelper.getUserDetailsByObj(userRequest);
			userDetails = userHelper.saveUser(userDetails);

			userRequest.setRespCode(Constant.SUCCESS_CODE);
			userRequest.setRespMesg(Constant.REGISTERED_SUCCESS);
		} else {
			userRequest.setRespCode(Constant.FAILED_REQUEST_CODE);
			userRequest.setRespMesg(Constant.MOBILE_EXIST);
		}
		return userRequest;
	}
	
	
	public UserRequestObject updateUser(Request<UserRequestObject> userRequestObject)
			throws BizException, Exception {
		UserRequestObject userRequest = userRequestObject.getPayload();
		userHelper.validateUserRequest(userRequest);

		Users existUsers = userHelper.getUserDetailsByUserId(userRequest.getUserId());
		if (existUsers != null) {
			existUsers.setFullName(userRequest.getFullName());
			existUsers.setMobileNo(userRequest.getMobileNo());
			
			existUsers = userHelper.updateUser(existUsers);
			
			userRequest.setRespCode(Constant.SUCCESS_CODE);
			userRequest.setRespMesg("Successfully Update");
			return userRequest;
		}else {
			userRequest.setRespCode(Constant.BAD_REQUEST_CODE);
			userRequest.setRespMesg("User Not Found");
			return userRequest;
		}
		

		}
	

	/**
	 * Active / Inactive User Status
	 **/
	@Transactional
	public UserRequestObject chaneUserStatus(Request<UserRequestObject> userRequestObject)
			throws BizException, Exception {
		UserRequestObject userRequest = userRequestObject.getPayload();
		userHelper.validateUserRequest(userRequest);

		Users existUsers = userHelper.getUserDetailsByUserId(userRequest.getUserId());
		if (existUsers != null) {
			if (Status.INACTIVE.name().equalsIgnoreCase(existUsers.getUserStatus())) {
				existUsers.setUserStatus(Status.ACTIVE.name());

				List<VehicleDetails> vehicleList = vehicleHelper.getAllVehicleList(userRequest.getUserId());
				for (int i = 0; i < vehicleList.size(); i++) {
					VehicleDetails vehicleDetails = vehicleHelper.getVehicleDetailsById(vehicleList.get(i).getId());
					vehicleDetails.setStatus("APPROVED");
					vehicleDetails = vehicleHelper.updateVehicle(vehicleDetails);
				}
			} else {

				List<VehicleDetails> vehicleList = vehicleHelper.getAllVehicleList(userRequest.getUserId());
				for (int i = 0; i < vehicleList.size(); i++) {
					VehicleDetails vehicleDetails = vehicleHelper.getVehicleDetailsById(vehicleList.get(i).getId());
					vehicleDetails.setStatus("PENDING");
					vehicleDetails = vehicleHelper.updateVehicle(vehicleDetails);
				}
				existUsers.setUserStatus(Status.INACTIVE.name());
			}
			existUsers = userHelper.updateUser(existUsers);

			userRequest.setRespCode(Constant.SUCCESS_CODE);
			userRequest.setRespMesg(Constant.UPDATED_SUCCESS);
		} else {
			userRequest.setRespCode(Constant.FAILED_REQUEST_CODE);
			userRequest.setRespMesg(Constant.USER_NOT_EXIST);
		}
		return userRequest;
	}

	@Transactional
	public UserRequestObject doInactive(Request<UserRequestObject> userRequestObject) throws BizException, Exception {
		UserRequestObject userRequest = userRequestObject.getPayload();
		userHelper.validateUserRequest(userRequest);

		Users existUsers = userHelper.getUserDetailsByUserId(userRequest.getUserId());
		if (existUsers != null) {

			existUsers.setUserStatus(Status.ACTIVE.name());
			existUsers = userHelper.updateUser(existUsers);

			List<VehicleDetails> vehicleList = vehicleHelper.getAllVehicleList(userRequest.getUserId());
			for (int i = 0; i < vehicleList.size(); i++) {
				VehicleDetails vehicleDetails = vehicleHelper.getVehicleDetailsById(vehicleList.get(i).getId());
				vehicleDetails.setStatus("INACTIVE");
				vehicleDetails = vehicleHelper.updateVehicle(vehicleDetails);
			}
			userRequest.setRespCode(Constant.SUCCESS_CODE);
			userRequest.setRespMesg(Constant.UPDATED_SUCCESS);
		} else {
			userRequest.setRespCode(Constant.FAILED_REQUEST_CODE);
			userRequest.setRespMesg(Constant.USER_NOT_EXIST);
		}
		return userRequest;
	}

	/**
	 * Login User By User ID
	 **/
	@Transactional
	public UserRequestObject userLogin(Request<UserRequestObject> userRequestObject) throws Exception, BizException {
		UserRequestObject userRequest = userRequestObject.getPayload();
		userHelper.validateUserRequest(userRequest);

		Users existUser = userHelper.getLoginDetailsByUserIdAndPassword(userRequest);
		if (existUser != null) {
			if (existUser.getUserStatus().equalsIgnoreCase(Status.INACTIVE.name())) {
				userRequest.setRespCode(Constant.FAILED_REQUEST_CODE);
				userRequest.setRespMesg(Constant.USER_INACTIVE);
				return userRequest;
			}
			HttpSession session = request.getSession();
			session.setAttribute("userId", userRequest.getUserId());
			session.setAttribute("password", userRequest.getPassword());
			session.setAttribute("memberType", userRequest.getMemberType());

			userRequest.setPassword(null);
			userRequest.setCity(existUser.getCity());
			userRequest.setMobile(existUser.getMobileNo());
			userRequest.setUserName(existUser.getFullName());
			userRequest.setUserRole(existUser.getMemberType());
			userRequest.setMemberType(existUser.getMemberType());
			userRequest.setPermission(existUser.getPermission());

			userRequest.setRespCode(Constant.SUCCESS_CODE);
			userRequest.setRespMesg(Constant.LOGIN_SUCCESS);
		} else {
			userRequest.setRespCode(Constant.FAILED_REQUEST_CODE); 
			userRequest.setRespMesg(Constant.INVALID_LOGIN);
		}
		return userRequest;
	}


	public UserRequestObject doLogin(Request<UserRequestObject> userRequestObject) throws Exception {
		UserRequestObject userRequest = userRequestObject.getPayload();
		userHelper.validateUserRequest(userRequest);
		Users userDetails = userHelper.getLoginDetailsByUserIdAndPassword(userRequest);
//		Users userDetails = userHelper.getUserDetailsByUserId(userRequest.getUserId());
		if (userDetails != null) {
			System.out.println(userRequest.getIpAddress()+" : IP Address");
			
//			String clientIp = request.getHeader("X-Forwarded-For") != null ? request.getHeader("X-Forwarded-For")
//					: request.getRemoteAddr();
//			if(!userRequest.getUserId().equalsIgnoreCase("8810454340")){
//				if(!userRequest.getUserId().equalsIgnoreCase("8976510535")){
//					if(!userRequest.getUserId().equalsIgnoreCase("9833220378")){
//						if(!userRequest.getUserId().equalsIgnoreCase("9619283833")){
//							if(!userRequest.getUserId().equalsIgnoreCase("7845273233")){
//								if(!userRequest.getUserId().equalsIgnoreCase("7694848781")){
//									if(!userRequest.getUserId().equalsIgnoreCase("9922957643")){
//										if(!userRequest.getUserId().equalsIgnoreCase("7718858883")){
//											if(!userRequest.getUserId().equalsIgnoreCase("9989179637")){
//												if(!userRequest.getUserId().equalsIgnoreCase("9820306982")){
//			
//				if (!userRequest.getIpAddress().equalsIgnoreCase("114.79.161.76")) {
//
//					if (!userRequest.getIpAddress().equalsIgnoreCase("106.201.237.211")) {
//						String parameter = commonHelper.unauthorizedLogin(userDetails, userRequest.getIpAddress());
//						String hi = commonHelper.interaktApi(parameter);
//
//						userRequest.setRespCode(Constant.BAD_REQUEST_CODE);
//						userRequest.setRespMesg("Unauthorized Access");
//						return userRequest;
//					}
//				}
//				}}}}}}}}}
//			}
			
//			if(!userRequest.getIpAddress().equalsIgnoreCase("114.79.161.76")) {
//				if(userDetails.getUserId().equalsIgnoreCase("9833220378") // Moshin
//						|| userDetails.getUserId().equalsIgnoreCase("9619283833") // Rashmi
//						|| userDetails.getUserId().equalsIgnoreCase("7845273233") // Arshad
//						|| userDetails.getUserId().equalsIgnoreCase("7694848781") // Akshi
//						|| userDetails.getUserId().equalsIgnoreCase("9922957643") // James
//						) {
//				} else {
//					String parameter = commonHelper.unauthorizedLogin(userDetails, userRequest.getIpAddress());
//					commonHelper.interaktApi(parameter);
//					
//					userRequest.setRespCode(Constant.BAD_REQUEST_CODE);
//					userRequest.setRespMesg("Unauthorized Access");
//					return userRequest;
//				}
//			}
			
//			String clientIp = request.getHeader("X-Forwarded-For") != null ? request.getHeader("X-Forwarded-For") : request.getRemoteAddr();
//			if(!userDetails.getUserId().equalsIgnoreCase("9833220378") // Moshin
//					|| !userDetails.getUserId().equalsIgnoreCase("9619283833") // Rashmi
//					|| !userDetails.getUserId().equalsIgnoreCase("7845273233") // Arshad
//					|| !userDetails.getUserId().equalsIgnoreCase("7694848781") // Akshi
//					|| !userDetails.getUserId().equalsIgnoreCase("9922957643") // James
//					) {
//				
//				if(!clientIp.equalsIgnoreCase("110.226.179.203") || !clientIp.equalsIgnoreCase("114.79.161.76")) {
//					
//					//send sms
//					String parameter = commonHelper.unauthorizedLogin(userDetails, clientIp);
//					commonHelper.interaktApi(parameter);
//					
//					userRequest.setRespCode(Constant.BAD_REQUEST_CODE);
//					userRequest.setRespMesg("unauthorized Access");
//					return userRequest;
//				}
//			}
			
			
			if (userDetails.getUserStatus().equalsIgnoreCase(Status.INACTIVE.name())) {
				userRequest.setUserStatus(userDetails.getUserStatus());
				userRequest.setRespCode(Constant.BAD_REQUEST_CODE);
				userRequest.setRespMesg(Constant.INACTIVE);
				return userRequest;
			} else {
				
//				String clientIp = request.getHeader("X-Forwarded-For") != null ? request.getHeader("X-Forwarded-For") : request.getRemoteAddr();
				
				HttpSession session = request.getSession();
				session.setAttribute("userId", userRequest.getUserId());
				session.setAttribute("password", userRequest.getPassword());
				session.setAttribute("memberType", userRequest.getMemberType());
				session.setAttribute("fullName", userRequest.getFullName());

				userRequest.setUserId(userDetails.getUserId());
				userRequest.setPassword(null);
				userRequest.setFullName(userDetails.getFullName());
				userRequest.setMobileNo(userDetails.getMobileNo());
				userRequest.setMemberType(userDetails.getMemberType());
				userRequest.setParentDetails(userDetails.getParentDetails());
				userRequest.setServices(userDetails.getServices());
				userRequest.setPermission(userDetails.getPermission());
				userRequest.setCreatedBy(userDetails.getCreatedBy());
				
				// change to lost
				List<BookingDetails> booking = bookingHelper.getAllToChangeLost();
				if(booking != null) {
					for (BookingDetails list : booking) {					    
					    list.setStatus("LOST");
					   System.out.println(list.getId());
					    bookingHelper.updateBookingDetails(list);
					}
				}

				userRequest.setRespCode(Constant.SUCCESS_CODE);
				userRequest.setRespMesg(Constant.LOGIN_SUCCESS);
				return userRequest;
			}
		} else {
			userRequest.setRespCode(Constant.BAD_REQUEST_CODE);
			userRequest.setRespMesg(Constant.INVALID_LOGIN);
			return userRequest;
		}
	}

	@Transactional
	public UserRequestObject changePassword(Request<UserRequestObject> userRequestObject)
			throws Exception, BizException {
		UserRequestObject userRequest = userRequestObject.getPayload();
		userHelper.validateUserRequest(userRequest);
		Users existUsers = userHelper.getUserDetailsByUserId(userRequest.getMobile());
		if (existUsers != null) {
			existUsers.setPassword(userRequest.getPassword());
			existUsers = userHelper.updateUser(existUsers);
			
			userRequest.setRespCode(Constant.SUCCESS_CODE);
			userRequest.setRespMesg("Password Reset Successfully");
		} else {
			userRequest.setRespCode(Constant.BAD_REQUEST_CODE);
			userRequest.setRespMesg(Constant.MOBILE_NO_EXIST);
		}
		return userRequest;
	}

	public List<Users> getAllUser(Request<UserRequestObject> userRequestObject) {
		UserRequestObject userRequest = userRequestObject.getPayload();
		List<Users> userList = userHelper.getAllUser(userRequest.getUserId());
		return userList;
	}

	public List<Users> getAllAdmin(Request<UserRequestObject> userRequestObject) {
		UserRequestObject userRequest = userRequestObject.getPayload();
		List<Users> userList = userHelper.getAllAdmin();
		return userList;
	}

	public List<Users> getAllSaleUser(Request<UserRequestObject> userRequestObject) {
		UserRequestObject userRequest = userRequestObject.getPayload();
		List<Users> userList = userHelper.getAllSaleUser();
		return userList;
	}
	
	public List<Users> getAllUserExceptVendor(Request<UserRequestObject> userRequestObject) {
		UserRequestObject userRequest = userRequestObject.getPayload();
		List<Users> userList = userHelper.getAllUserExceptVendor(userRequest);
		return userList;
	}

	public List<Users> getAllVendorUser(Request<UserRequestObject> userRequestObject) {
		UserRequestObject userRequest = userRequestObject.getPayload();
		List<Users> userList = userHelper.getAllVendorUser(userRequest);
		return userList;
	}
	
	public List<Users> getAllDeliveryAgent(Request<UserRequestObject> userRequestObject) {
		UserRequestObject userRequest = userRequestObject.getPayload();
		List<Users> userList = userHelper.getAllDeliveryAgent(userRequest);
		return userList;
	}

	public List<Users> getUserByUserId(Request<UserRequestObject> userRequestObject) {
		UserRequestObject userRequest = userRequestObject.getPayload();
		List<Users> userList = userHelper.getUserByUserId(userRequest.getUserId());
		return userList;
	}

	@SuppressWarnings("unused")
	public List<Users> getTotalUser(Request<UserRequestObject> userRequestObject) {
		UserRequestObject userRequest = userRequestObject.getPayload();
		List<Users> userList = userHelper.getTotalUser();
		return userList;
	}

	@SuppressWarnings("unused")
	public List<Users> getTotalPendingUser(Request<UserRequestObject> userRequestObject) {
		UserRequestObject userRequest = userRequestObject.getPayload();
		List<Users> userList = userHelper.getTotalPendingUser();
		return userList;
	}




}