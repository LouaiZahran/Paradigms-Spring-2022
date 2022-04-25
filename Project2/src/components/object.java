package components;

public class object{
    int id;    //32 bits (<=20 used)
    int start;  
    int end;

    public object(int id, int start, int end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }
}