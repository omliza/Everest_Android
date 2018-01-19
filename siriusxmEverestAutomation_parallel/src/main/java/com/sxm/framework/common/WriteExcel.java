package com.sxm.framework.common;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.sxm.framework.dto.PerfectoCapability;
import com.sxm.framework.dto.handler.PerfectoCapabilityHandler;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Border;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * @author 312187
 *
 */
/**
 * @author 312187
 *
 */
public class WriteExcel {

    private static WriteExcel writeInstance;
    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;
    private WritableCellFormat footerFormat;  
    private String inputFile;
    private static WritableWorkbook writableworkbook = null;
    private static WritableWorkbook copy = null;
    private static Workbook workbook = null;
    private static Workbook workbookSummary = null;
    
    private static PerfectoCapability perfectocapability = PerfectoCapabilityHandler.getInstance()
            .getPerfectoCapability();

    public static WriteExcel getInstance() {
        if (writeInstance == null) {
            writeInstance = new WriteExcel();
        }
        return writeInstance;
        
    }

    public void setOutputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public WritableSheet getSheet() throws IOException, WriteException, BiffException {
        File file = new File(inputFile);
        workbook = Workbook.getWorkbook(file);
        copy = Workbook.createWorkbook(file, workbook);
        WritableSheet excelSheet = copy.getSheet(0);
        return excelSheet;
    }
    
    public List<WritableSheet> getSheet1() throws IOException, WriteException, BiffException {
        File file = new File(inputFile);
        workbook = Workbook.getWorkbook(file);
        copy = Workbook.createWorkbook(file, workbook);
        List<WritableSheet> sheetList = new ArrayList<WritableSheet>();
        WritableSheet excelSheet = copy.getSheet(0);
        WritableSheet excelSheetSummary = copy.getSheet(1);
        sheetList.add(excelSheet);
        sheetList.add(excelSheetSummary);
        return sheetList;
    }

    /** Create header label of report excel
     * @param sheet
     * @throws WriteException
     */
    private void createLabel(WritableSheet sheet) throws WriteException {
    	System.out.println("In createLable Method");
        // Lets create a times font
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        // Define the cell format
        times = new WritableCellFormat(times10pt);
        // Lets automatically wrap the cells
        times.setWrap(true);

        // create create a bold font with underlines
        WritableFont times10ptBoldUnderline = new WritableFont(
                WritableFont.TIMES, 12, WritableFont.BOLD, false,
                UnderlineStyle.SINGLE);
        times10ptBoldUnderline.setColour(Colour.WHITE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        // Lets automatically wrap the cells
        timesBoldUnderline.setWrap(true);
        timesBoldUnderline.setBackground(Colour.LIGHT_BLUE);
       
        CellView cv = new CellView();
        cv.setFormat(timesBoldUnderline);
        cv.setAutosize(true);

     // Write headers
        addCaption(sheet, 0, 0, "Test Case No");
        addCaption(sheet, 1, 0, "Test Module");
        addCaption(sheet, 2, 0, "Test Case Name");
        addCaption(sheet, 3, 0, "Test Case Description");
        addCaption(sheet, 4, 0, "Test case Result");
        addCaption(sheet, 5, 0, "Execution Time");
        addCaption(sheet, 6, 0, "Exception");
        addCaption(sheet, 7, 0, "Rca");
        addCaption(sheet, 8, 0, "Test Method Name");
        addCaption(sheet, 9, 0, "DeviceModel");
        addCaption(sheet, 10, 0, "DeviceID");
        addCaption(sheet, 11, 0, "PerfectoReportLink");
    }
    

    /**Create Summary Excel
     * @param sheet
     * @throws RowsExceededException
     * @throws WriteException
     */
    private void createSummaryLabel(WritableSheet sheet) throws RowsExceededException, WriteException {
    	 CellView cv = new CellView();
         cv.setFormat(timesBoldUnderline);
         cv.setAutosize(true);
         //Excel headers
         addCaption(sheet, 0, 0, "Test Module");
         addCaption(sheet, 1, 0, "Pass Count");
         addCaption(sheet, 2, 0, "Fail Count");
         addCaption(sheet, 3, 0, "Skip Count");
         addCaption(sheet, 4, 0, "Total Test Cases");
         addCaption(sheet, 5, 0, "Perfecto Report Link");

         //defining different modules
         addLabel(sheet, 0, 1, "Login");
         addLabel(sheet, 0, 2, "Home");
         addLabel(sheet, 0, 3, "Categories");
         addLabel(sheet, 0, 4, "Favorites");
         addLabel(sheet, 0, 5, "NowPlaying");
         addLabel(sheet, 0, 6, "HowardSternCategory");
         addLabel(sheet, 0, 7, "Profile");
         addLabel(sheet, 0, 8, "Search");
         addLabel(sheet, 0, 9, "MiniBar");

         
         //populate last Total row code
         addCaption(sheet, 0, 12, "Total Count");
         Formula sumPassTotal = new Formula(1, 12, "SUM(B2:B10)");
         sheet.addCell(sumPassTotal);
         Formula sumFailTotal = new Formula(2, 12, "SUM(C1:C10)");
         sheet.addCell(sumFailTotal);
         Formula sumSkipTotal = new Formula(3, 12, "SUM(D1:D10)");
         sheet.addCell(sumSkipTotal);
         Formula sumAllTotal = new Formula(4, 12, "SUM(E1:E10)");
         sheet.addCell(sumAllTotal);
         
         
         //populate Total test count column
         Formula sum = new Formula(4, 1, "SUM(B2:D2)");
         sheet.addCell(sum);
         Formula sum1 = new Formula(4, 2, "SUM(B3:D3)");
         sheet.addCell(sum1);
         Formula sum2 = new Formula(4, 3, "SUM(B4:D4)");
         sheet.addCell(sum2);
         Formula sum3 = new Formula(4, 4, "SUM(B5:D5)");
         sheet.addCell(sum3);
         Formula sum4 = new Formula(4, 5, "SUM(B6:D6)");
         sheet.addCell(sum4);
         Formula sum46 = new Formula(4, 6, "SUM(B7:D7)");
         sheet.addCell(sum46);
         Formula sum47 = new Formula(4, 7, "SUM(B8:D8)");
         sheet.addCell(sum47);
         Formula sum48 = new Formula(4, 8, "SUM(B9:D9)");
         sheet.addCell(sum48);        
         Formula sum49= new Formula(4, 9, "SUM(B10:D10)");
         sheet.addCell(sum49);
         Formula sum410 = new Formula(4, 10, "SUM(B11:D11)");
         sheet.addCell(sum410);
         Formula sum411 = new Formula(4, 11, "SUM(B12:D12)");
         sheet.addCell(sum411);
         
         
         //populate device details
         addFooter(sheet, 0, 16, "Model Name:");
         addFooter(sheet, 1, 16, perfectocapability.getModel());
         
         addFooter(sheet, 0, 17, "Device Id:");
         addFooter(sheet, 1, 17, perfectocapability.getDeviceName());
         
         addFooter(sheet, 0, 18, "Os:");
         addFooter(sheet, 1, 18, perfectocapability.getOs());
         
         addFooter(sheet, 0, 19, "Os Version:");
         addFooter(sheet, 1, 19, perfectocapability.getOsVersion());
         
         addFooter(sheet, 0, 20, "Execution Time:");
         SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy HH:mm:ss"); 
         addFooter(sheet, 1, 20, formatter.format(new Date())); 	
         
         addFooter(sheet, 0, 21, "Build No:");
         String appName = perfectocapability.getApp();
         addFooter(sheet, 1, 21, appName.substring(appName.indexOf("/")+1));
         
         addFooter(sheet, 0, 22, "User");
         appName = perfectocapability.getUser();
         addFooter(sheet, 1, 22, appName);
         
         addFooter(sheet, 0, 23, "App Package");
         appName = perfectocapability.getAppPackage();
         addFooter(sheet, 1, 23, appName);
         
         addFooter(sheet, 0, 24, "Network Profile");
         appName = perfectocapability.getNetworkProfile();
         addFooter(sheet, 1, 24, appName);
    }

    public static void createContent(WritableSheet sheet, int iColumnNumber,
            int iRowNumber, String strData) throws WriteException,
            RowsExceededException {

        Label labTemp = null;
        // Create cell font and format
        WritableFont cellFont = null;
        WritableCellFormat cellFormat = null;
        try {
            if (iColumnNumber == 4) {
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
            sheet.addCell(labTemp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCaption(WritableSheet sheet, int column, int row, String s)
            throws RowsExceededException, WriteException {
        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);
    }

    public void addNumber(WritableSheet sheet, int column, int row,
            Integer integer) throws WriteException, RowsExceededException {
        Number number;
        number = new Number(column, row, integer, times);
        sheet.addCell(number);
    }

    public void addLabel(WritableSheet sheet, int column, int row, String s)
            throws WriteException, RowsExceededException {
        Label label;
        label = new Label(column, row, s, times);
        sheet.addCell(label);
    }  
    
    public void addFooter(WritableSheet sheet, int column, int row, String s)
            throws RowsExceededException, WriteException {
    	// format
    	WritableFont footerFont = new WritableFont(
                WritableFont.TIMES, 10, WritableFont.BOLD, true);
    	footerFont.setColour(Colour.GREEN);
    	footerFormat = new WritableCellFormat(footerFont);
        // Lets automatically wrap the cells
    	//footerFormat.setWrap(true);
        
    	
        Label label;
        label = new Label(column, row, s, footerFormat);
        sheet.addCell(label);
    }
    
    public static void closeFile() {
        try {
        	copy.write();
            copy.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (WriteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**Create Test Report Excel
	 * @throws IOException
	 * @throws WriteException
	 */
	public void creatWorkbook() throws IOException, WriteException {
		File file = new File(inputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        writableworkbook = Workbook.createWorkbook(file, wbSettings);
        writableworkbook.createSheet("Test Report", 0);
        writableworkbook.createSheet("Test Summary", 1);
        
        //Create Label for test report excel
        WritableSheet excelSheet = writableworkbook.getSheet(0);
        createLabel(excelSheet);
        
        //Create Label for test summary excel
        WritableSheet excelSheetSummary = writableworkbook.getSheet(1);
        createSummaryLabel(excelSheetSummary);
        		
        writableworkbook.write();
        writableworkbook.close();
	}
	public void createLatestReport() throws IOException, BiffException, WriteException {
		System.out.println("Final report is generating...");
	//	String finalReportName = "AndroidEverest_" + LocalDateTime.now() + "_FinalTestReport.xls"; 
		String finalReportName = "AndroidEverest_FinalTestReport.xls";
		String finalFilepath = "src/test/resources/testreports/" + finalReportName;
		finalFilepath = System.getProperty("user.dir") + "/" + finalFilepath;
		System.out.println("Final file path = " + finalFilepath);
		
		File finalFile = new File(finalFilepath);
        //WorkbookSettings wbSettings = new WorkbookSettings();
        //wbSettings.setLocale(new Locale("en", "EN"));
        //WritableWorkbook finalWritableWorkbook = Workbook.createWorkbook(finalFile, wbSettings);
		
		
		File file = new File(inputFile);
        workbook = Workbook.getWorkbook(file);
        
        WritableWorkbook finalWritableWorkbook = Workbook.createWorkbook(finalFile, workbook);
        
        finalWritableWorkbook.write();
        finalWritableWorkbook.close();

		
	}
}