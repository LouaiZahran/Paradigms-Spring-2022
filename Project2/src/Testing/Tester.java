package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import GarbageCollectors.Copy;
import GarbageCollectors.MarkCompact;
import GarbageCollectors.MarkSweep;

public class Tester {
//------------------------------------------------------------------------------------------
    //each test should be a folder in "/tests/" & fill {tests} with their names
    //each folder must have files with names identical to {args} and {expected}
 
    final String[] tests={"t1","t2","..."};
    final String[] args = {"/heap.csv","/adj.csv","/root.csv"};
    final String[] expected = {"/MS_exp.csv","/MSC_exp.csv","/COPY_exp.csv","/G1_exp.csv"};
//------------------------------------------------------------------------------------------
    
    final String[] results = {"/MS_res.csv","/MSC_res.csv","/COPY_res.csv","/G1_res.csv"};
    MarkSweep ms;
    MarkCompact msc;
    Copy c;
    //  G1 g1;
    

    public Tester(){
        this.ms = new MarkSweep();
        this.msc=new MarkCompact();
        this.c=new Copy();
        // this.g1=new G1();
    }
    private String[] runTest(String directory){
        try {
            String[] test_args = {"/tests/"+directory+args[0],"/tests/"+directory+args[1],"/tests/"+directory+args[2],results[0]};
            MarkSweep.main(test_args);

            test_args[3] =  "/tests/"+directory+results[1];
            MarkCompact.main(args);

            test_args[3] =  "/tests/"+directory+results[2];
            Copy.main(args);

            // test_args[3] =  "/tests/"+directory+results[3];
            // G1.main(args);

            String[] test_res = new String[2*results.length] ;
            for (int i = 0; i < results.length; i++) {
                String exp = Files.readString(Paths.get(new File("/tests/"+directory+expected[i]).getAbsolutePath()));            
                String res =* Files.readString(Paths.get(new File("/tests/"+directory+results[i]).getAbsolutePath()));
                // test_res[i]=exp.equals(res);
                test_res[i]=exp;
                test_res[2*i]=res;
            }
            return test_res;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    @TestFactory
    public Collection<DynamicTest>  generateTests(){
        ArrayList<DynamicTest> generated = new ArrayList<>();
        IntStream.iterate(0, n -> (n+1)).limit(tests.length).mapToObj(i -> Map.entry(tests[i],runTest(tests[i]))).forEach(
            entry->{
                processTestRes(entry,generated);
        });;
        return generated;
    }
    private void processTestRes(java.util.Map.Entry<String, String[]> entry,ArrayList<DynamicTest> generated) {
        for(int i=0;i<entry.getValue().length;i++){
            int k=i;
            generated.add(DynamicTest.dynamicTest(entry.getKey()+"-"+i,()->assertEquals(entry.getValue()[k],entry.getValue()[2*k])));
        }
        // ()->assertTrue(entry.getValue()[k])
    }
}
