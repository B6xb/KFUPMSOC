package Captain;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Admin.AdminPage;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class cap {

	private JFrame frame;
	private JTextField CapText;
	private JTextField MatchText;
	private JTextField TeamText;

	private Connection con;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/kfupm";
	private String user = "root";
	private String pass = "09Labas09--";
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cap window = new cap();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public cap() throws ClassNotFoundException, SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void initialize() throws ClassNotFoundException, SQLException {

		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);

		frame = new JFrame();
		frame.setBounds(300, 300, 742, 546);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblCaptainPage = new JLabel("Captain page");
		lblCaptainPage.setBounds(310, 13, 106, 16);
		frame.getContentPane().add(lblCaptainPage);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AdminPage j = new AdminPage();
				AdminPage.main(null);
			}
		});
		btnBack.setBounds(158, 457, 97, 25);
		frame.getContentPane().add(btnBack);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(385, 457, 97, 25);
		frame.getContentPane().add(btnExit);

		JLabel lblNewLabel = new JLabel("Captin");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(46, 106, 67, 25);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblMatch = new JLabel("Match");
		lblMatch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMatch.setBounds(46, 142, 67, 25);
		frame.getContentPane().add(lblMatch);

		CapText = new JTextField();
		CapText.setBounds(123, 110, 67, 19);
		frame.getContentPane().add(CapText);
		CapText.setColumns(10);

		MatchText = new JTextField();
		MatchText.setColumns(10);
		MatchText.setBounds(123, 146, 67, 19);
		frame.getContentPane().add(MatchText);

		JLabel lblTeam = new JLabel("Team");
		lblTeam.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTeam.setBounds(46, 177, 67, 25);
		frame.getContentPane().add(lblTeam);

		TeamText = new JTextField();
		TeamText.setColumns(10);
		TeamText.setBounds(123, 181, 67, 19);
		frame.getContentPane().add(TeamText);

		JButton AddCap = new JButton("ADD");
		AddCap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String query = "insert into match_captain(match_no,team_id,player_captain) values (?,?,?)";
					PreparedStatement stat = con.prepareStatement(query);

					stat.setString(1, MatchText.getText());
					stat.setString(2, TeamText.getText());
					stat.setString(3, CapText.getText());

					MatchText.setText(null);
					TeamText.setText(null);
					CapText.setText(null);

					JOptionPane.showMessageDialog(null, "Captain Selected ");
					stat.execute();

					con.close();

					stat.close();
				} catch (Exception ex) {
					ex.getStackTrace();
				}
			}

		});
		AddCap.setBounds(276, 398, 85, 21);
		frame.getContentPane().add(AddCap);

	}
}
