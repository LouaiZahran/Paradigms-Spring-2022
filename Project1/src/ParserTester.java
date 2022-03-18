import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test.*;
import org.junit.jupiter.api.TestFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTester {
    static Parser myParser;
    FileInputStream input;
    int N; //number of tests
    HashMap<String,String> TestTable;

    public ParserTester(){
        if(myParser==null){
            myParser= new Parser(System.in);
        }

    }
    void AddTest(String InFile, String OutFile){
        this.TestTable.put("Project1/tests/"+InFile+".txt","Project1/tests/"+OutFile+".txt");
        N++;
    }
    String ApplyTest(String TestFile) throws FileNotFoundException, ParseException {
        File f = new File(TestFile);
        input = new FileInputStream(f);
        myParser.ReInit(input);
        return myParser.create();
    }
    String getExpected(String expectedFile) throws IOException {
        String path = new File(expectedFile).getAbsolutePath();

        return Files.readString(Paths.get(path));
    }
    @TestFactory
    Stream<DynamicTest> Test() {
        TestTable = new HashMap<>();
        AddTest("Tpf","pf");
        AddTest("Tuc","uc");
        AddTest("Tpfc","pfc");
        AddTest("Tufc","ufc");
        AddTest("Tpara","para");
        AddTest("Thead","head");
        AddTest("Timg","img");
        AddTest("Turl","url");
        return IntStream.iterate(0, n -> (n+1)).limit(N)
                .mapToObj(n -> DynamicTest.dynamicTest((String) TestTable.keySet().toArray()[n],
                        () -> assertEquals(getExpected(TestTable.get( TestTable.keySet().toArray()[n])),ApplyTest((String)TestTable.keySet().toArray()[n]))));
    }
              @TestFactory

    Stream<DynamicTest> ValidTest() {

    ArrayList<String> Valid= new ArrayList<>();

        Valid.add("Tpf");
        Valid.add("Turl");
        Valid.add("Tpara");
        Valid=(ArrayList<String>) Valid.stream().map((String n)-> "Project1/tests/"+n+".txt").collect(Collectors.toList());

                  ArrayList<String> finalValid = Valid;
                  return IntStream.iterate(0, n -> (n+1)).limit(Valid.size())
                .mapToObj(n -> DynamicTest.dynamicTest(finalValid.get(n),
                        ()->{
                                assertTrue(()->{
                                    try {
                                        ApplyTest(finalValid.get(n));
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (ParseException e) {
                                        return false;
                                    }
                                    return true;});
//                                assertTrue(()-> {
//                                    try {
//                                        ApplyTest(Valid.get(n));
//                                        return true;
//                                    } catch (Exception e) {
//                                        return false;
//                                    }
//                                });
                        }));

      

}

    

            @TestFactory

    Stream<DynamicTest> NonValidTest() {

                ArrayList<String> NonValid = new ArrayList<String>(Arrays.asList("NonValid_0", "NonValid_1", "NonValid_2",
                        "NonValid_3", "NonValid_4", "NonValid_5",
                        "NonValid_6", "NonValid_7", "NonValid_8",
                        "NonValid_9"));
        NonValid=(ArrayList<String>) NonValid.stream().map((String n)-> "Project1/tests/"+n+".txt").collect(Collectors.toList());

                ArrayList<String> finalNonValid = NonValid;
                return IntStream.iterate(0, n -> (n+1)).limit(NonValid.size())

                .mapToObj(n -> DynamicTest.dynamicTest(finalNonValid.get(n),

                        () -> assertFalse(()->{
                            try {
                                ApplyTest(finalNonValid.get(n));
                                return true;
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                                return true;
                            } catch (ParseException | Error e) {
                                return false;
                            }
                        })));

}








}
