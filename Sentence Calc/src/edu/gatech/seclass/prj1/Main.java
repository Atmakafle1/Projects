package edu.gatech.seclass.prj1;

import java.io.File;

public class Main {
  private static final String NEED_L = "You must pass an integer at least 0 after -l";
  private static final String NEED_D = "You must pass in delimiters after -d";
  private static final String NEED_FILE = "You must pass in the path to the essay file";
  private static final String ERROR_MSG = "\nSyntax: Main {path_to_essay} [-l min_length_for_word] [-d delimiters]";
  /**
   * This program takes in a path to a file, and then print out the average sentence length in the file
   * @param args
   */
  public static void main(String... args) {
    String delimiter = null;
    int minLength = -1;
    String essay = null;
    for (int i = 0; i < args.length; i++) {
      //process -l
      if (args[i].equals("-l")) {
        if (args.length > i+1) {
          try {
            minLength = Integer.valueOf(args[i+1]);
          } catch (Exception e) {
            System.err.println(NEED_L + ERROR_MSG);
            System.exit(1);
          }
        } else {
          System.err.println(NEED_L + ERROR_MSG);
          System.exit(1);
        }
      } else if (args[i].equals("-d")) {
        if (args.length > i+1) {
          try {
            delimiter = args[i+1];
          } catch (Exception e) {
            System.err.println(NEED_D + ERROR_MSG);
            System.exit(1);
          }
        } else {
          System.err.println(NEED_L + ERROR_MSG);
          System.exit(1);
        }
      } else if (i == 0 || (!args[i - 1].equals("-d") && !args[i].equals("-l"))) {
        essay = args[i];
      }
    }
    AvgSentenceLength asl = new AvgSentenceLength();
    if (essay == null) {
      System.err.println(NEED_FILE + ERROR_MSG);
      System.exit(1);
    } else {
      asl.setFile(new File(essay));
    }
    
    if (delimiter != null)
      asl.setSentenceDelimiters(delimiter);
    if (minLength != -1)
      asl.setMinWordLength(minLength);    
    int avg = asl.computeAverageSentenceLength();
    System.out.println("Total words: " + asl.getWordCount() + ". Total sentences: " + asl.getSentenceCount() + ". Average words per sentence: " + avg);
  }
}
