package GarbageCollectors;

import java.util.HashMap;
import java.util.Map;

import components.Heap;

public class mark_sweep{
    public static void mark(Integer id, Heap heap, HashMap<Integer,Boolean> marked){
        if(marked.get(id)!=null && marked.get(id)){
            return;
        }
        marked.put(id, true);
        if(heap.network.get(id) == null)
            return;
        for (Integer child : heap.network.get(id)) {
            mark(child, heap, marked);
        }
    }
    public static void collect(Heap heap){
        HashMap<Integer,Boolean> marked = new HashMap<>();
        //marking
        for (Integer id : heap.objects.keySet()) {
            if(heap.activeIds.contains(id)){
                mark(id, heap, marked);
            }
            else{
                Boolean a = marked.get(id);
                Boolean b = (a == null)? false : a;
                marked.put(id, b);
            }
        }
        //sweeping
        for (Map.Entry<Integer, Boolean> visited : marked.entrySet()) {
            if(!visited.getValue()){
                heap.objects.remove(visited.getKey());
            }
        }
    }
}
