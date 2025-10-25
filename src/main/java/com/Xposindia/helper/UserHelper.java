package com.Xposindia.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.codec.binary.Base64;
import com.Xposindia.constant.Constant;
import com.Xposindia.dao.UserDao;

import com.Xposindia.entities.Users;
import com.Xposindia.enums.Status;
import com.Xposindia.expections.BizException;
import com.Xposindia.object.request.UserRequestObject;
import com.Xposindia.object.request.VehicleRequestObject;

@SuppressWarnings("unchecked")
@Component
public class UserHelper {
		
	@Autowired
	private UserDao userDao;
	
	
	/* Validate User Request Object */
	public void validateUserRequest(UserRequestObject userRequestObject) 
			throws BizException
	{ 
		if(userRequestObject == null) {
			throw new BizException(Constant.BAD_REQUEST_CODE, "Bad Request Object Null"); 
		}
	}
	
	/* Get User Details By Mobile Number */
	@Transactional
	public Users getUserDetailsByUserId(String userId)
	{ 
		Criteria cr = userDao.getSession().createCriteria(Users.class);
		cr.add(Restrictions.eq("userId", userId));
		Users  existUsers = (Users) cr.uniqueResult();
		return existUsers;
	}
	
	
	@Transactional
	public Users getUserDetailsByUserIdLoginId(VehicleRequestObject vehicleRequest)
	{ 
		Criteria cr = userDao.getSession().createCriteria(Users.class);
		cr.add(Restrictions.eq("userId", vehicleRequest.getCreatedBy()));
		Users  existUsers = (Users) cr.uniqueResult();
		return existUsers;
	}
	
	
	public Users getUserDetailsByObj(UserRequestObject userRequest)
	{ 
		Users userDetails = new Users();
		
		userDetails.setFullName(userRequest.getFullName());
		userDetails.setUserId(userRequest.getMobileNo());
		userDetails.setMobileNo(userRequest.getMobileNo());
		userDetails.setEmailId(userRequest.getEmailId());
		userDetails.setPassword(userRequest.getPassword());
		
		userDetails.setPincode("N/A");
		userDetails.setCity(userRequest.getCity());
		userDetails.setUserPhoto("N/A");
		userDetails.setPanImage("N/A");
		userDetails.setPanNumber("N/A");
		userDetails.setAadhaarImage("N/A");
		userDetails.setAadhaarNumber("N/A");
		userDetails.setUserStatus(Status.ACTIVE.name());
		userDetails.setMemberType(userRequest.getMemberType());
		userDetails.setServices("VEHICLE");
		userDetails.setParentDetails("[mpfaith]");
		if(userRequest.getMemberType().equals("SALE")) {
			userDetails.setPermission("dosale,sale-history,upcoming-vehicle,pickup-vehicle,enquiry,followup,lost,call-quality-details,");
			userDetails.setUserStatus(Status.ACTIVE.name());
		}else if(userRequest.getMemberType().equals("ADMIN")) {		
			userDetails.setPermission("dosale,sale-history,upcoming-vehicle,vehicle-list,");
			userDetails.setUserStatus(Status.ACTIVE.name());
		}else if(userRequest.getMemberType().equals("CCE")) {	
			userDetails.setPermission("dosale,sale-history,upcoming-vehicle,vehicle-list,pickup-vehicle,enquiry,followup,lost,");
			userDetails.setUserStatus(Status.ACTIVE.name());
		}else if(userRequest.getMemberType().equals("MANAGER")) {	
			userDetails.setPermission("cancel-request,,");
			userDetails.setUserStatus(Status.ACTIVE.name());
		}else {
			userDetails.setPermission("no permission");
			userDetails.setUserStatus(Status.INACTIVE.name());
		}
		userDetails.setCreatedAt(new Date());
		userDetails.setUpdatedAt(new Date());
		return userDetails;
	}
	
	@Transactional
	public Users saveUser(Users userDetails) 
	{ 
		userDao.persist(userDetails);
		return userDetails;
	}
	


	/* Set User Update Details By Request Object */
	@Transactional
	public Users setUpdatedUserDetailsByReqObj(UserRequestObject userRequest, Users userDetails)
	{
		userDetails.setFullName(userRequest.getFullName());
		userDetails.setCity(userRequest.getCity());
		userDetails.setMobileNo(userRequest.getMobileNo());
		userDetails.setEmailId(userRequest.getEmailId());
		userDetails.setUserPhoto(userRequest.getUserImage());
		userDetails.setPanImage(userRequest.getPanImage());
		userDetails.setPanNumber(userRequest.getPanNumber());
		userDetails.setAadhaarImage(userRequest.getAadhaarImage());
		userDetails.setAadhaarNumber(userRequest.getAadhaarNumber());
		userDetails.setParentDetails("[mpfaith]");
		userDetails.setPermission("no permission");
		userDetails.setMemberType("VENDOR");
		userDetails.setServices("DEPARTMENTAL");
		
		userDetails.setCreatedAt(new Date());
		userDetails.setUpdatedAt(new Date());
		
		userDetails.setUpdatedAt(new Date());
		return userDetails;
	}
	
	/* Update User Details */
	@Transactional
	public Users updateUser(Users userDetails)
	{ 
		userDao.update(userDetails);
		return userDetails;
	}
	
	/* Get User Login Details User ID Password */
	@Transactional
	public Users getLoginDetailsByUserIdAndPassword(UserRequestObject userRequest) 
	{
		Criteria cr = userDao.getSession().createCriteria(Users.class); 
		cr.add(Restrictions.eq("userId", userRequest.getUserId()));
		cr.add(Restrictions.eq("password",userRequest.getPassword() ));
		Users  existUsers = (Users) cr.uniqueResult();
		return existUsers;
	}
	
	
	/**
	 * Get Server Url **/
	public String getServerPath(HttpServletRequest request) throws Exception {
		URL url = new URL(request.getRequestURL().toString());
		String serverPath = url.getProtocol() + "://" + url.getHost() + ":" + url.getPort();
		return serverPath;
	}
	

	/** Upload Photos from base 64 **/
	public String getPathToUploadFile(String Type) { // Use
		String pathtoUploads;
		if (Type.equalsIgnoreCase("USER")) {
			pathtoUploads = Constant.docLocation + Constant.userPicPath;
		} else if (Type.equalsIgnoreCase("Proof")) {
			pathtoUploads = Constant.docLocation + Constant.proofPicPath;
		} else if (Type.equalsIgnoreCase("VehicleImage")) {
			pathtoUploads = Constant.docLocation + Constant.VehicleImage;
		}else if (Type.equalsIgnoreCase("AadhharImage")) {
			pathtoUploads = Constant.docLocation + Constant.AadhharImage;
		}else if (Type.equalsIgnoreCase("RECIEPT")) {
			pathtoUploads = Constant.docLocation + Constant.receipt;
		}else if (Type.equalsIgnoreCase("VehicleNumberImage")) {
			pathtoUploads = Constant.docLocation + Constant.VehicleNumberImage;
		}
		else {
			pathtoUploads = Constant.docLocation + Constant.defaultPath;
		}
		if (!new File(pathtoUploads).exists()) {
			File dir = new File(pathtoUploads);
			dir.mkdirs();
		}
		Path path = Paths.get(pathtoUploads);
		return path.toString();
	}

	
	@SuppressWarnings({ "rawtypes"})
	public String uploadPhotos(String file, String fileName) {
		try {
			String fileBasefile = "";
			String[] values = file.split(",");
			ArrayList list = new ArrayList(Arrays.asList(values));
			if (list.size() >= 2) {
				fileBasefile = (String) list.get(1);
			} else {
				fileBasefile = fileName;
			}
			byte[] imageByteArray = Base64.decodeBase64(fileBasefile);
			FileOutputStream imageOutFile = new FileOutputStream(fileName);
			imageOutFile.write(imageByteArray);
			imageOutFile.close();
		} catch (Exception e) {
			e.printStackTrace();
			PrintWriter writer;
			try {
				writer = new PrintWriter("/var/lib/tomcat8/webapps/test.txt", "UTF-8");
				writer.println(e.getMessage());
				writer.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return fileName;
	}

	public List<Users> getAllUser(String userId) {
		List<Users> results = userDao.getEntityManager()
				.createQuery(" SELECT UD FROM Users UD Where UD.memberType NOT IN(:superadmin, :super2admin) ORDER BY UD.id DESC")
//				.createQuery(" SELECT UD FROM Users UD Where UD.memberType =:memberType ORDER BY UD.id DESC")
				.setParameter("superadmin","SUPERADMIN" )
				.setParameter("super2admin","SUPER2ADMIN" )
				.getResultList();
		return results;
	}
	
	public List<Users> getAllAdmin() {
		List<Users> results = userDao.getEntityManager()
				.createQuery("SELECT UD FROM Users UD WHERE UD.memberType =:memberType ORDER BY UD.id DESC")
				.setParameter("memberType","ADMIN" )
				.getResultList();
		return results;
	}
	
	public List<Users> getAllSaleUser() {
		List<Users> results = userDao.getEntityManager()
				.createQuery(" SELECT UD FROM Users UD Where UD.memberType =:memberType AND UD.userStatus =:userStatus ORDER BY UD.id DESC")
				.setParameter("memberType","SALE" )
				.setParameter("userStatus","ACTIVE" )
				.getResultList();
		return results;
	}
	
	public List<Users> getAllUserExceptVendor(UserRequestObject userRequest) {
		if(userRequest.getUserRole().equalsIgnoreCase("SUPER2ADMIN")) {
			List<Users> results = userDao.getEntityManager()
					.createQuery(" SELECT UD FROM Users UD Where UD.memberType NOT IN:memberType AND UD.userStatus =:userStatus ORDER BY UD.id DESC")
					.setParameter("memberType","VENDOR" )
					.setParameter("userStatus","ACTIVE" )
					.getResultList();
			return results;
		}else {
			List<Users> results = userDao.getEntityManager()
					.createQuery(" SELECT UD FROM Users UD Where UD.memberType NOT IN:memberType AND UD.userId NOT IN(:rohan,:rashmi,:akshi,:serena, :mohsin) AND UD.userStatus =:userStatus ORDER BY UD.id DESC")
					.setParameter("memberType","VENDOR" )
					.setParameter("userStatus","ACTIVE" )
					.setParameter("rohan", "7845273233")
	        		.setParameter("rashmi", "9619283833")
	        		.setParameter("akshi", "7694848781")
	        		.setParameter("serena", "7718858883")
	        		.setParameter("mohsin", "9833220378")
					.getResultList();
			return results;
		}
		
	}
	
	public List<Users> getAllVendorUser(UserRequestObject userRequest) {
		List<Users> results = userDao.getEntityManager()
				.createQuery(" SELECT UD FROM Users UD Where UD.memberType =:memberType AND UD.userStatus =:userStatus AND UD.city =:city ORDER BY UD.id DESC")
				.setParameter("memberType","VENDOR" )
				.setParameter("userStatus", "ACTIVE")
				.setParameter("city", userRequest.getCity())
				.getResultList();
		return results;
	}
	
	
	public List<Users> getAllDeliveryAgent(UserRequestObject userRequest) {
		List<Users> results = userDao.getEntityManager()
				.createQuery(" SELECT UD FROM Users UD Where UD.memberType =:memberType AND UD.userStatus =:userStatus ORDER BY UD.id DESC")
				.setParameter("memberType","DELIVERY_EXECUTIVE" )
				.setParameter("userStatus", "ACTIVE")
				.getResultList();
		return results;
	}
	
	public List<Users> getUserByUserId(String userId) {
		List<Users> results = userDao.getEntityManager()
				.createQuery("SELECT UD FROM Users UD where UD.userId =:userId ORDER BY UD.id DESC")
				.setParameter("userId", userId)
				.getResultList();
		return results;
	}
	
	public List<Users> getTotalUser() {
		List<Users> results = userDao.getEntityManager()
				.createQuery("SELECT count(*) FROM Users UD Where UD.memberType NOT IN:memberType")
				.setParameter("memberType","ADMIN" )
				.getResultList();
		return results;
	}
	
	public List<Users> getTotalPendingUser() {
		List<Users> results = userDao.getEntityManager()
				.createQuery(" SELECT count(*) FROM Users UD Where UD.memberType NOT IN:memberType AND UD.userStatus =:userStatus")
				.setParameter("memberType","ADMIN")
				.setParameter("userStatus","INACTIVE")
				.getResultList();
		return results;
	}

}
