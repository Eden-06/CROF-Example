package bank;

import java.util.List;

public interface Person{

	
	String getTitle();
	void setTitle(String title);
	String getFirstName();
	void setFirstName(String firstName);
	String getLastName();
	void setLastName(String lastName);
	String getAddress();
	void setAddress(String address);
	
	
	boolean hasBankConsultant(Bank bank);
	Bank.Consultant getBankConsultant(Bank bank);
	List<Bank.Consultant> getBankConsultants();
	boolean addBankConsultant(Bank.Consultant consultant);
	boolean removeBankConsultant(Bank bank);
	boolean hasBankCustomer(Bank bank);
	Bank.Customer getBankCustomer(Bank bank);
	List<Bank.Customer> getBankCustomers();
	boolean addBankCustomer(Bank.Customer customer);
	boolean removeBankCustomer(Bank bank);
	
	Person getCompoundObject();
	boolean isSame(Object object);
}
