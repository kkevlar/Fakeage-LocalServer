
public class Lie extends ResponseData
{
	
	private Player[] origins;
	public Lie(String choice, Player[] players) 
	{
		this.setText(choice);
		origins = players;
	}
	public Lie(String choice, Player player)
	{
		this(choice,new Player[]{player});
	}
	
	public Player[] getOrigins() {
		return origins;
	}
	public void setOrigins(Player[] origin) {
		this.origins = origin;
	}
	@Override
	public boolean isLie()
	{
		return true;
	}
	
	
}
