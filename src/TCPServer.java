import java.io.*;
import java.net.*;
import JsonClasses.CalendarInfo;

import com.google.gson.stream.JsonReader;

class TCPServer{    
	
	public static void main(String argv[]) throws Exception       {

		AdminWorker admin = new AdminWorker();
		System.out.println("admin: " + admin);
		//Creates a socket to send and recieve messages in port 8888
		ServerSocket welcomeSocket = new ServerSocket(8888);
		System.out.println("welcomeSocket: " + welcomeSocket);
		//While something is true
		while(true){
			System.out.println("spam: ");
			//Creates a socket and a buffered reader which receives some sort of input from somewhere around the internet!
			Socket connectionSocket = welcomeSocket.accept();
			System.out.println("connectionSocket: " + connectionSocket);
			ClientWorker client= new ClientWorker(connectionSocket);
			System.out.println("client: " + client);
			Thread thread = new Thread(client, "client");
			System.out.println("client: " + client);
			thread.start();
		}
	}
}