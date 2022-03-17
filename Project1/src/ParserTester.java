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
        this.TestTable.put("Project1/tests/"+InFile+".txt","Project1/tests/"+OutFile+".txt");
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
        AddTest("Tpara","para");
        AddTest("Thead","head");
        return IntStream.iterate(0, n -> (n+1)).limit(N)
                .mapToObj(n -> DynamicTest.dynamicTest((String) TestTable.keySet().toArray()[n],
                        () -> assertEquals(getExpected(TestTable.get(TestTable.keySet().toArray()[n])),ApplyTest((String)TestTable.keySet().toArray()[n]))));
    }

//    @TestFactory
//    Collection<DynamicTest> StartTesting() throws FileNotFoundException, ParseException {
//        return Arrays.asList(
//            DynamicTest.dynamicTest()
//        );
//        String res = ApplyTest("Paragraph");
//        System.out.println(res);
//        assertEquals("<p>test</p>",res);
//    }


}
