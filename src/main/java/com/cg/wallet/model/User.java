package com.cg.wallet.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "WALLETUSER")
public class User {

	@Id
	@GeneratedValue
	private long userId;
	private String userName;
	private String userEmailId;
	private String userPassword;
	private long userBalance;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Transaction> transactions = new HashSet<>();
	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	public User() {

	}

	public User(long userId, String userName, String userEmailId, String userPassword) {
		this.userId = userId;
		this.userName = userName;
		this.userEmailId = userEmailId;
		this.userPassword = userPassword;
		this.userBalance = 0L;
	}
	
	public User(String userName, String userEmailId, String userPassword) {
		this.userName = userName;
		this.userEmailId = userEmailId;
		this.userPassword = userPassword;
		this.userBalance = 0L;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public long getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(long userBalance) {
		this.userBalance = userBalance;
	}

}
