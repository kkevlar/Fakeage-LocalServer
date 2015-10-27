import java.util.ArrayList;

public class ChoicePair 
{
	private ArrayList<Player> players = new ArrayList<Player>();
	private ResponseData sharedChoice;
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	public ResponseData getSharedChoice() {
		return sharedChoice;
	}
	public void setSharedChoice(ResponseData sharedChoice) {
		this.sharedChoice = sharedChoice;
	}
}
