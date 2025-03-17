package com.Xposindia.helper;

import com.Xposindia.dao.BookingDetailsDao;
import com.Xposindia.dao.TotalBookingCountDao;
import com.Xposindia.entities.BookingDetails;
import com.Xposindia.entities.BookingDetailsHistory;
import com.Xposindia.entities.TotalBookingCount;
import com.Xposindia.entities.VehicleDetails;
import com.Xposindia.expections.BizException;
import com.Xposindia.object.request.Request;
import com.Xposindia.object.request.VehicleRequestObject;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.TemporalType;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BookingHelper {
   @Autowired
   private BookingDetailsDao bookingDetailsDao;
   @Autowired
   private TotalBookingCountDao totalBookingCountDao;

   public void validateBookingRequest(VehicleRequestObject vehicleRequestObject) throws BizException {
      if (vehicleRequestObject == null) {
         throw new BizException(401, "Bad Request Object Null");
      }
   }
   
   
   
   @Transactional
   public BookingDetails getVehicleDetailsByReceiptNumber(String receiptNumber) {
      Criteria cr = this.bookingDetailsDao.getSession().createCriteria(BookingDetails.class);
      cr.add(Restrictions.eq("receiptNumber", receiptNumber));
      BookingDetails bookingDetails = (BookingDetails)cr.uniqueResult();
      return bookingDetails;
   }
   
   @Transactional
   public BookingDetails getVehicleDetailsByBookingId(String bookingId) {
      Criteria cr = this.bookingDetailsDao.getSession().createCriteria(BookingDetails.class);
      cr.add(Restrictions.eq("bookingId", bookingId));
      BookingDetails bookingDetails = (BookingDetails)cr.uniqueResult();
      return bookingDetails;
   }

   @Transactional
   public BookingDetails getVehicleDetailsById(Long id) {
      Criteria cr = this.bookingDetailsDao.getSession().createCriteria(BookingDetails.class);
      cr.add(Restrictions.eq("id", id));
      BookingDetails bookingDetails = (BookingDetails)cr.uniqueResult();
      return bookingDetails;
   }

   @Transactional
   public BookingDetails getVehicleDetailsByObj(VehicleRequestObject vehicleRequest) throws ParseException {
      BookingDetails bookingDetails = new BookingDetails();
      bookingDetails.setEnquirySource(vehicleRequest.getEnquirySource());
      bookingDetails.setVehicleType(vehicleRequest.getVehicleType());
      bookingDetails.setVehicleBrand(vehicleRequest.getVehicleBrand());
      bookingDetails.setVehicleName(vehicleRequest.getVehicleName());
      bookingDetails.setCity(vehicleRequest.getCity());
      bookingDetails.setVehicleQuantity(vehicleRequest.getVehicleQuantity());
      bookingDetails.setAdminVehiclePrice(vehicleRequest.getAdminVehiclePrice());
      bookingDetails.setCustomerName(vehicleRequest.getFirstName() + " " + vehicleRequest.getLastName());
      bookingDetails.setCountryCode(vehicleRequest.getCountryCode());
      bookingDetails.setCustomerMobile(vehicleRequest.getCustomerMobile());
      bookingDetails.setVehicleDetailsType(vehicleRequest.getVehicleDetailsType());
      bookingDetails.setStatus(vehicleRequest.getStatus());
      bookingDetails.setReBookingStatus("OPEN");
      bookingDetails.setBookingPrice(vehicleRequest.getBookingPrice());
      bookingDetails.setMiscellaneous(vehicleRequest.getMiscellaneous());
      bookingDetails.setFromDate(vehicleRequest.getFromDate());
      bookingDetails.setToDate(vehicleRequest.getToDate());
      bookingDetails.setDeliveryTime(vehicleRequest.getDeliveryTime());
      bookingDetails.setPickupTime(vehicleRequest.getPickupTime());
      bookingDetails.setAreaFrom(vehicleRequest.getAreaFrom());
      bookingDetails.setAreaTo(vehicleRequest.getAreaTo());
      bookingDetails.setDeliveryCharges(vehicleRequest.getDeliveryCharges());
      bookingDetails.setReceivedAmount(vehicleRequest.getReceivedAmount());
      bookingDetails.setBalenceAmount(vehicleRequest.getBalenceAmount());
      bookingDetails.setTotalAmount(vehicleRequest.getTotalAmount());
      bookingDetails.setNotes(vehicleRequest.getNotes());
      bookingDetails.setSecurityDeposit(vehicleRequest.getSecurityDeposit());
      bookingDetails.setAdminId("9922957643");
      bookingDetails.setAdminName("James N");
      bookingDetails.setDeliveryExecutiveId("N/A");
      bookingDetails.setDeliveryExecutiveName("N/A");
      bookingDetails.setCreatedBy(vehicleRequest.getCreatedBy());
      bookingDetails.setCreatedbyName(vehicleRequest.getCreatedbyName());
      bookingDetails.setCreatedAt(new Date());
      bookingDetails.setUpdatedAt(new Date());
      return bookingDetails;
   }

   @Transactional
   public BookingDetails saveBookingDetails(BookingDetails bookingDetails) {
      this.bookingDetailsDao.persist(bookingDetails);
      return bookingDetails;
   }

   @Transactional
   public BookingDetails getUpdatedBookingDetailsByObj(VehicleRequestObject vehicleRequest, BookingDetails bookingDetails) throws ParseException {
      bookingDetails.setVehicleType(vehicleRequest.getVehicleType());
      bookingDetails.setVehicleDetailsType(vehicleRequest.getVehicleDetailsType());
      bookingDetails.setVehicleBrand(vehicleRequest.getVehicleBrand());
      bookingDetails.setVehicleName(vehicleRequest.getVehicleName());
      bookingDetails.setVehicleQuantity(vehicleRequest.getVehicleQuantity());
      bookingDetails.setCity(bookingDetails.getCity());
      bookingDetails.setVenderVehiclePrice(vehicleRequest.getVenderVehiclePrice());
      bookingDetails.setAdminVehiclePrice(vehicleRequest.getAdminVehiclePrice());
      bookingDetails.setCustomerName(vehicleRequest.getCustomerName());
      bookingDetails.setCountryCode(vehicleRequest.getCountryCode());
      bookingDetails.setCustomerMobile(vehicleRequest.getCustomerMobile());
      if (vehicleRequest.getCreatedBy().equals("7845273233")) {
         bookingDetails.setCustomerMobile(vehicleRequest.getCustomerMobile());
      }

      bookingDetails.setStatus(vehicleRequest.getStatus());
      bookingDetails.setReBookingStatus(bookingDetails.getReBookingStatus());
      bookingDetails.setBookingPrice(vehicleRequest.getBookingPrice());
      bookingDetails.setMiscellaneous(vehicleRequest.getMiscellaneous());
      bookingDetails.setFromDate(vehicleRequest.getFromDate());
      bookingDetails.setToDate(vehicleRequest.getToDate());
      bookingDetails.setDeliveryTime(vehicleRequest.getDeliveryTime());
      bookingDetails.setPickupTime(vehicleRequest.getPickupTime());
      bookingDetails.setAreaFrom(vehicleRequest.getAreaFrom());
      bookingDetails.setAreaTo(vehicleRequest.getAreaTo());
      bookingDetails.setDeliveryCharges(vehicleRequest.getDeliveryCharges());
      bookingDetails.setReceivedAmount(vehicleRequest.getReceivedAmount());
      bookingDetails.setBalenceAmount(vehicleRequest.getBalenceAmount());
      bookingDetails.setTotalAmount(vehicleRequest.getTotalAmount());
      bookingDetails.setNotes(vehicleRequest.getNotes());
      bookingDetails.setAdminId("9922957643");
      bookingDetails.setAdminName("James N");
      bookingDetails.setSecurityDeposit(vehicleRequest.getSecurityDeposit());
      bookingDetails.setCreatedBy(vehicleRequest.getCreatedBy());
      bookingDetails.setCreatedbyName(vehicleRequest.getCreatedbyName());
      bookingDetails.setUpdatedAt(new Date());
      return bookingDetails;
   }

   @Transactional
   public BookingDetails updateBookingDetails(BookingDetails bookingDetails) {
      this.bookingDetailsDao.update(bookingDetails);
      return bookingDetails;
   }

   @Transactional
   public BookingDetails getUpdatedBookingDetailsByMissCallByObj(VehicleRequestObject vehicleRequest, BookingDetails bookingDetails) throws ParseException {
      bookingDetails.setEnquirySource(vehicleRequest.getEnquirySource());
      bookingDetails.setVehicleType(vehicleRequest.getVehicleType());
      bookingDetails.setVehicleBrand(vehicleRequest.getVehicleBrand());
      bookingDetails.setVehicleName(vehicleRequest.getVehicleName());
      bookingDetails.setCity(vehicleRequest.getCity());
      bookingDetails.setVehicleQuantity(vehicleRequest.getVehicleQuantity());
      bookingDetails.setAdminVehiclePrice(vehicleRequest.getAdminVehiclePrice());
      bookingDetails.setCustomerName(vehicleRequest.getFirstName().substring(0, 1).toUpperCase() + " " + vehicleRequest.getLastName().substring(0, 1).toUpperCase());
      bookingDetails.setCountryCode(vehicleRequest.getCountryCode());
      bookingDetails.setCustomerMobile(vehicleRequest.getCustomerMobile());
      bookingDetails.setVehicleDetailsType(vehicleRequest.getVehicleDetailsType());
      bookingDetails.setStatus(vehicleRequest.getStatus());
      bookingDetails.setReBookingStatus(bookingDetails.getReBookingStatus());
      bookingDetails.setBookingPrice(vehicleRequest.getBookingPrice());
      bookingDetails.setMiscellaneous(vehicleRequest.getMiscellaneous());
      bookingDetails.setFromDate(vehicleRequest.getFromDate());
      bookingDetails.setToDate(vehicleRequest.getToDate());
      bookingDetails.setDeliveryTime(vehicleRequest.getDeliveryTime());
      bookingDetails.setPickupTime(vehicleRequest.getPickupTime());
      bookingDetails.setAreaFrom(vehicleRequest.getAreaFrom());
      bookingDetails.setAreaTo(vehicleRequest.getAreaTo());
      bookingDetails.setDeliveryCharges(vehicleRequest.getDeliveryCharges());
      bookingDetails.setReceivedAmount(vehicleRequest.getReceivedAmount());
      bookingDetails.setBalenceAmount(vehicleRequest.getBalenceAmount());
      bookingDetails.setTotalAmount(vehicleRequest.getTotalAmount());
      bookingDetails.setNotes(vehicleRequest.getNotes());
      bookingDetails.setReBookingStatus("OPEN");
      bookingDetails.setAdminId("9922957643");
      bookingDetails.setAdminName("James N");
      bookingDetails.setCreatedBy(vehicleRequest.getCreatedBy());
      bookingDetails.setCreatedbyName(vehicleRequest.getCreatedbyName());
      bookingDetails.setSecurityDeposit(vehicleRequest.getSecurityDeposit());
      bookingDetails.setUpdatedAt(new Date());
      return bookingDetails;
   }

   @Transactional
   public TotalBookingCount getTotalBookingCountByuserId(String userId) {
      Criteria cr = this.totalBookingCountDao.getSession().createCriteria(TotalBookingCount.class);
      cr.add(Restrictions.eq("userId", userId));
      TotalBookingCount totalBookingCount = (TotalBookingCount)cr.uniqueResult();
      return totalBookingCount;
   }

   public TotalBookingCount getTotalBookingCountByObj(VehicleRequestObject vehicleRequest) throws ParseException {
      TotalBookingCount totalBookingCount = new TotalBookingCount();
      totalBookingCount.setUserId(vehicleRequest.getCreatedBy());
      totalBookingCount.setBookingQtyDay(1);
      totalBookingCount.setBookingQtyWeek(1);
      totalBookingCount.setBookingQtyMonth(1);
      totalBookingCount.setBookingQtyYear(1);
      totalBookingCount.setAmountReceiveDay((double)vehicleRequest.getReceivedAmount());
      totalBookingCount.setAmountReceiveWeek((double)vehicleRequest.getReceivedAmount());
      totalBookingCount.setAmountReceiveMonth((double)vehicleRequest.getReceivedAmount());
      totalBookingCount.setAmountReceiveYear((double)vehicleRequest.getReceivedAmount());
      return totalBookingCount;
   }

   public TotalBookingCount getUpdatedTotalBookingCountByObj(VehicleRequestObject vehicleRequest, TotalBookingCount totalBookingCount) throws ParseException {
      totalBookingCount.setUserId(vehicleRequest.getCreatedBy());
      totalBookingCount.setBookingQtyDay(1);
      totalBookingCount.setBookingQtyWeek(1);
      totalBookingCount.setBookingQtyMonth(1);
      totalBookingCount.setBookingQtyYear(1);
      totalBookingCount.setAmountReceiveDay((double)vehicleRequest.getReceivedAmount());
      totalBookingCount.setAmountReceiveWeek((double)vehicleRequest.getReceivedAmount());
      totalBookingCount.setAmountReceiveMonth((double)vehicleRequest.getReceivedAmount());
      totalBookingCount.setAmountReceiveYear((double)vehicleRequest.getReceivedAmount());
      return totalBookingCount;
   }

   @Transactional
   public TotalBookingCount saveTotalBookingCount(TotalBookingCount totalBookingCount) {
      this.totalBookingCountDao.persist(totalBookingCount);
      return totalBookingCount;
   }

   public List<BookingDetails> getRejectedCancelList(VehicleRequestObject bookingRequest) {
      List<BookingDetails> results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD WHERE UD.status =:status ORDER BY UD.id DESC").setParameter("status", "CANCELREJECT").getResultList();
      return results;
   }

   public List<BookingDetails> getRefundedList(VehicleRequestObject bookingRequest) {
      List<BookingDetails> results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD WHERE UD.status =:status ORDER BY UD.cancelDate DESC").setParameter("status", "REFUND").getResultList();
      return results;
   }

   public List<BookingDetails> getBookingDetailsByCustomerMobileNo(VehicleRequestObject bookingRequest) {
      Date date = new Date();
      Date previousDay = Date.from(date.toInstant().minus(15L, ChronoUnit.DAYS));
      Date nextDay = Date.from(date.toInstant().plus(1L, ChronoUnit.DAYS));
      List results;
      if (!bookingRequest.getVehicleType().equalsIgnoreCase("CAR") && !bookingRequest.getVehicleType().equalsIgnoreCase("BIKE")) {
         System.out.println("Enter 2");
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD WHERE UD.customerMobile =:customerMobile AND UD.createdAt BETWEEN :currentDate AND :lastDate AND vehicleType IN(:vehicleType) AND status NOT IN(:BOOKED,:REQUESTED,:LOST,:CANCEL,:CANCELREJECT,:REFUND)").setParameter("customerMobile", bookingRequest.getCustomerMobile()).setParameter("currentDate", previousDay, TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").setParameter("LOST", "LOST").setParameter("REFUND", "REFUND").setParameter("CANCELREJECT", "CANCELREJECT").setParameter("CANCEL", "CANCEL").setParameter("vehicleType", bookingRequest.getVehicleType()).getResultList();
         return results;
      } else {
         System.out.println("Enter 1");
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD WHERE UD.customerMobile =:customerMobile AND UD.createdAt BETWEEN :currentDate AND :lastDate AND  vehicleType IN(:CAR, :BIKE) AND status NOT IN(:BOOKED,:REQUESTED,:LOST,:CANCEL,:CANCELREJECT,:REFUND)").setParameter("customerMobile", bookingRequest.getCustomerMobile()).setParameter("currentDate", previousDay, TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").setParameter("LOST", "LOST").setParameter("REFUND", "REFUND").setParameter("CANCELREJECT", "CANCELREJECT").setParameter("CANCEL", "CANCEL").setParameter("CAR", "CAR").setParameter("BIKE", "BIKE").getResultList();
         return results;
      }
   }

   public List<BookingDetails> getBookingDetailsByCustomerMobileNoForUpdate(VehicleRequestObject bookingRequest) {
      Date date = new Date();
      Date previousDay = Date.from(date.toInstant().minus(15L, ChronoUnit.DAYS));
      Date nextDay = Date.from(date.toInstant().plus(1L, ChronoUnit.DAYS));
      List results;
      if (!bookingRequest.getVehicleType().equalsIgnoreCase("CAR") && !bookingRequest.getVehicleType().equalsIgnoreCase("BIKE")) {
         System.out.println("Enter 2");
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD WHERE UD.customerMobile =:customerMobile AND UD.createdAt BETWEEN :currentDate AND :lastDate AND id NOT IN(:id) AND vehicleType IN(:vehicleType) AND status NOT IN(:BOOKED,:REQUESTED,:LOST,:CANCEL,:CANCELREJECT,:REFUND)").setParameter("customerMobile", bookingRequest.getCustomerMobile()).setParameter("currentDate", previousDay, TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("id", bookingRequest.getId()).setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").setParameter("LOST", "LOST").setParameter("REFUND", "REFUND").setParameter("CANCELREJECT", "CANCELREJECT").setParameter("CANCEL", "CANCEL").setParameter("vehicleType", bookingRequest.getVehicleType()).getResultList();
         return results;
      } else {
         System.out.println("Enter 1");
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD WHERE UD.customerMobile =:customerMobile AND UD.createdAt BETWEEN :currentDate AND :lastDate AND id NOT IN(:id) AND vehicleType IN(:CAR, :BIKE) AND status NOT IN(:BOOKED,:REQUESTED,:LOST,:CANCEL,:CANCELREJECT,:REFUND)").setParameter("customerMobile", bookingRequest.getCustomerMobile()).setParameter("currentDate", previousDay, TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("id", bookingRequest.getId()).setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").setParameter("LOST", "LOST").setParameter("REFUND", "REFUND").setParameter("CANCELREJECT", "CANCELREJECT").setParameter("CANCEL", "CANCEL").setParameter("CAR", "CAR").setParameter("BIKE", "BIKE").getResultList();
         return results;
      }
   }
   
   
  @SuppressWarnings("unchecked")
public List<BookingDetails> getBookingDetailsForCall(VehicleRequestObject bookingRequest) {
	      Date todayDate = new Date();
	      Date nextDay = Date.from(todayDate.toInstant().plus(1L, ChronoUnit.DAYS));
	      Date backMonth = Date.from(todayDate.toInstant().minus(30L, ChronoUnit.DAYS));
	      
	      
	      List<BookingDetails> results = this.bookingDetailsDao.getEntityManager()
	  		    .createQuery("SELECT UD FROM BookingDetails UD WHERE UD.status IN (:REQUESTED, :BOOKED) AND (UD.callStatus NOT IN (:callStatus) OR UD.callStatus IS NULL) " +
	  		                 "ORDER BY UD.fromDate ASC", BookingDetails.class)
	  		    .setParameter("REQUESTED", "REQUESTED")
	  		    .setParameter("BOOKED", "BOOKED")
	  		    .setParameter("callStatus", "CALL_DONE")
//	  		    .setFirstResult(0).setMaxResults(300)
	  		    .getResultList();
	  
	  		return results;
	    }


//	      List<BookingDetails> results = this.bookingDetailsDao.getEntityManager()
//	        		 .createQuery("SELECT UD FROM BookingDetails UD where UD.status IN (:REQUESTED,:BOOKED) AND UD.callStatus NOT IN(:callStatus) OR UD.callStatus IS NULL ORDER BY UD.fromDate ASC")
//	        		 .setParameter("REQUESTED", "REQUESTED")
//	        		 .setParameter("BOOKED", "BOOKED")
//	        		 .setParameter("callStatus", "CALL_DONE")
////	        		 .setParameter("empity", "")
////	        		 .setParameter("currentDate", new Date(), TemporalType.DATE)
////	        		 .setParameter("lastDate", nextDay, TemporalType.DATE)
////	        		 .setFirstResult(0).setMaxResults(300)
//	        		 .getResultList();
//	         return results;
//	      } 
	



   public List<BookingDetails> getBookingDetails(VehicleRequestObject bookingRequest) {
      Date todayDate = new Date();
      Date nextDay = Date.from(todayDate.toInstant().plus(1L, ChronoUnit.DAYS));
      Date backMonth = Date.from(todayDate.toInstant().minus(30L, ChronoUnit.DAYS));
      List results;
      if (bookingRequest.getRequestFor().equals("TODAY")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.status =:status AND UD.createdAt BETWEEN :currentDate AND :lastDate ORDER BY UD.fromDate ASC").setParameter("status", "REQUESTED").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setFirstResult(0).setMaxResults(300).getResultList();
         return results;
      } else if (bookingRequest.getRequestFor().equals("REQUESTED")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD WHERE UD.status =:status AND UD.createdAt BETWEEN :currentDate AND :lastDate ORDER BY UD.fromDate ASC").setParameter("status", "REQUESTED").setParameter("currentDate", backMonth, TemporalType.DATE).setParameter("lastDate", new Date(), TemporalType.DATE).setFirstResult(0).setMaxResults(300).getResultList();
         return results;
      } else if (bookingRequest.getRequestFor().equals("BOOKED")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD WHERE UD.status =:status AND UD.fromDate >=:fromDate ORDER BY UD.fromDate ASC").setParameter("fromDate", new Date(), TemporalType.DATE).setParameter("status", "BOOKED").getResultList();
         return results;
      } else if (bookingRequest.getRequestFor().equals("BYDATE")) {
         System.out.println("Enter hai yaha");
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD WHERE UD.status IN (:REQUESTED,:BOOKED) AND UD.createdAt BETWEEN :fromDate AND :toDate ORDER BY UD.id DESC").setParameter("fromDate", bookingRequest.getFromDate()).setParameter("toDate", bookingRequest.getToDate()).setParameter("REQUESTED", "REQUESTED").setParameter("BOOKED", "BOOKED").getResultList();
         return results;
      } else if (bookingRequest.getRequestFor().equals("ALL")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD ORDER BY UD.fromDate DESC").getResultList();
         return results;
      } else {
         return null;
      }
   }

   public List<BookingDetails> getBookingDetailsBySellerId(VehicleRequestObject bookingRequest) {
      Date todayDate = new Date();
      Date startDate = Date.from(todayDate.toInstant().minus(7L, ChronoUnit.DAYS));
      Date nextDate = Date.from(todayDate.toInstant().plus(1L, ChronoUnit.DAYS));
      List results;
      if (bookingRequest.getRequestFor().equals("ALL")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD WHERE UD.status IN (:REQUESTED,:BOOKED) ORDER BY UD.id DESC").setParameter("REQUESTED", "REQUESTED").setParameter("BOOKED", "BOOKED").getResultList();
         return results;
      } else if (bookingRequest.getRequestFor().equals("SORTED")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD WHERE UD.status IN (:REQUESTED,:BOOKED) AND UD.fromDate > :startDate  ORDER BY UD.fromDate DESC").setParameter("REQUESTED", "REQUESTED").setParameter("BOOKED", "BOOKED").setParameter("startDate", startDate).getResultList();
         return results;
      } else if (bookingRequest.getRequestFor().equals("INSTANT")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD WHERE UD.status IN (:REQUESTED) AND UD.fromDate =:todaydate ORDER BY UD.fromDate DESC").setParameter("REQUESTED", "REQUESTED").setParameter("todaydate", todayDate, TemporalType.DATE).getResultList();
         return results;
      } else if (bookingRequest.getRequestFor().equals("REQUESTED")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD WHERE UD.status =:status ORDER BY UD.id DESC").setParameter("status", "REQUESTED").getResultList();
         return results;
      } else if (bookingRequest.getRequestFor().equals("BOOKED")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD WHERE UD.status =:status ORDER BY UD.id DESC").setParameter("status", "BOOKED").getResultList();
         return results;
      } else if (bookingRequest.getRequestFor().equals("BYCUSTMOBILE")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD WHERE UD.customerMobile =:customerMobile ORDER BY UD.id DESC").setParameter("customerMobile", bookingRequest.getCustomerMobile()).getResultList();
         return results;
      } else if (bookingRequest.getRequestFor().equals("BYDATE")) {
         if (bookingRequest.getUserId() != null && !bookingRequest.getUserId().equalsIgnoreCase("All Report")) {
            results = this.bookingDetailsDao.getEntityManager()
            		.createQuery("SELECT UD FROM BookingDetails UD WHERE UD.createdBy =:createdBy AND UD.status IN (:REQUESTED,:BOOKED) AND UD.createdAt BETWEEN :fromDate AND :toDate ORDER BY UD.id DESC")
            		.setParameter("createdBy", bookingRequest.getUserId())
            		.setParameter("fromDate", bookingRequest.getFromDate())
            		.setParameter("toDate", bookingRequest.getToDate())
            		.setParameter("REQUESTED", "REQUESTED")
            		.setParameter("BOOKED", "BOOKED")
            		.getResultList();
            return results;
         } else {
            System.out.println("else part");
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD WHERE UD.createdAt BETWEEN :fromDate AND :toDate AND UD.status IN (:REQUESTED,:BOOKED) ORDER BY UD.id DESC").setParameter("fromDate", bookingRequest.getFromDate()).setParameter("toDate", bookingRequest.getToDate()).setParameter("REQUESTED", "REQUESTED").setParameter("BOOKED", "BOOKED").getResultList();
            return results;
         }
      } else {
         return null;
      }
   }

   public List<BookingDetails> getBookingDetailsByUserId(VehicleRequestObject vehicleRequest) {
      Calendar c = Calendar.getInstance();
      c.setTime(new Date());
      c.add(2, 1);
      Date currentDatePlusOne = c.getTime();
      Date todayDate = new Date();
      Date nextDay = Date.from(todayDate.toInstant().plus(1L, ChronoUnit.DAYS));
      List results;
      if (vehicleRequest.getRequestFor().equals("TODAY")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD WHERE UD.userId =:userId AND UD.status =:status AND UD.fromDate BETWEEN :currentDate AND :lastDate ORDER BY UD.deliveryTime ASC").setParameter("userId", vehicleRequest.getUserId()).setParameter("status", "BOOKED").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).getResultList();
         return results;
      } else if (vehicleRequest.getRequestFor().equals("MONTHLY")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD WHERE UD.userId =:userId AND status =:status AND UD.fromDate BETWEEN :currentDate AND :lastDate ORDER BY UD.id DESC").setParameter("userId", vehicleRequest.getUserId()).setParameter("status", "BOOKED").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", currentDatePlusOne, TemporalType.DATE).getResultList();
         return results;
      } else if (vehicleRequest.getRequestFor().equals("NEWORDER")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD WHERE UD.userId =:userId AND status =:status ORDER BY UD.id DESC").setParameter("userId", vehicleRequest.getUserId()).setParameter("status", "BOOKED").getResultList();
         return results;
      } else {
         return null;
      }
   }

   public List<BookingDetails> getTotalEarningByUserId(VehicleRequestObject vehicleRequest) {
      LocalDate today = LocalDate.now();
      LocalDate firstDate = today.withDayOfMonth(1);
      LocalDate lastDate = today.withDayOfMonth(today.lengthOfMonth());
      Date todayDate = new Date();
      ZoneId defaultZoneId = ZoneId.systemDefault();
      Date fromDate = Date.from(firstDate.atStartOfDay(defaultZoneId).toInstant());
      Date toDate = Date.from(lastDate.atStartOfDay(defaultZoneId).toInstant());
      Date nextDay = Date.from(todayDate.toInstant().plus(1L, ChronoUnit.DAYS));
      List results;
      if (vehicleRequest.getRequestFor().equals("TODAY")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(venderVehiclePrice) FROM BookingDetails WHERE userId =:userId AND status =:status AND createdAt BETWEEN :fromDate AND :toDate ORDER BY UD.id DESC").setParameter("userId", vehicleRequest.getUserId()).setParameter("status", "BOOKED").setParameter("fromDate", todayDate, TemporalType.DATE).setParameter("toDate", nextDay, TemporalType.DATE).getResultList();
         return results;
      } else if (vehicleRequest.getRequestFor().equals("MONTHLY")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(venderVehiclePrice) FROM BookingDetails WHERE userId =:userId AND status =:status AND createdAt BETWEEN :fromDate AND :toDate ORDER BY UD.id DESC").setParameter("userId", vehicleRequest.getUserId()).setParameter("status", "BOOKED").setParameter("fromDate", fromDate, TemporalType.DATE).setParameter("toDate", toDate, TemporalType.DATE).getResultList();
         return results;
      } else {
         return null;
      }
   }

   public List<VehicleDetails> getUserByVehicleName(VehicleRequestObject bookingRequest) {
      List<VehicleDetails> results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM VehicleDetails UD ORDER BY UD.id DESC").getResultList();
      return results;
   }

   public List<BookingDetails> getbookingVehicleByPickupDate(VehicleRequestObject bookingRequest) {
      Date todayDate = new Date();
      Date nextDay = Date.from(todayDate.toInstant().plus(1L, ChronoUnit.DAYS));
      Date tomorrow = Date.from(todayDate.toInstant().plus(2L, ChronoUnit.DAYS));
      List results;
      if (bookingRequest.getMemberType().equals("VENDOR")) {
         if (bookingRequest.getRequestFor().equalsIgnoreCase("TODAY")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.fromDate =:currentDate AND status IN(:BOOKED) AND vehicleOwnner =:vehicleOwnner ORDER BY UD.fromDate, UD.pickupTime").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("vehicleOwnner", bookingRequest.getVehicleOwnner()).getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("TOMORROW")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.fromDate =:currentDate AND status IN(:BOOKED)  ORDER BY UD.fromDate, UD.pickupTime").setParameter("currentDate", nextDay, TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("vehicleOwnner", bookingRequest.getVehicleOwnner()).getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("AFTERTOMORROW")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.fromDate =:currentDate AND status IN(:BOOKED) AND vehicleOwnner =:vehicleOwnner ORDER BY UD.fromDate, UD.pickupTime").setParameter("currentDate", tomorrow, TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("vehicleOwnner", bookingRequest.getVehicleOwnner()).getResultList();
            return results;
         }
      }

      if (bookingRequest.getMemberType().equals("DELIVERY_EXECUTIVE")) {
         if (bookingRequest.getRequestFor().equalsIgnoreCase("TODAY")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.fromDate =:currentDate AND status IN(:BOOKED) AND deliveryExecutiveId =:deliveryExecutiveId ORDER BY UD.fromDate, UD.pickupTime").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("deliveryExecutiveId", bookingRequest.getDeliveryExecutiveId()).getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("TOMORROW")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.fromDate =:currentDate AND status IN(:BOOKED) AND deliveryExecutiveId =:deliveryExecutiveId ORDER BY UD.fromDate, UD.pickupTime").setParameter("currentDate", nextDay, TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("deliveryExecutiveId", bookingRequest.getDeliveryExecutiveId()).getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("AFTERTOMORROW")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.fromDate =:currentDate AND status IN(:BOOKED) AND deliveryExecutiveId =:deliveryExecutiveId ORDER BY UD.fromDate, UD.pickupTime").setParameter("currentDate", tomorrow, TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("deliveryExecutiveId", bookingRequest.getDeliveryExecutiveId()).getResultList();
            return results;
         }
      } else if (bookingRequest.getMemberType().equals("ADMIN")) {
         if (bookingRequest.getRequestFor().equalsIgnoreCase("TODAY")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.fromDate =:currentDate AND status IN(:BOOKED,:REQUESTED) ORDER BY UD.fromDate, UD.pickupTime").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("TOMORROW")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.fromDate =:currentDate AND status IN(:BOOKED,:REQUESTED) ORDER BY UD.fromDate, UD.pickupTime").setParameter("currentDate", nextDay, TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("AFTERTOMORROW")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.fromDate =:currentDate AND status IN(:BOOKED, :REQUESTED) ORDER BY UD.fromDate, UD.pickupTime").setParameter("currentDate", tomorrow, TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("CUSTOM")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where fromDate BETWEEN :startDate AND :endDate AND status IN(:BOOKED,:REQUESTED) ORDER BY UD.fromDate, UD.pickupTime").setParameter("startDate", bookingRequest.getStartDate(), TemporalType.DATE).setParameter("endDate", bookingRequest.getEndDate(), TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").getResultList();
            return results;
         }
      }

      return null;
   }

   public List<BookingDetails> getbookingByPickupDateAndVehicleType(VehicleRequestObject bookingRequest) {
      Date todayDate = new Date();
      Date nextDay = Date.from(todayDate.toInstant().plus(1L, ChronoUnit.DAYS));
      Date tomorrow = Date.from(todayDate.toInstant().plus(2L, ChronoUnit.DAYS));
      List results;
      if (bookingRequest.getVehicleType().equalsIgnoreCase("WATERSPORTS")) {
         if (bookingRequest.getRequestFor().equalsIgnoreCase("TODAY")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.fromDate =:currentDate AND status IN(:REQUESTED) AND UD.vehicleType =:vehicleType ORDER BY UD.fromDate, UD.pickupTime").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("REQUESTED", "REQUESTED").setParameter("vehicleType", "WATERSPORTS").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("TOMORROW")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.fromDate =:currentDate AND status IN(:REQUESTED) AND UD.vehicleType =:vehicleType ORDER BY UD.fromDate, UD.pickupTime").setParameter("currentDate", nextDay, TemporalType.DATE).setParameter("REQUESTED", "REQUESTED").setParameter("vehicleType", "WATERSPORTS").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("AFTERTOMORROW")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.fromDate =:currentDate AND status IN(:REQUESTED) AND UD.vehicleType =:vehicleType ORDER BY UD.fromDate, UD.pickupTime").setParameter("currentDate", tomorrow, TemporalType.DATE).setParameter("REQUESTED", "REQUESTED").setParameter("vehicleType", "WATERSPORTS").getResultList();
            return results;
         }
      } else {
         if (bookingRequest.getRequestFor().equalsIgnoreCase("TODAY")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.fromDate =:currentDate AND status IN(:REQUESTED) AND vehicleType NOT IN(:vehicleType) ORDER BY UD.fromDate, UD.pickupTime").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("REQUESTED", "REQUESTED").setParameter("vehicleType", "WATERSPORTS").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("TOMORROW")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.fromDate =:currentDate AND status IN(:REQUESTED) AND vehicleType NOT IN(:vehicleType) ORDER BY UD.fromDate, UD.pickupTime").setParameter("currentDate", nextDay, TemporalType.DATE).setParameter("REQUESTED", "REQUESTED").setParameter("vehicleType", "WATERSPORTS").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("AFTERTOMORROW")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.fromDate =:currentDate AND status IN(:REQUESTED) AND vehicleType NOT IN(:vehicleType) ORDER BY UD.fromDate, UD.pickupTime").setParameter("currentDate", tomorrow, TemporalType.DATE).setParameter("REQUESTED", "REQUESTED").setParameter("vehicleType", "WATERSPORTS").getResultList();
            return results;
         }
      }

      return null;
   }

   public List<BookingDetails> getbookingVehicleByDropDate(VehicleRequestObject bookingRequest) {
      Date todayDate = new Date();
      Date nextDay = Date.from(todayDate.toInstant().plus(1L, ChronoUnit.DAYS));
      Date tomorrow = Date.from(todayDate.toInstant().plus(2L, ChronoUnit.DAYS));
      List results;
      if (bookingRequest.getMemberType().equals("VENDOR")) {
         if (bookingRequest.getRequestFor().equalsIgnoreCase("TODAY")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.toDate =:toDate AND status IN(:BOOKED) AND vehicleOwnner =:vehicleOwnner ORDER BY UD.toDate, UD.deliveryTime").setParameter("toDate", new Date(), TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("vehicleOwnner", bookingRequest.getVehicleOwnner()).getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("TOMORROW")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.toDate =:toDate AND status IN(:BOOKED) AND vehicleOwnner =:vehicleOwnner ORDER BY UD.toDate, UD.deliveryTime").setParameter("toDate", nextDay, TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("vehicleOwnner", bookingRequest.getVehicleOwnner()).getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("AFTERTOMORROW")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.toDate =:toDate AND status IN(:BOOKED) AND vehicleOwnner =:vehicleOwnner ORDER BY UD.toDate, UD.deliveryTime").setParameter("toDate", tomorrow, TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("vehicleOwnner", bookingRequest.getVehicleOwnner()).getResultList();
            return results;
         }
      }

      if (bookingRequest.getMemberType().equals("DELIVERY_EXECUTIVE")) {
         if (bookingRequest.getRequestFor().equalsIgnoreCase("TODAY")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.toDate =:toDate AND status IN(:BOOKED) AND deliveryExecutiveId =:deliveryExecutiveId ORDER BY UD.toDate, UD.deliveryTime").setParameter("toDate", new Date(), TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("deliveryExecutiveId", bookingRequest.getDeliveryExecutiveId()).getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("TOMORROW")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.toDate =:toDate AND status IN(:BOOKED) AND deliveryExecutiveId =:deliveryExecutiveId ORDER BY UD.toDate, UD.deliveryTime").setParameter("toDate", nextDay, TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("deliveryExecutiveId", bookingRequest.getDeliveryExecutiveId()).getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("AFTERTOMORROW")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.toDate =:toDate AND status IN(:BOOKED) AND deliveryExecutiveId =:deliveryExecutiveId ORDER BY UD.toDate, UD.deliveryTime").setParameter("toDate", tomorrow, TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("deliveryExecutiveId", bookingRequest.getDeliveryExecutiveId()).getResultList();
            return results;
         }
      } else if (bookingRequest.getMemberType().equalsIgnoreCase("ADMIN")) {
         if (bookingRequest.getRequestFor().equalsIgnoreCase("TODAY")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.toDate =:toDate AND status IN(:BOOKED,:REQUESTED) ORDER BY UD.toDate, UD.deliveryTime").setParameter("toDate", new Date(), TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("TOMORROW")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.toDate =:toDate AND status IN(:BOOKED,:REQUESTED) ORDER BY UD.toDate, UD.deliveryTime").setParameter("toDate", nextDay, TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("AFTERTOMORROW")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.toDate =:toDate AND status IN(:BOOKED,:REQUESTED) ORDER BY UD.toDate, UD.deliveryTime").setParameter("toDate", tomorrow, TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("ALL")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.toDate >=:toDate ORDER BY UD.toDate, UD.deliveryTime").setParameter("toDate", new Date(), TemporalType.DATE).setParameter("REQUESTED", "REQUESTED").setParameter("CANCEL", "CANCEL").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("CUSTOM")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where toDate BETWEEN :startDate AND :endDate AND status IN(:BOOKED,:REQUESTED,:CANCEL) ORDER BY UD.toDate, UD.deliveryTime").setParameter("startDate", bookingRequest.getStartDate(), TemporalType.DATE).setParameter("endDate", bookingRequest.getEndDate(), TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").setParameter("CANCEL", "CANCEL").getResultList();
            return results;
         }
      }

      return null;
   }
   
   
	@SuppressWarnings("unchecked")
	public List<BookingDetails> getbookingVehicleByVehicleDetailsType(VehicleRequestObject bookingRequest) {

		Date date = new Date();
		Date todate = Date.from(date.toInstant().plus(3L, ChronoUnit.DAYS));
		
		List<BookingDetails> results = new ArrayList<BookingDetails>();
		if(bookingRequest.getRequestFor().equalsIgnoreCase("BIKE")) {
			results = this.bookingDetailsDao.getEntityManager().createQuery(
					"SELECT UD FROM BookingDetails UD WHERE UD.status =:status AND UD.vehicleType =:vehicleType AND UD.fromDate >= :todate ORDER BY UD.fromDate, UD.pickupTime")
					.setParameter("status", "REQUESTED")
					.setParameter("vehicleType", "BIKE")
					 .setParameter("todate",todate, TemporalType.DATE)
					.getResultList();
			return results;
		} else if(bookingRequest.getRequestFor().equalsIgnoreCase("HATCHNSEDAN")) {
			results = this.bookingDetailsDao.getEntityManager().createQuery(
				    "SELECT UD FROM BookingDetails UD WHERE UD.status = :status AND UD.vehicleType = :vehicleType AND UD.vehicleDetailsType IN :vehicleDetailsTypes AND UD.fromDate >= :todate ORDER BY UD.fromDate, UD.pickupTime")
				    .setParameter("status", "REQUESTED")
				    .setParameter("vehicleType", "CAR")
				    .setParameter("todate", todate, TemporalType.DATE)
//				    .setParameter("vehicleDetailsTypes", Arrays.asList("Manual-SD", "Auto-SD"))
				    .setParameter("vehicleDetailsTypes", Arrays.asList("Manual-SD","Auto-PRMHB","Auto-SD"))
				    .getResultList();
				return results;
		}
		 else if(bookingRequest.getRequestFor().equalsIgnoreCase("MINISUV")) {
			 results = this.bookingDetailsDao.getEntityManager().createQuery(
					    "SELECT UD FROM BookingDetails UD WHERE UD.status = :status AND UD.vehicleType = :vehicleType AND UD.vehicleDetailsType IN :vehicleDetailsTypes AND UD.fromDate >= :todate ORDER BY UD.fromDate, UD.pickupTime")
					    .setParameter("status", "REQUESTED")
					    .setParameter("vehicleType", "CAR")
					    .setParameter("todate", todate, TemporalType.DATE)
					    .setParameter("vehicleDetailsTypes", Arrays.asList("Manual-CSUV", "Manual-MUV", "Manual-SUV", "Auto-CSUV", "Auto-SUV"))
					    .getResultList();
				return results;
			}
		return results;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<BookingDetails> getbookedVehicle(VehicleRequestObject bookingRequest) {

		Date todate = new Date();
		
		List<BookingDetails> results = new ArrayList<BookingDetails>();

		results = this.bookingDetailsDao.getEntityManager().createQuery(
				"SELECT UD FROM BookingDetails UD WHERE UD.status =:status AND UD.fromDate >= :todate ORDER BY UD.fromDate, UD.pickupTime")
				.setParameter("status", "BOOKED")
//				.setParameter("vehicleType", "BIKE")
				 .setParameter("todate",todate, TemporalType.DATE)
				.getResultList();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<BookingDetails> getBookingAmountOfTodaysForWATERSPORTSCHATSALE(VehicleRequestObject bookingRequest) {
		      Date todayDate = new Date();
		      Date nextDay = Date.from(todayDate.toInstant().plus(1L, ChronoUnit.DAYS));
		      Calendar c = Calendar.getInstance();
		      c.set(7, 1);
		      Date monday = c.getTime();
		      Date sunday = Date.from(c.toInstant().plus(8L, ChronoUnit.DAYS));
		      
		      List<BookingDetails> results = new ArrayList<BookingDetails>();
		         results = this.bookingDetailsDao.getEntityManager()
		        		 .createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status IN(:BOOKED,:REQUESTED)  AND UD.memberType IN(:CARCHATSALE,:WATERSPORTSCHATSALE")
		        		 .setParameter("currentDate", new Date(), TemporalType.DATE)
		        		 .setParameter("lastDate", nextDay, TemporalType.DATE)
		        		 .setParameter("BOOKED", "BOOKED")
		        		 .setParameter("REQUESTED", "REQUESTED")
		        		 .setParameter("CARCHATSALE", "CARCHATSALE")
		        		 .setParameter("WATERSPORTSCHATSALE", "WATERSPORTSCHATSALE")
		        		 .getResultList();
		         return results;
		      }
		   
	@SuppressWarnings("unchecked")
	public List<BookingDetails> getBookingAmountOfWeekForWATERSPORTSCHATSALE(VehicleRequestObject bookingRequest) {
	      Date todayDate = new Date();
	      Date nextDay = Date.from(todayDate.toInstant().plus(1L, ChronoUnit.DAYS));
	      Calendar c = Calendar.getInstance();
	      c.set(7, 1);
	      Date monday = c.getTime();
	      Date sunday = Date.from(c.toInstant().plus(8L, ChronoUnit.DAYS));
	      
	      List<BookingDetails> results = new ArrayList<BookingDetails>();
	         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status IN(:BOOKED,:REQUESTED) AND UD.memberType IN(:CARCHATSALE,:WATERSPORTSCHATSALE)")
	        		 .setParameter("currentDate", monday, TemporalType.DATE)
	        		 .setParameter("lastDate", sunday, TemporalType.DATE)
	        		 .setParameter("BOOKED", "BOOKED")
	        		 .setParameter("REQUESTED", "REQUESTED")
	        		 .setParameter("CARCHATSALE", "CARCHATSALE")
	        		 .setParameter("WATERSPORTSCHATSALE", "WATERSPORTSCHATSALE")
	        		 .getResultList();
	         return results;
	      }
	
	 @SuppressWarnings("unchecked")
	public List<BookingDetails> getBookingAmountOfMonthForWATERSPORTSCHATSALE(VehicleRequestObject bookingRequest) {
	      System.out.println("Role : " + bookingRequest.getMemberType());
	      Calendar calendar = Calendar.getInstance();
	      Date today = new Date();
	      calendar.setTime(today);
	      calendar.set(5, 1);
	      Date firstDayOfMonth = calendar.getTime();
	      calendar.set(5, calendar.getActualMaximum(5));
	      Date lastDayOfMonth = calendar.getTime();
	      List<BookingDetails> results = new ArrayList<BookingDetails>();
	         results = this.bookingDetailsDao.getEntityManager()
	        		 .createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status IN(:BOOKED,:REQUESTED) AND UD.memberType IN(:CARCHATSALE,:WATERSPORTSCHATSALE)")
	        		 .setParameter("lastDate", lastDayOfMonth, TemporalType.DATE)
	        		 .setParameter("BOOKED", "BOOKED")
	        		 .setParameter("REQUESTED", "REQUESTED")
	        		 .setParameter("CARCHATSALE", "CARCHATSALE")
	        		 .setParameter("WATERSPORTSCHATSALE", "WATERSPORTSCHATSALE")
	        		 .getResultList();
	         return results;
	      }
	   

	   
	
	

   public List<BookingDetails> getBookingAmountOfTodays(VehicleRequestObject bookingRequest) {
      Date todayDate = new Date();
      Date nextDay = Date.from(todayDate.toInstant().plus(1L, ChronoUnit.DAYS));
      Calendar c = Calendar.getInstance();
      c.set(7, 1);
      Date monday = c.getTime();
      Date sunday = Date.from(c.toInstant().plus(8L, ChronoUnit.DAYS));
      List results;
      if (bookingRequest.getMemberType().equals("SALE")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND createdBy =:createdBy AND status IN(:BOOKED,:REQUESTED,:CANCELREQUEST,:CANCELREJECT))")
        		 .setParameter("currentDate", new Date(), TemporalType.DATE)
        		 .setParameter("lastDate", nextDay, TemporalType.DATE)
        		 .setParameter("createdBy", bookingRequest.getCreatedBy())
        		 .setParameter("BOOKED", "BOOKED")
        		 .setParameter("REQUESTED", "REQUESTED")
         		 .setParameter("CANCELREQUEST", "CANCELREQUEST")        		 
         		 .setParameter("CANCELREJECT", "CANCELREJECT")  
        		 .getResultList();
         return results;
      } else if (bookingRequest.getMemberType().equals("CARCHATSALE") || bookingRequest.getMemberType().equals("WATERSPORTSCHATSALE")) {
          results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND createdBy =:createdBy AND status IN(:BOOKED,:REQUESTED, :CANCELREQUEST,:CANCELREJECT))")
        		  .setParameter("currentDate", new Date(), TemporalType.DATE)
        		  .setParameter("lastDate", nextDay, TemporalType.DATE)
        		  .setParameter("createdBy", bookingRequest.getCreatedBy())
        		  .setParameter("BOOKED", "BOOKED")
        		  .setParameter("REQUESTED", "REQUESTED")
         		 .setParameter("CANCELREQUEST", "CANCELREQUEST")        		 
         		 .setParameter("CANCELREJECT", "CANCELREJECT")    
         		 .getResultList();
          return results;
       }else {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status IN(:BOOKED,:REQUESTED, :CANCELREQUEST,:CANCELREJECT)  AND UD.createdBy NOT IN(:rohan,:rashmi,:akshi)")
        		 .setParameter("currentDate", new Date(), TemporalType.DATE)
        		 .setParameter("lastDate", nextDay, TemporalType.DATE)
        		 .setParameter("BOOKED", "BOOKED")
        		 .setParameter("REQUESTED", "REQUESTED")        		 
        		 .setParameter("CANCELREQUEST", "CANCELREQUEST")        		 
        		 .setParameter("CANCELREJECT", "CANCELREJECT")        		 
        		 .setParameter("rohan", "7845273233")
        		 .setParameter("rashmi", "9619283833")
        		 .setParameter("akshi", "7694848781")
        		 .getResultList();
         return results;
      }
   }

   public List<BookingDetails> getBookingAmountOfWeek(VehicleRequestObject bookingRequest) {
      Date todayDate = new Date();
      Date nextDay = Date.from(todayDate.toInstant().plus(1L, ChronoUnit.DAYS));
      Calendar c = Calendar.getInstance();
      c.set(7, 1);
      Date monday = c.getTime();
      Date sunday = Date.from(c.toInstant().plus(8L, ChronoUnit.DAYS));
      System.out.println("Monday : " + monday);
      System.out.println("Sunday : " + sunday);
      List results;
      if (bookingRequest.getMemberType().equals("SALE")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND createdBy =:createdBy AND status IN(:BOOKED,:REQUESTED, :CANCELREQUEST,:CANCELREJECT))")
        		 .setParameter("currentDate", monday, TemporalType.DATE)
        		 .setParameter("lastDate", sunday, TemporalType.DATE)
        		 .setParameter("createdBy", bookingRequest.getCreatedBy())
        		 .setParameter("BOOKED", "BOOKED")
        		 .setParameter("REQUESTED", "REQUESTED")
         		 .setParameter("CANCELREQUEST", "CANCELREQUEST")        		 
         		 .setParameter("CANCELREJECT", "CANCELREJECT") 
         		 .getResultList();
         return results;
      } else if (bookingRequest.getMemberType().equals("CARCHATSALE") || bookingRequest.getMemberType().equals("WATERSPORTSCHATSALE")) {
          results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND createdBy =:createdBy AND status IN(:BOOKED,:REQUESTED, :CANCELREQUEST,:CANCELREJECT))")
        		  .setParameter("currentDate", monday, TemporalType.DATE)
        		  .setParameter("lastDate", sunday, TemporalType.DATE)
        		  .setParameter("createdBy", bookingRequest.getCreatedBy())
        		  .setParameter("BOOKED", "BOOKED")
        		  .setParameter("REQUESTED", "REQUESTED")
          		 .setParameter("CANCELREQUEST", "CANCELREQUEST")        		 
          		 .setParameter("CANCELREJECT", "CANCELREJECT") 
          		 .getResultList();
          return results;
       } else {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status IN(:BOOKED,:REQUESTED, :CANCELREQUEST,:CANCELREJECT)  AND UD.createdBy NOT IN(:rohan,:rashmi,:akshi)")
        		 .setParameter("currentDate", monday, TemporalType.DATE)
        		 .setParameter("lastDate", sunday, TemporalType.DATE)
        		 .setParameter("BOOKED", "BOOKED")
        		 .setParameter("REQUESTED", "REQUESTED")
         		 .setParameter("CANCELREQUEST", "CANCELREQUEST").setParameter("CANCELREJECT", "CANCELREJECT") 
        		 .setParameter("rohan", "7845273233")
        		 .setParameter("rashmi", "9619283833")
        		 .setParameter("akshi", "7694848781")
        		 .getResultList();
         return results;
      }
   }

   public List<BookingDetails> getBookingAmountOfMonth(VehicleRequestObject bookingRequest) {
      System.out.println("Role : " + bookingRequest.getMemberType());
      Calendar calendar = Calendar.getInstance();
      Date today = new Date();
      calendar.setTime(today);
      calendar.set(5, 1);
      Date firstDayOfMonth = calendar.getTime();
      calendar.set(5, calendar.getActualMaximum(5));
      Date lastDayOfMonth = calendar.getTime();
      System.out.println(firstDayOfMonth);
      System.out.println(lastDayOfMonth);
      List results;
      if (bookingRequest.getMemberType().equals("SALE")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate  AND createdBy =:createdBy AND status IN(:BOOKED,:REQUESTED, :CANCELREQUEST,:CANCELREJECT))").setParameter("currentDate", firstDayOfMonth, TemporalType.DATE).setParameter("lastDate", lastDayOfMonth, TemporalType.DATE).setParameter("createdBy", bookingRequest.getCreatedBy()).setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").setParameter("CANCELREQUEST", "CANCELREQUEST").setParameter("CANCELREJECT", "CANCELREJECT") .getResultList();
         return results;
      } else if (bookingRequest.getMemberType().equals("CARCHATSALE") || bookingRequest.getMemberType().equals("WATERSPORTSCHATSALE")) {
          results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate  AND createdBy =:createdBy AND status IN(:BOOKED,:REQUESTED, :CANCELREQUEST,:CANCELREJECT))").setParameter("currentDate", firstDayOfMonth, TemporalType.DATE).setParameter("lastDate", lastDayOfMonth, TemporalType.DATE).setParameter("createdBy", bookingRequest.getCreatedBy()).setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED") .setParameter("CANCELREQUEST", "CANCELREQUEST").setParameter("CANCELREJECT", "CANCELREJECT").getResultList();
          return results;
       }else {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status IN(:BOOKED,:REQUESTED,:CANCELREQUEST,:CANCELREJECT) AND UD.createdBy NOT IN(:rohan,:rashmi,:akshi)").setParameter("currentDate", firstDayOfMonth, TemporalType.DATE).setParameter("lastDate", lastDayOfMonth, TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").setParameter("CANCELREQUEST", "CANCELREQUEST").setParameter("CANCELREJECT", "CANCELREJECT").setParameter("rohan", "7845273233").setParameter("rashmi", "9619283833").setParameter("akshi", "7694848781").getResultList();
         return results;
      }
   }

   public List<BookingDetails> getBookingCount(VehicleRequestObject bookingRequest, String requestFor) {
      Date todayDate = new Date();
      Date nextDay = Date.from(todayDate.toInstant().plus(1L, ChronoUnit.DAYS));
      Calendar c = Calendar.getInstance();
      c.set(7, 1);
      Date monday = c.getTime();
      Date sunday = Date.from(c.toInstant().plus(8L, ChronoUnit.DAYS));
      List results;
      if (bookingRequest.getMemberType().equals("SALE")) {
         if (requestFor.equalsIgnoreCase("DAY_BOOKED_COUNT")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND createdBy =:createdBy AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL)").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("createdBy", bookingRequest.getCreatedBy()).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            return results;
         }

         if (requestFor.equalsIgnoreCase("DAY_BOOKED_AMOUNT")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND createdBy =:createdBy AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL)").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("createdBy", bookingRequest.getCreatedBy()).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            return results;
         }

         if (requestFor.equalsIgnoreCase("WEEK_BOOKED_COUNT")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT COUNT(*) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND createdBy =:createdBy AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL)").setParameter("currentDate", monday, TemporalType.DATE).setParameter("lastDate", sunday, TemporalType.DATE).setParameter("createdBy", bookingRequest.getCreatedBy()).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            return results;
         }

         if (requestFor.equalsIgnoreCase("WEEK_BOOKED_AMOUNT")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND createdBy =:createdBy AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL)").setParameter("currentDate", monday, TemporalType.DATE).setParameter("lastDate", sunday, TemporalType.DATE).setParameter("createdBy", bookingRequest.getCreatedBy()).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            return results;
         }

         if (requestFor.equalsIgnoreCase("ENQUIRY_COUNT")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT COUNT(*) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND createdBy =:createdBy AND status NOT IN(:CANCEL,:BOOKED,:REQUESTED,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL)").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("createdBy", bookingRequest.getCreatedBy()).setParameter("CANCEL", "CANCEL").setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            return results;
         }

         if (requestFor.equalsIgnoreCase("ALL_BOOKED_COUNT")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) AND UD.createdBy NOT IN(:rohan,:rashmi,:akshi)").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("rohan", "7845273233").setParameter("rashmi", "9619283833").setParameter("akshi", "7694848781").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            return results;
         }
      } else if (!bookingRequest.getMemberType().equals("SUPERADMIN") && !bookingRequest.getMemberType().equals("ADMIN")) {
         if (bookingRequest.getMemberType().equals("SUPER2ADMIN")) {
            if (requestFor.equalsIgnoreCase("DAY_BOOKED_COUNT")) {
               results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT COUNT(*) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL)").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
               return results;
            }

            if (requestFor.equalsIgnoreCase("DAY_BOOKED_AMOUNT")) {
               results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL)").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
               return results;
            }

            if (requestFor.equalsIgnoreCase("WEEK_BOOKED_COUNT")) {
               results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT COUNT(*) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL)").setParameter("currentDate", monday, TemporalType.DATE).setParameter("lastDate", sunday, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
               return results;
            }

            if (requestFor.equalsIgnoreCase("WEEK_BOOKED_AMOUNT")) {
               results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL)").setParameter("currentDate", monday, TemporalType.DATE).setParameter("lastDate", sunday, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
               return results;
            }

            if (requestFor.equalsIgnoreCase("ENQUIRY_COUNT")) {
               results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT COUNT(*) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:BOOKED,:REQUESTED,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL)").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
               return results;
            }

            if (requestFor.equalsIgnoreCase("ALL_BOOKED_COUNT")) {
               results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) AND UD.createdBy NOT IN(:rohan,:rashmi,:akshi)").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("rohan", "7845273233").setParameter("rashmi", "9619283833").setParameter("akshi", "7694848781").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
               return results;
            }
         }
      } else {
         if (requestFor.equalsIgnoreCase("DAY_BOOKED_COUNT")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT COUNT(*) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) AND UD.createdBy NOT IN(:rohan,:rashmi,:akshi)").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("rohan", "7845273233").setParameter("rashmi", "9619283833").setParameter("akshi", "7694848781").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            return results;
         }

         if (requestFor.equalsIgnoreCase("DAY_BOOKED_AMOUNT")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) AND UD.createdBy NOT IN(:rohan,:rashmi,:akshi)").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("rohan", "7845273233").setParameter("rashmi", "9619283833").setParameter("akshi", "7694848781").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            return results;
         }

         if (requestFor.equalsIgnoreCase("WEEK_BOOKED_COUNT")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT COUNT(*) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) AND UD.createdBy NOT IN(:rohan,:rashmi,:akshi)").setParameter("currentDate", monday, TemporalType.DATE).setParameter("lastDate", sunday, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("rohan", "7845273233").setParameter("rashmi", "9619283833").setParameter("akshi", "7694848781").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            return results;
         }

         if (requestFor.equalsIgnoreCase("WEEK_BOOKED_AMOUNT")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) AND UD.createdBy NOT IN(:rohan,:rashmi, :akshi)").setParameter("currentDate", monday, TemporalType.DATE).setParameter("lastDate", sunday, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("rohan", "7845273233").setParameter("rashmi", "9619283833").setParameter("akshi", "7694848781").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            return results;
         }

         if (requestFor.equalsIgnoreCase("ENQUIRY_COUNT")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT COUNT(*) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:BOOKED,:REQUESTED,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) AND UD.createdBy NOT IN(:rohan,:rashmi,:akshi)").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("rohan", "7845273233").setParameter("rashmi", "9619283833").setParameter("akshi", "7694848781").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            return results;
         }

         if (requestFor.equalsIgnoreCase("ALL_BOOKED_COUNT")) {
            results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) AND UD.createdBy NOT IN(:rohan,:rashmi,:akshi)").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("rohan", "7845273233").setParameter("rashmi", "9619283833").setParameter("akshi", "7694848781").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            return results;
         }
      }

      return null;
   }

   public List<BookingDetails> penddingBookingDetails(VehicleRequestObject vehicleRequest, String requestFor) {
      Date todayDate = new Date();
      Date nextDay = Date.from(todayDate.toInstant().plus(1L, ChronoUnit.DAYS));
      Date afterNextDay = Date.from(nextDay.toInstant().plus(1L, ChronoUnit.DAYS));
      Date afterAfterNextDay = Date.from(nextDay.toInstant().plus(1L, ChronoUnit.DAYS));
      Date weekly = Date.from(todayDate.toInstant().plus(6L, ChronoUnit.DAYS));
      Date halfMonth = Date.from(todayDate.toInstant().plus(13L, ChronoUnit.DAYS));
      Date monthly = Date.from(todayDate.toInstant().plus(29L, ChronoUnit.DAYS));
      List results;
      if (requestFor.equalsIgnoreCase("TODAY_PENDING_COUNT")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT COUNT(*) FROM BookingDetails UD where UD.fromDate BETWEEN :currentDate AND :lastDate AND status IN(:REQUESTED)").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", new Date(), TemporalType.DATE).setParameter("REQUESTED", "REQUESTED").getResultList();
         return results;
      } else if (requestFor.equalsIgnoreCase("TOMORROW_PENDING_COUNT")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT COUNT(*) FROM BookingDetails UD where UD.fromDate BETWEEN :currentDate AND :lastDate AND status IN(:REQUESTED)").setParameter("currentDate", nextDay, TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("REQUESTED", "REQUESTED").getResultList();
         return results;
      } else if (requestFor.equalsIgnoreCase("AFTER_TOMORROW_PENDING_COUNT")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT COUNT(*) FROM BookingDetails UD where UD.fromDate BETWEEN :currentDate AND :lastDate AND status IN(:REQUESTED)").setParameter("currentDate", afterNextDay, TemporalType.DATE).setParameter("lastDate", afterNextDay, TemporalType.DATE).setParameter("REQUESTED", "REQUESTED").getResultList();
         return results;
      } else if (requestFor.equalsIgnoreCase("WEEKLY_PENDING_COUNT")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT COUNT(*) FROM BookingDetails UD where UD.fromDate BETWEEN :currentDate AND :lastDate AND status IN(:REQUESTED)").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", weekly, TemporalType.DATE).setParameter("REQUESTED", "REQUESTED").getResultList();
         return results;
      } else if (requestFor.equalsIgnoreCase("HALF_MONTH_PENDING_COUNT")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT COUNT(*) FROM BookingDetails UD where UD.fromDate BETWEEN :currentDate AND :lastDate AND status IN(:REQUESTED)").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", halfMonth, TemporalType.DATE).setParameter("REQUESTED", "REQUESTED").getResultList();
         return results;
      } else if (requestFor.equalsIgnoreCase("MONTH_PENDING_COUNT")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT COUNT(*) FROM BookingDetails UD where UD.fromDate BETWEEN :currentDate AND :lastDate AND status IN(:REQUESTED)").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", monthly, TemporalType.DATE).setParameter("REQUESTED", "REQUESTED").getResultList();
         return results;
      } else {
         return null;
      }
   }

   public List<BookingDetails> cancelRequestList(VehicleRequestObject bookingRequest) {
      List<BookingDetails> results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.status =:status ORDER BY UD.cancelDate DESC").setParameter("status", "CANCELREQUEST").getResultList();
      return results;
   }

   public List<BookingDetails> cancelBookingDetails(VehicleRequestObject bookingRequest) {
      List<BookingDetails> results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.status =:status  ORDER BY UD.createdAt DESC").setParameter("status", "CANCEL").getResultList();
      return results;
   }

   public List<BookingDetails> completedBookingDetails(VehicleRequestObject bookingRequest) {
      List<BookingDetails> results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.toDate <:toDate  ORDER BY UD.createdAt DESC").setParameter("toDate", new Date(), TemporalType.DATE).getResultList();
      return results;
   }

   public List<BookingDetails> inprocessBookingDetails(VehicleRequestObject bookingRequest) {
      List<BookingDetails> results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.fromDate <:fromDate AND UD.toDate >:toDate  ORDER BY UD.createdAt DESC").setParameter("toDate", new Date(), TemporalType.DATE).setParameter("fromDate", new Date(), TemporalType.DATE).getResultList();
      return results;
   }

   public List<BookingDetails> bookingDetailsGroupByUserName(VehicleRequestObject bookingRequest) {
      Date todayDate = new Date();
      Date nextDay = Date.from(todayDate.toInstant().plus(1L, ChronoUnit.DAYS));
      Calendar c = Calendar.getInstance();
      c.set(7, 1);
      Date monday = c.getTime();
      Date sunday = Date.from(c.toInstant().plus(8L, ChronoUnit.DAYS));
      c.set(5, 1);
      Date monthFirstDay = c.getTime();
      c.set(5, c.getActualMaximum(5));
      Date monthLastDay = c.getTime();
      ArrayList results;
      if (bookingRequest.getMemberType().equalsIgnoreCase("SUPER2ADMIN")) {
         if (bookingRequest.getRequestFor().equalsIgnoreCase("TODAYS")) {
            new ArrayList();
            results = (ArrayList)this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD.createdbyName, COUNT(*), SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) GROUP BY UD.createdbyName").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("WEEK")) {
            new ArrayList();
            results = (ArrayList)this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD.createdbyName, COUNT(*), SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) GROUP BY UD.createdbyName").setParameter("currentDate", monday, TemporalType.DATE).setParameter("lastDate", sunday, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("MONTH")) {
            new ArrayList();
            results = (ArrayList)this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD.createdbyName, COUNT(*), SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) GROUP BY UD.createdbyName ").setParameter("currentDate", monthFirstDay, TemporalType.DATE).setParameter("lastDate", monthLastDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("ENQUIRY")) {
            new ArrayList();
            results = (ArrayList)this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD.createdbyName, COUNT(*), SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:BOOKED,:REQUESTED,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) GROUP BY UD.createdbyName ").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("FOLLOWUP")) {
            new ArrayList();
            results = (ArrayList)this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD.createdbyName, COUNT(*), SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:BOOKED,:REQUESTED,:ENQUIRY,:LOST,:REJECTED,:REFUND,:MISSEDCALL) AND UD.createdBy NOT IN(:rohan,:rashmi,:akshi) GROUP BY UD.createdbyName ").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").setParameter("ENQUIRY", "ENQUIRY").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("rohan", "7845273233").setParameter("rashmi", "9619283833").setParameter("akshi", "7694848781").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("LOST")) {
            new ArrayList();
            results = (ArrayList)this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD.createdbyName, COUNT(*), SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:BOOKED,:REQUESTED,:ENQUIRY,:FOLLOWUP,:REJECTED,:REFUND,:MISSEDCALL) GROUP BY UD.createdbyName ").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            return results;
         }
      } else {
         if (bookingRequest.getRequestFor().equalsIgnoreCase("TODAYS")) {
            new ArrayList();
            results = (ArrayList)this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD.createdbyName, COUNT(*), SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) AND UD.createdBy NOT IN(:rohan,:rashmi,:akshi) GROUP BY UD.createdbyName").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").setParameter("rohan", "7845273233").setParameter("rashmi", "9619283833").setParameter("akshi", "7694848781").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("WEEK")) {
            new ArrayList();
            results = (ArrayList)this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD.createdbyName, COUNT(*), SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) AND UD.createdBy NOT IN(:rohan,:rashmi,:akshi) GROUP BY UD.createdbyName").setParameter("currentDate", monday, TemporalType.DATE).setParameter("lastDate", sunday, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").setParameter("rohan", "7845273233").setParameter("rashmi", "9619283833").setParameter("akshi", "7694848781").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("MONTH")) {
            new ArrayList();
            results = (ArrayList)this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD.createdbyName, COUNT(*), SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) AND UD.createdBy NOT IN(:rohan,:rashmi,:akshi) GROUP BY UD.createdbyName ").setParameter("currentDate", monthFirstDay, TemporalType.DATE).setParameter("lastDate", monthLastDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").setParameter("rohan", "7845273233").setParameter("rashmi", "9619283833").setParameter("akshi", "7694848781").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("ENQUIRY")) {
            new ArrayList();
            results = (ArrayList)this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD.createdbyName, COUNT(*), SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:BOOKED,:REQUESTED,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) AND UD.createdBy NOT IN(:rohan,:rashmi,:akshi) GROUP BY UD.createdbyName ").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").setParameter("rohan", "7845273233").setParameter("rashmi", "9619283833").setParameter("akshi", "7694848781").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("FOLLOWUP")) {
            new ArrayList();
            results = (ArrayList)this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD.createdbyName, COUNT(*), SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:BOOKED,:REQUESTED,:ENQUIRY,:LOST,:REJECTED,:REFUND,:MISSEDCALL) AND UD.createdBy NOT IN(:rohan,:rashmi,:akshi) GROUP BY UD.createdbyName ").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").setParameter("ENQUIRY", "ENQUIRY").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").setParameter("rohan", "7845273233").setParameter("rashmi", "9619283833").setParameter("akshi", "7694848781").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("LOST")) {
            new ArrayList();
            results = (ArrayList)this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD.createdbyName, COUNT(*), SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:BOOKED,:REQUESTED,:ENQUIRY,:FOLLOWUP,:REJECTED,:REFUND,:MISSEDCALL) AND UD.createdBy NOT IN(:rohan,:rashmi,:akshi) GROUP BY UD.createdbyName ").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").setParameter("rohan", "7845273233").setParameter("rashmi", "9619283833").setParameter("akshi", "7694848781").getResultList();
            return results;
         }
      }

      return null;
   }

   public List<BookingDetails> bookingDetailsGroupByUserNameAndCustomeDate(VehicleRequestObject bookingRequest) {
      Date date = new Date();
      Date nextDay = Date.from(date.toInstant().plus(1L, ChronoUnit.DAYS));
      Date previousDay = Date.from(date.toInstant().minus(1L, ChronoUnit.DAYS));
      System.out.println(bookingRequest.getFromDate() + "  ,  " + bookingRequest.getToDate());
      ArrayList results;
      if (bookingRequest.getMemberType().equalsIgnoreCase("SUPER2ADMIN")) {
         if (bookingRequest.getRequestFor().equalsIgnoreCase("CUSTOM")) {
            new ArrayList();
            results = (ArrayList)this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD.createdbyName, COUNT(*), SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) GROUP BY UD.createdbyName").setParameter("currentDate", bookingRequest.getFromDate(), TemporalType.DATE).setParameter("lastDate", bookingRequest.getToDate(), TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            System.out.println(bookingRequest.getFromDate() + "  ,  " + bookingRequest.getToDate());
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("TODAY")) {
            new ArrayList();
            results = (ArrayList)this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD.createdbyName, COUNT(*), SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) GROUP BY UD.createdbyName").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("YESTERDAY")) {
            new ArrayList();
            results = (ArrayList)this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD.createdbyName, COUNT(*), SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) GROUP BY UD.createdbyName").setParameter("currentDate", previousDay, TemporalType.DATE).setParameter("lastDate", new Date(), TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").getResultList();
            return results;
         }
      } else {
         if (bookingRequest.getRequestFor().equalsIgnoreCase("CUSTOM")) {
            new ArrayList();
            results = (ArrayList)this.bookingDetailsDao.getEntityManager()
            		.createQuery("SELECT UD.createdbyName, COUNT(*), SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND "
//            				+ "status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) "
            				+ "UD.status IN (:REQUESTED,:BOOKED)"
            				+ "AND UD.createdBy NOT IN(:rohan,:rashmi,:akshi) GROUP BY UD.createdbyName")
            		.setParameter("currentDate", bookingRequest.getFromDate(), TemporalType.DATE)
            		.setParameter("lastDate", bookingRequest.getToDate(), TemporalType.DATE)
            		.setParameter("REQUESTED", "REQUESTED")
            		.setParameter("BOOKED", "BOOKED")
//            		.setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY")
//            		.setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST")
//            		.setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND")
//            		.setParameter("MISSEDCALL", "MISSEDCALL")
            		.setParameter("rohan", "7845273233")
            		.setParameter("rashmi", "9619283833")
            		.setParameter("akshi", "7694848781")
            		.getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("TODAY")) {
            new ArrayList();
            results = (ArrayList)this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD.createdbyName, COUNT(*), SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) AND UD.createdBy NOT IN(:rohan,:rashmi,:akshi) GROUP BY UD.createdbyName").setParameter("currentDate", new Date(), TemporalType.DATE).setParameter("lastDate", nextDay, TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").setParameter("rohan", "7845273233").setParameter("rashmi", "9619283833").setParameter("akshi", "7694848781").getResultList();
            return results;
         }

         if (bookingRequest.getRequestFor().equalsIgnoreCase("YESTERDAY")) {
            new ArrayList();
            results = (ArrayList)this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD.createdbyName, COUNT(*), SUM(receivedAmount) FROM BookingDetails UD where UD.createdAt BETWEEN :currentDate AND :lastDate AND status NOT IN(:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) AND UD.createdBy NOT IN(:rohan,:rashmi,:akshi) GROUP BY UD.createdbyName").setParameter("currentDate", previousDay, TemporalType.DATE).setParameter("lastDate", new Date(), TemporalType.DATE).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").setParameter("rohan", "7845273233").setParameter("rashmi", "9619283833").setParameter("akshi", "7694848781").getResultList();
            return results;
         }
      }

      return null;
   }

   public List<BookingDetailsHistory> getUpdateBookingDetailsHistory(VehicleRequestObject bookingRequest) {
      List<BookingDetailsHistory> results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetailsHistory UD where UD.bookingDetailsId =:bookingDetailsId ORDER BY UD.createdAt ASC").setParameter("bookingDetailsId", bookingRequest.getBookingDetailsId()).getResultList();
      return results;
   }

   public List<BookingDetails> enqiryDetails(VehicleRequestObject bookingRequest) {
      List<BookingDetails> results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.status =:status ORDER BY UD.createdAt DESC").setParameter("status", "ENQUIRY").getResultList();
      return results;
   }

   public List<BookingDetails> followUpDetails(VehicleRequestObject bookingRequest) {
      List<BookingDetails> results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.status =:status ORDER BY UD.createdAt DESC").setParameter("status", "FOLLOWUP").getResultList();
      return results;
   }

   public List<BookingDetails> lostDetails(VehicleRequestObject bookingRequest) {
      List<BookingDetails> results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.status =:status  ORDER BY UD.createdAt DESC").setParameter("status", "LOST").getResultList();
      return results;
   }

   public List<BookingDetails> getAllToChangeLost() {
      List<BookingDetails> results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where status IN(:ENQUIRY,:FOLLOWUP) AND fromDate <:fromDate").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("fromDate", new Date(), TemporalType.DATE).getResultList();
      return results;
   }

   public List<BookingDetails> getMissedCallList(VehicleRequestObject bookingRequest) {
      List<BookingDetails> results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where status =:status ORDER BY UD.id DESC").setParameter("status", "MISSEDCALL").getResultList();
      return results;
   }

   public List<BookingDetails> bookingDetailsByCreatedBy(VehicleRequestObject bookingRequest) {
      System.out.println(bookingRequest.getStatus() + " 1 " + bookingRequest.getCreatedBy());
      List results;
      if (bookingRequest.getStatus().equals("BOOKED")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where status IN(:BOOKED,:REQUESTED) AND createdBy =:createdBy ORDER BY UD.id DESC").setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").setParameter("createdBy", bookingRequest.getCreatedBy()).getResultList();
         return results;
      } else {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where status =:status AND createdBy =:createdBy ORDER BY UD.id DESC").setParameter("status", bookingRequest.getStatus()).setParameter("createdBy", bookingRequest.getCreatedBy()).getResultList();
         return results;
      }
   }

   public List<BookingDetails> getVehicleDetailsBeforeAIM(VehicleRequestObject bookingRequest) {
      Date todayDate = new Date();
      Date nextDay = Date.from(todayDate.toInstant().plus(1L, ChronoUnit.DAYS));
      int firstHour = LocalTime.now().getHour() - 6;
      int lastHour = firstHour + 4;
      List results;
      if (bookingRequest.getStatus().equals("PICKEDUP")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.fromDate BETWEEN :startDate AND :endDate AND UD.pickupTime BETWEEN :startTime AND :endTime  AND status IN(:BOOKED,:REQUESTED) ORDER BY UD.pickupTime DESC").setParameter("startTime", firstHour).setParameter("endTime", lastHour).setParameter("startDate", todayDate, TemporalType.DATE).setParameter("endDate", nextDay, TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").getResultList();
         return results;
      } else if (bookingRequest.getStatus().equals("DROP")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.toDate BETWEEN :startDate AND :endDate AND UD.deliveryTime BETWEEN :startTime AND :endTime  AND status IN(:BOOKED,:REQUESTED) ORDER BY UD.deliveryTime DESC").setParameter("startTime", firstHour).setParameter("endTime", lastHour).setParameter("startDate", todayDate, TemporalType.DATE).setParameter("endDate", nextDay, TemporalType.DATE).setParameter("BOOKED", "BOOKED").setParameter("REQUESTED", "REQUESTED").getResultList();
         return results;
      } else {
         return null;
      }
   }

   public List<BookingDetails> bookingDetailsByStatusChangeWithOwnnerDetails(VehicleRequestObject bookingRequest) {
      List<BookingDetails> results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where status NOT IN(:BOOKED,:CANCELREJECT,:REJECTED,:REFUND,:CANCELREQUEST) AND vehicleOwnner NOT IN(:null, :empty) ORDER BY UD.id DESC").setParameter("BOOKED", "BOOKED").setParameter("CANCELREJECT", "CANCELREJECT").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("CANCELREQUEST", "CANCELREQUEST").setParameter("null", "NULL").setParameter("empty", "").getResultList();
      return results;
   }

   public List<BookingDetails> getNotAssignedBookingForNotification(VehicleRequestObject bookingRequest) {
      Date date = new Date();
      long currentTime = (long)date.getHours();
      List<BookingDetails> results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where UD.toDate BETWEEN :startDate AND :endDate AND UD.deliveryTime BETWEEN :startTime AND :endTime  AND status IN(:BOOKED,:REQUESTED) ORDER BY UD.deliveryTime DESC").setParameter("REQUESTED", currentTime).getResultList();
      return results;
   }

   public List<BookingDetails> getBookingCount() {
      List<BookingDetails> results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT count(*) FROM BookingDetails UD where status IN(:BOOKED,:REQUESTED)").setParameter("REQUESTED", "REQUESTED").setParameter("BOOKED", "BOOKED").getResultList();
      return results;
   }

   public List<BookingDetails> helpDeskList(VehicleRequestObject bookingRequest) {
      List<BookingDetails> results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where cancelationFor IN(:deposit_issue,:id_Card_issue,:extra_Charge,:unprofessional_behavior,:vehicle_breakdown)").setParameter("deposit_issue", "deposit_issue").setParameter("id_Card_issue", "id_Card_issue").setParameter("extra_Charge", "extra_Charge").setParameter("unprofessional_behavior", "unprofessional_behavior").setParameter("vehicle_breakdown", "vehicle_breakdown").getResultList();
      return results;
   }

   public List<Object> totalAmountByAgentAndDate(VehicleRequestObject bookingRequest) {
      List results;
      if (bookingRequest.getRequestFor().equals("ALL")) {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where status IN(:BOOKED,:REQUESTED) AND UD.createdAt BETWEEN :firstDate AND :lastDate").setParameter("REQUESTED", "REQUESTED").setParameter("BOOKED", "BOOKED").setParameter("firstDate", bookingRequest.getStartDate(), TemporalType.DATE).setParameter("lastDate", bookingRequest.getEndDate(), TemporalType.DATE).getResultList();
         return results;
      } else {
         results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(receivedAmount) AS receivedAmount FROM BookingDetails UD where status IN(:BOOKED,:REQUESTED) AND UD.createdAt BETWEEN :firstDate AND :lastDate AND createdBy =:createdBy GROUP BY DATE(UD.createdAt)").setParameter("REQUESTED", "REQUESTED").setParameter("BOOKED", "BOOKED").setParameter("firstDate", bookingRequest.getStartDate(), TemporalType.DATE).setParameter("lastDate", bookingRequest.getEndDate(), TemporalType.DATE).setParameter("createdBy", bookingRequest.getCreatedBy()).getResultList();
         System.out.println(results);
         return results;
      }
   }

   public Object getTotalSaleAmountByCreatedBy(VehicleRequestObject bookingRequest, Date firstDate, Date lastDate) {
      Object results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT SUM(UD.receivedAmount) AS receivedAmount FROM BookingDetails UD WHERE createdBy = :createdBy AND UD.status NOT IN (:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) AND UD.createdAt BETWEEN :firstDate AND :lastDate").setParameter("createdBy", bookingRequest.getCreatedBy()).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").setParameter("firstDate", firstDate, TemporalType.DATE).setParameter("lastDate", lastDate, TemporalType.DATE).getSingleResult();
      return results;
   }

   public List<Object[]> getBonusDetails(VehicleRequestObject bookingRequest, Date firstDate, Date lastDate) {
      List<Object[]> results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT \n    bd.createdbyName AS createdbyName, \n    COUNT(*) AS totalCount, \n    SUM(bd.receivedAmount) AS receivedAmount,\n    (\n        SELECT bs.bonusAmount\n        FROM BonusSlab bs\n        WHERE SUM(bd.receivedAmount) >= bs.startAmount \n        AND SUM(bd.receivedAmount) <= bs.endAmount AND bs.bonusType = :bonusType\n    ) AS bonusAmount\nFROM \n    BookingDetails bd\nWHERE \n    bd.createdAt BETWEEN :currentDate AND :lastDate\n    AND bd.status NOT IN (:CANCEL,:ENQUIRY,:FOLLOWUP,:LOST,:REJECTED,:REFUND,:MISSEDCALL) \n    AND bd.createdBy NOT IN(:rohan,:rashmi,:akshi)\nGROUP BY \n    bd.createdbyName").setParameter("currentDate", firstDate, TemporalType.DATE).setParameter("lastDate", lastDate, TemporalType.DATE).setParameter("bonusType", bookingRequest.getBonusType()).setParameter("CANCEL", "CANCEL").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("LOST", "LOST").setParameter("REJECTED", "REJECTED").setParameter("REFUND", "REFUND").setParameter("MISSEDCALL", "MISSEDCALL").setParameter("rohan", "7845273233").setParameter("rashmi", "9619283833").setParameter("akshi", "7694848781").getResultList();
      System.out.println(firstDate + " , " + lastDate + " , " + bookingRequest.getBonusType() + "  hhuh");
      return results;
   }

   public List<BookingDetails> getFollowUp(String requestFor) {
      Date nextDay = null;
      Date todayDate = new Date();
      if (requestFor.equals("TODAY")) {
         nextDay = todayDate;
      }

      if (requestFor.equals("TOMMORROW")) {
         nextDay = Date.from(todayDate.toInstant().plus(1L, ChronoUnit.DAYS));
      }

      if (requestFor.equals("AFTER_TOMMORROW")) {
         nextDay = Date.from(todayDate.toInstant().plus(2L, ChronoUnit.DAYS));
      }

      List<BookingDetails> results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD WHERE status IN(:ENQUIRY,:FOLLOWUP) AND UD.fromDate =:fromDate ORDER BY UD.deliveryTime ASC").setParameter("ENQUIRY", "ENQUIRY").setParameter("FOLLOWUP", "FOLLOWUP").setParameter("fromDate", nextDay, TemporalType.DATE).getResultList();
      System.out.println("Date : " + nextDay);
      return results;
   }

   public List<BookingDetails> unknownDataList(Request<VehicleRequestObject> bookingRequest) {
      List<BookingDetails> results = this.bookingDetailsDao.getEntityManager().createQuery("SELECT UD FROM BookingDetails UD where createdBy IN(:Null,:empity) order by UD.id desc").setParameter("Null", "null").setParameter("empity", "").getResultList();
      return results;
   }
   
   //for new user role
   public List<BookingDetails> forNewUserRole(Request<VehicleRequestObject> bookingRequest) {
	      List<BookingDetails> results = bookingDetailsDao.getEntityManager().
	    		  createQuery("SELECT UD FROM BookingDetails UD where createdBy IN(:Null,:empity) order by UD.id desc")
	    		  .setParameter("Null", "null")
	    		  .setParameter("empity", "").getResultList();
	      return results;
	   }
}