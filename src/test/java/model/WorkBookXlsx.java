package model;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class WorkBookXlsx {
    
    public WorkBookXlsx(XSSFWorkbook workBook){
    this.workBook=workBook;
    }
    
    public WorkBookXlsx(String workBookName){
    
        FileInputStream fis=null;
        try {
            fis=new FileInputStream(new File(workBookName));
            this.workBook= new XSSFWorkbook(fis);
            
        } catch (IOException e) {
    
            e.printStackTrace();
            Assert.fail("unable to read excel");
        }
    }
    
    
    private XSSFWorkbook workBook;
    private XSSFSheet sheet;
    
    public WorkBookXlsx inSheet(String sheetName){
        
        sheet=workBook.getSheet(sheetName);
        return this;
    }
    
    public List<String>  getColumnHeaders(){
        
        List<String> values=new ArrayList<String>();
        DataFormatter dataFormatter = new DataFormatter();
    
        XSSFRow headerRow = sheet.getRow(0);
        
        headerRow.forEach(
                (cell)->
                        values.add(dataFormatter.formatCellValue(cell))
        );
        
        return values;
    }
    
    public List<String> getSheetNames(){
        
        int noOfSheets=workBook.getNumberOfSheets();
        
        List<String> sheets=new ArrayList<String>();
        for(int i=0;i<noOfSheets;i++){
            String name=workBook.getSheetAt(i).getSheetName();
            sheets.add(name);
        }
        return sheets;
    }
    
    public List<XSSFSheet> getSheets(){
        
        int noOfSheets=workBook.getNumberOfSheets();
        
        List<XSSFSheet> sheets=new ArrayList<XSSFSheet>();
        for(int i=0;i<noOfSheets;i++){
            XSSFSheet name=workBook.getSheetAt(i);
            sheets.add(name);
        }
        
        return sheets;
    }
}
