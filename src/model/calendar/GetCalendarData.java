package model.calendar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;

/**
 * Created by jesperbruun on 13/10/14.
 */
public class GetCalendarData {
	
	private static final String USER_ID = "krri13ab";
	private static final String CALENDAR_URL = "http://calendar.cbs.dk/events.php/<USER_ID_PLACEHOLDER>/<ENCRYPTED_KEY_PLACEHOLDER>.json";
	private static final String USER_ID_PLACEHOLDER = "<USER_ID_PLACEHOLDER>";
	private static final String ENCRYPTED_KEY_PLACEHOLDER = "<ENCRYPTED_KEY_PLACEHOLDER>";
	EncryptUserID e = new EncryptUserID(USER_ID);

	//henter data fra URL og l??er ind til en string
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
    //Nu har vi alle data liggende i en string (JSON). 
    //Saa bruger vi Google's udviklede library Json string. den kan lave det om til java objekter
    //Events laver en arraylist af Event
    
    /**
     * Allows client to retrieve CBS's calendar and then access it.
     * @throws Exception
     */
    public void getDataFromCalendar() throws Exception {

        /**
         * Get URL From calendar.cbs.dk -> Subscribe -> change URL to end with .json
         * Encrypt hash from
         */
    	String userID = USER_ID;
    	String json = readUrl("http://calendar.cbs.dk/events.php/"+userID+"/"+e.getKey()+".json");
//    	String json = readUrl("http://calendar.cbs.dk/events.php/anha13ao/c69e9c9423e1154d9eea09b9a02a6a91.json");
        

        //Gson gson = new Gson();
        //Events events = gson.fromJson(json, Events.class); 
        
        System.out.println(json);
        //tester events activityID's
//        for (int i = 0; i < events.getEvents().size(); i++){
//            System.out.println(events.getEvents().get(i).getActivityid());
//        }
    }
    
    public CBSEvents getDataFromCBSCalendar(String userName) {
    	String[] userIdSplit = userName.split("@");
    	CBSEvents events = null;
    	if(userIdSplit!=null && userIdSplit.length>0){
    		EncryptUserID encryptUserId = new EncryptUserID(userIdSplit[0]);
    		try {
				String json = readUrl(CALENDAR_URL.replace(USER_ID_PLACEHOLDER, userIdSplit[0]).replace(ENCRYPTED_KEY_PLACEHOLDER, encryptUserId.getKey()));
				System.out.println("json: " + json);
				Gson gson = new Gson();
				events = gson.fromJson(json, CBSEvents.class);
				
    		} catch (Exception e) {
				e.printStackTrace();
			}
    		
    	}
    	return events;
    }
}
