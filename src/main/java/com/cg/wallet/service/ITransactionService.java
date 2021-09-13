package com.cg.wallet.service;

import java.util.List;

import com.cg.wallet.exception.BalanceInSufficientException;
import com.cg.wallet.model.Transaction;

public interface ITransactionService {
	
	List<Transaction> getTransactionList(long userId);
	Transaction addTransaction(Transaction trans) throws BalanceInSufficientException;
	Transaction getTransactionById(long transactionId);
	
}
