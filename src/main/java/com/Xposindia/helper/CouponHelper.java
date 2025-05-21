package com.Xposindia.helper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.Xposindia.constant.Constant;
import com.Xposindia.dao.CallQualityDetailsDao;
import com.Xposindia.dao.CouponDetailsDao;
import com.Xposindia.entities.CallQualityDetails;
import com.Xposindia.entities.CouponDetails;
import com.Xposindia.entities.Customer;
import com.Xposindia.expections.BizException;
import com.Xposindia.object.request.CouponRequestObject;
import com.Xposindia.object.request.QualityRequestObject;

@Component
public class CouponHelper {
	
	@Autowired
	private CouponDetailsDao couponDetailsDao;

	public void validateCouponRequest(CouponRequestObject couponRequest) 
			throws BizException
	{ 
		if(couponRequest == null) {
			throw new BizException(Constant.BAD_REQUEST_CODE, "Bad Request Object Null"); 
		}
	}
	
	
	@Transactional
	public CouponDetails getCouponDetailsByCoupon(String coupon)
	{ 
		Criteria cr = couponDetailsDao.getSession().createCriteria(CouponDetails.class);
		cr.add(Restrictions.eq("coupon", coupon));
		CouponDetails  couponDetails = (CouponDetails) cr.uniqueResult();
		return couponDetails;
	}
	
//	@Transactional
//	public CouponDetails getCouponDetailsByCouponNByStatus(String coupon, String status)
//	{ 
//		Criteria cr = couponDetailsDao.getSession().createCriteria(CouponDetails.class);
//		cr.add(Restrictions.eq("coupon", coupon));
//		cr.add(Restrictions.eq("status", status));
//		CouponDetails  couponDetails = (CouponDetails) cr.uniqueResult();
//		return couponDetails;
//	}
	
	@Transactional
	public CouponDetails getValidCouponDetails(String coupon) {
	    Criteria cr = couponDetailsDao.getSession().createCriteria(CouponDetails.class);
	    cr.add(Restrictions.eq("coupon", coupon));
	    cr.add(Restrictions.eq("status", "active"));
	    cr.add(Restrictions.ltProperty("usedLimit", "couponLimit"));

	    // Ensure current date is before or equal to couponExpiredOn
	    cr.add(Restrictions.ge("couponExpiredOn", new Date()));

	    return (CouponDetails) cr.uniqueResult();
	}
	
	
	public CouponDetails getCouponDetailsByReqObj(CouponRequestObject couponRequest) 
	{ 
		CouponDetails couponDetails = new CouponDetails();
		
		couponDetails.setCoupon(couponRequest.getCoupon());
		couponDetails.setCouponAmount(couponRequest.getCouponAmount());
		couponDetails.setCouponType(couponRequest.getCouponType());
		couponDetails.setCouponLimit(couponRequest.getCouponLimit());
		couponDetails.setCouponExpiredOn(couponRequest.getCouponExpiredOn());
		couponDetails.setCreatedBy(couponRequest.getCreatedBy());
		couponDetails.setCreatedAt(new Date());

		return couponDetails;
	}
	
	@Transactional
	public CouponDetails saveCouponDetails(CouponDetails couponDetails) 
	{ 
		couponDetailsDao.persist(couponDetails);
		return couponDetails;
	}

	@SuppressWarnings("unchecked")
	public List<CouponDetails> getCouponDetails(CouponRequestObject couponRequest) {
		if(couponRequest.getRequestFor().equalsIgnoreCase("BYUSERID")) {
			List<CouponDetails> results = couponDetailsDao.getEntityManager()
					.createQuery("SELECT CD FROM CouponDetails CD WHERE CD.createdBy =:createdBy ORDER BY CD.id DESC")
					.setParameter("createdBy", couponRequest.getCreatedBy())
					.getResultList();
			return results;
		}
		return null;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<CallQualityDetails> getTotalScoreForCallQualityDetails(QualityRequestObject qualityRequest) {
//		Date date = new Date();
//		Date nextDay = Date.from(date.toInstant().plus(1, ChronoUnit.DAYS));
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

		c.set(Calendar.DAY_OF_MONTH, 1);
		Date monthFirstDay = c.getTime();

		c.set(Calendar.DATE, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date monthLastDay = c.getTime();
		
//		if(qualityRequest.getRequestFor().equalsIgnoreCase("SCORE")) {
//			List<CallQualityDetails> results = callQualityDetailsDao.getEntityManager()
//					.createQuery("SELECT SUM(score) AS score from CallQualityDetails WHERE userId =:userId AND createdAt BETWEEN :currentDate AND :lastDate")
//					.setParameter("userId", qualityRequest.getUserId())
//					.setParameter("currentDate", monthFirstDay, TemporalType.DATE)
//					.setParameter("lastDate", monthLastDay, TemporalType.DATE)
//					.getResultList();
//			return results;
//		}
		return null;
	}
	
	
}
