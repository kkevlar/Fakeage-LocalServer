import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.flipturnapps.kevinLibrary.net.ClientData;

public class LocalClient extends ClientData implements Player
{
	private static final String FOUND_TRUTH_MESSAGE = "Dats the truth!";
	private LocalServer fakeageServer;
	private PrintWriter writer;
	private String choice;
	private int id;
	private int points;
	private String truth;
	private ResponseData data;
	private String name;
	public LocalClient(Socket socket, LocalServer server) throws IOException 
	{
		super(socket, server);
		this.setFakeageServer(server);
		writer = new PrintWriter(socket.getOutputStream());
		writer.println("Name pls?");
	}

	public void dealWithInput(String input) 
	{	
		if(input != null && !input.equals(""))
		{
			if(this.getName() == null)
			{
				this.setName(input);
			}
			else
			{
				setChoice(input);
			}
		}
	}

	private void setChoice(String input) 
	{
		if(input.equalsIgnoreCase(truth))
			this.sendMessage(FOUND_TRUTH_MESSAGE);
		else
			this.choice = input;
	}

	public LocalServer getFakeageServer() {
		return fakeageServer;
	}

	public void setFakeageServer(LocalServer fakeageServer) {
		this.fakeageServer = fakeageServer;
	}

	@Override
	public void sayTo(String s) 
	{
		this.sendMessage(s);
	}

	@Override
	public String getChoice() 
	{
		return this.choice;
	}

	@Override
	public boolean hasChoice() 
	{
		return choice != null;
	}

	@Override
	public void resetChoice() 
	{
		choice = null;
	}

	@Override
	public void setId(int i) 
	{
		id = i;

	}

	@Override
	public int getId() 
	{
		return id;

	}

	public int getPoints() 
	{
		return points;
	}

	public void setPoints(int points) 
	{
		this.points = points;
	}

	@Override
	public void truth(String s) 
	{
	this.truth = s;
	}

	@Override
	public void holdResponseData(ResponseData choice)
	{
		this.data = choice;		
	}

	@Override
	public ResponseData getResponseData() 
	{
		return this.data;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public void setName(String nm) 
	{
		name = nm;
	}
}
