package GarbageCollectors;

import components.Heap;

public class MarkCompact extends mark_sweep{
    public static void markAndCompact(Heap heap){
        mark_sweep.collect(heap);
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
}
