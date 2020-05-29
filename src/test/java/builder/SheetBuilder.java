package builder;

import model.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.ArrayList;
import java.util.List;

public class SheetBuilder {

    Sheet sheet =new Sheet();
    XSSFSheet xssfSheet;
    public void withSheet(XSSFSheet xssfSheet){
        this.xssfSheet=xssfSheet;
        sheet.setSheetName(xssfSheet.getSheetName());
    }
    
    public SheetBuilder mapValues(){
        
    
        XSSFRow rowhead = xssfSheet.getRow(0);
        rowhead.forEach(
                (cell)->
                        withColumnName(new DataFormatter().formatCellValue(cell))
        );
        
        return this;
    }
    
    public SheetBuilder withColumnName(String columnName){
        sheet.addColumn(columnName);
        XSSFRow rowhead = xssfSheet.getRow(0);
        
        int i=0;
        for(Cell cell:rowhead){
            if(new DataFormatter().formatCellValue(cell).equals(columnName)){
                break;
            }
            i++;
        }
    
        List<String> colummnValue= new ArrayList<String>();
        for(int j=0;j<xssfSheet.getLastRowNum();j++){
        colummnValue.add(new DataFormatter().formatCellValue(xssfSheet.getRow(j).getCell(i)));
        }
        
        
        sheet.mapValues(columnName,colummnValue);
        
       return this;
    }
    
    public  Sheet build(){
        return sheet;
    }

}
