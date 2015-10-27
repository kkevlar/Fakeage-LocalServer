
public class Lie 
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Player[] getOrigins() {
		return origins;
	}
	public void setOrigins(Player[] origin) {
		this.origins = origin;
	}
	public boolean equals(Object object)
	{
		if (object instanceof Lie)
		{
			Lie lie = (Lie) object;
			if(lie.getText().equalsIgnoreCase(this.getText()));
			return true;
		}
		return false;
		
	}
	
}
