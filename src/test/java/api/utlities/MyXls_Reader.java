package api.utlities;



import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;


import java.io.*;
import java.util.Calendar;


public class MyXls_Reader {
	public static String filename ;//= System.getProperty("user.dir")+"\\src\\config\\testcases\\TestData.xlsx";
	public  String path;
	public  FileInputStream fis = null;
	public  FileOutputStream fo =null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row   =null;
	private XSSFCell cell = null;
	
	public MyXls_Reader(String path) {
		
		this.path=path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			//sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	// returns the row count in a sheet
	public int getRowCount(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else{
		sheet = workbook.getSheetAt(index);
		int number=sheet.getLastRowNum()+1;
		return number;
		}
	}	
	
		public int getCellCount(String sheetName){
			int index = workbook.getSheetIndex(sheetName);
			if(index==-1)
				return 0;
			else{
			sheet = workbook.getSheetAt(index);
			row=sheet.getRow(1);
			int cellCount=row.getLastCellNum();
			return cellCount;
			}
		 }
	
			public String getCellData(String sheetName, int rowNum, int colNum) {
				int index = workbook.getSheetIndex(sheetName);

				sheet = workbook.getSheetAt(index);
				row = sheet.getRow(rowNum);
				cell = row.getCell(colNum);

				DataFormatter formatter = new DataFormatter();
				String data = null;
				try {
					data = formatter.formatCellValue(cell);
				} catch (Exception e) {
				}
				return data;
			}
			
			public void setCellData(String sheetName, int rowNum, int colNum,String data) throws IOException {
				File xlfile=new File(path);//if file not exists then create new file
				if(!xlfile.exists())
				{
				  workbook=new XSSFWorkbook();
				  fo=new FileOutputStream(path);
				  workbook.write(fo);
				}
				 fis=new FileInputStream(path);
				 workbook=new XSSFWorkbook(fis);
				 
				 if (workbook.getSheetIndex(sheetName)==-1)//if sheet not exists create sheet
					 workbook.createSheet();
				  sheet=workbook.getSheet(sheetName);
				  
				  if(sheet.getRow(rowNum)==null)//if row not exists create row
					  sheet.createRow(rowNum);
				  row=sheet.getRow(rowNum);
				  
				  cell.getRow().createCell(colNum);
				  cell.setCellValue(data);
				  fo=new FileOutputStream(path);
				  workbook.write(fo);
				  fo.close();
				
			}
		 
	
}
