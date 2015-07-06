package edu.gatech.seclass.prj1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class AvgSentenceLength {
  private File file;
  private String sentenceDelimiters;
  private int minWordLength;
  private HashSet<Character> delimiterSet;
  private int wordCount;
  private int sentenceCount;
  
  public static final Character SPACE = ' ';
  public static final Character TAB = '\t';
  public static final Character NEWLINE = '\n';
  
  public AvgSentenceLength() {
    file = null;
    sentenceDelimiters = ".";
    delimiterSet = new HashSet<Character>();
    delimiterSet.add('.');
    minWordLength = 3;
    wordCount = 0;
    sentenceCount = 0;
  }

  public int getWordCount() {
    return wordCount;
  }

  public void setWordCount(int wordCount) {
    this.wordCount = wordCount;
  }

  public int getSentenceCount() {
    return sentenceCount;
  }

  public void setSentenceCount(int sentenceCount) {
    this.sentenceCount = sentenceCount;
  }

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  public String getSentenceDelimiters() {
    return sentenceDelimiters;
  }

  public void setSentenceDelimiters(String sentenceDelimiters) {
    this.sentenceDelimiters = sentenceDelimiters;
    delimiterSet = new HashSet<Character>();
    for (int i = 0; i < sentenceDelimiters.length(); i++) {
      delimiterSet.add(sentenceDelimiters.charAt(i));
    }
    
  }

  public int getMinWordLength() {
    return minWordLength;
  }

  public void setMinWordLength(int minWordLength) {
    this.minWordLength = minWordLength;
  }
  
  /**
   * This function calculate the average sentence length
   * @return
   */

  public int computeAverageSentenceLength() {
    this.sentenceCount = 0;
    this.wordCount = 0;
    int previousPos = 0;
    boolean inWord = false;
    //we use inWord state to deal with multiple white spaces between
    //words. For example "apple   orange". The variable previousPos should point
    //to the begin of each word.
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line = null;
      while ((line = br.readLine()) != null) {
        previousPos = 0;
        for (int i = 0; i < line.length(); i++) {
          //check for word separators
          if (line.charAt(i) == SPACE || line.charAt(i) == TAB 
              || line.charAt(i) == NEWLINE) {
            
            //we have a new word
            if (i - previousPos >= minWordLength && inWord == true) {
              wordCount++;
            }
            inWord = false;
          } else if (delimiterSet.contains(line.charAt(i))) {
            //End of a sentence
            if (i - previousPos >= minWordLength && inWord == true) {
              wordCount++;
            }
            sentenceCount++;
            inWord = false;
          } else if (!inWord) {
            inWord = true;
            previousPos = i;
          }
        }
        
      }
      //in case user forgot to add delimiter at the end of the file
      if (inWord) {
        if (line.length() - previousPos >= minWordLength && inWord == true) {
          wordCount++;
        }
        sentenceCount++;
      }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("wordCount = " + wordCount);
    System.out.println("sentenceCount = " + sentenceCount);
    if (this.wordCount == 0 && this.sentenceCount == 0)
      return 0;
    else
     return (this.wordCount / this.sentenceCount);
  }
}
