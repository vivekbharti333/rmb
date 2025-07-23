 package com.Xposindia.service;

import com.Xposindia.entities.BonusSlab;
import com.Xposindia.entities.BookingDetails;
import com.Xposindia.entities.BookingDetailsHistory;
import com.Xposindia.entities.Users;
import com.Xposindia.entities.VehicleDetails;
import com.Xposindia.entities.VehicleName;
import com.Xposindia.expections.BizException;
import com.Xposindia.helper.AdminVehicleHelper;
import com.Xposindia.helper.BookingHelper;
import com.Xposindia.helper.BookingHistoryHelper;
import com.Xposindia.helper.CommonHelper;
import com.Xposindia.helper.InvoiceHelper;
import com.Xposindia.helper.UserHelper;
import com.Xposindia.helper.VehicleHelper;
import com.Xposindia.object.request.Request;
import com.Xposindia.object.request.VehicleRequestObject;
import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {
   private static final int BookingDetails = 0;
   @Autowired
   private BookingHelper bookingHelper;
   @Autowired
   private BookingHistoryHelper bookingHistoryHelper;
   @Autowired
   private VehicleHelper vehicleHelper;
   @Autowired
   private AdminVehicleHelper adminVehicleHelper;
   @Autowired
   private UserHelper userHelper;
   @Autowired
   private CommonHelper commonHelper;
   @Autowired
   private InvoiceHelper invoiceHelper;
   
   private LocalDate localDate = LocalDate.now();
   private LocalDate nextday;
   private LocalDate preday;
   private LocalDate firstDateOfMonth;
   private LocalDate lastDateOfMonth;
   private Date todayDate;
   private Date tomorrowDate;
   private Date previousDate;
   private Date firstDateMonth;
   private Date lastDateMonth;

   public BookingService() {
      this.nextday = this.localDate.plus(1L, ChronoUnit.DAYS);
      this.preday = this.localDate.minus(1L, ChronoUnit.DAYS);
      this.firstDateOfMonth = this.localDate.withDayOfMonth(1);
      this.lastDateOfMonth = this.localDate.with(TemporalAdjusters.lastDayOfMonth());
      this.todayDate = Date.from(this.localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
      this.tomorrowDate = Date.from(this.nextday.atStartOfDay(ZoneId.systemDefault()).toInstant());
      this.previousDate = Date.from(this.preday.atStartOfDay(ZoneId.systemDefault()).toInstant());
      this.firstDateMonth = Date.from(this.firstDateOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
      this.lastDateMonth = Date.from(this.lastDateOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
   }

   public VehicleRequestObject sendFollowupBulkMesg(Request<VehicleRequestObject> vehicleRequestObject) throws BizException, Exception {
      VehicleRequestObject vehicleRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      this.bookingHelper.validateBookingRequest(vehicleRequest);
      List<BookingDetails> bookingDetails = this.bookingHelper.getFollowUp(vehicleRequest.getRequestFor());
      Iterator var4 = bookingDetails.iterator();

      while(var4.hasNext()) {
         BookingDetails bookingDetail = (BookingDetails)var4.next();
         String param = this.commonHelper.forSchedular(bookingDetail);
         this.commonHelper.interaktApi(param);
      }

      vehicleRequest.setRespCode(200);
      vehicleRequest.setRespMesg("Send Successfully");
      return vehicleRequest;
   }

   public VehicleRequestObject updateVehicleNumber(Request<VehicleRequestObject> vehicleRequestObject) throws BizException, Exception {
      VehicleRequestObject vehicleRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      this.bookingHelper.validateBookingRequest(vehicleRequest);
      BookingDetails existsBooking = this.bookingHelper.getVehicleDetailsById(vehicleRequest.getId());
      if (existsBooking != null) {
         Date date = new Date();
         String basePath;
         String fileName;
         String finalFileName;
         if (vehicleRequest.getVehicleNumberImage() != null && !vehicleRequest.getVehicleNumberImage().isEmpty()) {
            basePath = this.userHelper.getPathToUploadFile("VehicleNumberImage");
            fileName = vehicleRequest.getVehicleNumber() + date.toString().replaceAll(" ", "") + "A";
            finalFileName = basePath + File.separator + fileName;
            finalFileName = this.userHelper.uploadPhotos(vehicleRequest.getVehicleNumberImage(), finalFileName);
            vehicleRequest.setVehicleNumberImage(fileName);
         }

         if (vehicleRequest.getVehicleFrontImage() != null && !vehicleRequest.getVehicleFrontImage().isEmpty()) {
            basePath = this.userHelper.getPathToUploadFile("VehicleNumberImage");
            fileName = "front" + vehicleRequest.getVehicleNumber() + date.toString().replaceAll(" ", "") + "A";
            finalFileName = basePath + File.separator + fileName;
            finalFileName = this.userHelper.uploadPhotos(vehicleRequest.getVehicleFrontImage(), finalFileName);
            vehicleRequest.setVehicleFrontImage(fileName);
         }

         if (vehicleRequest.getVehicleBackImage() != null && !vehicleRequest.getVehicleBackImage().isEmpty()) {
            basePath = this.userHelper.getPathToUploadFile("VehicleNumberImage");
            fileName = "back" + vehicleRequest.getVehicleBackImage() + date.toString().replaceAll(" ", "") + "A";
            finalFileName = basePath + File.separator + fileName;
            finalFileName = this.userHelper.uploadPhotos(vehicleRequest.getVehicleBackImage(), finalFileName);
            vehicleRequest.setVehicleBackImage(fileName);
         }

         if (vehicleRequest.getVehicleLeftImage() != null && !vehicleRequest.getVehicleLeftImage().isEmpty()) {
            basePath = this.userHelper.getPathToUploadFile("VehicleNumberImage");
            fileName = "left" + vehicleRequest.getVehicleLeftImage() + date.toString().replaceAll(" ", "") + "A";
            finalFileName = basePath + File.separator + fileName;
            finalFileName = this.userHelper.uploadPhotos(vehicleRequest.getVehicleLeftImage(), finalFileName);
            vehicleRequest.setVehicleLeftImage(fileName);
         }

         if (vehicleRequest.getVehicleRightImage() != null && !vehicleRequest.getVehicleRightImage().isEmpty()) {
            basePath = this.userHelper.getPathToUploadFile("VehicleNumberImage");
            fileName = "Right" + vehicleRequest.getVehicleRightImage() + date.toString().replaceAll(" ", "") + "A";
            finalFileName = basePath + File.separator + fileName;
            finalFileName = this.userHelper.uploadPhotos(vehicleRequest.getVehicleRightImage(), finalFileName);
            vehicleRequest.setVehicleRightImage(fileName);
         }

         existsBooking.setVehicleNumberImage(vehicleRequest.getVehicleNumberImage());
         existsBooking.setVehicleNumber(vehicleRequest.getVehicleNumber());
         existsBooking = this.bookingHelper.updateBookingDetails(existsBooking);
         BookingDetailsHistory bookingHistory = this.bookingHistoryHelper.getVehicleDetailsHistoryByObj(existsBooking);
         this.bookingHistoryHelper.saveBookingDetailsHistory(bookingHistory);
         vehicleRequest.setRespCode(200);
         vehicleRequest.setRespMesg("Update Successfully");
         return vehicleRequest;
      } else {
         vehicleRequest.setRespCode(401);
         vehicleRequest.setRespMesg("Something Went Wrong");
         return vehicleRequest;
      }
   }

   public VehicleRequestObject changeLeadStatus(Request<VehicleRequestObject> vehicleRequestObject) throws BizException, Exception {
	      VehicleRequestObject vehicleRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
	      this.bookingHelper.validateBookingRequest(vehicleRequest);
	      BookingDetails existsBooking = this.bookingHelper.getVehicleDetailsById(vehicleRequest.getId());
	      if (existsBooking != null) {
	         if (existsBooking.getStatus().equalsIgnoreCase("CANCEL")) {
	            existsBooking.setStatus(vehicleRequest.getStatus());
	            existsBooking.setUpdatedAtLetest(new Date());
	            existsBooking = this.bookingHelper.updateBookingDetails(existsBooking);
	         } else if (existsBooking.getStatus().equalsIgnoreCase(vehicleRequest.getStatus())) {
	            existsBooking.setCreatedAt(existsBooking.getCreatedAt());
	            existsBooking.setStatus(vehicleRequest.getStatus());
	            existsBooking.setUpdatedAtLetest(new Date());
	            existsBooking.setUpdatedBy(vehicleRequest.getCreatedBy());
	            existsBooking = this.bookingHelper.updateBookingDetails(existsBooking);
	         } else {
	            existsBooking.setCreatedAt(new Date());
	            existsBooking.setStatus(vehicleRequest.getStatus());
	            existsBooking.setUpdatedAtLetest(new Date());
//	            existsBooking.setUpdatedAtLetest(new Date());
	            existsBooking.setUpdatedBy(vehicleRequest.getCreatedBy());
	            existsBooking = this.bookingHelper.updateBookingDetails(existsBooking);
	            String enqPara;
	            String apiResp;
	            String receiptNumber;
	            String enqPara1;
	            String apiResp1;
	            String hu;
	            String balancePayment;
	            String securityDeposit;
	            if (existsBooking.getVehicleType().equalsIgnoreCase("BIKE")) {
	               if (vehicleRequest.getStatus().equals("FOLLOWUP")) {
	                  enqPara = this.commonHelper.fallowUpParameter(existsBooking);
	                  this.commonHelper.interaktApi(enqPara);
	               } else if (vehicleRequest.getStatus().equals("REQUESTED")) {
	                  enqPara = this.commonHelper.bookingConfirmationParameters(existsBooking);
	                  apiResp = this.commonHelper.interaktApi(enqPara);
	                  receiptNumber = this.invoiceHelper.receiptpdf(existsBooking);
	                  existsBooking.setReceiptNumber(receiptNumber);
	                  existsBooking.setReceiptStatus("NO");
	                  this.bookingHelper.updateBookingDetails(existsBooking);
	                  enqPara1 = this.commonHelper.bookingReceiptPdfParametersForVehicle(existsBooking);
	                  apiResp1 = this.commonHelper.interaktApi(enqPara1);
	                  System.out.println("Enq Param : " + enqPara1);
	                  System.out.println("resp : " + apiResp1);
	                  hu = this.commonHelper.forRentalBreakup(existsBooking);
	                  this.commonHelper.interaktApi(hu);
	                  balancePayment = this.commonHelper.forBalancePayment(existsBooking);
	                  this.commonHelper.interaktApi(balancePayment);
	                  securityDeposit = this.commonHelper.forSecurityDeposit(existsBooking);
	                  this.commonHelper.interaktApi(securityDeposit);
	                  System.out.println(enqPara + " oo");
	                  System.out.println(apiResp + " oo");
	               } else if (vehicleRequest.getStatus().equals("ENQUIRY")) {
	                  enqPara = this.commonHelper.enquiryParameters(existsBooking);
	                  apiResp = this.commonHelper.interaktApi(enqPara);
	                  System.out.println(enqPara + " oo");
	                  System.out.println(apiResp + " oo");
	               }
	            } else if (existsBooking.getVehicleType().equalsIgnoreCase("CAR")) {
	               System.out.println("Enter : 2");
	               if (vehicleRequest.getStatus().equals("FOLLOWUP")) {
	                  enqPara = this.commonHelper.fallowUpParameter(existsBooking);
	                  apiResp = this.commonHelper.interaktApi(enqPara);
	                  System.out.println(enqPara + " oo");
	                  System.out.println(apiResp);
	               } else if (vehicleRequest.getStatus().equals("REQUESTED")) {
	                  enqPara = this.commonHelper.bookingConfirmationParametersForCar(existsBooking);
	                  apiResp = this.commonHelper.interaktApi(enqPara);
	                  System.out.println("Response : " + apiResp);
	                  receiptNumber = this.invoiceHelper.receiptpdf(existsBooking);
	                  existsBooking.setReceiptNumber(receiptNumber);
	                  existsBooking.setReceiptStatus("NO");
	                  this.bookingHelper.updateBookingDetails(existsBooking);
	                  enqPara1 = this.commonHelper.bookingReceiptPdfParametersForVehicle(existsBooking);
	                  this.commonHelper.interaktApi(enqPara1);
	                  hu = this.commonHelper.forRentalBreakup(existsBooking);
	                  this.commonHelper.interaktApi(hu);
	                  balancePayment = this.commonHelper.forBalancePayment(existsBooking);
	                  this.commonHelper.interaktApi(balancePayment);
	                  securityDeposit = this.commonHelper.forSecurityDeposit(existsBooking);
	                  this.commonHelper.interaktApi(securityDeposit);
	               } else if (vehicleRequest.getStatus().equals("ENQUIRY")) {
	                  enqPara = this.commonHelper.enquiryParametersForCar(existsBooking);
	                  this.commonHelper.interaktApi(enqPara);
	               }
	            } else if (existsBooking.getVehicleType().equalsIgnoreCase("WATERSPORTS")) {
	               if (vehicleRequest.getStatus().equals("ENQUIRY")) {
	                  enqPara = this.commonHelper.enquiryForWaterSports(existsBooking);
	                  this.commonHelper.interaktApi(enqPara);
	               }

	               if (vehicleRequest.getStatus().equals("REQUESTED")) {
	                  enqPara = this.commonHelper.conformationForWaterSports(existsBooking);
	                  this.commonHelper.interaktApi(enqPara);
	                  receiptNumber = this.invoiceHelper.receiptpdf(existsBooking);
	                  existsBooking.setReceiptNumber(receiptNumber);
	                  existsBooking.setReceiptStatus("NO");
	                  this.bookingHelper.updateBookingDetails(existsBooking);
	                  enqPara1 = this.commonHelper.bookingReceiptPdfParametersForVehicle(existsBooking);
	                  apiResp1 = this.commonHelper.interaktApi(enqPara1);
	                  System.out.println("enqPara1 : " + enqPara1);
	                  System.out.println("apiResp1 : " + apiResp1);
	               }
	            }
	         }

	         BookingDetailsHistory bookingHistory = this.bookingHistoryHelper.getVehicleDetailsHistoryByObj(existsBooking);
	         this.bookingHistoryHelper.saveBookingDetailsHistory(bookingHistory);
	         vehicleRequest.setRespCode(200);
	         vehicleRequest.setRespMesg("Update Successfully");
	         return vehicleRequest;
	      } else {
	         vehicleRequest.setRespCode(401);
	         vehicleRequest.setRespMesg("Something Went Wrong");
	         return vehicleRequest;
	      }
	   }
   
   
   
		public VehicleRequestObject confirmCall(Request<VehicleRequestObject> vehicleRequestObject)
				throws BizException, Exception {
			VehicleRequestObject vehicleRequest = (VehicleRequestObject) vehicleRequestObject.getPayload();
			this.bookingHelper.validateBookingRequest(vehicleRequest);
			BookingDetails bookingDetails = bookingHelper.getVehicleDetailsById(vehicleRequest.getId());
			if (bookingDetails != null) {
				bookingDetails.setCallStatus("CALL_DONE");
				bookingHelper.updateBookingDetails(bookingDetails);
				vehicleRequest.setRespCode(200);
				vehicleRequest.setRespMesg("Update Successfully");
				return vehicleRequest;
			} else {
				vehicleRequest.setRespCode(404);
				vehicleRequest.setRespMesg("Booking Not found");
				return vehicleRequest;
			}
		}
		
		
		 public List<BookingDetails> getBookingDetailsForCall(Request<VehicleRequestObject> vehicleRequestObject) {
		      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
		      List<BookingDetails> bookingList = this.bookingHelper.getBookingDetailsForCall(bookingRequest);
		      return bookingList;
		   }
   
   @Transactional
   public VehicleRequestObject vehicleBooking(Request<VehicleRequestObject> vehicleRequestObject) throws BizException, Exception {
      VehicleRequestObject vehicleRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      this.bookingHelper.validateBookingRequest(vehicleRequest);
      BookingDetails bookingId = this.bookingHelper.getVehicleDetailsByBookingId(vehicleRequest.getBookingId());
      if (bookingId != null) {
         vehicleRequest.setRespCode(401);
         vehicleRequest.setRespMesg("Some problem Occure. Please check booking history first then book again");
         return vehicleRequest;
      } else {
         List<BookingDetails> bookingList = this.bookingHelper.getBookingDetailsByCustomerMobileNo(vehicleRequest);
         if (!bookingList.isEmpty()) {
            vehicleRequest.setRespCode(401);
            vehicleRequest.setRespMesg("Booking With Same Mobile No. Already Exists");
            return vehicleRequest;
         } else if (vehicleRequest.getEntryType().equalsIgnoreCase("MISSEDCALL")) {
            if (!vehicleRequest.getCustomerMobile().isEmpty() && vehicleRequest.getCustomerMobile() != null) {
               BookingDetails bookingDetails = new BookingDetails();
               bookingDetails.setCustomerMobile(vehicleRequest.getCustomerMobile());
               bookingDetails.setStatus(vehicleRequest.getEntryType());
               bookingDetails.setCreatedBy(vehicleRequest.getCreatedBy());
               bookingDetails.setCreatedbyName(vehicleRequest.getCreatedbyName());
               bookingDetails.setCreatedAt(new Date());
               if (vehicleRequest.getCountryCode().isEmpty()) {
                  bookingDetails.setCountryCode("+91");
               } else {
                  bookingDetails.setCountryCode(vehicleRequest.getCountryCode());
               }

               this.bookingHelper.saveBookingDetails(bookingDetails);
               vehicleRequest.setRespCode(200);
               vehicleRequest.setRespMesg("Missed Call Recoded");
               return vehicleRequest;
            } else {
               vehicleRequest.setRespCode(401);
               vehicleRequest.setRespMesg("Customer Mobile no. can not be Null");
               return vehicleRequest;
            }
         } else if (!vehicleRequest.getEntryType().isEmpty() && vehicleRequest.getEntryType() != null) {
            if (!vehicleRequest.getEnquirySource().isEmpty() && vehicleRequest.getEnquirySource() != null) {
               if (!vehicleRequest.getVehicleType().isEmpty() && vehicleRequest.getVehicleType() != null) {
                  if (!vehicleRequest.getVehicleName().isEmpty() && vehicleRequest.getVehicleName() != null) {
                     if (!vehicleRequest.getVehicleDetailsType().isEmpty() && vehicleRequest.getVehicleDetailsType() != null) {
                        if (!vehicleRequest.getCity().isEmpty() && vehicleRequest.getCity() != null) {
                           if (vehicleRequest.getFromDate() == null) {
                        	   
                        	   if (!vehicleRequest.getToDate().after(vehicleRequest.getFromDate())) {
                        		   vehicleRequest.setRespCode(401);
                                   vehicleRequest.setRespMesg("Pickup Date cann't after drop date");
                                   return vehicleRequest;
                        	    } 
                        	   
                              vehicleRequest.setRespCode(401);
                              vehicleRequest.setRespMesg("Pickup Date can not be Null");
                              return vehicleRequest;
                           } else if (vehicleRequest.getPickupTime() == 0) {
                              vehicleRequest.setRespCode(401);
                              vehicleRequest.setRespMesg("Pickup Time can not be Null");
                              return vehicleRequest;
                           } else {
                              System.out.println("Enter 3@");
                              if (!vehicleRequest.getAreaFrom().isEmpty() && vehicleRequest.getAreaFrom() != null) {
                                 if (vehicleRequest.getToDate() == null) {
                                    vehicleRequest.setRespCode(401);
                                    vehicleRequest.setRespMesg("Drop Date can not be Null");
                                    return vehicleRequest;
                                 } else if (vehicleRequest.getDeliveryTime() == 0) {
                                    vehicleRequest.setRespCode(401);
                                    vehicleRequest.setRespMesg("Drop Time can not be Null");
                                    return vehicleRequest;
                                 } else if (!vehicleRequest.getAreaTo().isEmpty() && vehicleRequest.getAreaTo() != null) {
                                    if (!vehicleRequest.getCustomerMobile().isEmpty() && vehicleRequest.getCustomerMobile() != null) {
                                       if (!vehicleRequest.getFirstName().isEmpty() && vehicleRequest.getFirstName() != null) {
                                          if (!vehicleRequest.getLastName().isEmpty() && vehicleRequest.getLastName() != null) {
                                             if (vehicleRequest.getVehicleQuantity() == 0) {
                                                vehicleRequest.setRespCode(401);
                                                vehicleRequest.setRespMesg("Booking Quntity can not be zero");
                                                return vehicleRequest;
                                             } else if (vehicleRequest.getBookingPrice() == 0.0D) {
                                                vehicleRequest.setRespCode(401);
                                                vehicleRequest.setRespMesg("Per Day Rental can not be zero");
                                                return vehicleRequest;
                                             } else if (vehicleRequest.getDeliveryCharges() == 0) {
                                                vehicleRequest.setRespCode(401);
                                                vehicleRequest.setRespMesg("Delivery Charges can not be zero");
                                                return vehicleRequest;
                                             } else if (vehicleRequest.getSecurityDeposit() == 0.0D) {
                                                vehicleRequest.setRespCode(401);
                                                vehicleRequest.setRespMesg("Security Deposit can not be zero");
                                                return vehicleRequest;
                                             } else if (vehicleRequest.getReceivedAmount() == 0) {
                                                vehicleRequest.setRespCode(401);
                                                vehicleRequest.setRespMesg("Booking Amount can not be zero");
                                                return vehicleRequest;
                                             } else if (vehicleRequest.getBalenceAmount() == 0) {
                                                System.out.println("jjjj  :  " + vehicleRequest.getBalenceAmount());
                                                vehicleRequest.setRespCode(401);
                                                vehicleRequest.setRespMesg("Balence Amount can not be zero");
                                                return vehicleRequest;
                                             } else if (vehicleRequest.getTotalAmount() == 0) {
                                                vehicleRequest.setRespCode(401);
                                                vehicleRequest.setRespMesg("Total Amount can not be zero");
                                                return vehicleRequest;
                                             } else if (!vehicleRequest.getStatus().isEmpty() && vehicleRequest.getStatus() != null) {
                                                if (vehicleRequest.getStatus().equals("REQUESTED") && vehicleRequest.getModeOfPayment().isEmpty()) {
                                                   vehicleRequest.setRespCode(401);
                                                   vehicleRequest.setRespMesg("Payment Mode can not be Null");
                                                   return vehicleRequest;
                                                } else if (vehicleRequest.getCreatedBy() == null && vehicleRequest.getCreatedBy().isEmpty()) {
                                                   vehicleRequest.setRespCode(401);
                                                   vehicleRequest.setRespMesg("Refresh Page And Try Again");
                                                   return vehicleRequest;
                                                } else {
                                                   if (vehicleRequest.getDeliveryCharges() == 1) {
                                                      vehicleRequest.setDeliveryCharges(0);
                                                   }

                                                   if (vehicleRequest.getBalenceAmount() == 1) {
                                                      vehicleRequest.setBalenceAmount(0);
                                                   }

                                                   if (vehicleRequest.getReceivedAmount() == 1) {
                                                      vehicleRequest.setReceivedAmount(0);
                                                   }

                                                   VehicleName existVehicle = this.adminVehicleHelper.getVehicleNameByVehicleNameAndVehicleDetailType(vehicleRequest.getVehicleName(), vehicleRequest.getVehicleDetailsType());
                                                   if (existVehicle != null && existVehicle.getPriceLimit() > vehicleRequest.getBookingPrice()) {
                                                      vehicleRequest.setRespCode(401);
                                                      vehicleRequest.setRespMesg("Per Day Rental Price Cann't be Less then " + existVehicle.getPriceLimit());
                                                      return vehicleRequest;
                                                   } else if (!vehicleRequest.getCustomerMobile().contains(" ") && !vehicleRequest.getCustomerMobile().contains("  ") && !vehicleRequest.getCustomerMobile().contains("  ")) {
                                                      if (vehicleRequest.getCountryCode().equalsIgnoreCase((String)null) || vehicleRequest.getCountryCode().equalsIgnoreCase("")) {
                                                         vehicleRequest.setCountryCode("+91");
                                                      }

                                                      LocalDate pickupDate = Instant.ofEpochMilli(vehicleRequest.getFromDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                                                      LocalDate dropDate = Instant.ofEpochMilli(vehicleRequest.getToDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                                                      Long difference = ChronoUnit.DAYS.between(pickupDate, dropDate);
                                                      System.out.println("Enter diff " + difference);
                                                      if (difference >= 0L) {
                                                         System.out.println("Save Booking");
                                                         BookingDetails bookingDetails = this.bookingHelper.getVehicleDetailsByObj(vehicleRequest);
                                                         bookingDetails = this.bookingHelper.saveBookingDetails(bookingDetails);
                                                         
                                                         String enqPara;
                                                         String apiResp;
                                                         String receiptNumber;
                                                         String enqPara1;
                                                         String hu;
                                                         if (existVehicle.getVehicleType().equalsIgnoreCase("BIKE")) {
                                                            if (vehicleRequest.getStatus().equals("FOLLOWUP")) {
                                                               enqPara = this.commonHelper.fallowUpParameter(bookingDetails);
                                                               apiResp = this.commonHelper.interaktApi(enqPara);
                                                              
                                                            } else if (vehicleRequest.getStatus().equals("REQUESTED")) {
                                                               enqPara = this.commonHelper.bookingConfirmationParameters(bookingDetails);
                                                               this.commonHelper.interaktApi(enqPara);
                                                               receiptNumber = this.invoiceHelper.receiptpdf(bookingDetails);
                                                               bookingDetails.setReceiptNumber(receiptNumber);
                                                               bookingDetails.setReceiptStatus("NO");
                                                               this.bookingHelper.updateBookingDetails(bookingDetails);
                                                               enqPara1 = this.commonHelper.bookingReceiptPdfParametersForVehicle(bookingDetails);
                                                               this.commonHelper.interaktApi(enqPara1);
                                                               hu = this.commonHelper.forRentalBreakup(bookingDetails);
                                                               this.commonHelper.interaktApi(hu);
                                                               String balancePayment = this.commonHelper.forBalancePayment(bookingDetails);
                                                               this.commonHelper.interaktApi(balancePayment);
                                                               String securityDeposit = this.commonHelper.forSecurityDeposit(bookingDetails);
                                                               this.commonHelper.interaktApi(securityDeposit);
                                                            } else if (vehicleRequest.getStatus().equals("ENQUIRY")) {
                                                            }
                                                         } else if (existVehicle.getVehicleType().equalsIgnoreCase("CAR")) {
                                                            if (vehicleRequest.getStatus().equals("FOLLOWUP")) {
                                                               enqPara = this.commonHelper.fallowUpParameter(bookingDetails);
                                                               this.commonHelper.interaktApi(enqPara);
                                                            } else if (vehicleRequest.getStatus().equals("REQUESTED")) {
                                                               enqPara = this.commonHelper.bookingConfirmationParametersForCar(bookingDetails);
                                                               apiResp = this.commonHelper.interaktApi(enqPara);
                                                               System.out.println("Response : " + apiResp);
                                                               receiptNumber = this.invoiceHelper.receiptpdf(bookingDetails);
                                                               bookingDetails.setReceiptNumber(receiptNumber);
                                                               bookingDetails.setReceiptStatus("NO");
                                                               this.bookingHelper.updateBookingDetails(bookingDetails);
                                                               enqPara1 = this.commonHelper.bookingReceiptPdfParametersForVehicle(bookingDetails);
                                                               this.commonHelper.interaktApi(enqPara1);
                                                               hu = this.commonHelper.forRentalBreakup(bookingDetails);
                                                               this.commonHelper.interaktApi(hu);
                                                            } else if (vehicleRequest.getStatus().equals("ENQUIRY")) {
                                                            	
                                                            }
                                                         } else if (existVehicle.getVehicleType().equalsIgnoreCase("WATERSPORTS")) {
                                                            System.out.println("Enter here123456");
                                                            if (vehicleRequest.getStatus().equals("ENQUIRY")) {
                                                            
                                                            }

                                                            if (vehicleRequest.getStatus().equals("REQUESTED")) {
                                                               enqPara = this.commonHelper.conformationForWaterSports(bookingDetails);
                                                               apiResp = this.commonHelper.interaktApi(enqPara);
                                                               System.out.println("7686: " + enqPara);
                                                               System.out.println("786786 : " + apiResp);
                                                               receiptNumber = this.invoiceHelper.receiptpdf(bookingDetails);
                                                               bookingDetails.setReceiptNumber(receiptNumber);
                                                               bookingDetails.setReceiptStatus("NO");
                                                               this.bookingHelper.updateBookingDetails(bookingDetails);
                                                               enqPara1 = this.commonHelper.bookingReceiptPdfParametersForVehicle(bookingDetails);
                                                               String apiResp1 = this.commonHelper.interaktApi(enqPara1);
                                                               System.out.println("enqPara1 : " + enqPara1);
                                                               System.out.println("apiResp1 : " + apiResp1);
                                                            }
                                                         }

                                                         try {
                                                            BookingDetailsHistory bookingHistory = this.bookingHistoryHelper.getVehicleDetailsHistoryByObj(bookingDetails);
                                                            this.bookingHistoryHelper.saveBookingDetailsHistory(bookingHistory);
                                                         } catch (Exception var21) {
                                                            var21.printStackTrace();
                                                         }

                                                         vehicleRequest.setRespCode(200);
                                                         vehicleRequest.setRespMesg("Booking request Successfull");
                                                         return vehicleRequest;
                                                      } else {
                                                         vehicleRequest.setRespCode(401);
                                                         vehicleRequest.setRespMesg("Check Pick Date & Drop Date");
                                                         return vehicleRequest;
                                                      }
                                                   } else {
                                                      vehicleRequest.setRespCode(401);
                                                      vehicleRequest.setRespMesg("Mobile Number Containt White Space");
                                                      return vehicleRequest;
                                                   }
                                                }
                                             } else {
                                                vehicleRequest.setRespCode(401);
                                                vehicleRequest.setRespMesg("Status can not be Null");
                                                return vehicleRequest;
                                             }
                                          } else {
                                             vehicleRequest.setRespCode(401);
                                             vehicleRequest.setRespMesg("Last Name can not be Null");
                                             return vehicleRequest;
                                          }
                                       } else {
                                          vehicleRequest.setRespCode(401);
                                          vehicleRequest.setRespMesg("First Name can not be Null");
                                          return vehicleRequest;
                                       }
                                    } else {
                                       vehicleRequest.setRespCode(401);
                                       vehicleRequest.setRespMesg("Customer Mobile no. can not be Null");
                                       return vehicleRequest;
                                    }
                                 } else {
                                    vehicleRequest.setRespCode(401);
                                    vehicleRequest.setRespMesg("Delivery Point can not be Null");
                                    return vehicleRequest;
                                 }
                              } else {
                                 vehicleRequest.setRespCode(401);
                                 vehicleRequest.setRespMesg("Pickup Point can not be Null");
                                 return vehicleRequest;
                              }
                           }
                        } else {
                           vehicleRequest.setRespCode(401);
                           vehicleRequest.setRespMesg("City can not be Null");
                           return vehicleRequest;
                        }
                     } else {
                        vehicleRequest.setRespCode(401);
                        vehicleRequest.setRespMesg("Vehicle Name can not be Null");
                        return vehicleRequest;
                     }
                  } else {
                     vehicleRequest.setRespCode(401);
                     vehicleRequest.setRespMesg("Vehicle Name can not be Null");
                     return vehicleRequest;
                  }
               } else {
                  vehicleRequest.setRespCode(401);
                  vehicleRequest.setRespMesg("Vehicle Type can not be Null");
                  return vehicleRequest;
               }
            } else {
               vehicleRequest.setRespCode(401);
               vehicleRequest.setRespMesg("Enquary Source can not be Null");
               return vehicleRequest;
            }
         } else {
            vehicleRequest.setRespCode(401);
            vehicleRequest.setRespMesg("Entry Type can not be Null");
            return vehicleRequest;
         }
      }
   }

   @Transactional
   public VehicleRequestObject updateBookingDetails(Request<VehicleRequestObject> vehicleRequestObject) throws BizException, Exception {
      VehicleRequestObject vehicleRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      this.bookingHelper.validateBookingRequest(vehicleRequest);
      List<BookingDetails> bookingList = this.bookingHelper.getBookingDetailsByCustomerMobileNoForUpdate(vehicleRequest);
      if (!bookingList.isEmpty()) {
         vehicleRequest.setRespCode(401);
         vehicleRequest.setRespMesg("Booking With Same Mobile No. Already Exists");
         return vehicleRequest;
      } else if (vehicleRequest.getCreatedBy() == null && vehicleRequest.getCreatedBy().isEmpty()) {
         vehicleRequest.setRespCode(401);
         vehicleRequest.setRespMesg("Refresh Page And Try Again");
         return vehicleRequest;
      } else if (vehicleRequest.getId() == 0L) {
         vehicleRequest.setRespCode(401);
         vehicleRequest.setRespMesg("Refresh Page And Try Again");
         return vehicleRequest;
      } else if (!vehicleRequest.getEnquirySource().isEmpty() && vehicleRequest.getEnquirySource() != null) {
         if (!vehicleRequest.getVehicleType().isEmpty() && vehicleRequest.getVehicleType() != null) {
            System.out.println("Enter 4");
            if (!vehicleRequest.getVehicleName().isEmpty() && vehicleRequest.getVehicleName() != null) {
               System.out.println("Enter 5");
               if (!vehicleRequest.getVehicleDetailsType().isEmpty() && vehicleRequest.getVehicleDetailsType() != null) {
                  if (vehicleRequest.getSecurityDeposit() == 0.0D) {
                  }

                  System.out.println("Enter 5");
                  if (!vehicleRequest.getCity().isEmpty() && vehicleRequest.getCity() != null) {
                     System.out.println("Enter 6");
                     if (vehicleRequest.getFromDate() == null) {
                        vehicleRequest.setRespCode(401);
                        vehicleRequest.setRespMesg("Pickup Date can not be Null");
                        return vehicleRequest;
                     } else {
                        System.out.println("Enter 6");
                        if (vehicleRequest.getPickupTime() == 0) {
                           vehicleRequest.setRespCode(401);
                           vehicleRequest.setRespMesg("Pickup Time can not be Null");
                           return vehicleRequest;
                        } else {
                           System.out.println("Enter 7");
                           if (!vehicleRequest.getAreaFrom().isEmpty() && vehicleRequest.getAreaFrom() != null) {
                              System.out.println("Enter 8");
                              if (vehicleRequest.getToDate() == null) {
                                 vehicleRequest.setRespCode(401);
                                 vehicleRequest.setRespMesg("Drop Date can not be Null");
                                 return vehicleRequest;
                              } else {
                                 System.out.println("Enter 9");
                                 if (vehicleRequest.getDeliveryTime() == 0) {
                                    vehicleRequest.setRespCode(401);
                                    vehicleRequest.setRespMesg("Drop Time can not be Null");
                                    return vehicleRequest;
                                 } else {
                                    System.out.println("Enter 10");
                                    if (!vehicleRequest.getAreaTo().isEmpty() && vehicleRequest.getAreaTo() != null) {
                                       System.out.println("Enter 11");
                                       if (!vehicleRequest.getCustomerMobile().isEmpty() && vehicleRequest.getCustomerMobile() != null) {
                                          System.out.println("Enter 12");
                                          if (!vehicleRequest.getCustomerName().isEmpty() && vehicleRequest.getCustomerName() != null) {
                                             System.out.println("Enter 14");
                                             if (vehicleRequest.getVehicleQuantity() == 0) {
                                                vehicleRequest.setRespCode(401);
                                                vehicleRequest.setRespMesg("Booking Quntity can not be zero");
                                                return vehicleRequest;
                                             } else {
                                                System.out.println("Enter 15");
                                                if (vehicleRequest.getBookingPrice() == 0.0D) {
                                                   vehicleRequest.setRespCode(401);
                                                   vehicleRequest.setRespMesg("Per Day Rental can not be zero");
                                                   return vehicleRequest;
                                                } else {
                                                   System.out.println("Enter 16");
                                                   if (vehicleRequest.getDeliveryCharges() == 0) {
                                                      vehicleRequest.setRespCode(401);
                                                      vehicleRequest.setRespMesg("Delivery Charges can not be zero");
                                                      return vehicleRequest;
                                                   } else {
                                                      if (vehicleRequest.getDeliveryCharges() == 1) {
                                                         vehicleRequest.setDeliveryCharges(0);
                                                      }

                                                      System.out.println("Enter 17");
                                                      if (vehicleRequest.getReceivedAmount() == 0) {
                                                         vehicleRequest.setRespCode(401);
                                                         vehicleRequest.setRespMesg("Booking Amount can not be zero");
                                                         return vehicleRequest;
                                                      } else {
                                                         System.out.println("Enter 18");
                                                         if (vehicleRequest.getBalenceAmount() == 0) {
                                                            vehicleRequest.setRespCode(401);
                                                            vehicleRequest.setRespMesg("Balence Amount can not be zero");
                                                            return vehicleRequest;
                                                         } else {
                                                            System.out.println("Enter 19");
                                                            if (vehicleRequest.getBalenceAmount() == 0) {
                                                               vehicleRequest.setRespCode(401);
                                                               vehicleRequest.setRespMesg("Balence Amount can not be zero");
                                                               return vehicleRequest;
                                                            } else {
                                                               System.out.println("Enter 20");
                                                               if (vehicleRequest.getTotalAmount() == 0) {
                                                                  vehicleRequest.setRespCode(401);
                                                                  vehicleRequest.setRespMesg("Total Amount can not be zero");
                                                                  return vehicleRequest;
                                                               } else {
                                                                  if (vehicleRequest.getDeliveryCharges() == 1) {
                                                                     vehicleRequest.setDeliveryCharges(0);
                                                                  }

                                                                  if (vehicleRequest.getBalenceAmount() == 1) {
                                                                     vehicleRequest.setBalenceAmount(0);
                                                                  }

                                                                  if (vehicleRequest.getReceivedAmount() == 1) {
                                                                     vehicleRequest.setReceivedAmount(0);
                                                                  }

                                                                  System.out.println("Enter 22");
                                                                  BookingDetails existsBooking = this.bookingHelper.getVehicleDetailsById(vehicleRequest.getId());
                                                                  if (existsBooking == null) {
                                                                     vehicleRequest.setRespCode(401);
                                                                     vehicleRequest.setRespMesg("Wrong Request. Try Again");
                                                                     return vehicleRequest;
                                                                  } else {
                                                                    
                                                                	  if(existsBooking.getStatus().equals("BOOKED") && !vehicleRequest.getMemberType().equalsIgnoreCase("SUPERADMIN")) {
                                                                		  vehicleRequest.setRespCode(401);
                                                                          vehicleRequest.setRespMesg("Edits not allowed as booking is already assigned");
                                                                          return vehicleRequest;
                                                                	  }
                                                                		  
                                                                		  
                                                                	  
                                                                     if (existsBooking.getStatus().equals("REQUESTED") || existsBooking.getStatus().equals("BOOKED")) {
                                                            
                                                                        if (vehicleRequest.getPickupTime() == existsBooking.getPickupTime() && vehicleRequest.getDeliveryTime() == existsBooking.getDeliveryTime() && vehicleRequest.getFromDate().equals(existsBooking.getFromDate()) && vehicleRequest.getToDate().equals(existsBooking.getToDate()) && vehicleRequest.getAreaFrom().equals(existsBooking.getAreaFrom()) && vehicleRequest.getAreaTo().equals(existsBooking.getAreaTo()) && vehicleRequest.getVehicleType().equals(existsBooking.getVehicleType()) && vehicleRequest.getVehicleName().equals(existsBooking.getVehicleName()) && vehicleRequest.getVehicleDetailsType().equals(existsBooking.getVehicleDetailsType())) {
                                                                          
                                                                           vehicleRequest.setStatus(existsBooking.getStatus());
                                                                        } else {
                                                                           vehicleRequest.setStatus("REQUESTED");
                                                                        }
                                                                     }

                                                                     System.out.println(vehicleRequest.getStatus() + ",  " + existsBooking.getStatus() + "9");
                                                                     if (vehicleRequest.getCountryCode().equalsIgnoreCase((String)null) || vehicleRequest.getCountryCode().equalsIgnoreCase("")) {
                                                                        vehicleRequest.setCountryCode("+91");
                                                                     }

                                                                     Users users = this.userHelper.getUserDetailsByUserId(vehicleRequest.getCreatedBy());
                                                                     if (users != null) {
                                                                        vehicleRequest.setCreatedbyName(users.getFullName());
                                                                     }

                                                                     if ((existsBooking.getStatus().equalsIgnoreCase("REQUESTED") || existsBooking.getStatus().equalsIgnoreCase("BOOKED")) && (!existsBooking.getVehicleType().equals(vehicleRequest.getVehicleType()) || !existsBooking.getVehicleName().equals(vehicleRequest.getVehicleName()) || !existsBooking.getVehicleDetailsType().equals(vehicleRequest.getVehicleDetailsType()) || !existsBooking.getFromDate().equals(existsBooking.getFromDate()) || existsBooking.getPickupTime() != vehicleRequest.getPickupTime() || !existsBooking.getAreaFrom().equals(vehicleRequest.getAreaFrom()) || existsBooking.getToDate() != vehicleRequest.getToDate() || existsBooking.getDeliveryTime() != vehicleRequest.getDeliveryTime() || !existsBooking.getAreaTo().equals(vehicleRequest.getAreaTo()))) {
                                                                        vehicleRequest.setStatus("REQUESTED");
                                                                     }

                                                                     existsBooking = this.bookingHelper.getUpdatedBookingDetailsByObj(vehicleRequest, existsBooking);
                                                                     existsBooking = this.bookingHelper.updateBookingDetails(existsBooking);
                                                                     String enqPara;
                                                                     String receiptNumber;
                                                                     String enqPara1;
                                                                     String hu;
                                                                     if (vehicleRequest.getVehicleType().equalsIgnoreCase("BIKE")) {
                                                                        if (vehicleRequest.getStatus().equals("FOLLOWUP")) {
                                                                           enqPara = this.commonHelper.fallowUpParameter(existsBooking);
                                                                           this.commonHelper.interaktApi(enqPara);
                                                                        } else if (vehicleRequest.getStatus().equals("REQUESTED")) {
                                                                           enqPara = this.commonHelper.bookingConfirmationParameters(existsBooking);
                                                                           this.commonHelper.interaktApi(enqPara);
                                                                           receiptNumber = this.invoiceHelper.receiptpdf(existsBooking);
                                                                           existsBooking.setReceiptNumber(receiptNumber);
                                                                           existsBooking.setReceiptStatus("NO");
                                                                           this.bookingHelper.updateBookingDetails(existsBooking);
                                                                           enqPara1 = this.commonHelper.bookingReceiptPdfParametersForVehicle(existsBooking);
                                                                           this.commonHelper.interaktApi(enqPara1);
                                                                           hu = this.commonHelper.forRentalBreakup(existsBooking);
                                                                           this.commonHelper.interaktApi(hu);
                                                                        } else if (vehicleRequest.getStatus().equals("ENQUIRY")) {
                                                                        }
                                                                     } else {
                                                                        String apiResp1;
                                                                        if (vehicleRequest.getVehicleType().equalsIgnoreCase("CAR")) {
                                                                           if (vehicleRequest.getStatus().equals("FOLLOWUP")) {
                                                                              enqPara = this.commonHelper.fallowUpParameter(existsBooking);
                                                                              this.commonHelper.interaktApi(enqPara);
                                                                           } else if (vehicleRequest.getStatus().equals("REQUESTED")) {
                                                                              enqPara = this.commonHelper.bookingConfirmationParametersForCar(existsBooking);
                                                                              this.commonHelper.interaktApi(enqPara);
                                                                              receiptNumber = this.invoiceHelper.receiptpdf(existsBooking);
                                                                              existsBooking.setReceiptNumber(receiptNumber);
                                                                              existsBooking.setReceiptStatus("NO");
                                                                              existsBooking.setUpdatedBy(vehicleRequest.getCreatedBy());
                                                                              existsBooking.setUpdatedBy(vehicleRequest.getUpdatedBy());
                                                                              existsBooking.setUpdatedAt(new Date());
                                                                              this.bookingHelper.updateBookingDetails(existsBooking);
                                                                              enqPara1 = this.commonHelper.bookingReceiptPdfParametersForVehicle(existsBooking);
                                                                              apiResp1 = this.commonHelper.interaktApi(enqPara1);
                                                                              hu = this.commonHelper.forRentalBreakup(existsBooking);
                                                                              this.commonHelper.interaktApi(hu);
                                                                              System.out.println("hggghghj  " + apiResp1);
                                                                           } else if (vehicleRequest.getStatus().equals("ENQUIRY")) {
                                                                           }
                                                                        } else if (vehicleRequest.getVehicleType().equalsIgnoreCase("WATERSPORTS")) {
                                                                           if (vehicleRequest.getStatus().equals("ENQUIRY")) {
                                                                           }

                                                                           if (vehicleRequest.getStatus().equals("REQUESTED")) {
                                                                              enqPara = this.commonHelper.conformationForWaterSports(existsBooking);
                                                                              String apiResp = this.commonHelper.interaktApi(enqPara);
                                                                              System.out.println("7686: " + enqPara);
                                                                              System.out.println("786786 : " + apiResp);
                                                                              receiptNumber = this.invoiceHelper.receiptpdf(existsBooking);
                                                                              existsBooking.setReceiptNumber(receiptNumber);
                                                                              existsBooking.setReceiptStatus("NO");
                                                                              existsBooking.setUpdatedBy(vehicleRequest.getCreatedBy());
                                                                              this.bookingHelper.updateBookingDetails(existsBooking);
                                                                              enqPara1 = this.commonHelper.bookingReceiptPdfParametersForVehicle(existsBooking);
                                                                              apiResp1 = this.commonHelper.interaktApi(enqPara1);
                                                                              System.out.println("enqPara1 : " + enqPara1);
                                                                              System.out.println("apiResp1 : " + apiResp1);
                                                                           }
                                                                        }
                                                                     }

                                                                     BookingDetailsHistory bookingHistory = this.bookingHistoryHelper.getVehicleDetailsHistoryByObj(existsBooking);
                                                                     this.bookingHistoryHelper.saveBookingDetailsHistory(bookingHistory);
                                                                     vehicleRequest.setRespCode(200);
                                                                     vehicleRequest.setRespMesg("Booking Update Successfull");
                                                                     return vehicleRequest;
                                                                  }
                                                               }
                                                            }
                                                         }
                                                      }
                                                   }
                                                }
                                             }
                                          } else {
                                             if (vehicleRequest.getFirstName().isEmpty() || vehicleRequest.getFirstName() == null) {
                                                vehicleRequest.setRespCode(401);
                                             }

                                             vehicleRequest.setRespMesg("Customer Name can not be Null");
                                             return vehicleRequest;
                                          }
                                       } else {
                                          vehicleRequest.setRespCode(401);
                                          vehicleRequest.setRespMesg("Customer Mobile no. can not be Null");
                                          return vehicleRequest;
                                       }
                                    } else {
                                       vehicleRequest.setRespCode(401);
                                       vehicleRequest.setRespMesg("Delivery Point can not be Null");
                                       return vehicleRequest;
                                    }
                                 }
                              }
                           } else {
                              vehicleRequest.setRespCode(401);
                              vehicleRequest.setRespMesg("Pickup Point can not be Null");
                              return vehicleRequest;
                           }
                        }
                     }
                  } else {
                     vehicleRequest.setRespCode(401);
                     vehicleRequest.setRespMesg("City can not be Null");
                     return vehicleRequest;
                  }
               } else {
                  vehicleRequest.setRespCode(401);
                  vehicleRequest.setRespMesg("Vehicle Name can not be Null");
                  return vehicleRequest;
               }
            } else {
               vehicleRequest.setRespCode(401);
               vehicleRequest.setRespMesg("Vehicle Name can not be Null");
               return vehicleRequest;
            }
         } else {
            vehicleRequest.setRespCode(401);
            vehicleRequest.setRespMesg("Vehicle Type can not be Null");
            return vehicleRequest;
         }
      } else {
         vehicleRequest.setRespCode(401);
         vehicleRequest.setRespMesg("Enquary Source can not be Null");
         return vehicleRequest;
      }
   }

   @Transactional
   public VehicleRequestObject updatedBookingDetailsOfMissCall(Request<VehicleRequestObject> vehicleRequestObject) throws BizException, Exception {
      VehicleRequestObject vehicleRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      this.bookingHelper.validateBookingRequest(vehicleRequest);
      if (!vehicleRequest.getEnquirySource().isEmpty() && vehicleRequest.getEnquirySource() != null) {
         if (!vehicleRequest.getVehicleType().isEmpty() && vehicleRequest.getVehicleType() != null) {
            if (!vehicleRequest.getVehicleName().isEmpty() && vehicleRequest.getVehicleName() != null) {
               if (!vehicleRequest.getVehicleDetailsType().isEmpty() && vehicleRequest.getVehicleDetailsType() != null) {
                  if (!vehicleRequest.getCity().isEmpty() && vehicleRequest.getCity() != null) {
                     if (vehicleRequest.getFromDate() == null) {
                        vehicleRequest.setRespCode(401);
                        vehicleRequest.setRespMesg("Pickup Date can not be Null");
                        return vehicleRequest;
                     } else if (vehicleRequest.getPickupTime() == 0) {
                        vehicleRequest.setRespCode(401);
                        vehicleRequest.setRespMesg("Pickup Time can not be Null");
                        return vehicleRequest;
                     } else if (!vehicleRequest.getAreaFrom().isEmpty() && vehicleRequest.getAreaFrom() != null) {
                        if (vehicleRequest.getToDate() == null) {
                           vehicleRequest.setRespCode(401);
                           vehicleRequest.setRespMesg("Drop Date can not be Null");
                           return vehicleRequest;
                        } else if (vehicleRequest.getDeliveryTime() == 0) {
                           vehicleRequest.setRespCode(401);
                           vehicleRequest.setRespMesg("Drop Time can not be Null");
                           return vehicleRequest;
                        } else if (!vehicleRequest.getAreaTo().isEmpty() && vehicleRequest.getAreaTo() != null) {
                           if (!vehicleRequest.getCustomerMobile().isEmpty() && vehicleRequest.getCustomerMobile() != null) {
                              if (!vehicleRequest.getFirstName().isEmpty() && vehicleRequest.getFirstName() != null) {
                                 if (!vehicleRequest.getLastName().isEmpty() && vehicleRequest.getLastName() != null) {
                                    if (vehicleRequest.getVehicleQuantity() == 0) {
                                       vehicleRequest.setRespCode(401);
                                       vehicleRequest.setRespMesg("Booking Quntity can not be zero");
                                       return vehicleRequest;
                                    } else if (vehicleRequest.getBookingPrice() == 0.0D) {
                                       vehicleRequest.setRespCode(401);
                                       vehicleRequest.setRespMesg("Per Day Rental can not be zero");
                                       return vehicleRequest;
                                    } else if (vehicleRequest.getDeliveryCharges() == 0) {
                                       vehicleRequest.setRespCode(401);
                                       vehicleRequest.setRespMesg("Delivery Charges can not be zero");
                                       return vehicleRequest;
                                    } else if (vehicleRequest.getReceivedAmount() == 0) {
                                       vehicleRequest.setRespCode(401);
                                       vehicleRequest.setRespMesg("Booking Amount can not be zero");
                                       return vehicleRequest;
                                    } else if (vehicleRequest.getBalenceAmount() == 0) {
                                       vehicleRequest.setRespCode(401);
                                       vehicleRequest.setRespMesg("Balence Amount can not be zero");
                                       return vehicleRequest;
                                    } else if (vehicleRequest.getBalenceAmount() == 0) {
                                       vehicleRequest.setRespCode(401);
                                       vehicleRequest.setRespMesg("Balence Amount can not be zero");
                                       return vehicleRequest;
                                    } else if (vehicleRequest.getTotalAmount() == 0) {
                                       vehicleRequest.setRespCode(401);
                                       vehicleRequest.setRespMesg("Total Amount can not be zero");
                                       return vehicleRequest;
                                    } else if (!vehicleRequest.getStatus().isEmpty() && vehicleRequest.getStatus() != null) {
                                       if (vehicleRequest.getStatus().equals("REQUESTED") && vehicleRequest.getModeOfPayment().isEmpty()) {
                                          vehicleRequest.setRespCode(401);
                                          vehicleRequest.setRespMesg("Payment Mode can not be Null");
                                          return vehicleRequest;
                                       } else {
                                          BookingDetails bookingDetails = this.bookingHelper.getVehicleDetailsById(vehicleRequest.getId());
                                          if (bookingDetails != null) {
                                             LocalDate pickupDate = Instant.ofEpochMilli(vehicleRequest.getFromDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                                             LocalDate dropDate = Instant.ofEpochMilli(vehicleRequest.getToDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                                             Long difference = ChronoUnit.DAYS.between(pickupDate, dropDate);
                                             VehicleName existVehicle = this.adminVehicleHelper.getVehicleNameByVehicleNameAndVehicleDetailType(vehicleRequest.getVehicleName(), vehicleRequest.getVehicleDetailsType());
                                             if (existVehicle != null) {
                                                if (existVehicle.getPriceLimit() > vehicleRequest.getBookingPrice()) {
                                                   vehicleRequest.setRespCode(401);
                                                   vehicleRequest.setRespMesg("Per Day Rental Price Cann't be Less then " + existVehicle.getPriceLimit());
                                                   return vehicleRequest;
                                                } else if (difference >= 0L) {
                                                   bookingDetails = this.bookingHelper.getUpdatedBookingDetailsByMissCallByObj(vehicleRequest, bookingDetails);
                                                   bookingDetails = this.bookingHelper.updateBookingDetails(bookingDetails);
                                                   String enqPara;
                                                   if (existVehicle.getVehicleType().equalsIgnoreCase("BIKE")) {
                                                      if (vehicleRequest.getStatus().equals("FOLLOWUP")) {
                                                         enqPara = this.commonHelper.fallowUpParameter(bookingDetails);
                                                         this.commonHelper.interaktApi(enqPara);
                                                      } else if (vehicleRequest.getStatus().equals("REQUESTED")) {
                                                         enqPara = this.commonHelper.bookingConfirmationParameters(bookingDetails);
                                                         this.commonHelper.interaktApi(enqPara);
                                                      } else if (vehicleRequest.getStatus().equals("ENQUIRY")) {
                                                         enqPara = this.commonHelper.enquiryParameters(bookingDetails);
                                                         String apiResp = this.commonHelper.interaktApi(enqPara);
                                                         System.out.println(enqPara);
                                                         System.out.println(apiResp);
                                                      }
                                                   } else if (existVehicle.getVehicleType().equalsIgnoreCase("CAR")) {
                                                      if (vehicleRequest.getStatus().equals("FOLLOWUP")) {
                                                         enqPara = this.commonHelper.fallowUpParameter(bookingDetails);
                                                         this.commonHelper.interaktApi(enqPara);
                                                      } else if (vehicleRequest.getStatus().equals("REQUESTED")) {
                                                         enqPara = this.commonHelper.bookingConfirmationParametersForCar(bookingDetails);
                                                         this.commonHelper.interaktApi(enqPara);
                                                      } else if (vehicleRequest.getStatus().equals("ENQUIRY")) {
                                                         enqPara = this.commonHelper.enquiryParametersForCar(bookingDetails);
                                                         this.commonHelper.interaktApi(enqPara);
                                                      }
                                                   }

                                                   BookingDetailsHistory bookingHistory = this.bookingHistoryHelper.getVehicleDetailsHistoryByObj(bookingDetails);
                                                   this.bookingHistoryHelper.saveBookingDetailsHistory(bookingHistory);
                                                   vehicleRequest.setRespCode(200);
                                                   vehicleRequest.setRespMesg("Booking request Successfull");
                                                   return vehicleRequest;
                                                } else {
                                                   vehicleRequest.setRespCode(401);
                                                   vehicleRequest.setRespMesg("Check Pick Date & Drop Date");
                                                   return vehicleRequest;
                                                }
                                             } else {
                                                vehicleRequest.setRespCode(401);
                                                vehicleRequest.setRespMesg("Vehicle Not Found");
                                                return vehicleRequest;
                                             }
                                          } else {
                                             return vehicleRequest;
                                          }
                                       }
                                    } else {
                                       vehicleRequest.setRespCode(401);
                                       vehicleRequest.setRespMesg("Status can not be Null");
                                       return vehicleRequest;
                                    }
                                 } else {
                                    vehicleRequest.setRespCode(401);
                                    vehicleRequest.setRespMesg("Last Name can not be Null");
                                    return vehicleRequest;
                                 }
                              } else {
                                 vehicleRequest.setRespCode(401);
                                 vehicleRequest.setRespMesg("First Name can not be Null");
                                 return vehicleRequest;
                              }
                           } else {
                              vehicleRequest.setRespCode(401);
                              vehicleRequest.setRespMesg("Customer Mobile no. can not be Null");
                              return vehicleRequest;
                           }
                        } else {
                           vehicleRequest.setRespCode(401);
                           vehicleRequest.setRespMesg("Delivery Point can not be Null");
                           return vehicleRequest;
                        }
                     } else {
                        vehicleRequest.setRespCode(401);
                        vehicleRequest.setRespMesg("Pickup Point can not be Null");
                        return vehicleRequest;
                     }
                  } else {
                     vehicleRequest.setRespCode(401);
                     vehicleRequest.setRespMesg("City can not be Null");
                     return vehicleRequest;
                  }
               } else {
                  vehicleRequest.setRespCode(401);
                  vehicleRequest.setRespMesg("Vehicle Name can not be Null");
                  return vehicleRequest;
               }
            } else {
               vehicleRequest.setRespCode(401);
               vehicleRequest.setRespMesg("Vehicle Name can not be Null");
               return vehicleRequest;
            }
         } else {
            vehicleRequest.setRespCode(401);
            vehicleRequest.setRespMesg("Vehicle Type can not be Null");
            return vehicleRequest;
         }
      } else {
         vehicleRequest.setRespCode(401);
         vehicleRequest.setRespMesg("Enquary Source can not be Null");
         return vehicleRequest;
      }
   }

   @Transactional
   public VehicleRequestObject makeRefund(Request<VehicleRequestObject> vehicleRequestObject) throws BizException, Exception {
      VehicleRequestObject vehicleRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      this.bookingHelper.validateBookingRequest(vehicleRequest);
      BookingDetails existsBooking = this.bookingHelper.getVehicleDetailsById(vehicleRequest.getId());
      if (existsBooking != null) {
         existsBooking.setStatus("REFUND");
         existsBooking.setRefundDate(new Date());
         this.bookingHelper.updateBookingDetails(existsBooking);
         BookingDetailsHistory bookingHistory = this.bookingHistoryHelper.getVehicleDetailsHistoryByObj(existsBooking);
         this.bookingHistoryHelper.saveBookingDetailsHistory(bookingHistory);
         vehicleRequest.setRespCode(200);
         vehicleRequest.setRespMesg("Successfully Refunded");
         return vehicleRequest;
      } else {
         vehicleRequest.setRespCode(401);
         vehicleRequest.setRespMesg("Booking Not Found");
         return vehicleRequest;
      }
   }

   @Transactional
   public VehicleRequestObject bookingApprovedOrReject(Request<VehicleRequestObject> vehicleRequestObject) throws BizException, Exception {
      VehicleRequestObject vehicleRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      this.bookingHelper.validateBookingRequest(vehicleRequest);
      BookingDetails existsBooking = this.bookingHelper.getVehicleDetailsById(vehicleRequest.getId());
      if (existsBooking != null) {
         Users users = this.userHelper.getUserDetailsByUserId(vehicleRequest.getUserId());
         if (users != null) {
            vehicleRequest.setVehicleOwnner(users.getFullName());
         }

         if (vehicleRequest.getStatus().equals("BOOKED")) {
            existsBooking.setStatus(vehicleRequest.getStatus());
            existsBooking.setVehicleNumber(vehicleRequest.getVehicleNumber());
            existsBooking.setVehicleOwnnerType(vehicleRequest.getVehicleOwnnerType());
            existsBooking.setDeliveryExecutiveId(vehicleRequest.getDeliveryExecutiveId());
            existsBooking.setDeliveryExecutiveName(vehicleRequest.getDeliveryExecutiveName());
            existsBooking.setVehicleOwnner(vehicleRequest.getVehicleOwnner());
            existsBooking.setUpdatedAt(new Date());
            existsBooking = this.bookingHelper.updateBookingDetails(existsBooking);
            String poc = this.commonHelper.forPocEzee(existsBooking);
            this.commonHelper.interaktApi(poc);
         } else if (vehicleRequest.getStatus().equals("REJECTED")) {
            existsBooking.setStatus("MOB_REJECTED");
            existsBooking.setUpdatedAt(new Date());
            existsBooking = this.bookingHelper.updateBookingDetails(existsBooking);
         }

         BookingDetailsHistory bookingHistory = this.bookingHistoryHelper.getVehicleDetailsHistoryByObj(existsBooking);
         this.bookingHistoryHelper.saveBookingDetailsHistory(bookingHistory);
         vehicleRequest.setRespCode(200);
         vehicleRequest.setRespMesg("Successfully Booked");
      } else {
         vehicleRequest.setRespCode(400);
         vehicleRequest.setRespMesg("Booking Not Found");
      }

      return vehicleRequest;
   }

   @Transactional
   public VehicleRequestObject bookingAssigned(Request<VehicleRequestObject> vehicleRequestObject) throws BizException, Exception {
      VehicleRequestObject vehicleRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      this.bookingHelper.validateBookingRequest(vehicleRequest);
      VehicleDetails existsVehicle = this.vehicleHelper.getBikeDetailsByBikeNumber(vehicleRequest.getVehicleName(), vehicleRequest.getUserId(), vehicleRequest.getVehicleDetailsType());
      BookingDetails existsBooking = this.bookingHelper.getVehicleDetailsById(vehicleRequest.getId());
      if (existsBooking != null) {
         if (existsVehicle != null) {
            int availableQuantity = existsVehicle.getVehicleQuantity();
            int requestedQuantity = vehicleRequest.getVehicleQuantity();
            int reminingQuantity = availableQuantity - requestedQuantity;
            if (availableQuantity >= requestedQuantity) {
               existsBooking.setUserId(vehicleRequest.getUserId());
               existsBooking.setStatus("BOOKED");
               existsBooking.setUpdatedAt(new Date());
               existsBooking = this.bookingHelper.updateBookingDetails(existsBooking);
               existsVehicle.setVehicleQuantity(reminingQuantity);
               this.vehicleHelper.updateVehicle(existsVehicle);
               BookingDetailsHistory bookingHistory = this.bookingHistoryHelper.getVehicleDetailsHistoryByObj(existsBooking);
               this.bookingHistoryHelper.saveBookingDetailsHistory(bookingHistory);
               String poc = this.commonHelper.forPocEzee(existsBooking);
               this.commonHelper.interaktApi(poc);
               vehicleRequest.setRespCode(200);
               vehicleRequest.setRespMesg("Successfully Assigned");
            } else {
               vehicleRequest.setRespCode(400);
               vehicleRequest.setRespMesg("Quantity Less ");
            }
         } else {
            vehicleRequest.setRespCode(400);
            vehicleRequest.setRespMesg("Vehicle not available Not Found");
         }
      } else {
         vehicleRequest.setRespCode(400);
         vehicleRequest.setRespMesg("Booking Not Found");
      }

      return vehicleRequest;
   }

   public VehicleRequestObject cancelBooking(Request<VehicleRequestObject> vehicleRequestObject) throws BizException, Exception {
      VehicleRequestObject vehicleRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      this.bookingHelper.validateBookingRequest(vehicleRequest);
      BookingDetails existsBooking = this.bookingHelper.getVehicleDetailsById(vehicleRequest.getId());
      if (existsBooking != null) {
         existsBooking.setStatus("CANCELREQUEST");
         existsBooking.setCancelationRequestNumber(vehicleRequest.getCustomerMobile().substring(0, 5) + StringUtils.substring(RandomStringUtils.random(64, false, true), 0, 1));
         existsBooking.setCancelationFor(vehicleRequest.getCancelationFor());
         existsBooking.setCancelationReason(vehicleRequest.getCancelationReason());
         existsBooking.setCancelBy(vehicleRequest.getCancelBy());
         existsBooking.setCancelByName(vehicleRequest.getCancelByName());
         existsBooking.setCancelDate(new Date());
         this.bookingHelper.updateBookingDetails(existsBooking);
         BookingDetailsHistory bookingHistory = this.bookingHistoryHelper.getVehicleDetailsHistoryByObj(existsBooking);
         this.bookingHistoryHelper.saveBookingDetailsHistory(bookingHistory);
         vehicleRequest.setRespCode(200);
         vehicleRequest.setRespMesg("Cancel Request Successfully");
         return vehicleRequest;
      } else {
         vehicleRequest.setRespCode(401);
         vehicleRequest.setRespMesg("Booking Not Found");
         return vehicleRequest;
      }
   }

   public VehicleRequestObject approveOrRejectCancelation(Request<VehicleRequestObject> vehicleRequestObject) throws BizException, Exception {
      VehicleRequestObject vehicleRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      this.bookingHelper.validateBookingRequest(vehicleRequest);
      BookingDetails existsBooking = this.bookingHelper.getVehicleDetailsById(vehicleRequest.getId());
      if (existsBooking != null) {
         if (vehicleRequest.getRequestFor().equalsIgnoreCase("APPROVE")) {
            existsBooking.setStatus("CANCEL");
         } else if (vehicleRequest.getRequestFor().equalsIgnoreCase("REJECT")) {
            existsBooking.setStatus("CANCELREJECT");
         }

         existsBooking.setUpdatedAt(new Date());
         this.bookingHelper.updateBookingDetails(existsBooking);
         BookingDetailsHistory bookingHistory = this.bookingHistoryHelper.getVehicleDetailsHistoryByObj(existsBooking);
         this.bookingHistoryHelper.saveBookingDetailsHistory(bookingHistory);
         vehicleRequest.setRespCode(200);
         vehicleRequest.setRespMesg("Cancel Successfully");
         return vehicleRequest;
      } else {
         vehicleRequest.setRespCode(401);
         vehicleRequest.setRespMesg("Request Not Found");
         return vehicleRequest;
      }
   }

   @Transactional
   public VehicleRequestObject changeDeliverStatus(Request<VehicleRequestObject> vehicleRequestObject) throws BizException, Exception {
      VehicleRequestObject vehicleRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      this.bookingHelper.validateBookingRequest(vehicleRequest);
      BookingDetails existsBooking = this.bookingHelper.getVehicleDetailsById(vehicleRequest.getId());
      if (existsBooking != null) {
         existsBooking.setStatus("DELIVERD");
         existsBooking = this.bookingHelper.updateBookingDetails(existsBooking);
         BookingDetailsHistory bookingHistory = this.bookingHistoryHelper.getVehicleDetailsHistoryByObj(existsBooking);
         this.bookingHistoryHelper.saveBookingDetailsHistory(bookingHistory);
         vehicleRequest.setRespCode(200);
         vehicleRequest.setRespMesg("Successfully Deliverd");
      } else {
         vehicleRequest.setRespCode(400);
         vehicleRequest.setRespMesg("Not Found");
      }

      return vehicleRequest;
   }

   public List<BookingDetails> getRejectedCancelList(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> bookingList = this.bookingHelper.getRejectedCancelList(bookingRequest);
      return bookingList;
   }

   public List<BookingDetails> getRefundedList(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> bookingList = this.bookingHelper.getRefundedList(bookingRequest);
      return bookingList;
   }

   public List<BookingDetails> getBookingDetails(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> bookingList = this.bookingHelper.getBookingDetails(bookingRequest);
      return bookingList;
   }

   public List<BookingDetails> getbookingByPickupDateAndVehicleType(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> bookingList = this.bookingHelper.getbookingByPickupDateAndVehicleType(bookingRequest);
      return bookingList;
   }

   public List<BookingDetails> getBookingDetailsBySellerId(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> bookingList = this.bookingHelper.getBookingDetailsBySellerId(bookingRequest);
      return bookingList;
   }

   public List<BookingDetails> getBookingDetailsByUserId(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> bookingList = this.bookingHelper.getBookingDetailsByUserId(bookingRequest);
      return bookingList;
   }

   public List<BookingDetails> getTotalEarningByUserId(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> bookingList = this.bookingHelper.getTotalEarningByUserId(bookingRequest);
      return bookingList;
   }

   public List<VehicleDetails> getUserByVehicleName(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<VehicleDetails> vendorList = this.bookingHelper.getUserByVehicleName(bookingRequest);
      return vendorList;
   }

   public List<BookingDetails> getbookingVehicleByPickupDate(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> pickupVehicle = this.bookingHelper.getbookingVehicleByPickupDate(bookingRequest);
      return pickupVehicle;
   }

   public List<BookingDetails> getbookingVehicleByDropDate(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> pickupVehicle = this.bookingHelper.getbookingVehicleByDropDate(bookingRequest);
      return pickupVehicle;
   }
   
   
   public List<BookingDetails> getbookingVehicleByVehicleDetailsType(Request<VehicleRequestObject> vehicleRequestObject) {
	      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
	      List<BookingDetails> pickupVehicle = this.bookingHelper.getbookingVehicleByVehicleDetailsType(bookingRequest);
	      return pickupVehicle;
	   }
   
   
   public List<BookingDetails> getbookedVehicle(Request<VehicleRequestObject> vehicleRequestObject) {
	      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
	      List<BookingDetails> pickupVehicle = this.bookingHelper.getbookedVehicle(bookingRequest);
	      return pickupVehicle;
	   }
   

   public VehicleRequestObject vehicleReceive(Request<VehicleRequestObject> vehicleRequestObject) throws BizException, Exception {
      VehicleRequestObject vehicleRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      this.bookingHelper.validateBookingRequest(vehicleRequest);
      BookingDetails existsBooking = this.bookingHelper.getVehicleDetailsById(vehicleRequest.getId());
      VehicleDetails existsVehicle = this.vehicleHelper.getBikeDetailsByBikeNumber(existsBooking.getVehicleName(), existsBooking.getUserId(), vehicleRequest.getVehicleDetailsType());
      if (existsBooking != null && existsVehicle != null) {
         existsBooking.setStatus("APPROVED");
         existsBooking = this.bookingHelper.updateBookingDetails(existsBooking);
         existsVehicle.setVehicleQuantity(existsVehicle.getVehicleQuantity() + existsBooking.getVehicleQuantity());
         this.vehicleHelper.updateVehicle(existsVehicle);
         BookingDetailsHistory bookingHistory = this.bookingHistoryHelper.getVehicleDetailsHistoryByObj(existsBooking);
         this.bookingHistoryHelper.saveBookingDetailsHistory(bookingHistory);
         vehicleRequest.setRespCode(200);
         vehicleRequest.setRespMesg("Successfully Receive");
      } else {
         vehicleRequest.setRespCode(400);
         vehicleRequest.setRespMesg("Not Found");
      }

      return vehicleRequest;
   }

   public VehicleRequestObject changeReBookingStatus(Request<VehicleRequestObject> vehicleRequestObject) throws BizException, Exception {
      VehicleRequestObject vehicleRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      this.bookingHelper.validateBookingRequest(vehicleRequest);
      BookingDetails existsBooking = this.bookingHelper.getVehicleDetailsById(vehicleRequest.getId());
      if (existsBooking != null) {
         existsBooking.setReBookingStatus("BOOKED");
         this.bookingHelper.updateBookingDetails(existsBooking);
         BookingDetailsHistory bookingHistory = this.bookingHistoryHelper.getVehicleDetailsHistoryByObj(existsBooking);
         this.bookingHistoryHelper.saveBookingDetailsHistory(bookingHistory);
         vehicleRequest.setRespCode(200);
         vehicleRequest.setRespMesg("Booed Successfully");
         return vehicleRequest;
      } else {
         vehicleRequest.setRespCode(400);
         vehicleRequest.setRespMesg("Not Found");
         return vehicleRequest;
      }
   }

   public VehicleRequestObject bookingDetails(Request<VehicleRequestObject> vehicleRequestObject) throws BizException, Exception {
      VehicleRequestObject vehicleRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      this.bookingHelper.validateBookingRequest(vehicleRequest);
      List<BookingDetails> dayBookedCount = this.bookingHelper.getBookingAmountOfTodays(vehicleRequest);
      if (dayBookedCount != null) {
         Object dbc = dayBookedCount.get(0);
         vehicleRequest.setDayBookCount(dbc.toString());
      } else {
         vehicleRequest.setDayBookCount("00");
      }

      List<BookingDetails> dayBookAmount = this.bookingHelper.getBookingAmountOfWeek(vehicleRequest);
      Object dra = dayBookAmount.get(0);
      if (dra != null) {
         vehicleRequest.setWeekBookCount(dra.toString());
      } else {
         vehicleRequest.setWeekBookCount("00");
      }

      List<BookingDetails> weekBookCount = this.bookingHelper.getBookingAmountOfMonth(vehicleRequest);
      Object wbc = weekBookCount.get(0);
      if (wbc != null) {
         vehicleRequest.setMonthBookCount(wbc.toString());
      } else {
         vehicleRequest.setMonthBookCount("0");
      }
      
      if(vehicleRequest.getMemberType().equalsIgnoreCase("CARCHATSALE") || vehicleRequest.getMemberType().equalsIgnoreCase("WATERSPORTSCHATSALE")) {
    	 
    	  List<BookingDetails> weekBookAmount = this.bookingHelper.getBookingAmountOfTodaysForWATERSPORTSCHATSALE(vehicleRequest);
          Object wba = weekBookAmount.get(0);
          if (wba != null) {
             vehicleRequest.setDayReceivedAmount(wba.toString());
          } else {
             vehicleRequest.setDayReceivedAmount("00");
          }

          List<BookingDetails> enquiryCount = this.bookingHelper.getBookingAmountOfWeekForWATERSPORTSCHATSALE(vehicleRequest);
          Object ec = enquiryCount.get(0);
          if (ec != null) {
             vehicleRequest.setWeekBookAmount(ec.toString());
          } else {
             vehicleRequest.setWeekBookAmount("00");
          }

          List<BookingDetails> followUpCount = this.bookingHelper.getBookingAmountOfMonthForWATERSPORTSCHATSALE(vehicleRequest);
          Object fc = followUpCount.get(0);
          if (ec != null) {
             vehicleRequest.setMonthBookAmount(fc.toString());
          } else {
             vehicleRequest.setMonthBookAmount("00");
          }

      

      } else {
    	  
    	  vehicleRequest.setMemberType("SUPERADMIN");
    	  
    	  List<BookingDetails> weekBookAmount = this.bookingHelper.getBookingAmountOfTodays(vehicleRequest);
          Object wba = weekBookAmount.get(0);
          if (wba != null) {
             vehicleRequest.setDayReceivedAmount(wba.toString());
          } else {
             vehicleRequest.setDayReceivedAmount("00");
          }

          List<BookingDetails> enquiryCount = this.bookingHelper.getBookingAmountOfWeek(vehicleRequest);
          Object ec = enquiryCount.get(0);
          if (ec != null) {
             vehicleRequest.setWeekBookAmount(ec.toString());
          } else {
             vehicleRequest.setWeekBookAmount("00");
          }

          List<BookingDetails> followUpCount = this.bookingHelper.getBookingAmountOfMonth(vehicleRequest);
          Object fc = followUpCount.get(0);
          if (ec != null) {
             vehicleRequest.setMonthBookAmount(fc.toString());
          } else {
             vehicleRequest.setMonthBookAmount("00");
          }

      }
      
      
      vehicleRequest.setRespCode(200);
      return vehicleRequest;
   }

   
   
   public VehicleRequestObject penddingBookingDetails(Request<VehicleRequestObject> vehicleRequestObject) throws BizException, Exception {
      VehicleRequestObject vehicleRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      this.bookingHelper.validateBookingRequest(vehicleRequest);
      List<BookingDetails> todaysCount = this.bookingHelper.penddingBookingDetails(vehicleRequest, "TODAY_PENDING_COUNT");
      Object tc = todaysCount.get(0);
      if (tc != null) {
         vehicleRequest.setTodaysCount((Long)tc);
      } else {
         vehicleRequest.setTodaysCount(0L);
      }

      List<BookingDetails> tomorrowCount = this.bookingHelper.penddingBookingDetails(vehicleRequest, "TOMORROW_PENDING_COUNT");
      Object toc = tomorrowCount.get(0);
      if (toc != null) {
         vehicleRequest.setTomorrowCount((Long)toc);
      } else {
         vehicleRequest.setTomorrowCount(0L);
      }

      List<BookingDetails> afterTomorrowCount = this.bookingHelper.penddingBookingDetails(vehicleRequest, "AFTER_TOMORROW_PENDING_COUNT");
      Object atc = afterTomorrowCount.get(0);
      if (atc != null) {
         vehicleRequest.setAfterTomorrowCont((Long)atc);
      } else {
         vehicleRequest.setAfterTomorrowCont(0L);
      }

      List<BookingDetails> weeklyCount = this.bookingHelper.penddingBookingDetails(vehicleRequest, "WEEKLY_PENDING_COUNT");
      Object wc = weeklyCount.get(0);
      if (wc != null) {
         vehicleRequest.setWeeklyCount((Long)wc);
      } else {
         vehicleRequest.setWeeklyCount(0L);
      }

      List<BookingDetails> halfMonthCount = this.bookingHelper.penddingBookingDetails(vehicleRequest, "HALF_MONTH_PENDING_COUNT");
      Object hmc = halfMonthCount.get(0);
      if (hmc != null) {
         vehicleRequest.setHalfMonthCount((Long)hmc);
      } else {
         vehicleRequest.setHalfMonthCount(0L);
      }

      List<BookingDetails> monthlyCount = this.bookingHelper.penddingBookingDetails(vehicleRequest, "MONTH_PENDING_COUNT");
      Object mc = monthlyCount.get(0);
      if (mc != null) {
         vehicleRequest.setMonthlyCount((Long)mc);
      } else {
         vehicleRequest.setMonthlyCount(0L);
      }

      vehicleRequest.setRespCode(200);
      return vehicleRequest;
   }

   public List<BookingDetails> cancelRequestList(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> pickupVehicle = this.bookingHelper.cancelRequestList(bookingRequest);
      return pickupVehicle;
   }

   public List<BookingDetails> cancelBookingDetails(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> pickupVehicle = this.bookingHelper.cancelBookingDetails(bookingRequest);
      return pickupVehicle;
   }

   public List<BookingDetails> completedBookingDetails(Request<VehicleRequestObject> vehicleRequestObject) throws Exception {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> completeBooking = this.bookingHelper.completedBookingDetails(bookingRequest);
      return completeBooking;
   }

   public List<BookingDetails> bookingDetailsGroupByUserName(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> completeBooking = this.bookingHelper.bookingDetailsGroupByUserName(bookingRequest);
      return completeBooking;
   }

   public List<BookingDetails> inprocessBookingDetails(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> completeBooking = this.bookingHelper.inprocessBookingDetails(bookingRequest);
      return completeBooking;
   }

   public List<BookingDetails> bookingDetailsGroupByUserNameAndCustomeDate(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> completeBooking = this.bookingHelper.bookingDetailsGroupByUserNameAndCustomeDate(bookingRequest);
      return completeBooking;
   }

   public List<BookingDetailsHistory> getUpdateBookingDetailsHistory(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetailsHistory> completeBooking = this.bookingHelper.getUpdateBookingDetailsHistory(bookingRequest);
      return completeBooking;
   }

   public List<BookingDetails> enqiryDetails(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> completeBooking = this.bookingHelper.enqiryDetails(bookingRequest);
      return completeBooking;
   }

   public List<BookingDetails> followUpDetails(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> completeBooking = this.bookingHelper.followUpDetails(bookingRequest);
      return completeBooking;
   }

   public List<BookingDetails> lostDetails(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> completeBooking = this.bookingHelper.lostDetails(bookingRequest);
      return completeBooking;
   }

   public List<BookingDetails> getMissedCallList(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> completeBooking = this.bookingHelper.getMissedCallList(bookingRequest);
      return completeBooking;
   }

   public List<BookingDetails> bookingDetailsByCreatedBy(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> bookingDetails = this.bookingHelper.bookingDetailsByCreatedBy(bookingRequest);
      return bookingDetails;
   }

   public List<BookingDetails> getVehicleDetailsBeforeAIM(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> bookingDetails = this.bookingHelper.getVehicleDetailsBeforeAIM(bookingRequest);
      return bookingDetails;
   }

   public List<BookingDetails> bookingDetailsByStatusChangeWithOwnnerDetails(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> bookingDetails = this.bookingHelper.bookingDetailsByStatusChangeWithOwnnerDetails(bookingRequest);
      return bookingDetails;
   }

   public List<BookingDetails> getNotAssignedBookingForNotification(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> bookingDetails = this.bookingHelper.getNotAssignedBookingForNotification(bookingRequest);
      return bookingDetails;
   }

   public List<BookingDetails> helpDeskList(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> bookingDetails = this.bookingHelper.helpDeskList(bookingRequest);
      return bookingDetails;
   }

   public VehicleRequestObject totalAmountByAgentAndDate(Request<VehicleRequestObject> vehicleRequestObject) throws BizException, Exception {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<Object> bookingDetails = this.bookingHelper.totalAmountByAgentAndDate(bookingRequest);
      System.out.println("Count : " + bookingDetails);

      List bonus;
      for(int i = 0; i < bookingDetails.size(); ++i) {
         new ArrayList();
         System.out.println("Value : " + bookingDetails.get(i));
         bonus = this.adminVehicleHelper.getBonusAmountByEarning((Long)bookingDetails.get(i));
      }

      Long totalBonus = 0L;
      Long totalBonus232 = 0L;
      bonus = null;

      for(int i = 0; i < bookingDetails.size(); ++i) {
         System.out.println("earning : " + bookingDetails);
         List<BonusSlab> bonusSlabList = this.adminVehicleHelper.getBonusAmountByEarning((Long)bookingDetails.get(i));
         if (!bonusSlabList.isEmpty()) {
            Object bonus1 = bonusSlabList.get(0);
            totalBonus232 = (Long)bonus1;
         } else {
            totalBonus232 = 0L;
         }

         totalBonus = totalBonus + totalBonus232;
      }

      bookingRequest.setBonusAmount(totalBonus);
      bookingRequest.setTotalAmount(0);
      bookingRequest.setRespCode(200);
      bookingRequest.setRespMesg("Successfully fetch");
      return bookingRequest;
   }

   public List<Object[]> getBonusDetails(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject vehicleRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      vehicleRequest.setBonusType("DAILY");
      List<Object[]> bookingDetails = this.bookingHelper.getBonusDetails(vehicleRequest, this.todayDate, this.tomorrowDate);
      return bookingDetails;
   }

   public List<Object[]> getBonusDetailsOfMonth(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject vehicleRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      vehicleRequest.setBonusType("MONTH");
      List<Object[]> bookingDetails = this.bookingHelper.getBonusDetails(vehicleRequest, this.firstDateMonth, this.lastDateMonth);
      return bookingDetails;
   }

   public VehicleRequestObject getBonusDetailsByCreatedBy(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject vehicleRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      Long totalBonus = 0L;
      Long totalSaleAmount = 0L;
      LocalDate currentDate = LocalDate.now();
      LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
      Date firstDate = Date.from(firstDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
      int lastDayOfMonth = firstDayOfMonth.lengthOfMonth();
      int todayDate = currentDate.getDayOfMonth();
      int lastCount = currentDate.getDayOfMonth() + 1;
      int hi = lastDayOfMonth - todayDate;

      for(int dayCount = 1; dayCount <= hi; ++dayCount) {
         LocalDate nextDay = firstDayOfMonth.plusDays((long)lastCount);
         Date tomorrowDate = Date.from(nextDay.atStartOfDay(ZoneId.systemDefault()).toInstant());
         Object bookingDetails = this.bookingHelper.getTotalSaleAmountByCreatedBy(vehicleRequest, firstDate, tomorrowDate);
         if (bookingDetails != null) {
            Long earning = (Long)bookingDetails;
            vehicleRequest.setTotalEarning(earning);
         } else {
            vehicleRequest.setTotalEarning(0L);
         }

         vehicleRequest.setBonusType("DAILY");
         BonusSlab bonus = this.adminVehicleHelper.getBonusAmountByTotalSale(vehicleRequest);
         if (bonus != null) {
            System.out.println(tomorrowDate + "  ,  Bonus id : " + bonus.getId() + " , Total earning  : " + vehicleRequest.getTotalEarning() + " Bonus : " + bonus.getBonusAmount());
            totalBonus = totalBonus + bonus.getBonusAmount();
         }
      }

      System.out.println(totalBonus + " :gg : " + vehicleRequest.getTotalEarning());
      return vehicleRequest;
   }

   public List<BookingDetails> unknownDataList(Request<VehicleRequestObject> bookingRequest) {
      List<BookingDetails> bookingDetails = this.bookingHelper.unknownDataList(bookingRequest);
      return bookingDetails;
   }

   public List<BookingDetails> getAlreadyExistsBookingWithSameNUmber(Request<VehicleRequestObject> vehicleRequestObject) {
      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      List<BookingDetails> bookingList = this.bookingHelper.getBookingDetailsByCustomerMobileNo(bookingRequest);
      return bookingList;
   }

   public VehicleRequestObject vehicleBookingByApp(Request<VehicleRequestObject> vehicleRequestObject) throws BizException, Exception {
      VehicleRequestObject vehicleRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
      this.bookingHelper.validateBookingRequest(vehicleRequest);
      List<BookingDetails> bookingList = this.bookingHelper.getBookingDetailsByCustomerMobileNo(vehicleRequest);
      if (!bookingList.isEmpty()) {
         vehicleRequest.setRespCode(401);
         vehicleRequest.setRespMesg("Booking With Same Mobile No. Already Exists");
         return vehicleRequest;
      } else {
         BookingDetails bookingDetails = new BookingDetails();
         bookingDetails.setCustomerMobile(vehicleRequest.getCustomerMobile());
         bookingDetails.setStatus(vehicleRequest.getEntryType());
         bookingDetails.setCreatedBy(vehicleRequest.getCreatedBy());
         bookingDetails.setCreatedbyName(vehicleRequest.getCreatedbyName());
         bookingDetails.setCreatedAt(new Date());
         if (vehicleRequest.getCountryCode().isEmpty()) {
            bookingDetails.setCountryCode("+91");
         } else {
            bookingDetails.setCountryCode(vehicleRequest.getCountryCode());
         }

         this.bookingHelper.saveBookingDetails(bookingDetails);
         vehicleRequest.setRespCode(200);
         vehicleRequest.setRespMesg("Booking Done");
         return vehicleRequest;
      }
   }
}