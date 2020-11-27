import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestServer {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int serverport = 7004;
		ServerSocket s = new ServerSocket(serverport, 100);
		s.setSoTimeout(1000000);
		System.out.println("Waiting for client on port " + s.getLocalPort());
		/*
		 * try (Socket connection = s.accept(); PrintWriter toClient = new
		 * PrintWriter(connection.getOutputStream(), true); BufferedReader br = new
		 * BufferedReader(new InputStreamReader(connection.getInputStream()));) {
		 * 
		 * String line = "";
		 * 
		 * while ((line = br.readLine()) != null) {
		 * 
		 * toClient.println("Server received: " + line); toClient.flush();
		 * 
		 * } }
		 */
		System.out.println("Shutting down server");
		Integer count = 0;
		ExecutorService pool = Executors.newFixedThreadPool(10);
		while (true) {
			Socket connection = s.accept();
			ClientConnection c = new ClientConnection(count.toString(), connection);
			pool.submit(c);
			count++;
			System.out.println("New client connected" + count);

		}

	}

}

class ClientConnection implements Runnable {

	String name;
	PrintWriter toClient;
	BufferedReader fromClient;
	Socket connection;

	ClientConnection(String name, Socket conn) throws IOException {
		this.name = name;
		connection = conn;
		toClient = new PrintWriter(conn.getOutputStream(), true);
		fromClient = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String line = "";

		try {
			while ((line = fromClient.readLine()) != null) {

				toClient.println("Server received: " + line);
				toClient.flush();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
