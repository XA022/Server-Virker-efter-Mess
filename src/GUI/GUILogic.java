package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.QueryBuild.QueryBuilder;
import dao.SwitchMethods;

public class GUILogic {
	private Screen screen;
	private String authReply;
	private boolean full = false;

	AuthUser a = new AuthUser();

	public GUILogic() {
		screen = new Screen();

		screen.getLogin().addActionListener(new LoginActionListener());
		screen.getMainMenu().addActionListener(new MainMenuActionListener());
		screen.getUserInfo().addActionListener(new UserInfoActionListener());
		screen.getCalendarList().addActionListener(new CalendarListActionListener());
		screen.getUserList().addActionListener(new UserListActionListener());
		screen.getEventlist().addActionListener(new EventListActionListener());
		screen.getAddEventGUI().addActionListener(
				new AddEventGUIActionListener());
		screen.getAddUser().addActionListener(new AddUserActionListener());

	}

	public void run() {
		System.out.println("In run");
		screen.show(Screen.LOGIN);
		screen.setVisible(true);
	}

	private class LoginActionListener implements ActionListener {
		private static final String LOGGED_IN = "0";

		public void actionPerformed(ActionEvent e) {
			try {

				String userName = screen.getLogin().getTextFieldUsername()
						.getText();
				String password = screen.getLogin().getTextFieldPassword()
						.getText();
				SwitchMethods switchMethods = new SwitchMethods();
				authReply = switchMethods.authenticate(userName, password);
				System.out.println("u: " + authReply);
				if (e.getSource() == screen.getLogin().getBtnLogIn()) {

					if (authReply.equals(LOGGED_IN)) {
//						new CalendarService().fetchCBSCalendar(userName);
						
						screen.show(Screen.MAINMENU);
					} else {
						JOptionPane.showMessageDialog(null,
								"\nPlease enter a valid username & password.",
								"Error message", JOptionPane.PLAIN_MESSAGE);
					}

				}
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		}
	}

	private class MainMenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == screen.getMainMenu().getBtnLogOut()) {
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getMainMenu().getBtnUserlist()) {
				screen.show(Screen.USERLIST);
			}
			if (e.getSource() == screen.getMainMenu().getBtnCalendarlist()) {
				screen.show(Screen.CALENDARLIST);
			}
			if (e.getSource() == screen.getMainMenu().getBtnEventlist()) {
				screen.show(Screen.EVENTLIST);
			}

		}
	}

	private class AddEventGUIActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == screen.getAddEventGUI().getBtnLogout()) {
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getAddEventGUI().getBtnMainMenu()) {
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getAddEventGUI().getBtnSubmit()) {
				String Type = screen.getAddEventGUI().getTextField_Type()
						.getText();
				String Location = screen.getAddEventGUI()
						.getTextField_Location().getText();
				String Createdby = screen.getAddEventGUI()
						.getTextField_Createdby().getText();
				String start = screen.getAddEventGUI().getTextField_Start()
						.getText();
				String end = screen.getAddEventGUI().getTextField_End()
						.getText();
				String name = screen.getAddEventGUI().getTextField_Name()
						.getText();
				String text = screen.getAddEventGUI().getTextField_Text()
						.getText();

				if (Type.equals("") || Location.equals("")
						|| Createdby.equals("") || start.equals("")
						|| end.equals("") || name.equals("") || text.equals("")) {
					JOptionPane.showMessageDialog(null,
							"\nPlease fill out all the fields",
							"Error message", JOptionPane.PLAIN_MESSAGE);
				} else {
					QueryBuilder qb = new QueryBuilder();

					String[] kolonner = { "eventid", "type", "location",
							"createdby", "start", "end", "name", "text" };
					String[] Values = { Type, Location, Createdby, start, end,
							name, text };
					try {
						qb.insertInto("events", kolonner).values(Values)
								.ExecuteQuery();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		}
	}

	private class AddUserActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == screen.getAddUser().getBtnLogout()) {
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getAddUser().getBtnMainMenu()) {
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getAddUser().getBtnSubmit()) {
				String Email = screen.getAddUser().getTextField_Email()
						.getText();
				String Type = screen.getAddUser().getTextField_Type().getText();
				String Password = screen.getAddUser().getTextField_Password()
						.getText();

				if (Email.equals("") || Type.equals("") || Password.equals("")) {
					JOptionPane.showMessageDialog(null,
							"\nPlease fill out all the fields",
							"Error message", JOptionPane.PLAIN_MESSAGE);
				} else {
					QueryBuilder qb = new QueryBuilder();

					String[] kolonner = { "email", "password" };
					String[] Values = { Email, Password };
					String[] kolonner2 = { "types" };
					String[] Values2 = { Type };
					try {
						qb.insertInto("users", kolonner).values(Values)
								.ExecuteQuery();
						qb.insertInto("roles", kolonner).values(Values)
								.ExecuteQuery();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		}
	}

	private class UserInfoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == screen.getUserInfo().getBtnMainMenu()) {
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getUserInfo().getBtnLogout()) {
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getUserInfo().getBtnSubmit()) {

			}
		}
	}

	private class CalendarListActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == screen.getCalendarList().getBtnMainMenu()) {
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getCalendarList().getBtnLogout()) {
				screen.show(Screen.LOGIN);
			}
		}
	}

	private class UserListActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == screen.getUserList().getBtnMainMenu()) {
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getUserList().getBtnLogout()) {
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getUserList().getBtnAdd()) {

			}
			if (e.getSource() == screen.getUserList().getBtnDeactivate()) {

			}

		}
	}

	private class EventListActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == screen.getEventlist().getBtnMainMenu()) {
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getEventlist().getBtnLogout()) {
				screen.show(Screen.LOGIN);
			}
		}
	}

}
