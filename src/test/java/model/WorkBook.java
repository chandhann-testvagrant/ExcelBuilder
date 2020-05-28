package model;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

import java.util.ArrayList;
import java.util.List;

public class WorkBook {
    
    
    public WorkBook(HSSFWorkbook workBook){
    this.workBook=workBook;
    }
    
    private HSSFWorkbook workBook;
    private  HSSFSheet sheet;
    
    public WorkBook inSheet(String sheetName){
        
        sheet=workBook.getSheet(sheetName);
        return this;
    }
    
    public List<String>  getColumnHeaders(){
        
        List<String> values=new ArrayList<String>();
        DataFormatter dataFormatter = new DataFormatter();
    
        HSSFRow headerRow = sheet.getRow(0);
        
        headerRow.forEach(
                (cell)->
                        values.add(dataFormatter.formatCellValue(cell))
        );
        
        return values;
    }
    
    public List<String> getSheets(){
        
        int noOfSheets=workBook.getNumberOfSheets();
        
        List<String> sheets=new ArrayList<String>();
        for(int i=0;i<noOfSheets;i++){
            String name=workBook.getSheetAt(i).getSheetName();
            sheets.add(name);
        }
        return sheets;
    }
    
}
