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
import com.Xposindia.entities.BookingDetailsHistory;
import com.Xposindia.entities.VehicleDetails;
import com.Xposindia.expections.BizException;
import com.Xposindia.helper.BookingHelper;
import com.Xposindia.helper.InvoiceHelper2;
import com.Xposindia.helper.UserHelper;
import com.Xposindia.object.request.Request;
import com.Xposindia.object.request.VehicleRequestObject;
import com.Xposindia.object.response.GenricResponse;
import com.Xposindia.object.response.Response;
import com.Xposindia.service.BookingService;


@CrossOrigin(origins = "*")
@RestController
public class BookingController 
{	
	
	@Autowired
	private UserHelper userHelper;
	
	@Autowired
	private BookingService bookingService;
	
	
//	@Scheduled(cron = "0 * 9 * * *")
//	public void schedularForFollowUpMesgAtTwo() throws Exception {
//		
//		bookingService.schedularForFollowUpMesg("TWO");
//	}
	
//	@Scheduled(cron = "0 * 10 * * *")
//	public void schedularForFollowUpMesgAtThree() throws Exception {
//		
//		bookingService.schedularForFollowUpMesg("THREE");	
//	}
		
//	@Scheduled(cron = "0 * 11 * * *")
//	public void schedularForFollowUpMesgAtFour() throws Exception {
//		
//		bookingService.schedularForFollowUpMesg("FOUR");	
//	}
	
	
	
	@RequestMapping(path = "sendFollowupBulkMesg", method = RequestMethod.POST)
	public Response<VehicleRequestObject>sendFollowupBulkMesg(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  bookingService.sendFollowupBulkMesg(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		}
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }

	
	@RequestMapping(path = "updateVehicleNumber", method = RequestMethod.POST)
	public Response<VehicleRequestObject>updateVehicleNumber(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  bookingService.updateVehicleNumber(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	@RequestMapping(path = "changeLeadStatus", method = RequestMethod.POST)
	public Response<VehicleRequestObject>changeLeadStatus(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  bookingService.changeLeadStatus(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	@RequestMapping(path = "confirmCall", method = RequestMethod.POST)
	public Response<VehicleRequestObject>confirmCall(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  bookingService.confirmCall(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	@RequestMapping(path = "getBookingDetailsForCall", method = RequestMethod.POST)
	public Response<BookingDetails> getBookingDetailsForCall(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> bikeList = bookingService.getBookingDetailsForCall(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "vehicleBooking", method = RequestMethod.POST)
	public Response<VehicleRequestObject>vehicleBooking(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  bookingService.vehicleBooking(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	@RequestMapping(path = "vehicleBookingByApp", method = RequestMethod.POST)
	public Response<VehicleRequestObject>vehicleBookingByApp(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  bookingService.vehicleBookingByApp(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	
	@RequestMapping(path = "updateBookingDetails", method = RequestMethod.POST)
	public Response<VehicleRequestObject>updateBookingDetails(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  bookingService.updateBookingDetails(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	@RequestMapping(path = "updatedBookingDetailsOfMissCall", method = RequestMethod.POST)
	public Response<VehicleRequestObject>updatedBookingDetailsOfMissCall(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  bookingService.updatedBookingDetailsOfMissCall(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	@RequestMapping(path = "makeRefund", method = RequestMethod.POST)
	public Response<VehicleRequestObject>makeRefund(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  bookingService.makeRefund(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	
	@RequestMapping(path = "bookingApprovedOrReject", method = RequestMethod.POST)
	public Response<VehicleRequestObject>bookingApprovedOrReject(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  bookingService.bookingApprovedOrReject(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	
	@RequestMapping(path = "bookingAssigned", method = RequestMethod.POST)
	public Response<VehicleRequestObject>bookingAssigned(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  bookingService.bookingAssigned(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	@RequestMapping(path = "cancelBooking", method = RequestMethod.POST)
	public Response<VehicleRequestObject>cancelBooking(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  bookingService.cancelBooking(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	@RequestMapping(path = "approveOrRejectCancelation", method = RequestMethod.POST)
	public Response<VehicleRequestObject>approveOrRejectCancelation(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  bookingService.approveOrRejectCancelation(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	
	@RequestMapping(path = "changeDeliverStatus", method = RequestMethod.POST)
	public Response<VehicleRequestObject>changeDeliverStatus(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  bookingService.changeDeliverStatus(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	
	@RequestMapping(path = "getRejectedCancelList", method = RequestMethod.POST)
	public Response<BookingDetails> getRejectedCancelList(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> bikeList = bookingService.getRejectedCancelList(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "getRefundedList", method = RequestMethod.POST)
	public Response<BookingDetails> getRefundedList(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> bikeList = bookingService.getRefundedList(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "getBookingDetails", method = RequestMethod.POST)
	public Response<BookingDetails> getBookingDetails(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> bikeList = bookingService.getBookingDetails(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "getbookingByPickupDateAndVehicleType", method = RequestMethod.POST)
	public Response<BookingDetails> getbookingByPickupDateAndVehicleType(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> bikeList = bookingService.getbookingByPickupDateAndVehicleType(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "getBookingDetailsBySellerId", method = RequestMethod.POST)
	public Response<BookingDetails> getBookingDetailsBySellerId(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> bikeList = bookingService.getBookingDetailsBySellerId(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "getBookingDetailsByUserId", method = RequestMethod.POST)
	public Response<BookingDetails> getBookingDetailsByUserId(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> bikeList = bookingService.getBookingDetailsByUserId(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}

	
	@RequestMapping(path = "getTotalEarningByUserId", method = RequestMethod.POST)
	public Response<BookingDetails> getTotalEarningByUserId(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> bikeList = bookingService.getTotalEarningByUserId(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "getUserByVehicleName", method = RequestMethod.POST)
	public Response<VehicleDetails> getUserByVehicleName(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<VehicleDetails> response = new GenricResponse<VehicleDetails>();
		try {
			List<VehicleDetails> vendorList = bookingService.getUserByVehicleName(vehicleRequestObject);
			return response.createListResponse(vendorList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "getbookingVehicleByPickupDate", method = RequestMethod.POST)
	public Response<BookingDetails> getbookingVehicleByPickupDate(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> pickupVehicleList = bookingService.getbookingVehicleByPickupDate(vehicleRequestObject);
			return response.createListResponse(pickupVehicleList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "getbookingVehicleByDropDate", method = RequestMethod.POST)
	public Response<BookingDetails> getbookingVehicleByDropDate(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> pickupVehicleList = bookingService.getbookingVehicleByDropDate(vehicleRequestObject);
			return response.createListResponse(pickupVehicleList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "getbookingVehicleByVehicleDetailsType", method = RequestMethod.POST)
	public Response<BookingDetails> getbookingVehicleByVehicleDetailsType(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> pickupVehicleList = bookingService.getbookingVehicleByVehicleDetailsType(vehicleRequestObject);
			return response.createListResponse(pickupVehicleList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "getbookedVehicle", method = RequestMethod.POST)
	public Response<BookingDetails> getbookedVehicle(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> pickupVehicleList = bookingService.getbookedVehicle(vehicleRequestObject);
			return response.createListResponse(pickupVehicleList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "vehicleReceive", method = RequestMethod.POST)
	public Response<VehicleRequestObject>vehicleReceive(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  bookingService.vehicleReceive(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	@RequestMapping(path = "changeReBookingStatus", method = RequestMethod.POST)
	public Response<VehicleRequestObject>changeReBookingStatus(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  bookingService.changeReBookingStatus(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	
	@RequestMapping(path = "bookingDetails", method = RequestMethod.POST)
	public Response<VehicleRequestObject>bookingDetails(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  bookingService.bookingDetails(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	
	@RequestMapping(path = "penddingBookingDetails", method = RequestMethod.POST)
	public Response<VehicleRequestObject>penddingBookingDetails(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  bookingService.penddingBookingDetails(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	
	@RequestMapping(path = "cancelRequestList", method = RequestMethod.POST)
	public Response<BookingDetails> cancelRequestList(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> pickupVehicleList = bookingService.cancelRequestList(vehicleRequestObject);
			return response.createListResponse(pickupVehicleList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "cancelBookingDetails", method = RequestMethod.POST)
	public Response<BookingDetails> cancelBookingDetails(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> pickupVehicleList = bookingService.cancelBookingDetails(vehicleRequestObject);
			return response.createListResponse(pickupVehicleList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "completedBookingDetails", method = RequestMethod.POST)
	public Response<BookingDetails> completedBookingDetails(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> pickupVehicleList = bookingService.completedBookingDetails(vehicleRequestObject);
			return response.createListResponse(pickupVehicleList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "inprocessBookingDetails", method = RequestMethod.POST)
	public Response<BookingDetails> inprocessBookingDetails(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> pickupVehicleList = bookingService.inprocessBookingDetails(vehicleRequestObject);
			return response.createListResponse(pickupVehicleList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "bookingDetailsGroupByUserName", method = RequestMethod.POST)
	public Response<BookingDetails> bookingDetailsGroupByUserName(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> list = bookingService.bookingDetailsGroupByUserName(vehicleRequestObject);
			return response.createListResponse(list, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "bookingDetailsGroupByUserNameAndCustomeDate", method = RequestMethod.POST)
	public Response<BookingDetails> bookingDetailsGroupByUserNameAndCustomeDate(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> list = bookingService.bookingDetailsGroupByUserNameAndCustomeDate(vehicleRequestObject);
			return response.createListResponse(list, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "getUpdateBookingDetailsHistory", method = RequestMethod.POST)
	public Response<BookingDetailsHistory> getUpdateBookingDetailsHistory(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetailsHistory> response = new GenricResponse<BookingDetailsHistory>();
		try {
			List<BookingDetailsHistory> list = bookingService.getUpdateBookingDetailsHistory(vehicleRequestObject);
			return response.createListResponse(list, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "enqiryDetails", method = RequestMethod.POST)
	public Response<BookingDetails> enqiryDetails(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> list = bookingService.enqiryDetails(vehicleRequestObject);
			return response.createListResponse(list, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "followUpDetails", method = RequestMethod.POST)
	public Response<BookingDetails> fallowUpDetails(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> list = bookingService.followUpDetails(vehicleRequestObject);
			return response.createListResponse(list, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "lostDetails", method = RequestMethod.POST)
	public Response<BookingDetails> lostDetails(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> list = bookingService.lostDetails(vehicleRequestObject);
			return response.createListResponse(list, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "getMissedCallList", method = RequestMethod.POST)
	public Response<BookingDetails> getMissedCallList(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> list = bookingService.getMissedCallList(vehicleRequestObject);
			return response.createListResponse(list, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "getVehicleDetailsBeforeAIM", method = RequestMethod.POST)
	public Response<BookingDetails> getVehicleDetailsBeforeAIM(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> list = bookingService.getVehicleDetailsBeforeAIM(vehicleRequestObject);
			return response.createListResponse(list, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "bookingDetailsByCreatedBy", method = RequestMethod.POST)
	public Response<BookingDetails> bookingDetailsByCreatedBy(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> pickupVehicleList = bookingService.bookingDetailsByCreatedBy(vehicleRequestObject);
			return response.createListResponse(pickupVehicleList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "bookingDetailsByStatusChangeWithOwnnerDetails", method = RequestMethod.POST)
	public Response<BookingDetails> bookingDetailsByStatusChangeWithOwnnerDetails(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> pickupVehicleList = bookingService.bookingDetailsByStatusChangeWithOwnnerDetails(vehicleRequestObject);
			return response.createListResponse(pickupVehicleList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "getNotAssignedBookingForNotification", method = RequestMethod.POST)
	public Response<BookingDetails> getNogetNotAssignedBookingForNotificationtAssignedBooking(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> bikeList = bookingService.getNotAssignedBookingForNotification(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "helpDeskList", method = RequestMethod.POST)
	public Response<BookingDetails> helpDeskList(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> bikeList = bookingService.helpDeskList(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "getBonusDetails", method = RequestMethod.POST)
	public Response<Object[]> getBonusDetails(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<Object[]> response = new GenricResponse<Object[]>();
		try {
			List<Object[]> pickupVehicleList = bookingService.getBonusDetails(vehicleRequestObject);
			return response.createListResponse(pickupVehicleList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "getBonusDetailsOfMonth", method = RequestMethod.POST)
	public Response<Object[]> getBonusDetailsOfMonth(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<Object[]> response = new GenricResponse<Object[]>();
		try {
			List<Object[]> pickupVehicleList = bookingService.getBonusDetailsOfMonth(vehicleRequestObject);
			return response.createListResponse(pickupVehicleList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
//	@RequestMapping(path = "getBonusDetailsByCreatedBy", method = RequestMethod.POST)
//	public Response<Object[]> getBonusDetailsByCreatedBy(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
//		GenricResponse<Object[]> response = new GenricResponse<Object[]>();
//		try {
//			List<Object[]> pickupVehicleList = bookingService.getBonusDetailsByCreatedBy(vehicleRequestObject);
//			return response.createListResponse(pickupVehicleList, 200);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return response.createErrorResponse(400, e.getMessage());
//		}
//	}
	
	@RequestMapping(path = "getBonusDetailsByCreatedBy", method = RequestMethod.POST)
	public Response<VehicleRequestObject>getBonusDetailsByCreatedBy(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  bookingService.getBonusDetailsByCreatedBy(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	
	@RequestMapping(path = "unknownDataList", method = RequestMethod.POST)
	public Response<BookingDetails> unknownDataList(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> bikeList = bookingService.unknownDataList(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "getAlreadyExistsBookingWithSameNUmber", method = RequestMethod.POST)
	public Response<BookingDetails> getAlreadyExistsBookingWithSameNUmber(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<BookingDetails> response = new GenricResponse<BookingDetails>();
		try {
			List<BookingDetails> bikeList = bookingService.getAlreadyExistsBookingWithSameNUmber(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "totalAmountByAgentAndDate", method = RequestMethod.POST)
	public Response<VehicleRequestObject>totalAmountByAgentAndDate(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  bookingService.totalAmountByAgentAndDate(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
	
	@RequestMapping(path = "/downloadReceipt/{receiptNo}", produces="application/pdf" ,method=RequestMethod.GET)
	public ResponseEntity<InputStreamResource> downloadApp(@PathVariable String receiptNo, HttpServletRequest request) throws Exception 
	{
		String basePath = userHelper.getPathToUploadFile("RECIEPT");
		String path = basePath + File.separator + receiptNo + ".pdf";
	    	    
	 		File file = new File(path.toString());
	 	    InputStreamResource isResource = new InputStreamResource(new FileInputStream(file));
	 	    FileSystemResource fileSystemResource = new FileSystemResource(file);
	 	    String fileName = FilenameUtils.getName(file.getAbsolutePath());
	 	    fileName=new String(fileName.getBytes("UTF-8"),"iso-8859-1");
	 	    HttpHeaders headers = new HttpHeaders();
	 	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	 	    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	 	    headers.add("Pragma", "no-cache");
	 	    headers.add("Expires", "0");
	 	    headers.setContentLength(fileSystemResource.contentLength());
	 	    headers.setContentDispositionFormData("attachment", fileName);
	 	    return new ResponseEntity<InputStreamResource>(isResource, headers, HttpStatus.OK); 
	}
	
	@Autowired
	private InvoiceHelper2 invoiceHelper2;
	
	@Autowired
	private BookingHelper bookingHelper;
	
	@RequestMapping(path = "/finalReceipt/{receiptNo}", produces="application/pdf" ,method=RequestMethod.GET)
	public ResponseEntity<InputStreamResource> finalReceipt(@PathVariable String receiptNo, HttpServletRequest request) throws Exception 
	{
		
//		Long ids = (long) Integer.parseInt(id); 
		BookingDetails bookingDetails = bookingHelper.getVehicleDetailsByReceiptNumber(receiptNo);
		
		invoiceHelper2.receiptpdf(bookingDetails);
        
		
		String basePath = userHelper.getPathToUploadFile("RECIEPT");
		String path = basePath + File.separator + bookingDetails.getReceiptNumber() + ".pdf";
		
	 		File file = new File(path.toString());
	 	    InputStreamResource isResource = new InputStreamResource(new FileInputStream(file));
	 	    FileSystemResource fileSystemResource = new FileSystemResource(file);
	 	    String fileName = FilenameUtils.getName(file.getAbsolutePath());
	 	    fileName=new String(fileName.getBytes("UTF-8"),"iso-8859-1");
	 	    HttpHeaders headers = new HttpHeaders();
	 	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	 	    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	 	    headers.add("Pragma", "no-cache");
	 	    headers.add("Expires", "0");
	 	    headers.setContentLength(fileSystemResource.contentLength());
	 	    headers.setContentDispositionFormData("attachment", fileName);
	 	    return new ResponseEntity<InputStreamResource>(isResource, headers, HttpStatus.OK); 
	}
	
	
	@RequestMapping(value = "/numberImages/{imageName}", method = RequestMethod.GET)
	public HttpEntity<byte[]> getOrder(@PathVariable String imageName, HttpServletRequest request) throws Exception {

		userHelper.getServerPath(request);
		byte[] image;

		String basePath = userHelper.getPathToUploadFile("VehicleNumberImage");
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

