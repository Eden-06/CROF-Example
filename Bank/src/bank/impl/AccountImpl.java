package bank.impl;

import java.util.List;
import java.util.Collections;

import bank.*;


public class AccountImpl implements Account{
	protected AccountState state;
	private final Model model;


	public AccountImpl(Model model) {
		this.state=new AccountState();
		this.state.compoundObject=this;
		this.model=model;
	}

	private Model getModel(){
		return this.model;
	}

	@Override
	public double getBalance(){
		return this.state.balance;
	}
	
	@Override
	public void setBalance(double balance){
		this.state.balance = balance;
	}
	@Override
	public int getId(){
		return this.state.id;
	}
	
	@Override
	public void setId(int id){
		this.state.id = id;
	}
	
	
	@Override
	public void increase(double amount){
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
	@Override
	public void decrease(double amount){
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
	
	

	@Override
	public Account getCompoundObject() {
		return (Account)this.state.compoundObject;
	}

	@Override
	public boolean isSame(Object object){
	    if(object instanceof AccountImpl)
			return this.state.equals(((AccountImpl) object).state);
		return false;
	}
	
	@Override
	public boolean hasBankSavingsAccount(Bank bank){
		for(int i=0;i<state.bankSavingsAccounts.size();i++){
			if(state.bankSavingsAccounts.get(i).getOwner().equals(bank)){
				return true;
			}
		}
		return false;
	}
	@Override
	public Bank.SavingsAccount getBankSavingsAccount(Bank bank){
		for(int i=0;i<state.bankSavingsAccounts.size();i++){
			if(state.bankSavingsAccounts.get(i).getOwner().equals(bank)){
				return this.state.bankSavingsAccounts.get(i);
			}
		}
		return null;
	}
	
	@Override
	public List<Bank.SavingsAccount> getBankSavingsAccounts(){
		return Collections.unmodifiableList(this.state.bankSavingsAccounts);
	}
	@Override
	public boolean addBankSavingsAccount(Bank.SavingsAccount savingsAccount) {
		if(state.bankSavingsAccounts.contains(savingsAccount))
				return false;
			return this.state.bankSavingsAccounts.add(savingsAccount);
	}
	@Override
	public boolean removeBankSavingsAccount(Bank bank){
		for(int i=0;i<state.bankSavingsAccounts.size();i++) {
				if(state.bankSavingsAccounts.get(i).getOwner().equals(bank)) {
					this.state.bankSavingsAccounts.remove(i);
					return true;
				}
			}
			return false;
	}
	@Override
	public boolean hasBankCheckingAccount(Bank bank){
		for(int i=0;i<state.bankCheckingAccounts.size();i++){
			if(state.bankCheckingAccounts.get(i).getOwner().equals(bank)){
				return true;
			}
		}
		return false;
	}
	@Override
	public Bank.CheckingAccount getBankCheckingAccount(Bank bank){
		for(int i=0;i<state.bankCheckingAccounts.size();i++){
			if(state.bankCheckingAccounts.get(i).getOwner().equals(bank)){
				return this.state.bankCheckingAccounts.get(i);
			}
		}
		return null;
	}
	
	@Override
	public List<Bank.CheckingAccount> getBankCheckingAccounts(){
		return Collections.unmodifiableList(this.state.bankCheckingAccounts);
	}
	@Override
	public boolean addBankCheckingAccount(Bank.CheckingAccount checkingAccount) {
		if(state.bankCheckingAccounts.contains(checkingAccount))
				return false;
			return this.state.bankCheckingAccounts.add(checkingAccount);
	}
	@Override
	public boolean removeBankCheckingAccount(Bank bank){
		for(int i=0;i<state.bankCheckingAccounts.size();i++) {
				if(state.bankCheckingAccounts.get(i).getOwner().equals(bank)) {
					this.state.bankCheckingAccounts.remove(i);
					return true;
				}
			}
			return false;
	}
	@Override
	public boolean hasTransactionSource(Transaction transaction){
		for(int i=0;i<state.transactionSources.size();i++){
			if(state.transactionSources.get(i).getOwner().equals(transaction)){
				return true;
			}
		}
		return false;
	}
	@Override
	public Transaction.Source getTransactionSource(Transaction transaction){
		for(int i=0;i<state.transactionSources.size();i++){
			if(state.transactionSources.get(i).getOwner().equals(transaction)){
				return this.state.transactionSources.get(i);
			}
		}
		return null;
	}
	
	@Override
	public List<Transaction.Source> getTransactionSources(){
		return Collections.unmodifiableList(this.state.transactionSources);
	}
	@Override
	public boolean addTransactionSource(Transaction.Source source) {
		if(state.transactionSources.contains(source))
				return false;
			return this.state.transactionSources.add(source);
	}
	@Override
	public boolean removeTransactionSource(Transaction transaction){
		for(int i=0;i<state.transactionSources.size();i++) {
				if(state.transactionSources.get(i).getOwner().equals(transaction)) {
					this.state.transactionSources.remove(i);
					return true;
				}
			}
			return false;
	}
	@Override
	public boolean hasTransactionTarget(Transaction transaction){
		for(int i=0;i<state.transactionTargets.size();i++){
			if(state.transactionTargets.get(i).getOwner().equals(transaction)){
				return true;
			}
		}
		return false;
	}
	@Override
	public Transaction.Target getTransactionTarget(Transaction transaction){
		for(int i=0;i<state.transactionTargets.size();i++){
			if(state.transactionTargets.get(i).getOwner().equals(transaction)){
				return this.state.transactionTargets.get(i);
			}
		}
		return null;
	}
	
	@Override
	public List<Transaction.Target> getTransactionTargets(){
		return Collections.unmodifiableList(this.state.transactionTargets);
	}
	@Override
	public boolean addTransactionTarget(Transaction.Target target) {
		if(state.transactionTargets.contains(target))
				return false;
			return this.state.transactionTargets.add(target);
	}
	@Override
	public boolean removeTransactionTarget(Transaction transaction){
		for(int i=0;i<state.transactionTargets.size();i++) {
				if(state.transactionTargets.get(i).getOwner().equals(transaction)) {
					this.state.transactionTargets.remove(i);
					return true;
				}
			}
			return false;
	}
	
}

