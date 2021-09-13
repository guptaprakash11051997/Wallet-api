package com.cg.wallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.wallet.exception.BalanceInSufficientException;
import com.cg.wallet.exception.UserAlreadyExistsException;
import com.cg.wallet.model.Transaction;
import com.cg.wallet.model.User;
import com.cg.wallet.service.ITransactionService;

@CrossOrigin(origins = "*")
@RestController
public class TransactionController {

	@Autowired
	ITransactionService serviceRef;
	
	@RequestMapping("/transaction/{userId}")
	public List<Transaction> getAllTransactions(@PathVariable long userId){
		return serviceRef.getTransactionList(userId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/transaction/add") 
//	public void addTransaction(@RequestBody Transaction transaction) {
	public Transaction addTransaction(@RequestBody Transaction transaction) throws BalanceInSufficientException {
		return serviceRef.addTransaction(transaction);
	}
	
	@RequestMapping("/transaction/id/{transactionId")
	public Transaction getTransactionById(@PathVariable long transactionId){
		return serviceRef.getTransactionById(transactionId);
	}
	@ExceptionHandler(value = BalanceInSufficientException.class)
	public ResponseEntity<Object> exceptionForBalanceInSufficient(Exception ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
}
