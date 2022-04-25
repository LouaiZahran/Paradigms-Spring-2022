package GarbageCollectors;

import java.util.HashMap;

import components.heap;

public class mark_sweep {
    public static void collect(heap Heap){
        HashMap<Integer,Boolean> marked=new HashMap<>();
        //marking
        for (Integer id : Heap.objects.keySet()) {
            if(Heap.activeIds.contains(id)){
                marked.put(id, true);
                for (Integer child : Heap.network.get(id)) {
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
        for (Integer id : Heap.objects.keySet()) {
            if(!marked.get(id)){
                Heap.objects.remove(id);
            }
        }
    }
}
