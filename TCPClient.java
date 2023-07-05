package TCPChat;

import java.net.*;
import java.io.*;
import java.util.Date;

/*
 * 4.07 клиент отправляет пакеты, но не получпет их
 * 
 * */


public class TCPClient {
	public static void main(String[] argv) {
		Socket clientSocket;
		try {
			String name = "denis";
			
			InetAddress localHost = InetAddress.getByName(argv[0]);
			InetAddress serverHost = InetAddress.getByName(argv[2]);
			int localPort = Integer.parseInt(argv[1]);
			int serverPort = Integer.parseInt(argv[3]);
			
			clientSocket = new Socket(serverHost, serverPort, localHost, localPort);
			
			Reciever reciever = new Reciever(clientSocket);
			Sender sender = new Sender(clientSocket, name);
			reciever.start();
			sender.start();
		}
		catch(Exception exep) {
			System.out.println(exep.getMessage());
			System.out.println(exep.getStackTrace());
		}
	}
}

class Reciever extends Thread {
	Socket currentSocket;
	
	private static BufferedReader in;
	
	Reciever(Socket socket){
		try {
			if (socket.isClosed()) {
	            throw new Exception("Cannot open recieving socket");
	        }
			currentSocket = socket;
			in = new BufferedReader(new InputStreamReader(currentSocket.getInputStream()));
		}
		catch(Exception exep) {
			System.out.println(exep.getMessage());
			System.out.println(exep.getStackTrace());
		}
	}
	
	public void run() {
		try {
			/*Thread.sleep(10000);*/
			while(true) {
					String message;
					if((message = in.readLine()) != null) {
						System.out.println("recieve " + new Date() + " --->" + message);
					}
				
			}
		}
		catch(Exception exep) {
			System.out.println(exep.getMessage());
			System.out.println(exep.getStackTrace());
		}
	}
}

class Sender extends Thread {
	Socket currentSocket;
	InetAddress ipAddress;
	int port;
	String clientName;
	
	private static BufferedWriter out;
	
	Sender(Socket socket, String name){
		try{
			if (socket.isClosed()) {
	            throw new Exception("Cannot open sender socket");
	        }
			currentSocket = socket;
			this.ipAddress = socket.getInetAddress();
			port = socket.getPort();
			clientName = name;
			
			out = new BufferedWriter(new OutputStreamWriter(currentSocket.getOutputStream()));
		}
		catch(Exception excep) {
			System.out.println(excep.getMessage());
			System.out.println(excep.getStackTrace());
		}
	}
	
	public void run(){
		try {
			while(true) {	
					BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
					String message;
					if((message = buffer.readLine()) != null) {
						System.out.println("send " + new Date() + " --->" + message);
						String finalMessage = message;
						out.write(finalMessage);
					}
				
			}
		}
		catch(Exception excep) {
			System.out.println(excep.getMessage());
			System.out.println(excep.getStackTrace());
		}
	}
}