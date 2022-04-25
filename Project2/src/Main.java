import components.InputGenerator;
import components.InputManager;
import components.Obj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        HashMap<Integer, Obj> map = InputManager.getObjects("heap.csv");
        for(Map.Entry<Integer, Obj> entry: map.entrySet())
            System.out.println(entry.getKey());
    }
}
