package stariq.algorithms;

// Finding the top five words in a text file.
// Using a map to store all the unique words as keys and their frequency as values.
// Looping through the lines of text to find the top five values and their corresponding keys. 

import java.io.*;

public class TopWordsTest {

    public static void main(String[] args) throws IOException{
        TopWords.findTopWords("C:\\Users\\User\\Documents\\Projects\\IntelliJ IDEA\\LogicPile\\source\\TopWords\\Words.txt");
    }
}