package com.Xposindia.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bonus_slab")
public class BonusSlab {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "start_amount")
	private Long startAmount;
	
	@Column(name = "end_amount")
	private Long endAmount;
	
	@Column(name = "bonus_amount")
	private Long bonusAmount;
	
	@Column(name = "bonus_type")
	private String bonusType;
	
	@Column(name = "member_type")
	private String memberType;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStartAmount() {
		return startAmount;
	}

	public void setStartAmount(Long startAmount) {
		this.startAmount = startAmount;
	}

	public Long getEndAmount() {
		return endAmount;
	}

	public void setEndAmount(Long endAmount) {
		this.endAmount = endAmount;
	}

	public Long getBonusAmount() {
		return bonusAmount;
	}

	public void setBonusAmount(Long bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public String getBonusType() {
		return bonusType;
	}

	public void setBonusType(String bonusType) {
		this.bonusType = bonusType;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
}
