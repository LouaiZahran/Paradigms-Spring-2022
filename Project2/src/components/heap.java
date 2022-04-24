package components;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class heap {
    // id --> object
    public HashMap<Long,object> objects;
    // id --> ids
    public HashMap<Long,ArrayList<Long>> network;
    // needed objects 
    public ArrayList<Long> activeIds;
    public heap(){
        this.objects=new HashMap<>();
        this.network=new HashMap<>();
        this.activeIds = new ArrayList<>();
    }
    public heap(InputManager i,String objects_f,String network_f,String activeids_f){
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
    
}
