package com.DataDrivenRead;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class read {

 public void particularData() throws IOException {
  
	 File f = new File("C:\\Users\\tvija\\eclipse-workspace\\maven1\\excelfolder\\Book.xlsx");
	 
	 FileInputStream fb = new FileInputStream(f);
	 
	 Workbook wb = new XSSFWorkbook(fb);
	 
	 //Sheet sheet = wb.getSheet("Details");
	 
	// sheet.getRow()
	 
	 
	
}
}