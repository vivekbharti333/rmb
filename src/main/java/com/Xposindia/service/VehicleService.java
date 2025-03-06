package com.Xposindia.service;

import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Xposindia.constant.Constant;
import com.Xposindia.entities.VehicleDetails;
import com.Xposindia.expections.BizException;
import com.Xposindia.helper.VehicleHelper;
import com.Xposindia.helper.UserHelper;
import com.Xposindia.object.request.VehicleRequestObject;
import com.Xposindia.object.request.Request;

@Service
public class VehicleService {

	@Autowired
	private VehicleHelper vehicleHelper;

	@Autowired
	private UserHelper userHelper;

//	public VehicleRequestObject changeVehicleStatusByAdmin(Request<VehicleRequestObject> vehicleRequestObject)
//			throws BizException, Exception {
//		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
//		vehicleHelper.validateVehicleRequest(vehicleRequest);
//
//		List<VehicleDetails> vehicleList = vehicleHelper.getAllVehicleList(vehicleRequest.getUserId());
//		for (int i = 0; i < vehicleList.size(); i++) {
//
//			VehicleDetails vehicleDetails = vehicleHelper.getVehicleDetailsById(vehicleList.get(i).getId());
//
//			vehicleDetails.setStatus("PENDING");
//			vehicleDetails = vehicleHelper.updateVehicle(vehicleDetails);
//		}
//
//		return vehicleRequest;
//	}

	public VehicleRequestObject addVehicle(Request<VehicleRequestObject> vehicleRequestObject)
			throws BizException, Exception {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		vehicleHelper.validateVehicleRequest(vehicleRequest);

		VehicleDetails existsBike = vehicleHelper.getBikeDetailsByBikeNumber(vehicleRequest.getVehicleName(),
				vehicleRequest.getUserId(), vehicleRequest.getVehicleDetailsType());
		if (existsBike == null) {

			if (vehicleRequest.getVehicleImages1() != null && !vehicleRequest.getVehicleImages1().isEmpty()) {
				String basePath = userHelper.getPathToUploadFile("VehicleImage");
				String fileName = vehicleRequest.getVehicleName() + vehicleRequest.getUserId().replaceAll(" ", "")
						+ "A";
				String finalFileName = basePath + File.separator + fileName;
				String serverPath =  fileName;
				finalFileName = userHelper.uploadPhotos(vehicleRequest.getVehicleImages1(), finalFileName);
				vehicleRequest.setVehicleImages1(serverPath);
			}

			if (vehicleRequest.getVehicleImages2() != null && !vehicleRequest.getVehicleImages2().isEmpty()) {
				String basePath = userHelper.getPathToUploadFile("VehicleImage");
				String fileName = vehicleRequest.getVehicleName() + vehicleRequest.getUserId().replaceAll(" ", "")
						+ "B";
				String finalFileName = basePath + File.separator + fileName;
				String serverPath = fileName;
				finalFileName = userHelper.uploadPhotos(vehicleRequest.getVehicleImages2(), finalFileName);
				vehicleRequest.setVehicleImages2(serverPath);
			}

			VehicleDetails vehicleDetails = vehicleHelper.getBikeDetailsByObj(vehicleRequest);
			vehicleDetails = vehicleHelper.saveBike(vehicleDetails);

			vehicleRequest.setRespCode(Constant.SUCCESS_CODE);
			vehicleRequest.setRespMesg(Constant.REGISTERED_SUCCESS);
		} else {

			vehicleRequest.setRespCode(Constant.FAILED_REQUEST_CODE);
			vehicleRequest.setRespMesg("Bike Already Exists. Please Update Quantity");
		}
		return vehicleRequest;
	}

	public VehicleRequestObject updateVehicle(Request<VehicleRequestObject> vehicleRequestObject)
			throws BizException, Exception {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		vehicleHelper.validateVehicleRequest(vehicleRequest);

		VehicleDetails existsBike = vehicleHelper.getBikeDetailsByBikeNumber(vehicleRequest.getVehicleName(),
				vehicleRequest.getUserId(), vehicleRequest.getVehicleDetailsType());
		if (existsBike != null) {

			if (vehicleRequest.getVehicleImages1() != null && !vehicleRequest.getVehicleImages1().isEmpty()) {
				String basePath = userHelper.getPathToUploadFile("VehicleImage");
				String fileName = vehicleRequest.getVehicleName() + vehicleRequest.getUserId().replaceAll(" ", "")
						+ "A";
				String finalFileName = basePath + File.separator + fileName;
				String serverPath = fileName;
				finalFileName = userHelper.uploadPhotos(vehicleRequest.getVehicleImages1(), finalFileName);
				vehicleRequest.setVehicleImages1(serverPath);
			}

			if (vehicleRequest.getVehicleImages2() != null && !vehicleRequest.getVehicleImages2().isEmpty()) {
				String basePath = userHelper.getPathToUploadFile("VehicleImage");
				String fileName = vehicleRequest.getVehicleName() + vehicleRequest.getUserId().replaceAll(" ", "")
						+ "B";
				String finalFileName = basePath + File.separator + fileName;
				String serverPath = fileName;
				finalFileName = userHelper.uploadPhotos(vehicleRequest.getVehicleImages2(), finalFileName);
				vehicleRequest.setVehicleImages2(serverPath);
			}

			existsBike = vehicleHelper.getUpdatedBikeDetailsByObj(vehicleRequest, existsBike);
			existsBike = vehicleHelper.updateVehicle(existsBike);

			vehicleRequest.setRespCode(Constant.SUCCESS_CODE);
			vehicleRequest.setRespMesg("Successfully Updated");
		} else {
			vehicleRequest.setRespCode(Constant.FAILED_REQUEST_CODE);
			vehicleRequest.setRespMesg("Not Found");

		}

		return vehicleRequest;
	}

	public VehicleRequestObject updateVehicleQuantity(Request<VehicleRequestObject> vehicleRequestObject)
			throws BizException, Exception {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		vehicleHelper.validateVehicleRequest(vehicleRequest);

		VehicleDetails existsBike = vehicleHelper.getVehicleDetailsById(vehicleRequest.getId());
		if (existsBike != null) {

			existsBike.setVehicleQuantity(vehicleRequest.getVehicleQuantity());
			existsBike = vehicleHelper.updateVehicle(existsBike);

			vehicleRequest.setRespCode(Constant.SUCCESS_CODE);
			vehicleRequest.setRespMesg("Successfully Updated");

		} else {
			vehicleRequest.setRespCode(Constant.BAD_REQUEST_CODE);
			vehicleRequest.setRespMesg("Fail to  Updated");
		}

		return vehicleRequest;
	}

	public VehicleRequestObject changeVehicleStatus(Request<VehicleRequestObject> vehicleRequestObject)
			throws BizException, Exception {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		vehicleHelper.validateVehicleRequest(vehicleRequest);

		VehicleDetails existVehicle = vehicleHelper.getVehicleDetailsById(vehicleRequest.getId());
		if (existVehicle != null) {

			existVehicle.setStatus(vehicleRequest.getStatus());
			existVehicle = vehicleHelper.updateVehicle(existVehicle);

			vehicleRequest.setRespCode(Constant.SUCCESS_CODE);
			vehicleRequest.setRespMesg("Change Status Successfully");

		} else {
			vehicleRequest.setRespCode(Constant.FAILED_REQUEST_CODE);
			vehicleRequest.setRespMesg("Not Found");
		}

		return vehicleRequest;
	}

	public VehicleRequestObject updateVehicleAdminPrice(Request<VehicleRequestObject> vehicleRequestObject)
			throws BizException, Exception {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		vehicleHelper.validateVehicleRequest(vehicleRequest);

		VehicleDetails existVehicle = vehicleHelper.getVehicleDetailsById(vehicleRequest.getId());
		if (existVehicle != null) {

			existVehicle.setAdminVehiclePrice(vehicleRequest.getAdminVehiclePrice());
			existVehicle = vehicleHelper.updateVehicle(existVehicle);

			vehicleRequest.setRespCode(Constant.SUCCESS_CODE);
			vehicleRequest.setRespMesg("Update Sale Price Successfully");

		} else {
			vehicleRequest.setRespCode(Constant.FAILED_REQUEST_CODE);
			vehicleRequest.setRespMesg("Not Found");
		}

		return vehicleRequest;
	}

	public List<VehicleDetails> getBikeList(Request<VehicleRequestObject> vehicleRequestObject) {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		List<VehicleDetails> bikeList = vehicleHelper.getBikeList(vehicleRequest);
		return bikeList;
	}

	public List<VehicleDetails> getVehicleListForSale(Request<VehicleRequestObject> vehicleRequestObject) {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		List<VehicleDetails> bikeList = vehicleHelper.getVehicleListForSale(vehicleRequest);
		return bikeList;
	}

	public List<VehicleDetails> getBikeListByStatus(Request<VehicleRequestObject> vehicleRequestObject) {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		List<VehicleDetails> bikeList = vehicleHelper.getBikeListByStatus(vehicleRequest);
		return bikeList;
	}

	public List<VehicleDetails> getVehicleListByUserId(Request<VehicleRequestObject> vehicleRequestObject) {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		List<VehicleDetails> bikeList = vehicleHelper.getVehicleListByUserId(vehicleRequest);
		return bikeList;
	}

	public List<VehicleDetails> getVehicleListByUserIdAndVehicleType(
			Request<VehicleRequestObject> vehicleRequestObject) {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		List<VehicleDetails> bikeList = vehicleHelper.getVehicleListByUserIdAndVehicleType(vehicleRequest);
		return bikeList;
	}

	@SuppressWarnings("unused")
	public List<VehicleDetails> getTotalNoOfVehicle(Request<VehicleRequestObject> vehicleRequestObject) {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		List<VehicleDetails> bikeList = vehicleHelper.getTotalNoOfVehicle();
		return bikeList;
	}

	@SuppressWarnings("unused")
	public List<VehicleDetails> getTotalNoOfPendingVehicle(Request<VehicleRequestObject> vehicleRequestObject) {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		List<VehicleDetails> bikeList = vehicleHelper.getTotalNoOfPendingVehicle();
		return bikeList;
	}

	public List<VehicleDetails> getTotalNoOfBikeByUserId(Request<VehicleRequestObject> vehicleRequestObject) {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		List<VehicleDetails> bikeList = vehicleHelper.getTotalNoOfBikeByUserId(vehicleRequest.getUserId());
		return bikeList;
	}

	public List<VehicleDetails> getTotalNoOfPendingBikeByUserId(Request<VehicleRequestObject> vehicleRequestObject) {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		List<VehicleDetails> bikeList = vehicleHelper.getTotalNoOfPendingBikeByUserId(vehicleRequest.getUserId());
		return bikeList;
	}

	public List<VehicleDetails> getTotalNoOfApprovedBikeByUserId(Request<VehicleRequestObject> vehicleRequestObject) {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		List<VehicleDetails> bikeList = vehicleHelper.getTotalNoOfApprovedBikeByUserId(vehicleRequest.getUserId());
		return bikeList;
	}

	public List<VehicleDetails> getTotalNoOfRejectedBikeByUserId(Request<VehicleRequestObject> vehicleRequestObject) {
		VehicleRequestObject vehicleRequest = vehicleRequestObject.getPayload();
		List<VehicleDetails> bikeList = vehicleHelper.getTotalNoOfRejectedBikeByUserId(vehicleRequest.getUserId());
		return bikeList;
	}
	
	
	public VehicleRequestObject importVehicleDetailsFromFile(MultipartFile file, String parentDetails)
			throws BizException, Exception {
		VehicleRequestObject vehicleRequestObject = new VehicleRequestObject();
		vehicleHelper.validateVehicleExcelFile(file);
		vehicleHelper.importVehicleDetailsFromFile(file, parentDetails);

		vehicleRequestObject.setRespCode(Constant.SUCCESS_CODE);
		vehicleRequestObject.setRespMesg("Vehicle Details Imported Successfully");
		return vehicleRequestObject;
	}

	
}