package Gust;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import Login.Loginpage;
import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

public class GustPage {

	private JFrame frame;
	private JTable Ttable;
	private JTable Gtable;
	private JTable Ctable;
	private JTextField TourField;

	
	private Connection con;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/kfupm";
	private String uname = "root";
	private String password = "09Labas09--";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GustPage window = new GustPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public GustPage() throws ClassNotFoundException, SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	private void initialize() throws ClassNotFoundException, SQLException {
		
		
		frame = new JFrame();
		frame.setBounds(300, 300, 742, 546);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblGustPage = new JLabel("results of a tournament ");
		lblGustPage.setBounds(12, 13, 255, 57);
		lblGustPage.setForeground(Color.ORANGE);
		lblGustPage.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(lblGustPage);

		JLabel lblHighestGoalScore = new JLabel("highest goal score ");
		lblHighestGoalScore.setBounds(279, 13, 190, 57);
		lblHighestGoalScore.setForeground(Color.ORANGE);
		lblHighestGoalScore.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(lblHighestGoalScore);

		JLabel NoCardsPlayers = new JLabel("No Cards Players");
		NoCardsPlayers.setBounds(511, 13, 190, 57);
		NoCardsPlayers.setForeground(Color.ORANGE);
		NoCardsPlayers.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(NoCardsPlayers);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(424, 461, 97, 25);
		frame.getContentPane().add(btnExit);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setBounds(22, 83, 230, 345);
		frame.getContentPane().add(scrollPane);
		
		Ttable = new JTable();
		Ttable.setFillsViewportHeight(true);
		scrollPane.setViewportView(Ttable);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(279, 83, 190, 345);
		frame.getContentPane().add(scrollPane_1);
		
		Gtable = new JTable();
		Gtable.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(Gtable);

		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(511, 83, 190, 345);
		frame.getContentPane().add(scrollPane_1_1);
		
		Ctable = new JTable();
		Ctable.setFillsViewportHeight(true);
		scrollPane_1_1.setViewportView(Ctable);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 441, 700, 2);
		frame.getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 68, 700, 2);
		frame.getContentPane().add(separator_1);

		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Loginpage i = new Loginpage();
				Loginpage.main(null);
				frame.dispose();
			}
		});
		btnBack.setBounds(604, 461, 97, 25);
		frame.getContentPane().add(btnBack);
		
		TourField = new JTextField();
		TourField.setColumns(10);
		TourField.setBounds(86, 464, 96, 19);
		frame.getContentPane().add(TourField);
		
		JLabel TourTable = new JLabel("Tournament");
		TourTable.setBounds(12, 467, 64, 13);
		frame.getContentPane().add(TourTable);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url, uname, password);
					String query = "SELECT\r\n"
							+ "        match_played.goal_score,\r\n"
							+ "        match_played.play_date \r\n"
							+ "    FROM\r\n"
							+ "        match_played \r\n"
							+ "    WHERE\r\n"
							+ "        match_played.match_no IN (\r\n"
							+ "            SELECT\r\n"
							+ "                match_details.match_no \r\n"
							+ "            FROM\r\n"
							+ "                match_details \r\n"
							+ "            WHERE\r\n"
							+ "                match_details.team_id IN (\r\n"
							+ "                    SELECT\r\n"
							+ "                        kfupm_team.team_id \r\n"
							+ "                    FROM\r\n"
							+ "                        kfupm_team \r\n"
							+ "                    WHERE\r\n"
							+ "                        kfupm_team.tr_id = ?\r\n"
							+ "                )\r\n"
							+ "            ) ";
					PreparedStatement stat = con.prepareStatement(query);
					stat.setString(1, TourField.getText());
			
					ResultSet result = stat.executeQuery();

					Ttable.setModel(DbUtils.resultSetToTableModel(result));


					String query1 = "select   team_id, player_id from goal_details GROUP BY player_id;\r\n";
					PreparedStatement stat1 = con.prepareStatement(query1);

					ResultSet result1 = stat1.executeQuery();

					Gtable.setModel(DbUtils.resultSetToTableModel(result1));


					String query2 = "select a.player_name,a.team_id from player a where not exists ( select 1 from player_booked b where a.player_id = b.player_id);\r\n";
					PreparedStatement stat2 = con.prepareStatement(query2);

					ResultSet result2 = stat2.executeQuery();

					Ctable.setModel(DbUtils.resultSetToTableModel(result2));
					
					stat1.execute();
					stat.execute();
					con.close();
					stat1.close();
					stat.close();

				} catch (Exception ex) {
					ex.getStackTrace();
				}

				
			}
		});
		btnView.setBounds(249, 461, 97, 25);
		frame.getContentPane().add(btnView);
	}
}
