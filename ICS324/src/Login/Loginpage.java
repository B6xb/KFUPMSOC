package Login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Admin.AdminPage;
import Gust.GustPage;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JPasswordField;

public class Loginpage {

	private JFrame frame;
	private JTextField textUser;
	private JPasswordField textPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loginpage window = new Loginpage();
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
	public Loginpage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(400, 450, 500, 350);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLoginPage = new JLabel("Login page");
		lblLoginPage.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoginPage.setBounds(190, 13, 159, 33);
		frame.getContentPane().add(lblLoginPage);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsername.setBounds(40, 62, 82, 16);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(40, 102, 82, 16);
		frame.getContentPane().add(lblPassword);
		
		textUser = new JTextField();
		textUser.setBounds(134, 59, 215, 22);
		frame.getContentPane().add(textUser);
		textUser.setColumns(10);
		
		JButton btnLoging = new JButton("Loging");
		btnLoging.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLoging.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String name = textUser.getText();
				@SuppressWarnings("deprecation")
				String passw = textPass.getText();
				String driver = "com.mysql.cj.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/kfupm";
				String user = "root";
				String pass = "09Labas09--";
				Class.forName(driver);
				Connection con = DriverManager.getConnection(url,user,pass);
				PreparedStatement st = con.prepareStatement("SELECT username,pass FROM log_in");
				
				ResultSet r = st.executeQuery();
				Boolean  temp = false;
				while(r.next()) {
					String a = r.getString("username");					 
					String p = r.getString("pass");
					if(name.contains(a)&&passw.contains(p)) {
						temp = true ;
						AdminPage j = new AdminPage();
						AdminPage.main(null);
						frame.dispose();
					} 
				}              
				if(temp.equals(false)) {
					JOptionPane.showMessageDialog(null, "wrong user or pass");
					textUser.setText(null);
					textPass.setText(null);
				}
				 
				}catch (Exception e1 ) { System.out.println(e1);}
				
			}
		});
		btnLoging.setBounds(40, 171, 97, 25);
		frame.getContentPane().add(btnLoging);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnReset.setBounds(192, 171, 97, 25);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textUser.setText(null);
				textPass.setText(null);
			}
		});
		frame.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnExit.setBounds(346, 171, 97, 25);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frame.getContentPane().add(btnExit);
		
		JButton btnGust = new JButton("Continue as Gust");
		btnGust.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					GustPage i = new GustPage();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				GustPage.main(null);
				frame.dispose();
			}
		});
		btnGust.setBounds(163, 228, 170, 35);
		frame.getContentPane().add(btnGust);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(45, 153, 398, 5);
		frame.getContentPane().add(separator);
		
		textPass = new JPasswordField();
		textPass.setBounds(132, 102, 217, 22);
		frame.getContentPane().add(textPass);
	}
}
