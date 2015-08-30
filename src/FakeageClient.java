import java.io.IOException;
import java.net.Socket;

import com.flipturnapps.kevinLibrary.net.ClientData;

public class FakeageClient extends ClientData
{
	private boolean isLegit = false;
	private Server fakeageServer;
	public FakeageClient(Socket socket, Server server) throws IOException 
	{
		super(socket, server);
		this.setFakeageServer(server);
	}

	public void dealWithInput(String input) 
	{
		if(!isLegit())
		{
			if(input.equalsIgnoreCase(this.getFakeageServer().getPassword()))
				this.setLegit(true);
			else
				this.setLegit(false);
		}
			
	}

	public boolean isLegit() 
	{
		return isLegit;
	}

	public void setLegit(boolean isLegit)
	{
		this.isLegit = isLegit;
	}

	public Server getFakeageServer() {
		return fakeageServer;
	}

	public void setFakeageServer(Server fakeageServer) {
		this.fakeageServer = fakeageServer;
	}

}
