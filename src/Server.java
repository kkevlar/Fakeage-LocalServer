import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.flipturnapps.kevinLibrary.net.KServer;

public class Server extends KServer<FakeageClient> 
{

	private Logger anonymousLogger;

	public Server(int port) throws IOException
	{
		super(port);
	}

	@Override
	protected void newMessage(String message, FakeageClient client) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected FakeageClient getNewClientData(Socket socket, KServer<FakeageClient> kServer) 
	{
		try 
		{
			return new FakeageClient(socket, kServer);
		} 
		catch (IOException e)
		{
		    getLogger().log(Level.SEVERE, "Had to give the server a null fakeageclient!",e);
		}
		return null;
	}

	private Logger getLogger() 
	{
		
	 anonymousLogger = Logger.getAnonymousLogger();
		return anonymousLogger;
	}

	@Override
	protected void newClient(FakeageClient data)
	{
		
	}

}
