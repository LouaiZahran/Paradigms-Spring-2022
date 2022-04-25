package components;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Heap {
    // id --> object
    public HashMap<Integer, Obj> objects;
    // id --> ids
    public HashMap<Integer,ArrayList<Integer>> network;
    // needed objects 
    public ArrayList<Integer> activeIds;
    public Heap(){
        this.objects=new HashMap<>();
        this.network=new HashMap<>();
        this.activeIds = new ArrayList<>();
    }
    public Heap(InputManager i, String objects_f, String network_f, String activeids_f){
        this.objects=new HashMap<>();
        this.network=new HashMap<>();
        try {
            this.activeIds = i.getActiveObjects(activeids_f);
            i.getObjects(objects_f, this.objects);
            i.getNetwork(network_f, this.network);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void print(){
        for (Integer id : objects.keySet()) {
            System.out.println(", "+id+": from "+objects.get(id).start+" to "+objects.get(id).end);
        }
        for (Integer id : network.keySet()) {
            System.out.print(id+" -> ");
            for (Integer c : network.get(id)) {
                System.out.print(", "+c);
            }
            System.out.print("\n");
        }
    }
    
}
