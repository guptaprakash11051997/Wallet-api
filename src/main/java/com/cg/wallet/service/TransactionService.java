package com.cg.wallet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.wallet.dao.ITransactionDAO;
import com.cg.wallet.dao.IUserDAO;
import com.cg.wallet.exception.BalanceInSufficientException;
import com.cg.wallet.exception.UserAlreadyExistsException;
import com.cg.wallet.model.Transaction;

@Service
public class TransactionService implements ITransactionService{

	@Autowired
	private ITransactionDAO daoRef;
	
	@Autowired
	private IUserDAO daoRefWallet;
	
	@Autowired
	IUserService serviceRef;
	
	@Override
	public List<Transaction> getTransactionList(long userId) {
		return daoRef.findByUserUserId(userId);
	}

	@Override
	public Transaction addTransaction(Transaction trans) throws BalanceInSufficientException {
		if(trans.getTransactionType().equalsIgnoreCase("deposit"))
		{
			trans.getUser().setUserBalance((trans.getUser().getUserBalance())+(trans.getTransactionAmount()));
			daoRefWallet.save(trans.getUser());
		//	System.out.println(trans.getUser().setUserBalance(trans.getUser().getUserBalance()+trans.getTransactionAmount()));
			
			return daoRef.save(trans);
		}
		else if (trans.getTransactionType().equalsIgnoreCase("transfer"))
		{
			long userBal=serviceRef.getUserById(trans.getUserId()).getUserBalance();
			long transferBal=trans.getTransactionAmount();
			if(userBal>transferBal)
			{
				serviceRef.getUserById(trans.getUserId()).setUserBalance(serviceRef.getUserById(trans.getUserId()).getUserBalance()-trans.getTransactionAmount());
				serviceRef.getUserById(trans.getBeneficiaryId()).setUserBalance(serviceRef.getUserById(trans.getBeneficiaryId()).getUserBalance()+trans.getTransactionAmount());
				daoRefWallet.save(serviceRef.getUserById(trans.getUserId()));
				daoRefWallet.save(serviceRef.getUserById(trans.getBeneficiaryId()));
			}
			else
			{
				throw new BalanceInSufficientException("Balance is insufficient");
			}
		}
		return daoRef.save(trans);
	}

	@Override
	public Transaction getTransactionById(long transactionId) {
		return daoRef.getOne(transactionId);
	}

}
