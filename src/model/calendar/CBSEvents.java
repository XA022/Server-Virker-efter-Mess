package model.calendar;

import java.util.ArrayList;


/**
 * Created by jesperbruun on 10/10/14.
 * Den laver selve arrayet af alle generede Events
 */
public class CBSEvents {
    ArrayList<CBSEvent> events = new ArrayList<CBSEvent>();

    public ArrayList<CBSEvent> getEvents() {
    	return events;
    }

    public void setEvents(ArrayList<CBSEvent> event) {
        this.events = event;
    }
    
}