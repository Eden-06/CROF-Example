package bank;

import java.util.List;

public interface Bank{
	String getName();
	void setName(String name);
	
	void transfer(int sourceId, Account target, double amount);
	
	interface Customer{
		int getId();
		void setId(int id);
		
		String getName();
		
		Object getPlayer();
		boolean isSame(Object object);
		Bank getOwner();
		List<CheckingAccount> getOwn_ca();
		boolean addOwn_ca(CheckingAccount checkingAccount);
		
		List<SavingsAccount> getOwn_sa();
		boolean addOwn_sa(SavingsAccount savingsAccount);
		
		List<Consultant> getAdvises();
		boolean addAdvises(Consultant consultant);
		
		
	}
	interface SavingsAccount{
		double getFee();
		void setFee(double fee);
		
		void decrease(double amount);
		
		Object getPlayer();
		boolean isSame(Object object);
		Bank getOwner();
		List<Customer> getOwn_sa();
		boolean addOwn_sa(Customer customer);
		
		
	}
	interface MoneyTransfer{
		int getExecution();
		void setExecution(int execution);
		
		
		Object getPlayer();
		boolean isSame(Object object);
		Bank getOwner();
		
	}
	interface CheckingAccount{
		double getLimit();
		void setLimit(double limit);
		
		void decrease(double amount);
		
		Object getPlayer();
		boolean isSame(Object object);
		Bank getOwner();
		List<Customer> getOwn_ca();
		boolean addOwn_ca(Customer customer);
		
		
	}
	interface Consultant{
		String getPhone();
		void setPhone(String phone);
		
		
		Object getPlayer();
		boolean isSame(Object object);
		Bank getOwner();
		List<Customer> getAdvises();
		boolean addAdvises(Customer customer);
		
		
	}
	
	interface Advises{
		boolean add(Consultant consultant, Customer customer);
		boolean remove(Consultant consultant, Customer customer);
		List<Customer> getCustomers(Consultant consultant);
		List<Consultant> getConsultants(Customer customer);
	}
	
	interface Own_ca{
		boolean add(Customer customer, CheckingAccount checkingAccount);
		boolean remove(Customer customer, CheckingAccount checkingAccount);
		List<CheckingAccount> getCheckingAccounts(Customer customer);
		List<Customer> getCustomers(CheckingAccount checkingAccount);
	}
	interface Own_sa{
		boolean add(Customer customer, SavingsAccount savingsAccount);
		boolean remove(Customer customer, SavingsAccount savingsAccount);
		List<SavingsAccount> getSavingsAccounts(Customer customer);
		List<Customer> getCustomers(SavingsAccount savingsAccount);
	}
	
	Customer bindCustomer(Person person);
	boolean unbindCustomer(Person person);
	Customer bindCustomer(Company company);
	boolean unbindCustomer(Company company);
	SavingsAccount bindSavingsAccount(Account account);
	boolean unbindSavingsAccount(Account account);
	MoneyTransfer bindMoneyTransfer(Transaction transaction);
	boolean unbindMoneyTransfer(Transaction transaction);
	CheckingAccount bindCheckingAccount(Account account);
	boolean unbindCheckingAccount(Account account);
	Consultant bindConsultant(Person person);
	boolean unbindConsultant(Person person);
	
	List<Account> getRole(Account account);
	MoneyTransfer getRole(Transaction transaction);
	List<Person> getRole(Person person);
	Customer getRole(Company company);
	
	Model getModel();
}
