package bank.impl;

import java.util.List;
import java.util.ArrayList;

import bank.*;

public class PersonState{
	public String title;
	public String firstName;
	public String lastName;
	public String address;
	
	transient List<Bank.Customer> bankCustomers=new ArrayList<Bank.Customer>();
	transient List<Bank.Consultant> bankConsultants=new ArrayList<Bank.Consultant>();
	public Object compoundObject;
}
