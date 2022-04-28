package GarbageCollectors;

import components.Heap;
import components.Obj;
import components.OutputManager;

import java.io.IOException;

public class Copy {
    private static Heap resultHeap;
    private static int addRoot(Heap oldHeap,Heap newHeap,Integer root,int endPtr){
        if(newHeap.objects.containsKey(root))
            return endPtr;
        int start=oldHeap.objects.get(root).getStart();
        int end=oldHeap.objects.get(root).getEnd();
        int size=end-start;
        newHeap.objects.put(root,new Obj(root,endPtr,endPtr+size));
        endPtr=endPtr+size+1;
        return endPtr;
    }
    private static int addChildren(Heap oldHeap,Heap newHeap,Integer root,int endPtr){
        if(oldHeap.network.get(root)==null)
            return endPtr;

        for(Integer child : oldHeap.network.get(root)) {
            if(newHeap.objects.containsKey(child))
                continue;
            int start=oldHeap.objects.get(child).getStart();
            int end=oldHeap.objects.get(child).getEnd();
            int size=end-start;
            newHeap.objects.put(child,new Obj(child,endPtr,endPtr+size));
            endPtr=endPtr+size+1;
            endPtr=addChildren(oldHeap,newHeap,child,endPtr);
        }
        return endPtr;
    }

    public static void collect(Heap oldHeap){
        Heap newHeap=new Heap();
        int endPtr=0;
        for(Integer active : oldHeap.activeIds){
            endPtr=addRoot(oldHeap,newHeap,active,endPtr);
            endPtr=addChildren(oldHeap,newHeap,active,endPtr);
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