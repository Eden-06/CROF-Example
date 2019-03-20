package bank.impl;

import java.util.List;
import java.util.ArrayList;


import bank.*;

public class ModelImpl implements Model{	
	private String model="bank";
	@Override
	public String getModelName(){
		return this.model;
	}
	private List<DateTime> dateTimes=new ArrayList<DateTime>();
	@Override
	public void setDateTime(List<DateTime> dateTimes){
		this.dateTimes=dateTimes;
	}
	@Override
	public List<DateTime> getDateTime(){
		return this.dateTimes;
	}
	@Override
	public DateTime createDateTime(){
		DateTimeImpl dateTime=new DateTimeImpl();
		this.dateTimes.add(dateTime);
		return dateTime;
	}
	@Override
	public int getIndex(DateTime dateTime) {
		return this.dateTimes.indexOf(dateTime);		
	}
	private List<Transaction> transactions=new ArrayList<Transaction>();
	@Override
	public void setTransaction(List<Transaction> transactions){
		this.transactions=transactions;
	}
	@Override
	public List<Transaction> getTransaction(){
		return this.transactions;
	}
	@Override
	public Transaction createTransaction(){
		TransactionImpl transaction=new TransactionImpl();
		this.transactions.add(transaction);
		return transaction;
	}
	@Override
	public int getIndex(Transaction transaction) {
		return this.transactions.indexOf(transaction);		
	}
	private List<Company> companys=new ArrayList<Company>();
	@Override
	public void setCompany(List<Company> companys){
		this.companys=companys;
	}
	@Override
	public List<Company> getCompany(){
		return this.companys;
	}
	@Override
	public Company createCompany(){
		CompanyImpl company=new CompanyImpl();
		this.companys.add(company);
		return company;
	}
	@Override
	public int getIndex(Company company) {
		return this.companys.indexOf(company);		
	}
	private List<Person> persons=new ArrayList<Person>();
	@Override
	public void setPerson(List<Person> persons){
		this.persons=persons;
	}
	@Override
	public List<Person> getPerson(){
		return this.persons;
	}
	@Override
	public Person createPerson(){
		PersonImpl person=new PersonImpl();
		this.persons.add(person);
		return person;
	}
	@Override
	public int getIndex(Person person) {
		return this.persons.indexOf(person);		
	}
	private List<Bank> banks=new ArrayList<Bank>();
	@Override
	public void setBank(List<Bank> banks){
		this.banks=banks;
	}
	@Override
	public List<Bank> getBank(){
		return this.banks;
	}
	@Override
	public Bank createBank(){
		BankImpl bank=new BankImpl();
		this.banks.add(bank);
		return bank;
	}
	@Override
	public int getIndex(Bank bank) {
		return this.banks.indexOf(bank);		
	}
	private List<Account> accounts=new ArrayList<Account>();
	@Override
	public void setAccount(List<Account> accounts){
		this.accounts=accounts;
	}
	@Override
	public List<Account> getAccount(){
		return this.accounts;
	}
	@Override
	public Account createAccount(){
		AccountImpl account=new AccountImpl();
		this.accounts.add(account);
		return account;
	}
	@Override
	public int getIndex(Account account) {
		return this.accounts.indexOf(account);		
	}
}
