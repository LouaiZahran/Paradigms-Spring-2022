package components;

public class object{
    long id;    //32 bits (<=20 used)
    int start;  
    public object(long id, int start, int end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }
    int end;
}