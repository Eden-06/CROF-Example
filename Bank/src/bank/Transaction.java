package bank;

import java.util.List;

public interface Transaction{
	double getAmount();
	void setAmount(double amount);
	int getCreationTime();
	void setCreationTime(int creationTime);
	
	void execute();
	
	interface Target{
		
		void deposite(Object amount);
		
		Object getPlayer();
		boolean isSame(Object object);
		Transaction getOwner();
		List<Source> getTrans();
		boolean addTrans(Source source);
		
		
	}
	interface Source{
		
		void withdraw(Object amount);
		
		Object getPlayer();
		boolean isSame(Object object);
		Transaction getOwner();
		List<Target> getTrans();
		boolean addTrans(Target target);
		
		
	}
	
	interface Trans{
		boolean add(Source source, Target target);
		boolean remove(Source source, Target target);
		List<Target> getTargets(Source source);
		List<Source> getSources(Target target);
	}
	
	
	Target bindTarget(Account account);
	boolean unbindTarget(Account account);
	Source bindSource(Account account);
	boolean unbindSource(Account account);
	
	List<Account> getRole(Account account);
	
	boolean isSame(Object object);
	boolean hasMoneyTransfer(Bank bank);
	Bank.MoneyTransfer getMoneyTransfer(Bank bank);
	List<Bank.MoneyTransfer> getMoneyTransfers();
	boolean addBankMoneyTransfer(Bank.MoneyTransfer moneyTransfer);
	boolean removeBankMoneyTransfer(Bank bank);
	
	Transaction getCompoundObject();
	Model getModel();
}
