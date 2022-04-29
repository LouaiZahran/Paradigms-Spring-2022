package components;

import java.util.LinkedHashSet;

public class Region {
    public int start;
    public int freeArea;
    public LinkedHashSet<Obj> content;
    public boolean appendable;

    public void insert(Obj obj){
        int size = obj.getSize();
        assert(size <= freeArea);
        freeArea -= size;
        obj.start = start;
        obj.end = start + size - 1;
        this.start += size;
        content.add(obj);
    }

    public void remove(Obj obj){
        freeArea += obj.getSize();
        content.remove(obj);
    }

    public void clear(){
        content.clear();
    }
}
