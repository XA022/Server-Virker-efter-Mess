package GUI;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.MatteBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import dao.DaoService;
import model.QueryBuild.QueryBuilder;
import model.user.User;
 
public class UserList extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final ActionListener ActionListener = null;
	private boolean DEBUG = false;
	private JButton btnAdd;
	private JButton btnDeactivate;
	private JButton btnLogout;
	private JButton btnMainMenu;
	private JButton btnActivate;
	private JTable table;
	private ResultSet rs;
	private static final String OBFUSCATED_PASSWORD = "********";
	private String[] columnNames = {"UserID",
             "Email",
             "Active",
             "Created date",
             "Password"};
	
    public UserList() {
       
    	setSize(new Dimension(1366, 768));
    	table = new JTable();
    	table.setModel(new DefaultTableModel());
        table = getUserTable();
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true);
        
        
        
 
        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }
        setLayout(null);
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255, 255), new Color(0, 0, 205), new Color(255, 255, 255)), new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 255))));
        scrollPane.setViewportBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 205), new Color(255, 255, 255), new Color(0, 0, 205), new Color(255, 255, 255)), null));

        scrollPane.setBounds(417, 225, 590, 360);

        scrollPane.setBounds(388, 225, 591, 361);

 
        //Add the scroll pane to this panel.
        add(scrollPane);
        
        btnAdd = new JButton("Add");
        btnAdd.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
        btnAdd.setForeground(new Color(0, 0, 205));
        btnAdd.setOpaque(true);
        
        
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
          
          
		          String eMail = JOptionPane.showInputDialog(null, "E-Mail", null);
		          String password = JOptionPane.showInputDialog(null, "Choose password", null);
		          User userToAdd = new User();
		          userToAdd.setActive(1);
		          userToAdd.setEmail(eMail);
		          userToAdd.setPassword(password);
		          userToAdd.setDate(new Date());
		          DaoService.getInstance().getUserDAO().addUser(userToAdd);
		          
		  		Object[][] data = {
		  				
				};
				ArrayList<User> users = DaoService.getInstance().getUserDAO().getAllUsers();
				User addedUser = users.get(users.size()-1);
				data = new Object[1][5];

					data[0][0] = addedUser.getUserId();
					data[0][1] = addedUser.getEmail();
					data[0][2] = addedUser.getActive();
					data[0][3] = addedUser.getDate();
					data[0][4] = OBFUSCATED_PASSWORD;
				
		          
		          
				DefaultTableModel model = new DefaultTableModel(getUserData(users), columnNames);
	            table.setModel(model);

			  table.repaint();
        	}
        });
        
        
        
        btnAdd.setBounds(1019, 556, 118, 29);
        add(btnAdd);
        
        btnLogout = new JButton("Log out");
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(new Font("Arial", Font.BOLD, 30));
        btnLogout.setContentAreaFilled(false);
        btnLogout.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
        btnLogout.setBounds(642, 688, 152, 44);
        add(btnLogout);
        
        btnMainMenu = new JButton("Main Menu");
        btnMainMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        btnMainMenu.setForeground(Color.WHITE);
        btnMainMenu.setFont(new Font("Arial", Font.BOLD, 30));
        btnMainMenu.setContentAreaFilled(false);
        btnMainMenu.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
        btnMainMenu.setBounds(622, 646, 194, 44);
        add(btnMainMenu);
        
        JLabel lblUserlist = new JLabel("Userlist");
        lblUserlist.setForeground(Color.WHITE);
        lblUserlist.setFont(new Font("Arial", Font.BOLD, 78));

        lblUserlist.setBounds(549, 118, 298, 90);

        lblUserlist.setBounds(534, 90, 298, 90);

        add(lblUserlist);
        
        btnDeactivate = new JButton("Deactivate");
        btnDeactivate.setOpaque(true);
        btnDeactivate.setForeground(new Color(0, 0, 205));
        btnDeactivate.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
        btnDeactivate.setBounds(1019, 515, 118, 29);
               
        btnDeactivate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		int deactivateRow = table.getSelectedRow();
                int numCols = table.getColumnCount();
                javax.swing.table.TableModel model = table.getModel();
                User userToDeactivate = new User();
                for (int j=0; j < numCols; j++) {
                	String columnName = model.getColumnName(j);
                	if(columnName=="UserID") {
                		userToDeactivate.setUserId((Integer) model.getValueAt(deactivateRow, j));
                	}
                	else if(columnName=="Email") {
                		userToDeactivate.setEmail(model.getValueAt(deactivateRow, j).toString());
                	}
                	else if(columnName=="Password") {
                		userToDeactivate.setPassword(model.getValueAt(deactivateRow, j).toString());
                	}
                }
                userToDeactivate.setActive(0);
                DaoService.getInstance().getUserDAO().updateUser(userToDeactivate);
                for (int j=0; j < numCols; j++) {
                	String columnName = model.getColumnName(j);
                	if(columnName=="Active") {
                		model.setValueAt(0, deactivateRow, j);
                	}
                }
//                table.tableChanged(new TableModelEvent(table.getModel()));
                table.repaint();
                }
        		
        	
        });
               
        
        add(btnDeactivate);
        
        
        btnActivate = new JButton("Activate");
        btnActivate.setOpaque(true);
        btnActivate.setForeground(new Color(0, 0, 205));
        btnActivate.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 255)));
        btnActivate.setBounds(1019, 600, 118, 29);
        
        btnActivate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		int activateRow = table.getSelectedRow();
                int numCols = table.getColumnCount();
                javax.swing.table.TableModel model = table.getModel();
                User userToActivate = new User();
                for (int j=0; j < numCols; j++) {
                	String columnName = model.getColumnName(j);
                	if(columnName=="UserID") {
                		userToActivate.setUserId((Integer) model.getValueAt(activateRow, j));
                	}
                	else if(columnName=="Email") {
                		userToActivate.setEmail(model.getValueAt(activateRow, j).toString());
                	}
                	else if(columnName=="Password") {
                		userToActivate.setPassword(model.getValueAt(activateRow, j).toString());
                	}
                }
                userToActivate.setActive(1);
                DaoService.getInstance().getUserDAO().updateUser(userToActivate);
                for (int j=0; j < numCols; j++) {
                	String columnName = model.getColumnName(j);
                	if(columnName=="Active") {
                		model.setValueAt(1, activateRow, j);
                	}
                }
                table.repaint();
                }
        		
        	
        });
        
        
        
        
        add(btnActivate);
        
        
        
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(UserList.class.getResource("/Images/CBSLogo3.png")));
        lblNewLabel.setBounds(36, 695, 223, 67);
        add(lblNewLabel);
    
        JLabel lblBackground = new JLabel("Background");
        lblBackground.setIcon(new ImageIcon(UserList.class.getResource("/Images/MetalBackground.jpg")));
        lblBackground.setBackground(new Color(245, 245, 245));
        lblBackground.setForeground(new Color(245, 255, 250));
        lblBackground.setOpaque(true);
        lblBackground.setBounds(0, 0, 1376, 768);
        add(lblBackground);
    }
 
    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();
 
        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("SimpleTableDemo");
        frame.setSize(1366, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        UserList newContentPane = new UserList();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 

        
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
     

        }
        });
    }
    
    public void addActionListener(ActionListener l) {
		btnAdd.addActionListener(l);
		btnDeactivate.addActionListener(l);
		btnLogout.addActionListener(l);
		btnMainMenu.addActionListener(l);
		btnActivate.addActionListener(l);
		
	}

	public static ActionListener getActionlistener() {
		return ActionListener;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnDeactivate() {
		return btnDeactivate;
	}

	public JButton getBtnMainMenu() {
		return btnMainMenu;
	}

	public JButton getBtnLogout() {
		return btnLogout;
	}
	
	private JTable getUserTable() {
		Object[][] data = {
		
		};
		ArrayList<User> users = DaoService.getInstance().getUserDAO().getAllUsers();
		
		data = new Object[users.size()][5];

		for(int i=0;i<users.size();i++) {
			User user = users.get(i);
			data[i][0] = user.getUserId();
			data[i][1] = user.getEmail();
			data[i][2] = user.getActive();
			data[i][3] = user.getDate();
			data[i][4] = OBFUSCATED_PASSWORD;
		}
		return new JTable(data, columnNames);
	}
	
	private Object[][] getUserData(ArrayList<User> users) {
		Object[][] data = {
				
		};
		
		data = new Object[users.size()][5];

		for(int i=0;i<users.size();i++) {
			User user = users.get(i);
			data[i][0] = user.getUserId();
			data[i][1] = user.getEmail();
			data[i][2] = user.getActive();
			data[i][3] = user.getDate();
			data[i][4] = OBFUSCATED_PASSWORD;
		}
		return data;
	}
}