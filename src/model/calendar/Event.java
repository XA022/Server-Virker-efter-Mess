package model.calendar;

import java.util.ArrayList;

/**
 * Created by jesperbruun on 10/10/14.
 * Til hver specifik event bliver de defineret saaledes.
 */
public class Event {
    private String activityid;
    private String eventid;
    private String type;
    private String title;
    private String description;
    private String location;
    private String userId;
    private ArrayList<String> start;
    private ArrayList<String> end;
    
    public Event() {
    	
    }
    
    // Settere og gettere for Event objektet 
    public void setActivityid(String activityid){
        this.activityid = activityid;
    }
    public String getActivityid(){
        return activityid;
    }

    public void setEventid(String eventid){
        this.eventid = eventid;
    }
    public String getEventid(){
        return eventid;
    }

    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return type;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }

    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return location;
    }
    
	public void setStart(ArrayList<String> start){
        this.start = start;
    }
    public ArrayList<String> getStart(){
        return start;
    }

    public void setEnd(ArrayList<String> end){
        this.end = end;
    }
    public ArrayList<String> getEnd(){
        return end;
    }
    
	public Event(String activityid, String eventid, String type, String title,
			String description, String location, String userId, ArrayList<String> start,
			ArrayList<String> end) {
		super();
		this.activityid = activityid;
		this.eventid = eventid;
		this.type = type;
		this.title = title;
		this.description = description;
		this.location = location;
		this.setUserId(userId);
		this.start = start;
		this.end = end;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
    
    @Override
	public String toString() {
		return "Event [activityid=" + activityid + ", eventid=" + eventid
				+ ", type=" + type + ", title=" + title + ", description="
				+ description + ", location=" + location + ", userId=" + userId
				+ ", start=" + start + ", end=" + end + "]";
	}

}
