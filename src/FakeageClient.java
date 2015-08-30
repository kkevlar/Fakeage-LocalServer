import java.io.IOException;
import java.net.Socket;

import com.flipturnapps.kevinLibrary.net.ClientData;

public class FakeageClient extends ClientData
{
	private boolean isLegit = false;
	private FakeageServer fakeageServer;
	public FakeageClient(Socket socket, FakeageServer server) throws IOException 
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

	public FakeageServer getFakeageServer() {
		return fakeageServer;
	}

	public void setFakeageServer(FakeageServer fakeageServer) {
		this.fakeageServer = fakeageServer;
	}

}
