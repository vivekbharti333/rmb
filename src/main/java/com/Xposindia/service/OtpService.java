package com.Xposindia.service;

import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xposindia.constant.Constant;
import com.Xposindia.entities.OtpDetails;
import com.Xposindia.expections.BizException;
import com.Xposindia.helper.OtpHelper;
import com.Xposindia.helper.UserHelper;
import com.Xposindia.object.request.Request;
import com.Xposindia.object.request.UserRequestObject;

@Service
public class OtpService {

	@Autowired
	private OtpHelper otpHelper;

	@Autowired
	private UserHelper userHelper;

	public static String generateRandomChars(String candidateChars, int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
		}
		return sb.toString();
	}

	@SuppressWarnings("static-access")
	@Transactional
	public UserRequestObject sendOtp(Request<UserRequestObject> userRequestObject) throws BizException, Exception {
		UserRequestObject userRequest = userRequestObject.getPayload();
//		otpHelper.validateOtpRequest(userRequest);

//		String otp = this.generateRandomChars("1234567890", 6);
		String otp = "123456";

		OtpDetails existsOtpDetails = otpHelper.getOtpDetailsByMobileNo(userRequest.getMobileNo());
		if (existsOtpDetails != null) {
			if (existsOtpDetails.getUpdatedAt() == new Date() && existsOtpDetails.getCount() >= 2) {

				userRequest.setRespCode(Constant.BAD_REQUEST_CODE);
				userRequest.setRespMesg("Contact to admin");
				return userRequest;
			} else {
				existsOtpDetails.setOtp(otp);
				existsOtpDetails.setStatus("INIT");
				existsOtpDetails.setCount(existsOtpDetails.getCount() + 1);
				existsOtpDetails.setUpdatedAt(new Date());

				// Send sms
				userRequest.setRespCode(Constant.SUCCESS_CODE);
				userRequest.setRespMesg("OTP Send on " + userRequest.getMobileNo());
				return userRequest;
			}

		} else {
			OtpDetails otpDetails = otpHelper.getOtpDetailsByReqObj(userRequest, otp);
			otpDetails = otpHelper.saveOtpDetails(otpDetails);

			// send sms
			userRequest.setRespCode(Constant.SUCCESS_CODE);
			userRequest.setRespMesg("OTP Send on " + userRequest.getMobileNo());
			return userRequest;
		}
	}

	public UserRequestObject verifyOtp(Request<UserRequestObject> userRequestObject) throws BizException, Exception {
		UserRequestObject userRequest = userRequestObject.getPayload();
		otpHelper.validateOtpRequest(userRequest);
		OtpDetails existsOtpDetails = otpHelper.getOtpDetailsByMobileNo(userRequest.getMobileNo());
		if (existsOtpDetails != null) {

			Long dbTime = existsOtpDetails.getUpdatedAt().getTime();
			Long nowTime = new Date().getTime();
			Long diffTime = nowTime - dbTime;

//			boolean hi = (existsOtpDetails.getUpdatedAt() == new Date());

			if (existsOtpDetails.getStatus().equalsIgnoreCase("INIT") && (diffTime <= 300000)) {
				if (existsOtpDetails.getOtp().equalsIgnoreCase(userRequest.getOtp())) {

					existsOtpDetails.setStatus("VERIFIED");
					otpHelper.updateOtpDetails(existsOtpDetails);

					userRequest.setRespCode(Constant.SUCCESS_CODE);
					userRequest.setRespMesg("OTP Verified");
					return userRequest;
				} else {
					userRequest.setRespCode(Constant.BAD_REQUEST_CODE);
					userRequest.setRespMesg("Wrong OTP");
					return userRequest;
				}
			} else {
				userRequest.setRespCode(Constant.BAD_REQUEST_CODE);
				userRequest.setRespMesg("OTP Expired");
				return userRequest;
			}
		} else {
			userRequest.setRespCode(Constant.BAD_REQUEST_CODE);
			userRequest.setRespMesg("Wrong OTP");
			return userRequest;
		}
	}
}