package tcp_chat;

import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] argv) {
		try {
			/**
			 * 1. Ввести адрес и порт сервера (v)
			 * 2. Создать сокет для этого сервера и настроить потоки (v)
			 * 3. Цикл работы с сервером с помощью протокола MyKnockKnockProtocol (x)
			 * 4. Работа с принятым сообщением с помощью протокола MyKnockKnockProtocol (x)
			 * **/
			
			InputStream inputStream = System.in;
			Reader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			System.out.print("server port ---> ");
			int serverPort = Integer.parseInt(bufferedReader.readLine());
			
			System.out.print("server ip address ---> ");
			InetAddress serverIpAddress = InetAddress.getByName(bufferedReader.readLine());
			
			System.out.print("local port ---> ");
			int localPort = Integer.parseInt(bufferedReader.readLine());
			
			System.out.print("local ip address ---> ");
			InetAddress localIpAddress = InetAddress.getByName(bufferedReader.readLine());
			
			Socket kksocket = new Socket(serverIpAddress, serverPort, localIpAddress, localPort);
			
			if(kksocket.isConnected()) {
				System.out.println("connected");
			}
			else {
				System.out.println("not connected");
			}
			
			PrintWriter out = new PrintWriter(kksocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(kksocket.getInputStream()));
			
			
			// for chat tests in one device
			class InputThread extends Thread{
				public void run() {
					try {
						while(true) {
							out.println("hi, bro");
							Thread.sleep(1000);
						}
					}
					catch(Exception exception) {
						System.out.print(exception.getMessage());
					}
				}
			}
			
			// for chat tests in one device
			class OutputThread extends Thread{

				
				public void run() {
					try {
						String message = new String();
						while((message = in.readLine()) != null) {
							System.out.println(message);
						}
						
						
					}
					catch(Exception exception) {
						System.out.print(exception.getMessage());
					}
				}
			}
			
			char[] buffer = new char[1024];
			Thread outputThread = new OutputThread();
			Thread inputThread = new InputThread();
			
			outputThread.start();
			inputThread.start();
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
}
