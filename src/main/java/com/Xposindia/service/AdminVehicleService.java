package com.Xposindia.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Xposindia.constant.Constant;
import com.Xposindia.entities.BonusSlab;
import com.Xposindia.entities.BookingDetails;
import com.Xposindia.entities.UsersArea;
import com.Xposindia.entities.UsersCity;
import com.Xposindia.entities.VehicleBrand;
import com.Xposindia.entities.VehicleDetails;
import com.Xposindia.entities.VehicleName;
import com.Xposindia.expections.BizException;
import com.Xposindia.helper.AdminVehicleHelper;
import com.Xposindia.helper.BookingHelper;
import com.Xposindia.helper.CommonHelper;
import com.Xposindia.helper.VehicleHelper;
import com.Xposindia.object.request.VehicleRequestObject;
import com.Xposindia.object.request.Request;
import com.Xposindia.object.request.UserRequestObject;

@Service
public class AdminVehicleService {

	@Autowired
	private AdminVehicleHelper adminVehicleHelper;
	
	@Autowired
	private VehicleHelper vehicleHelper;
	
	@Autowired
	private BookingHelper bookingHelper;
	
	@Autowired
	private CommonHelper commonHelper;
	

	public VehicleRequestObject addVehicleBrand(Request<VehicleRequestObject> vehicleRequestObject)
			throws BizException, Exception {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		adminVehicleHelper.validateVehicleRequest(vehicleRequest);
		VehicleBrand existsVehicleBrand = adminVehicleHelper
				.getVehicleBrandByVehicleBrand(vehicleRequest.getVehicleBrand(), vehicleRequest.getVehicleType());
		if (existsVehicleBrand == null) {

			VehicleBrand vehicleBrand = adminVehicleHelper.getVehicleBrandByReqObj(vehicleRequest);
			vehicleBrand = adminVehicleHelper.saveVehicleBrand(vehicleBrand);

			vehicleRequest.setRespCode(Constant.SUCCESS_CODE);
			vehicleRequest.setRespMesg("Vehicle Brand Added");
		} else {
			vehicleRequest.setRespCode(Constant.FAILED_REQUEST_CODE);
			vehicleRequest.setRespMesg("Vehicle Brand Already Exists");
		}
		return vehicleRequest;
	}
	
	public List<VehicleBrand> getVehicleBrandList(Request<VehicleRequestObject> vehicleRequestObject) {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		List<VehicleBrand> vehicleBrandList = adminVehicleHelper.getVehicleBrandList(vehicleRequest);
		return vehicleBrandList;
	}
	
	
	public List<VehicleBrand> getAllVehicleBrandList(Request<VehicleRequestObject> vehicleRequestObject) {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		List<VehicleBrand> vehicleBrandList = adminVehicleHelper.getAllVehicleBrandList(vehicleRequest);
		return vehicleBrandList;
	}
	
	@Transactional
	public VehicleRequestObject deleteVehicleBrand(Request<VehicleRequestObject> vehicleRequestObject) throws BizException {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		adminVehicleHelper.validateVehicleRequest(vehicleRequest);
		VehicleBrand vehicleBrand = adminVehicleHelper.getVehicleBrandById(vehicleRequest.getId());
		if(vehicleBrand != null) {
			adminVehicleHelper.deleteVehicleBrand(vehicleBrand);
			
			vehicleRequest.setRespCode(Constant.SUCCESS_CODE);
			vehicleRequest.setRespMesg("Delete Successfully");
			return vehicleRequest;
		}else {
			vehicleRequest.setRespCode(Constant.FAILED_REQUEST_CODE);
			vehicleRequest.setRespMesg("Vehicle Brand Not Exists");
			return vehicleRequest;
		}
	}

	public VehicleRequestObject addVehicleName(Request<VehicleRequestObject> vehicleRequestObject)
			throws BizException, Exception {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		adminVehicleHelper.validateVehicleRequest(vehicleRequest);

		VehicleName existsVehicleName = adminVehicleHelper.getVehicleNameByVehicleName(vehicleRequest.getVehicleBrand(), vehicleRequest.getVehicleName(), vehicleRequest.getVehicleDetailsType());
		if (existsVehicleName == null) {

			VehicleName vehicleName = adminVehicleHelper.getVehicleNameByReqObj(vehicleRequest);
			vehicleName = adminVehicleHelper.saveVehicleName(vehicleName);

			vehicleRequest.setRespCode(Constant.SUCCESS_CODE);
			vehicleRequest.setRespMesg("Vehicle Name Added");
		} else {

			vehicleRequest.setRespCode(Constant.FAILED_REQUEST_CODE);
			vehicleRequest.setRespMesg("Vehicle Name Already Exists");
		}
		return vehicleRequest;
	}

//	public List<VehicleName> getVehicleNameList(Request<VehicleRequestObject> vehicleRequestObject) {
//		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
//		List<VehicleName> vehicleNameList = adminVehicleHelper.getVehicleNameList(vehicleRequest);
//		return vehicleNameList;
//	}
	
	
	
	public List<VehicleName> vehicleNameByType(Request<VehicleRequestObject> vehicleRequestObject) throws IOException {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		List<VehicleName> vehicleNameList = adminVehicleHelper.vehicleNameByType(vehicleRequest);
		return vehicleNameList;
	}
	
	
	public List<VehicleName> getGroupVehicleNameList(Request<VehicleRequestObject> vehicleRequestObject) throws IOException {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		
//		adminVehicleHelper.CalculationByDistance(28.569588, 77.323109, 28.628151, 77.367783);
		
		List<VehicleName> vehicleNameList = adminVehicleHelper.getGroupVehicleNameList(vehicleRequest);
		return vehicleNameList;
	}
	
	public List<VehicleName> getVehicleNameListByVehicleName(Request<VehicleRequestObject> vehicleRequestObject) {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		List<VehicleName> vehicleNameList = adminVehicleHelper.getVehicleNameListByVehicleName(vehicleRequest);
//		System.out.println(vehicleRequest.getVehicleDetailsType()+"  "+vehicleRequest.getVehicleName());
		return vehicleNameList;
	}
	
	public List<VehicleName> getVehiclePriceByVehicleNameAndTypeDetails(Request<VehicleRequestObject> vehicleRequestObject) {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		List<VehicleName> vehicleNameList = adminVehicleHelper.getVehiclePriceByVehicleNameAndTypeDetails(vehicleRequest);
//		System.out.println(vehicleRequest.getVehicleDetailsType()+"  "+vehicleRequest.getVehicleName());
		return vehicleNameList;
	}
	
	public List<VehicleName> getAllVehicleNameList(Request<VehicleRequestObject> vehicleRequestObject) {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		List<VehicleName> vehicleNameList = adminVehicleHelper.getAllVehicleNameList(vehicleRequest);
		return vehicleNameList;
	}
	
	
	public List<UsersCity> getAllUserCityList(Request<UserRequestObject> userRequestObject) {
		UserRequestObject userRequest = userRequestObject.getPayload();
		List<UsersCity> userCityList = adminVehicleHelper.getAllUserCityList(userRequest);
		return userCityList;
	}
	
	public List<UsersArea> getAllUserAreaList(Request<UserRequestObject> userRequestObject) {
		UserRequestObject userRequest = userRequestObject.getPayload();
		List<UsersArea> userAreaList = adminVehicleHelper.getAllUserAreaList(userRequest);
		return userAreaList;
	}

	public VehicleRequestObject changeAllVehiclePrice(Request<VehicleRequestObject> vehicleRequestObject) throws BizException {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		adminVehicleHelper.validateVehicleRequest(vehicleRequest);
		List<VehicleDetails> vehicleNameList = vehicleHelper.getVahicleDetailsByVehicleNameNameBrandType(vehicleRequest);
	
		if(vehicleNameList != null) {
			System.out.println("enter 1"+vehicleNameList.size());
			
			for (int i = 0; i < vehicleNameList.size(); i++)  {
				System.out.println(vehicleNameList.get(i).getId()+" id");
				
				VehicleDetails vehicleDetails = vehicleHelper.getVehicleDetailsById(vehicleNameList.get(i).getId());
				vehicleDetails.setAdminVehiclePrice(vehicleRequest.getBookingPrice());
				vehicleDetails.setVenderVehiclePrice(vehicleRequest.getBookingPrice());
				
				vehicleHelper.updateVehicle(vehicleDetails);
			}
			VehicleName vehicleName = adminVehicleHelper.getVehicleNameByVehicleName(vehicleRequest.getVehicleName(), vehicleRequest.getVehicleBrand(), vehicleRequest.getVehicleDetailsType());
			if(vehicleName != null) {
				vehicleName.setPriceLimit(vehicleRequest.getBookingPrice());
				adminVehicleHelper.updateVehicleName(vehicleName);
			}
			
			vehicleRequest.setRespCode(Constant.SUCCESS_CODE);
			vehicleRequest.setRespMesg("Successfully Update Price");
			return vehicleRequest;
		}else {
			vehicleRequest.setRespCode(Constant.BAD_REQUEST_CODE);
			vehicleRequest.setRespMesg("Something went wrong");
			return vehicleRequest;
		}
		
	}

	public VehicleRequestObject sendMessage(Request<VehicleRequestObject> vehicleRequestObject) throws Exception {
	    VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
	    adminVehicleHelper.validateVehicleRequest(vehicleRequest);

	    	List<BookingDetails> bookingDetails = adminVehicleHelper.getBookingDetailsByStatusAndPickupdate(vehicleRequest);
	    	
	    	System.out.println("Booking details : "+bookingDetails);
	    

		if (bookingDetails != null) {
			if (vehicleRequest.getType().equals("CONFORMATION")) {
				for (BookingDetails existsBooking : bookingDetails) {

					if (existsBooking.getVehicleType().equals("CAR")) {
						String enqPara = commonHelper.bookingConfirmationParametersForCar(existsBooking);
						String apiResp = commonHelper.interaktApi(enqPara);
						
					}
					if (existsBooking.getVehicleType().equals("BIKE")) {
						String enqPara = commonHelper.bookingConfirmationParameters(existsBooking);
						String apiResp = commonHelper.interaktApi(enqPara);
					}
				}
			} else if (vehicleRequest.getType().equals("RECEIPT")) {
				for (BookingDetails existsBooking : bookingDetails) {
					System.out.println(existsBooking.getCustomerMobile()+" mobile no");
					if (existsBooking.getVehicleType().equals("CAR")) {
						String enqPara1 = commonHelper.bookingReceiptPdfParametersForVehicle(existsBooking);
						String apiResp1 = commonHelper.interaktApi(enqPara1);
					}
					if (existsBooking.getVehicleType().equals("BIKE")) {
						String enqPara1 = commonHelper.bookingReceiptPdfParametersForVehicle(existsBooking);
						String apiResp1 = commonHelper.interaktApi(enqPara1);
					}
				}
			} else if (vehicleRequest.getType().equals("RENTAL_BREAKUP")) {
				for (BookingDetails existsBooking : bookingDetails) {
					System.out.println(existsBooking.getCustomerMobile()+" mobile no");
					if (existsBooking.getVehicleType().equals("CAR")) {
						String hu = commonHelper.forRentalBreakup(existsBooking);
						String resp = commonHelper.interaktApi(hu);
					}
					if (existsBooking.getVehicleType().equals("BIKE")) {
						String hu = commonHelper.forRentalBreakup(existsBooking);
						String resp = commonHelper.interaktApi(hu);
					}
				}
			} else if (vehicleRequest.getType().equals("FOR_BALENCE_PAYMENT")) {
				for (BookingDetails existsBooking : bookingDetails) {
					System.out.println(existsBooking.getCustomerMobile()+" mobile no");
					if (existsBooking.getVehicleType().equals("CAR")) {

					}
					if (existsBooking.getVehicleType().equals("BIKE")) {
						String balancePayment = commonHelper.forBalancePayment(existsBooking);
						String balancePaymentResp = commonHelper.interaktApi(balancePayment);
					}
				}
			} else if (vehicleRequest.getType().equals("FOR_SECURITY_DEPOSIT")) {
				for (BookingDetails existsBooking : bookingDetails) {
					System.out.println(existsBooking.getCustomerMobile()+" mobile no");
					if (existsBooking.getVehicleType().equals("CAR")) {

					}
					if (existsBooking.getVehicleType().equals("BIKE")) {
						String securityDeposit = commonHelper.forSecurityDeposit(existsBooking);
						String securityDepositResp = commonHelper.interaktApi(securityDeposit);
					}
				}
			} else if (vehicleRequest.getType().equals("POC")) {
				for (BookingDetails existsBooking : bookingDetails) {
					System.out.println(existsBooking.getCustomerMobile()+" mobile no");
					if (existsBooking.getVehicleType().equals("CAR")) {

					}
					if (existsBooking.getVehicleType().equals("BIKE")) {
						String poc = commonHelper.forPocEzee(existsBooking);
						String pocResp = commonHelper.interaktApi(poc);
						
						System.out.println(pocResp);
					}
				}
			}else if (vehicleRequest.getType().equals("ADDSERVICES")) {
				System.out.println("Enter 1" +vehicleRequest.getFromDate()+" , "+vehicleRequest.getToDate());
				for (BookingDetails existsBooking : bookingDetails) {
					if (existsBooking.getVehicleType().equals("CAR")) {
						System.out.println(" Car existsBooking : "+existsBooking.getId());
//						System.out.println("Enter : "+existsBooking.getVehicleType());
						String addServices = commonHelper.forAdditonalServices(existsBooking);
//						String pocResp = commonHelper.interaktApi(addServices);
						
						System.out.println("Request : "+addServices);
//						System.out.println("Res car :  "+pocResp);
					} else
					if (existsBooking.getVehicleType().equals("BIKE")) {
						
						System.out.println(" Bike existsBooking : "+existsBooking.getId());
						
						String addServices = commonHelper.forAdditonalServices(existsBooking);
//						String pocResp = commonHelper.interaktApi(addServices);
						
						System.out.println("resp :  "+addServices);
//						System.out.println("Bike :  "+pocResp);
					} else  {
						String addServices = commonHelper.forAdditonalServices(existsBooking);
//						String pocResp = commonHelper.interaktApi(addServices);
						
						System.out.println(" Water existsBooking : "+existsBooking.getId());
						
						System.out.println("resp :  "+addServices);
//						System.out.println("Bike :  "+pocResp);
					}
				}
			}
		}

		vehicleRequest.setRespCode(Constant.SUCCESS_CODE);
		vehicleRequest.setRespMesg("Message Send Successfully");
		return vehicleRequest;
	}

	public VehicleRequestObject addBonusSlab(Request<VehicleRequestObject> vehicleRequestObject) throws BizException {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		adminVehicleHelper.validateVehicleRequest(vehicleRequest);
		
		BonusSlab existsBonusSlab = adminVehicleHelper.getBonusSlabDetails(vehicleRequest);
		if(existsBonusSlab == null) {
			
			BonusSlab bonusSlab = adminVehicleHelper.getBonusSlabByReqObj(vehicleRequest);
			bonusSlab = adminVehicleHelper.saveBonusSlab(bonusSlab);
			
			vehicleRequest.setRespCode(Constant.SUCCESS_CODE);
			vehicleRequest.setRespMesg("Added Successfully");
			return vehicleRequest;
		}else {
			
			vehicleRequest.setRespCode(Constant.BAD_REQUEST_CODE);
			vehicleRequest.setRespMesg("Already Exists");
			return vehicleRequest;
		}
	}

	public List<BonusSlab> getBonusSlabList(Request<VehicleRequestObject> vehicleRequestObject) {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		List<BonusSlab> bonusSlabList = adminVehicleHelper.getBonusSlabList(vehicleRequest);
		return bonusSlabList;
	}



}