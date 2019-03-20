package bank.impl;

import java.util.List;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

import bank.*;

public class TransactionImpl implements Transaction{
	TransactionState state;
	public TransactionImpl() {
		state=new TransactionState();
		state.compoundObject=this;
	}

	@Override
	public Transaction getCompoundObject() {
		return this.state.compoundObject;
	}

	
	transient List<Source> accountSources=new ArrayList<Source>();
	public List<Source> getAccountSources() {
		return accountSources;
	}
	public void setAccountSources(List<Source> accountSources) {
		this.accountSources = accountSources;
	}
	
	transient List<Target> accountTargets=new ArrayList<Target>();
	public List<Target> getAccountTargets() {
		return accountTargets;
	}
	public void setAccountTargets(List<Target> accountTargets) {
		this.accountTargets = accountTargets;
	}
	
	
	private TransImpl transExtent=new TransImpl();
	
		
	
	private double amount;
	private int creationTime;
	
	
	@Override
	public double getAmount(){
		return amount;
	}
	
	@Override
	public void setAmount(double amount){
		this.amount = amount;
	}
	@Override
	public int getCreationTime(){
		return creationTime;
	}
	
	@Override
	public void setCreationTime(int creationTime){
		this.creationTime = creationTime;
	}
	
	
	@Override
	public void execute(){
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
	
	
	public class AccountSource extends AccountImpl implements Source{
		
	
		public AccountSource(){
			super();
		}
		public AccountSource(Account account){
			super();
			this.state=((AccountImpl)account).state;
		}
		
		@Override
		public void withdraw(Object amount){
			throw new UnsupportedOperationException("Invalid operation for sorted list.");
		}
		
		
		@Override
		public Transaction getOwner() {
			return TransactionImpl.this;
		}
		@Override
		public Account getPlayer() {
			return (Account)getCompoundObject();
		}
		@Override
		public List<Target> getTrans(){
			return transExtent.getTargets(this);
		}
		@Override
		public boolean addTrans(Target target){
			return transExtent.add(this,target);
		}
		
		
	
	}
	public class AccountTarget extends AccountImpl implements Target{
		
	
		public AccountTarget(){
			super();
		}
		public AccountTarget(Account account){
			super();
			this.state=((AccountImpl)account).state;
		}
		
		@Override
		public void deposite(Object amount){
			throw new UnsupportedOperationException("Invalid operation for sorted list.");
		}
		
		
		@Override
		public Transaction getOwner() {
			return TransactionImpl.this;
		}
		@Override
		public Account getPlayer() {
			return (Account)getCompoundObject();
		}
		@Override
		public List<Source> getTrans(){
			return transExtent.getSources(this);
		}
		@Override
		public boolean addTrans(Source source){
			return transExtent.add(source,this);
		}
		
		
	
	}
	
	
	public class TransImpl implements Trans{
		List<Map.Entry<Source, Target>> trans=new ArrayList<Map.Entry<Source, Target>>();
		
		@Override
		public boolean remove(Source source, Target target){
			for(int i = 0;i<trans.size();i++) {
				Map.Entry<Source, Target> entry=trans.get(i);
				if(entry.getKey().equals(source)&&entry.getValue().equals(target)){
					trans.remove(i);
					return true;
				}
			}
			return false;
		}
		
		@Override
		public List<Target> getTargets(Source source){
			List<Target> targets=new ArrayList<Target>();
			for(int i = 0;i<trans.size();i++) {
				Map.Entry<Source, Target> entry=trans.get(i);
				if(entry.getKey().equals(source)){
					targets.add(entry.getValue());
				}
			}
			return targets;
		}
		@Override
		public List<Source> getSources(Target target){
			List<Source> sources=new ArrayList<Source>();
			for(int i = 0;i<trans.size();i++) {
				Map.Entry<Source, Target> entry=trans.get(i);
				if(entry.getValue().equals(target)){
					sources.add(entry.getKey());
				}
			}		
			return sources;
		}
		@Override
		public boolean add(Source source, Target target){
			if(source.isSame(target))
				return false;
			return trans.add(new AbstractMap.SimpleEntry<Source, Target>(source,target));
		}
	}
	
	
	
	@Override
	public Source bindSource(Account account){
		for (int i = 0; i < accountSources.size(); i++) {
			if (accountSources.get(i).isSame(account)) {
				return accountSources.get(i);
			}
		}
		AccountSource role = new AccountSource(account);
		accountSources.add(role);
		((AccountImpl) account).addTransactionSource(role);
		return role;
	}
	@Override
	public boolean unbindSource(Account account){
		for (int i = 0; i < accountSources.size(); i++) {
			if (accountSources.get(i).isSame(account)) {
				accountSources.remove(i);
				return account.removeTransactionSource(this);
			}
		}
		return false;
	}
	@Override
	public Target bindTarget(Account account){
		for (int i = 0; i < accountTargets.size(); i++) {
			if (accountTargets.get(i).isSame(account)) {
				return accountTargets.get(i);
			}
		}
		AccountTarget role = new AccountTarget(account);
		accountTargets.add(role);
		((AccountImpl) account).addTransactionTarget(role);
		return role;
	}
	@Override
	public boolean unbindTarget(Account account){
		for (int i = 0; i < accountTargets.size(); i++) {
			if (accountTargets.get(i).isSame(account)) {
				accountTargets.remove(i);
				return account.removeTransactionTarget(this);
			}
		}
		return false;
	}
	
	@Override
	public List<Account> getRole(Account account){
		List<Account> list=new ArrayList<Account>();
		for (int i = 0; i < accountSources.size(); i++) {
			if (accountSources.get(i).isSame(account)) {
				list.add((Account)accountSources.get(i));break;
			}
		}
		for (int i = 0; i < accountTargets.size(); i++) {
			if (accountTargets.get(i).isSame(account)) {
				list.add((Account)accountTargets.get(i));break;
			}
		}
		return list;
	}
	
	@Override
	public boolean isSame(Object object){
		if(object instanceof BankImpl.TransactionMoneyTransfer)
			return this.state.equals(((BankImpl.TransactionMoneyTransfer)object).state);
		return false;
	}
	@Override
	public boolean hasMoneyTransfer(Bank bank){
		for(int i=0;i<state.moneyTransfers.size();i++){
			if(state.moneyTransfers.get(i).getOwner().equals(bank)){
				return true;
			}
		}
		return false;
	}
	@Override
	public Bank.MoneyTransfer getMoneyTransfer(Bank bank){
		for(int i=0;i<state.moneyTransfers.size();i++){
			if(state.moneyTransfers.get(i).getOwner().equals(bank)){
				return state.moneyTransfers.get(i);
			}
		}
		return null;
	}
	@Override
	public List<Bank.MoneyTransfer> getMoneyTransfers(){
		return state.moneyTransfers;
	}
	@Override
	public boolean addBankMoneyTransfer(Bank.MoneyTransfer moneyTransfer) {
		if(state.moneyTransfers.contains(moneyTransfer))
				return false;
			return state.moneyTransfers.add(moneyTransfer);
	}
	@Override
	public boolean removeBankMoneyTransfer(Bank bank){
		for(int i=0;i<state.moneyTransfers.size();i++) {
				if(state.moneyTransfers.get(i).getOwner().equals(bank)) {
					state.moneyTransfers.remove(i);
					return true;
				}
			}
			return false;
	}
	
}
