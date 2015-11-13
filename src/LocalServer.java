import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.flipturnapps.kevinLibrary.net.KServer;

public class LocalServer extends KServer<LocalClient> 
{

	private static final int FAKEAGE_PORT = 25568;
	private Logger anonymousLogger;

	public LocalServer() throws IOException
	{
		super(FAKEAGE_PORT);
	}

	@Override
	protected void newMessage(String message, LocalClient client) 
	{
		client.dealWithInput(message);
		System.out.println("msg. " + message);
	}

	@Override
	protected LocalClient getNewClientData(Socket socket, KServer<LocalClient> kServer) 
	{
		try 
		{
			return new LocalClient(socket, this);
		} 
		catch (IOException e)
		{
			getLogger().log(Level.SEVERE, "Had to give the server a null fakeageclient!",e);
		}
		return null;
	}

	private Logger getLogger()  
	{
		if(anonymousLogger == null)
			anonymousLogger = Logger.getAnonymousLogger();
		return anonymousLogger;
	}

	@Override
	protected void newClient(LocalClient data)
	{
		System.out.println("ello i got new client");
	}

}
