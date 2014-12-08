package dao;

public class DaoController {
	private static DaoController instance;
	private UserDAO userDAO;
	
	private DaoController() {
		this.userDAO = new UserDAO();
	}
	
	public static DaoController getInstance(){
		if(instance==null){
			instance = new DaoController();
		}
		return instance;
	}
	
	public UserDAO getUserDAO(){
		return userDAO;
	}
}
