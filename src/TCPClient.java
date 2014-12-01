import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import JsonClasses.*;

public class TCPClient {
	public static void main(String[] args) throws Exception {
		String modifiedSentence;
		Gson gson = new GsonBuilder().create();
//		CreateCalendar CC = new CreateCalendar();
//		CC.setCalendarName("kalender2");
//		CC.setPublicOrPrivate(1);
//		CC.setUserName("HA.it");
//		CC.setUserID(1);
		CreateEvent CE = new CreateEvent();
		CE.setType(1);
		CE.setLocation("CBS");
		CE.setCreatedby("Krri13ab");
		CE.setStarttime("18:00:00");
		CE.setEndtime("23:59:00");
		CE.setName("Julefrokost");
		CE.setText("datoen er den 5/12");
		CE.setActive(1);
		String gsonString = gson.toJson(CE);
		System.out.println(CE);
		System.out.println(gsonString);

		Socket clientSocket = new Socket("localhost", 8888);
		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());
		byte[] input = gsonString.getBytes();
		byte key = (byte) 3.1470;
		byte[] encrypted = input;
		for (int i = 0; i < encrypted.length; i++)
			encrypted[i] = (byte) (encrypted[i] ^ key);

		int length = encrypted.length;
		outToServer.writeInt(length);
		outToServer.write(encrypted);
		outToServer.flush();
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		modifiedSentence = inFromServer.readLine();
		System.out.println(modifiedSentence);
		System.out.println("FROM SERVER: " + modifiedSentence);
		clientSocket.close();
	}
}