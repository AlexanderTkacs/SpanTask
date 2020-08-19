import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ChooseTest extends JFrame {

	private JPanel contentPane;
	
	public ChooseTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, main.width, main.height);
		//Make the window borderless fullscreen, comment out the line above
		setUndecorated(true);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("pref:grow"),
				ColumnSpec.decode("pref:grow"),
				ColumnSpec.decode("pref:grow"),
				ColumnSpec.decode("pref:grow"),
				ColumnSpec.decode("pref:grow"),
				ColumnSpec.decode("pref:grow"),
				ColumnSpec.decode("pref:grow"),},
			new RowSpec[] {
				RowSpec.decode("35px:grow"),
				RowSpec.decode("14px:grow"),
				RowSpec.decode("215px:grow"),
				RowSpec.decode("23px:grow"),
				RowSpec.decode("default:grow"),}));
		
		JLabel lblWhichTest = new JLabel("Which Test?");
		lblWhichTest.setFont(new Font("Tahoma", Font.PLAIN, 30));
		contentPane.add(lblWhichTest, "4, 2, center, center");
		
		JButton btnNewButton_1 = new JButton("Start Audio Span");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new AudioTest(3, 4).setVisible(true);
				//dispose();
				main.makeScreen(5);
			}
		});
		
		JButton btnNewButton = new JButton("Start Visual Span");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new ColorTest(3, 4).setVisible(true);
				//dispose();
				main.makeScreen(4);
			}
		});
		btnNewButton.setToolTipText("Start Visual Span");
		contentPane.add(btnNewButton, "3, 4, center, center");
		btnNewButton_1.setToolTipText("Start Audio Span");
		contentPane.add(btnNewButton_1, "5, 4, center, center");
	}
	
	public ChooseTest(int width, int height) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
		//Make the window borderless fullscreen, comment out the line above
		setUndecorated(true);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("pref:grow"),
				ColumnSpec.decode("pref:grow"),
				ColumnSpec.decode("pref:grow"),
				ColumnSpec.decode("pref:grow"),
				ColumnSpec.decode("pref:grow"),
				ColumnSpec.decode("pref:grow"),
				ColumnSpec.decode("pref:grow"),},
			new RowSpec[] {
				RowSpec.decode("35px:grow"),
				RowSpec.decode("14px:grow"),
				RowSpec.decode("215px:grow"),
				RowSpec.decode("23px:grow"),
				RowSpec.decode("default:grow"),}));
		
		JLabel lblWhichTest = new JLabel("Which Test?");
		contentPane.add(lblWhichTest, "4, 2, center, center");
		
		JButton btnNewButton = new JButton("Start Visual Span");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new ColorTest(3, 4).setVisible(true);
				//dispose();
				main.makeScreen(4);
			}
		});
		btnNewButton.setToolTipText("Start Visual Span");
		contentPane.add(btnNewButton, "2, 4, center, center");
		
		JButton btnNewButton_1 = new JButton("Start Audio Span");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new AudioTest(3, 4).setVisible(true);
				//dispose();
				main.makeScreen(5);
			}
		});
		btnNewButton_1.setToolTipText("Start Audio Span");
		contentPane.add(btnNewButton_1, "6, 4, center, center");
	}

}
