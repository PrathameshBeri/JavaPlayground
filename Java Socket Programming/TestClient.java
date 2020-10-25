import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {

	public static void main(String args[]) throws UnknownHostException, IOException {
		int port = 7004;
		InetAddress host = InetAddress.getByName("localhost");
		Socket connection = new Socket(host, port);
		System.out.println("connection successful to" + connection.getRemoteSocketAddress());

		PrintWriter output = new PrintWriter(connection.getOutputStream(), true);
		BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		while (true) {
			System.out.println("Writing to the server");
			output.println("hello from " + connection.getLocalSocketAddress() + " \n");
			System.out.println("Wrote to the server");

			String i = input.readLine();

			System.out.println("Server sent " + i);
			output.flush();
			output.println("GoodBye!");
			break;

		}
		output.close();
		connection.close();
		input.close();

	}

}
