package com.sxm.mobile.pages;

import java.io.File;
import java.io.IOException;

import org.testng.Reporter;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.biff.EmptyCell;
import jxl.read.biff.BiffException;

public class ReadExcel {
	public String readData(String searchString) {

		Cell cell = null;
		String str = null;

		try {

			String filename = System.getProperty("user.dir")
					+ "/src/test/resources/InputReader.xls";			
			Workbook workbook = Workbook.getWorkbook(new File(filename)); // load
																			
								

			Sheet s = workbook.getSheet(0); // confirms to look at sheet 0

			LabelCell lc = s.findLabelCell(searchString);
			int col = lc.getColumn();
			int row = lc.getRow();

			cell = s.getCell(++col, row);

			if (cell.getType() == CellType.LABEL) {
				Reporter.log("cell type is-LABEL",true);
				LabelCell labelCell = (LabelCell) cell;
				str = (String) labelCell.getContents();

			}else if (cell.getType() == CellType.NUMBER) {
				Reporter.log("cell type is-NUMBER",true);
				NumberCell numCell = (NumberCell) cell;
				str = numCell.getContents();

			}else if (cell.getType() == CellType.DATE) {
				Reporter.log("cell type is-DATE",true);
				DateCell dateCell = (DateCell) cell;
				str = dateCell.getContents();

			}else if (cell.getType() == CellType.EMPTY) {
				Reporter.log("cell type is-EMPTY",true);
				EmptyCell emptyCell = (EmptyCell) cell;
				str = emptyCell.getContents();

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.err.println("Null Pointer Exception caught");
			e.printStackTrace();
		}

		return str;
	}
	
	
	 

}