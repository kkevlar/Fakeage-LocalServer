import java.util.ArrayList;

import com.flipturnapps.kevinLibrary.helper.NumberScrambler;
import com.flipturnapps.kevinLibrary.helper.Numbers;

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
			Thread.sleep(foolTimer);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Lie[] lies = getLies(question);
		ResponseData[] allChoices = new ResponseData[lies.length + 1];
		int[] newIndecies = NumberScrambler.scrambledArray(allChoices.length);
		allChoices[newIndecies[0]] = new Truth(question.getTruth());
		for (int i = 1; i < newIndecies.length; i++) 
		{
			allChoices[newIndecies[i]] = lies[i];
		}
		sayAllToAll(allChoices);
		try {
			Thread.sleep(truthTimer);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//get each answer
		validateAllChoices(allChoices);
		//create ChoicePairs
		
		ArrayList<ChoicePair> pairs = new ArrayList<ChoicePair>();
		for(int x = 0; x < players.length; x++)
		{
			Player player = players[x];
			boolean added = false;
			for(int y = 0; y < pairs.size() && !added; y++)
			{
				ChoicePair currPair = pairs.get(y);
				if(currPair.getSharedChoice().equals(player.getResponseData()))
				{
					currPair.getPlayers().add(player);
					added = true;
				}
			}
			if(!added)
			{
				ChoicePair newChoice = new ChoicePair();
				newChoice.setSharedChoice(players[x].getResponseData());
				newChoice.getPlayers().add(players[x]);
			}
		}
		showFaiureDoPoints(pairs);
		//announce failure
	}
	private void showFaiureDoPoints(ArrayList<ChoicePair> pairs) 
	{
		System.out.println("Results: ");
		while(!pairs.isEmpty())	
		{
			int index = (int) (Math.random() * pairs.size());
			if(!pairs.get(index).getSharedChoice().isLie() && pairs.size() > 1)
				continue;
			ChoicePair currPair = pairs.remove(index);
			System.out.print(currPair.getPlayers() + " chose " + currPair.getSharedChoice().getText());
			for(int i = 0; i < 5; i++)
			{
				Thread.sleep(1000);
				System.out.print(".");
			}
			//say whos lie/truth
		}
	}
	private void validateAllChoices(ResponseData[] choices)
	{
		for(int x = 0; x < players.length; x++)
		{
			Player player = players[x];
			player.holdResponseData(null);
			String choice = player.getChoice();
			player.resetChoice();
			if(choice != null && !choice.equals(""))
			{
				for(int y = 0; y < choices.length; y++)
				{
					if(choice.equalsIgnoreCase(choices[y].getText()))
						player.holdResponseData(choices[y]);
				}
			}
		}
	}
	private void sayAllToAll(ResponseData[] allChoices)
	{
		for(int x = 0; x < players.length; x++)
		{
			Player player = players[x];
			player.sayTo("Answer choices: ");
			for(int y = 0; y < allChoices.length; y++)
			{
				player.sayTo(allChoices[y].getText());
			}
		}
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
			players[i].resetChoice();
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
