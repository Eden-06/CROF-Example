package bank.crof;

import java.util.ArrayList;
import java.util.List;


public class CrofModel{
	private String model="bank";
	public String getModelName(){
		return this.model;
	}
	List<CrofTransaction> crofTransactions=new ArrayList<CrofTransaction>();
	public List<CrofTransaction> getCrofTransactions() {
		return crofTransactions;
	}

	public void setCrofTransactions(List<CrofTransaction> crofTransactions) {
		this.crofTransactions = crofTransactions;
	}
	List<CrofCompany> crofCompanys=new ArrayList<CrofCompany>();
	public List<CrofCompany> getCrofCompanys() {
		return crofCompanys;
	}

	public void setCrofCompanys(List<CrofCompany> crofCompanys) {
		this.crofCompanys = crofCompanys;
	}
	List<CrofDateTime> crofDateTimes=new ArrayList<CrofDateTime>();
	public List<CrofDateTime> getCrofDateTimes() {
		return crofDateTimes;
	}

	public void setCrofDateTimes(List<CrofDateTime> crofDateTimes) {
		this.crofDateTimes = crofDateTimes;
	}
	List<CrofAccount> crofAccounts=new ArrayList<CrofAccount>();
	public List<CrofAccount> getCrofAccounts() {
		return crofAccounts;
	}

	public void setCrofAccounts(List<CrofAccount> crofAccounts) {
		this.crofAccounts = crofAccounts;
	}
	List<CrofPerson> crofPersons=new ArrayList<CrofPerson>();
	public List<CrofPerson> getCrofPersons() {
		return crofPersons;
	}

	public void setCrofPersons(List<CrofPerson> crofPersons) {
		this.crofPersons = crofPersons;
	}
	List<CrofBank> crofBanks=new ArrayList<CrofBank>();
	public List<CrofBank> getCrofBanks() {
		return crofBanks;
	}

	public void setCrofBanks(List<CrofBank> crofBanks) {
		this.crofBanks = crofBanks;
	}


}
