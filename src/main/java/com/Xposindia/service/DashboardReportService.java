package com.Xposindia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xposindia.constant.Constant;
import com.Xposindia.entities.BookingDetails;
import com.Xposindia.entities.Users;
import com.Xposindia.expections.BizException;
import com.Xposindia.helper.AdminVehicleHelper;
import com.Xposindia.helper.CommonHelper;
import com.Xposindia.helper.DashboardReportHelper;
import com.Xposindia.helper.InvoiceHelper;
import com.Xposindia.helper.UserHelper;
import com.Xposindia.helper.VehicleHelper;
import com.Xposindia.helper.WebBookingHelper;
import com.Xposindia.object.request.Request;
import com.Xposindia.object.request.UserRequestObject;
import com.Xposindia.object.request.VehicleRequestObject;

@Service
public class DashboardReportService {

	@Autowired
	private DashboardReportHelper DashboardReportHelper;


//	public VehicleRequestObject getDashboardReportList(Request<VehicleRequestObject> vehicleRequestObject) {
//		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
//		List<Object[]> booking = DashboardReportHelper.getDashboardReportList(vehicleRequest);
//		
//		System.out.println("Booking List : "+booking.get(0).toString());
//		return booking;
//	}
	
	public List<Object[]> getDashboardReportListDaily(Request<VehicleRequestObject> vehicleRequestObject) {
	    VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
	    List<Object[]> booking = DashboardReportHelper.getDashboardReportListDaily(vehicleRequest);
	    
	    return booking;
	}
	
	public List<Object[]> getDashboardReportListWeekly(Request<VehicleRequestObject> vehicleRequestObject) {
	    VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
	    List<Object[]> booking = DashboardReportHelper.getDashboardReportListWeekly(vehicleRequest);
	    
	    System.out.println("Booking List : " + booking.get(0).toString());
	    return booking;
	}
	
	public List<Object[]> getDashboardReportListHalfMonth(Request<VehicleRequestObject> vehicleRequestObject) {
	    VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
	    List<Object[]> booking = DashboardReportHelper.getDashboardReportListHalfMonth(vehicleRequest);
	    
	    System.out.println("Booking List : " + booking.get(0).toString());
	    return booking;
	}
	
	public List<Object[]> getDashboardReportListMonthly(Request<VehicleRequestObject> vehicleRequestObject) {
	    VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
	    List<Object[]> booking = DashboardReportHelper.getDashboardReportListMonthly(vehicleRequest);
	    
	    System.out.println("Booking List : " + booking.get(0).toString());
	    return booking;
	}

	
	
}
