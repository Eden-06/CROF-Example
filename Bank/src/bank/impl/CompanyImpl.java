package bank.impl;

import java.util.List;
import java.util.Collections;

import bank.*;


public class CompanyImpl implements Company{
	protected CompanyState state;
	private final Model model;


	public CompanyImpl(Model model) {
		this.state=new CompanyState();
		this.state.compoundObject=this;
		this.model=model;
	}

	private Model getModel(){
		return this.model;
	}

	@Override
	public String getPOBox(){
		return this.state.POBox;
	}
	
	@Override
	public void setPOBox(String POBox){
		this.state.POBox = POBox;
	}
	@Override
	public String getLegalForm(){
		return this.state.legalForm;
	}
	
	@Override
	public void setLegalForm(String legalForm){
		this.state.legalForm = legalForm;
	}
	@Override
	public String getName(){
		return this.state.name;
	}
	
	@Override
	public void setName(String name){
		this.state.name = name;
	}
	@Override
	public String getAddresses(){
		return this.state.addresses;
	}
	
	@Override
	public void setAddresses(String addresses){
		this.state.addresses = addresses;
	}
	
	
	
	

	@Override
	public Company getCompoundObject() {
		return (Company)this.state.compoundObject;
	}

	@Override
	public boolean isSame(Object object){
	    if(object instanceof CompanyImpl)
			return this.state.equals(((CompanyImpl) object).state);
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
	
}

