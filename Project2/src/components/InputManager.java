package components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class InputManager {
    public static LinkedHashMap<Integer, Obj> getObjects(String filepath) throws IOException{
        LinkedHashMap<Integer, Obj> map = new LinkedHashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(filepath)); 
        String line="";
        String[] seg;
        while ((line = reader.readLine()) != null){
            seg=line.split(",");
            map.put(Integer.parseInt(seg[0]),new Obj(Integer.parseInt(seg[0]), Integer.parseInt(seg[1]),Integer.parseInt(seg[2])));
        }
        reader.close();
        return map;
    }
    public static LinkedHashMap<Integer,ArrayList<Integer>> getNetwork(String filepath) throws IOException{
        LinkedHashMap<Integer,ArrayList<Integer>> map = new LinkedHashMap<>();
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
        return map;
    }
    /*
    *
    */
    public static ArrayList<Integer> getActiveObjects(String filepath) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filepath)); 
        String line="";
        
        ArrayList<Integer> activeIds = new ArrayList<>();
        while ((line = reader.readLine()) != null && line.compareTo("") != 0){
            activeIds.add(Integer.parseInt(line));
        }
        reader.close();
        return activeIds;
    }
}
