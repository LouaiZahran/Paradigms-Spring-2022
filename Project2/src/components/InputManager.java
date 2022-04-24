package components;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class InputManager {
    public static void getObjects(String filepath,HashMap<Long,object>map) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filepath)); 
        String line="";
        String[] seg;
        while ((line = reader.readLine()) != null){
            seg=line.split(",");
            map.put(Long.parseLong(seg[0]),new object(Long.parseLong(seg[0]), Integer.parseInt(seg[1]),Integer.parseInt(seg[2])));
        }
        reader.close();
    }
    public static void getNetwork(String filepath,HashMap<Long,ArrayList<Long>>map) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filepath)); 
        String line="";
        String[] seg;
        while ((line = reader.readLine()) != null){
            seg=line.split(",");
            var list = map.get(Long.parseLong(seg[0]));
            if(list==null){
                list = new ArrayList<Long>();
                map.put(Long.parseLong(seg[0]), list);
            }
            list.add(Long.parseLong(seg[1]));
        }
        reader.close();
    }
    /*
    * 
    */
    public static ArrayList<Long> assignActiveObjects(String filepath,HashMap<Long,Boolean> active) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filepath)); 
        String line="";
        
        ArrayList<Long> activeIds = new ArrayList<>();
        while ((line = reader.readLine()) != null){
            activeIds.add(Long.parseLong(line));
        }
        reader.close();
        return activeIds;
    }
}
