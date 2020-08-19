import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.*;
import java.sql.*;

import org.apache.log4j.BasicConfigurator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ViewDataScreen extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public ViewDataScreen() {
		BasicConfigurator.configure();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, main.width, main.height);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JPanel TopPanel = new JPanel();
		contentPane.add(TopPanel, "2, 2, fill, fill");
		TopPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("227px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("1665px"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("982px"),}));
		
		JPanel SelectionPanel = new JPanel();
		TopPanel.add(SelectionPanel, "2, 2, fill, fill");
		SelectionPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(100dlu;min):grow"),},
			new RowSpec[] {
				RowSpec.decode("4dlu:grow"),
				RowSpec.decode("default:grow"),
				RowSpec.decode("4dlu:grow"),}));
		
		JComboBox SelectionComboBox = new JComboBox();
		SelectionComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		SelectionComboBox.setModel(new DefaultComboBoxModel(new String[] {"Participant Number", "Primary Language", "Secondary Language", "Ethnicity", "Age", "Grade", "City", "State"}));
		SelectionComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				
				switch ((String)SelectionComboBox.getSelectedItem()) {
					case "Ethnicity":
						try
						{
							Connection conn = DriverManager.getConnection(main.connPath);
							
							Statement s = conn.createStatement();
							
							
							String query = "SELECT Ethnicity.EthnicityValue AS EthnicityV, ROUND(AVG(CAST(VisualColorScore AS FLOAT)), 2) AS vColor, "+
									"ROUND(AVG(CAST(VisualNumberScore AS FLOAT)), 2) AS vNum, ROUND(AVG(CAST(AudioColorScore AS FLOAT)), 2) AS aColor, ROUND(AVG(CAST(AudioNumberScore AS FLOAT)), 2) AS aNum " + 
									"FROM ParticipantScores " + 
									"INNER JOIN Participant ON ParticipantScores.FK_Participant = Participant.ParticipantNumber " + 
									"INNER JOIN Ethnicity ON Participant.FK_Ethnicity = Ethnicity.ID " + 
									"GROUP BY EthnicityValue " + 
									"ORDER BY EthnicityValue ";
							 
							// TableModel definition
							String[] tableColumnsName = {"Ethnicity","Visual Color Score","Visual Number Score","Audio Color Score","Audio Number Score"};
							DefaultTableModel aModel = (DefaultTableModel) table.getModel();
							aModel.setColumnIdentifiers(tableColumnsName);

							// the query
							ResultSet rs = s.executeQuery(query);

							// Loop through the ResultSet and transfer in the Model
							java.sql.ResultSetMetaData rsmd = rs.getMetaData();
							int colNo = rsmd.getColumnCount();
							while(rs.next())
							{
								Object[] objects = new Object[colNo];
								for(int i=0;i<colNo;i++)
								{
									objects[i]=rs.getObject(i+1);
								}
								aModel.addRow(objects);
							}
							table.setModel(aModel);
							
						}
						catch(Exception e)
						{
							System.out.println("UpdateComboBox");
							System.out.println(e);
						}
						break;
					case "Age":
						try
						{
							Connection conn = DriverManager.getConnection(main.connPath);
							
							Statement s = conn.createStatement();
							
							String query = "SELECT Age, ROUND(AVG(CAST(VisualColorScore AS FLOAT)), 2) AS vColor, "+
									"ROUND(AVG(CAST(VisualNumberScore AS FLOAT)), 2) AS vNum, ROUND(AVG(CAST(AudioColorScore AS FLOAT)), 2) AS aColor, ROUND(AVG(CAST(AudioNumberScore AS FLOAT)), 2) AS aNum "+
									"FROM ParticipantScores " + 
									"GROUP BY Age " + 
									"ORDER BY Age ";						
							
							// TableModel definition
							String[] tableColumnsName = {"Age","Visual Color Score","Visual Number Score","Audio Color Score","Audio Number Score"};
							DefaultTableModel aModel = (DefaultTableModel) table.getModel();
							aModel.setColumnIdentifiers(tableColumnsName);

							// the query
							ResultSet rs = s.executeQuery(query);

							// Loop through the ResultSet and transfer in the Model
							java.sql.ResultSetMetaData rsmd = rs.getMetaData();
							int colNo = rsmd.getColumnCount();
							while(rs.next())
							{
								Object[] objects = new Object[colNo];
								for(int i=0;i<colNo;i++)
								{
									objects[i]=rs.getObject(i+1);
								}
								aModel.addRow(objects);
							}
							table.setModel(aModel);
						}
						catch(Exception e)
						{
							System.out.println("UpdateComboBox");
							System.out.println(e);
						}
						break;
					default:
						
				
				}
			}
		});
		SelectionPanel.add(SelectionComboBox, "2, 2, fill, default");
		
		JScrollPane scrollPane = new JScrollPane();
		TopPanel.add(scrollPane, "4, 2, fill, fill");
		
		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 22));
		table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 22));
		table.setRowHeight(40);
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		JPanel BottomPanel = new JPanel();
		contentPane.add(BottomPanel, "2, 4, fill, fill");
		BottomPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("4dlu:grow"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("4dlu:grow"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("4dlu:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("4dlu:grow"),}));
		
		JButton ExportButtom = new JButton("Export to Excel");
		ExportButtom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ExportButtom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				export();
				
				JFrame parent = new JFrame();
				JOptionPane.showMessageDialog(parent, "Export Complete");
			}
		});
		BottomPanel.add(ExportButtom, "2, 2");
		
		JButton BackButton = new JButton("Back");
		BackButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.makeScreen(1);
			}
		});
		BottomPanel.add(BackButton, "4, 2");
	}

	
	public void export() {
        try (Connection connection = DriverManager.getConnection(main.connPath)) {
            String sql = "SELECT ParticipantNumber, EthnicityValue, PLang.LanguageValue AS PrimaryLanguage, SLang.LanguageValue AS SecondaryLanguage, Age, Grade, City, State, DateOfTest, VisualColorScore, "+
            		"VisualNumberScore, AudioColorScore, AudioNumberScore FROM ParticipantScores " + 
            		"INNER JOIN Participant ON Participant.ParticipantNumber = FK_Participant  " + 
            		"INNER JOIN Ethnicity ON FK_Ethnicity = Ethnicity.ID " + 
            		"INNER JOIN Language AS PLang On FK_PrimaryLanguage = PLang.ID " + 
            		"LEFT JOIN Language AS SLang On FK_SecondaryLanguage = SLang.ID;";
 
            Statement statement = connection.createStatement();
 
            ResultSet result = statement.executeQuery(sql);
 
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("SpanScores");
 
            writeHeaderLine(sheet);
 
            writeDataLines(result, workbook, sheet);
 
            FileOutputStream outputStream = new FileOutputStream(main.excelPath);
            workbook.write(outputStream);
            workbook.close();
 
            statement.close();
            connection.close();
            outputStream.close();
 
        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
    }
 
    private void writeHeaderLine(XSSFSheet sheet) {
        Row headerRow = sheet.createRow(0);
 
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Participant Number");
 
        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Ethnicity");
 
        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Primary Language");
 
        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Secondary Language");
 
        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("Age");
        
        headerCell = headerRow.createCell(5);
        headerCell.setCellValue("Grade");
        
        headerCell = headerRow.createCell(6);
        headerCell.setCellValue("City");
        
        headerCell = headerRow.createCell(7);
        headerCell.setCellValue("State");
        
        headerCell = headerRow.createCell(8);
        headerCell.setCellValue("Date of Test");
        
        headerCell = headerRow.createCell(9);
        headerCell.setCellValue("Visual Color Score");
        
        headerCell = headerRow.createCell(10);
        headerCell.setCellValue("Visual Number Score");
        
        headerCell = headerRow.createCell(11);
        headerCell.setCellValue("Audio Number Score");
        
        headerCell = headerRow.createCell(12);
        headerCell.setCellValue("Audio Number Score");
    }
 
    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
            XSSFSheet sheet) throws SQLException {
        int rowCount = 1;
 
        while (result.next()) {
            int partNum = result.getInt("ParticipantNumber");
            String eth = result.getString("EthnicityValue");
            String pLang = result.getString("PrimaryLanguage");
            String sLang = (result.getString("SecondaryLanguage") != null) ? result.getString("SecondaryLanguage") : "(none)";
            int age = result.getInt("Age");
            int grade = result.getInt("Grade");
            String city = result.getString("City");
            String state = result.getString("State");
            Timestamp dateOfTest = result.getTimestamp("DateOfTest");
            int vcScore = result.getInt("VisualColorScore");
            int vnScore = result.getInt("VisualNumberScore");
            int acScore = result.getInt("AudioColorScore");
            int anScore = result.getInt("AudioNumberScore");

            Row row = sheet.createRow(rowCount++);
 
            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(partNum);
 
            cell = row.createCell(columnCount++);
            cell.setCellValue(eth);
            
            cell = row.createCell(columnCount++);
            cell.setCellValue(pLang);
            
            cell = row.createCell(columnCount++);
            cell.setCellValue(sLang);
            
            cell = row.createCell(columnCount++);
            cell.setCellValue(age);
            
            cell = row.createCell(columnCount++);
            cell.setCellValue(grade);
            
            cell = row.createCell(columnCount++);
            cell.setCellValue(city);
            
            cell = row.createCell(columnCount++);
            cell.setCellValue(state);
            
            cell = row.createCell(columnCount++);
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("MM/dd/yyyy"));
            cell.setCellStyle(cellStyle);
            cell.setCellValue(dateOfTest);
            
            cell = row.createCell(columnCount++);
            cell.setCellValue(vcScore);
            
            cell = row.createCell(columnCount++);
            cell.setCellValue(vnScore);
            
            cell = row.createCell(columnCount++);
            cell.setCellValue(acScore);
            
            cell = row.createCell(columnCount++);
            cell.setCellValue(anScore);
        }
    }
}
