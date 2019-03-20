package bank.util;

import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

import bank.*;
import bank.impl.*;
import bank.crof.*;

public class Serializer{
	private CrofModel crofModel=new CrofModel();
	private Model model;

	public void exchangeDateTime(List<DateTime> dateTimes){
		List<CrofDateTime> crofDateTimes=new ArrayList<CrofDateTime>();
		for(int i=0;i<dateTimes.size();i++) {
			CrofDateTime crofDateTime=new CrofDateTime();
			
			crofDateTimes.add(crofDateTime);
		}
		crofModel.setCrofDateTimes(crofDateTimes);
	}	
	public void exchangeTransaction(List<Transaction> transactions){
		List<CrofTransaction> crofTransactions=new ArrayList<CrofTransaction>();
		for(int i=0;i<transactions.size();i++) {
			CrofTransaction crofTransaction=new CrofTransaction();
			crofTransaction.setAmount(transactions.get(i).getAmount());
			crofTransaction.setCreationTime(transactions.get(i).getCreationTime());
			crofTransactions.add(crofTransaction);
		}
		crofModel.setCrofTransactions(crofTransactions);
	}

	public void exchangeTransaction(Transaction transaction) {
		CrofTransaction crofTransaction= crofModel.getCrofTransactions().get(model.getIndex(transaction));
		TransactionImpl transactionImpl=(TransactionImpl)transaction;
		List<TransactionImpl.AccountSource> accountSources=new ArrayList<TransactionImpl.AccountSource>();
		for(int i=0;i<transactionImpl.getAccountSources().size();i++) {
		TransactionImpl.AccountSource accountSource=(TransactionImpl.AccountSource)transactionImpl.getAccountSources().get(i);
		accountSources.add(accountSource);
		CrofSource crofSource= new CrofSource();
		List<CrofSource> crofSources=crofTransaction.getCrofSources();
		crofSources.add(crofSource);
		crofTransaction.setCrofSources(crofSources);
		CrofAccountSource crofAccountSource=new CrofAccountSource();
		int playerPosition=model.getIndex((Account)accountSource.getPlayer());
		crofAccountSource.setPlayer("CrofAccount@"+playerPosition);			
		int playedPosition=crofSources.size()-1;
		crofAccountSource.setPlayed("CrofSource@"+playedPosition);
		
		crofTransaction.getCrofAccountSources().add(crofAccountSource);
		}
		List<TransactionImpl.AccountTarget> accountTargets=new ArrayList<TransactionImpl.AccountTarget>();
		for(int i=0;i<transactionImpl.getAccountTargets().size();i++) {
		TransactionImpl.AccountTarget accountTarget=(TransactionImpl.AccountTarget)transactionImpl.getAccountTargets().get(i);
		accountTargets.add(accountTarget);
		CrofTarget crofTarget= new CrofTarget();
		List<CrofTarget> crofTargets=crofTransaction.getCrofTargets();
		crofTargets.add(crofTarget);
		crofTransaction.setCrofTargets(crofTargets);
		CrofAccountTarget crofAccountTarget=new CrofAccountTarget();
		int playerPosition=model.getIndex((Account)accountTarget.getPlayer());
		crofAccountTarget.setPlayer("CrofAccount@"+playerPosition);			
		int playedPosition=crofTargets.size()-1;
		crofAccountTarget.setPlayed("CrofTarget@"+playedPosition);
		
		crofTransaction.getCrofAccountTargets().add(crofAccountTarget);
		}
		//List<CrofTrans> crofTranss = new ArrayList<CrofTrans>();
		for(int i=0;i<accountSources.size();i++){
			TransactionImpl.AccountSource accountSource=(TransactionImpl.AccountSource)accountSources.get(i);
			List<Transaction.Target> targets=accountSource.getTrans();
			if(!targets.isEmpty()) {
				for(int j=0;j<targets.size();j++){
					Transaction.Target target=targets.get(j);
					if(target instanceof TransactionImpl.AccountTarget && target.getPlayer().getClass().getName().substring(target.getPlayer().getClass().getName().lastIndexOf(".")+1).equals("AccountImpl")){
						TransactionImpl.AccountTarget accountTarget=(TransactionImpl.AccountTarget)target;
						CrofTrans crofTrans=new CrofTrans();
						crofTrans.setFirst("AccountSource@"+accountSources.indexOf(accountSource));
						crofTrans.setSecond("AccountTarget@"+accountTargets.indexOf(accountTarget));
						crofTransaction.getCrofTrans().add(crofTrans);
					}
				}
			}
		}
		
	}
	public void exchangeCompany(List<Company> companys){
		List<CrofCompany> crofCompanys=new ArrayList<CrofCompany>();
		for(int i=0;i<companys.size();i++) {
			CrofCompany crofCompany=new CrofCompany();
			crofCompany.setName(companys.get(i).getName());
			crofCompany.setLegalForm(companys.get(i).getLegalForm());
			crofCompany.setPOBox(companys.get(i).getPOBox());
			crofCompany.setAddresses(companys.get(i).getAddresses());
			
			crofCompanys.add(crofCompany);
		}
		crofModel.setCrofCompanys(crofCompanys);
	}
	public void exchangePerson(List<Person> persons){
		List<CrofPerson> crofPersons=new ArrayList<CrofPerson>();
		for(int i=0;i<persons.size();i++) {
			CrofPerson crofPerson=new CrofPerson();
			crofPerson.setTitle(persons.get(i).getTitle());
			crofPerson.setFirstName(persons.get(i).getFirstName());
			crofPerson.setLastName(persons.get(i).getLastName());
			crofPerson.setAddress(persons.get(i).getAddress());
			
			crofPersons.add(crofPerson);
		}
		crofModel.setCrofPersons(crofPersons);
	}
	public void exchangeBank(List<Bank> banks){
		List<CrofBank> crofBanks=new ArrayList<CrofBank>();
		for(int i=0;i<banks.size();i++) {
			CrofBank crofBank=new CrofBank();
			crofBank.setName(banks.get(i).getName());
			crofBanks.add(crofBank);
		}
		crofModel.setCrofBanks(crofBanks);
	}

	public void exchangeBank(Bank bank) {
		CrofBank crofBank= crofModel.getCrofBanks().get(model.getIndex(bank));
		BankImpl bankImpl=(BankImpl)bank;
		List<BankImpl.TransactionMoneyTransfer> transactionMoneyTransfers=new ArrayList<BankImpl.TransactionMoneyTransfer>();
		for(int i=0;i<bankImpl.getTransactionMoneyTransfers().size();i++) {
		BankImpl.TransactionMoneyTransfer transactionMoneyTransfer=(BankImpl.TransactionMoneyTransfer)bankImpl.getTransactionMoneyTransfers().get(i);
		transactionMoneyTransfers.add(transactionMoneyTransfer);
		CrofMoneyTransfer crofMoneyTransfer= new CrofMoneyTransfer();
		crofMoneyTransfer.setExecution(transactionMoneyTransfer.getExecution());
		List<CrofMoneyTransfer> crofMoneyTransfers=crofBank.getCrofMoneyTransfers();
		crofMoneyTransfers.add(crofMoneyTransfer);
		crofBank.setCrofMoneyTransfers(crofMoneyTransfers);
		CrofTransactionMoneyTransfer crofTransactionMoneyTransfer=new CrofTransactionMoneyTransfer();
		int playerPosition=model.getIndex((Transaction)transactionMoneyTransfer.getPlayer());
		crofTransactionMoneyTransfer.setPlayer("CrofTransaction@"+playerPosition);			
		int playedPosition=crofMoneyTransfers.size()-1;
		crofTransactionMoneyTransfer.setPlayed("CrofMoneyTransfer@"+playedPosition);
		
		crofBank.getCrofTransactionMoneyTransfers().add(crofTransactionMoneyTransfer);
		}
		List<BankImpl.CompanyCustomer> companyCustomers=new ArrayList<BankImpl.CompanyCustomer>();
		for(int i=0;i<bankImpl.getCompanyCustomers().size();i++) {
		BankImpl.CompanyCustomer companyCustomer=(BankImpl.CompanyCustomer)bankImpl.getCompanyCustomers().get(i);
		companyCustomers.add(companyCustomer);
		CrofCustomer crofCustomer= new CrofCustomer();
		crofCustomer.setId(companyCustomer.getId());
		List<CrofCustomer> crofCustomers=crofBank.getCrofCustomers();
		crofCustomers.add(crofCustomer);
		crofBank.setCrofCustomers(crofCustomers);
		CrofCompanyCustomer crofCompanyCustomer=new CrofCompanyCustomer();
		int playerPosition=model.getIndex((Company)companyCustomer.getPlayer());
		crofCompanyCustomer.setPlayer("CrofCompany@"+playerPosition);			
		int playedPosition=crofCustomers.size()-1;
		crofCompanyCustomer.setPlayed("CrofCustomer@"+playedPosition);
		
		crofBank.getCrofCompanyCustomers().add(crofCompanyCustomer);
		}
		List<BankImpl.PersonCustomer> personCustomers=new ArrayList<BankImpl.PersonCustomer>();
		for(int i=0;i<bankImpl.getPersonCustomers().size();i++) {
		BankImpl.PersonCustomer personCustomer=(BankImpl.PersonCustomer)bankImpl.getPersonCustomers().get(i);
		personCustomers.add(personCustomer);
		CrofCustomer crofCustomer= new CrofCustomer();
		crofCustomer.setId(personCustomer.getId());
		List<CrofCustomer> crofCustomers=crofBank.getCrofCustomers();
		crofCustomers.add(crofCustomer);
		crofBank.setCrofCustomers(crofCustomers);
		CrofPersonCustomer crofPersonCustomer=new CrofPersonCustomer();
		int playerPosition=model.getIndex((Person)personCustomer.getPlayer());
		crofPersonCustomer.setPlayer("CrofPerson@"+playerPosition);			
		int playedPosition=crofCustomers.size()-1;
		crofPersonCustomer.setPlayed("CrofCustomer@"+playedPosition);
		
		crofBank.getCrofPersonCustomers().add(crofPersonCustomer);
		}
		List<BankImpl.PersonConsultant> personConsultants=new ArrayList<BankImpl.PersonConsultant>();
		for(int i=0;i<bankImpl.getPersonConsultants().size();i++) {
		BankImpl.PersonConsultant personConsultant=(BankImpl.PersonConsultant)bankImpl.getPersonConsultants().get(i);
		personConsultants.add(personConsultant);
		CrofConsultant crofConsultant= new CrofConsultant();
		crofConsultant.setPhone(personConsultant.getPhone());
		List<CrofConsultant> crofConsultants=crofBank.getCrofConsultants();
		crofConsultants.add(crofConsultant);
		crofBank.setCrofConsultants(crofConsultants);
		CrofPersonConsultant crofPersonConsultant=new CrofPersonConsultant();
		int playerPosition=model.getIndex((Person)personConsultant.getPlayer());
		crofPersonConsultant.setPlayer("CrofPerson@"+playerPosition);			
		int playedPosition=crofConsultants.size()-1;
		crofPersonConsultant.setPlayed("CrofConsultant@"+playedPosition);
		
		crofBank.getCrofPersonConsultants().add(crofPersonConsultant);
		}
		List<BankImpl.AccountCheckingAccount> accountCheckingAccounts=new ArrayList<BankImpl.AccountCheckingAccount>();
		for(int i=0;i<bankImpl.getAccountCheckingAccounts().size();i++) {
		BankImpl.AccountCheckingAccount accountCheckingAccount=(BankImpl.AccountCheckingAccount)bankImpl.getAccountCheckingAccounts().get(i);
		accountCheckingAccounts.add(accountCheckingAccount);
		CrofCheckingAccount crofCheckingAccount= new CrofCheckingAccount();
		crofCheckingAccount.setLimit(accountCheckingAccount.getLimit());
		List<CrofCheckingAccount> crofCheckingAccounts=crofBank.getCrofCheckingAccounts();
		crofCheckingAccounts.add(crofCheckingAccount);
		crofBank.setCrofCheckingAccounts(crofCheckingAccounts);
		CrofAccountCheckingAccount crofAccountCheckingAccount=new CrofAccountCheckingAccount();
		int playerPosition=model.getIndex((Account)accountCheckingAccount.getPlayer());
		crofAccountCheckingAccount.setPlayer("CrofAccount@"+playerPosition);			
		int playedPosition=crofCheckingAccounts.size()-1;
		crofAccountCheckingAccount.setPlayed("CrofCheckingAccount@"+playedPosition);
		
		crofBank.getCrofAccountCheckingAccounts().add(crofAccountCheckingAccount);
		}
		List<BankImpl.AccountSavingsAccount> accountSavingsAccounts=new ArrayList<BankImpl.AccountSavingsAccount>();
		for(int i=0;i<bankImpl.getAccountSavingsAccounts().size();i++) {
		BankImpl.AccountSavingsAccount accountSavingsAccount=(BankImpl.AccountSavingsAccount)bankImpl.getAccountSavingsAccounts().get(i);
		accountSavingsAccounts.add(accountSavingsAccount);
		CrofSavingsAccount crofSavingsAccount= new CrofSavingsAccount();
		crofSavingsAccount.setFee(accountSavingsAccount.getFee());
		List<CrofSavingsAccount> crofSavingsAccounts=crofBank.getCrofSavingsAccounts();
		crofSavingsAccounts.add(crofSavingsAccount);
		crofBank.setCrofSavingsAccounts(crofSavingsAccounts);
		CrofAccountSavingsAccount crofAccountSavingsAccount=new CrofAccountSavingsAccount();
		int playerPosition=model.getIndex((Account)accountSavingsAccount.getPlayer());
		crofAccountSavingsAccount.setPlayer("CrofAccount@"+playerPosition);			
		int playedPosition=crofSavingsAccounts.size()-1;
		crofAccountSavingsAccount.setPlayed("CrofSavingsAccount@"+playedPosition);
		
		crofBank.getCrofAccountSavingsAccounts().add(crofAccountSavingsAccount);
		}
		//List<CrofOwn_ca> crofOwn_cas = new ArrayList<CrofOwn_ca>();
		for(int i=0;i<companyCustomers.size();i++){
			BankImpl.CompanyCustomer companyCustomer=(BankImpl.CompanyCustomer)companyCustomers.get(i);
			List<Bank.CheckingAccount> checkingAccounts=companyCustomer.getOwn_ca();
			if(!checkingAccounts.isEmpty()) {
				for(int j=0;j<checkingAccounts.size();j++){
					Bank.CheckingAccount checkingAccount=checkingAccounts.get(j);
					if(checkingAccount instanceof BankImpl.AccountCheckingAccount && checkingAccount.getPlayer().getClass().getName().substring(checkingAccount.getPlayer().getClass().getName().lastIndexOf(".")+1).equals("AccountImpl")){
						BankImpl.AccountCheckingAccount accountCheckingAccount=(BankImpl.AccountCheckingAccount)checkingAccount;
						CrofOwn_ca crofOwn_ca=new CrofOwn_ca();
						crofOwn_ca.setFirst("CompanyCustomer@"+companyCustomers.indexOf(companyCustomer));
						crofOwn_ca.setSecond("AccountCheckingAccount@"+accountCheckingAccounts.indexOf(accountCheckingAccount));
						crofBank.getCrofOwn_ca().add(crofOwn_ca);
					}
				}
			}
		}
		for(int i=0;i<personCustomers.size();i++){
			BankImpl.PersonCustomer personCustomer=(BankImpl.PersonCustomer)personCustomers.get(i);
			List<Bank.CheckingAccount> checkingAccounts=personCustomer.getOwn_ca();
			if(!checkingAccounts.isEmpty()) {
				for(int j=0;j<checkingAccounts.size();j++){
					Bank.CheckingAccount checkingAccount=checkingAccounts.get(j);
					if(checkingAccount instanceof BankImpl.AccountCheckingAccount && checkingAccount.getPlayer().getClass().getName().substring(checkingAccount.getPlayer().getClass().getName().lastIndexOf(".")+1).equals("AccountImpl")){
						BankImpl.AccountCheckingAccount accountCheckingAccount=(BankImpl.AccountCheckingAccount)checkingAccount;
						CrofOwn_ca crofOwn_ca=new CrofOwn_ca();
						crofOwn_ca.setFirst("PersonCustomer@"+personCustomers.indexOf(personCustomer));
						crofOwn_ca.setSecond("AccountCheckingAccount@"+accountCheckingAccounts.indexOf(accountCheckingAccount));
						crofBank.getCrofOwn_ca().add(crofOwn_ca);
					}
				}
			}
		}
		
		//List<CrofAdvises> crofAdvisess = new ArrayList<CrofAdvises>();
		for(int i=0;i<personConsultants.size();i++){
			BankImpl.PersonConsultant personConsultant=(BankImpl.PersonConsultant)personConsultants.get(i);
			List<Bank.Customer> customers=personConsultant.getAdvises();
			if(!customers.isEmpty()) {
				for(int j=0;j<customers.size();j++){
					Bank.Customer customer=customers.get(j);
					if(customer instanceof BankImpl.CompanyCustomer && customer.getPlayer().getClass().getName().substring(customer.getPlayer().getClass().getName().lastIndexOf(".")+1).equals("CompanyImpl")){
						BankImpl.CompanyCustomer companyCustomer=(BankImpl.CompanyCustomer)customer;
						CrofAdvises crofAdvises=new CrofAdvises();
						crofAdvises.setFirst("PersonConsultant@"+personConsultants.indexOf(personConsultant));
						crofAdvises.setSecond("CompanyCustomer@"+companyCustomers.indexOf(companyCustomer));
						crofBank.getCrofAdvises().add(crofAdvises);
					}
					if(customer instanceof BankImpl.PersonCustomer && customer.getPlayer().getClass().getName().substring(customer.getPlayer().getClass().getName().lastIndexOf(".")+1).equals("PersonImpl")){
						BankImpl.PersonCustomer personCustomer=(BankImpl.PersonCustomer)customer;
						CrofAdvises crofAdvises=new CrofAdvises();
						crofAdvises.setFirst("PersonConsultant@"+personConsultants.indexOf(personConsultant));
						crofAdvises.setSecond("PersonCustomer@"+personCustomers.indexOf(personCustomer));
						crofBank.getCrofAdvises().add(crofAdvises);
					}
				}
			}
		}
		
		//List<CrofOwn_sa> crofOwn_sas = new ArrayList<CrofOwn_sa>();
		for(int i=0;i<companyCustomers.size();i++){
			BankImpl.CompanyCustomer companyCustomer=(BankImpl.CompanyCustomer)companyCustomers.get(i);
			List<Bank.SavingsAccount> savingsAccounts=companyCustomer.getOwn_sa();
			if(!savingsAccounts.isEmpty()) {
				for(int j=0;j<savingsAccounts.size();j++){
					Bank.SavingsAccount savingsAccount=savingsAccounts.get(j);
					if(savingsAccount instanceof BankImpl.AccountSavingsAccount && savingsAccount.getPlayer().getClass().getName().substring(savingsAccount.getPlayer().getClass().getName().lastIndexOf(".")+1).equals("AccountImpl")){
						BankImpl.AccountSavingsAccount accountSavingsAccount=(BankImpl.AccountSavingsAccount)savingsAccount;
						CrofOwn_sa crofOwn_sa=new CrofOwn_sa();
						crofOwn_sa.setFirst("CompanyCustomer@"+companyCustomers.indexOf(companyCustomer));
						crofOwn_sa.setSecond("AccountSavingsAccount@"+accountSavingsAccounts.indexOf(accountSavingsAccount));
						crofBank.getCrofOwn_sa().add(crofOwn_sa);
					}
				}
			}
		}
		for(int i=0;i<personCustomers.size();i++){
			BankImpl.PersonCustomer personCustomer=(BankImpl.PersonCustomer)personCustomers.get(i);
			List<Bank.SavingsAccount> savingsAccounts=personCustomer.getOwn_sa();
			if(!savingsAccounts.isEmpty()) {
				for(int j=0;j<savingsAccounts.size();j++){
					Bank.SavingsAccount savingsAccount=savingsAccounts.get(j);
					if(savingsAccount instanceof BankImpl.AccountSavingsAccount && savingsAccount.getPlayer().getClass().getName().substring(savingsAccount.getPlayer().getClass().getName().lastIndexOf(".")+1).equals("AccountImpl")){
						BankImpl.AccountSavingsAccount accountSavingsAccount=(BankImpl.AccountSavingsAccount)savingsAccount;
						CrofOwn_sa crofOwn_sa=new CrofOwn_sa();
						crofOwn_sa.setFirst("PersonCustomer@"+personCustomers.indexOf(personCustomer));
						crofOwn_sa.setSecond("AccountSavingsAccount@"+accountSavingsAccounts.indexOf(accountSavingsAccount));
						crofBank.getCrofOwn_sa().add(crofOwn_sa);
					}
				}
			}
		}
		
	}
	public void exchangeAccount(List<Account> accounts){
		List<CrofAccount> crofAccounts=new ArrayList<CrofAccount>();
		for(int i=0;i<accounts.size();i++) {
			CrofAccount crofAccount=new CrofAccount();
			crofAccount.setBalance(accounts.get(i).getBalance());
			crofAccount.setId(accounts.get(i).getId());
			
			crofAccounts.add(crofAccount);
		}
		crofModel.setCrofAccounts(crofAccounts);
	}
	public void toJson(Model model,String filePath) {
		this.model=model;
		exchangeDateTime(model.getDateTime());
		List<Transaction> transactions=model.getTransaction();
		exchangeTransaction(transactions);
		exchangeCompany(model.getCompany());
		exchangePerson(model.getPerson());
		List<Bank> banks=model.getBank();
		exchangeBank(banks);
		exchangeAccount(model.getAccount());
		for(int i = 0; i<transactions.size(); i++){
			exchangeTransaction(transactions.get(i));
		}
		for(int i = 0; i<banks.size(); i++){
			exchangeBank(banks.get(i));
		}
		String str = new Gson().toJson(crofModel,CrofModel.class);
		File file = new File(filePath);
		try {  
            file.createNewFile();  
	        try ( BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false), "UTF-8")); ){  
	            writer.write(str);
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }
        } catch (IOException e) {  
            e.printStackTrace();
        }
	}
	public String toJson(Model model) {
		this.model=model;
		exchangeDateTime(model.getDateTime());
		List<Transaction> transactions=model.getTransaction();
		exchangeTransaction(transactions);
		exchangeCompany(model.getCompany());
		exchangePerson(model.getPerson());
		List<Bank> banks=model.getBank();
		exchangeBank(banks);
		exchangeAccount(model.getAccount());
		for(int i = 0; i<transactions.size(); i++){
			exchangeTransaction(transactions.get(i));
		}
		for(int i = 0; i<banks.size(); i++){
			exchangeBank(banks.get(i));
		}
		return new Gson().toJson(crofModel,CrofModel.class);		
	}
}
