import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

//for audio
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioTest extends JFrame {

	private JPanel contentPane;
	JLabel lblNumber = new JLabel("number");
	Timer tm;

	public AudioTest(int spanLen, int numLeft) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, main.width, main.height);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.setBackground(Color.decode("#707070"));
		
		main.testPattern.newPattern(spanLen);
		colors(spanLen, numLeft);
	}

	private void colors(int spanLen, int numLeft) {
		int[] i = {0};
		tm = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(i[0]<spanLen)
				{
					try
				    {
						final String relName = main.testPattern.getColorName(i[0])+".wav";
						AudioInputStream inputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(relName));
						
						Clip clip = AudioSystem.getClip();
						clip.open(inputStream);
						clip.start();
				    }
				    catch (Exception exc)
				    {
				        exc.printStackTrace(System.out);
				    }					
					i[0]++;
				}
				else
				{
					tm.stop();
					number(spanLen, numLeft);
				}
			}
		});
		
		tm.start();
	}
	
	private void number(int spanLen, int numLeft) {
		
			try
		    {
				final String relName = main.testPattern.getNumber()+".wav";
				AudioInputStream inputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(relName));
				
				Clip clip = AudioSystem.getClip();
				clip.open(inputStream);
				clip.start();
		    }
		    catch (Exception exc)
		    {
		        exc.printStackTrace(System.out);
		    }
			tm = new Timer(4000, new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				tm.stop();
				switch (spanLen) {
				case 3:
					new AnswerScreen3Span(numLeft, false).setVisible(true);
					break;
				case 4:
					new AnswerScreen4Span(numLeft, false).setVisible(true);
					break;
				case 5:
					new AnswerScreen5Span(numLeft, false).setVisible(true);
					break;
				case 6:
					new AnswerScreen6Span(numLeft, false).setVisible(true);
					break;
			}
			dispose();

			}
		});
		tm.start();
	}
}
