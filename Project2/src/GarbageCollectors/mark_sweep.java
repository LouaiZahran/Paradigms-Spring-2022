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
                for (Long child : Heap.network.get(id)) {
                    marked.put(child, true);
                }
            }
            else{
                Boolean a;
                Boolean b = ((a = marked.get(id))==null)?false:a;
                marked.put(id,b);
            }
        }
        //sweeping
        for (Long id : Heap.objects.keySet()) {
            if(!marked.get(id)){
                Heap.objects.remove(id);
            }
        }
    }
}
