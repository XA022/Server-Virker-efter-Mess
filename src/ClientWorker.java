import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import sun.security.util.Length;


public class ClientWorker implements  Runnable{
	private Socket connectionSocketConected;
	private CalendarInfo CI = new CalendarInfo();
	private GiantSwitch GS = new GiantSwitch();
	private encryption cryp = new encryption();
	private String incomingJson;
	
	ClientWorker(Socket connectionSocket){
		this.connectionSocketConected = connectionSocket;
	}
	
	public void run(){
		try{
			//BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
//			byte[] b = new byte[500000];
//			int count = connectionSocketConected.getInputStream().read(b);
//			ByteArrayInputStream bais = new ByteArrayInputStream(b);
			DataInputStream inFromClient = new DataInputStream(connectionSocketConected.getInputStream());		
			//Creates an object of the data which is to be send back to the client, via the connectionSocket
			DataOutputStream outToClient = new DataOutputStream(connectionSocketConected.getOutputStream());
			int Length = inFromClient.readInt();
			//Sets client sentence equals input from client
//			incomingJson = inFromClient.readLine();			
//			
			byte[] b = new byte[Length];
//			Added Skovmand d. 17/11
			inFromClient.read(b);
			
			String ny = cryp.decrypt(b);
			
			//cryp.StringEncryption(inFromClient.readLine());
			//Sysout received message
			System.out.println("Received: " + ny);
			String returnSvar = GS.GiantSwitchMethod(ny);		
			//Sends the capitalized message back to client!!
			outToClient.writeBytes(returnSvar + "\n");
			System.out.println("Replied: " + returnSvar);
			//BufferedWriter writer = new BufferedWriter(arg0)
		}catch(Exception exception){
			System.err.print(exception);
		}
	}


}
