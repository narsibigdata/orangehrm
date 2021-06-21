package com.orange.helpers;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaders {

	public static FileInputStream file;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static String data;
	public static XSSFRow row = null;
		 
		/*public  void createworkbook() throws IOException
		 
		{
			 
		//To create a new WorkBook with xlsx extension
		 
		XSSFWorkbook wb = new XSSFWorkbook();
		String path
		 
		FileOutputStream TestFile = new FileOutputStream("D:\\LoyaltyCRM\\LoyaltyCRM\\TestData\\Test1.xlsx");
		 
		wb.write(TestFile);
		 
		}
		
		public void createsheet() throws IOException
		{
			XSSFSheet SheetName_LoginPage= wb.createSheet("CRMLogin");
			XSSFSheet SheetName_ContactPage= wb.createSheet("ContactPage");
			XSSFSheet SheetName_SummaryTab =wb.createSheet("SummaryTab");
		}*/
		 
		
	public int GetRowCount(String path,String sheetName) throws IOException {
		file = new FileInputStream(path);
		wb = new XSSFWorkbook(file);
		ws = wb.getSheet(sheetName);
		int rowcount = ws.getLastRowNum()+1;
		return rowcount;
	}

	public Row getRowData(String path, String sheet, int r) throws IOException {
		file = new FileInputStream(path);
		wb = new XSSFWorkbook(file);
		ws = wb.getSheet(sheet);

		Row row = ws.getRow(r);
		return row;
	}

	public String getCellData(String path,String sheet, int r, int c) throws IOException {
		file=new FileInputStream(path);
		wb = new XSSFWorkbook(file);
		ws = wb.getSheet(sheet);
		String data = ws.getRow(r).getCell(c).getStringCellValue();
		return data;

	}

	public double getCellvalue(String path,String sheetName, int r, int c) throws IOException {
		file=new FileInputStream(path);
		wb = new XSSFWorkbook(file);
		 ws = wb.getSheet(sheetName);
		double data = ws.getRow(r).getCell(c).getNumericCellValue();
		return data;

	}
	
	 
	   public int getColumnCount(String path,String sheetName,int i) throws Exception
	   {
		   file=new FileInputStream(path);
		   wb = new XSSFWorkbook(file);
		   ws = wb.getSheet(sheetName);
	     
	       row = ws.getRow(i);
	       
	       
	       int colCount = row.getLastCellNum();
	       return colCount;
	   }
	 public Object[][] testData(String path, String sheetName) throws Exception
	    {
		     file=new FileInputStream(path);
	        Object[][] excelData = null;
	       
	        int rows = GetRowCount(path,sheetName);
	      
	        int columns = getColumnCount(path,sheetName,0);
	                 
	        excelData = new Object[rows-1][columns];
	         
	        for(int i=1; i<rows; i++)
	        {
	        	
	            for(int j=0; j<columns; j++)
	            {
	                excelData[i-1][j] = getCellData(path,sheetName, i, j);
	            }
	             
	        }
	        return excelData;
	    }
	public void setCellData(String path, String shname, int rownum, int colnum, String value) throws IOException {
		try
	    {
			file=new FileInputStream(path);
	        wb=new XSSFWorkbook(file);
	        ws=wb.getSheet(shname);
	        Row row=ws.getRow(rownum);
	        FileOutputStream webdata=new FileOutputStream(path);	        
	        row.createCell(colnum).setCellValue(value);
	        wb.write(webdata);
	        webdata.flush();
	        webdata.close();
	        
	               
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	}
	
}

