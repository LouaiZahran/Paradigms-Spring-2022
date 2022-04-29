package GarbageCollectors.MarkCompact;

import java.io.IOException;

import GarbageCollectors.MarkSweep.MarkSweep;
import components.Heap;
import components.OutputManager;

public class MarkCompact {
    public static void collect(Heap heap){
        MarkSweep.collect(heap);
        int count=0;
        for(Integer i:heap.objects.keySet()){
            if(heap.objects.get(i)!=null){
                int start=heap.objects.get(i).getStart();
                int end=heap.objects.get(i).getEnd();
                int size=end-start;
                heap.objects.get(i).setStart(count);
                heap.objects.get(i).setEnd(count+size);
                count=count+size+1;
            }
        }
    }
    public static void main (String[] args) throws IOException{
        if(args.length != 4){
            System.out.println("Please enter the required (4) arguments");
            return;
        }

        Heap heap;
        try {
            heap = Heap.summonHeap(args);
        } catch (Exception e){
            System.out.println("The input files are not valid");
            return;
        }

        collect(heap);

        try {
            OutputManager.writeHeap(heap, args[3]);
        } catch (Exception e){
            System.out.println("The output file cannot be written to disk");
        }
    }
}
