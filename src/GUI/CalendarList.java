package GUI;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import model.calendar.Calendar;
import model.note.Note;
import model.note.NoteModel;
import model.user.User;
import dao.DaoController;
import dao.SwitchMethods;

public class CalendarList extends JPanel {
	private JTable table;
	private final JLabel lblBackground = new JLabel("");
	private JLabel lblHeader;
	private JButton btnDelete;
	private JButton btnAdd;
	private JButton btnMainMenu;
	private JButton btnLogout;
	private JLabel label;
	private SwitchMethods switchMethods;

	private String[] columnNames = { "ID","Name", "User", "Active"};

	/**
	 * Create the panel.
	 */
	public CalendarList() {
		switchMethods = new SwitchMethods();
		setSize(new Dimension(1366, 768));
		setLayout(null);
		
		//Laver tabellen inde i Eventlisten.



		table = new JTable();
    	table.setModel(new DefaultTableModel());
        table = getCalendarTable();

		table.setSurrendersFocusOnKeystroke(true);
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255,
						255), new Color(0, 0, 205), new Color(255, 255, 255)),
				new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 255))));
		scrollPane.setViewportBorder(new CompoundBorder(new BevelBorder(
				BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255,
						255), new Color(0, 0, 205), new Color(255, 255, 255)),
				null));
		scrollPane.setBounds(149, 171, 1062, 376);

		// Add the scroll pane to this panel.
		add(scrollPane);
		
		lblHeader = new JLabel("CalendarList");
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setFont(new Font("Arial", Font.BOLD, 40));
		lblHeader.setBounds(527, 90, 500, 90);
		add(lblHeader);
		
		btnDelete = new JButton("Delete");
		btnDelete.setOpaque(true);
		btnDelete.setForeground(new Color(0, 0, 205));
		btnDelete.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
		btnDelete.setBounds(1222, 227, 118, 29);
		
		btnDelete.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		int deleteRow = table.getSelectedRow();
	                int numCols = table.getColumnCount();
	                javax.swing.table.TableModel model = table.getModel();
	                boolean canDelete = true;
	                String calendarId = "";
	                for (int j=0; j < numCols; j++) {
	                	String columnName = model.getColumnName(j);
	                	if(columnName=="Name") {
	                		String calendarName = (String)model.getValueAt(deleteRow, j);
	                		canDelete = !calendarName.contains("_cbs_calendar");
	                	}
	                	if(columnName=="ID") {
	                		calendarId = "" + (int)model.getValueAt(deleteRow, j);
	                		
	                	}
	                }
	                if(canDelete) {
	                	switchMethods.deleteCalendar(calendarId);
	                }
	               
		          
		          
				DefaultTableModel reModel = new DefaultTableModel(getData(), columnNames);
	            table.setModel(reModel);
                table.repaint();
                }
	        		
	        	
	        });
		
		add(btnDelete);
		
		
		btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setForeground(Color.WHITE);
		btnMainMenu.setFont(new Font("Arial", Font.BOLD, 30));
		btnMainMenu.setContentAreaFilled(false);
		btnMainMenu.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnMainMenu.setBounds(601, 553, 163, 43);
		add(btnMainMenu);
		
		btnLogout = new JButton("Log out");
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Arial", Font.BOLD, 30));
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnLogout.setBounds(624, 627, 117, 43);
		add(btnLogout);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(CalendarList.class.getResource("/Images/CBSLogo3.png")));
		label.setBounds(10, 698, 250, 59);
		add(label);
		lblBackground.setIcon(new ImageIcon(CalendarList.class.getResource("/Images/MetalBackground.jpg")));
		lblBackground.setBounds(0, 0, 1366, 768);
		
		add(lblBackground);
	}
	
	public void addActionListener(ActionListener l) {
		btnDelete.addActionListener(l);
		btnLogout.addActionListener(l);
		btnMainMenu.addActionListener(l);
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnMainMenu() {
		return btnMainMenu;
	}

	public JButton getBtnLogout() {
		return btnLogout;
	}
	
	private JTable getCalendarTable() {
		return new JTable(getData(), columnNames);
	}
	
	private Object[][] getData() {
		Object[][] data = {
				
		};
		ArrayList<Calendar> calendars = new ArrayList<Calendar>();
		try {
			calendars = switchMethods.getAllCalendars();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		data = new Object[calendars.size()][4];

		for(int i=0;i<calendars.size();i++) {
			Calendar calendar = calendars.get(i);
			
			User user = DaoController.getInstance().getUserDAO().getUserFromId(calendar.getUserId());
			
			data[i][0] = calendar.getId();
			data[i][1] = calendar.getName();
			data[i][2] = user.getEmail();
			data[i][3] = calendar.getActive();
		}
		return data;
	}
	
	
}
