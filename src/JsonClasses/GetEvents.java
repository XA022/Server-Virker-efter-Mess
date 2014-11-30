package JsonClasses;

public class GetEvents implements java.io.Serializable {

	private static final long serialVersionUID = -3269894274812108796L;
	private String overallID = "getEvents"; 
	private String createdby;
	
	//Getters and setters
	public String getOverallID() {
		return overallID;
	}
	public void setOverallID(String overallID) {
		this.overallID = overallID;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby (String createdby) {
		this.createdby = createdby;
	}
}