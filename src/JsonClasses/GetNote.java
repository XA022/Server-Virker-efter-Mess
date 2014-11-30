package JsonClasses;

public class GetNote implements java.io.Serializable {

	private static final long serialVersionUID = -3269894274812108796L;
	private String overallID = "GetNote"; 
	private String eventId;
	
	//Getters and setters
	public String getOverallID() {
		return overallID;
	}
	public void setOverallID(String overallID) {
		this.overallID = overallID;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

}