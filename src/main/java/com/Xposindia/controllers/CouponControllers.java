package com.Xposindia.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.Xposindia.constant.Constant;
import com.Xposindia.entities.CallQualityDetails;
import com.Xposindia.entities.CouponDetails;
import com.Xposindia.entities.UsersArea;
import com.Xposindia.entities.UsersCity;
import com.Xposindia.entities.VehicleBrand;
import com.Xposindia.entities.VehicleName;
import com.Xposindia.expections.BizException;
import com.Xposindia.object.request.VehicleRequestObject;
import com.Xposindia.object.request.CouponRequestObject;
import com.Xposindia.object.request.QualityRequestObject;
import com.Xposindia.object.request.Request;
import com.Xposindia.object.request.UserRequestObject;
import com.Xposindia.object.response.GenricResponse;
import com.Xposindia.object.response.Response;
import com.Xposindia.service.AdminVehicleService;
import com.Xposindia.service.CallQualityService;
import com.Xposindia.service.CouponService;


@CrossOrigin(origins = "*")
@RestController
public class CouponControllers 
{		
	

	@Autowired
	private CouponService couponService;
	
	
	@RequestMapping(path = "generateCouponDetails", method = RequestMethod.POST)
	public Response<CouponRequestObject>generateCouponDetails(@RequestBody Request<CouponRequestObject> couponRequestObject)
	{
		GenricResponse<CouponRequestObject> responseObj = new GenricResponse<CouponRequestObject>();
		try {
			CouponRequestObject responce =  couponService.generateCouponDetails(couponRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	@RequestMapping(path = "validateCouponDetails", method = RequestMethod.POST)
	public Response<CouponRequestObject>validateCouponDetails(@RequestBody Request<CouponRequestObject> couponRequestObject)
	{
		GenricResponse<CouponRequestObject> responseObj = new GenricResponse<CouponRequestObject>();
		try {
			CouponRequestObject responce =  couponService.validateCouponDetails(couponRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	
	@RequestMapping(path = "getCouponDetails", method = RequestMethod.POST)
	public Response<CouponDetails> getCallQualityDetails(@RequestBody Request<CouponRequestObject> couponRequestObject) {
		GenricResponse<CouponDetails> response = new GenricResponse<CouponDetails>();
		try {
			List<CouponDetails> couponDetailsList = couponService.getCouponDetails(couponRequestObject);
			return response.createListResponse(couponDetailsList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
//	@RequestMapping(path = "getScore", method = RequestMethod.POST)
//	public Response<QualityRequestObject>getScore(@RequestBody Request<QualityRequestObject> qualityRequestObject)
//	{
//		GenricResponse<QualityRequestObject> responseObj = new GenricResponse<QualityRequestObject>();
//		try {
//			QualityRequestObject responce =  couponService.getScore(qualityRequestObject);
//			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
//		}catch (BizException e) {
//			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
//		} 
// 		catch (Exception e) {
//			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
//		}
//	 }
	
} 

