package suite;

import sheet.BUY_A_ABOVE_X_GET_DISCOUNT;
import builder.ExcelBuilder;
import com.creditdatamw.zerocell.Reader;
import model.WorkBook;
import model.WorkBookXlsx;
import org.apache.commons.io.FileUtils;
import org.apache.poi.excel.ExcelWriter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    @Test
    public void CreateExcelFromPojo(){
    
        File file = new File("./test.xlsx");
    
        String sheet = "BUY_A_ABOVE_X_GET_DISCOUNT";
    
        List<BUY_A_ABOVE_X_GET_DISCOUNT> people = Reader.of(BUY_A_ABOVE_X_GET_DISCOUNT.class)
                .from(file)
                .sheet(sheet)
                .list();
    
    
        System.out.println("People from the file:"+people.size());
    
        ExcelBuilder builder = new ExcelBuilder("fromPojoToExcel.xls");
    
    
        ExcelWriter.write("./", "fromPojoToExcel.xls", people);
    }
    
    
    @Test
    public void createExcelFromExcel() throws Exception {
        
        WorkBookXlsx workBook= new WorkBookXlsx("test.xlsx");
        ExcelBuilder builder=new ExcelBuilder("FromExcelToExcel.xls");
        
        
        Iterator<String> sheetNamesIterator = workBook.getSheetNames().iterator();
        
    
        while(sheetNamesIterator.hasNext()){
            String sheetName=sheetNamesIterator.next();
    
            ExcelBuilder finalBuilder = builder;
            workBook.inSheet(sheetName).getColumnHeaders().forEach(
                    (columnName) ->
                            finalBuilder.inSheet(sheetName)
                                    .appendColumnHeader(columnName)
            );
            
        }
        
        
        WorkBook workBook1 = builder.build();
        Assert.assertEquals(workBook1.getSheetNames(),workBook.getSheetNames());
    }
    
    
    @Test
    public void createExcelFromJson() throws Exception {
    
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file=new File(classLoader.getResource("data.json").getFile());
        String body=FileUtils.readFileToString(file, "UTF-8");
    
    
        JSONObject data= new JSONObject(body);
    
        Iterator<String> keys = data.keys();
    
        ExcelBuilder builder=new ExcelBuilder("JsonToExcel.xls");
        
        while (keys.hasNext()){
            
            String sheetName=keys.next();
            JSONArray columns=data.getJSONArray(sheetName);
    
            columns.forEach(
                    (column)->
                            builder.inSheet(sheetName)
                                    .appendColumnHeader((String)column)
            );
            
        }
        
        
        WorkBook workBook = builder.build();
    
       
        
        List<String> actualColumns=workBook.inSheet("Promotion Basic").getColumnHeaders();
        
        List<String > expectedColumns=new ArrayList<String>();
        
        data.getJSONArray("Promotion Basic").forEach(
                (value) ->
                        expectedColumns.add((String) value)
        );
        
        System.out.println(actualColumns);
        
        Assert.assertEquals(actualColumns,expectedColumns);
    
    }
}
