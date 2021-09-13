package com.cg.wallet.model;

import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "WALLETTRANSACTION")
public class Transaction {

	@Id
	@GeneratedValue
	private long transactionId;
	private long transactionAmount;
	private String transactionType;
	private Timestamp transactionTime;
	private long beneficiaryId;

	
	private long userId;
	
	@ManyToOne
	@JoinColumn(name="userId", insertable = false, updatable = false)
//	@JoinColumn(name="userId", cascade = CascadeType.ALL)
	private User user;

	public Transaction() {
		this.transactionTime = new Timestamp(new Date().getTime());
	}

//	public Transaction(long transactionAmount, String transactionType, long beneficiaryId, long userId) {
//		this.beneficiaryId = beneficiaryId;
//		this.transactionAmount = transactionAmount;
//		this.transactionType = transactionType;
//		this.transactionTime = new Timestamp(new Date().getTime());
//		this.user = new User(userId, "", "", "");
//	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public long getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(long transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Timestamp getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Timestamp transactionTime) {
		this.transactionTime = transactionTime;
	}

	public long getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(long beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	
}
