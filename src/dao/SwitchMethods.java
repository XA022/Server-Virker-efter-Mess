package dao;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import model.QOTD.QOTDModel;
import model.QueryBuild.QueryBuilder;
import model.calendar.CBSEvent;
import model.calendar.Calendar;
import model.calendar.Event;
import model.util.DateHelper;

public class SwitchMethods extends ModelDAO
{
	private static final String ACTIVE_BIT = "1";
	QueryBuilder qb = new QueryBuilder();
	QOTDModel qm = new QOTDModel();
	

	
	/**
	 * Allows the client to create a new calendar
	 * @param userName
	 * @param calendarName
	 * @param privatePublic
	 * @return
	 * @throws SQLException
	 */

	public boolean createNewCalendar (long userID, String calendarName, int isPrivate) throws SQLException
	{
		testConnection();
		if(authenticateNewCalendar(calendarName) == false)
		{
			System.out.println("der proeves ar oprette en ny kalender");
			addNewCalendar(calendarName, String.valueOf(userID), isPrivate);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean authenticateNewCalendar(String newCalendarName) throws SQLException
	{
		getConn();
		boolean authenticate = false;
		
		resultSet= qb.selectFrom("calendar").where("name", "=", newCalendarName).ExecuteQuery();
				
				//("select * from test.calender where Name = '"+newCalendarName+"';");
		while(resultSet.next())
		{
			authenticate = true;
		}
		return authenticate;
	}
	
	public void addNewCalendar (String newCalendarName, String userID, int isPrivate) throws SQLException
	{
		String [] keys = {"Name","active","userId","isPrivate"};
		String [] values = {newCalendarName, "1", userID, Integer.toString(isPrivate)};
		
		qb.insertInto("calendar", keys).values(values).Execute();
//		doUpdate("insert into test.calendar (Name, Active, CreatedBy, PrivatePublic) VALUES ('"+newCalenderName+"', '1', '"+userName+"', '"+publicOrPrivate+"');");
	}
	/**
	 * Allows the client to delete a calendar
	 * @param userName
	 * @param calenderName
	 * @return
	 */
	public String deleteCalendar (String userName, String calendarName) throws SQLException
	{
		String stringToBeReturned ="";
		testConnection();
		stringToBeReturned = removeCalendar(userName, calendarName);

		return stringToBeReturned;
	}
	
	public void deleteCalendar (String calendarId) {
		qb.deleteFrom("Calendar",false).where("id", "=", calendarId).Execute();
	}
	
	public String removeCalendar (String userName, String calendarName) throws SQLException
	{
		String stringToBeReturend = "";
		String usernameOfCreator ="";
		String calendarExists = "";
		resultSet = qb.selectFrom("Calendar").where("Name", "=", calendarName).ExecuteQuery();
				
//				("select * from calendar where Name = '"+calenderName+"';");
		while(resultSet.next())
		{
			calendarExists = resultSet.toString();
		}
		if(!calendarExists.equals(""))
		{
			String [] value = {"userID"};
			resultSet = qb.selectFrom(value, "Calendar").where("Name", "=", calendarName).ExecuteQuery();
			while(resultSet.next())
			{
				usernameOfCreator = resultSet.toString();
				System.out.println(usernameOfCreator);
			}
			if(!usernameOfCreator.equals(userName))
			{
				stringToBeReturend = "Only the creator of the calendar is able to delete it.";
			}
			else
			{
				String [] keys = {"Active"};
				String [] values = {"2"};
				qb.update("Calendar", keys, values).where("Name", "=", calendarName).Execute();
				stringToBeReturend = "Calendar has been set inactive";
			}
			stringToBeReturend = resultSet.toString();
		}
		else
		{
			stringToBeReturend = "The calendar you are trying to delete, does not exists.";
		}
		
		
		
		return stringToBeReturend;
	}
	
	public ArrayList<Calendar> getAllCalendars() throws SQLException
	{
		ArrayList<Calendar> calendars = new ArrayList<Calendar>();
	 	
		resultSet = qb.selectFrom("Calendar").all().ExecuteQuery();
		
		while(resultSet.next())
		{
			Calendar calendar = new Calendar();
			calendar.setId(resultSet.getInt("id"));
			calendar.setActive(resultSet.getInt("active"));
			calendar.setName(resultSet.getString("name"));
			calendar.setUserId(resultSet.getString("userId"));
			calendar.setIsPrivate(resultSet.getInt("isPrivate"));
			calendars.add(calendar);
			
		}
		return calendars;
	}
	
	public ArrayList<Calendar> getCalendars(String userId) throws SQLException
	{
		ArrayList<Calendar> calendars = new ArrayList<Calendar>();
	 	
		resultSet = qb.selectFrom("Calendar").where("userId", "=", userId).ExecuteQuery();
		
		while(resultSet.next())
		{
			Calendar calendar = new Calendar();
			calendar.setId(resultSet.getInt("id"));
			calendar.setActive(resultSet.getInt("active"));
			calendar.setName(resultSet.getString("name"));
			calendar.setUserId(resultSet.getString("userId"));
			calendar.setIsPrivate(resultSet.getInt("isPrivate"));
			calendars.add(calendar);
			
		}
		return calendars;
	}
	
	public Calendar getCalendar(String name) throws SQLException
	{
		Calendar calendar = null;
		resultSet = qb.selectFrom("Calendar").where("name", "=", name).ExecuteQuery();
		
		while(resultSet.next())
		{
			calendar = new Calendar();
			calendar.setId(resultSet.getInt("id"));
			calendar.setActive(resultSet.getInt("active"));
			calendar.setName(resultSet.getString("name"));
			calendar.setUserId(resultSet.getString("userId"));
			calendar.setIsPrivate(resultSet.getInt("isPrivate"));
			return calendar;
			
		}
		return calendar;
	}
	
	public Calendar getCalendar(int id) throws SQLException
	{
		Calendar calendar = null;
		resultSet = qb.selectFrom("Calendar").where("id", "=", String.valueOf(id)).ExecuteQuery();
		
		while(resultSet.next())
		{
			calendar = new Calendar();
			calendar.setId(resultSet.getInt("id"));
			calendar.setActive(resultSet.getInt("active"));
			calendar.setName(resultSet.getString("name"));
			calendar.setUserId(resultSet.getString("userId"));
			calendar.setIsPrivate(resultSet.getInt("isPrivate"));
			return calendar;
			
		}
		return calendar;
	}
	
	public String deleteEvent(String name) throws SQLException
	{
		String stringToBeReturned = "";
		String [] keys = {"active"};
		String [] values = {"0"};
		
		qb.update("events", keys, values).where("Name", "=", name).Execute();
		stringToBeReturned = "Event is now deleted";
		
		return stringToBeReturned;
	}
	
	public void deleteEvents(String calendarId) throws SQLException
	{		
		qb.deleteFrom("events",false).where("calendarId", "=", calendarId).Execute();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Event> GetEvents(String userId) throws SQLException
	{
		resultSet = qb.selectFrom("events").where("userId", "=", userId).ExecuteQuery();
		ArrayList<Event> events = new ArrayList<Event>();
		while(resultSet.next())
		{
			Event event = new Event();
			event.setTitle(resultSet.getString("title"));
			event.setDescription(resultSet.getString("description"));
			event.setLocation(resultSet.getString("location"));
			event.setUserId(resultSet.getString("userId"));
			
			event.setEventid(resultSet.getString("id"));
			event.setType(resultSet.getString("type"));

//			event.setStart((ArrayList<String>) resultSet.getArray("start"));
//			event.setEnd((ArrayList<String>) resultSet.getArray("end"));

			events.add(event);
		}
		return events;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Event> GetEvents() throws SQLException
	{
		resultSet = qb.selectFrom("events").all().ExecuteQuery();
		ArrayList<Event> events = new ArrayList<Event>();
		while(resultSet.next())
		{
			Event event = new Event();
			event.setTitle(resultSet.getString("title"));
			event.setDescription(resultSet.getString("description"));
			event.setLocation(resultSet.getString("location"));
			event.setUserId(resultSet.getString("userId"));
			
			event.setEventid(resultSet.getString("id"));
			event.setType(resultSet.getString("type"));
			event.setCalendarId(resultSet.getInt("calendarId"));
//			event.setStart((ArrayList<String>) resultSet.getArray("start"));
//			event.setEnd((ArrayList<String>) resultSet.getArray("end"));

			events.add(event);
		}
		
		return events;
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Event> GetEventsFromCalendarId(String calendarId) throws SQLException
	{
		resultSet = qb.selectFrom("events").where("calendarId", "=", calendarId).ExecuteQuery();
		ArrayList<Event> events = new ArrayList<Event>();
		while(resultSet.next())
		{
			Event event = new Event();
			event.setTitle(resultSet.getString("title"));
			event.setDescription(resultSet.getString("description"));
			event.setLocation(resultSet.getString("location"));
			event.setUserId(resultSet.getString("userId"));
			
			event.setEventid(resultSet.getString("id"));
			event.setType(resultSet.getString("type"));
			event.setCalendarId(resultSet.getInt("calendarId"));
			Timestamp start = resultSet.getTimestamp(("start"));
			Timestamp end = resultSet.getTimestamp(("end"));
			System.out.println("start: " + start);
			System.out.println("end: " + end);
			event.setStart(new Date(start.getTime()));
			event.setEnd(new Date(end.getTime()));

			events.add(event);
		}
		return events;
	}
	
	public void createEvent(String type, String location, String userId, String start, String end, String title, String description, String calendarId)
	{
		String [] keys = {"type", "location", "userId", "start", "end", "title", "description", "active","calendarID"};
		String [] values = {type, location, userId, start, end, title, description, ACTIVE_BIT, calendarId};

	    qb.insertInto("events", keys).values(values).Execute();

	}
		
	public String createEvent(String calendarId, String customEvent, Event event) throws SQLException
	{
		String stringToBeReturned ="";
		String [] keys = {"type", "location", "userId", "start", "end", "title", "description", "active","customevent","calendarId"};
		String startDate = DateHelper.getFormattedDateString(event.getStart());
		String endDate = DateHelper.getFormattedDateString(event.getEnd());
		String [] values = {
				event.getType(), 
				event.getLocation(), 
				event.getUserId(), 
				startDate, 
				endDate, 
				event.getTitle(), 
				event.getDescription(), 
				ACTIVE_BIT,
				customEvent,
				calendarId
				};
	
		qb.insertInto("events", keys).values(values).Execute();
		
		stringToBeReturned = "event oprettet";
		
		return stringToBeReturned;
	}

	public String createEvent(String calendarId, String customEvent, CBSEvent event) throws SQLException
	{
		String stringToBeReturned ="";
		String [] keys = {"type", "location", "userId", "start", "end", "title", "description", "active","customevent","calendarId"};
		String startDate = DateHelper.getFormattedDateStringFromCBSEvent(event.getStart());
		String endDate = DateHelper.getFormattedDateStringFromCBSEvent(event.getEnd());
		String [] values = {
				event.getType(), 
				event.getLocation(), 
				event.getUserId(), 
				startDate, 
				endDate, 
				event.getTitle(), 
				event.getDescription(), 
				ACTIVE_BIT,
				customEvent,
				calendarId
				};
	
		qb.insertInto("events", keys).values(values).Execute();
		
		stringToBeReturned = "event oprettet";
		
		return stringToBeReturned;
	}
	
	
	public String CreateNote (int eventID, String createdby, String text, String dateTime) throws SQLException
	{
		String stringToBeReturned ="";
		String [] keys = {"eventID", "createdby", "text", "dateTime", "active"};
		String [] values = {String.valueOf(eventID), createdby, text, dateTime, "1"};
		
		qb.insertInto("notes", keys).values(values).Execute();
		
		stringToBeReturned = "note oprettet";
		
		return stringToBeReturned;

	}
	
	public String GetNote(String eventId) throws SQLException
	{
		String stringToBeReturned ="";
		
		resultSet = qb.selectFrom("notes").where("eventId", "=", eventId).ExecuteQuery();
		
		while(resultSet.next())
		{
			
			stringToBeReturned += resultSet.toString();
		}
		return stringToBeReturned;
	}
	
	public String DeleteNote(String eventId) throws SQLException
	{
		
		String stringToBeReturned = "";
		String [] keys = {"active"};
		String [] values = {"0"};
		
		qb.update("notes", keys, values).where("eventId", "=", eventId).Execute();
		stringToBeReturned = "Note is now deleted";
		
		return stringToBeReturned;
	}
	
	// Metoden faar email og password fra switchen (udtrukket fra en json) samt en boolean der skal saettes til true hvis det er serveren der logger paa, og false hvis det er en klient
	/**
	 * Allows the client to log in
	 * @param email
	 * @param password
	 * @param isAdmin
	 * @return
	 * @throws Exception
	 */
	public String authenticate(String email, String password) throws SQLException {

		String[] keys = {"id", "email", "active", "password"};

		qb = new QueryBuilder();

		// Henter info om bruger fra database via querybuilder
		resultSet = qb.selectFrom(keys, "users").where("email", "=", email).ExecuteQuery();

		// Hvis en bruger med forespurgt email findes
		if (resultSet.next()){

			// Hvis brugeren er aktiv
			if(resultSet.getInt("active")==1)
			{					
				// Hvis passwords matcher
				if(resultSet.getString("password").equals(password))
				{
//					int userID = resultSet.getInt("userid");
//
//					String[] key = {"type"};

//					resultSet = qb.selectFrom(key, "roles").where("userid", "=", new Integer(userID).toString()).ExecuteQuery();

					// Hvis brugeren baade logger ind og er registreret som admin, eller hvis brugeren baade logger ind og er registreret som bruger
//					if((resultSet.getString("type").equals("admin")) || (resultSet.getString("type").equals("user") && !isAdmin))
//					{
						return "0"; // returnerer "0" hvis bruger/admin er godkendt
//					} else {
//						return "4"; // returnerer fejlkoden "4" hvis brugertype ikke stemmer overens med loginplatform
//					}
				} else {
					return "3"; // returnerer fejlkoden "3" hvis password ikke matcher
				}
			} else {
				return "2"; // returnerer fejlkoden "2" hvis bruger er sat som inaktiv
			}
		} else {
			return "1"; // returnerer fejlkoden "1" hvis email ikke findes
		}
	}
	
	
}
