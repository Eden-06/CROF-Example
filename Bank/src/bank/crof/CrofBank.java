package bank.crof;

import java.util.List;
import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;


public class CrofBank{	
	private String name;
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
		
	@SerializedName("customers")
	List<CrofCustomer> crofCustomers=new ArrayList<CrofCustomer>();
	
	public void setCrofCustomers(List<CrofCustomer> crofCustomers){
		this.crofCustomers=crofCustomers;
	}
	
	public List<CrofCustomer> getCrofCustomers(){
		return crofCustomers;
	}
	
	@SerializedName("savingsAccounts")
	List<CrofSavingsAccount> crofSavingsAccounts=new ArrayList<CrofSavingsAccount>();
	
	public void setCrofSavingsAccounts(List<CrofSavingsAccount> crofSavingsAccounts){
		this.crofSavingsAccounts=crofSavingsAccounts;
	}
	
	public List<CrofSavingsAccount> getCrofSavingsAccounts(){
		return crofSavingsAccounts;
	}
	
	@SerializedName("moneyTransfers")
	List<CrofMoneyTransfer> crofMoneyTransfers=new ArrayList<CrofMoneyTransfer>();
	
	public void setCrofMoneyTransfers(List<CrofMoneyTransfer> crofMoneyTransfers){
		this.crofMoneyTransfers=crofMoneyTransfers;
	}
	
	public List<CrofMoneyTransfer> getCrofMoneyTransfers(){
		return crofMoneyTransfers;
	}
	
	@SerializedName("checkingAccounts")
	List<CrofCheckingAccount> crofCheckingAccounts=new ArrayList<CrofCheckingAccount>();
	
	public void setCrofCheckingAccounts(List<CrofCheckingAccount> crofCheckingAccounts){
		this.crofCheckingAccounts=crofCheckingAccounts;
	}
	
	public List<CrofCheckingAccount> getCrofCheckingAccounts(){
		return crofCheckingAccounts;
	}
	
	@SerializedName("consultants")
	List<CrofConsultant> crofConsultants=new ArrayList<CrofConsultant>();
	
	public void setCrofConsultants(List<CrofConsultant> crofConsultants){
		this.crofConsultants=crofConsultants;
	}
	
	public List<CrofConsultant> getCrofConsultants(){
		return crofConsultants;
	}
	
	 
	@SerializedName("personCustomers")
	List<CrofPersonCustomer> crofPersonCustomers=new ArrayList<CrofPersonCustomer>();
	
	public void setCrofPersonCustomers(List<CrofPersonCustomer> crofPersonCustomers){
		this.crofPersonCustomers=crofPersonCustomers;
	}
	
	public List<CrofPersonCustomer> getCrofPersonCustomers(){
		return crofPersonCustomers;
	}
	
	@SerializedName("companyCustomers")
	List<CrofCompanyCustomer> crofCompanyCustomers=new ArrayList<CrofCompanyCustomer>();
	
	public void setCrofCompanyCustomers(List<CrofCompanyCustomer> crofCompanyCustomers){
		this.crofCompanyCustomers=crofCompanyCustomers;
	}
	
	public List<CrofCompanyCustomer> getCrofCompanyCustomers(){
		return crofCompanyCustomers;
	}
	
	@SerializedName("accountSavingsAccounts")
	List<CrofAccountSavingsAccount> crofAccountSavingsAccounts=new ArrayList<CrofAccountSavingsAccount>();
	
	public void setCrofAccountSavingsAccounts(List<CrofAccountSavingsAccount> crofAccountSavingsAccounts){
		this.crofAccountSavingsAccounts=crofAccountSavingsAccounts;
	}
	
	public List<CrofAccountSavingsAccount> getCrofAccountSavingsAccounts(){
		return crofAccountSavingsAccounts;
	}
	
	@SerializedName("transactionMoneyTransfers")
	List<CrofTransactionMoneyTransfer> crofTransactionMoneyTransfers=new ArrayList<CrofTransactionMoneyTransfer>();
	
	public void setCrofTransactionMoneyTransfers(List<CrofTransactionMoneyTransfer> crofTransactionMoneyTransfers){
		this.crofTransactionMoneyTransfers=crofTransactionMoneyTransfers;
	}
	
	public List<CrofTransactionMoneyTransfer> getCrofTransactionMoneyTransfers(){
		return crofTransactionMoneyTransfers;
	}
	
	@SerializedName("accountCheckingAccounts")
	List<CrofAccountCheckingAccount> crofAccountCheckingAccounts=new ArrayList<CrofAccountCheckingAccount>();
	
	public void setCrofAccountCheckingAccounts(List<CrofAccountCheckingAccount> crofAccountCheckingAccounts){
		this.crofAccountCheckingAccounts=crofAccountCheckingAccounts;
	}
	
	public List<CrofAccountCheckingAccount> getCrofAccountCheckingAccounts(){
		return crofAccountCheckingAccounts;
	}
	
	@SerializedName("personConsultants")
	List<CrofPersonConsultant> crofPersonConsultants=new ArrayList<CrofPersonConsultant>();
	
	public void setCrofPersonConsultants(List<CrofPersonConsultant> crofPersonConsultants){
		this.crofPersonConsultants=crofPersonConsultants;
	}
	
	public List<CrofPersonConsultant> getCrofPersonConsultants(){
		return crofPersonConsultants;
	}
	
	
	
	@SerializedName("own_cas")
	List<CrofOwn_ca> crofOwn_ca=new ArrayList<CrofOwn_ca>();
	
	public void setCrofOwn_ca(List<CrofOwn_ca> crofOwn_ca){
		this.crofOwn_ca=crofOwn_ca;
	}
	
	public List<CrofOwn_ca> getCrofOwn_ca(){
		return crofOwn_ca;
	}
	
	
	@SerializedName("advisess")
	List<CrofAdvises> crofAdvises=new ArrayList<CrofAdvises>();
	
	public void setCrofAdvises(List<CrofAdvises> crofAdvises){
		this.crofAdvises=crofAdvises;
	}
	
	public List<CrofAdvises> getCrofAdvises(){
		return crofAdvises;
	}
	
	
	@SerializedName("own_sas")
	List<CrofOwn_sa> crofOwn_sa=new ArrayList<CrofOwn_sa>();
	
	public void setCrofOwn_sa(List<CrofOwn_sa> crofOwn_sa){
		this.crofOwn_sa=crofOwn_sa;
	}
	
	public List<CrofOwn_sa> getCrofOwn_sa(){
		return crofOwn_sa;
	}
	
}












