package com.Xposindia.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle_details")
public class VehicleDetails {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_id")
	private String userId;

	@Column(name = "vehicle_images1")
	private String vehicleImages1;
	
	@Column(name = "vehicle_images2")
	private String vehicleImages2;
	
	@Column(name = "Vehicle_brand")
	private String vehicleBrand;
	
	@Column(name = "vehicle_name")
	private String vehicleName;
	
	@Column(name = "vehicle_type")
	private String vehicleType;
	
	@Column(name = "vehicle_quantity")
	private int vehicleQuantity;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "area_from")
	private String areaFrom;
	
	@Lob
	@Column(name = "area_to")
	private String areaTo;
	
	@Lob
	@Column(name = "discription")
	private String discription;
	
	@Column(name = "vehicle_details_type")
	private String vehicleDetailsType;
	
	@Column(name = "vender_vehicle_price")
	private double venderVehiclePrice;
	
	@Column(name = "admin_vehicle_price")
	private double adminVehiclePrice;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getVehicleImages1() {
		return vehicleImages1;
	}

	public void setVehicleImages1(String vehicleImages1) {
		this.vehicleImages1 = vehicleImages1;
	}

	public String getVehicleImages2() {
		return vehicleImages2;
	}

	public void setVehicleImages2(String vehicleImages2) {
		this.vehicleImages2 = vehicleImages2;
	}

	public String getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public int getVehicleQuantity() {
		return vehicleQuantity;
	}

	public void setVehicleQuantity(int vehicleQuantity) {
		this.vehicleQuantity = vehicleQuantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAreaFrom() {
		return areaFrom;
	}

	public void setAreaFrom(String areaFrom) {
		this.areaFrom = areaFrom;
	}

	public String getAreaTo() {
		return areaTo;
	}

	public void setAreaTo(String areaTo) {
		this.areaTo = areaTo;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getVehicleDetailsType() {
		return vehicleDetailsType;
	}

	public void setVehicleDetailsType(String vehicleDetailsType) {
		this.vehicleDetailsType = vehicleDetailsType;
	}

	public double getVenderVehiclePrice() {
		return venderVehiclePrice;
	}

	public void setVenderVehiclePrice(double venderVehiclePrice) {
		this.venderVehiclePrice = venderVehiclePrice;
	}

	public double getAdminVehiclePrice() {
		return adminVehiclePrice;
	}

	public void setAdminVehiclePrice(double adminVehiclePrice) {
		this.adminVehiclePrice = adminVehiclePrice;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

}
