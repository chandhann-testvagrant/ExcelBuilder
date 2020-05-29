package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sheet {

    List<String> columns= new ArrayList<String>();
    
    Map<String ,List<String>> map= new HashMap<String ,List<String>>();
    
    private String sheetName;
    public void setSheetName(String sheetName){
        this.sheetName=sheetName;
    }
    
    public String getSheetName(String sheetName){
        return this.sheetName;
    }
    
    public void addColumn(String columnName){
    
        columns.add(columnName);
    
    }
    
    public void mapValues(String columnName,List<String> columnValues){
        map.put(columnName,columnValues);
    }
    
    public Map<String ,List<String>> getMap(){
        return map;
    
    }
    
    public List<String> getcolumnValues(String columnName){
        return map.get(columnName);
    }
    


}
