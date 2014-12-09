package GUI;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;

public class MainMenu extends JPanel {
	private JLabel label;
	private JLabel lblMainMenu;
	private JButton btnLogOut;
	private JButton btnUserlist;
	private JButton btnEventlist;
	private JButton btnCalendarlist;
	private JLabel lblCBSlogo;
	

	
	public MainMenu() {
		setSize(new Dimension(1366, 768));
		setLayout(null);
		
		lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setForeground(Color.WHITE);
		lblMainMenu.setFont(new Font("Arial", Font.BOLD, 78));
		lblMainMenu.setBounds(481, 90, 404, 90);
		add(lblMainMenu);
		

		btnUserlist = new JButton("User list");
		btnUserlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUserlist.setForeground(Color.WHITE);
		btnUserlist.setFont(new Font("Arial", Font.BOLD, 20));
		btnUserlist.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
//		btnUserlist.setBackground(Color.WHITE);
		btnUserlist.setBounds(610, 330, 145, 50);
		add(btnUserlist);
		
		btnEventlist = new JButton("Event list");
		btnEventlist.setContentAreaFilled(false);
		btnEventlist.setForeground(Color.WHITE);
		btnEventlist.setFont(new Font("Arial", Font.BOLD, 20));
		btnEventlist.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnEventlist.setBackground(Color.WHITE);
		btnEventlist.setBounds(610, 422, 145, 50);
		add(btnEventlist);
		
		btnCalendarlist = new JButton("Calendar list");
		btnCalendarlist.setContentAreaFilled(false);
		btnCalendarlist.setForeground(Color.WHITE);
		btnCalendarlist.setFont(new Font("Arial", Font.BOLD, 20));
		btnCalendarlist.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnCalendarlist.setBackground(Color.WHITE);
		btnCalendarlist.setBounds(610, 243, 145, 50);
		add(btnCalendarlist);
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.setContentAreaFilled(false);
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setFont(new Font("Arial", Font.BOLD, 20));
		btnLogOut.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0)), new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(0, 0, 0), new Color(255, 255, 255), new Color(0, 0, 0))));
		btnLogOut.setBackground(Color.WHITE);
		btnLogOut.setBounds(610, 541, 145, 50);
		add(btnLogOut);
		
		lblCBSlogo = new JLabel("");
		lblCBSlogo.setIcon(new ImageIcon(MainMenu.class.getResource("/Images/CBSLogo3.png")));
		lblCBSlogo.setBounds(10, 698, 250, 59);
		add(lblCBSlogo);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(MainMenu.class.getResource("/Images/MetalBackground.jpg")));
		lblBackground.setBounds(0, 0, 1366, 768);
		add(lblBackground);

	}
	public void addActionListener(ActionListener l) {
		btnLogOut.addActionListener(l);
		btnEventlist.addActionListener(l);
		btnCalendarlist.addActionListener(l);
		btnUserlist.addActionListener(l);
		
		
	}
	public JButton getBtnUserlist() {
		return btnUserlist;
	}
	public JButton getBtnEventlist() {
		return btnEventlist;
	}
	public JButton getBtnCalendarlist() {
		return btnCalendarlist;
	}
	public JButton getBtnLogOut() {
		return btnLogOut;
	}
	
}
