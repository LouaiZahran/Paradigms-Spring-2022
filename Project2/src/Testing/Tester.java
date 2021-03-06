package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import GarbageCollectors.Copy;
import GarbageCollectors.G1;
import GarbageCollectors.MarkCompact;
import GarbageCollectors.MarkSweep;

public class Tester {
//------------------------------------------------------------------------------------------
    //each test should be a folder in "/tests/" & fill {tests} with their names
    //each folder must have files with names identical to {args} and {expected}
    final String[] tests={"all connected and have cycle","all roots","Garbage Cycle Test","garbage points to Root",
        "no Roots","one pointer or more from an object","one poitner or more from an object in garbage"
        ,"2 roots one object","Normal heap","self pointer"};
    final String[] args = {"/heap.csv","/pointers.csv","/roots.csv"};
    final String[] expected = {"/MS_exp.csv","/MSC_exp.csv","/COPY_exp.csv","/G1_exp.csv"};

    final String cwd = (Path.of("").toAbsolutePath()).toString();
//------------------------------------------------------------------------------------------
    final String[] gcs = {"mark_sweep","mark_compact","copy","g1"};
    final String[] results = {"/MS_res.csv","/MSC_res.csv","/COPY_res.csv","/G1_res.csv"};
    MarkSweep ms;
    MarkCompact msc;
    Copy c;
    G1 g1;
    

    public Tester(){
        this.ms = new MarkSweep();
        this.msc=new MarkCompact();
        this.c=new Copy();
        this.g1=new G1();
    }
    private String[] runTest(String directory){
        try {
            System.out.println("..");
            String[] test_args_all = {cwd+"/tests/"+directory+args[0],cwd+"/tests/"+directory+args[1],cwd+"/tests/"+directory+args[2],cwd+"/tests/"+directory+results[0]};
            String[] test_args_G1 = {cwd+"/tests/"+directory+args[0],cwd+"/tests/"+directory+args[1],cwd+"/tests/"+directory+args[2],cwd+"/tests/"+directory+results[0], "512"};

            MarkSweep.main(test_args_all);


            test_args_all[3] =  cwd+"/tests/"+directory+results[1];
            MarkCompact.main(test_args_all);

            test_args_all[3] =  cwd+"/tests/"+directory+results[2];
            Copy.main(test_args_all);
            System.out.println("..");

            test_args_G1[3] =  cwd+"/tests/"+directory+results[3];
            G1.main(test_args_G1);
            System.out.println("..");


            String[] test_res = new String[2*results.length] ;
            for (int i = 0; i < results.length; i++) {
                test_res[i] = Files.readString(Paths.get(new File(cwd+"/tests/"+directory+expected[i]).getAbsolutePath()));            
                test_res[results.length+i] = Files.readString(Paths.get(new File(cwd+"/tests/"+directory+results[i]).getAbsolutePath()));
            }
            return test_res;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    @TestFactory
    public Collection<DynamicTest>  generateTests(){
        System.out.println((Path.of("").toAbsolutePath()).toString());

        ArrayList<DynamicTest> generated = new ArrayList<>();
        IntStream.iterate(0, n -> (n+1)).limit(tests.length).mapToObj(i -> Map.entry(tests[i],runTest(tests[i]))).forEach(
            entry->{
                System.out.println(entry.getKey());

                processTestRes(entry,generated);
        });;
        return generated;
    }
    private void processTestRes(java.util.Map.Entry<String, String[]> entry,ArrayList<DynamicTest> generated) {
        for(int i=0;i<entry.getValue().length/2;i++){
            int k=i;
            generated.add(DynamicTest.dynamicTest(entry.getKey()+"_"+gcs[k],()->assertEquals(entry.getValue()[k],entry.getValue()[results.length+k])));
        }
    }
}
