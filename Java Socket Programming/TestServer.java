import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int serverport = 7004;
		ServerSocket s = new ServerSocket(serverport, 2);
		s.setSoTimeout(1000000);
		while (true) {
			System.out.println("Waiting for client on port " + s.getLocalPort());
			Socket connection = s.accept();
			System.out.println("Just connected to " + connection.getRemoteSocketAddress());

			PrintWriter toClient = new PrintWriter(connection.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = br.readLine();

			System.out.println("Server receives message " + line);
			toClient.write("Thanks for messaging me \n ");
			toClient.flush();
			if (line.equals("GoodBye!")) {
				break;
			}
		}

		System.out.println("Shutting down server");
		s.close();

	}

}
