import java.io.IOException;

public class Main implements QuestionPool
{

	public static void main(String[] args) throws IOException, InterruptedException
	{
		new Main().go(args);
		
	}

	private void go(String[] args) throws IOException, InterruptedException
	{
		LocalServer server = new LocalServer();
		//int count = Integer.parseInt(args[0]);
		int count = 3;
		boolean namesOK = false;
		while(count > server.getClientCount() ||  !namesOK)
		{
			Thread.sleep(1000);
			boolean noName =false;
			for(int i = 0; i < server.getClientCount(); i++)
			{
				LocalClient client = server.getClient(i);
				if(client.getName() == null || client.getName().equals(""))
				{
					noName = true;
					break;
				}
			}
			namesOK = !noName;
		}
		Player[] players = new Player[server.getClientCount()];
		for(int i = 0; i < server.getClientCount(); i++)
		{
			players[i] = server.getClient(i);
		}
		GameCore core = new GameCore(players, this);
		core.launchGame();
	}

	@Override
	public Question getQuestion() 
	{
		Question q = new Question();
		q.setQuestionText("What was the color of George Washington's white horse?");
		q.setTruth("white");
		Player p = null;
		q.setLies(new Lie[]{new Lie("Black",p) , new Lie("HorseyColored",p), new Lie("Pink",p)});
		return q;
	}

}
