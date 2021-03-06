package components;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Heap {
    public LinkedHashMap<Integer, Obj> objects;                   // id --> object
    public LinkedHashMap<Integer,ArrayList<Integer>> network;     // id --> ids
    public ArrayList<Integer> activeIds;                    // needed objects

    public Heap(){
        this.objects=new LinkedHashMap<>();
        this.network=new LinkedHashMap<>();
        this.activeIds = new ArrayList<>();
    }
    
    public Heap(String objects_f, String network_f, String activeids_f){
        this.objects=new LinkedHashMap<>();
        this.network=new LinkedHashMap<>();
        try {
            this.activeIds = InputManager.getActiveObjects(activeids_f);
            this.objects = InputManager.getObjects(objects_f);
            this.network = InputManager.getNetwork(network_f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Heap(LinkedHashMap<Integer, Obj> map, LinkedHashMap<Integer, ArrayList<Integer>> adj, ArrayList<Integer> roots) {
    this.objects=map;
    this.network=adj;
    this.activeIds=roots;
    }
    public static Heap summonHeap(String[] args) throws IOException{
        LinkedHashMap<Integer, Obj> map = InputManager.getObjects(args[0]);
        LinkedHashMap<Integer, ArrayList<Integer>> adj = InputManager.getNetwork(args[1]);
        ArrayList<Integer> roots = InputManager.getActiveObjects(args[2]);

        //Validation
        for(Integer parent: adj.keySet())
            if(!map.containsKey(parent))
                throw new IOException();
        for(Integer root: roots)
            if(!map.containsKey(root))
                throw new IOException();

        Heap myHeap = new Heap(map, adj, roots);
        return myHeap;
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
