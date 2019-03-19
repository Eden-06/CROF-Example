package bank;

import java.util.List;


public interface Model {
	String getModelName();
	void setPerson(List<Person> persons);
	List<Person> getPerson();
	Person createPerson();
	int getIndex(Person person);
	void setCompany(List<Company> companys);
	List<Company> getCompany();
	Company createCompany();
	int getIndex(Company company);
	void setTransaction(List<Transaction> transactions);
	List<Transaction> getTransaction();
	Transaction createTransaction();
	int getIndex(Transaction transaction);
	void setBank(List<Bank> banks);
	List<Bank> getBank();
	Bank createBank();
	int getIndex(Bank bank);
	void setDateTime(List<DateTime> dateTimes);
	List<DateTime> getDateTime();
	DateTime createDateTime();
	int getIndex(DateTime dateTime);
	void setAccount(List<Account> accounts);
	List<Account> getAccount();
	Account createAccount();
	int getIndex(Account account);
}
