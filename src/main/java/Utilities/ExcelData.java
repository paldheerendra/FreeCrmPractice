package Utilities;

import org.testng.annotations.Test;

public class ExcelData {
	
	@Test
	public void readExcel() {
		 Xls_Reader reader=new Xls_Reader("D:\\Dheeru\\Work\\TestNgTest\\src\\com\\testdata\\TestNGLoginTestData.xlsx");
		 
		 
		 String username=reader.getCellData("LoginTestData", "username" , 2);
		 String password=reader.getCellData("LoginTestData", "password" , 2);
		 System.out.println(username);
		 System.out.println(password);
		}

	

}
