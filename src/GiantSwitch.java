import java.sql.SQLException;
import java.util.ArrayList;

import service.CalendarService;
import model.Forecast.ForecastModel;
import model.Forecast.Forecasts;
import model.QOTD.QOTDModel;
import model.calendar.Calendar;
import model.calendar.Calendars;
import model.calendar.Event;
import model.calendar.Events;
import model.note.Note;
import model.user.User;
import model.util.DateHelper;
import JsonClasses.AuthUser;
import JsonClasses.CreateCalendar;
import JsonClasses.CreateEvent;
import JsonClasses.CreateNote;
import JsonClasses.DeleteCalendar;
import JsonClasses.DeleteEvent;
import JsonClasses.DeleteNote;
import JsonClasses.GetCalendar;
import JsonClasses.GetCalendars;
import JsonClasses.GetEvents;
import JsonClasses.GetNote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.DaoController;
import dao.SwitchMethods;

public class GiantSwitch {
	
	
	User user;
	public String GiantSwitchMethod(String jsonString) throws SQLException {

		//Events eventsKlasse = new Events(0, 0, 0, jsonString, jsonString, jsonString, jsonString, jsonString);


		//ForecastModel forecastKlasse = new ForecastModel();
		QOTDModel qotd = new QOTDModel();
		qotd.saveQuote();
		SwitchMethods switchMethods = new SwitchMethods();
		ForecastModel forecast = new ForecastModel();
		Gson gson = new GsonBuilder().create();
		String answer = "";	
		System.out.println("jsonString: " + jsonString);
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
				answer = switchMethods.authenticate(AU.getAuthUserEmail(), AU.getAuthUserPassword());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case "logOut": //OVERFLOEDIG
			System.out.println("Recieved logOut");
			break;

		/*************
		 ** CALENDAR **
		 *************/
		case "createCalendar":
			CreateCalendar CC = (CreateCalendar)gson.fromJson(jsonString, CreateCalendar.class);
		    user = DaoController.getInstance().getUserDAO().getUser(CC.getUserName());
			answer = String.valueOf(switchMethods.createNewCalendar(user.getUserId(), CC.getCalendarName(), CC.getPublicOrPrivate()));
			break;
		
		case "deleteCalender":
			DeleteCalendar DC = (DeleteCalendar)gson.fromJson(jsonString, DeleteCalendar.class);
			System.out.println(DC.getCalendarName()+ "Den har lagt det nye ind i klassen");
			answer = switchMethods.deleteCalendar(DC.getUserName(), DC.getCalendarName());
			break;
		
		case "saveImportedCalendar": // Unoedig
			break;
			
		case "getCalendar":
			GetCalendar getCalendar = (GetCalendar)gson.fromJson(jsonString, GetCalendar.class);
			user = DaoController.getInstance().getUserDAO().getUser(getCalendar.getUserName());
			ArrayList<Event> events = switchMethods.GetEvents(String.valueOf(user.getUserId()));
			Events eventsObject = new Events();
			eventsObject.setEvents(events);
			answer = gson.toJson(eventsObject);
			break;
		case "getCalendars":
			GetCalendars getCalendars = (GetCalendars)gson.fromJson(jsonString, GetCalendars.class);
			user = DaoController.getInstance().getUserDAO().getUser(getCalendars.getUserName());
			//Add or update CBS calendar for the user to get
			new CalendarService().fetchCBSCalendar(getCalendars.getUserName());
			ArrayList<Calendar> calendarList = switchMethods.getCalendars(String.valueOf(user.getUserId()));
			Calendars calendars = new Calendars();
			calendars.setCalendars(calendarList);
			answer = gson.toJson(calendars);
			break;

			/*************
			 ** Events **
			 *************/

		case "getEvents":
			System.out.println("getting events");
			GetEvents GE = (GetEvents)gson.fromJson(jsonString, GetEvents.class);
			System.out.println("getting events for calendar " + GE.getCalendarId());
			events = switchMethods.GetEventsFromCalendarId(String.valueOf(GE.getCalendarId()));
			eventsObject = new Events();
			eventsObject.setEvents(events);
			answer = gson.toJson(eventsObject);
			break;

		case "createEvent":
			CreateEvent CE = (CreateEvent)gson.fromJson(jsonString, CreateEvent.class);
			user = DaoController.getInstance().getUserDAO().getUser( CE.getUserName());
			switchMethods.createEvent(
					CE.getType(), 
					CE.getLocation(), 
					String.valueOf(user.getUserId()), 
					DateHelper.getFormattedDateString(CE.getStarttime()), 
					DateHelper.getFormattedDateString(CE.getEndtime()), 
					CE.getTitle(), 
					CE.getDescription(),
					String.valueOf(CE.getCalendarId())
					);
			answer = "Event " +CE.getTitle()+ " created";
			break;

		case "deleteEvent":

			System.out.println("Recieved deleteEvent");
			DeleteEvent DE = (DeleteEvent)gson.fromJson(jsonString, DeleteEvent.class);
			answer = switchMethods.deleteEvent(DE.getName());
			break;
			
		case "createNote":
			System.out.println("Recieved CreateNote");
			CreateNote CN = (CreateNote)gson.fromJson(jsonString, CreateNote.class);
			answer = switchMethods.CreateNote(CN.getEventID(), CN.getUserID(), CN.getText(), CN.getDatetime());
			
		case "getNote":
			System.out.println("Recieved getNote");
			GetNote GN = (GetNote)gson.fromJson(jsonString, GetNote.class);
			answer = switchMethods.GetNote(GN.getEventId());
			break;
			
		case "deleteNote":
			System.out.println("Recieved deleteNote");
			DeleteNote DN = (DeleteNote)gson.fromJson(jsonString, DeleteNote.class);
			answer = switchMethods.DeleteNote(DN.getEventId());
			break;

		/**********
		 ** QUOTE **
		 **********/
		case "getQuote":
			System.out.println("in getQuote");
		answer = qotd.getQuote();
			System.out.println(answer);
			
			break;

		/************
		 ** WEATHER **
		 ************/

		case "getWeatherForecast":
			Forecasts forecasts = new Forecasts();
			forecasts.setForecasts(forecast.getForecast());
			answer = gson.toJson(forecasts);
			break;
		
		default:
			System.out.println("Message not understood.");
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
		} else if (ID.contains("getWeatherForecast")) {
			return "getWeatherForecast";
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
		} else if (ID.contains("getCalendars")) {
			return "getCalendars";
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
