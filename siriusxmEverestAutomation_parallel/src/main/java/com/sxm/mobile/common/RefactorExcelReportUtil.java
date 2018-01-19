package com.sxm.mobile.common;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Border;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class RefactorExcelReportUtil {

    private static RefactorExcelReportUtil writeInstance;
    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;
    private String inputFile;
    private static WritableWorkbook workbook;

    public static RefactorExcelReportUtil getInstance() {
        if (writeInstance == null) {
            writeInstance = new RefactorExcelReportUtil();
        }
        return writeInstance;
    }

    public void setOutputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public WritableSheet getSheet() throws IOException, WriteException {
        File file = new File(inputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("en", "EN"));

        workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Report", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        createLabel(excelSheet);

        return excelSheet;

    }

    private void createLabel(WritableSheet sheet) throws WriteException {
        // Lets create a times font
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        // Define the cell format
        times = new WritableCellFormat(times10pt);
        // Lets automatically wrap the cells
        times.setWrap(true);

        // create create a bold font with unterlines
        WritableFont times10ptBoldUnderline = new WritableFont(
                WritableFont.TIMES, 10, WritableFont.BOLD, false,
                UnderlineStyle.SINGLE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        // Lets automatically wrap the cells
        timesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);
        cv.setAutosize(true);

        // Write a few headers
        addCaption(sheet, 1, 0, "S.No");
        addCaption(sheet, 2, 0, "Page Name");
        addCaption(sheet, 3, 0, "Reference UX");
        addCaption(sheet, 4, 0, "UI Control Name");
        
        addCaption(sheet, 5, 0, "Dimension");
        
        addCaption(sheet, 6, 0, "Expected Dimension %");

        addCaption(sheet, 7, 0, "Actual Dimension %");

    }

    public static void createContent(WritableSheet sheet, int iColumnNumber,
            int iRowNumber, String strData) throws WriteException,
            RowsExceededException {

        Label labTemp = null;
        // Create cell font and format
        WritableFont cellFont = null;
        WritableCellFormat cellFormat = null;
        try {
            if (iColumnNumber == 7) {        
                    cellFont = new WritableFont(WritableFont.TIMES, 12,
                            WritableFont.BOLD);
                    cellFont.setColour(Colour.RED);
                    cellFormat = new WritableCellFormat(cellFont);
                    cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
                    // Create the label, specifying content and format
                    labTemp = new Label(iColumnNumber, iRowNumber, strData,
                            cellFormat);

            } else {
                cellFont = new WritableFont(WritableFont.TIMES, 12);
                cellFont.setColour(Colour.BLACK);
                cellFormat = new WritableCellFormat(cellFont);
                cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
                labTemp = new Label(iColumnNumber, iRowNumber, strData,
                        cellFormat);
            }
            // add cell data with format to the sheet
            sheet.addCell(labTemp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addCaption(WritableSheet sheet, int column, int row, String s)
            throws RowsExceededException, WriteException {
        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);
    }

    private void addNumber(WritableSheet sheet, int column, int row,
            Integer integer) throws WriteException, RowsExceededException {
        Number number;
        number = new Number(column, row, integer, times);
        sheet.addCell(number);
    }

    private void addLabel(WritableSheet sheet, int column, int row, String s)
            throws WriteException, RowsExceededException {
        Label label;
        label = new Label(column, row, s, times);
        sheet.addCell(label);
    }

    public static void main(String[] args) throws WriteException, IOException {
        RefactorExcelReportUtil test = new RefactorExcelReportUtil();
        test.setOutputFile("D:/SXM/Report.xls");
        WritableSheet excelSheet = test.getSheet();
        createContent(excelSheet, 1, 2, "123");
        createContent(excelSheet, 2, 2, "UIXXXXX");
        createContent(excelSheet, 3, 2, "Width");
        createContent(excelSheet, 4, 2, "65%");
        createContent(excelSheet, 5, 2, "85%");
        closeFile();
        System.out
                .println("Please check the result file under c:/temp/lars.xls ");
    }

    public static void closeFile() {
        try {
            workbook.write();
            workbook.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (WriteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}