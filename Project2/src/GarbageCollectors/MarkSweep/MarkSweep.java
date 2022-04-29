package GarbageCollectors.MarkSweep;

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
