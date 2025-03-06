package com.Xposindia.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TemporalType;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.Xposindia.constant.Constant;
import com.Xposindia.dao.BookingDetailsDao;
import com.Xposindia.dao.TotalBookingCountDao;
import com.Xposindia.entities.BonusSlab;
import com.Xposindia.entities.BookingDetails;
import com.Xposindia.entities.BookingDetailsHistory;
import com.Xposindia.entities.TotalBookingCount;
import com.Xposindia.entities.Users;
import com.Xposindia.entities.VehicleDetails;
import com.Xposindia.expections.BizException;
import com.Xposindia.object.request.Request;
import com.Xposindia.object.request.VehicleRequestObject;
import com.itextpdf.text.log.SysoCounter;

@Component
public class DashboardReportHelper {

	@Autowired
	private BookingDetailsDao bookingDetailsDao;


	public void validateBookingRequest(VehicleRequestObject vehicleRequestObject) throws BizException {
		if (vehicleRequestObject == null) {
			throw new BizException(Constant.BAD_REQUEST_CODE, "Bad Request Object Null");
		}
	}



//	@SuppressWarnings("unchecked")
//	public List<Object[]> getDashboardReportList(VehicleRequestObject vehicleRequest) {
//	    
//	    Date date = new Date();
//	    Date nextDay = Date.from(date.toInstant().plus(1, ChronoUnit.DAYS));
//	    
//	    if (vehicleRequest.getMemberType().equals("SALE")) {
//	        List<Object[]> results = bookingDetailsDao.getEntityManager().createQuery(
//	                "SELECT SUM(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.receivedAmount ELSE 0 END), " +
//	                "COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) AS saleCount ," +
//	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END) AS enquiryCount " +
//	                "FROM BookingDetails ud " +
//	                "WHERE ud.createdAt BETWEEN :currentDate AND :lastDate " +
//	                "AND ud.createdBy = :createdBy " +
//	                "GROUP BY ud.createdBy")
//	                .setParameter("currentDate", date, TemporalType.DATE)
//	                .setParameter("lastDate", nextDay, TemporalType.DATE)
//	                .setParameter("createdBy", vehicleRequest.getCreatedBy())
//	                .setParameter("BOOKED", "BOOKED")
//	                .setParameter("REQUESTED", "REQUESTED")
//	                .setParameter("ENQUIRY", "ENQUIRY")
//	                .getResultList();
//	        return results;
//	    }
//	    return null; 
//	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDashboardReportListDaily(VehicleRequestObject vehicleRequest) {
	    
	    Date date = new Date();
	    Date nextDay = Date.from(date.toInstant().plus(1, ChronoUnit.DAYS));
	    
	    
	    if (vehicleRequest.getMemberType().equals("SUPER2ADMIN")) {
	        List<Object[]> results = bookingDetailsDao.getEntityManager().createQuery(
	                "SELECT ud.createdbyName, SUM(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.receivedAmount ELSE 0 END), " +
	                "COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) AS saleCount ," +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END) AS enquiryCount, " +
	                
	                "(((COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) * 100) / " +
	                "(COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) + " +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END)))) as percentage " +
	                "FROM BookingDetails ud " +
	                "WHERE ud.createdAt BETWEEN :currentDate AND :lastDate " +
//	                "AND ud.createdBy = :createdBy " +
	                "GROUP BY ud.createdbyName")
	                .setParameter("currentDate", date, TemporalType.DATE)
	                .setParameter("lastDate", nextDay, TemporalType.DATE)
//	                .setParameter("createdBy", vehicleRequest.getCreatedBy())
	                .setParameter("BOOKED", "BOOKED")
	                .setParameter("REQUESTED", "REQUESTED")
	                .setParameter("ENQUIRY", "ENQUIRY")
	                .getResultList();
	        return results;
	    } else if (vehicleRequest.getMemberType().equals("SALE") 
	    		|| vehicleRequest.getMemberType().equals("CARCHATSALE") 
	    		|| vehicleRequest.getMemberType().equals("WATERSPORTSCHATSALE")) {
	        List<Object[]> results = bookingDetailsDao.getEntityManager().createQuery(
	                "SELECT ud.createdbyName, SUM(CASE WHEN ud.status IN (:BOOKED, :REQUESTED,:CANCELREQUEST,:CANCELREJECT) THEN ud.receivedAmount ELSE 0 END), " +
	                "COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED,:CANCELREQUEST,:CANCELREJECT) THEN ud.id END) AS saleCount ," +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END) AS enquiryCount, " +
	                
	                "(((COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED,:CANCELREQUEST,:CANCELREJECT) THEN ud.id END) * 100) / " +
	                "(COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED,:CANCELREQUEST,:CANCELREJECT) THEN ud.id END) + " +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END)))) as percentage " +
	                "FROM BookingDetails ud " +
	                "WHERE ud.createdAt BETWEEN :currentDate AND :lastDate " +
	                "AND ud.createdBy = :createdBy " +
	                "GROUP BY ud.createdbyName")
	                .setParameter("currentDate", date, TemporalType.DATE)
	                .setParameter("lastDate", nextDay, TemporalType.DATE)
	                .setParameter("createdBy", vehicleRequest.getCreatedBy())
	                .setParameter("BOOKED", "BOOKED")
	                .setParameter("REQUESTED", "REQUESTED")
	                .setParameter("ENQUIRY", "ENQUIRY")
	                .setParameter("CANCELREQUEST", "CANCELREQUEST").setParameter("CANCELREJECT", "CANCELREJECT")
	                .getResultList();
	        return results;
	    } else {
	        List<Object[]> results = bookingDetailsDao.getEntityManager().createQuery(
	                "SELECT ud.createdbyName, SUM(CASE WHEN ud.status IN (:BOOKED, :REQUESTED, :CANCELREQUEST,:CANCELREJECT) THEN ud.receivedAmount ELSE 0 END), " +
	                "COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED,:CANCELREQUEST,:CANCELREJECT) THEN ud.id END) AS saleCount ," +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END) AS enquiryCount, " +
	                
	                "(((COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED,:CANCELREQUEST,:CANCELREJECT) THEN ud.id END) * 100) / " +
	                "(COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED,:CANCELREQUEST,:CANCELREJECT) THEN ud.id END) + " +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END)))) as percentage " +
	                "FROM BookingDetails ud " +
	                "WHERE ud.createdAt BETWEEN :currentDate AND :lastDate AND ud.createdBy NOT IN(:rohan,:rashmi,:akshi,:sameer)" +
	                "GROUP BY ud.createdbyName")
	                .setParameter("currentDate", date, TemporalType.DATE)
	                .setParameter("lastDate", nextDay, TemporalType.DATE)
//	                .setParameter("createdBy", vehicleRequest.getCreatedBy())
	                .setParameter("BOOKED", "BOOKED")
	                .setParameter("REQUESTED", "REQUESTED")
	                .setParameter("ENQUIRY", "ENQUIRY")
	                .setParameter("CANCELREQUEST", "CANCELREQUEST")
	                .setParameter("CANCELREJECT", "CANCELREJECT")
	                .setParameter("rohan", "7845273233")
	                .setParameter("rashmi", "9619283833")
	                .setParameter("akshi", "7694848781")
	                .setParameter("sameer", "7718858883")
	                .getResultList();
	        return results;
	    }
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDashboardReportListWeekly(VehicleRequestObject vehicleRequest) {
	    
	    Date date = new Date();
//	    Date nextDay = Date.from(date.toInstant().plus(1, ChronoUnit.DAYS));
	    
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date monday = c.getTime();
		Date sunday = Date.from(c.toInstant().plus(8, ChronoUnit.DAYS));
		
		System.out.println("Monday : "+monday);
		System.out.println("Sunday : "+sunday);
		
		if (vehicleRequest.getMemberType().equals("SUPER2ADMIN")) {
	        List<Object[]> results = bookingDetailsDao.getEntityManager().createQuery(
	                "SELECT ud.createdbyName, SUM(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.receivedAmount ELSE 0 END), " +
	                "COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) AS saleCount ," +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END) AS enquiryCount, " +
	                
	                "(((COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) * 100) / " +
	                "(COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) + " +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END)))) as percentage " +
	                "FROM BookingDetails ud " +
	                "WHERE ud.createdAt BETWEEN :currentDate AND :lastDate " +
//	                "AND ud.createdBy = :createdBy " +
	                "GROUP BY ud.createdbyName")
	                .setParameter("currentDate", monday, TemporalType.DATE)
	                .setParameter("lastDate", sunday, TemporalType.DATE)
//	                .setParameter("createdBy", vehicleRequest.getCreatedBy())
	                .setParameter("BOOKED", "BOOKED")
	                .setParameter("REQUESTED", "REQUESTED")
	                .setParameter("ENQUIRY", "ENQUIRY")
	                .getResultList();
	        return results;
	    } else if (vehicleRequest.getMemberType().equals("SALE")
	    		|| vehicleRequest.getMemberType().equals("CARCHATSALE") 
	    		|| vehicleRequest.getMemberType().equals("WATERSPORTSCHATSALE")) {
	        List<Object[]> results = bookingDetailsDao.getEntityManager().createQuery(
	                "SELECT ud.createdbyName, SUM(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.receivedAmount ELSE 0 END), " +
	                "COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) AS saleCount ," +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END) AS enquiryCount, " +
	                
	                "(((COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) * 100) / " +
	                "(COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) + " +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END)))) as percentage " +
	                "FROM BookingDetails ud " +
	                "WHERE ud.createdAt BETWEEN :currentDate AND :lastDate " +
	                "AND ud.createdBy = :createdBy " +
	                "GROUP BY ud.createdbyName")
	                .setParameter("currentDate", monday, TemporalType.DATE)
	                .setParameter("lastDate", sunday, TemporalType.DATE)
	                .setParameter("createdBy", vehicleRequest.getCreatedBy())
	                .setParameter("BOOKED", "BOOKED")
	                .setParameter("REQUESTED", "REQUESTED")
	                .setParameter("ENQUIRY", "ENQUIRY")
	                .getResultList();
	        return results;
	    } else {
	        List<Object[]> results = bookingDetailsDao.getEntityManager().createQuery(
	                "SELECT ud.createdbyName, SUM(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.receivedAmount ELSE 0 END), " +
	                "COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) AS saleCount ," +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END) AS enquiryCount, " +
	                
	                "(((COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) * 100) / " +
	                "(COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) + " +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END)))) as percentage " +
	                "FROM BookingDetails ud " +
	                "WHERE ud.createdAt BETWEEN :currentDate AND :lastDate AND ud.createdBy NOT IN(:rohan,:rashmi,:akshi,:sameer)" +
	                "GROUP BY ud.createdbyName")
	                .setParameter("currentDate", monday, TemporalType.DATE)
	                .setParameter("lastDate", sunday, TemporalType.DATE)
	                .setParameter("BOOKED", "BOOKED")
	                .setParameter("REQUESTED", "REQUESTED")
	                .setParameter("ENQUIRY", "ENQUIRY")
	                .setParameter("rohan", "7845273233")
	                .setParameter("rashmi", "9619283833")
	                .setParameter("akshi", "7694848781")
	                .setParameter("sameer", "7718858883")
	                .getResultList();
	        return results;
	    }
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDashboardReportListHalfMonth(VehicleRequestObject vehicleRequest) {
		
		Calendar calendar = Calendar.getInstance();
        Date today = new Date();
        calendar.setTime(today);
        
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();
        
        Date lastdate = Date.from(firstDayOfMonth.toInstant().plus(16, ChronoUnit.DAYS));
        
//        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//        Date lastDayOfMonth = calendar.getTime();
//	    
//	    Date date = new Date();
//	    Date firstDate = Date.from(date.toInstant().minus(15, ChronoUnit.DAYS));
//	    Date lastdate = Date.from(date.toInstant().plus(1, ChronoUnit.DAYS));
        
        if (vehicleRequest.getMemberType().equals("SUPER2ADMIN")) {
	        List<Object[]> results = bookingDetailsDao.getEntityManager().createQuery(
	                "SELECT ud.createdbyName, SUM(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.receivedAmount ELSE 0 END), " +
	                "COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) AS saleCount ," +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END) AS enquiryCount, " +
	                
	                "(((COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) * 100) / " +
	                "(COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) + " +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END)))) as percentage " +
	                "FROM BookingDetails ud " +
	                "WHERE ud.createdAt BETWEEN :currentDate AND :lastDate " +
//	                "AND ud.createdBy = :createdBy " +
	                "GROUP BY ud.createdbyName")
	                .setParameter("currentDate", firstDayOfMonth, TemporalType.DATE)
	                .setParameter("lastDate", lastdate, TemporalType.DATE)
//	                .setParameter("createdBy", vehicleRequest.getCreatedBy())
	                .setParameter("BOOKED", "BOOKED")
	                .setParameter("REQUESTED", "REQUESTED")
	                .setParameter("ENQUIRY", "ENQUIRY")
	                .getResultList();
	        return results;
	    }else if (vehicleRequest.getMemberType().equals("SALE")
	    		|| vehicleRequest.getMemberType().equals("CARCHATSALE") 
	    		|| vehicleRequest.getMemberType().equals("WATERSPORTSCHATSALE")) {
	        List<Object[]> results = bookingDetailsDao.getEntityManager().createQuery(
	                "SELECT ud.createdbyName, SUM(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.receivedAmount ELSE 0 END), " +
	                "COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) AS saleCount ," +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END) AS enquiryCount, " +
	                
	                "(((COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) * 100) / " +
	                "(COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) + " +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END)))) as percentage " +
	                "FROM BookingDetails ud " +
	                "WHERE ud.createdAt BETWEEN :currentDate AND :lastDate " +
	                "AND ud.createdBy = :createdBy " +
	                "GROUP BY ud.createdbyName")
	                .setParameter("currentDate", firstDayOfMonth, TemporalType.DATE)
	                .setParameter("lastDate", lastdate, TemporalType.DATE)
	                .setParameter("createdBy", vehicleRequest.getCreatedBy())
	                .setParameter("BOOKED", "BOOKED")
	                .setParameter("REQUESTED", "REQUESTED")
	                .setParameter("ENQUIRY", "ENQUIRY")
	                .getResultList();
	        return results;
	    } else {
	        List<Object[]> results = bookingDetailsDao.getEntityManager().createQuery(
	                "SELECT ud.createdbyName, SUM(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.receivedAmount ELSE 0 END), " +
	                "COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) AS saleCount ," +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END) AS enquiryCount, " +
	                
	                "(((COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) * 100) / " +
	                "(COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) + " +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END)))) as percentage " +
	                "FROM BookingDetails ud " +
	                "WHERE ud.createdAt BETWEEN :currentDate AND :lastDate AND ud.createdBy NOT IN(:rohan,:rashmi,:akshi,:sameer)" +
	                "GROUP BY ud.createdbyName")
	                .setParameter("currentDate", firstDayOfMonth, TemporalType.DATE)
	                .setParameter("lastDate", lastdate, TemporalType.DATE)
	                .setParameter("BOOKED", "BOOKED")
	                .setParameter("REQUESTED", "REQUESTED")
	                .setParameter("ENQUIRY", "ENQUIRY")
	                .setParameter("rohan", "7845273233")
	                .setParameter("rashmi", "9619283833")
	                .setParameter("akshi", "7694848781")
	                .setParameter("sameer", "7718858883")
	                .getResultList();
	        return results;
	    }
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDashboardReportListMonthly(VehicleRequestObject vehicleRequest) {
	    
		Calendar calendar = Calendar.getInstance();
        Date today = new Date();
        calendar.setTime(today);
        
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();
        
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date lastDayOfMonth = calendar.getTime();
        
        if (vehicleRequest.getMemberType().equals("SUPER2ADMIN")) {
	        List<Object[]> results = bookingDetailsDao.getEntityManager().createQuery(
	                "SELECT ud.createdbyName, SUM(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.receivedAmount ELSE 0 END), " +
	                "COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) AS saleCount ," +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END) AS enquiryCount, " +
	                
	                "(((COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) * 100) / " +
	                "(COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) + " +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END)))) as percentage " +
	                "FROM BookingDetails ud " +
	                "WHERE ud.createdAt BETWEEN :currentDate AND :lastDate " +
//	                "AND ud.createdBy = :createdBy " +
	                "GROUP BY ud.createdbyName")
	                .setParameter("currentDate", firstDayOfMonth, TemporalType.DATE)
	                .setParameter("lastDate", lastDayOfMonth, TemporalType.DATE)
//	                .setParameter("createdBy", vehicleRequest.getCreatedBy())
	                .setParameter("BOOKED", "BOOKED")
	                .setParameter("REQUESTED", "REQUESTED")
	                .setParameter("ENQUIRY", "ENQUIRY")
	                .getResultList();
	        return results;
	    }else if (vehicleRequest.getMemberType().equals("SALE")) {
	        List<Object[]> results = bookingDetailsDao.getEntityManager().createQuery(
	                "SELECT ud.createdbyName, SUM(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.receivedAmount ELSE 0 END), " +
	                "COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) AS saleCount ," +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END) AS enquiryCount, " +
	                
	                "(((COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) * 100) / " +
	                "(COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) + " +
	                "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END)))) as percentage " +
	                "FROM BookingDetails ud " +
	                "WHERE ud.createdAt BETWEEN :currentDate AND :lastDate " +
	                "AND ud.createdBy = :createdBy " +
	                "GROUP BY ud.createdbyName")
	                .setParameter("currentDate", firstDayOfMonth, TemporalType.DATE)
	                .setParameter("lastDate", lastDayOfMonth, TemporalType.DATE)
	                .setParameter("createdBy", vehicleRequest.getCreatedBy())
	                .setParameter("BOOKED", "BOOKED")
	                .setParameter("REQUESTED", "REQUESTED")
	                .setParameter("ENQUIRY", "ENQUIRY")
	                .getResultList();
	        return results;
	    } else {
	        List<Object[]> results = bookingDetailsDao.getEntityManager().createQuery(
	        		"SELECT ud.createdbyName, " +
	                        "SUM(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.receivedAmount ELSE 0 END), " +
	                        "COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) AS saleCount ," +
	                        "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END) AS enquiryCount, " +
	                        "(((COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) * 100) / " +
	                        "(COUNT(CASE WHEN ud.status IN (:BOOKED, :REQUESTED) THEN ud.id END) + " +
	                        "COUNT(CASE WHEN ud.status IN (:ENQUIRY) THEN ud.id END)))) as percentage " +
	                        "FROM BookingDetails ud " +
	                        "WHERE ud.createdAt BETWEEN :currentDate AND :lastDate AND ud.createdBy NOT IN(:rohan,:rashmi,:akshi,:sameer)" +
	                        "GROUP BY ud.createdbyName")
	                .setParameter("currentDate", firstDayOfMonth, TemporalType.DATE)
	                .setParameter("lastDate", lastDayOfMonth, TemporalType.DATE)
	                .setParameter("BOOKED", "BOOKED")
	                .setParameter("REQUESTED", "REQUESTED")
	                .setParameter("ENQUIRY", "ENQUIRY")
	                .setParameter("rohan", "7845273233")
	                .setParameter("rashmi", "9619283833")
	                .setParameter("akshi", "7694848781")
	                .setParameter("sameer", "7718858883")
	                .getResultList();
	        return results;
	    }
	}


}
