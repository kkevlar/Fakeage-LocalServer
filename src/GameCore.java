
public class GameCore 
{
	private Player[] players;
	private QuestionPool qpool;
	private static final int roundCount = 5;
	private static final int truthPoints = 10;
	private static final int foolPoints = 5;
	private static final long foolTimer = 5000;
	private static final long truthTimer = 5000;
	public GameCore(Player[] players, QuestionPool pool)
	{
		this.players = players;
		this.qpool = pool;
	}
	public void launchGame()
	{
		resetPoints();
	}
	private void resetPoints() 
	{
		for (int i = 0; i < players.length; i++) 
		{
			players[i].setPoints(0);
		}
	}
	
}
