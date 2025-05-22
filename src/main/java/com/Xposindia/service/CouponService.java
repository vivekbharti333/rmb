package com.Xposindia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xposindia.constant.Constant;
import com.Xposindia.entities.CouponDetails;
import com.Xposindia.expections.BizException;
import com.Xposindia.helper.CouponHelper;
import com.Xposindia.object.request.CouponRequestObject;
import com.Xposindia.object.request.Request;

@Service
public class CouponService {

	@Autowired
	private CouponHelper couponHelper;

	public CouponRequestObject generateCouponDetails(Request<CouponRequestObject> couponRequestObject) throws BizException {

		CouponRequestObject couponRequest = couponRequestObject.getPayload();
		couponHelper.validateCouponRequest(couponRequest);

		CouponDetails existsCoupondetails = couponHelper.getCouponDetailsByCoupon(couponRequest.getCoupon());
		if (existsCoupondetails == null) {

			CouponDetails couponDetails = couponHelper.getCouponDetailsByReqObj(couponRequest);
			couponDetails = couponHelper.saveCouponDetails(couponDetails);

			couponRequest.setRespCode(Constant.SUCCESS_CODE);
			couponRequest.setRespMesg("Coupon Renerated Successfully");
		} else {
			couponRequest.setRespCode(Constant.BAD_REQUEST_CODE);
			couponRequest.setRespMesg("Coupon Already Exists");
		}
		return couponRequest;
	}
	
	public CouponRequestObject validateCouponDetails(Request<CouponRequestObject> couponRequestObject) throws BizException {
		CouponRequestObject couponRequest = couponRequestObject.getPayload();
		couponHelper.validateCouponRequest(couponRequest);
		
		CouponDetails couponDetails = couponHelper.getValidCouponDetails(couponRequest.getCoupon());
		if (couponDetails != null) {
			couponRequest.setCouponAmount(couponDetails.getCouponAmount());
			couponRequest.setRespCode(Constant.SUCCESS_CODE);
			couponRequest.setRespMesg("Coupon Validate Successfully");
		} else {
			couponRequest.setRespCode(Constant.BAD_REQUEST_CODE);
			couponRequest.setRespMesg("Invalid Coupon");
		}
		
		return couponRequest;
	}

	public List<CouponDetails> getCouponDetails(Request<CouponRequestObject> couponRequestObject) {
		CouponRequestObject couponRequest = couponRequestObject.getPayload();

		List<CouponDetails> callQualityDetails = couponHelper.getCouponDetails(couponRequest);
		return callQualityDetails;
	}

	
	
	
	
	
	
	
	

//	public QualityRequestObject getScore(Request<QualityRequestObject> qualityRequestObject) throws BizException {
//
//		QualityRequestObject qualityRequest = qualityRequestObject.getPayload();
//		callQualityHelper.validateCallQualityRequest(qualityRequest);
//
//		List<CallQualityDetails> sum = callQualityHelper.getTotalScoreForCallQualityDetails(qualityRequest);
//
//		List<CallQualityDetails> count = callQualityHelper.getTotalCountForCallQualityDetails(qualityRequest);
//
//		if (sum.get(0) != null) {
//			Object tota = sum.get(0);
//			Object coun = count.get(0);
//			Long totalScore = (Long) tota;
//			Long totalCount = (Long) coun;
//
//			double score = totalScore / totalCount;
//
//			qualityRequest.setTotalScore(score);
//
//			System.out.println(score + " " + totalCount);
//
//			qualityRequest.setRespCode(Constant.SUCCESS_CODE);
//			qualityRequest.setRespMesg("Save Successfull");
//			return qualityRequest;
//		} else {
//			qualityRequest.setTotalScore(0);
//
//			qualityRequest.setRespCode(Constant.SUCCESS_CODE);
//			qualityRequest.setRespMesg("Save Successfull");
//			return qualityRequest;
//		}
//	}

}