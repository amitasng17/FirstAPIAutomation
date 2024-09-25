package api.utlities;
//1800 482 2800
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

	@DataProvider(name = "Data")
    public Object[][] getAllData() throws IOException {
		 String path=System.getProperty("user.dir")+"//Userdata.xlsx"; 
         MyXls_Reader xlreader=new MyXls_Reader(path); 
         int rowCount=xlreader.getRowCount("Sheet1");
         int colCount=xlreader.getCellCount("Sheet1");
         Object[][] data=new Object[rowCount - 1][colCount];
         for (int i = 1; i < rowCount; i++) {
             for (int j = 0; j < colCount; j++) {
                	data[i - 1][j] = xlreader.getCellData("Sheet1", i, j);
             }
         }
     return data;    
    }
 
	@DataProvider(name = "Usernames")
    public Object[] getUserNames() throws IOException {
		 String path=System.getProperty("user.dir")+"//Userdata.xlsx"; 
         MyXls_Reader xlreader=new MyXls_Reader(path); 
         int rowCount=xlreader.getRowCount("Sheet1");
         Object[] data=new String[rowCount - 1];
         for (int i = 1; i < rowCount; i++) {
                  	data[i - 1] = xlreader.getCellData("Sheet1", i, 1);
             }
     return data;    
    }
    
}	