package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.QOTD.QOTDModel;
import model.QueryBuild.QueryBuilder;
import model.user.User;
import model.util.DateHelper;

public class UserDAO extends ModelDAO {
	
	QueryBuilder qb = new QueryBuilder();
	QOTDModel qm = new QOTDModel();
	private static final String tableName = "users";
	private static final String userPK ="id";
	private static final String EQUALS_STRING ="=";
	
	public String addUser(User user) {
		getConn();
		boolean authenticate = false;
		String[] keys = {"email","active","createdDate","password"};
		
		try {
			qb.insertInto(tableName, keys).values(getFieldsFromUser(user)).Execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String updateUser(User user) {
		getConn();
		boolean authenticate = false;
		String[] fields = {"email","active","password"};
		String[] values = {user.getEmail(),String.valueOf(user.getActive()),user.getPassword()};
		try {
			qb.update(tableName, fields, values).where(userPK, EQUALS_STRING, String.valueOf(user.getUserId())).Execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	private String[] getFieldsFromUser(User user) {
		ArrayList<String> userFields = new ArrayList<String>();
		userFields.add(user.getEmail());
		userFields.add(String.valueOf(user.getActive()));
		userFields.add(DateHelper.getFormattedDateString(user.getDate()));
		userFields.add(user.getPassword());
		
		
		return userFields.toArray(new String[userFields.size()]);
		
	}
	
	public ArrayList<User> getAllUsers() {
        String[] columnNames = {"Id",
                "Email",
                "Active",
                "CreatedDate",
                "Password"};

        ResultSet rs;
        ArrayList<User> users = new ArrayList<User>();
		Object[][] data = {		
		};
        try {
			QueryBuilder qb = new QueryBuilder();
			rs = qb.selectFrom("users").all().ExecuteQuery();
			int initialCount = 0;
			while(rs.next()){
				initialCount++;
			}
			rs.beforeFirst();
			data = new Object[initialCount][5];
	        int count = 0;
	        while (rs.next()) {
	        	User user = new User();
	        	user.setUserId(Integer.valueOf(rs.getString("id")));
	        	user.setEmail(rs.getString("email"));
	        	user.setActive(Integer.valueOf(rs.getString("active")));
	        	user.setDate(DateHelper.getFormattedDateObject(rs.getString("createdDate")));
	        	user.setPassword(rs.getString("password"));
	        	users.add(user);

	        }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return users;
	}	
}
