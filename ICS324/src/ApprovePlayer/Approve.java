package ApprovePlayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Admin.AdminPage;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
import java.awt.Color;

public class Approve {

	private JFrame frame;
	private JTable table;
	private JTextField textField;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/kfupm";
	private String user = "root";
	private String pass = "09Labas09--";
	private JTextField Player_ID_txtF;
	private JTextField textField_NAme;
	private JTextField textField_Pos;
	private JTextField textField_Brith;
	private JTextField textField_Jers;
	private JTextField textField_TeamID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Approve window = new Approve();
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
	public Approve() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 300, 950, 555);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblA = new JLabel("Approve a player to join a team");
		lblA.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblA.setBounds(270, 0, 343, 36);
		frame.getContentPane().add(lblA);

		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AdminPage j = new AdminPage();
				AdminPage.main(null);
			}
		});
		btnBack.setBounds(26, 414, 100, 25);
		frame.getContentPane().add(btnBack);

		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(792, 414, 100, 25);
		frame.getContentPane().add(btnExit);

		JList list = new JList();
		list.setValueIsAdjusting(true);
		list.setVisibleRowCount(5);
		list.setBounds(226, 118, 343, 225);
		frame.getContentPane().add(list);

		DefaultListModel DLM = new DefaultListModel();
		DLM.addElement("1031\t Abdulrahman\t 1999-01-02\t 1214\t GK\t 13");
		DLM.addElement("1032\t Qasim\t 1998-12-06\t 1214\t GK\t 14");
		DLM.addElement("1033\t BANDAR\t 1997-11-10\t 1215\t GK\t 13");
		DLM.addElement("1034\t EID\t 1996-10-14\t 1215\t GK\t 14");
		DLM.addElement("1035\t BASIM\t\t 1996-09-18\t 1216\t GK\t 13");
		DLM.addElement("1036\t OMAR\t 1997-08-22\t 1216\t GK\t 14");
		DLM.addElement("1037\t HASAN\t 1998-07-26\t 1217\t GK\t 13");
		list.setModel(DLM);

		JButton btnApprove = new JButton("Approve");
		btnApprove.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnApprove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> SeList = list.getSelectedValuesList();

				for (String Selected : SeList) {
					// Id
					int ApPlaId = Integer.parseInt(Selected.substring(0, 4));

					// Name
					int indSp1 = Selected.indexOf(32);
					String SelSub1 = Selected.substring(indSp1 + 1);
					int indSp2 = SelSub1.indexOf(32);
					String ApPlaNa = SelSub1.substring(0, indSp2 - 1);

					// Birth
					String SelSub2 = SelSub1.substring(indSp2 + 1);
					int indSp3 = SelSub2.indexOf(32);
					String ApPlaBi = SelSub2.substring(0, indSp3 - 1);

					// team id
					String SelSub3 = SelSub2.substring(indSp3 + 1);
					int indSp4 = SelSub3.indexOf(32);
					int ApPlaTe = Integer.parseInt(SelSub3.substring(0, indSp4 - 1));

					// Position
					String SelSub4 = SelSub3.substring(indSp4 + 1);
					int indSp5 = SelSub4.indexOf(32);
					String ApPlaPo = SelSub4.substring(0, indSp5 - 1);

					// jersey
					int indSJer = Selected.lastIndexOf(32);
					int ApPlaJe = Integer.parseInt(Selected.substring(indSJer + 1));

					// Insert into DB
					try {

						Class.forName(driver);
						Connection con = DriverManager.getConnection(url, user, pass);

						String query = " insert into player values (?,?,?,?,?,?)";
						PreparedStatement stat = con.prepareStatement(query);

						stat.setInt(1, ApPlaId);
						stat.setInt(2, ApPlaTe);
						stat.setInt(3, ApPlaJe);
						stat.setString(4, ApPlaNa);
						stat.setString(5, ApPlaPo);
						stat.setString(6, ApPlaBi);
						stat.execute();

						JOptionPane.showMessageDialog(null, "Approved");

						con.close();
						stat.close();

					} catch (Exception ex) {
						ex.getStackTrace();
					}

					DLM.removeElement(Selected);
				}
			}
		});
		btnApprove.setBounds(295, 414, 100, 25);
		frame.getContentPane().add(btnApprove);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(226, 83, 45, 13);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(280, 83, 45, 13);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Birth");
		lblNewLabel_2.setBounds(334, 83, 45, 13);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Team ID");
		lblNewLabel_3.setBounds(371, 83, 75, 13);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblJersey = new JLabel("Jersey");
		lblJersey.setBounds(523, 83, 45, 13);
		frame.getContentPane().add(lblJersey);

		JLabel lblPosition = new JLabel("Position");
		lblPosition.setBounds(452, 83, 75, 13);
		frame.getContentPane().add(lblPosition);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(626, 118, 258, 231);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton viewB = new JButton("Search");
		viewB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					Class.forName(driver);
					Connection con = DriverManager.getConnection(url, user, pass);
					String query = "select * from player where player_id = ?";
					PreparedStatement stat = con.prepareStatement(query);
					stat.setString(1, textField.getText());

					ResultSet result = stat.executeQuery();

					table.setModel(DbUtils.resultSetToTableModel(result));
					textField.setText(null);
					con.close();
					stat.close();

				} catch (Exception ex) {
					ex.getStackTrace();
				}
			}
		});
		viewB.setForeground(Color.BLACK);
		viewB.setFont(new Font("Tahoma", Font.BOLD, 13));
		viewB.setBounds(788, 85, 85, 21);
		frame.getContentPane().add(viewB);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(650, 87, 96, 19);
		frame.getContentPane().add(textField);

		JLabel lblSerchByTorunament = new JLabel("Search by player id");
		lblSerchByTorunament.setForeground(Color.BLACK);
		lblSerchByTorunament.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSerchByTorunament.setBounds(684, 53, 164, 26);
		frame.getContentPane().add(lblSerchByTorunament);

		JButton btnVei = new JButton("view ");
		btnVei.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					Class.forName(driver);
					Connection con = DriverManager.getConnection(url, user, pass);
					String query = "select * from player";
					PreparedStatement stat = con.prepareStatement(query);

					ResultSet result = stat.executeQuery();

					table.setModel(DbUtils.resultSetToTableModel(result));

					con.close();
					stat.close();

				} catch (Exception ex) {
					ex.getStackTrace();
				}
			}
		});
		btnVei.setForeground(Color.BLACK);
		btnVei.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVei.setBounds(559, 414, 97, 25);
		frame.getContentPane().add(btnVei);

		JLabel lblPlayerId = new JLabel("Player ID");
		lblPlayerId.setBounds(10, 100, 115, 33);
		frame.getContentPane().add(lblPlayerId);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 140, 115, 33);
		frame.getContentPane().add(lblName);

		JLabel lblNewLabel_4 = new JLabel("Birth");
		lblNewLabel_4.setBounds(10, 180, 115, 33);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Position");
		lblNewLabel_5.setBounds(10, 220, 115, 33);
		frame.getContentPane().add(lblNewLabel_5);

		JLabel lblJersey_1 = new JLabel("Jersey");
		lblJersey_1.setBounds(10, 260, 115, 33);
		frame.getContentPane().add(lblJersey_1);

		JLabel lblTeamId = new JLabel("Team ID");
		lblTeamId.setBounds(10, 300, 115, 33);
		frame.getContentPane().add(lblTeamId);

		Player_ID_txtF = new JTextField();
		Player_ID_txtF.setBounds(70, 100, 100, 30);
		frame.getContentPane().add(Player_ID_txtF);
		Player_ID_txtF.setColumns(10);

		textField_NAme = new JTextField();
		textField_NAme.setBounds(70, 140, 100, 30);
		frame.getContentPane().add(textField_NAme);
		textField_NAme.setColumns(10);

		textField_Pos = new JTextField();
		textField_Pos.setBounds(70, 220, 100, 30);
		frame.getContentPane().add(textField_Pos);
		textField_Pos.setColumns(10);

		textField_Brith = new JTextField();
		textField_Brith.setText("");
		textField_Brith.setBounds(70, 180, 100, 30);
		frame.getContentPane().add(textField_Brith);
		textField_Brith.setColumns(10);

		textField_Jers = new JTextField();
		textField_Jers.setText("");
		textField_Jers.setBounds(70, 260, 100, 30);
		frame.getContentPane().add(textField_Jers);
		textField_Jers.setColumns(10);

		textField_TeamID = new JTextField();
		textField_TeamID.setText("");
		textField_TeamID.setBounds(70, 300, 100, 30);
		frame.getContentPane().add(textField_TeamID);
		textField_TeamID.setColumns(10);

		JButton btnAddToList = new JButton("Add to List");
		btnAddToList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String PayerIDInput = Player_ID_txtF.getText();
				String NameInput = textField_NAme.getText();
				String PositionInput = textField_Pos.getText();
				String BirthInput = textField_Brith.getText();
				String JerInput = textField_Jers.getText();
				String TeamIDInput = textField_TeamID.getText();

				DLM.addElement(PayerIDInput + "\t " + NameInput + "\t " + BirthInput + "\t " + TeamIDInput + "\t "
						+ PositionInput + "\t " + JerInput);
				list.setModel(DLM);

				Player_ID_txtF.setText(null);
				textField_NAme.setText(null);
				textField_Pos.setText(null);
				textField_Brith.setText(null);
				textField_Jers.setText(null);
				textField_TeamID.setText(null);

			}
		});
		btnAddToList.setBounds(25, 345, 120, 35);
		frame.getContentPane().add(btnAddToList);

	}
}