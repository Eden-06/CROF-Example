package bank.crof;


public class CrofOwn_sa{

	private String first;
	private String second;
	
	public int getFirstPosition() {
		return Integer.parseInt(first.substring(first.indexOf("@")+1));
	}
	public String getFirst() {
		return first.substring(0,first.indexOf("@"));
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public int getSecondPosition() {
		return Integer.parseInt(second.substring(second.indexOf("@")+1));
	}
	public String getSecond() {
		return second.substring(0,second.indexOf("@"));
	}
	public void setSecond(String second) {
		this.second = second;
	}
}
