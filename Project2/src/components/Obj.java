package components;

public class Obj {
    public int id;    //32 bits (<=20 used)
    public int start;
    public int end;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getSize(){
        return end - start;
    }

    public Obj(int id, int start, int end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }
}