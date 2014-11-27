import java.sql.SQLException;

import model.QOTD.QOTDModel;
import model.calendar.Event;
import model.note.Note;
import JsonClasses.AuthUser;
import JsonClasses.CalendarInfo;
import JsonClasses.CreateCalendar
import JsonClasses.CreateEvent;
import JsonClasses.CreateNote;
import JsonClasses.DeleteCalendar;
import JsonClasses.DeleteEvent;
import JsonClasses.DeleteNote;
import JsonClasses.GetCalendar;
import JsonClasses.GetEvents;
import JsonClasses.GetNote;


import com.google.gson.*;

import dao.SwitchMethods;

public class GiantSwitch {
	
	
	
	public String GiantSwitchMethod(String jsonString) throws SQLException {

		//Events eventsKlasse = new Events(0, 0, 0, jsonString, jsonString, jsonString, jsonString, jsonString);

		Note noteKlasse = new Note();
		//ForecastModel forecastKlasse = new ForecastModel();
		QOTDModel QOTDKlasse = new QOTDModel();
		SwitchMethods SW = new SwitchMethods();
		
		Gson gson = new GsonBuilder().create();
		String answer = "";	
		//Creates a switch which determines which method should be used. Methods will be applied later on
		switch (Determine(jsonString)) {
		//If the Json String contains one of the keywords below, run the relevant method.

		/************
		 ** COURSES **
		 ************/

		case "importCalendar":
			System.out.println("Recieved importCourse");
			break;

		/**********
		 ** LOGIN **
		 **********/
		case "logIn":
			AuthUser AU = (AuthUser)gson.fromJson(jsonString, AuthUser.class);
			System.out.println("Recieved logIn");
			try {
				answer = SW.authenticate(AU.getAuthUserEmail(), AU.getAuthUserPassword(), AU.getAuthUserIsAdmin());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case "logOut": //OVERFLØDIG
			System.out.println("Recieved logOut");
			break;

		/*************
		 ** CALENDAR **
		 *************/
		case "createCalender":
			CreateCalendar CC = (CreateCalendar)gson.fromJson(jsonString, CreateCalendar.class);
			System.out.println(CC.getCalendarName()+ "Den har lagt det nye ind i klassen");
			answer = SW.createNewCalendar(CC.getUserName(), CC.getCalendarName(), CC.getPublicOrPrivate());
			break;
		
		case "deleteCalender":
			DeleteCalendar DC = (DeleteCalendar)gson.fromJson(jsonString, DeleteCalendar.class);
			System.out.println(DC.getCalendarName()+ "Den har lagt det nye ind i klassen");
			answer = SW.deleteCalendar(DC.getUserName(), DC.getCalendarName());
			break;
		
		case "saveImportedCalendar": // Unødig
			
			
			break;
			
		case "getCalendar":
			System.out.println("Recieved getCalendar");
			// 1 - OPRET JSON CLASS
			GetCalendar GC = (CreateCalendar)gson.fromJson(jsonString, CreateCalendar.class);
			// 2 - CONVERT FROM JSON STRING TO JAVA OBJECT
			answer = SW.getCalendar(GC.getName());
			// 3 - OPRETTE SWITCHMETHODS
			break;

			/*************
			 ** Events **
			 *************/

		case "getEvents":
			System.out.println("Recieved getEvents");
			GetEvents GE = (CreateCalendar)gson.fromJson(jsonString, CreateCalendar.class);
			answer = SW.GetEvents(GE.getCreatedby());
			break;

		case "createEvent":
			System.out.println("Recieved saveEvent");
			CreateEvents CE = (CreateCalendar)gson.fromJson(jsonString, CreateCalendar.class);
			answer = SW.CreateEvent(CE.getType(), CE.getLocation(), CE.getCreatedby(), 
					CE.getStarttime(), CE.getEndtime(), CE.getName(), CE.getText());
			break;

		case "deleteEvent":

			System.out.println("Recieved deleteEvent");
			DeleteEvent DE = (DeleteEvent).gsonfromJson(jsonString,DeleteEvent.class);
			answer = SW.DeleteEvent(DE.getName());
			break;
			
		case "createNote":
			System.out.println("Recieved CreateNote");
			CreateNote CN = (CreateNote)gson.fromJson(jsonString, CreateNote.class);
			answer = SW.CreateNote(CN.getEventID(), CN.getCreatedBy(), CN.getText(), CN.getDatetime());
			
		case "getNote":
			System.out.println("Recieved getNote");
			GetNote GN = (GetNote)gson.fromJson(jsonString, getNote.class);
			answer = SW.GetNote(GN.getEventId());
			break;
			
		case "deleteNote":
			System.out.println("Recieved deleteNote");
			break;

		/**********
		 ** QUOTE **
		 **********/
		case "getQuote":

		answer = QOTDKlasse.getQuote();
			System.out.println(answer);
			
			break;

		/************
		 ** WEATHER **
		 ************/

		case "getClientForecast":
			System.out.println("Recieved getClientForecast");
			break;
		
		default:
			System.out.println("Error");
			break;
		}
		return answer;
		
	}

	//Creates a long else if statement, which checks the JSon string which keyword it contains, and returns the following 
	//keyword if
	public String Determine(String ID) {

		if (ID.contains("getEvents")) {
			return "getEvents";
		} else if (ID.contains("getNote")) {
			return "getNote";
		} else if (ID.contains("deleteNote")){
			return "deleteNote";
		}else if  (ID.contains("deleteCalendar")){
			return "deleteCalendar";
		}else if   (ID.contains("createNote")) {
			return "createNote";
		} else if (ID.contains("getClientForecast")) {
			return "getClientForecast";
		} else if (ID.contains("saveImportedCalendar")) {
			return "saveImportedCalendar";
		}else if (ID.contains("importCourse")) {
			return "importCourse";
		} else if (ID.contains("exportCourse")) {
			return "exportCourse";
		} else if (ID.contains("getQuote")) {
			return "getQuote";
		} else if (ID.contains("logIn")) {
			return "logIn";
		} else if (ID.contains("logOut")) {
			return "logOut";
		} else if (ID.contains("getCalendar")) {
			return "getCalendar";
		} else if (ID.contains("createEvent")) {
			return "createEvent";
		} else if (ID.contains("deleteEvent")) {
			return "deleteEvent"; 
		} else if (ID.contains("createCalendar")) {
			return "createCalendar";
		}

		else
			return "error";
	}
	

}
