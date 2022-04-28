package GarbageCollectors;

import components.Heap;
import components.Obj;
import components.OutputManager;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Copy {
    private static Heap resultHeap;

    public static void collect(Heap oldHeap){
        Heap newHeap=new Heap();
        Queue<Integer> queue=new LinkedList<>();
        int endPtr=0;
        for(Integer active :oldHeap.activeIds)
            queue.add(active);

        while (queue.size()!=0){
            Integer curr=queue.poll();
            if(oldHeap.network.get(curr)!=null) {
                for (Integer child : oldHeap.network.get(curr)) {
                    if (newHeap.objects.containsKey(curr)) //skip child already added
                        continue;
                    queue.add(child);
                }
            }
            if(newHeap.objects.containsKey(curr)) // skip root already added
                continue;
            int start=oldHeap.objects.get(curr).getStart();
            int end=oldHeap.objects.get(curr).getEnd();
            int size=end-start;
            newHeap.objects.put(curr,new Obj(curr,endPtr,endPtr+size));
            endPtr=endPtr+size+1;
        }
        resultHeap=newHeap;
    }
    public static Heap getResultHeap(){
        return resultHeap;
    }
    public static void main(String[] args) throws IOException {
        Heap heap = Heap.summonHeap(args);
        collect(heap);
        OutputManager.writeHeap(resultHeap,args[3]);
    }
}