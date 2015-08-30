import java.io.IOException;
import java.net.Socket;

import com.flipturnapps.kevinLibrary.net.ClientData;
import com.flipturnapps.kevinLibrary.net.KServer;

public class FakeageClient extends ClientData
{
	private boolean isLegit = false;
	public FakeageClient(Socket socket, KServer<?> server) throws IOException 
	{
		super(socket, server);
	}

	public void dealWithInput(String input) 
	{
		if(!isLegit())
		{
			if(input.equalsIgnoreCase(this.server))
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

}
