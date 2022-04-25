package components;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputGenerator {
    public static void generate(){
        ArrayList<object> list = new ArrayList<>();
        ArrayList<object> roots = new ArrayList<>();
        HashMap<object, ArrayList<object>> adj = new HashMap<>();
        int num = (int) (Math.random()*100);
        int currentAddress = 0;
        for(int i=0; i<num; i++){
            int objectSize = (int) (Math.random()*100);
            int objectId = (int) (Math.random()*1000000);
            list.add(new object(objectId, currentAddress, currentAddress + objectSize));
            currentAddress += objectSize;
            currentAddress += (int) (Math.random()*100 + 1);
        }

        for(int i=0; i<num; i++){
            if(Math.random()>=0.5)
                roots.add(list.get(i));
        }

        for(int i=0; i<num; i++){
            object parent = list.get(i);
            ArrayList<object> neighbors = new ArrayList<>();
            for(int j=0; j<num; j++){
                if(Math.random()>=0.1)
                    neighbors.add(list.get(j));
            }
            adj.put(parent, neighbors);
        }

        writeObjects(list, "heap.csv");
        writeRoots(roots, "roots.csv");
        writePointers(adj, "pointers.csv");
    }

    public static void writeObjects(ArrayList<object> objects, String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            StringBuilder sb = new StringBuilder();
            for (object obj: objects) {
                sb.append(obj.id);
                sb.append(",");
                sb.append(obj.start);
                sb.append(",");
                sb.append(obj.end);
                sb.append("\n");
            }
            writer.write(sb.toString());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeRoots(ArrayList<object> objects, String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            StringBuilder sb = new StringBuilder();
            for (object obj: objects) {
                sb.append(obj.id);
                sb.append("\n");
            }
            writer.write(sb.toString());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writePointers(HashMap<object, ArrayList<object>> adj, String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            StringBuilder sb = new StringBuilder();
            for(Map.Entry<object, ArrayList<object>> entry: adj.entrySet()){
                object parent = entry.getKey();
                ArrayList<object> children = entry.getValue();
                for(object child: children){
                    sb.append(parent.id);
                    sb.append(",");
                    sb.append(child.id);
                    sb.append("\n");
                }
            }
            writer.write(sb.toString());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
