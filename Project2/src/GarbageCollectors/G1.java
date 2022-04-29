package GarbageCollectors;

import components.G1Heap;
import components.Heap;
import components.Obj;
import components.OutputManager;

import java.io.IOException;
import java.util.*;

public class G1 {
    public static void main (String[] args) throws IOException {
        G1Heap heap = G1Heap.createHeap(args);
        collect(heap);
        OutputManager.writeHeap(heap, args[3]);
    }

    public static void collect(G1Heap heap){

    }

}
