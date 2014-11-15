package dao;

public class DaoService {
	private static DaoService instance;
	private UserDAO userDAO;
	
	private DaoService() {
		this.userDAO = new UserDAO();
	}
	
	public static DaoService getInstance(){
		if(instance==null){
			instance = new DaoService();
		}
		return instance;
	}
	
	public UserDAO getUserDAO(){
		return userDAO;
	}
}
