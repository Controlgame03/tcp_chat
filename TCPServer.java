package TCPChat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Date;
import java.util.Scanner;

public class TCPServer {
	public static void main(String[] argv) {		
		try {
			Scanner in = new Scanner(System.in);
			Socket serverSocket;
			
			System.out.print("Введите порт на котором работает сервер: ");
			int serverPort = Integer.parseInt(in.next());
			
			ServerSocket server = new ServerSocket(serverPort);
			System.out.println("Ожидание подключения клиента...");
			/*InetAddress clientHost = InetAddress.getByName(argv[0]);
			InetAddress serverHost = InetAddress.getByName(argv[2]);
			int clientPort = Integer.parseInt(argv[1]);
			int serverPort = Integer.parseInt(argv[3]);*/
			
			//clientSocket = new Socket(serverHost, serverPort, localHost, localPort);
			//serverSocket = new Socket(clientHost, clientPort, serverHost, serverPort);
			
			serverSocket = server.accept();
			System.out.print("Подключился клиент. Ip адрес: ");
			System.out.print(serverSocket.getInetAddress().toString());
			System.out.print("; Порт: ");
			System.out.println(serverSocket.getPort());
			Reciever reciever = new Reciever(serverSocket);
			//Sender sender = new Sender(serverSocket, name);
			//reciever.start();
			reciever.start();
		}
		catch(Exception exep) {
			System.out.println(exep.getMessage());
			System.out.println(exep.getStackTrace());
		}
	}
}

