package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// self created class others are from Naveen

public class ExcelReader {
	
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static File src;

	
	public static Object[][] getData() throws IOException
	{
		src= new File("C:\\Users\\Mohsin\\Dheeru\\Work\\FreeCRMPractice\\src\\main\\java\\testData\\TestNGLoginTestData.xlsx");
		
		FileInputStream fis=new FileInputStream(src);
		
		
		wb=new XSSFWorkbook(fis);
		sheet=wb.getSheet("LoginTestData");
		
				
		int rowNum=sheet.getLastRowNum();
		int colNum=sheet.getRow(0).getLastCellNum();
				
		Object[][] data=new Object[rowNum][colNum];
		for(int i=1;i<=rowNum;i++) {
		
		String username= sheet.getRow(i).getCell(0).getStringCellValue();
		String password= sheet.getRow(i).getCell(1).getStringCellValue();
		
		data[0][0]= username;
		data[0][1]=password;
		System.out.println(data[0][0]);
		System.out.println(data[0][1]);
		
		}
		return data;
				
		}
	
	public static void main(String args[]) throws IOException {
		getData();
		
	}
	
	
}
