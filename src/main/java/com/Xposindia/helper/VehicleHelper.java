package com.Xposindia.helper;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.Xposindia.constant.Constant;
import com.Xposindia.dao.VehicleDetailsDao;
import com.Xposindia.entities.VehicleDetails;
import com.Xposindia.expections.BizException;
import com.Xposindia.object.request.VehicleRequestObject;

@SuppressWarnings("unchecked")
@Component
public class VehicleHelper {

	@Autowired
	private VehicleDetailsDao vehicleDetailsDao;

	/* Validate User Request Object */
	public void validateVehicleRequest(VehicleRequestObject vehicleRequestObject) throws BizException {
		if (vehicleRequestObject == null) {
			throw new BizException(Constant.BAD_REQUEST_CODE, "Bad Request Object Null");
		}
	}
	
	public void validateVehicleExcelFile(MultipartFile file) throws BizException {
		if (file == null) {
			throw new BizException(401, "Invaild Request, File is Empty");
		}
	}

	/**
	 * Get Server Url
	 **/
	public String getServerPath(HttpServletRequest request) throws Exception {
		URL url = new URL(request.getRequestURL().toString());
		String serverPath = url.getProtocol() + "://" + url.getHost() + ":" + url.getPort();
		return serverPath;
	}

	@Transactional
	public VehicleDetails getVehicleDetailsById(Long id) {
		Criteria cr = vehicleDetailsDao.getSession().createCriteria(VehicleDetails.class);
		cr.add(Restrictions.eq("id", id));
		VehicleDetails existsBike = (VehicleDetails) cr.uniqueResult();
		return existsBike;
	}
	

	@Transactional
	public VehicleDetails getBikeDetailsByBikeNumber(String vehicleName, String userId, String vehicleDetailsType) {
		Criteria cr = vehicleDetailsDao.getSession().createCriteria(VehicleDetails.class);
		cr.add(Restrictions.eq("vehicleName", vehicleName));
		cr.add(Restrictions.eq("userId", userId));
		cr.add(Restrictions.eq("vehicleDetailsType", vehicleDetailsType));
		VehicleDetails existsBike = (VehicleDetails) cr.uniqueResult();
		return existsBike;
	}

	public VehicleDetails getBikeDetailsByObj(VehicleRequestObject vehicleRequest) {
		VehicleDetails vehicleDetails = new VehicleDetails();

		vehicleDetails.setVehicleImages1(vehicleRequest.getVehicleImages1());
		vehicleDetails.setVehicleImages2(vehicleRequest.getVehicleImages2());
		vehicleDetails.setUserId(vehicleRequest.getUserId());
		vehicleDetails.setVehicleBrand(vehicleRequest.getVehicleBrand());
		vehicleDetails.setVehicleName(vehicleRequest.getVehicleName().toUpperCase());
		vehicleDetails.setDiscription(vehicleRequest.getDiscription());
		vehicleDetails.setVehicleQuantity(vehicleRequest.getVehicleQuantity());
		vehicleDetails.setVehicleType(vehicleRequest.getVehicleType());
		vehicleDetails.setCity(vehicleRequest.getCity());
		vehicleDetails.setVehicleDetailsType(vehicleRequest.getVehicleDetailsType());
		vehicleDetails.setVenderVehiclePrice(vehicleRequest.getVenderVehiclePrice());
		vehicleDetails.setAdminVehiclePrice(00.00);
		vehicleDetails.setStatus("PENDING");

		vehicleDetails.setAreaFrom(vehicleRequest.getAreaFrom());
		vehicleDetails.setAreaTo(vehicleRequest.getAreaTo());

		vehicleDetails.setCreatedAt(new Date());
		vehicleDetails.setUpdatedAt(new Date());
		return vehicleDetails;
	}

	@Transactional
	public VehicleDetails saveBike(VehicleDetails vehicleDetails) {
		vehicleDetailsDao.persist(vehicleDetails);
		return vehicleDetails;
	}

	public VehicleDetails getUpdatedBikeDetailsByObj(VehicleRequestObject vehicleRequest,
			VehicleDetails vehicleDetails) {

		vehicleDetails.setVehicleImages1(vehicleRequest.getVehicleImages1());
		vehicleDetails.setVehicleImages2(vehicleRequest.getVehicleImages2());
		vehicleDetails.setVehicleBrand(vehicleRequest.getVehicleBrand());
		vehicleDetails.setVehicleName(vehicleRequest.getVehicleName().toUpperCase());
		vehicleDetails.setDiscription(vehicleRequest.getDiscription());
		vehicleDetails.setVehicleQuantity(vehicleRequest.getVehicleQuantity());
		vehicleDetails.setVehicleType(vehicleRequest.getVehicleType());
		vehicleDetails.setVenderVehiclePrice(vehicleRequest.getVenderVehiclePrice());
		vehicleDetails.setCity(vehicleRequest.getCity());
		vehicleDetails.setStatus("PENDING");

		vehicleDetails.setAreaFrom(vehicleRequest.getAreaFrom());
		vehicleDetails.setAreaTo(vehicleRequest.getAreaTo());

		vehicleDetails.setCreatedAt(new Date());
		vehicleDetails.setUpdatedAt(new Date());
		return vehicleDetails;
	}

	@Transactional
	public VehicleDetails updateVehicle(VehicleDetails vehicleDetails) {
		vehicleDetailsDao.update(vehicleDetails);
		return vehicleDetails;
		
	}

	public List<VehicleDetails> getBikeList(VehicleRequestObject vehicleRequest) {
		if (vehicleRequest.getUserId().equals("ALL")) {
			List<VehicleDetails> results = vehicleDetailsDao.getEntityManager()
					.createQuery("SELECT VD FROM VehicleDetails VD").getResultList();
			return results;
		} else {
			List<VehicleDetails> results = vehicleDetailsDao.getEntityManager()
					.createQuery("SELECT UD FROM VehicleDetails UD where UD.userId =:userId ORDER BY UD.id DESC")
					.setParameter("userId", vehicleRequest.getUserId())
					.getResultList();
			return results;
		}
	}
	
	public List<VehicleDetails> getVahicleDetailsByVehicleName(VehicleRequestObject vehicleRequest) {
			List<VehicleDetails> results = vehicleDetailsDao.getEntityManager()
					.createQuery("SELECT VD FROM VehicleDetails VD WHERE VD.vehicleName =:vehicleName")
					.setParameter("vehicleName", vehicleRequest.getVehicleName())
					.getResultList();
			return results;
	}

	public List<VehicleDetails> getVahicleDetailsByVehicleNameNameBrandType(VehicleRequestObject vehicleRequest) {
		List<VehicleDetails> results = vehicleDetailsDao.getEntityManager()
				.createQuery("SELECT VD FROM VehicleDetails VD WHERE VD.vehicleName =:vehicleName AND VD.vehicleBrand =:vehicleBrand AND VD.vehicleDetailsType =:vehicleDetailsType")
				.setParameter("vehicleName", vehicleRequest.getVehicleName())
				.setParameter("vehicleBrand", vehicleRequest.getVehicleBrand())
				.setParameter("vehicleDetailsType", vehicleRequest.getVehicleDetailsType())
				.getResultList();
		return results;
}

	
	public List<VehicleDetails> getAllVehicleList(String userId) {
		
		List<VehicleDetails> results = vehicleDetailsDao.getEntityManager()
				.createQuery("SELECT UD FROM VehicleDetails UD where UD.userId =:userId")
				.setParameter("userId", userId)
				.getResultList();
		return results;
		
	}
	
	public List<VehicleDetails> getVehicleListForSale(VehicleRequestObject vehicleRequest) {
		List<VehicleDetails> results = vehicleDetailsDao.getEntityManager()
				.createQuery("SELECT VD FROM VehicleDetails VD WHERE VD.status =:status ORDER BY VD.id DESC")
				.setParameter("status", "APPROVED")
				.getResultList();
		return results;
	}

	public List<VehicleDetails> getBikeListByStatus(VehicleRequestObject vehicleRequest) {
		if (vehicleRequest.getStatus().equals("PENDING")) {
			List<VehicleDetails> results = vehicleDetailsDao.getEntityManager()
					.createQuery("SELECT UD FROM VehicleDetails UD where UD.status =:status ORDER BY UD.id DESC")
					.setParameter("status", "PENDING")
					.getResultList();
			return results;
		} else if (vehicleRequest.getStatus().equals("APPROVE")) {
			List<VehicleDetails> results = vehicleDetailsDao.getEntityManager()
					.createQuery("SELECT UD FROM VehicleDetails UD where UD.status =:status ORDER BY UD.id DESC")
					.setParameter("status", "APPROVED")
					.getResultList();
			return results;
		} else if (vehicleRequest.getStatus().equals("REJECT")) {
			List<VehicleDetails> results = vehicleDetailsDao.getEntityManager()
					.createQuery("SELECT UD FROM VehicleDetails UD where UD.status =:status ORDER BY UD.id DESC")
					.setParameter("status", "REJECTED")
					.getResultList();
			return results;
		}
		return null;

	}

	public List<VehicleDetails> getVehicleListByUserId(VehicleRequestObject vehicleRequest) {
		if (vehicleRequest.getStatus().equals("ALL")) {
			List<VehicleDetails> results = vehicleDetailsDao.getEntityManager()
					.createQuery("SELECT UD FROM VehicleDetails UD where UD.userId =:userId ORDER BY UD.id DESC")
					.setParameter("userId", vehicleRequest.getUserId())
					.getResultList();
			return results;
		} else if (vehicleRequest.getStatus().equals("PENDING")) {
			List<VehicleDetails> results = vehicleDetailsDao.getEntityManager().createQuery(
					"SELECT UD FROM VehicleDetails UD where UD.userId =:userId AND UD.status =:status ORDER BY UD.id DESC")
					.setParameter("userId", vehicleRequest.getUserId())
					.setParameter("status", "PENDING")
					.getResultList();
			return results;
		} else if (vehicleRequest.getStatus().equals("APPROVED")) {
			List<VehicleDetails> results = vehicleDetailsDao.getEntityManager().createQuery(
					"SELECT UD FROM VehicleDetails UD where UD.userId =:userId AND UD.status =:status ORDER BY UD.id DESC")
					.setParameter("userId", vehicleRequest.getUserId())
					.setParameter("status", "APPROVED")
					.getResultList();
			return results;
		} else if (vehicleRequest.getStatus().equals("REJECTED")) {
			List<VehicleDetails> results = vehicleDetailsDao.getEntityManager().createQuery(
					"SELECT UD FROM VehicleDetails UD where UD.userId =:userId AND UD.status =:status ORDER BY UD.id DESC")
					.setParameter("userId", vehicleRequest.getUserId())
					.setParameter("status", "REJECTED")
					.getResultList();
			return results;
		}
		return null;

	}

	public List<VehicleDetails> getVehicleListByUserIdAndVehicleType(VehicleRequestObject vehicleRequest) {
		if (vehicleRequest.getRequestFor().equals("ALL")) {
			List<VehicleDetails> results = vehicleDetailsDao.getEntityManager()
					.createQuery("SELECT UD FROM VehicleDetails UD WHERE UD.userId =:userId")
					.setParameter("userId", vehicleRequest.getUserId())
					.getResultList();
			return results;
		} else {
			List<VehicleDetails> results = vehicleDetailsDao.getEntityManager().createQuery(
					"SELECT UD FROM VehicleDetails UD WHERE UD.vehicleType =:vehicleType AND UD.userId =:userId")
					.setParameter("vehicleType", vehicleRequest.getVehicleType())
					.setParameter("userId", vehicleRequest.getUserId())
					.getResultList();
			return results;
		}

	}

	public List<VehicleDetails> getTotalNoOfVehicle() {
		List<VehicleDetails> results = vehicleDetailsDao.getEntityManager()
				.createQuery("SELECT COUNT(*) FROM VehicleDetails UD")
				.getResultList();
		return results;
	}

	public List<VehicleDetails> getTotalNoOfPendingVehicle() {
		List<VehicleDetails> results = vehicleDetailsDao.getEntityManager()
				.createQuery("SELECT COUNT(*) FROM VehicleDetails UD where UD.status =:status")
				.setParameter("status", "PENDING")
				.getResultList();
		return results;
	}

	public List<VehicleDetails> getTotalNoOfBikeByUserId(String userId) {
		List<VehicleDetails> results = vehicleDetailsDao.getEntityManager()
				.createQuery("SELECT COUNT(*) FROM VehicleDetails UD where UD.userId =:userId")
				.setParameter("userId", userId)
				.getResultList();
		return results;
	}

	public List<VehicleDetails> getTotalNoOfPendingBikeByUserId(String userId) {
		List<VehicleDetails> results = vehicleDetailsDao.getEntityManager()
				.createQuery("SELECT COUNT(*) FROM VehicleDetails UD where UD.userId =:userId AND UD.status =:status")
				.setParameter("userId", userId)
				.setParameter("status", "PENDING")
				.getResultList();
		return results;
	}

	public List<VehicleDetails> getTotalNoOfApprovedBikeByUserId(String userId) {
		List<VehicleDetails> results = vehicleDetailsDao.getEntityManager()
				.createQuery("SELECT COUNT(*) FROM VehicleDetails UD where UD.userId =:userId AND UD.status =:status")
				.setParameter("userId", userId)
				.setParameter("status", "APPROVE")
				.getResultList();
		return results;
	}

	public List<VehicleDetails> getTotalNoOfRejectedBikeByUserId(String userId) {
		List<VehicleDetails> results = vehicleDetailsDao.getEntityManager()
				.createQuery("SELECT COUNT(*) FROM VehicleDetails UD where UD.userId =:userId AND UD.status =:status")
				.setParameter("userId", userId)
				.setParameter("status", "REJECT")
				.getResultList();
		return results;
	}
	
	
	@Transactional
	public void importVehicleDetailsFromFile(MultipartFile file, String parentDetails)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		Workbook workbook;
		String fileType = FilenameUtils.getExtension(file.getOriginalFilename());

		if (fileType.equalsIgnoreCase(".xlsx")) {
			workbook = new HSSFWorkbook(file.getInputStream());
		} else {
			workbook = new XSSFWorkbook(file.getInputStream());
		}
		DataFormatter dataFormatter = new DataFormatter();

		Iterator<Sheet> sheetIterator = workbook.sheetIterator();
		while (sheetIterator.hasNext()) {
			Sheet sheet = sheetIterator.next();
			int rowCount = 1;

			for (Row row : sheet) {
				if (rowCount > 1) {
					int column = 1;
					VehicleDetails vehicleDetails = new VehicleDetails();

//					SimpleDateFormat month = new SimpleDateFormat("MMM");
//					SimpleDateFormat yr = new SimpleDateFormat("yyyy");

					for (Cell cell : row) {
						String cellValue = dataFormatter.formatCellValue(cell);
						switch (column) {
						case 1:
							vehicleDetails.setVehicleBrand(cellValue);
							break;
						case 2:
							vehicleDetails.setVehicleName(cellValue);
							break;
						case 3:
							vehicleDetails.setDiscription(cellValue);
							break;
						case 4:
							vehicleDetails.setVehicleQuantity(Integer.parseInt(cellValue));
							break;
						case 5:
							vehicleDetails.setVehicleType(cellValue);
							break;
						case 6:
							vehicleDetails.setVehicleDetailsType(cellValue);
							break;
						case 7:
							vehicleDetails.setVenderVehiclePrice(Double.parseDouble(cellValue));
							break;
						case 8:
							vehicleDetails.setAdminVehiclePrice(Double.parseDouble(cellValue));
							break;
						case 9:
							vehicleDetails.setCity(cellValue);
							break;
						case 10:
							vehicleDetails.setAreaFrom(cellValue);
							break;
						case 11:
							vehicleDetails.setAreaTo(cellValue);
							break;
						case 12:
							vehicleDetails.setUserId(cellValue);
						default:
						}
						column++;
					}
//					VehicleDetails existsBike = this.getBikeDetailsByBikeNumber(vehicleRequest.getVehicleName(),
//							vehicleRequest.getUserId());
//					if (existsBike == null) {
						vehicleDetails.setVehicleImages1("N/A");
						vehicleDetails.setVehicleImages2("N/A");
						vehicleDetails.setStatus("APPROVED");
						vehicleDetails.setCreatedAt(new Date());
						vehicleDetails.setUpdatedAt(new Date());
						vehicleDetails = this.saveBike(vehicleDetails);
//					}
				}
				rowCount++;
			}
		}
		workbook.close();
	}

	
}
