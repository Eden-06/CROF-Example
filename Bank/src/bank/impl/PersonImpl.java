package bank.impl;

import java.util.List;
import java.util.Collections;

import bank.*;


public class PersonImpl implements Person{
	protected PersonState state;
	private final Model model;


	public PersonImpl(Model model) {
		this.state=new PersonState();
		this.state.compoundObject=this;
		this.model=model;
	}

	private Model getModel(){
		return this.model;
	}

	@Override
	public String getLastName(){
		return this.state.lastName;
	}
	
	@Override
	public void setLastName(String lastName){
		this.state.lastName = lastName;
	}
	@Override
	public String getTitle(){
		return this.state.title;
	}
	
	@Override
	public void setTitle(String title){
		this.state.title = title;
	}
	@Override
	public String getFirstName(){
		return this.state.firstName;
	}
	
	@Override
	public void setFirstName(String firstName){
		this.state.firstName = firstName;
	}
	@Override
	public String getAddress(){
		return this.state.address;
	}
	
	@Override
	public void setAddress(String address){
		this.state.address = address;
	}
	
	
	
	

	@Override
	public Person getCompoundObject() {
		return (Person)this.state.compoundObject;
	}

	@Override
	public boolean isSame(Object object){
	    if(object instanceof PersonImpl)
			return this.state.equals(((PersonImpl) object).state);
		return false;
	}
	
	@Override
	public boolean hasBankCustomer(Bank bank){
		for(int i=0;i<state.bankCustomers.size();i++){
			if(state.bankCustomers.get(i).getOwner().equals(bank)){
				return true;
			}
		}
		return false;
	}
	@Override
	public Bank.Customer getBankCustomer(Bank bank){
		for(int i=0;i<state.bankCustomers.size();i++){
			if(state.bankCustomers.get(i).getOwner().equals(bank)){
				return this.state.bankCustomers.get(i);
			}
		}
		return null;
	}
	
	@Override
	public List<Bank.Customer> getBankCustomers(){
		return Collections.unmodifiableList(this.state.bankCustomers);
	}
	@Override
	public boolean addBankCustomer(Bank.Customer customer) {
		if(state.bankCustomers.contains(customer))
				return false;
			return this.state.bankCustomers.add(customer);
	}
	@Override
	public boolean removeBankCustomer(Bank bank){
		for(int i=0;i<state.bankCustomers.size();i++) {
				if(state.bankCustomers.get(i).getOwner().equals(bank)) {
					this.state.bankCustomers.remove(i);
					return true;
				}
			}
			return false;
	}
	@Override
	public boolean hasBankConsultant(Bank bank){
		for(int i=0;i<state.bankConsultants.size();i++){
			if(state.bankConsultants.get(i).getOwner().equals(bank)){
				return true;
			}
		}
		return false;
	}
	@Override
	public Bank.Consultant getBankConsultant(Bank bank){
		for(int i=0;i<state.bankConsultants.size();i++){
			if(state.bankConsultants.get(i).getOwner().equals(bank)){
				return this.state.bankConsultants.get(i);
			}
		}
		return null;
	}
	
	@Override
	public List<Bank.Consultant> getBankConsultants(){
		return Collections.unmodifiableList(this.state.bankConsultants);
	}
	@Override
	public boolean addBankConsultant(Bank.Consultant consultant) {
		if(state.bankConsultants.contains(consultant))
				return false;
			return this.state.bankConsultants.add(consultant);
	}
	@Override
	public boolean removeBankConsultant(Bank bank){
		for(int i=0;i<state.bankConsultants.size();i++) {
				if(state.bankConsultants.get(i).getOwner().equals(bank)) {
					this.state.bankConsultants.remove(i);
					return true;
				}
			}
			return false;
	}
	
}

