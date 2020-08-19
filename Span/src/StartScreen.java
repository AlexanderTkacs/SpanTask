import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Font;

public class StartScreen extends JFrame {

	private JPanel contentPane;

	public StartScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, main.width, main.height);
		//Make the window borderless fullscreen, comment out the line above
		//setUndecorated(true);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("143px:grow"),
				ColumnSpec.decode("max(35dlu;default):grow"),},
			new RowSpec[] {
				RowSpec.decode("35px:grow"),
				RowSpec.decode("41px"),
				RowSpec.decode("362px"),
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("4dlu:grow"),
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblStartPage = new JLabel("SPAN TESTS");
		lblStartPage.setFont(new Font("Tahoma", Font.PLAIN, 40));
		contentPane.add(lblStartPage, "2, 2, center, center");
		
		JButton btnNewButton = new JButton("Test Taker Information");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.makeScreen(2);
				return;
				
			}
		});
		contentPane.add(btnNewButton, "2, 4, center, center");
		
		JButton ViewDataButton = new JButton("View Data");
		ViewDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.makeScreen(6);
				return;
			}
		});
		ViewDataButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(ViewDataButton, "2, 6, center, center");
	}

}
