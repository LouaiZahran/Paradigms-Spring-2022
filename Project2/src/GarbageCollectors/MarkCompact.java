package GarbageCollectors;

import java.io.IOException;

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
        Heap heap = Heap.summonHeap(args);
        collect(heap);
        OutputManager.writeHeap(heap,args[3]);
    }
}
