
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;

import java.io.FileOutputStream;
import java.security.PrivateKey;
import java.util.Iterator;
import java.util.List;

public class ExcelBuilder {

String fileName;
    ExcelBuilder(String fileName){
        this.fileName=fileName;
        
    }
    
    
    private HSSFWorkbook workBook = new HSSFWorkbook();
    
    private HSSFSheet sheet;
    
    
    public ExcelBuilder addSheet(String sheetName){
    
        workBook.createSheet(sheetName);
        workBook.getSheet(sheetName).createRow((short)0);
        return this;
    }
    
    public ExcelBuilder addSheets(List<String> sheetNames){
    
        sheetNames.forEach((sheet)-> addSheet(sheet));
        
        return this;
    }

    public void appendColumnHeader(String sheetName,String headerName){
        
        int lastCellNum = workBook.getSheet(sheetName).getRow(0).getLastCellNum();
        HSSFCellStyle font = workBook.createCellStyle();
        HSSFFont font1 = workBook.createFont();
        font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setFont(font1);
        
        HSSFRow rowhead = workBook.getSheet(sheetName).getRow(0);
        HSSFCell cell = rowhead.createCell(lastCellNum);
        cell.setCellStyle(font);
        cell.setCellValue(headerName);
    }
    
    public void addColumnHeaders(String sheetName,List<String> headerNames){
    
        Iterator<String> names = headerNames.iterator();
        while(names.hasNext()){
            appendColumnHeader(sheetName,names.next());
        }
    }
    
    public ExcelBuilder inSheet(String sheetName){
        
        if(workBook.getSheet(sheetName)==null){
            addSheet(sheetName);
        }
        
        sheet= workBook.getSheet(sheetName);
        return this;
    }
    
    public void appendColumnHeader(String headerName){
    
    
        HSSFRow rowhead = sheet.getRow(0);
        
        HSSFCellStyle font = workBook.createCellStyle();
        
        HSSFFont fontStyle = workBook.createFont();
        fontStyle.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setFont(fontStyle);
    
        int lastRowColumnIndex=rowhead.getLastCellNum();
        if(lastRowColumnIndex==-1)
        {
            lastRowColumnIndex=0;
        }
        
        HSSFCell cell = rowhead.createCell(lastRowColumnIndex);
        cell.setCellStyle(font);
        cell.setCellValue(headerName);
        
    }
    
    public void addColumnHeaders(List<String> headerNames){
        headerNames.forEach((headerName)->appendColumnHeader(headerName));
    }
    
    public WorkBook build(){
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(fileName);
       
            workBook.write(fileOut);
            
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {}
        
        return new WorkBook(workBook);
    }
    
    

}
