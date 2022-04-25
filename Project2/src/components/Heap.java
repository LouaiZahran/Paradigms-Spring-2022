package components;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Heap {
    public HashMap<Integer, Obj> objects;                   // id --> object
    public HashMap<Integer,ArrayList<Integer>> network;     // id --> ids
    public ArrayList<Integer> activeIds;                    // needed objects

    public Heap(){
        this.objects=new HashMap<>();
        this.network=new HashMap<>();
        this.activeIds = new ArrayList<>();
    }
    
    public Heap(String objects_f, String network_f, String activeids_f){
        this.objects=new HashMap<>();
        this.network=new HashMap<>();
        try {
            this.activeIds = InputManager.getActiveObjects(activeids_f);
            this.objects = InputManager.getObjects(objects_f);
            this.network = InputManager.getNetwork(network_f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
