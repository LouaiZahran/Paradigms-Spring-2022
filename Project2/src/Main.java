import GarbageCollectors.mark_sweep;
import components.Heap;
import components.InputGenerator;
import components.InputManager;
import components.OutputManager;

public class Main {
    public static void main(String[] args){
        InputGenerator.generate();
        InputManager i=new InputManager();
        Heap myHeap = new Heap(i, "heap.csv", "pointers.csv", "roots.csv");
        myHeap.print();
        mark_sweep.collect(myHeap);
        OutputManager.writeHeap(myHeap,"res.csv");
    }
}
