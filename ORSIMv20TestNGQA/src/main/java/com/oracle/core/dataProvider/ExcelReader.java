package com.oracle.core.dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private static  FileInputStream ExcelInputStreamFile;

	private static  XSSFSheet ExcelWSheet;

	private static  XSSFWorkbook ExcelWBook;

	private  XSSFRow row;

	private static XSSFCell Cell;



	static DataFormatter formatter;


	public static Object[][] loadExcel( String FilePath, String SheetName) throws Exception{

		File file=new File(FilePath);

		ExcelInputStreamFile=new FileInputStream(file);

		ExcelWBook = new XSSFWorkbook(ExcelInputStreamFile);

		ExcelWSheet = ExcelWBook.getSheet(SheetName);

		ExcelWBook.close();

		formatter = new DataFormatter();
		int lastRowNum = ExcelWSheet.getLastRowNum() ;
		int lastCellNum = ExcelWSheet.getRow(0).getLastCellNum();


		Object[][] obj = new Object[lastRowNum][1];

		for (int i = 0; i < lastRowNum; i++) {
			Map<String, String> datamap = new HashMap<String,String>();
			for (int j = 0; j < lastCellNum; j++) {
				datamap.put(formatter.formatCellValue(ExcelWSheet.getRow(0).getCell(j)), formatter.formatCellValue(ExcelWSheet.getRow(i+1).getCell(j)));
			}
			obj[i][0] = datamap;

		}
		return  obj;

	}
	
	public static void enterDataInExcel(String FilePath, String SheetName, String ColumnName, String ColumnValue) throws Exception{
		
		File file=new File(FilePath);

		ExcelInputStreamFile=new FileInputStream(file);

		ExcelWBook = new XSSFWorkbook(ExcelInputStreamFile);

		ExcelWSheet = ExcelWBook.getSheet(SheetName);

		formatter = new DataFormatter();
		int lastRowNum = ExcelWSheet.getLastRowNum() ;
		int lastCellNum = ExcelWSheet.getRow(0).getLastCellNum();
			
			for (int i = 0; i <= lastRowNum; i++) {
			
				for (int j = 0; j < lastCellNum; j++) {
					if(ExcelWSheet.getRow(0).getCell(j).getStringCellValue().equals(ColumnName) && i>0){
					
						ExcelWSheet.getRow(i).createCell(j).setCellValue(ColumnValue);
						break;
					}
				}					
			}

			ExcelInputStreamFile.close();
			FileOutputStream outFile =new FileOutputStream(new File(FilePath));
			ExcelWBook.write(outFile);
			outFile.close();
			System.out.println("end");
}

	public static void enterDataInExcelSheets(String FilePath,  String ColumnName, String ColumnValue) throws Exception{

		File file=new File(FilePath);
	
		ExcelInputStreamFile=new FileInputStream(file);
	
		ExcelWBook = new XSSFWorkbook(ExcelInputStreamFile);
	
		for(int SheetName=0; SheetName < ExcelWBook.getNumberOfSheets(); SheetName++ ) {
			ExcelWSheet = ExcelWBook.getSheetAt(SheetName);
			System.out.println(ExcelWSheet.getSheetName());
			formatter = new DataFormatter();
			int lastRowNum = ExcelWSheet.getLastRowNum() ;
			int lastCellNum = ExcelWSheet.getRow(0).getLastCellNum();
	
			for (int i = 0; i <= lastRowNum; i++) {
	
				for (int j = 0; j < lastCellNum; j++) {
					//System.out.println("Actual Value Cell Value: "+formatter.formatCellValue(ExcelWSheet.getRow(0).getCell(j)) );
					
					if(formatter.formatCellValue(ExcelWSheet.getRow(0).getCell(j)).equals(ColumnName) && i>0){
						
						
						ExcelWSheet.getRow(i).createCell(j).setCellValue(ColumnValue);
						break;
					}
				}					
			}
		}
		ExcelInputStreamFile.close();
		FileOutputStream outFile =new FileOutputStream(new File(FilePath));
		ExcelWBook.write(outFile);
		outFile.close();
		System.out.println("end");
	}
	
	/*public static void main(String arg[]) throws Exception {
		
		ExcelReader.enterDataInExcelSheets("src\\test\\resources\\TestData\\CardDefinition.xlsx", "CardSerialNumberPrefix", String.valueOf("12345"));
	}*/
}
	



