package com.Xposindia.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Xposindia.constant.Constant;
import com.Xposindia.entities.VehicleDetails;
import com.Xposindia.expections.BizException;
import com.Xposindia.helper.UserHelper;
import com.Xposindia.object.request.VehicleRequestObject;
import com.Xposindia.object.request.Request;
import com.Xposindia.object.response.GenricResponse;
import com.Xposindia.object.response.Response;
import com.Xposindia.service.VehicleService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@CrossOrigin(origins = "*")
@RestController
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private UserHelper usersHelper;

	@RequestMapping(value = "/images/{imageName}", method = RequestMethod.GET)
	public HttpEntity<byte[]> getOrder(@PathVariable String imageName, HttpServletRequest request) throws Exception {

		usersHelper.getServerPath(request);
		byte[] image;

		String basePath = usersHelper.getPathToUploadFile("VehicleImage");
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

//	@RequestMapping(path = "changeVehicleStatusByAdmin", method = RequestMethod.POST)
//	public Response<VehicleRequestObject> changeVehicleStatusByAdmin(@RequestBody Request<VehicleRequestObject> vehicleRequestObject,
//			HttpServletRequest request) {
//		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
//		try {
//
//			VehicleRequestObject responce = vehicleService.changeVehicleStatusByAdmin(vehicleRequestObject);
//			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
//		} catch (BizException e) {
//			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE, e.getMessage());
//		} catch (Exception e) {
//			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
//		}
//	}

	@RequestMapping(path = "addVehicle", method = RequestMethod.POST)
	public Response<VehicleRequestObject> addVehicle(@RequestBody Request<VehicleRequestObject> vehicleRequestObject,
			HttpServletRequest request) {
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {

			VehicleRequestObject responce = vehicleService.addVehicle(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		} catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE, e.getMessage());
		} catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	}

	@RequestMapping(path = "updateVehicle", method = RequestMethod.POST)
	public Response<VehicleRequestObject> updateVehicle(@RequestBody Request<VehicleRequestObject> vehicleRequestObject,
			HttpServletRequest request) {
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			String serverPath = usersHelper.getServerPath(request);
			vehicleRequestObject.getPayload().setUrlPath(serverPath);

			VehicleRequestObject responce = vehicleService.updateVehicle(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		} catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE, e.getMessage());
		} catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	}

	@RequestMapping(path = "updateVehicleQuantity", method = RequestMethod.POST)
	public Response<VehicleRequestObject> updateVehicleQuantity(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject, HttpServletRequest request) {
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {

			VehicleRequestObject responce = vehicleService.updateVehicleQuantity(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		} catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE, e.getMessage());
		} catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	}

	@RequestMapping(path = "changeVehicleStatus", method = RequestMethod.POST)
	public Response<VehicleRequestObject> changeVehicleStatus(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject, HttpServletRequest request) {
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce = vehicleService.changeVehicleStatus(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		} catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE, e.getMessage());
		} catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	}

	@RequestMapping(path = "updateVehicleAdminPrice", method = RequestMethod.POST)
	public Response<VehicleRequestObject> updateVehicleAdminPrice(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject, HttpServletRequest request) {
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce = vehicleService.updateVehicleAdminPrice(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		} catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE, e.getMessage());
		} catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	}

	@RequestMapping(path = "getBikeList", method = RequestMethod.POST)
	public Response<VehicleDetails> getBikeList(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<VehicleDetails> response = new GenricResponse<VehicleDetails>();
		try {
			List<VehicleDetails> bikeList = vehicleService.getBikeList(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}

	@RequestMapping(path = "getVehicleListForSale", method = RequestMethod.POST)
	public Response<VehicleDetails> getVehicleListForSale(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<VehicleDetails> response = new GenricResponse<VehicleDetails>();
		try {
			List<VehicleDetails> bikeList = vehicleService.getVehicleListForSale(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}

	@RequestMapping(path = "getBikeListByStatus", method = RequestMethod.POST)
	public Response<VehicleDetails> getBikeListByStatus(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<VehicleDetails> response = new GenricResponse<VehicleDetails>();
		try {
			List<VehicleDetails> bikeList = vehicleService.getBikeListByStatus(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}

	@RequestMapping(path = "getVehicleListByUserId", method = RequestMethod.POST)
	public Response<VehicleDetails> getBikeListByUserId(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<VehicleDetails> response = new GenricResponse<VehicleDetails>();
		try {
			List<VehicleDetails> bikeList = vehicleService.getVehicleListByUserId(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}

	@RequestMapping(path = "getVehicleListByUserIdAndVehicleType", method = RequestMethod.POST)
	public Response<VehicleDetails> getVehicleListByUserIdAndVehicleType(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<VehicleDetails> response = new GenricResponse<VehicleDetails>();
		try {
			List<VehicleDetails> bikeList = vehicleService.getVehicleListByUserIdAndVehicleType(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}

	@RequestMapping(path = "getTotalNoOfVehicle", method = RequestMethod.POST)
	public Response<VehicleDetails> getTotalNoOfVehicle(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<VehicleDetails> response = new GenricResponse<VehicleDetails>();
		try {
			List<VehicleDetails> bikeList = vehicleService.getTotalNoOfVehicle(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}

	@RequestMapping(path = "getTotalNoOfPendingVehicle", method = RequestMethod.POST)
	public Response<VehicleDetails> getTotalNoOfPendingVehicle(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<VehicleDetails> response = new GenricResponse<VehicleDetails>();
		try {
			List<VehicleDetails> bikeList = vehicleService.getTotalNoOfPendingVehicle(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}

	@RequestMapping(path = "getTotalNoOfBikeByUserId", method = RequestMethod.POST)
	public Response<VehicleDetails> getTotalNoOfBikeByUserId(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<VehicleDetails> response = new GenricResponse<VehicleDetails>();
		try {
			List<VehicleDetails> bikeList = vehicleService.getTotalNoOfBikeByUserId(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}

	@RequestMapping(path = "getTotalNoOfPendingBikeByUserId", method = RequestMethod.POST)
	public Response<VehicleDetails> getTotalNoOfPendingBikeByUserId(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<VehicleDetails> response = new GenricResponse<VehicleDetails>();
		try {
			List<VehicleDetails> bikeList = vehicleService.getTotalNoOfPendingBikeByUserId(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}

	@RequestMapping(path = "getTotalNoOfApprovedBikeByUserId", method = RequestMethod.POST)
	public Response<VehicleDetails> getTotalNoOfApprovedBikeByUserId(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<VehicleDetails> response = new GenricResponse<VehicleDetails>();
		try {
			List<VehicleDetails> bikeList = vehicleService.getTotalNoOfApprovedBikeByUserId(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}

	@RequestMapping(path = "getTotalNoOfRejectedBikeByUserId", method = RequestMethod.POST)
	public Response<VehicleDetails> getTotalNoOfRejectedBikeByUserId(
			@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<VehicleDetails> response = new GenricResponse<VehicleDetails>();
		try {
			List<VehicleDetails> bikeList = vehicleService.getTotalNoOfRejectedBikeByUserId(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	@RequestMapping(path = "importVehicleDetailsFromFile", method = RequestMethod.POST)
	public Response<VehicleRequestObject> importVehicleDetailsFromFile(@RequestParam("file") MultipartFile file,
			String parentDetails) {
		GenricResponse<VehicleRequestObject> response = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce = vehicleService.importVehicleDetailsFromFile(file, parentDetails);
			return response.createSuccessResponse(responce, 200);
		} catch (BizException e) {
			return response.createErrorResponse(400, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(500, e.getMessage());
		}
	}

}
