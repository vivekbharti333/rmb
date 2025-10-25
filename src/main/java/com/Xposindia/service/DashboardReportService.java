package com.Xposindia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xposindia.helper.DashboardReportHelper;
import com.Xposindia.object.request.Request;
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
