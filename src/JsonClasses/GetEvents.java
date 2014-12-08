package JsonClasses;

public class GetEvents implements java.io.Serializable {

	private static final long serialVersionUID = -3269894274812108796L;
	private String overallID = "getEvents"; 
	private String userID;
	
	//Getters and setters
	public String getOverallID() {
		return overallID;
	}
	public void setOverallID(String overallID) {
		this.overallID = overallID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID (String userID) {
		this.userID = userID;
	}
}