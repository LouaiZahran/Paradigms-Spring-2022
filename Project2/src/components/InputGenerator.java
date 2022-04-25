package components;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputGenerator {
    public static void generate(){
        ArrayList<Obj> list = new ArrayList<>();
        ArrayList<Obj> roots = new ArrayList<>();
        HashMap<Obj, ArrayList<Obj>> adj = new HashMap<>();
        int num = (int) (Math.random()*100);
        int currentAddress = 0;
        for(int i=0; i<num; i++){
            int objectSize = (int) (Math.random()*100);
            int objectId = (int) (Math.random()*1000000);
            list.add(new Obj(objectId, currentAddress, currentAddress + objectSize));
            currentAddress += objectSize;
            currentAddress += (int) (Math.random()*100 + 1);
        }

        for(int i=0; i<num; i++){
            if(Math.random()>=0.8)
                roots.add(list.get(i));
        }

        for(int i=0; i<num; i++){
            Obj parent = list.get(i);
            ArrayList<Obj> neighbors = new ArrayList<>();
            for(int j=0; j<num/4; j++){
                if(Math.random()>0.95){
                    neighbors.add(list.get(j));
                }
            }
            adj.put(parent, neighbors);
        }

        writeObjects(list, "heap.csv");
        writeRoots(roots, "roots.csv");
        writePointers(adj, "pointers.csv");
    }

    public static void writeObjects(ArrayList<Obj> objects, String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            StringBuilder sb = new StringBuilder();
            for (Obj obj: objects) {
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

    public static void writeRoots(ArrayList<Obj> objects, String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            StringBuilder sb = new StringBuilder();
            for (Obj obj: objects) {
                sb.append(obj.id);
                sb.append("\n");
            }
            writer.write(sb.toString());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writePointers(HashMap<Obj, ArrayList<Obj>> adj, String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            StringBuilder sb = new StringBuilder();
            for(Map.Entry<Obj, ArrayList<Obj>> entry: adj.entrySet()){
                Obj parent = entry.getKey();
                ArrayList<Obj> children = entry.getValue();
                for(Obj child: children){
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
