import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.flipturnapps.kevinLibrary.net.ClientData;

public class FakeageClient extends ClientData
{
	private static final boolean SHOULD_SEND_UR_LEGIT_MESSAGE = true;
	private static final String UR_LEGIT = "Ur legit!";
	private boolean isLegit = false;
	private FakeageServer fakeageServer;
	private PrintWriter writer;
	public FakeageClient(Socket socket, FakeageServer server) throws IOException 
	{
		super(socket, server);
		this.setFakeageServer(server);
		writer = new PrintWriter(socket.getOutputStream());
	}

	public void dealWithInput(String input) 
	{
		if(!isLegit() && input != null)
		{
			if(input.equalsIgnoreCase(this.getFakeageServer().getPassword()))
			{
				this.setLegit(true);
				if(SHOULD_SEND_UR_LEGIT_MESSAGE)
				{
					writer.println(UR_LEGIT);
					writer.flush();
				}
				else
					this.setLegit(false);
				return;
			}	
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
