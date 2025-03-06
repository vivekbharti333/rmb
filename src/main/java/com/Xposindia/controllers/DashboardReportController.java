package com.Xposindia.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.Xposindia.constant.Constant;
import com.Xposindia.entities.BookingDetails;
import com.Xposindia.entities.VehicleDetails;
import com.Xposindia.expections.BizException;
import com.Xposindia.helper.UserHelper;
import com.Xposindia.object.request.Request;
import com.Xposindia.object.request.VehicleRequestObject;
import com.Xposindia.object.response.GenricResponse;
import com.Xposindia.object.response.Response;
import com.Xposindia.service.BookingService;
import com.Xposindia.service.DashboardReportService;
import com.Xposindia.service.WebBookingService;


@CrossOrigin(origins = "*")
@RestController
public class DashboardReportController 
{	
	
	@Autowired
	private UserHelper userHelper;
	
	@Autowired
	private DashboardReportService dashboardReportService;
		
	
//	@RequestMapping(path = "getDashboardReportList", method = RequestMethod.POST)
//	public Response<VehicleRequestObject>getDashboardReportList(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) throws BizException
//	{
//		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
//		try {
//			VehicleRequestObject responce =  dashboardReportService.getDashboardReportList(vehicleRequestObject);
//			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
//		}catch (Exception e) {
//			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
//		}
//	 }
	
	@RequestMapping(path = "getDashboardReportListDaily", method = RequestMethod.POST)
	public Response<List<Object[]>> getDashboardReportListDaily(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) throws BizException {
	    GenricResponse<List<Object[]>> responseObj = new GenricResponse<>();
	    try {
	        List<Object[]> response = dashboardReportService.getDashboardReportListDaily(vehicleRequestObject);
	        return responseObj.createSuccessResponse(response, Constant.SUCCESS_CODE);
	    } catch (Exception e) {
	        return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
	    }
	}
	
	
	@RequestMapping(path = "getDashboardReportListWeekly", method = RequestMethod.POST)
	public Response<List<Object[]>> getDashboardReportListWeekly(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) throws BizException {
	    GenricResponse<List<Object[]>> responseObj = new GenricResponse<>();
	    try {
	        List<Object[]> response = dashboardReportService.getDashboardReportListWeekly(vehicleRequestObject);
	        return responseObj.createSuccessResponse(response, Constant.SUCCESS_CODE);
	    } catch (Exception e) {
	        return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
	    }
	}
	
	@RequestMapping(path = "getDashboardReportListHalfMonth", method = RequestMethod.POST)
	public Response<List<Object[]>> getDashboardReportListHalfMonth(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) throws BizException {
	    GenricResponse<List<Object[]>> responseObj = new GenricResponse<>();
	    try {
	        List<Object[]> response = dashboardReportService.getDashboardReportListHalfMonth(vehicleRequestObject);
	        return responseObj.createSuccessResponse(response, Constant.SUCCESS_CODE);
	    } catch (Exception e) {
	        return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
	    }
	}
	
	@RequestMapping(path = "getDashboardReportListMonthly", method = RequestMethod.POST)
	public Response<List<Object[]>> getDashboardReportListMonthly(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) throws BizException {
	    GenricResponse<List<Object[]>> responseObj = new GenricResponse<>();
	    try {
	        List<Object[]> response = dashboardReportService.getDashboardReportListMonthly(vehicleRequestObject);
	        return responseObj.createSuccessResponse(response, Constant.SUCCESS_CODE);
	    } catch (Exception e) {
	        return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
	    }
	}
	

} 

