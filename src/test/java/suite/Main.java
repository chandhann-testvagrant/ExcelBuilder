package suite;

import builder.ExcelBuilder;
import model.WorkBook;
import org.apache.commons.io.FileUtils;
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
    public void createExcel() throws Exception {
    
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file=new File(classLoader.getResource("data.json").getFile());
        String body=FileUtils.readFileToString(file, "UTF-8");
    
    
        JSONObject data= new JSONObject(body);
    
        Iterator<String> keys = data.keys();
    
        ExcelBuilder builder=new ExcelBuilder("test.xls");
        
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
