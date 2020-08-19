import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;
import java.text.SimpleDateFormat;

public class main 
{
	//to fit to screen
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int width = (int)screenSize.getWidth();
	static int height = (int)screenSize.getHeight();
	
	//database path, and excel path, so they can be accessed anywhere
	static String connPath = "jdbc:ucanaccess://";
	static String excelPath;
	
	static TestPattern testPattern = new TestPattern();
	
	static StartScreen startScreen = new StartScreen();
	static ViewDataScreen dataScreen = new ViewDataScreen();
	static ChooseTest chooseTestScreen = new ChooseTest();
	
	//For when to return to main screen after both are true
	static int participantScoreID;
	static boolean visualDone = false;
	static boolean audioDone = false;
	
	//how many times each span length should be shown
	//	3 by default
	static final int SPANAMOUNT = 3;
	
	public static void main(String[] args) 
	{
		//get where file is located to know where database is/where to place excel file
		String pathOfMain = main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		int index = pathOfMain.lastIndexOf("/");
		String path = pathOfMain.substring(0, index);
		try {
			String decodedPath = URLDecoder.decode(path, "UTF-8");
			connPath += decodedPath+"//testParticipants.accdb";
			excelPath = decodedPath+"//SpanTest.xlsx";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		makeScreen(1);
	}
	
	public static void makeScreen(int screenNum)
	{
		/*
		 * 1 start screen
		 * 2 info screen
		 * 3 choose test screen
		 * 4 color test 
		 * 5 audio test
		 * 6 Data screen
		 */
		switch (screenNum) {
			case 1: 
				showStart();
				visualDone = false;
				audioDone = false;
				dataScreen.setVisible(false);
				break;
			case 2: 
				new ParticipantLookup().setVisible(true);
				startScreen.setVisible(false);
				break;
			case 3:
				showChooseTest();
				break;
			case 4:
				startVisualTest();
				chooseTestScreen.setVisible(false);
				break;
			case 5:
				chooseTestScreen.setVisible(false);
				startAudioTest();
				break;
			case 6: 
				startScreen.setVisible(false);
				dataScreen.setVisible(true);
				break;
		}
	}
	
	public static void showStart()
	{
		startScreen.setVisible(true);
	}

	public static void showChooseTest()
	{
		chooseTestScreen.setVisible(true);
	}
	
	public static void startVisualTest()
	{
		testPattern.newPattern(3);
		new ColorTest(3, SPANAMOUNT).setVisible(true);
		
	}
	
	public static void startAudioTest()
	{
		testPattern.newPattern(3);
		new AudioTest(3, SPANAMOUNT).setVisible(true);
	}
	
	public static int getEthnicityId(String Eth)
	{
		try
		{
			Connection conn = DriverManager.getConnection(connPath);
			
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT ID FROM Ethnicity WHERE EthnicityValue = '"+Eth+"';");
			
			rs.next();
			
			int ethID = (int)rs.getObject(1);
			conn.close();
			return ethID;
		}
		catch(Exception e)
		{
			System.out.println("getEthID");
			System.out.println(e);
		}
	
		return -1;
	}
	
	public static int getLanguageId(String Lang)
	{
		
		try
		{
			Connection conn = DriverManager.getConnection(connPath);
			
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT ID FROM Language WHERE LanguageValue = '"+Lang+"';");
			
			rs.next();
			
			int langID = (int)rs.getObject(1);
			conn.close();
			return langID;
		}
		catch(Exception e)
		{
			System.out.println("getLangID");
			System.out.println(e);
		}
	
		return -1;
	}

}
