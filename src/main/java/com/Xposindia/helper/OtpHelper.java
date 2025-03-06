package com.Xposindia.helper;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.Xposindia.constant.Constant;
import com.Xposindia.dao.OtpDetailsDao;
import com.Xposindia.entities.OtpDetails;
import com.Xposindia.expections.BizException;
import com.Xposindia.object.request.UserRequestObject;

@Component
public class OtpHelper {
	
	@Autowired
	private OtpDetailsDao otpDetailsDao;

	public void validateOtpRequest(UserRequestObject userRequestObject) throws BizException {
		if (userRequestObject == null) {
			throw new BizException(Constant.BAD_REQUEST_CODE, "Bad Request Object Null");
		}
	}


@Transactional
public OtpDetails getOtpDetailsByMobileNo(String mobileNumber) {
	Criteria cr = otpDetailsDao.getSession().createCriteria(OtpDetails.class);
	cr.add(Restrictions.eq("mobileNumber", mobileNumber));
	OtpDetails otpDetails = (OtpDetails) cr.uniqueResult();
	return otpDetails;
}

public OtpDetails getOtpDetailsByReqObj(UserRequestObject userRequest, String otp) {

	OtpDetails otpDetails = new OtpDetails();

	otpDetails.setMobileNumber(userRequest.getMobileNo());
	otpDetails.setOtp(otp);
	otpDetails.setCount(0);
	otpDetails.setStatus("INIT");
	otpDetails.setCreatedAt(new Date());
	otpDetails.setUpdatedAt(new Date());

	return otpDetails;
}

@Transactional
public OtpDetails saveOtpDetails(OtpDetails otpDetails) {
	otpDetailsDao.persist(otpDetails);
	return otpDetails;
}

@Transactional
public OtpDetails updateOtpDetails(OtpDetails otpDetails) {
	otpDetailsDao.update(otpDetails);
	return otpDetails;
}
}
