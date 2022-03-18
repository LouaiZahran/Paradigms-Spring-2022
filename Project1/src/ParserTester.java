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
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTester {
    Parser myParser= new Parser(System.in);
    FileInputStream input;
    int N; //number of tests
    HashMap<String,String> TestTable = new HashMap<String,String>();

    void AddTest(String InFile, String OutFile){
        this.TestTable.put("tests/"+InFile+".txt","tests/"+OutFile+".txt");
        N++;
     }
    String ApplyTest(String TestFile) throws FileNotFoundException, ParseException {
        File f = new File(TestFile);
        input = new FileInputStream(f);
        myParser.ReInit(input);
        String res = myParser.create();
        return res;
    }
    String getExpected(String expectedFile) throws IOException {
        String path = new File(expectedFile).getAbsolutePath();

        return Files.readString(Paths.get(path));
    }
    @TestFactory
    Stream<DynamicTest> Test() {
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

    ArrayList<String> Valid= new ArrayList<String>;

        //Validations.add("...");

        return IntStream.iterate(0, n -> (n+1)).limit(Valid.Size())

                .mapToObj(n -> DynamicTest.dynamicTest(Valid.get(n),

                        () ->{                      

assertThrows(Exception.class,()->{

                        ApplyTest(Valid.get(n);});}));

      

}

    

            @TestFactory

    Stream<DynamicTest> NonValidTest() {

    ArrayList<String> NonValid= new ArrayList<String>;

        //Validations.add("...");

        return IntStream.iterate(0, n -> (n+1)).limit(NonValid.Size())

                .mapToObj(n -> DynamicTest.dynamicTest(NonValid.get(n),

                        () ->{                      

assertDoesNotThrow(Exception.class,()->{

                        ApplyTest(NonValid.get(n);});}));

      

}








}
