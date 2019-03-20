package bank;

import java.util.List;

public interface Account{

	
	double getBalance();
	void setBalance(double balance);
	int getId();
	void setId(int id);
	
	void increase(double amount);
	void decrease(double amount);
	
	boolean hasTransactionSource(Transaction transaction);
	Transaction.Source getTransactionSource(Transaction transaction);
	List<Transaction.Source> getTransactionSources();
	boolean addTransactionSource(Transaction.Source source);
	boolean removeTransactionSource(Transaction transaction);
	boolean hasBankCheckingAccount(Bank bank);
	Bank.CheckingAccount getBankCheckingAccount(Bank bank);
	List<Bank.CheckingAccount> getBankCheckingAccounts();
	boolean addBankCheckingAccount(Bank.CheckingAccount checkingAccount);
	boolean removeBankCheckingAccount(Bank bank);
	boolean hasBankSavingsAccount(Bank bank);
	Bank.SavingsAccount getBankSavingsAccount(Bank bank);
	List<Bank.SavingsAccount> getBankSavingsAccounts();
	boolean addBankSavingsAccount(Bank.SavingsAccount savingsAccount);
	boolean removeBankSavingsAccount(Bank bank);
	boolean hasTransactionTarget(Transaction transaction);
	Transaction.Target getTransactionTarget(Transaction transaction);
	List<Transaction.Target> getTransactionTargets();
	boolean addTransactionTarget(Transaction.Target target);
	boolean removeTransactionTarget(Transaction transaction);
	
	Account getCompoundObject();
	boolean isSame(Object object);
}
