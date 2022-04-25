package components;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

public class OutputManager {
    public static void writeHeap(Heap heap, String fileName){
        try{
            PrintWriter writer = new PrintWriter(fileName);
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
            writer.write(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
