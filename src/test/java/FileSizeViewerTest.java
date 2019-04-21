
import javafx.util.Pair;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileSizeViewerTest {
    @Test
    public void sizeTest() throws IOException {


        String name1 = "./Temp.txt";
        String name2 = "./folder/";
        String name3 = "./Temp2.txt";

        File tempFile1 = new File(name1);
        File tempFile2 = new File(name2);
        File tempFile3 = new File(name3);
        tempFile1.createNewFile();
        tempFile2.createNewFile();
        tempFile3.createNewFile();



        FileWriter fileWriter = new FileWriter("./Temp2.txt");
        fileWriter.write("Hello, World!");
        fileWriter.close();

        List<String> input = new ArrayList<String>();
        input.add(name1);
        input.add(name2);
        input.add(name3);
        FileSizeViewer FSV = new FileSizeViewer(input);

        Long testSumSize = tempFile1.length() + tempFile2.length()+ tempFile3.length();
        FSV.size();
        List<Pair<String, Long>> testList = new ArrayList<Pair<String, Long>>();
        Pair<String, Long> testPair1 = new Pair<String, Long>(name1, tempFile1.length() );
        Pair<String, Long> testPair2 = new Pair<String, Long>(name2, tempFile2.length() );
        Pair<String, Long> testPair3 = new Pair<String, Long>(name3, tempFile3.length() );
        testList.add(testPair1);
        testList.add(testPair2);
        testList.add(testPair3);
        Assert.assertEquals(FSV.table, testList);
        FSV.getSum();
        Assert.assertEquals(FSV.sumSize, testSumSize);



//        List<String> tempFilesNames = new ArrayList<String>();
//        tempFilesNames.add("C:\\User\\notepad.exe");
//        FileSizeViewer testFile = new FileSizeViewer(tempFilesNames);
//        List<Long> testSizes;
//        testSizes = testFile.size();
//        List<Long> realSizes = new ArrayList<Long>();
//        realSizes.add(243200L);
//        assertEquals(realSizes, testSizes);
    }

}
