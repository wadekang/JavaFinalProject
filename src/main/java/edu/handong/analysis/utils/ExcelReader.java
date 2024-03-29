package edu.handong.analysis.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import edu.handong.analysis.datamodel.ExcelType1;
import edu.handong.analysis.datamodel.ExcelType2;
import edu.handong.analysis.datamodel.HSLinkedList;

public class ExcelReader {
	
	public void getDataOfFile1(InputStream is, HSLinkedList<ExcelType1> values, String fileName, ArrayList<String> errorFileName) {
		ExcelType1 excel1 = null;
		String[] cellInfo = new String[7];
		try (InputStream inp = is) {
			Workbook wb = WorkbookFactory.create(inp);
		    Sheet sheet = wb.getSheetAt(0);
		    int rowNum = sheet.getPhysicalNumberOfRows();
		    int rowIndex;
		    for(rowIndex = 1; rowIndex<rowNum; rowIndex++) {
		    	Row row = sheet.getRow(rowIndex);
			    for(int i=0; i<7; i++) {
			    	Cell cell = row.getCell(i);
			    	if (cell == null) {
			    		cellInfo[i] = "";
			    		continue;
//			    		cell = row.createCell(i);
			    	}
			    	CellType type = cell.getCellType();
				    if(type.toString().contentEquals("STRING")) {
				    	cellInfo[i] = cell.getStringCellValue();
				    }
				    else if(type.toString().equals("NUMERIC")){
				    	int n = (int) cell.getNumericCellValue();
				    	cellInfo[i] = Integer.toString(n);
				    }
			    }
			    excel1 = new ExcelType1(fileName, cellInfo);
			    values.insertNode(excel1);
		    }
		} catch (FileNotFoundException e) {
			errorFileName.add(fileName);
			e.getMessage();
		} catch (IOException e) {
			errorFileName.add(fileName);
			e.getMessage();
		}
	}
	
	public void getDataOfFile2(InputStream is, HSLinkedList<ExcelType2> values, String fileName, ArrayList<String> errorFileName) {
		ExcelType2 excel2 = null;
		String[] cellInfo = new String[5];
		try (InputStream inp = is) {
			Workbook wb = WorkbookFactory.create(inp);
		    Sheet sheet = wb.getSheetAt(0);
		    int rowNum = sheet.getPhysicalNumberOfRows();
		    int rowIndex;
		    for(rowIndex = 2; rowIndex < rowNum; rowIndex++) {
		    	Row row = sheet.getRow(rowIndex);
			    for(int i=0; i<5; i++) {
			    	Cell cell = row.getCell(i);
			    	if (cell == null) {
			    		cellInfo[i] = "";
			    		continue;
//			    		cell = row.createCell(i);
			    	}
			    	CellType type = cell.getCellType();
				    if(type.toString().contentEquals("STRING")) {
				    	cellInfo[i] = cell.getStringCellValue();
				    }
				    else if(type.toString().equals("NUMERIC")){
				    	int n = (int) cell.getNumericCellValue();
				    	cellInfo[i] = Integer.toString(n);
				    }
			    }
			    excel2 = new ExcelType2(fileName, cellInfo);
			    values.insertNode(excel2);
		    }
		} catch (FileNotFoundException e) {
			errorFileName.add(fileName);
			e.printStackTrace();
		} catch (IOException e) {
			errorFileName.add(fileName);
			e.getMessage();
		}
	}
}