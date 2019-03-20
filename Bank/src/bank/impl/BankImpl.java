package bank.impl;

import java.util.List;
import java.time.Instant;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import bank.*;

public class BankImpl implements Bank{
	private final Model model;

	public BankImpl(Model model) {
		this.model=model;
	}

	@Override
	public Model getModel(){
		return this.model;
	}
	
	transient List<MoneyTransfer> transactionMoneyTransfers=new ArrayList<MoneyTransfer>();
	public List<MoneyTransfer> getTransactionMoneyTransfers() {
		return transactionMoneyTransfers;
	}
	public void setTransactionMoneyTransfers(List<MoneyTransfer> transactionMoneyTransfers) {
		this.transactionMoneyTransfers = transactionMoneyTransfers;
	}
	
	transient List<SavingsAccount> accountSavingsAccounts=new ArrayList<SavingsAccount>();
	public List<SavingsAccount> getAccountSavingsAccounts() {
		return accountSavingsAccounts;
	}
	public void setAccountSavingsAccounts(List<SavingsAccount> accountSavingsAccounts) {
		this.accountSavingsAccounts = accountSavingsAccounts;
	}
	
	transient List<CheckingAccount> accountCheckingAccounts=new ArrayList<CheckingAccount>();
	public List<CheckingAccount> getAccountCheckingAccounts() {
		return accountCheckingAccounts;
	}
	public void setAccountCheckingAccounts(List<CheckingAccount> accountCheckingAccounts) {
		this.accountCheckingAccounts = accountCheckingAccounts;
	}
	
	transient List<Consultant> personConsultants=new ArrayList<Consultant>();
	public List<Consultant> getPersonConsultants() {
		return personConsultants;
	}
	public void setPersonConsultants(List<Consultant> personConsultants) {
		this.personConsultants = personConsultants;
	}
	
	transient List<Customer> companyCustomers=new ArrayList<Customer>();
	public List<Customer> getCompanyCustomers() {
		return companyCustomers;
	}
	public void setCompanyCustomers(List<Customer> companyCustomers) {
		this.companyCustomers = companyCustomers;
	}
	
	transient List<Customer> personCustomers=new ArrayList<Customer>();
	public List<Customer> getPersonCustomers() {
		return personCustomers;
	}
	public void setPersonCustomers(List<Customer> personCustomers) {
		this.personCustomers = personCustomers;
	}
	
	
	private Own_caImpl own_caExtent=new Own_caImpl();
	private AdvisesImpl advisesExtent=new AdvisesImpl();
	private Own_saImpl own_saExtent=new Own_saImpl();
	
		
	
	private String name;
	
	
	@Override
	public String getName(){
		return name;
	}
	
	@Override
	public void setName(String name){
		this.name = name;
	}
	
	
	@Override
	public void transfer(int sourceId, Account target, double amount){
		if (amount<=0)
			throw new IllegalArgumentException();
		
		Account source=findAccountById(sourceId);
		if (source!=null && target!=null && (!source.isSame(target))){
			Transaction t=getModel().createTransaction();
			t.bindSource(source);
			t.bindTarget(target);
			t.setAmount(amount);
			t.setCreationTime(Instant.now().getNano());
			this.bindMoneyTransfer(t);			
		}else {
			System.out.println("Could not create transaction");
		}
	}
	
	
	private Account findAccountById(int id) {
		Optional<Account> as=Stream
				.concat(accountSavingsAccounts.stream(), accountCheckingAccounts.stream())
				.map(a -> (Account) a)
				.filter(a -> a.getId()==id)
				.findFirst();
		return as.orElse(null);
	}
	
	
	public class TransactionMoneyTransfer extends TransactionImpl implements MoneyTransfer{
		private int execution;
		
		public TransactionMoneyTransfer(Transaction transaction){
			super(BankImpl.this.getModel());
			this.state=((TransactionImpl)transaction).state;
		}
		@Override
		public int getExecution(){
			return execution;
		}
		
		@Override
		public void setExecution(int execution){
			this.execution = execution;
		}
		
		
		
		@Override
		public Bank getOwner() {
			return BankImpl.this;
		}
		@Override
		public Transaction getPlayer() {
			return (Transaction)getCompoundObject();
		}
		
	
	}
	public class AccountSavingsAccount extends AccountImpl implements SavingsAccount{
		private double fee;
		
		public AccountSavingsAccount(Account account){
			super(BankImpl.this.getModel());
			this.state=((AccountImpl)account).state;
		}
		@Override
		public double getFee(){
			return fee;
		}
		
		@Override
		public void setFee(double fee){
			this.fee = fee;
		}
		
		@Override
		public void decrease(double amount){
			throw new UnsupportedOperationException("Invalid operation for sorted list.");
		}
		
		
		@Override
		public Bank getOwner() {
			return BankImpl.this;
		}
		@Override
		public Account getPlayer() {
			return (Account)getCompoundObject();
		}
		@Override
		public List<Customer> getOwn_sa(){
			return own_saExtent.getCustomers(this);
		}
		@Override
		public boolean addOwn_sa(Customer customer){
			return own_saExtent.add(customer,this);
		}
		
		
	
	}
	public class AccountCheckingAccount extends AccountImpl implements CheckingAccount{
		private double limit;
		
		public AccountCheckingAccount(Account account){
			super(BankImpl.this.getModel());
			this.state=((AccountImpl)account).state;
		}
		@Override
		public double getLimit(){
			return limit;
		}
		
		@Override
		public void setLimit(double limit){
			this.limit = limit;
		}
		
		@Override
		public void decrease(double amount){
			throw new UnsupportedOperationException("Invalid operation for sorted list.");
		}
		
		
		@Override
		public Bank getOwner() {
			return BankImpl.this;
		}
		@Override
		public Account getPlayer() {
			return (Account)getCompoundObject();
		}
		@Override
		public List<Customer> getOwn_ca(){
			return own_caExtent.getCustomers(this);
		}
		@Override
		public boolean addOwn_ca(Customer customer){
			return own_caExtent.add(customer,this);
		}
		
		
	
	}
	public class PersonConsultant extends PersonImpl implements Consultant{
		private String phone;
		
		public PersonConsultant(Person person){
			super(BankImpl.this.getModel());
			this.state=((PersonImpl)person).state;
		}
		@Override
		public String getPhone(){
			return phone;
		}
		
		@Override
		public void setPhone(String phone){
			this.phone = phone;
		}
		
		
		
		@Override
		public Bank getOwner() {
			return BankImpl.this;
		}
		@Override
		public Person getPlayer() {
			return (Person)getCompoundObject();
		}
		@Override
		public List<Customer> getAdvises(){
			return advisesExtent.getCustomers(this);
		}
		@Override
		public boolean addAdvises(Customer customer){
			return advisesExtent.add(this,customer);
		}
		
		
	
	}
	public class CompanyCustomer extends CompanyImpl implements Customer{
		private int id;
		
		public CompanyCustomer(Company company){
			super(BankImpl.this.getModel());
			this.state=((CompanyImpl)company).state;
		}
		@Override
		public int getId(){
			return id;
		}
		
		@Override
		public void setId(int id){
			this.id = id;
		}
		
		@Override
		public String getName(){
			throw new UnsupportedOperationException("Invalid operation for sorted list.");
		}
		
		
		@Override
		public Bank getOwner() {
			return BankImpl.this;
		}
		@Override
		public Company getPlayer() {
			return (Company)getCompoundObject();
		}
		@Override
		public List<SavingsAccount> getOwn_sa(){
			return own_saExtent.getSavingsAccounts(this);
		}
		@Override
		public boolean addOwn_sa(SavingsAccount savingsAccount){
			return own_saExtent.add(this,savingsAccount);
		}
		
		@Override
		public List<CheckingAccount> getOwn_ca(){
			return own_caExtent.getCheckingAccounts(this);
		}
		@Override
		public boolean addOwn_ca(CheckingAccount checkingAccount){
			return own_caExtent.add(this,checkingAccount);
		}
		
		@Override
		public List<Consultant> getAdvises(){
			return advisesExtent.getConsultants(this);
		}
		@Override
		public boolean addAdvises(Consultant consultant){
			return advisesExtent.add(consultant,this);
		}
		
		
	
	}
	public class PersonCustomer extends PersonImpl implements Customer{
		private int id;
		
		public PersonCustomer(Person person){
			super(BankImpl.this.getModel());
			this.state=((PersonImpl)person).state;
		}
		@Override
		public int getId(){
			return id;
		}
		
		@Override
		public void setId(int id){
			this.id = id;
		}
		
		@Override
		public String getName(){
			throw new UnsupportedOperationException("Invalid operation for sorted list.");
		}
		
		
		@Override
		public Bank getOwner() {
			return BankImpl.this;
		}
		@Override
		public Person getPlayer() {
			return (Person)getCompoundObject();
		}
		@Override
		public List<SavingsAccount> getOwn_sa(){
			return own_saExtent.getSavingsAccounts(this);
		}
		@Override
		public boolean addOwn_sa(SavingsAccount savingsAccount){
			return own_saExtent.add(this,savingsAccount);
		}
		
		@Override
		public List<CheckingAccount> getOwn_ca(){
			return own_caExtent.getCheckingAccounts(this);
		}
		@Override
		public boolean addOwn_ca(CheckingAccount checkingAccount){
			return own_caExtent.add(this,checkingAccount);
		}
		
		@Override
		public List<Consultant> getAdvises(){
			return advisesExtent.getConsultants(this);
		}
		@Override
		public boolean addAdvises(Consultant consultant){
			return advisesExtent.add(consultant,this);
		}
		
		
	
	}
	
	
	public class AdvisesImpl implements Advises{
		List<Map.Entry<Consultant, Customer>> advises=new ArrayList<Map.Entry<Consultant, Customer>>();
		
		@Override
		public boolean remove(Consultant consultant, Customer customer){
			for(int i = 0;i<advises.size();i++) {
				Map.Entry<Consultant, Customer> entry=advises.get(i);
				if(entry.getKey().equals(consultant)&&entry.getValue().equals(customer)){
					advises.remove(i);
					return true;
				}
			}
			return false;
		}
		
		@Override
		public List<Customer> getCustomers(Consultant consultant){
			List<Customer> customers=new ArrayList<Customer>();
			for(int i = 0;i<advises.size();i++) {
				Map.Entry<Consultant, Customer> entry=advises.get(i);
				if(entry.getKey().equals(consultant)){
					customers.add(entry.getValue());
				}
			}
			return customers;
		}
		@Override
		public List<Consultant> getConsultants(Customer customer){
			List<Consultant> consultants=new ArrayList<Consultant>();
			for(int i = 0;i<advises.size();i++) {
				Map.Entry<Consultant, Customer> entry=advises.get(i);
				if(entry.getValue().equals(customer)){
					consultants.add(entry.getKey());
				}
			}		
			return consultants;
		}
		@Override
		public boolean add(Consultant consultant, Customer customer){
			if(consultant.isSame(customer))
				return false;
			return advises.add(new AbstractMap.SimpleEntry<Consultant, Customer>(consultant,customer));
		}
	}
	
	public class Own_saImpl implements Own_sa{
		List<Map.Entry<Customer, SavingsAccount>> own_sa=new ArrayList<Map.Entry<Customer, SavingsAccount>>();
		
		@Override
		public boolean remove(Customer customer, SavingsAccount savingsAccount){
			for(int i = 0;i<own_sa.size();i++) {
				Map.Entry<Customer, SavingsAccount> entry=own_sa.get(i);
				if(entry.getKey().equals(customer)&&entry.getValue().equals(savingsAccount)){
					own_sa.remove(i);
					return true;
				}
			}
			return false;
		}
		
		@Override
		public List<SavingsAccount> getSavingsAccounts(Customer customer){
			List<SavingsAccount> savingsAccounts=new ArrayList<SavingsAccount>();
			for(int i = 0;i<own_sa.size();i++) {
				Map.Entry<Customer, SavingsAccount> entry=own_sa.get(i);
				if(entry.getKey().equals(customer)){
					savingsAccounts.add(entry.getValue());
				}
			}
			return savingsAccounts;
		}
		@Override
		public List<Customer> getCustomers(SavingsAccount savingsAccount){
			List<Customer> customers=new ArrayList<Customer>();
			for(int i = 0;i<own_sa.size();i++) {
				Map.Entry<Customer, SavingsAccount> entry=own_sa.get(i);
				if(entry.getValue().equals(savingsAccount)){
					customers.add(entry.getKey());
				}
			}		
			return customers;
		}
		@Override
		public boolean add(Customer customer, SavingsAccount savingsAccount){
			return own_sa.add(new AbstractMap.SimpleEntry<Customer, SavingsAccount>(customer,savingsAccount));
		}
	}
	public class Own_caImpl implements Own_ca{
		List<Map.Entry<Customer, CheckingAccount>> own_ca=new ArrayList<Map.Entry<Customer, CheckingAccount>>();
		
		@Override
		public boolean remove(Customer customer, CheckingAccount checkingAccount){
			for(int i = 0;i<own_ca.size();i++) {
				Map.Entry<Customer, CheckingAccount> entry=own_ca.get(i);
				if(entry.getKey().equals(customer)&&entry.getValue().equals(checkingAccount)){
					own_ca.remove(i);
					return true;
				}
			}
			return false;
		}
		
		@Override
		public List<CheckingAccount> getCheckingAccounts(Customer customer){
			List<CheckingAccount> checkingAccounts=new ArrayList<CheckingAccount>();
			for(int i = 0;i<own_ca.size();i++) {
				Map.Entry<Customer, CheckingAccount> entry=own_ca.get(i);
				if(entry.getKey().equals(customer)){
					checkingAccounts.add(entry.getValue());
				}
			}
			return checkingAccounts;
		}
		@Override
		public List<Customer> getCustomers(CheckingAccount checkingAccount){
			List<Customer> customers=new ArrayList<Customer>();
			for(int i = 0;i<own_ca.size();i++) {
				Map.Entry<Customer, CheckingAccount> entry=own_ca.get(i);
				if(entry.getValue().equals(checkingAccount)){
					customers.add(entry.getKey());
				}
			}		
			return customers;
		}
		@Override
		public boolean add(Customer customer, CheckingAccount checkingAccount){
			return own_ca.add(new AbstractMap.SimpleEntry<Customer, CheckingAccount>(customer,checkingAccount));
		}
	}
	
	
	@Override
	public MoneyTransfer bindMoneyTransfer(Transaction transaction){
		for (int i = 0; i < transactionMoneyTransfers.size(); i++) {
			if (transactionMoneyTransfers.get(i).isSame(transaction)) {
				return transactionMoneyTransfers.get(i);
			}
		}
		TransactionMoneyTransfer role = new TransactionMoneyTransfer((TransactionImpl) transaction);
		transactionMoneyTransfers.add(role);
		((TransactionImpl) transaction).addBankMoneyTransfer(role);
		return role;
	}
	@Override
	public boolean unbindMoneyTransfer(Transaction transaction){
		for (int i = 0; i < transactionMoneyTransfers.size(); i++) {
			if (transactionMoneyTransfers.get(i).isSame(transaction)) {
				transactionMoneyTransfers.remove(i);
				return transaction.removeBankMoneyTransfer(this);
			}
		}
		return false;
	}
	@Override
	public SavingsAccount bindSavingsAccount(Account account){
		for (int i = 0; i < accountSavingsAccounts.size(); i++) {
			if (accountSavingsAccounts.get(i).isSame(account)) {
				return accountSavingsAccounts.get(i);
			}
		}
		AccountSavingsAccount role = new AccountSavingsAccount(account);
		accountSavingsAccounts.add(role);
		((AccountImpl) account).addBankSavingsAccount(role);
		return role;
	}
	@Override
	public boolean unbindSavingsAccount(Account account){
		for (int i = 0; i < accountSavingsAccounts.size(); i++) {
			if (accountSavingsAccounts.get(i).isSame(account)) {
				accountSavingsAccounts.remove(i);
				return account.removeBankSavingsAccount(this);
			}
		}
		return false;
	}
	@Override
	public CheckingAccount bindCheckingAccount(Account account){
		for (int i = 0; i < accountCheckingAccounts.size(); i++) {
			if (accountCheckingAccounts.get(i).isSame(account)) {
				return accountCheckingAccounts.get(i);
			}
		}
		AccountCheckingAccount role = new AccountCheckingAccount(account);
		accountCheckingAccounts.add(role);
		((AccountImpl) account).addBankCheckingAccount(role);
		return role;
	}
	@Override
	public boolean unbindCheckingAccount(Account account){
		for (int i = 0; i < accountCheckingAccounts.size(); i++) {
			if (accountCheckingAccounts.get(i).isSame(account)) {
				accountCheckingAccounts.remove(i);
				return account.removeBankCheckingAccount(this);
			}
		}
		return false;
	}
	@Override
	public Consultant bindConsultant(Person person){
		for (int i = 0; i < personConsultants.size(); i++) {
			if (personConsultants.get(i).isSame(person)) {
				return personConsultants.get(i);
			}
		}
		PersonConsultant role = new PersonConsultant(person);
		personConsultants.add(role);
		((PersonImpl) person).addBankConsultant(role);
		return role;
	}
	@Override
	public boolean unbindConsultant(Person person){
		for (int i = 0; i < personConsultants.size(); i++) {
			if (personConsultants.get(i).isSame(person)) {
				personConsultants.remove(i);
				return person.removeBankConsultant(this);
			}
		}
		return false;
	}
	@Override
	public Customer bindCustomer(Company company){
		for (int i = 0; i < companyCustomers.size(); i++) {
			if (companyCustomers.get(i).isSame(company)) {
				return companyCustomers.get(i);
			}
		}
		CompanyCustomer role = new CompanyCustomer(company);
		companyCustomers.add(role);
		((CompanyImpl) company).addBankCustomer(role);
		return role;
	}
	@Override
	public boolean unbindCustomer(Company company){
		for (int i = 0; i < companyCustomers.size(); i++) {
			if (companyCustomers.get(i).isSame(company)) {
				companyCustomers.remove(i);
				return company.removeBankCustomer(this);
			}
		}
		return false;
	}
	@Override
	public Customer bindCustomer(Person person){
		for (int i = 0; i < personCustomers.size(); i++) {
			if (personCustomers.get(i).isSame(person)) {
				return personCustomers.get(i);
			}
		}
		PersonCustomer role = new PersonCustomer(person);
		personCustomers.add(role);
		((PersonImpl) person).addBankCustomer(role);
		return role;
	}
	@Override
	public boolean unbindCustomer(Person person){
		for (int i = 0; i < personCustomers.size(); i++) {
			if (personCustomers.get(i).isSame(person)) {
				personCustomers.remove(i);
				return person.removeBankCustomer(this);
			}
		}
		return false;
	}
	
	@Override
	public MoneyTransfer getRole(Transaction transaction){
		for (int i = 0; i < transactionMoneyTransfers.size(); i++) {
			if (transactionMoneyTransfers.get(i).isSame(transaction)) {
				return transactionMoneyTransfers.get(i);
			}
		}
		return null;
	}
	@Override
	public Customer getRole(Company company){
		for (int i = 0; i < companyCustomers.size(); i++) {
			if (companyCustomers.get(i).isSame(company)) {
				return companyCustomers.get(i);
			}
		}
		return null;
	}
	@Override
	public List<Account> getRole(Account account){
		List<Account> list=new ArrayList<Account>();
		for (int i = 0; i < accountSavingsAccounts.size(); i++) {
			if (accountSavingsAccounts.get(i).isSame(account)) {
				list.add((Account)accountSavingsAccounts.get(i));break;
			}
		}
		for (int i = 0; i < accountCheckingAccounts.size(); i++) {
			if (accountCheckingAccounts.get(i).isSame(account)) {
				list.add((Account)accountCheckingAccounts.get(i));break;
			}
		}
		return list;
	}
	@Override
	public List<Person> getRole(Person person){
		List<Person> list=new ArrayList<Person>();
		for (int i = 0; i < personConsultants.size(); i++) {
			if (personConsultants.get(i).isSame(person)) {
				list.add((Person)personConsultants.get(i));break;
			}
		}
		for (int i = 0; i < personCustomers.size(); i++) {
			if (personCustomers.get(i).isSame(person)) {
				list.add((Person)personCustomers.get(i));break;
			}
		}
		return list;
	}
	
}
