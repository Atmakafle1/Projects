package edu.gatech.seclass.prj1;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AvgSentenceLengthTestExtra {

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
        asl.setFile(new File(fileDir + "multi.txt"));
        assertEquals(6, asl.computeAverageSentenceLength(), 0);
    }

    @Test
    public void testComputeAverageSentenceLength2() {
        asl.setFile(new File(fileDir + "essay.txt"));
        assertEquals(10, asl.computeAverageSentenceLength(), 0);
    }

    @Test
    public void testComputeAverageSentenceLength3() {
        asl.setFile(new File(fileDir + "essay.txt"));
        asl.setMinWordLength(5);
        assertEquals(5, asl.computeAverageSentenceLength(), 0);
    }

    @Test
    public void testComputeAverageSentenceLength4() {
        asl.setFile(new File(fileDir + "numbers.txt"));
        asl.setSentenceDelimiters("/|");
        asl.setMinWordLength(1);
        assertEquals(2, asl.computeAverageSentenceLength(), 0);
    }

    @Test
    public void testComputeAverageSentenceLength5() {
        asl.setFile(new File(fileDir + "case1.txt"));
        asl.setSentenceDelimiters(".");
        asl.setMinWordLength(1);
        assertEquals(7, asl.computeAverageSentenceLength(), 0);
    }

    @Test
    public void testComputeAverageSentenceLength6() {
        asl.setFile(new File(fileDir + "case2.txt"));
        asl.setSentenceDelimiters(".?!");
        asl.setMinWordLength(2);
        assertEquals(4, asl.computeAverageSentenceLength(), 0);
    }

    @Test
    public void testComputeAverageSentenceLength7() {
        asl.setFile(new File(fileDir + "case3.txt"));
        asl.setSentenceDelimiters(".");
        asl.setMinWordLength(3);
        assertEquals(4, asl.computeAverageSentenceLength(), 0);
    }
}
