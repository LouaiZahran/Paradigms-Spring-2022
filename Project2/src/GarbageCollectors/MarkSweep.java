package GarbageCollectors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import components.Heap;
import components.InputManager;
import components.Obj;
import components.OutputManager;

public class MarkSweep{
    public static void mark(Integer id, Heap heap, HashMap<Integer,Boolean> marked){
        if(marked.get(id)!=null && marked.get(id)){
            return;
        }
        marked.put(id,true);
        if(heap.network.get(id)!=null){
            for (Integer child : heap.network.get(id)) {
                mark(child, heap, marked);
            }
        }
    }
    public static void collect(Heap heap){
        HashMap<Integer,Boolean> marked = new HashMap<>();
        //marking
        for(Integer id : heap.activeIds){
            mark(id, heap, marked);
        }
        for (Integer id : heap.objects.keySet()) {
            Boolean a = marked.get(id);
            Boolean b = (a == null)? false : a;
            marked.put(id, b);
        }
        //sweeping
        for(Entry<Integer,Boolean> e :marked.entrySet()){
            if(!marked.get(e.getKey())){
                heap.objects.remove(e.getKey());
            }
        }
    }

    public static void main(String[] args) throws IOException{
        Heap heap = Heap.summonHeap(args);
        collect(heap);
        OutputManager.writeHeap(heap,args[3]);
    }
}
