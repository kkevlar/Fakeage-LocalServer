
public class Lie extends ResponseData
{
	private String text;
	private Player[] origins;
	public Lie(String choice, Player[] players) 
	{
		text = choice;
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
