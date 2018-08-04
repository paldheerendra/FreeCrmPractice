package Utilities;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelDataProvider {
	
	@Test
	public static Object readExcel() {
		 Xls_Reader reader=new Xls_Reader("D:\\Dheeru\\Work\\TestNgTest\\src\\com\\testData\\TestNGLoginTestData.xlsx");
		 
		 int rowCount=reader.getRowCount("LoginTestData");
		 int colCount=reader.getColumnCount("LoginTestData");
		 
		 Object data[][]=new Object[rowCount][colCount];
		 for(int rowNum=2; rowNum <= rowCount; rowNum++)
		 {
			 
		 String username=reader.getCellData("LoginTestData", "username" , rowNum);
		 String password=reader.getCellData("LoginTestData", "password" , rowNum);
		 //System.out.println(username +" And  "+ password);
		 
		 for(int i=1; i<rowNum;i++)
		 {
		 data[rowNum][i] =username;
		 
		 data[rowNum][i++] =password;
		 }
		 
		
		 }
		 return data;
		 
		}

	
	@DataProvider
	public Iterator <Object [][]> getData() {
		
		Object data[][]=new Object[3][2];
		ArrayList <Object [][]> testData=(ArrayList<Object[][]>) ExcelDataProvider.readExcel();
		
		
		return testData.iterator();
		
	}
	
	@Test(dataProvider="getData")
	public void loginApp(String username, String password) {
		
		System.out.println(username + "   And   " + password);
		
		
	}

}
