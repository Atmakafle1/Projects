package edu.gatech.seclass.prj1;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;



public class AvgSentenceLengthTest {

    private AvgSentenceLength asl;
    private String fileDir;
    
    @Before
    public void setUp() throws Exception {
        asl = new AvgSentenceLength();
        fileDir = "test" + File.separator + "inputfiles" + File.separator;
    }
  
    @After
    public void tearDown() throws Exception {
        asl = null;
        fileDir = null;
    }
    @Test
    public void testComputeAverageSentenceLength1() {
        String comment = "Testing sentences that span multiple lines";
        asl.setFile(new File(fileDir + "input.txt"));
        assertEquals(comment, 7, asl.computeAverageSentenceLength(), 0);
    }
    @Test
    public void testComputeAverageSentenceLength2() {
        String comment = "Testing customized delimiters";
        asl.setFile(new File(fileDir + "input.txt"));
        asl.setSentenceDelimiters("%.");
        assertEquals(comment, 3, asl.computeAverageSentenceLength(), 0);
    }
    @Test
    public void testComputeAverageSentenceLength3() {
        String comment = "Testing customized minimal word length";
        asl.setFile(new File(fileDir + "input.txt"));
        asl.setMinWordLength(5);
        assertEquals(comment, 3, asl.computeAverageSentenceLength(), 0);
    }
    @Test
    public void testComputeAverageSentenceLength4() {
        String comment = "Testing multiple delimiters";
        asl.setFile(new File(fileDir + "multidelim.txt"));
        asl.setSentenceDelimiters("%#");
        asl.setSentenceDelimiters("%$");
        asl.setSentenceDelimiters("%,");
        asl.setSentenceDelimiters("()");
        asl.setSentenceDelimiters("%.");
        assertEquals(comment, 3, asl.computeAverageSentenceLength(), 0);
    }
    @Test
    public void testComputeAverageSentenceLength5() {
        String comment = "Testing delimiters in the middle of sentence";
        asl.setFile(new File(fileDir + "middle.txt"));
        asl.setSentenceDelimiters("%.");
        assertEquals(comment, 2, asl.computeAverageSentenceLength(), 0);
    }
         
    @Test
    public void setSentenceDelimiters1() {
        String comment = "Testing set delimiters";
        String sentenceDelimiters = "!?.";
        asl.setSentenceDelimiters(sentenceDelimiters);
        assertEquals(comment,"!?.", asl.getSentenceDelimiters());
    }

    @Test(expected = NullPointerException.class)
    public void setSentenceDelimiters2(){
    	//Testing if no delimiter was passed
    	String sentenceDelimiters = null;
    	asl.setSentenceDelimiters(sentenceDelimiters);
    }
}
