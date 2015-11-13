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
	private static final long foolTimer = 10000;
	private static final long truthTimer = 10000;
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
		System.out.println(question.getQuestionText() + "|" + question.getTruth());
		tellQuestion(question);
		try {
			Thread.sleep(foolTimer);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Lie[] lies = getLies(question);
		System.out.println("lielength:" + lies.length);
		ResponseData[] allChoices = new ResponseData[lies.length + 1];
		int[] newIndecies = NumberScrambler.scrambledArray(allChoices.length);
		for (int i = 0; i < newIndecies.length; i++) {
			System.out.println(newIndecies[i]);
		}
		allChoices[newIndecies[0]] = new Truth(question.getTruth());
		for (int i = 1; i < newIndecies.length; i++) 
		{
			allChoices[newIndecies[i]] = lies[i-1];
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
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.print(".");
			}
			String description = "";
			if(!currPair.getSharedChoice().isLie())
				description = "The TRUTH!";
			else
			{
				Lie lie = (Lie) currPair.getSharedChoice();
				for(int i = 0; i < lie.getOrigins().length; i++)
				{
					if(i != 0)
					{
						description += " And ";
					}
					description += lie.getOrigins()[i].getName()+"'s";
				}
				description +=" Lie";
			}
			System.out.println(description);
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
					{
						player.holdResponseData(choices[y]);
						System.out.println("player " + player.getName() + " is holding responsedata:" + player.getResponseData());
					}
				}
			}
		}
	}
	private void sayAllToAll(ResponseData[] allChoices)
	{
		for(int x = 0; x < players.length; x++)
		{
			Player player = players[x];
			player.setTellTruth(false);
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
			Player currPlayer = players[i];
			System.out.println(currPlayer.getName() + ": " + currPlayer.getChoice());
			Lie lie = new Lie(currPlayer.getChoice(),currPlayer);
			if(lie != null && 
					!(lie.equals("")) &&
					!lie.getText().equalsIgnoreCase(question.getTruth()))
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
							nor[or.length] = currPlayer;
							liesList.get(x).setOrigins(nor);
						}
					}
				}
			}
			currPlayer.resetChoice();
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
			players[i].setTellTruth(true);
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
