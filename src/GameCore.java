import java.util.ArrayList;

public class GameCore 
{
	private Player[] players;
	private QuestionPool qpool;
	//private static final int roundCount = 1;
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
		Question question = qpool.getQuestion();
		tellTruth(question);
		tellQuestion(question);
		try {
			Thread.sleep(foolPoints);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Lie[] lies = getLies(question);
		//tell answers
		//get each answer
		//announce failure
	}
	private Lie[] getLies(Question question)
	{
		ArrayList<Lie> liesList = new ArrayList<Lie>();
		for (int i = 0; i < players.length; i++) 
		{
			Lie lie = new Lie(players[i].getChoice(),players[i]);
			if(lie != null && !(lie.equals("")) &&!lie.getText().equalsIgnoreCase(question.getTruth()))
			{ 
				if(!(liesList.contains(lie)))
				liesList.add(lie);
				else
				{
					for(int x = 0; x < liesList.size(); x++)
					{
						if(liesList.get(x).equals(lie))
						{
							Player[] or = liesList.get(x).getOrigins();
							Player[] nor = new Player[or.length + 1];
							for(int y = 0; y < or.length; y++)
							{
								nor[y] = or[y];
							}
							nor[or.length] = players[i];
							liesList.get(x).setOrigins(nor);
						}
					}
				}
			}
		}
		int count = 0;
		while (liesList.size() < players.length && count < question.getLies().length)
		{
			Lie lie = question.getLies()[count];
			if(lie != null && !(lie.equals("")) && !(liesList.contains(lie)) && !lie.getText().equalsIgnoreCase(question.getTruth()))
			liesList.add(lie);
			count++;
		}
		Lie[] lies = new Lie[liesList.size()];
		for(int i = 0; i < liesList.size(); i++)
		{
			lies[i] = liesList.get(i);
		}
		return lies;
	}
	public void tellQuestion (Question q)
	{
		for (int i = 0; i < players.length; i++) 
		{
			players[i].sayTo(q.getQuestionText());
			players[i].resetChoice();
		}
	}
	private void tellTruth(Question q)
	{
		for (int i = 0; i < players.length; i++) 
		{
			players[i].truth(q.getTruth());
		}	
	}
	private void resetPoints() 
	{
		for (int i = 0; i < players.length; i++) 
		{
			players[i].setPoints(0);
		}
	}
	
}
