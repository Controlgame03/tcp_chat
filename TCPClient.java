package TCPChat;

import java.net.*;
import java.io.*;
import java.util.Date;
import java.util.Scanner;

/*
 * 4.07 клиент отправляет пакеты, но не получпет их
 * 
 * 11.07 написать программу где клиент посылает сообщения а сервер их получает
 * нужно сделать логи программы
 * */


public class TCPClient {
	public static void main(String[] argv) {
		try {
			Socket clientSocket;
			String name = "denis";
			/*
			InetAddress localHost = InetAddress.getByName(argv[0]);
			InetAddress serverHost = InetAddress.getByName(argv[2]);
			int localPort = Integer.parseInt(argv[1]);
			int serverPort = Integer.parseInt(argv[3]);
			*/
			Scanner in = new Scanner(System.in);
			
			System.out.print("Введите ip адрес сервера: ");
			InetAddress serverHost = InetAddress.getByName(in.next());
			
			System.out.print("Введите порт на котором работает сервер: ");
			int serverPort = Integer.parseInt(in.next());
			
			clientSocket = new Socket(serverHost, serverPort);
			
			System.out.println("\nУспешное соединение");
			
			Sender sender = new Sender(clientSocket, name);
			sender.start();
		}
		catch(Exception exep) {
			System.out.println(exep.getMessage());
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
				//String message = in.readLine();
				//System.out.print(message);
				InputStreamReader myin = new InputStreamReader()
				/*if((message = in.readLine()) != null) {
					System.out.println("Получено сообщение " + new Date() + " --->" + message);
				}*/
				
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
	String clientName;
	
	private static BufferedWriter out;
	
	Sender(Socket socket, String name){
		try{
			if (socket.isClosed()) {
	            throw new Exception("Cannot open sender socket");
	        }
			currentSocket = socket;
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
				System.out.print("Введите сообщение: ");
				if((message = buffer.readLine()) != null) {
					message += "\n";
					System.out.println("Отправлено сообщение " + new Date() + " ---> " + message);
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