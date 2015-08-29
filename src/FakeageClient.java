import java.io.IOException;
import java.net.Socket;

import com.flipturnapps.kevinLibrary.net.ClientData;
import com.flipturnapps.kevinLibrary.net.KServer;

public class FakeageClient extends ClientData
{

	public FakeageClient(Socket socket, KServer<?> server) throws IOException {
		super(socket, server);
		// TODO Auto-generated constructor stub
	}

}
