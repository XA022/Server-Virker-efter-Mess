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
		String[] keys = {"email","active","createdDate","password"};
		
		qb.insertInto(tableName, keys).values(getFieldsFromUser(user)).Execute();

		return null;
		
	}
	
	public String updateUserActiveStatus(User user) {
		getConn();
		String[] fields = {"active"};
		String[] values = {String.valueOf(user.getActive())};
		qb.update(tableName, fields, values).where(userPK, EQUALS_STRING, String.valueOf(user.getUserId())).Execute();
	
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
        ResultSet rs;
        ArrayList<User> users = new ArrayList<User>();

        try {

			QueryBuilder qb = new QueryBuilder();
			rs = qb.selectFrom("users").all().ExecuteQuery();

			rs.beforeFirst();

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

	public User getUserFromId(String userId) {
        ResultSet rs;
        User user = null;
        try {

			QueryBuilder qb = new QueryBuilder();
			rs = qb.selectFrom("users").where("id", "=", userId).ExecuteQuery();

			rs.beforeFirst();

	        while (rs.next()) {
	        	user = new User();
	        	user.setUserId(Integer.valueOf(rs.getString("id")));
	        	user.setEmail(rs.getString("email"));
	        	user.setActive(Integer.valueOf(rs.getString("active")));
	        	user.setDate(DateHelper.getFormattedDateObject(rs.getString("createdDate")));
	        	user.setPassword(rs.getString("password"));
	        	return user;

	        }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return user;
	}		
	
	public User getUser(String userName) {
        ResultSet rs;
        User user = null;
        try {

			QueryBuilder qb = new QueryBuilder();
			rs = qb.selectFrom("users").where("email", "=", userName).ExecuteQuery();

			rs.beforeFirst();

	        while (rs.next()) {
	        	user = new User();
	        	user.setUserId(Integer.valueOf(rs.getString("id")));
	        	user.setEmail(rs.getString("email"));
	        	user.setActive(Integer.valueOf(rs.getString("active")));
	        	user.setDate(DateHelper.getFormattedDateObject(rs.getString("createdDate")));
	        	user.setPassword(rs.getString("password"));
	        	return user;

	        }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return user;
	}	
}
