import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TestClient {

	public static void main(String args[]) throws UnknownHostException, IOException {
		int port = 7004;
		InetAddress host = InetAddress.getByName("localhost");
		try (Socket connection = new Socket(host, port);

				PrintWriter output = new PrintWriter(connection.getOutputStream(), true);
				BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				Scanner sc = new Scanner(System.in);) {
			String u = "";
			String message = "";
			System.out.println("Enter hyour message \n");
			while ((message = sc.next()) != "quit") {

				output.println(message);
				// output.flush();
				u = input.readLine();
				System.out.println("Server sent " + u);

			}
			output.println("GoodBye!");
		}

	}

}
