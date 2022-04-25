package components;

public class Obj {
    public int id;    //32 bits (<=20 used)
    public int start;
    public int end;

    public Obj(int id, int start, int end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }
}