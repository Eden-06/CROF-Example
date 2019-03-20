package bank.crof;

import java.util.List;
import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;


public class CrofTransaction{	
	private double amount;
	private int creationTime;
	
	public double getAmount(){
		return amount;
	}
	
	public void setAmount(double amount){
		this.amount = amount;
	}
	public int getCreationTime(){
		return creationTime;
	}
	
	public void setCreationTime(int creationTime){
		this.creationTime = creationTime;
	}
		
	@SerializedName("targets")
	List<CrofTarget> crofTargets=new ArrayList<CrofTarget>();
	
	public void setCrofTargets(List<CrofTarget> crofTargets){
		this.crofTargets=crofTargets;
	}
	
	public List<CrofTarget> getCrofTargets(){
		return crofTargets;
	}
	
	@SerializedName("sources")
	List<CrofSource> crofSources=new ArrayList<CrofSource>();
	
	public void setCrofSources(List<CrofSource> crofSources){
		this.crofSources=crofSources;
	}
	
	public List<CrofSource> getCrofSources(){
		return crofSources;
	}
	
	 
	@SerializedName("accountTargets")
	List<CrofAccountTarget> crofAccountTargets=new ArrayList<CrofAccountTarget>();
	
	public void setCrofAccountTargets(List<CrofAccountTarget> crofAccountTargets){
		this.crofAccountTargets=crofAccountTargets;
	}
	
	public List<CrofAccountTarget> getCrofAccountTargets(){
		return crofAccountTargets;
	}
	
	@SerializedName("accountSources")
	List<CrofAccountSource> crofAccountSources=new ArrayList<CrofAccountSource>();
	
	public void setCrofAccountSources(List<CrofAccountSource> crofAccountSources){
		this.crofAccountSources=crofAccountSources;
	}
	
	public List<CrofAccountSource> getCrofAccountSources(){
		return crofAccountSources;
	}
	
	
	
	@SerializedName("transs")
	List<CrofTrans> crofTrans=new ArrayList<CrofTrans>();
	
	public void setCrofTrans(List<CrofTrans> crofTrans){
		this.crofTrans=crofTrans;
	}
	
	public List<CrofTrans> getCrofTrans(){
		return crofTrans;
	}
	
}





