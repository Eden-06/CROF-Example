package bank.impl;

import java.util.List;
import java.util.ArrayList;

import bank.*;

public class AccountState{
	public double balance;
	public int id;
	
	transient List<Transaction.Target> transactionTargets=new ArrayList<Transaction.Target>();
	transient List<Bank.SavingsAccount> bankSavingsAccounts=new ArrayList<Bank.SavingsAccount>();
	transient List<Transaction.Source> transactionSources=new ArrayList<Transaction.Source>();
	transient List<Bank.CheckingAccount> bankCheckingAccounts=new ArrayList<Bank.CheckingAccount>();
	public Object compoundObject;
}
