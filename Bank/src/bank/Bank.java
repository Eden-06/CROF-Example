package bank;

import java.util.List;

public interface Bank{
	String getName();
	void setName(String name);
	
	void transfer(int sourceId, Account target, double amount);
	
	interface MoneyTransfer{
		int getExecution();
		void setExecution(int execution);
		
		
		Object getPlayer();
		boolean isSame(Object object);
		Bank getOwner();
		
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
	interface Customer{
		int getId();
		void setId(int id);
		
		String getName();
		
		Object getPlayer();
		boolean isSame(Object object);
		Bank getOwner();
		List<Consultant> getAdvises();
		boolean addAdvises(Consultant consultant);
		
		List<CheckingAccount> getOwn_ca();
		boolean addOwn_ca(CheckingAccount checkingAccount);
		
		List<SavingsAccount> getOwn_sa();
		boolean addOwn_sa(SavingsAccount savingsAccount);
		
		
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
	
	MoneyTransfer bindMoneyTransfer(Transaction transaction);
	boolean unbindMoneyTransfer(Transaction transaction);
	Consultant bindConsultant(Person person);
	boolean unbindConsultant(Person person);
	CheckingAccount bindCheckingAccount(Account account);
	boolean unbindCheckingAccount(Account account);
	Customer bindCustomer(Person person);
	boolean unbindCustomer(Person person);
	Customer bindCustomer(Company company);
	boolean unbindCustomer(Company company);
	SavingsAccount bindSavingsAccount(Account account);
	boolean unbindSavingsAccount(Account account);
	
	List<Person> getRole(Person person);
	Customer getRole(Company company);
	MoneyTransfer getRole(Transaction transaction);
	List<Account> getRole(Account account);
	
}
