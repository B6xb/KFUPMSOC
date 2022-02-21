package AddTournament;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Admin.AdminPage;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.sql.*;

public class addtournament {

	private JFrame frame;
	private JTextField textid;
	private JTextField textname;
	private JTextField textstart;
	private JTextField textend;
	private JTable table;

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
					addtournament window = new addtournament();
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
	 * @throws Exception
	 */
	public addtournament() throws Exception {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 */
	private void initialize() throws Exception {

		frame = new JFrame();
		frame.setBounds(300, 300, 742, 546);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblAddTournament = new JLabel("Add Tournament");
		lblAddTournament.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAddTournament.setBounds(310, 13, 110, 16);
		frame.getContentPane().add(lblAddTournament);

		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AdminPage j = new AdminPage();
				AdminPage.main(null);
			}
		});
		btnBack.setBounds(310, 371, 97, 25);
		frame.getContentPane().add(btnBack);

		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(310, 424, 97, 25);
		frame.getContentPane().add(btnExit);

		JLabel lblTiid = new JLabel("tr_id");
		lblTiid.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTiid.setBounds(32, 89, 76, 16);
		frame.getContentPane().add(lblTiid);

		textid = new JTextField();
		textid.setBounds(110, 86, 116, 22);
		frame.getContentPane().add(textid);
		textid.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 344, 371, -7);
		frame.getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 329, 700, 2);
		frame.getContentPane().add(separator_1);

		JLabel lblTrname = new JLabel("tr_name");
		lblTrname.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTrname.setBounds(32, 131, 76, 16);
		frame.getContentPane().add(lblTrname);

		textname = new JTextField();
		textname.setColumns(10);
		textname.setBounds(110, 128, 116, 22);
		frame.getContentPane().add(textname);

		JLabel lblStartDate = new JLabel("start date");
		lblStartDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStartDate.setBounds(32, 176, 76, 16);
		frame.getContentPane().add(lblStartDate);

		textstart = new JTextField();
		textstart.setColumns(10);
		textstart.setBounds(110, 173, 116, 22);
		frame.getContentPane().add(textstart);

		JLabel lblEndDate = new JLabel("End date");
		lblEndDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEndDate.setBounds(32, 215, 76, 16);
		frame.getContentPane().add(lblEndDate);

		textend = new JTextField();
		textend.setColumns(10);
		textend.setBounds(110, 212, 116, 22);
		frame.getContentPane().add(textend);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url, user, pass);
					String query = "insert into kfupm_tournament (tr_id,tr_name,start_date,end_date) values (?,?,?,?) ";
					PreparedStatement stat = con.prepareStatement(query);
					stat.setString(1, textid.getText());
					stat.setString(2, textname.getText());
					stat.setString(3, textstart.getText());
					stat.setString(4, textend.getText());
					stat.execute();
					textid.setText(null);
					textname.setText(null);
					textstart.setText(null);
					textend.setText(null);
					JOptionPane.showMessageDialog(null, "Insertion done ");
					con.close();
					stat.close();
				} catch (Exception ex) {
					ex.getStackTrace();
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAdd.setBounds(76, 247, 110, 25);
		frame.getContentPane().add(btnAdd);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(12, 45, 700, 2);
		frame.getContentPane().add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(260, 45, 1, 286);
		frame.getContentPane().add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(247, 55, 1, 282);
		frame.getContentPane().add(separator_4);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(551, 173, 2, 2);
		frame.getContentPane().add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(310, 89, 371, 230);
		frame.getContentPane().add(scrollPane_1);

		table = new JTable();
		scrollPane_1.setViewportView(table);

		JButton btnViewTable = new JButton("View Table");
		btnViewTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url, user, pass);
					String query = "select * from kfupm_tournament ";
					PreparedStatement stat = con.prepareStatement(query);

					ResultSet result = stat.executeQuery();

					table.setModel(DbUtils.resultSetToTableModel(result));

				} catch (Exception ex) {
					ex.getStackTrace();
				}
			}
		});
		btnViewTable.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnViewTable.setBounds(76, 285, 110, 25);
		frame.getContentPane().add(btnViewTable);

	}
}