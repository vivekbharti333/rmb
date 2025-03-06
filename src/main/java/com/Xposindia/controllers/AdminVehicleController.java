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
import com.Xposindia.entities.BonusSlab;
import com.Xposindia.entities.UsersArea;
import com.Xposindia.entities.UsersCity;
import com.Xposindia.entities.VehicleBrand;
import com.Xposindia.entities.VehicleName;
import com.Xposindia.expections.BizException;
import com.Xposindia.object.request.VehicleRequestObject;
import com.Xposindia.object.request.Request;
import com.Xposindia.object.request.UserRequestObject;
import com.Xposindia.object.response.GenricResponse;
import com.Xposindia.object.response.Response;
import com.Xposindia.service.AdminVehicleService;

@CrossOrigin(origins = "*")
@RestController
public class AdminVehicleController {

	@Autowired
	private AdminVehicleService adminVehicleService;

	@RequestMapping(path = "addVehicleBrand", method = RequestMethod.POST)
	public Response<VehicleRequestObject> addVehicleBrand(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject, HttpServletRequest request) {
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce = adminVehicleService.addVehicleBrand(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		} catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE, e.getMessage());
		} catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	}

	// for vender drop down
	@RequestMapping(path = "getVehicleBrandList", method = RequestMethod.POST)
	public Response<VehicleBrand> getVehicleBrandList(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<VehicleBrand> response = new GenricResponse<VehicleBrand>();
		try {
			List<VehicleBrand> vehicleBrandList = adminVehicleService.getVehicleBrandList(vehicleRequestObject);
			return response.createListResponse(vehicleBrandList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}

	// for admin
	@RequestMapping(path = "getAllVehicleBrandList", method = RequestMethod.POST)
	public Response<VehicleBrand> getAllVehicleBrandList(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<VehicleBrand> response = new GenricResponse<VehicleBrand>();
		try {
			List<VehicleBrand> vehicleBrandList = adminVehicleService.getAllVehicleBrandList(vehicleRequestObject);
			return response.createListResponse(vehicleBrandList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "deleteVehicleBrand", method = RequestMethod.POST)
	public Response<VehicleRequestObject> deleteVehicleBrand(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject, HttpServletRequest request) {
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce = adminVehicleService.deleteVehicleBrand(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		} catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE, e.getMessage());
		} catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	}
	

	@RequestMapping(path = "addVehicleName", method = RequestMethod.POST)
	public Response<VehicleRequestObject> addVehicleName(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject, HttpServletRequest request) {
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce = adminVehicleService.addVehicleName(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		} catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE, e.getMessage());
		} catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	}

	// for vender drop down
//	@RequestMapping(path = "getVehicleNameList", method = RequestMethod.POST)
//	public Response<VehicleName> getVehicleNameList(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
//		GenricResponse<VehicleName> response = new GenricResponse<VehicleName>();
//		try {
//			List<VehicleName> vehicleBrandList = adminVehicleService.getVehicleNameList(vehicleRequestObject);
//			return response.createListResponse(vehicleBrandList, 200);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return response.createErrorResponse(400, e.getMessage());
//		}
//	}
	
	@RequestMapping(path = "vehicleNameByType", method = RequestMethod.POST)
	public Response<VehicleName> vehicleNameByType(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<VehicleName> response = new GenricResponse<VehicleName>();
		try {
			List<VehicleName> vehicleBrandList = adminVehicleService.vehicleNameByType(vehicleRequestObject);
			return response.createListResponse(vehicleBrandList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "getGroupVehicleNameList", method = RequestMethod.POST)
	public Response<VehicleName> getGroupVehicleNameList(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<VehicleName> response = new GenricResponse<VehicleName>();
		try {
			List<VehicleName> vehicleBrandList = adminVehicleService.getGroupVehicleNameList(vehicleRequestObject);
			return response.createListResponse(vehicleBrandList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "getVehicleNameListByVehicleName", method = RequestMethod.POST)
	public Response<VehicleName> getVehicleNameListByVehicleName(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<VehicleName> response = new GenricResponse<VehicleName>();
		try {
			List<VehicleName> vehicleBrandList = adminVehicleService.getVehicleNameListByVehicleName(vehicleRequestObject);
			return response.createListResponse(vehicleBrandList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "getVehiclePriceByVehicleNameAndTypeDetails", method = RequestMethod.POST)
	public Response<VehicleName> getVehiclePriceByVehicleNameAndTypeDetails(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<VehicleName> response = new GenricResponse<VehicleName>();
		try {
			List<VehicleName> vehicleBrandList = adminVehicleService.getVehiclePriceByVehicleNameAndTypeDetails(vehicleRequestObject);
			return response.createListResponse(vehicleBrandList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}

	// for admin
	@RequestMapping(path = "getAllVehicleNameList", method = RequestMethod.POST)
	public Response<VehicleName> getAllVehicleNameList(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<VehicleName> response = new GenricResponse<VehicleName>();
		try {
			List<VehicleName> vehicleBrandList = adminVehicleService.getAllVehicleNameList(vehicleRequestObject);
			return response.createListResponse(vehicleBrandList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "getAllUserCityList", method = RequestMethod.POST)
	public Response<UsersCity> getAllUserCityList(
			@RequestBody Request<UserRequestObject> userRequestObject) {
		GenricResponse<UsersCity> response = new GenricResponse<UsersCity>();
		try {
			List<UsersCity> userCityList = adminVehicleService.getAllUserCityList(userRequestObject);
			return response.createListResponse(userCityList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}

	
	@RequestMapping(path = "getAllUserAreaList", method = RequestMethod.POST)
	public Response<UsersArea> getAllUserAreaList(
			@RequestBody Request<UserRequestObject> userRequestObject) {
		GenricResponse<UsersArea> response = new GenricResponse<UsersArea>();
		try {
			List<UsersArea> userAreaList = adminVehicleService.getAllUserAreaList(userRequestObject);
			return response.createListResponse(userAreaList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "changeAllVehiclePrice", method = RequestMethod.POST)
	public Response<VehicleRequestObject> changeAllVehiclePrice(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject, HttpServletRequest request) {
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce = adminVehicleService.changeAllVehiclePrice(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		} catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE, e.getMessage());
		} catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "sendMessage", method = RequestMethod.POST)
	public Response<VehicleRequestObject> sendMessage(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject, HttpServletRequest request) {
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce = adminVehicleService.sendMessage(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		} catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE, e.getMessage());
		} catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	}
	
	@RequestMapping(path = "addBonusSlab", method = RequestMethod.POST)
	public Response<VehicleRequestObject> addBonusSlab(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject, HttpServletRequest request) {
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce = adminVehicleService.addBonusSlab(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		} catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE, e.getMessage());
		} catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	}
		
	@RequestMapping(path = "getBonusSlabList", method = RequestMethod.POST)
	public Response<BonusSlab> getBonusSlabList(
			@RequestBody Request<VehicleRequestObject> userRequestObject) {
		GenricResponse<BonusSlab> response = new GenricResponse<BonusSlab>();
		try {
			List<BonusSlab> bonusSlab = adminVehicleService.getBonusSlabList(userRequestObject);
			return response.createListResponse(bonusSlab, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	
	
}
