package bank.util;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

import bank.*;
import bank.impl.*;
import bank.crof.*;

public class Deserializer{
	//private String filePath="./instance/model.croj";
	private CrofModel crofModel=new CrofModel();
	private Model model=Factory.INSTANCE.createModel();

	public void exchangeDateTime(List<CrofDateTime> crofDateTimes){
		for(int i=0;i<crofDateTimes.size();i++) {
			DateTime dateTime=model.createDateTime();
			
		}
	}
	public void exchangeTransaction(List<CrofTransaction> crofTransactions){
		for(int i=0;i<crofTransactions.size();i++) {
			Transaction transaction=model.createTransaction();
			transaction.setAmount(crofTransactions.get(i).getAmount());
			transaction.setCreationTime(crofTransactions.get(i).getCreationTime());
		}
	}
	public void exchangeTransaction(CrofTransaction crofTransaction,int index) {
		Transaction transaction=((List<Transaction>)model.getTransaction()).get(index);
		List<CrofAccountSource> crofAccountSources=crofTransaction.getCrofAccountSources();
		List<TransactionImpl.AccountSource> accountSources=new ArrayList<TransactionImpl.AccountSource>();
		for(int i=0;i<crofAccountSources.size();i++) {
			TransactionImpl.AccountSource accountSource=null;
			CrofAccountSource accountsource=crofAccountSources.get(i);
			accountSource=(TransactionImpl.AccountSource)transaction.bindSource(model.getAccount().get(accountsource.getPlayerPosition()));
			
			accountSources.add(accountSource);
		}
		List<CrofAccountTarget> crofAccountTargets=crofTransaction.getCrofAccountTargets();
		List<TransactionImpl.AccountTarget> accountTargets=new ArrayList<TransactionImpl.AccountTarget>();
		for(int i=0;i<crofAccountTargets.size();i++) {
			TransactionImpl.AccountTarget accountTarget=null;
			CrofAccountTarget accounttarget=crofAccountTargets.get(i);
			accountTarget=(TransactionImpl.AccountTarget)transaction.bindTarget(model.getAccount().get(accounttarget.getPlayerPosition()));
			
			accountTargets.add(accountTarget);
		}
		List<CrofTrans> crofTranss = crofTransaction.getCrofTrans();
		for(int i=0;i<crofTranss.size();i++){
			CrofTrans crofTrans=crofTranss.get(i);
			if(crofTrans.getFirst().equals("AccountSource") && crofTrans.getSecond().equals("AccountTarget")){
				accountSources.get(crofTrans.getFirstPosition()).addTrans(accountTargets.get(crofTrans.getSecondPosition()));
			}
			
		}
	}
	public void exchangeCompany(List<CrofCompany> crofCompanys){
		for(int i=0;i<crofCompanys.size();i++) {
			Company company=model.createCompany();
			company.setName(crofCompanys.get(i).getName());
			company.setLegalForm(crofCompanys.get(i).getLegalForm());
			company.setPOBox(crofCompanys.get(i).getPOBox());
			company.setAddresses(crofCompanys.get(i).getAddresses());
			
		}
	}
	public void exchangePerson(List<CrofPerson> crofPersons){
		for(int i=0;i<crofPersons.size();i++) {
			Person person=model.createPerson();
			person.setTitle(crofPersons.get(i).getTitle());
			person.setFirstName(crofPersons.get(i).getFirstName());
			person.setLastName(crofPersons.get(i).getLastName());
			person.setAddress(crofPersons.get(i).getAddress());
			
		}
	}
	public void exchangeBank(List<CrofBank> crofBanks){
		for(int i=0;i<crofBanks.size();i++) {
			Bank bank=model.createBank();
			bank.setName(crofBanks.get(i).getName());
		}
	}
	public void exchangeBank(CrofBank crofBank,int index) {
		Bank bank=((List<Bank>)model.getBank()).get(index);
		List<CrofTransactionMoneyTransfer> crofTransactionMoneyTransfers=crofBank.getCrofTransactionMoneyTransfers();
		List<BankImpl.TransactionMoneyTransfer> transactionMoneyTransfers=new ArrayList<BankImpl.TransactionMoneyTransfer>();
		for(int i=0;i<crofTransactionMoneyTransfers.size();i++) {
			BankImpl.TransactionMoneyTransfer transactionMoneyTransfer=null;
			CrofTransactionMoneyTransfer transactionmoneyTransfer=crofTransactionMoneyTransfers.get(i);
			transactionMoneyTransfer=(BankImpl.TransactionMoneyTransfer)bank.bindMoneyTransfer(model.getTransaction().get(transactionmoneyTransfer.getPlayerPosition()));
			
			transactionMoneyTransfer.setExecution(crofBank.getCrofMoneyTransfers().get(crofTransactionMoneyTransfers.get(i).getPlayedPosition()).getExecution());
			transactionMoneyTransfers.add(transactionMoneyTransfer);
		}
		List<CrofCompanyCustomer> crofCompanyCustomers=crofBank.getCrofCompanyCustomers();
		List<BankImpl.CompanyCustomer> companyCustomers=new ArrayList<BankImpl.CompanyCustomer>();
		for(int i=0;i<crofCompanyCustomers.size();i++) {
			BankImpl.CompanyCustomer companyCustomer=null;
			CrofCompanyCustomer companycustomer=crofCompanyCustomers.get(i);
			companyCustomer=(BankImpl.CompanyCustomer)bank.bindCustomer(model.getCompany().get(companycustomer.getPlayerPosition()));
			
			companyCustomer.setId(crofBank.getCrofCustomers().get(crofCompanyCustomers.get(i).getPlayedPosition()).getId());
			companyCustomers.add(companyCustomer);
		}
		List<CrofPersonCustomer> crofPersonCustomers=crofBank.getCrofPersonCustomers();
		List<BankImpl.PersonCustomer> personCustomers=new ArrayList<BankImpl.PersonCustomer>();
		for(int i=0;i<crofPersonCustomers.size();i++) {
			BankImpl.PersonCustomer personCustomer=null;
			CrofPersonCustomer personcustomer=crofPersonCustomers.get(i);
			personCustomer=(BankImpl.PersonCustomer)bank.bindCustomer(model.getPerson().get(personcustomer.getPlayerPosition()));
			
			personCustomer.setId(crofBank.getCrofCustomers().get(crofPersonCustomers.get(i).getPlayedPosition()).getId());
			personCustomers.add(personCustomer);
		}
		List<CrofPersonConsultant> crofPersonConsultants=crofBank.getCrofPersonConsultants();
		List<BankImpl.PersonConsultant> personConsultants=new ArrayList<BankImpl.PersonConsultant>();
		for(int i=0;i<crofPersonConsultants.size();i++) {
			BankImpl.PersonConsultant personConsultant=null;
			CrofPersonConsultant personconsultant=crofPersonConsultants.get(i);
			personConsultant=(BankImpl.PersonConsultant)bank.bindConsultant(model.getPerson().get(personconsultant.getPlayerPosition()));
			
			personConsultant.setPhone(crofBank.getCrofConsultants().get(crofPersonConsultants.get(i).getPlayedPosition()).getPhone());
			personConsultants.add(personConsultant);
		}
		List<CrofAccountCheckingAccount> crofAccountCheckingAccounts=crofBank.getCrofAccountCheckingAccounts();
		List<BankImpl.AccountCheckingAccount> accountCheckingAccounts=new ArrayList<BankImpl.AccountCheckingAccount>();
		for(int i=0;i<crofAccountCheckingAccounts.size();i++) {
			BankImpl.AccountCheckingAccount accountCheckingAccount=null;
			CrofAccountCheckingAccount accountcheckingAccount=crofAccountCheckingAccounts.get(i);
			accountCheckingAccount=(BankImpl.AccountCheckingAccount)bank.bindCheckingAccount(model.getAccount().get(accountcheckingAccount.getPlayerPosition()));
			
			accountCheckingAccount.setLimit(crofBank.getCrofCheckingAccounts().get(crofAccountCheckingAccounts.get(i).getPlayedPosition()).getLimit());
			accountCheckingAccounts.add(accountCheckingAccount);
		}
		List<CrofAccountSavingsAccount> crofAccountSavingsAccounts=crofBank.getCrofAccountSavingsAccounts();
		List<BankImpl.AccountSavingsAccount> accountSavingsAccounts=new ArrayList<BankImpl.AccountSavingsAccount>();
		for(int i=0;i<crofAccountSavingsAccounts.size();i++) {
			BankImpl.AccountSavingsAccount accountSavingsAccount=null;
			CrofAccountSavingsAccount accountsavingsAccount=crofAccountSavingsAccounts.get(i);
			accountSavingsAccount=(BankImpl.AccountSavingsAccount)bank.bindSavingsAccount(model.getAccount().get(accountsavingsAccount.getPlayerPosition()));
			
			accountSavingsAccount.setFee(crofBank.getCrofSavingsAccounts().get(crofAccountSavingsAccounts.get(i).getPlayedPosition()).getFee());
			accountSavingsAccounts.add(accountSavingsAccount);
		}
		List<CrofOwn_ca> crofOwn_cas = crofBank.getCrofOwn_ca();
		for(int i=0;i<crofOwn_cas.size();i++){
			CrofOwn_ca crofOwn_ca=crofOwn_cas.get(i);
			if(crofOwn_ca.getFirst().equals("CompanyCustomer") && crofOwn_ca.getSecond().equals("AccountCheckingAccount")){
				companyCustomers.get(crofOwn_ca.getFirstPosition()).addOwn_ca(accountCheckingAccounts.get(crofOwn_ca.getSecondPosition()));
			}
			if(crofOwn_ca.getFirst().equals("PersonCustomer") && crofOwn_ca.getSecond().equals("AccountCheckingAccount")){
				personCustomers.get(crofOwn_ca.getFirstPosition()).addOwn_ca(accountCheckingAccounts.get(crofOwn_ca.getSecondPosition()));
			}
			
		}
		List<CrofAdvises> crofAdvisess = crofBank.getCrofAdvises();
		for(int i=0;i<crofAdvisess.size();i++){
			CrofAdvises crofAdvises=crofAdvisess.get(i);
			if(crofAdvises.getFirst().equals("PersonConsultant") && crofAdvises.getSecond().equals("CompanyCustomer")){
				personConsultants.get(crofAdvises.getFirstPosition()).addAdvises(companyCustomers.get(crofAdvises.getSecondPosition()));
			}
			if(crofAdvises.getFirst().equals("PersonConsultant") && crofAdvises.getSecond().equals("PersonCustomer")){
				personConsultants.get(crofAdvises.getFirstPosition()).addAdvises(personCustomers.get(crofAdvises.getSecondPosition()));
			}
			
		}
		List<CrofOwn_sa> crofOwn_sas = crofBank.getCrofOwn_sa();
		for(int i=0;i<crofOwn_sas.size();i++){
			CrofOwn_sa crofOwn_sa=crofOwn_sas.get(i);
			if(crofOwn_sa.getFirst().equals("CompanyCustomer") && crofOwn_sa.getSecond().equals("AccountSavingsAccount")){
				companyCustomers.get(crofOwn_sa.getFirstPosition()).addOwn_sa(accountSavingsAccounts.get(crofOwn_sa.getSecondPosition()));
			}
			if(crofOwn_sa.getFirst().equals("PersonCustomer") && crofOwn_sa.getSecond().equals("AccountSavingsAccount")){
				personCustomers.get(crofOwn_sa.getFirstPosition()).addOwn_sa(accountSavingsAccounts.get(crofOwn_sa.getSecondPosition()));
			}
			
		}
	}
	public void exchangeAccount(List<CrofAccount> crofAccounts){
		for(int i=0;i<crofAccounts.size();i++) {
			Account account=model.createAccount();
			account.setBalance(crofAccounts.get(i).getBalance());
			account.setId(crofAccounts.get(i).getId());
			
		}
	}
	public Model fromJson(String filePath) {
        File file = new File(filePath);
        BufferedReader reader = null;
        String str="";
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = reader.readLine();
            do {  
            		str+=line+"\n";
            		line = reader.readLine();
            }  while(line != null);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {  
            try {  
                if(reader != null){  
                	reader.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
		str=str.trim();
		this.crofModel= new Gson().fromJson(str,CrofModel.class);
		if(!crofModel.getModelName().equals("bank")){
			System.out.println("wrong Model");
			return null;
		}
		exchangeDateTime(crofModel.getCrofDateTimes());
		exchangeCompany(crofModel.getCrofCompanys());
		exchangePerson(crofModel.getCrofPersons());
		exchangeAccount(crofModel.getCrofAccounts());
		List<CrofTransaction> crofTransactions= crofModel.getCrofTransactions();
		exchangeTransaction(crofTransactions);
		List<CrofBank> crofBanks= crofModel.getCrofBanks();
		exchangeBank(crofBanks);
		for(int i = 0; i<crofTransactions.size(); i++){
			exchangeTransaction(crofTransactions.get(i),i);
		}
		for(int i = 0; i<crofBanks.size(); i++){
			exchangeBank(crofBanks.get(i),i);
		}
		return this.model;
	}
}
