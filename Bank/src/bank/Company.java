package bank;

import java.util.List;

public interface Company{

	
	String getName();
	void setName(String name);
	String getLegalForm();
	void setLegalForm(String legalForm);
	String getPOBox();
	void setPOBox(String POBox);
	String getAddresses();
	void setAddresses(String addresses);
	
	
	boolean hasBankCustomer(Bank bank);
	Bank.Customer getBankCustomer(Bank bank);
	List<Bank.Customer> getBankCustomers();
	boolean addBankCustomer(Bank.Customer customer);
	boolean removeBankCustomer(Bank bank);
	
	Company getCompoundObject();
	boolean isSame(Object object);
}
