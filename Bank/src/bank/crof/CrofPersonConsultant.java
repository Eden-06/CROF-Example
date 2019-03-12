package bank.crof;


public class CrofPersonConsultant{

	private String player;
	private String played;
	
	public int getPlayerPosition() {
		return Integer.parseInt(player.substring(player.indexOf("@")+1));
	}
	public String getPlayer() {
		return player.substring(0,player.indexOf("@"));
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public int getPlayedPosition() {
		return Integer.parseInt(played.substring(played.indexOf("@")+1));
	}
	public String getPlayed() {
		return played.substring(0,played.indexOf("@"));
	}
	public void setPlayed(String played) {
		this.played = played;
	}
}
