import java.io.IOException;
import java.net.Socket;
import java.net.SocketImpl;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.flipturnapps.kevinLibrary.net.KServer;

public class Server extends KServer<FakeageClient> 
{

	private static final int FAKEAGE_PORT = 23454;
	private Logger anonymousLogger;
	private String password;

	public Server(String password) throws IOException
	{
		super(FAKEAGE_PORT);
		this.setPassword(password);
	}

	@Override
	protected void newMessage(String message, FakeageClient client) 
	{
		client.dealWithInput(message);
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
		if(anonymousLogger == null)
			anonymousLogger = Logger.getAnonymousLogger();
		return anonymousLogger;
	}

	@Override
	protected void newClient(FakeageClient data)
	{

	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

}
