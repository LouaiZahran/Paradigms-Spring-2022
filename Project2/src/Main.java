
import components.Heap;
import components.InputManager;
import components.Obj;
import components.OutputManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import GarbageCollectors.mark_sweep;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Heap.csv read test");
        System.out.println("ID: startAddress -> endAddress\n");
        HashMap<Integer, Obj> map = InputManager.getObjects("heap.csv");
        for(Map.Entry<Integer, Obj> entry: map.entrySet()) {
            Obj object = entry.getValue();
            System.out.printf("%d: %d -> %d\n", object.id, object.start, object.end);
        }

        System.out.println("=====================");

        System.out.println("Pointers.csv read test");
        System.out.println("ParentID -> ChildID\n");
        HashMap<Integer, ArrayList<Integer>> adj = InputManager.getNetwork("pointers.csv");
        for(Map.Entry<Integer, ArrayList<Integer>> entry: adj.entrySet()) {
            for(Integer child: entry.getValue())
                System.out.printf("%d -> %d\n", entry.getKey(), child);
        }

        System.out.println("\n=====================\n");

        System.out.println("Roots.csv read test");
        System.out.println("RootID\n");
        ArrayList<Integer> roots = InputManager.getActiveObjects("roots.csv");
        for(Integer root: roots)
            System.out.println(root);
        Heap myHeap = new Heap(map, adj, roots);
        mark_sweep.collect(myHeap);
        OutputManager.writeHeap(myHeap,"res.csv");
    }
}
