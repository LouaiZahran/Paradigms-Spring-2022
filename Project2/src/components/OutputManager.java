package components;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class OutputManager {
    public static void writeHeap(Heap heap, String fileName) throws IOException{
        try{
            BufferedWriter Bwriter = new BufferedWriter(new FileWriter(fileName)); 
            StringBuilder sb = new StringBuilder();
            for(Map.Entry<Integer, Obj> entry: heap.objects.entrySet()){
                Obj obj = entry.getValue();
                sb.append(obj.id);
                sb.append(",");
                sb.append(obj.start);
                sb.append(",");
                sb.append(obj.end);
                sb.append("\n");
            }
            Bwriter.write(sb.toString());
            Bwriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeHeap(G1Heap heap, String fileName) throws IOException{
        try{
            BufferedWriter Bwriter = new BufferedWriter(new FileWriter(fileName));
            StringBuilder sb = new StringBuilder();
            for(Region block: heap.blocks) {
                for (Obj obj : block.content) {
                    sb.append(obj.id);
                    sb.append(",");
                    sb.append(obj.start);
                    sb.append(",");
                    sb.append(obj.end);
                    sb.append("\n");
                }
            }
            Bwriter.write(sb.toString());
            Bwriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
