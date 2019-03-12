package bank.impl;

import java.util.List;
import java.util.ArrayList;

import bank.*;

public class CompanyState{
	public String name;
	public String legalForm;
	public String POBox;
	public String addresses;
	
	transient List<Bank.Customer> bankCustomers=new ArrayList<Bank.Customer>();
	public Object compoundObject;
}
