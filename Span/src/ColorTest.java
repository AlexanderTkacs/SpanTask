import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import java.awt.Color;

import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ColorTest extends JFrame {

	private JPanel contentPane;
	JLabel lblNumber = new JLabel("number");
	Timer tm;

	public ColorTest(int spanLen, int numLeft) {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0,  main.width, main.height);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		lblNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumber.setFont(new Font("Tahoma", Font.PLAIN, 70));
		contentPane.add(lblNumber, "4, 4");
		lblNumber.setVisible(false);

		main.testPattern.newPattern(spanLen);
		colors(spanLen, numLeft);
	}
	
	private void colors(int spanLen, int numLeft) {
		int[] i = {0};
		tm = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(i[0]<spanLen)
					contentPane.setBackground(Color.decode(main.testPattern.getColorHex(i[0])));
				i[0]++;
				if(i[0]>spanLen)
				{
					contentPane.setBackground(Color.WHITE);
					tm.stop();
					number(spanLen, numLeft);
				}
			}
		});
		
		tm.start();
	}
	
	private void number(int spanLen, int numLeft) {
			lblNumber.setVisible(true);
			lblNumber.setText(main.testPattern.getNumber());
			tm = new Timer(4000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tm.stop();
				switch (spanLen) {
					case 3:
						new AnswerScreen3Span(numLeft, true).setVisible(true);
						break;
					case 4:
						new AnswerScreen4Span(numLeft, true).setVisible(true);
						break;
					case 5:
						new AnswerScreen5Span(numLeft, true).setVisible(true);
						break;
					case 6:
						new AnswerScreen6Span(numLeft, true).setVisible(true);
						break;
				}
				dispose();
			}
		});
		
		tm.start();
	}
}
