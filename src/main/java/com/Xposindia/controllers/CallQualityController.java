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
import com.Xposindia.entities.UsersArea;
import com.Xposindia.entities.UsersCity;
import com.Xposindia.entities.VehicleBrand;
import com.Xposindia.entities.VehicleName;
import com.Xposindia.expections.BizException;
import com.Xposindia.object.request.VehicleRequestObject;
import com.Xposindia.object.request.QualityRequestObject;
import com.Xposindia.object.request.Request;
import com.Xposindia.object.request.UserRequestObject;
import com.Xposindia.object.response.GenricResponse;
import com.Xposindia.object.response.Response;
import com.Xposindia.service.AdminVehicleService;
import com.Xposindia.service.CallQualityService;


@CrossOrigin(origins = "*")
@RestController
public class CallQualityController 
{		
	

	@Autowired
	private CallQualityService callQualityService;
	
	
	@RequestMapping(path = "saveCallQuality", method = RequestMethod.POST)
	public Response<QualityRequestObject>saveCallQuality(@RequestBody Request<QualityRequestObject> qualityRequestObject)
	{
		GenricResponse<QualityRequestObject> responseObj = new GenricResponse<QualityRequestObject>();
		try {
			QualityRequestObject responce =  callQualityService.saveCallQuality(qualityRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	
	@RequestMapping(path = "getCallQualityDetails", method = RequestMethod.POST)
	public Response<CallQualityDetails> getCallQualityDetails(
			@RequestBody Request<QualityRequestObject> qualityRequestObject) {
		GenricResponse<CallQualityDetails> response = new GenricResponse<CallQualityDetails>();
		try {
			List<CallQualityDetails> vehicleBrandList = callQualityService.getCallQualityDetails(qualityRequestObject);
			return response.createListResponse(vehicleBrandList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "getScore", method = RequestMethod.POST)
	public Response<QualityRequestObject>getScore(@RequestBody Request<QualityRequestObject> qualityRequestObject)
	{
		GenricResponse<QualityRequestObject> responseObj = new GenricResponse<QualityRequestObject>();
		try {
			QualityRequestObject responce =  callQualityService.getScore(qualityRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
} 

