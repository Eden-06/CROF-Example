package bank.impl;

import java.util.List;
import java.util.ArrayList;

import bank.*;

public class TransactionState{
	public double amount;
	public int creationTime;
	
	List<Bank.MoneyTransfer>  moneyTransfers=new ArrayList<Bank.MoneyTransfer>();
	public TransactionImpl compoundObject;
}
