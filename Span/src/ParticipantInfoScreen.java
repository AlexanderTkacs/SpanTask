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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;

import java.sql.*;

public class ParticipantInfoScreen extends JFrame {

	private static JPanel contentPane;
	private JTextField IDField;
	private static JTextField AgeField;
	private static JTextField GradeField;
	private static JTextField CityField;
	private static JTextField StateField;
	private static JTextField DateField;
	
	private static JComboBox EthnicityComboBox;
	private static JComboBox PrimaryLanguageComboBox;
	private static JComboBox SecondaryLanguageComboBox;
	
	LocalDateTime date = LocalDateTime.now();
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	String dateOut = dateFormat.format(date);
	
	private static int partNum;
	
	public ParticipantInfoScreen(int idNum) {
		partNum = idNum;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, main.width, main.height);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("fill:4dlu"),
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblEnterParticipantInformation = new JLabel("Enter Participant Information");
		lblEnterParticipantInformation.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblEnterParticipantInformation.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblEnterParticipantInformation, "4, 2");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "4, 4, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default:grow"),
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default:grow"),}));
		
		JLabel lblId = new JLabel("Participant Number");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblId, "2, 4, right, default");
		
		IDField = new JTextField();
		IDField.setText(""+idNum);
		IDField.setEnabled(false);
		IDField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(IDField, "4, 4, fill, default");
		IDField.setColumns(10);
		
		JLabel AgeLabel = new JLabel("Age");
		AgeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(AgeLabel, "2, 6, right, default");
		
		AgeField = new JTextField();
		AgeField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(AgeField, "4, 6, fill, default");
		AgeField.setColumns(10);
		
		JLabel GradeLabel = new JLabel("Grade");
		GradeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(GradeLabel, "2, 8, right, default");
		
		GradeField = new JTextField();
		GradeField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(GradeField, "4, 8, fill, default");
		GradeField.setColumns(10);
		
		JLabel EthnicityLabel = new JLabel("Ethnicity");
		EthnicityLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(EthnicityLabel, "2, 10, right, default");
		
		EthnicityComboBox = new JComboBox(getEthnicityList());
		EthnicityComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(EthnicityComboBox, "4, 10, fill, default");
		
		JLabel PrimaryLanguageLabel = new JLabel("Primary Language");
		PrimaryLanguageLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(PrimaryLanguageLabel, "2, 12, right, default");
		
		PrimaryLanguageComboBox = new JComboBox(getLanguageList(true));
		PrimaryLanguageComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(PrimaryLanguageComboBox, "4, 12, fill, default");
		
		JLabel SecondaryLanguageLabel = new JLabel("Secondary Language");
		SecondaryLanguageLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(SecondaryLanguageLabel, "2, 14, right, default");
		
		SecondaryLanguageComboBox = new JComboBox(getLanguageList(false));
		SecondaryLanguageComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(SecondaryLanguageComboBox, "4, 14, fill, default");
		
		JLabel CityLabel = new JLabel("City");
		CityLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(CityLabel, "2, 16, right, default");
		
		CityField = new JTextField();
		CityField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(CityField, "4, 16, fill, default");
		CityField.setColumns(10);
		
		JLabel StateLabel = new JLabel("State");
		StateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(StateLabel, "2, 18, right, default");
		
		StateField = new JTextField();
		StateField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(StateField, "4, 18, fill, default");
		StateField.setColumns(10);
		
		JLabel DateLabel = new JLabel("Date");
		DateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(DateLabel, "2, 20, right, default");
		
		DateField = new JTextField();
		DateField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DateField.setText(dateOut);
		panel.add(DateField, "4, 20, fill, default");
		DateField.setColumns(10);
		
		JButton SubmitButton = new JButton("Submit");
		SubmitButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		SubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(getParticipant(idNum))
				{
					saveTodayInfo();
				}
				else
				{
					saveParticipant();
					saveTodayInfo();
				}
				
				main.makeScreen(3);
				dispose();
				
			}
		});
		contentPane.add(SubmitButton, "4, 6");
		
		if(getParticipant(idNum))
		{
			//old user
			EthnicityComboBox.setEnabled(false);
			PrimaryLanguageComboBox.setEnabled(false);
			updateComboBoxes();
			
		}

	}
	
	private String[] getEthnicityList()
	{
		ArrayList<String> ethnicities = new ArrayList<String>();
		ethnicities.add("");
		
		try
		{
			Connection conn = DriverManager.getConnection(main.connPath);
			
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT EthnicityValue FROM Ethnicity ORDER BY EthnicityValue;");
			
			while (rs.next()) { 
			    ethnicities.add(rs.getString(1));
			}
		}
		catch(Exception e)
		{
			System.out.println("getEthnicityList");
			System.out.println(e);
		}
		
		String[] ethnicityArray = new String[ethnicities.size()];
		ethnicityArray = ethnicities.toArray(ethnicityArray);
		
		return ethnicityArray;
	}
	
	private String[] getLanguageList(boolean primaryLang)
	{
		ArrayList<String> languages = new ArrayList<String>();
		if(primaryLang)
			languages.add("");
		else
			languages.add("<None>");
		
		try
		{
			Connection conn = DriverManager.getConnection(main.connPath);
			
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT LanguageValue FROM Language ORDER BY LanguageValue;");
			
			while (rs.next()) 
			{ 
			    languages.add(rs.getString(1));
			}
		}
		catch(Exception e)
		{
			System.out.println("getLanguageList");
			System.out.println(e);
		}
		
		String[] languageArray = new String[languages.size()];
		languageArray = languages.toArray(languageArray);
		
		return languageArray;
	}
	
	public static boolean getParticipant(int partNum)
	{
		try
		{
			Connection conn = DriverManager.getConnection(main.connPath);
			
			Statement s = conn.createStatement();
			
			String query = "SELECT FK_Ethnicity, FK_PrimaryLanguage FROM Participant WHERE ParticipantNumber = "+partNum+";";
			
			ResultSet rs = s.executeQuery(query);
			
			if(rs.next())
			{
				//old user
				return true;
			}
			else
			{
				//not exist, create person
				return false;
			}
			
		}
		catch(Exception e)
		{
			System.out.println("getParticipant");
			System.out.println(e);
		}
		
		return false;
		
	}
	
	public static void saveParticipant()
	{		
		try
		{
			Connection conn = DriverManager.getConnection(main.connPath);
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Participant (ParticipantNumber, FK_Ethnicity, FK_PrimaryLanguage) VALUES (?, ?, ?)");
			
			ps.setInt(1, partNum); 		//ParticipantNumber
			ps.setInt(2, main.getEthnicityId((String)EthnicityComboBox.getSelectedItem())); 			//FK_Ethnicity
			ps.setInt(3, main.getLanguageId((String)PrimaryLanguageComboBox.getSelectedItem()));		//FK_PrimaryLanguage

			ps.executeUpdate();
			
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println("SaveParticipant");
			System.out.println(e);
		}

	}
	
	public static void saveTodayInfo()
	{
		try
		{
			Connection conn = DriverManager.getConnection(main.connPath);
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO ParticipantScores (FK_Participant, Age, Grade, FK_SecondaryLanguage, City, State, DateOfTest) VALUES (?, ?, ?, ?, ?, ?, ?);");
			
			ps.setInt(1, partNum);																	//FK_Participant
			ps.setInt(2, Integer.parseInt(AgeField.getText())); 									//Age
			ps.setInt(3, Integer.parseInt(GradeField.getText()));									//Grade
			
			if(main.getLanguageId((String)SecondaryLanguageComboBox.getSelectedItem()) != -1)
				ps.setInt(4, main.getLanguageId((String)SecondaryLanguageComboBox.getSelectedItem()));	//FK_SecondaryLanguage
			else
				ps.setNull(4, Types.BIGINT);
			
			ps.setString(5, CityField.getText()); 													//City
			ps.setString(6, StateField.getText());													//State
			
			SimpleDateFormat mmddyyyyFormat = new SimpleDateFormat("MM/dd/yyyy");
			Timestamp date = new Timestamp(mmddyyyyFormat.parse(DateField.getText()).getTime());
			ps.setTimestamp(7, date);													//Date
			
			ps.executeUpdate();
			
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT MAX(ID) FROM ParticipantScores;");
			rs.next();
			
			main.participantScoreID = (int)rs.getObject(1);
			
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println("SaveTodayInfo");
			System.out.println(e);
		}

	}
	
	private static void updateComboBoxes()
	{
		try
		{
			Connection conn = DriverManager.getConnection(main.connPath);
			
			Statement s = conn.createStatement();
			
			String query = "SELECT EthnicityValue, LanguageValue FROM Participant INNER JOIN Ethnicity ON FK_Ethnicity = Ethnicity.ID INNER JOIN Language ON FK_PrimaryLanguage = Language.ID WHERE ParticipantNumber = "+partNum+";";
			
			ResultSet rs = s.executeQuery(query);
			
			rs.next();
			
			PrimaryLanguageComboBox.setSelectedItem((String)rs.getObject(2));
			EthnicityComboBox.setSelectedItem((String)rs.getObject(1));
		}
		catch(Exception e)
		{
			System.out.println("UpdateComboBox");
			System.out.println(e);
		}
	}

}
