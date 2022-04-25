package components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class InputManager {
    public void getObjects(String filepath,HashMap<Integer,object>map) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filepath)); 
        String line="";
        String[] seg;
        while ((line = reader.readLine()) != null){
            seg=line.split(",");
            map.put(Integer.parseInt(seg[0]),new object(Integer.parseInt(seg[0]), Integer.parseInt(seg[1]),Integer.parseInt(seg[2])));
        }
        reader.close();
    }
    public void getNetwork(String filepath,HashMap<Integer,ArrayList<Integer>>map) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filepath)); 
        String line="";
        String[] seg;
        while ((line = reader.readLine()) != null){
            seg=line.split(",");
            var list = map.get(Integer.parseInt(seg[0]));
            if(list==null){
                list = new ArrayList<Integer>();
                map.put(Integer.parseInt(seg[0]), list);
            }
            list.add(Integer.parseInt(seg[1]));
        }
        reader.close();
    }
    /*
    *
    */
    public ArrayList<Integer> getActiveObjects(String filepath) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filepath)); 
        String line="";
        
        ArrayList<Integer> activeIds = new ArrayList<>();
        while ((line = reader.readLine()) != null){
            activeIds.add(Integer.parseInt(line));
        }
        reader.close();
        return activeIds;
    }
}
