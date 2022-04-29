package components;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;

public class G1Heap extends Heap{
    int size;
    int blockSize;
    public Region[] blocks = new Region[16];

    public G1Heap(){
        for(int i=0; i<16; i++)
            blocks[i] = new Region();
    }

    public static G1Heap createHeap(String[] args) throws IOException {
        //Taking input
        G1Heap myHeap = new G1Heap();
        myHeap.objects = InputManager.getObjects(args[0]);
        myHeap.network = InputManager.getNetwork(args[1]);
        myHeap.activeIds = InputManager.getActiveObjects(args[2]);
        myHeap.size = Integer.parseInt(args[4]);
        myHeap.blockSize = myHeap.size/16;

        //Validation
        if(myHeap.size%16 != 0)
            throw new IOException();
        for(Integer parent: myHeap.network.keySet())
            if(!myHeap.objects.containsKey(parent))
                throw new IOException();
        for(Integer root: myHeap.activeIds)
            if(!myHeap.objects.containsKey(root))
                throw new IOException();

        //Initialization
        for(int i=0; i<16; i++){
            myHeap.blocks[i].start = i * myHeap.blockSize;
            myHeap.blocks[i].freeArea = myHeap.blockSize;
            myHeap.blocks[i].content = new LinkedHashSet<>();
            myHeap.blocks[i].appendable = true;
        }
        for(Obj obj: myHeap.objects.values()){
            int regionNumber = obj.getStart()/myHeap.blockSize;
            assert(obj.getEnd() < (regionNumber+1) * myHeap.blockSize); //Otherwise, this object spawns multiple blocks
            myHeap.blocks[regionNumber].content.add(obj);
            myHeap.blocks[regionNumber].appendable = false;
        }
        return myHeap;
    }
}
