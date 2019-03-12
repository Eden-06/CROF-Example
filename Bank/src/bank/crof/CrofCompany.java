package bank.crof;


public class CrofCompany  {
	private String name;
	private String legalForm;
	private String POBox;
	private String addresses;
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getLegalForm(){
		return legalForm;
	}
	
	public void setLegalForm(String legalForm){
		this.legalForm = legalForm;
	}
	public String getPOBox(){
		return POBox;
	}
	
	public void setPOBox(String POBox){
		this.POBox = POBox;
	}
	public String getAddresses(){
		return addresses;
	}
	
	public void setAddresses(String addresses){
		this.addresses = addresses;
	}
	
}
