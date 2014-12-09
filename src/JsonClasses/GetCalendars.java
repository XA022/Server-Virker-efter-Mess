package JsonClasses;

public class GetCalendars  implements java.io.Serializable
{
	private static final long serialVersionUID = -3269894274812108796L;
	private String overallID = "getCalendars"; 
	private String UserName;
	
	//Getters and setters
	public String getOverallID() {
		return overallID;
	}
	public void setOverallID(String overallID) {
		this.overallID = overallID;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
}