import javax.swing.*;
import java.awt.*;

public class Main extends javax.swing.JFrame {

	private static void LogPage() {
		JFrame f = new JFrame("ICS324 project");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(600, 600);
		f.setVisible(true);

		JLabel Label1 = new JLabel("User Name");
		JTextField userName = new JTextField();
		JLabel Label2 = new JLabel("Password");
		JTextField password = new JTextField();
		JButton loginButton = new JButton("Login");
		JLabel Label3 = new JLabel();
		Label1.setAlignmentX(0);
		Label1.setAlignmentY(0);
		f.add(Label1);
		f.add(Label2);

		f.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LogPage();
	}

}
