package com.sxm.framework.common;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.Cell;
import jxl.CellType;
import jxl.CellView;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Border;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

@SuppressWarnings("deprecation")
public class ExcelUtil {
	private static Workbook WBOOK;
	private static WritableWorkbook WWBCOPY;
	private static WritableSheet SHSHEET;
	private static WritableCellFormat TIMES;
	private static WritableCellFormat TIMESBOLDUNDERLINE;

	public ExcelUtil() {
			try {

				String filepath = "src/test/resources/Report.xls";
				filepath = System.getProperty("user.dir") + "/" + filepath;
				
				WorkbookSettings wbSettings = new WorkbookSettings();

			    wbSettings.setLocale(new Locale("en", "EN"));

			    WWBCOPY = Workbook.createWorkbook(new File(filepath), wbSettings);
			    SHSHEET = WWBCOPY.createSheet("Report", 0);

				/*WBOOK = Workbook.createWorkbook(new File(filepath));
				WWBCOPY = Workbook.createWorkbook(new File(filepath), WBOOK);
				SHSHEET = WWBCOPY.getSheet(0);*/
				createLabel();

			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	private static void createLabel() throws WriteException {
		WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
		TIMES = new WritableCellFormat(times10pt);
		TIMES.setWrap(true);
		WritableFont times10ptBoldUnderline = new WritableFont(
				WritableFont.TIMES, 12, WritableFont.BOLD, false,
				UnderlineStyle.SINGLE);
		CellView cv = new CellView();
		cv.setFormat(TIMES);
		cv.setFormat(TIMESBOLDUNDERLINE);
		addCaption(1, 1, "Test case No");
		addCaption(2, 1, "Test case Name");
		addCaption(3, 1, "Test case Desc");
		addCaption(4, 1, "Test case Result");
	}

	@SuppressWarnings("deprecation")
	public void setValueIntoCell(String strSheetName, int iColumnNumber,
			int iRowNumber, String strData) throws WriteException {
		SHSHEET = WWBCOPY.getSheet(0);
		Label labTemp = null;
		// Create cell font and format
		WritableFont cellFont = null;
		WritableCellFormat cellFormat = null;
		try {
			if (iColumnNumber == 3) {
				if (strData.equalsIgnoreCase("Fail")
						|| strData.equalsIgnoreCase("Skip")) {
					cellFont = new WritableFont(WritableFont.TIMES, 12,
							WritableFont.BOLD);
					cellFont.setColour(Colour.RED);
					cellFormat = new WritableCellFormat(cellFont);
					cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
					// Create the label, specifying content and format
					labTemp = new Label(iColumnNumber, iRowNumber, strData,
							cellFormat);
				} else if (strData.equalsIgnoreCase("Pass")) {
					cellFont = new WritableFont(WritableFont.TIMES, 12,
							WritableFont.BOLD);
					cellFont.setColour(Colour.GREEN);
					cellFormat = new WritableCellFormat(cellFont);
					cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
					// Create the label, specifying content and format
					labTemp = new Label(iColumnNumber, iRowNumber, strData,
							cellFormat);
				}

			} else {
				cellFont = new WritableFont(WritableFont.TIMES, 12);
				cellFont.setColour(Colour.BLACK);
				cellFormat = new WritableCellFormat(cellFont);
				cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
				labTemp = new Label(iColumnNumber, iRowNumber, strData,
						cellFormat);
			}
			// add cell data with format to the sheet
			SHSHEET.addCell(labTemp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void addCaption(int column, int row, String s)
			throws RowsExceededException, WriteException {
		Label label;
		label = new Label(column, row, s, TIMESBOLDUNDERLINE);
		SHSHEET.addCell(label);
	}

	public void closeFile() {
		try {
			System.out.println("after");
			// Closing the writable work book
			WWBCOPY.write();
			WWBCOPY.close();

			// Closing the original work book
			//WBOOK.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * readData(String searchString) method is used for retrieving the data from
	 * excel sheet
	 * 
	 * @param searchString
	 *            String to be searched to get the corresponding value
	 * @return The real value from excel sheet
	 */
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

				LabelCell labelCell = (LabelCell) cell;
				str = (String) labelCell.getContents();

			}

			else if (cell.getType() == CellType.NUMBER) {

				NumberCell numCell = (NumberCell) cell;
				str = numCell.getContents();

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
		return str;
	}
}