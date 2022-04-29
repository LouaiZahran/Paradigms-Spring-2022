package GarbageCollectors;

import components.*;

import java.io.IOException;
import java.util.*;

public class G1 {
    public static void main (String[] args) throws IOException {
        G1Heap heap = G1Heap.createHeap(args);
        collect(heap);
        OutputManager.writeHeap(heap, args[3]);
    }

    public static void collect(G1Heap heap){
        markAndSweep(heap);
        GarbageFirst(heap);
        defragment(heap);
    }

    private static void markAndSweep(G1Heap heap){
        //Marking
        HashSet<Obj> marked = new HashSet<>();
        for(Region block: heap.blocks)
            for(Obj obj: block.content)
                if(heap.activeIds.contains(obj.id))
                    mark(heap, obj, marked);

        //Sweeping
        for(Region block: heap.blocks)
            block.content.removeIf(obj -> !marked.contains(obj));
    }

    private static void mark(G1Heap heap, Obj obj, HashSet<Obj> marked){
        if(marked.contains(obj))    //Already visited
            return;
        marked.add(obj);
        if(heap.network.get(obj.id) == null)
            return;
        for(Integer child: heap.network.get(obj.id))
            mark(heap, heap.objects.get(child), marked);
    }

    private static void GarbageFirst(G1Heap heap){
        for(Region block: heap.blocks)
            if(block.content.isEmpty())
                block.appendable = true;
    }

    private static void defragment(G1Heap heap){
        for(Region block: heap.blocks){
            if(block.appendable)        //The block is empty or being copied into
                continue;
            for(Obj obj: block.content){
                for(Region nestedBlock: heap.blocks){
                    if(nestedBlock.appendable && nestedBlock.freeArea >= obj.getSize()){
                        nestedBlock.insert(obj);
                        break;
                    }
                }
            }
            block.clear();
        }
    }
}
