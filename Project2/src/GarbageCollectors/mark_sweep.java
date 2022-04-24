package GarbageCollectors;

import java.util.HashMap;

import components.heap;

public class mark_sweep {
    public static void collect(heap Heap){
        HashMap<Long,Boolean> marked=new HashMap<>();
        //marking
        for (Long id : Heap.objects.keySet()) {
            if(Heap.activeIds.contains(id)){
                marked.put(id, true);
            }
            else{
                marked.put(id, false);
            }
        }
        //swapping
        
    }
}
