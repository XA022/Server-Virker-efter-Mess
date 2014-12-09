package service;

import java.sql.SQLException;

import model.calendar.CBSEvent;
import model.calendar.CBSEvents;
import model.calendar.Calendar;
import model.calendar.Event;
import model.calendar.Events;
import model.calendar.GetCalendarData;
import model.user.User;
import dao.DaoController;
import dao.SwitchMethods;

public class CalendarService {
	
	SwitchMethods switchMethods;
	
	public CalendarService() {
		switchMethods = new SwitchMethods();
	}
	
	public void fetchCBSCalendar(String userName) {
		CBSEvents events = new GetCalendarData().getDataFromCBSCalendar(userName);
		User user = DaoController.getInstance().getUserDAO().getUser(userName);
		Calendar calendar;
		try {
			calendar = switchMethods.getCalendar(userName+"_cbs_calendar");
		
		
		//The CBS calendar will be called <userid>_cbs_calendar
			if(calendar!=null) {
				switchMethods.deleteEvents(String.valueOf(calendar.getId()));
				for(CBSEvent event : events.getEvents()) {
					event.setUserId(String.valueOf(user.getUserId()));
					switchMethods.createEvent(String.valueOf(calendar.getId()),"0",event);
				}
			} else {
				switchMethods.addNewCalendar(userName+"_cbs_calendar", String.valueOf(user.getUserId()), 0);
				calendar = switchMethods.getCalendar(userName+"_cbs_calendar");
				for(CBSEvent event : events.getEvents()) {
					event.setUserId(String.valueOf(user.getUserId()));
					switchMethods.createEvent(String.valueOf(calendar.getId()),"0",event);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
