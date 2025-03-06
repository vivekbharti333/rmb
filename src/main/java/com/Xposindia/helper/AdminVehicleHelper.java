package com.Xposindia.helper;

import java.io.IOException;
import java.util.List;

import javax.persistence.TemporalType;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.Xposindia.constant.Constant;
import com.Xposindia.dao.BonusSlabDao;
import com.Xposindia.dao.BookingDetailsDao;
import com.Xposindia.dao.UserAreaDao;
import com.Xposindia.dao.UserCityDao;
import com.Xposindia.dao.VehicleBrandDao;
import com.Xposindia.dao.VehicleNameDao;
import com.Xposindia.entities.BonusSlab;
import com.Xposindia.entities.BookingDetails;
import com.Xposindia.entities.UsersArea;
import com.Xposindia.entities.UsersCity;
import com.Xposindia.entities.VehicleBrand;
import com.Xposindia.entities.VehicleName;
import com.Xposindia.expections.BizException;
import com.Xposindia.object.request.UserRequestObject;
import com.Xposindia.object.request.VehicleRequestObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@SuppressWarnings("unchecked")
@Component
public class AdminVehicleHelper {

	@Autowired
	private VehicleBrandDao vehicleBrandDao;

	@Autowired
	private VehicleNameDao vehicleNameDao;

	@Autowired
	private UserCityDao userCityDao;

	@Autowired
	private UserAreaDao userAreaDao;

	@Autowired
	private BookingDetailsDao bookingDetailsDao;

	@Autowired
	private BonusSlabDao bonusSlabDao;

	/* Validate User Request Object */
	public void validateVehicleRequest(VehicleRequestObject vehicleRequestObject) throws BizException {
		if (vehicleRequestObject == null) {
			throw new BizException(Constant.BAD_REQUEST_CODE, "Bad Request Object Null");
		}
	}

	@Transactional
	public VehicleBrand getVehicleBrandById(Long id) {
		Criteria cr = vehicleBrandDao.getSession().createCriteria(VehicleBrand.class);
		cr.add(Restrictions.eq("id", id));
		VehicleBrand existsVehicle = (VehicleBrand) cr.uniqueResult();
		return existsVehicle;
	}

	@Transactional
	public VehicleBrand getVehicleBrandByVehicleBrand(String vehicleBrand, String vehicleType) {
		Criteria cr = vehicleBrandDao.getSession().createCriteria(VehicleBrand.class);
		cr.add(Restrictions.eq("vehicleBrand", vehicleBrand));
		cr.add(Restrictions.eq("vehicleType", vehicleType));
		VehicleBrand existsVehicle = (VehicleBrand) cr.uniqueResult();
		return existsVehicle;
	}

	public VehicleBrand getVehicleBrandByReqObj(VehicleRequestObject vehicleRequest) {

		VehicleBrand vehicleBrand = new VehicleBrand();

		vehicleBrand.setVehicleType(vehicleRequest.getVehicleType());
		vehicleBrand.setVehicleBrand(vehicleRequest.getVehicleBrand());
		vehicleBrand.setStatus("ACTIVE");

		return vehicleBrand;
	}

	@Transactional
	public VehicleBrand saveVehicleBrand(VehicleBrand vehicleName) {
		vehicleBrandDao.persist(vehicleName);
		return vehicleName;
	}

	@Transactional
	public void deleteVehicleBrand(VehicleBrand vehicleName) {
		vehicleBrandDao.delete(vehicleName);
	}

	public List<VehicleBrand> getVehicleBrandList(VehicleRequestObject vehicleRequest) {
		System.out.println("Enter" + vehicleRequest.getVehicleType());
//		if(vehicleRequest.getRequestFor().equalsIgnoreCase("Drop Down"))
		List<VehicleBrand> results = vehicleBrandDao.getEntityManager().createQuery(
				"SELECT VB FROM VehicleBrand VB where VB.vehicleType =:vehicleType AND VB.status =:status ORDER BY VB.vehicleBrand ASC")
				.setParameter("vehicleType", vehicleRequest.getVehicleType()).setParameter("status", "ACTIVE")
				.getResultList();
		return results;
	}

	public List<VehicleBrand> getAllVehicleBrandList(VehicleRequestObject vehicleRequest) {
		if (vehicleRequest.getRequestFor().equalsIgnoreCase("ALL")) {
			List<VehicleBrand> results = vehicleBrandDao.getEntityManager()
					.createQuery("SELECT VB FROM VehicleBrand VB ORDER BY VB.id DESC").getResultList();
			return results;

		} else if (vehicleRequest.getRequestFor().equalsIgnoreCase("DROPDOWN")) {
			List<VehicleBrand> results = vehicleBrandDao.getEntityManager()
					.createQuery("SELECT VB FROM VehicleBrand VB WHERE VB.status =:status ORDER BY VB.vehicleBrand ASC")
					.setParameter("status", "ACTIVE").getResultList();
			return results;
		} else {
//			List<VehicleBrand> results = vehicleBrandDao.getEntityManager()
//					.createQuery("SELECT VB FROM VehicleBrand VB")
//					.getResultList();
//			return results;
		}
		return null;
	}

	@Transactional
	public VehicleName getVehicleNameById(Long id) {
		Criteria cr = vehicleNameDao.getSession().createCriteria(VehicleName.class);
		cr.add(Restrictions.eq("id", id));
		VehicleName existsVehicle = (VehicleName) cr.uniqueResult();
		return existsVehicle;
	}

	@Transactional
	public VehicleName getVehicleNameByVehicleNameAndVehicleDetailType(String vehicleName, String vehicleDetailsType) {
		Criteria cr = vehicleNameDao.getSession().createCriteria(VehicleName.class);
		cr.add(Restrictions.eq("vehicleName", vehicleName));
		cr.add(Restrictions.eq("vehicleDetailsType", vehicleDetailsType));
		VehicleName existsVehicle = (VehicleName) cr.uniqueResult();
		return existsVehicle;
	}

	@Transactional
	public VehicleName getVehicleNameByVehicleName(String vehicleName, String vehicleBrand, String vehicleDetailsType) {
		Criteria cr = vehicleNameDao.getSession().createCriteria(VehicleName.class);
		cr.add(Restrictions.eq("vehicleName", vehicleName));
		cr.add(Restrictions.eq("vehicleBrand", vehicleBrand));
		cr.add(Restrictions.eq("vehicleDetailsType", vehicleDetailsType));
		VehicleName existsVehicle = (VehicleName) cr.uniqueResult();
		return existsVehicle;
	}

	public VehicleName getVehicleNameByReqObj(VehicleRequestObject vehicleRequest) {

		VehicleName vehicleName = new VehicleName();

		vehicleName.setVehicleType(vehicleRequest.getVehicleType());
		vehicleName.setVehicleBrand(vehicleRequest.getVehicleBrand());
		vehicleName.setVehicleName(vehicleRequest.getVehicleName().toUpperCase());
		vehicleName.setVehicleDetailsType(vehicleRequest.getVehicleDetailsType());
		vehicleName.setPriceLimit(vehicleRequest.getPriceLimit());

		return vehicleName;
	}

	@Transactional
	public VehicleName saveVehicleName(VehicleName vehicleName) {
		vehicleNameDao.persist(vehicleName);
		return vehicleName;
	}

	@Transactional
	public VehicleName updateVehicleName(VehicleName vehicleName) {
		vehicleNameDao.update(vehicleName);
		return vehicleName;
	}

//	public List<VehicleName> getVehicleNameList(VehicleRequestObject vehicleRequest) {
//		List<VehicleName> results = vehicleNameDao.getEntityManager()
//				.createQuery("SELECT VN FROM VehicleName VN where VN.vehicleType =:vehicleType "
//						+ "AND VN.vehicleBrand =:vehicleBrand")
//				.setParameter("vehicleType", vehicleRequest.getVehicleType())
//				.setParameter("vehicleBrand", vehicleRequest.getVehicleBrand())
//				.getResultList();
//		return results;
//	}

	public double CalculationByDistance(double initialLat, double initialLong, double finalLat, double finalLong)
			throws IOException {
		/* PRE: All the input values are in radians! */

		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder().url(
				"https://distance-calculator.p.rapidapi.com/distance/simple?lat_1=28.569588&long_1=77.323109&lat_2=28.628151&long_2=77.367783&unit=miles&decimal_places=2")
				.get().addHeader("Content-Type", "application/json")
				.addHeader("X-RapidAPI-Host", "distance-calculator.p.rapidapi.com")
				.addHeader("X-RapidAPI-Key", "SIGN-UP-FOR-KEY").build();

		Response response = client.newCall(request).execute();

		System.out.println(response);
		return finalLong;
	}

	public List<VehicleName> vehicleNameByType(VehicleRequestObject vehicleRequest) {
		List<VehicleName> results = vehicleNameDao.getEntityManager().createQuery(
				"SELECT COUNT(*), VN.vehicleName FROM VehicleName VN WHERE VN.vehicleType =:vehicleType GROUP BY VN.vehicleName")
				.setParameter("vehicleType", vehicleRequest.getVehicleType()).getResultList();
		return results;
	}

	public List<VehicleName> getGroupVehicleNameList(VehicleRequestObject vehicleRequest) {
		List<VehicleName> results = vehicleNameDao.getEntityManager()
				.createQuery("SELECT COUNT(*), VN.vehicleName FROM VehicleName VN GROUP BY VN.vehicleName")
				.getResultList();
		return results;
	}

	public List<VehicleName> getVehicleNameListByVehicleName(VehicleRequestObject vehicleRequest) {
		List<VehicleName> results = vehicleNameDao.getEntityManager()
				.createQuery("SELECT VN FROM VehicleName VN WHERE VN.vehicleName =:vehicleName")
				.setParameter("vehicleName", vehicleRequest.getVehicleName()).getResultList();
		return results;
	}

	public List<VehicleName> getVehiclePriceByVehicleNameAndTypeDetails(VehicleRequestObject vehicleRequest) {
		List<VehicleName> results = vehicleNameDao.getEntityManager().createQuery(
				"SELECT VN FROM VehicleName VN WHERE VN.vehicleName =:vehicleName AND VN.vehicleDetailsType =:vehicleDetailsType")
				.setParameter("vehicleName", vehicleRequest.getVehicleName())
				.setParameter("vehicleDetailsType", vehicleRequest.getVehicleDetailsType()).getResultList();
		return results;
	}

	public List<VehicleName> getAllVehicleNameList(VehicleRequestObject vehicleRequest) {
		List<VehicleName> results = vehicleNameDao.getEntityManager()
				.createQuery("SELECT VN FROM VehicleName VN ORDER BY VN.id DESC").getResultList();
		return results;
	}

	public List<UsersCity> getAllUserCityList(UserRequestObject userRequest) {
		List<UsersCity> results = userCityDao.getEntityManager().createQuery("SELECT UC FROM UsersCity UC")
				.getResultList();
		return results;
	}

	public List<UsersArea> getAllUserAreaList(UserRequestObject userRequest) {
		List<UsersArea> results = userAreaDao.getEntityManager()
				.createQuery("SELECT UC FROM UsersArea UC WHERE UC.city =:city")
				.setParameter("city", userRequest.getCity()).getResultList();
		return results;
	}

	public List<BookingDetails> getBookingDetailsByStatusAndPickupdate(VehicleRequestObject bookingRequest) {
		String sql = "";
		if ("BOOKED".equalsIgnoreCase(bookingRequest.getDateType())) {
		    sql = "SELECT UD FROM BookingDetails UD WHERE UD.status = :status " +
		          "AND UD.createdAt BETWEEN :startDate AND :endDate ORDER BY UD.id DESC";
		} else if ("PICKUP".equalsIgnoreCase(bookingRequest.getDateType())) {
		    sql = "SELECT UD FROM BookingDetails UD WHERE UD.status = :status " +
		          "AND UD.fromDate BETWEEN :startDate AND :endDate ORDER BY UD.id DESC";
		}
		
		System.out.println("SQL : "+sql);
		 
		List<BookingDetails> results = bookingDetailsDao.getEntityManager().createQuery(sql)
				.setParameter("status", bookingRequest.getStatus())
				.setParameter("startDate", bookingRequest.getFromDate(), TemporalType.DATE)
				.setParameter("endDate", bookingRequest.getToDate(), TemporalType.DATE).getResultList();
		return results;
	}

	@Transactional
	public BonusSlab getBonusSlabDetails(VehicleRequestObject vehicleRequest) {
		Criteria cr = bonusSlabDao.getSession().createCriteria(BonusSlab.class);
		cr.add(Restrictions.eq("startAmount", vehicleRequest.getStartAmount()));
		cr.add(Restrictions.eq("endAmount", vehicleRequest.getEndAmount()));
		cr.add(Restrictions.eq("bonusAmount", vehicleRequest.getBonusAmount()));
		cr.add(Restrictions.eq("memberType", vehicleRequest.getMemberType()));
		BonusSlab bonusSlab = (BonusSlab) cr.uniqueResult();
		return bonusSlab;
	}

	public BonusSlab getBonusSlabByReqObj(VehicleRequestObject vehicleRequest) {

		BonusSlab bonusSlab = new BonusSlab();

		bonusSlab.setStartAmount(vehicleRequest.getStartAmount());
		bonusSlab.setEndAmount(vehicleRequest.getEndAmount());
		bonusSlab.setBonusAmount(vehicleRequest.getBonusAmount());
		bonusSlab.setMemberType(vehicleRequest.getMemberType());
		bonusSlab.setBonusType(vehicleRequest.getBonusType());

		return bonusSlab;
	}

	@Transactional
	public BonusSlab saveBonusSlab(BonusSlab bonusSlab) {
		bonusSlabDao.persist(bonusSlab);
		return bonusSlab;
	}

	public List<BonusSlab> getBonusSlabList(VehicleRequestObject vehicleRequest) {

		List<BonusSlab> results = bonusSlabDao.getEntityManager().createQuery("SELECT UC FROM BonusSlab UC ")
				.getResultList();
		return results;
	}

	public List<BonusSlab> getBonusAmountByEarning(Long amount) {

		System.out.println("Entry in Query : " + (amount.toString()));

		List<BonusSlab> results = bonusSlabDao.getEntityManager().createQuery(
				"SELECT bonusAmount FROM BonusSlab UC WHERE UC.startAmount <=:amount AND UC.endAmount >=:amount AND memberType =:memberType")
				.setParameter("amount", amount).setParameter("memberType", "OLD").getResultList();

		return results;
	}

	public BonusSlab getBonusAmountByTotalSale(VehicleRequestObject vehicleRequest) {

	    List<BonusSlab> results = bonusSlabDao.getEntityManager()
	            .createQuery("SELECT UC FROM BonusSlab UC WHERE UC.startAmount <= :totalEarning AND UC.endAmount >= :totalEarning AND bonusType = :bonusType", BonusSlab.class)
	            .setParameter("totalEarning", vehicleRequest.getTotalEarning())
	            .setParameter("bonusType", vehicleRequest.getBonusType())
	            .getResultList();

	    if (!results.isEmpty()) {
	        return results.get(0);
	    } else {
	        // Handle the case where no BonusSlab is found
	        return null; // Or throw an exception or return a default BonusSlab object
	    }
	}

}
