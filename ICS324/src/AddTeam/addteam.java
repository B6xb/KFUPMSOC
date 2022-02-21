package AddTeam;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Admin.AdminPage;
import JDBC.JDBC;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class addteam extends JFrame {

	private JPanel contentPane;
	private JTextField ID;
	private JTextField Tournament;
	private JDBC JDBC;
	private JTable TeamSchema;

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
					addteam frame = new addteam();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public addteam() throws SQLException, ClassNotFoundException {



		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 742, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTeamAddPage = new JLabel("team add page");
		lblTeamAddPage.setBounds(340, 13, 143, 25);
		contentPane.add(lblTeamAddPage);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});
		btnExit.setBounds(127, 474, 97, 25);
		contentPane.add(btnExit);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdminPage j = new AdminPage();
				AdminPage.main(null);
			}
		});
		btnBack.setBounds(356, 474, 97, 25);
		contentPane.add(btnBack);

		ID = new JTextField();
		ID.setBounds(127, 108, 96, 19);
		contentPane.add(ID);
		ID.setColumns(10);

		JLabel IDLabel_1 = new JLabel("ID");
		IDLabel_1.setBounds(10, 105, 107, 25);
		contentPane.add(IDLabel_1);

		Tournament = new JTextField();
		Tournament.setBounds(127, 160, 96, 19);
		contentPane.add(Tournament);
		Tournament.setColumns(10);

		JLabel lblEnterTorunament = new JLabel("Torunament");
		lblEnterTorunament.setBounds(10, 154, 107, 25);
		contentPane.add(lblEnterTorunament);

		JButton btnNewButton = new JButton("ADD Team");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Class.forName(driver);
					con = DriverManager.getConnection(url, uname, password);
					String query = " insert into kfupm_team values (?,?,'x',0,0,0,0,0,0,0,0,0)";
					PreparedStatement stat = con.prepareStatement(query);
					
					stat.setString(1, ID.getText());
					stat.setString(2, Tournament.getText());
					
					stat.execute();

					Tournament.setText(null);
					ID.setText(null);
				
					
					JOptionPane.showMessageDialog(null, "Team Added ");
					con.close();
					stat.close();

				} catch (Exception ex) {
					ex.getStackTrace();
				}

			}
		});
		btnNewButton.setBounds(310, 418, 85, 21);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(250, 48, 286, 358);
		contentPane.add(scrollPane_1);

		JScrollPane SchemaT = new JScrollPane();
		scrollPane_1.setViewportView(SchemaT);

		TeamSchema = new JTable();
		SchemaT.setViewportView(TeamSchema);

		JButton viewB = new JButton("View");
		viewB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					Class.forName(driver);
					con = DriverManager.getConnection(url, uname, password);
					String query = "select * from kfupm_team where tr_id = ?";
					PreparedStatement stat = con.prepareStatement(query);
					stat.setString(1, Tournament.getText());

					ResultSet result = stat.executeQuery();

					TeamSchema.setModel(DbUtils.resultSetToTableModel(result));
					Tournament.setText(null);
					con.close();
					stat.close();

				} catch (Exception ex) {
					ex.getStackTrace();
				}
			}
		});
		viewB.setBounds(417, 418, 85, 21);
		contentPane.add(viewB);

	}
}