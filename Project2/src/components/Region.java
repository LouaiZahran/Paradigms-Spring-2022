package components;

import java.util.ArrayList;
import java.util.HashSet;

public class Region {
    int start;
    int freeArea;
    HashSet<Obj> content;

    public void insert(Obj obj){
        int size = obj.getSize();
        assert(size <= freeArea);
        freeArea -= size;
        obj.start = start;
        obj.end = start + size;
        this.start += size;
        content.add(obj);
    }

    public void remove(Obj obj){
        freeArea += obj.getSize();
        content.remove(obj);
    }
}
