import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.border.LineBorder;

import java.awt.event.*;

public class AnswerScreen6Span extends JFrame {
	private int selectedColor = -1;
	private boolean even;
	private int[] colors = {-1, -1, -1, -1, -1, -1};
	
	private JPanel color1Answer;
	private JPanel color2Answer;
	private JPanel color3Answer;
	private JPanel color4Answer;
	private JPanel color5Answer;
	private JPanel color6Answer;
	
	//color selection
	private int colorBoxWidth = (int)(main.width/9);
	private int colorBoxHeight = (int)(main.width*0.15);
	
	private int redColorBoxX = (int)(main.width/9);
	private int redColorBoxY = (int)(main.height*0.1);

	private int yellowColorBoxX = (int)(3*main.width/9);
	private int yellowColorBoxY = (int)(main.height*0.1);
	
	private int blueColorBoxX = (int)(5*main.width/9);
	private int blueColorBoxY = (int)(main.height*0.1);

	private int purpleColorBoxX = (int)(7*main.width/9);
	private int purpleColorBoxY = (int)(main.height*0.1);

	//color answer
	private int answerBoxWidth = (int)(main.width*0.11);
	private int answerBoxHeight = (int)(main.height*0.25);
	
	private int answer1BoxX = (int)(main.width*0.20);
	private int answer1BoxY = (int)(main.height*0.55);
	
	private int answer2BoxX = (int)(main.width*0.33);
	private int answer2BoxY = (int)(main.height*0.55);
	
	private int answer3BoxX = (int)(main.width*0.46);
	private int answer3BoxY = (int)(main.height*0.55);
	
	private int answer4BoxX = (int)(main.width*0.59);
	private int answer4BoxY = (int)(main.height*0.55);
	
	private int answer5BoxX = (int)(main.width*0.72);
	private int answer5BoxY = (int)(main.height*0.55);
	
	private int answer6BoxX = (int)(main.width*0.85);
	private int answer6BoxY = (int)(main.height*0.55);
	
	//misc bounding panes
	private int questionPanelyEnd = (int)(main.height*0.05);
	
	private int oddEvenPaneY = (int)(main.height*0.5);
	private int oddEvenPaneWidth = (int)(main.width*0.2);
	private int oddEvenPaneHeight = (int)(main.height*0.4);
	
	private int submitY = (int)(main.height*0.9);
	private int submitHeight = (int)(main.height*0.1);

	public AnswerScreen6Span(int numLeft, boolean visual) {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				int mouseX = e.getXOnScreen();
				int mouseY = e.getYOnScreen();
				
				//ClickInRed
				if(((mouseX >= redColorBoxX && mouseX <= redColorBoxX+colorBoxWidth)) &&
					(mouseY >= redColorBoxY && mouseY <= redColorBoxY+colorBoxHeight))
					selectedColor = 0;
				//Blue
				else if(((mouseX >= yellowColorBoxX && mouseX <= yellowColorBoxX+colorBoxWidth)) &&
						(mouseY >= yellowColorBoxY && mouseY <= yellowColorBoxY+colorBoxHeight))
					selectedColor = 1;
				//Blue
				else if(((mouseX >= blueColorBoxX && mouseX <= blueColorBoxX+colorBoxWidth)) &&
						(mouseY >= blueColorBoxY && mouseY <= blueColorBoxY+colorBoxHeight))
					selectedColor = 2;
				//Blue
				else if(((mouseX >= purpleColorBoxX && mouseX <= purpleColorBoxX+colorBoxWidth)) &&
						(mouseY >= purpleColorBoxY && mouseY <= purpleColorBoxY+colorBoxHeight))
					selectedColor = 3;
				else
					selectedColor = -1;
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				int mouseX = e.getXOnScreen();
				int mouseY = e.getYOnScreen();
				
				//if color selected
				if(selectedColor != -1)
				{
					//Release in color1
					if((mouseX >= answer1BoxX) && (mouseX <= answer1BoxX + answerBoxWidth) &&
							(mouseY >= answer1BoxY) && (mouseY <= answer1BoxY + answerBoxHeight))
						{
							colors[0] = selectedColor;
							color1Answer.setBackground(Color.decode(main.testPattern.getColorHex1(colors[0])));

						}
						//color2
						else if((mouseX >= answer2BoxX) && (mouseX <= answer2BoxX + answerBoxWidth) &&
								(mouseY >= answer2BoxY) && (mouseY <= answer2BoxY + answerBoxHeight))
						{
							colors[1] = selectedColor;
							color2Answer.setBackground(Color.decode(main.testPattern.getColorHex1(colors[1])));
							
						}
						//color3
						else if((mouseX >= answer3BoxX) && (mouseX <= answer3BoxX + answerBoxWidth) &&
								(mouseY >= answer3BoxY) && (mouseY <= answer3BoxY + answerBoxHeight))
						{
							colors[2] = selectedColor;
							color3Answer.setBackground(Color.decode(main.testPattern.getColorHex1(colors[2])));
						}
						else if((mouseX >= answer4BoxX) && (mouseX <= answer4BoxX + answerBoxWidth) &&
								(mouseY >= answer4BoxY) && (mouseY <= answer4BoxY + answerBoxHeight))
						{
							colors[3] = selectedColor;
							color4Answer.setBackground(Color.decode(main.testPattern.getColorHex1(colors[3])));
						}
						else if((mouseX >= answer5BoxX) && (mouseX <= answer5BoxX + answerBoxWidth) &&
								(mouseY >= answer5BoxY) && (mouseY <= answer5BoxY + answerBoxHeight))
						{
							colors[4] = selectedColor;
							color5Answer.setBackground(Color.decode(main.testPattern.getColorHex1(colors[4])));
						}
						else if((mouseX >= answer6BoxX) && (mouseX <= answer6BoxX + answerBoxWidth) &&
								(mouseY >= answer6BoxY) && (mouseY <= answer6BoxY + answerBoxHeight))
						{
							colors[5] = selectedColor;
							color6Answer.setBackground(Color.decode(main.testPattern.getColorHex1(colors[5])));
						}
						else
						{
							
						}
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0,  main.width, main.height);
		setUndecorated(true);
		getContentPane().setLayout(null);
		
		JPanel questionPanel = new JPanel();
		questionPanel.setBounds(0, 0, main.width, questionPanelyEnd);
		getContentPane().add(questionPanel);
		questionPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		JLabel lblNewLabel = new JLabel("What order were the colors in?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionPanel.add(lblNewLabel, "4, 1");
		
		JPanel redSelect = new JPanel();
		redSelect.setBackground(Color.decode("#800000"));
		redSelect.setBounds(redColorBoxX, redColorBoxY, colorBoxWidth, colorBoxHeight);
		getContentPane().add(redSelect);
		
		JPanel yellowSelect = new JPanel();
		yellowSelect.setBackground(Color.decode("#FFFF00"));
		yellowSelect.setBounds(yellowColorBoxX, yellowColorBoxY, colorBoxWidth, colorBoxHeight);
		getContentPane().add(yellowSelect);
		
		JPanel blueSelect = new JPanel();
		blueSelect.setBackground(Color.decode("#004080"));
		blueSelect.setBounds(blueColorBoxX, blueColorBoxY, colorBoxWidth, colorBoxHeight);
		getContentPane().add(blueSelect);
		
		JPanel purpleSelect = new JPanel();
		purpleSelect.setBackground(Color.decode("#807FFE"));
		purpleSelect.setBounds(purpleColorBoxX, purpleColorBoxY, colorBoxWidth, colorBoxHeight);
		getContentPane().add(purpleSelect);
		
		JPanel oddEvenPanel = new JPanel();
		oddEvenPanel.setBounds(0, oddEvenPaneY, oddEvenPaneWidth, oddEvenPaneHeight);
		getContentPane().add(oddEvenPanel);
		oddEvenPanel.setLayout(new FormLayout(new ColumnSpec[] {
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel oddEvenLabel = new JLabel("Was the number");
		oddEvenLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		oddEvenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		oddEvenPanel.add(oddEvenLabel, "4, 4");
		
		JLabel oddEvenLabel2 = new JLabel("odd or even?");
		oddEvenLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		oddEvenLabel2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		oddEvenPanel.add(oddEvenLabel2, "4, 6");
		
		JButton btnOdd = new JButton("Odd");
		btnOdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				even = false;
			}
		});
		btnOdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		oddEvenPanel.add(btnOdd, "4, 10");
		
		JButton evenButton = new JButton("Even");
		evenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				even = true;
			}
		});
		evenButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		oddEvenPanel.add(evenButton, "4, 14, default, top");
		
		//JPanel color1Answer = new JPanel();
		color1Answer = new JPanel();
		color1Answer.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		color1Answer.setBounds(answer1BoxX, answer1BoxY, answerBoxWidth, answerBoxHeight);
		getContentPane().add(color1Answer);
		
		//JPanel color2Answer = new JPanel();
		color2Answer = new JPanel();
		color2Answer.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		color2Answer.setBounds(answer2BoxX, answer2BoxY, answerBoxWidth, answerBoxHeight);
		getContentPane().add(color2Answer);
		
		//JPanel color3Answer = new JPanel();
		color3Answer = new JPanel();
		color3Answer.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		color3Answer.setBounds(answer3BoxX, answer3BoxY, answerBoxWidth, answerBoxHeight);
		getContentPane().add(color3Answer);
		
		color4Answer = new JPanel();
		color4Answer.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		color4Answer.setBounds(answer4BoxX, answer4BoxY, answerBoxWidth, answerBoxHeight);
		getContentPane().add(color4Answer);
		
		color5Answer = new JPanel();
		color5Answer.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		color5Answer.setBounds(answer5BoxX, answer5BoxY, answerBoxWidth, answerBoxHeight);
		getContentPane().add(color5Answer);
		
		color6Answer = new JPanel();
		color6Answer.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		color6Answer.setBounds(answer6BoxX, answer6BoxY, answerBoxWidth, answerBoxHeight);
		getContentPane().add(color6Answer);
		
		JPanel submitButtonPanel = new JPanel();
		submitButtonPanel.setBounds(0, submitY, main.width, submitHeight);
		getContentPane().add(submitButtonPanel);
		submitButtonPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		JButton sumbitButton = new JButton("Submit");
		sumbitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int correctColors = 0;
				int incorrectColors = 0;
				
				for(int i =0; i < 3; i++)
				{
					if(colors[i] == main.testPattern.getColorAt(i))
						correctColors++;
					else
						incorrectColors++;
				}
				
				if(visual)
				{
					try
					{	
						Connection conn = DriverManager.getConnection(main.connPath);
						
						PreparedStatement ps = conn.prepareStatement("UPDATE ParticipantScores SET VisualColorScore = VisualColorScore + ?, VisualNumberScore = VisualNumberScore + ? WHERE ID = ?;");
						
						ps.setInt(1, correctColors);
						
						if(main.testPattern.getEven() == even)
							ps.setInt(2, 1);
						else
							ps.setInt(2, -1);
						
						ps.setInt(3, main.participantScoreID);
						
						ps.executeUpdate();
						
					}
					catch(Exception exeption)
					{
						System.out.println(exeption);
					}
					
				}
				else
				{
					try
					{	
						Connection conn = DriverManager.getConnection(main.connPath);
						
						PreparedStatement ps = conn.prepareStatement("UPDATE ParticipantScores SET AudioColorScore = AudioColorScore + ?, AudioNumberScore = AudioNumberScore + ? WHERE ID = ?;");
						
						ps.setInt(1, correctColors);
						
						if(main.testPattern.getEven() == even)
							ps.setInt(2, 1);
						else
							ps.setInt(2, -1);
						
						ps.setInt(3, main.participantScoreID);
						
						ps.executeUpdate();
						
					}
					catch(Exception exeption)
					{
						System.out.println(exeption);
					}
				}
				
				//next test
				if(visual)
				{
					//if still need more tests in same span
					if(numLeft > 0)
					{
						new ColorTest(6, numLeft-1).setVisible(true);
						dispose();
					}
					//if need to increase span length
					else
					{
						//main.updateVisualScore();
						main.makeScreen(3);
						dispose();
					}
				}
				else
				{
					if(numLeft > 0)
					{
						new AudioTest(6, numLeft-1).setVisible(true);
						dispose();
					}
					else
					{
						//main.updateAudioScore();
						main.makeScreen(3);
						dispose();
					}
				}
				
			}
		});
		sumbitButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		submitButtonPanel.add(sumbitButton, "4, 1");
		
		
	}
	
}
