package Admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JSeparator;

import AddTeam.addteam;
import AddTournament.addtournament;
import ApprovePlayer.Approve;
import Captain.cap;
import Login.Loginpage;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AdminPage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage window = new AdminPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 300, 742, 546);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblWelcomeToAdmin = new JLabel("Welcome To Admin Page");
		lblWelcomeToAdmin.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWelcomeToAdmin.setBounds(253, 13, 295, 28);
		frame.getContentPane().add(lblWelcomeToAdmin);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 57, 700, 14);
		frame.getContentPane().add(separator);

		JLabel lblNewLabel = new JLabel("\u2022\tAdd a tournament");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(22, 84, 152, 46);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblSelectA = new JLabel("\u2022\tSelect a captain ");
		lblSelectA.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSelectA.setBounds(379, 84, 152, 46);
		frame.getContentPane().add(lblSelectA);

		JLabel lblApproveA = new JLabel("\u2022\tApprove a player");
		lblApproveA.setFont(new Font("Dialog", Font.BOLD, 12));
		lblApproveA.setBounds(560, 84, 152, 46);
		frame.getContentPane().add(lblApproveA);

		JLabel lblAddA = new JLabel("\u2022\tAdd a team ");
		lblAddA.setFont(new Font("Dialog", Font.BOLD, 12));
		lblAddA.setBounds(204, 84, 152, 46);
		frame.getContentPane().add(lblAddA);

		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
					addtournament v = new addtournament();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				addtournament.main(null);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(37, 171, 97, 25);
		frame.getContentPane().add(btnNewButton);

		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
					addteam a = new addteam();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				addteam.main(null);
			}
		});
		btnAdd.setBounds(204, 171, 97, 25);
		frame.getContentPane().add(btnAdd);

		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
					cap c = new cap();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cap.main(null);
			}
		});
		btnSelect.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSelect.setBounds(379, 171, 97, 25);
		frame.getContentPane().add(btnSelect);

		JButton btnApprove = new JButton("Approve");
		btnApprove.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnApprove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Approve b = new Approve();
				Approve.main(null);
			}
		});
		btnApprove.setBounds(560, 171, 97, 25);
		frame.getContentPane().add(btnApprove);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 248, 700, 2);
		frame.getContentPane().add(separator_1);

		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});
		btnNewButton_1.setBounds(292, 313, 127, 46);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Loginpage i = new Loginpage();
				Loginpage.main(null);
			}
		});
		btnNewButton_2.setBounds(292, 391, 127, 46);
		frame.getContentPane().add(btnNewButton_2);
	}
}
