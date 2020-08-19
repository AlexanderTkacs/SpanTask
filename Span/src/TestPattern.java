import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class TestPattern
{
	
	/***************************************
	 * 				NUMBER		HEX		
	 * RED				0		#800000
	 * YELLOW			1		#FFFF00
	 * BLUE				2		#004080
	 * PURPLE			3		#807FFE
	 * 
	 ***************************************/
	
	private final String[][] COLORS = {	{"#800000", "red"}, 
										{"#FFFF00", "yellow"},
										{"#004080", "blue"},
										{"#807FFE", "purple"}};
	
	
	private ArrayList colorPattern = new ArrayList();
	private int numberShown;
	private Boolean even;
	
	public TestPattern() {
		colorPattern.clear();
		numberShown = -1;
		even = true;
	}
	
	public TestPattern(int length) {
		
		Random rand = new Random();
		
		int lastColor = -1;
		for(int i = 0; i < length; i++)
		{
			int newColor = rand.nextInt(3);
			
			if(newColor != lastColor)
			{
				colorPattern.add(newColor);
				lastColor = newColor;
			}
			else
				i--;
		}
		numberShown = rand.nextInt(8) + 1;
		
		if((numberShown%2)==0)
			even = true;
		else
			even = false;
		
	}
	
	public void newPattern(int length)
	{
		colorPattern.clear();
		
		Random rand = new Random();
		
		int lastColor = -1;
		for(int i = 0; i < length; i++)
		{
			int newColor = rand.nextInt(4);
			
			if(newColor != lastColor)
			{
				colorPattern.add(newColor);
				lastColor = newColor;
			}
			else
				i--;
			
		}
		
		numberShown = rand.nextInt(8)+1;
		
		if((numberShown%2)==0)
			even = true;
		else
			even = false;
		
	}
	
	//returns the pattern
	public ArrayList getPattern()
	{
		return colorPattern;
	}
	
	//returns number
	public String getNumber()
	{
		return numberShown+"";
	}
	
	//returns true if number is even
	public boolean getEven()
	{
		return even;
	}
	
	public String getColorHex(int col)
	{
		return COLORS[(int)colorPattern.get(col)][0];
	}
	
	public String getColorAudioPath(int col)
	{
		return COLORS[(int)colorPattern.get(col)][1];
	}
	
	public int getColorAt(int i)
	{
		return (int)colorPattern.get(i);
	}
	
	public String getColorName(int i)
	{
		return COLORS[(int)colorPattern.get(i)][1];
	}
	
	//given a color in number form (0-3) returns the hex value for it
	public String getColorHex1(int col)
	{
		return COLORS[col][0];
	}
}
