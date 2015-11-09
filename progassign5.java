/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package progassign5;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author Monte
 */
public class ProgAssign5<E extends Comparable<E>>{
    //Declaring attributes
    BinarySearchTree[] dictionary = new BinarySearchTree[26];
    long time = 0;
    double wordsFound;
    double wordsNotFound;
    double comparisonsNotFound;
    double comparisonsFound;

    
    //construct
    public ProgAssign5(){
        for (int i = 0; i < dictionary.length; i++) {
            dictionary[i] = new BinarySearchTree<E>();
        }
    }

    //main
    public static void main(String[] args) {
        ProgAssign5 programmingAssignment5 = new ProgAssign5();
        programmingAssignment5.time();
        programmingAssignment5.readDictionary();
        programmingAssignment5.readOliver();
        programmingAssignment5.printData();
        programmingAssignment5.time();
    }
    
    /*
    @pre-condition: begins a timer at program start, time method called a second time then 
    the difference is calculated.
    @post-condition: The difference in time is then used to find the minutes
    and seconds, and output them.
    */
    public void time(){
        if(time != 0){
            time = System.currentTimeMillis() - time;
            time = time/1000;
            int seconds = (int)(time%60);
            int minutes = (int)((time-seconds)/60);
            System.out.println("\nRun time: " + minutes + " minutes " + seconds + " seconds");
        }
        else{
            time = System.currentTimeMillis();
        }
    }

    /*
    @pre-condition: reads dictionary file
    @post-condition: each word is alligned alphabetically in list according to 
    the first character.
    */
    public void readDictionary() {
        File f = new File("random_dictionary.txt");
        try {
            Scanner input = new Scanner(f); 
            while (input.hasNext()) {
                String s = input.nextLine().toLowerCase();
                dictionary[(int) s.charAt(0) - 97].insert(s);
            }
            input.close();
        }
        catch (IOException e) {
            System.out.println("Could not find random_dictionary.txt file.");
            e.printStackTrace();
        }
    }

    
    /*
    @pre-condition: reads oliver.txt one word at a time.
    @post-condition: Depending if the word being read is in one of the 26 
    dictionaries or not, the counter increments.
    */
    public void readOliver() {
        System.out.println("Loading, Please Wait...");
        String fileName = "oliver.txt";
        try {
            FileInputStream inf = new FileInputStream(new File(fileName));
            char let;
            String word = "";
            int n = 0;
            while ((n = inf.read()) != -1) {
                let = (char) n;
                if (Character.isLetter(let)) {
                    word += Character.toLowerCase(let);
                }
                if ((let == 10 || let == 45 || let == 32) && !word.isEmpty()) {
                    word = word.toLowerCase();
                    int x = dictionary[(int) word.charAt(0) - 97].compare(word); 
                    if (dictionary[(int) word.charAt(0) - 97].search(word)){
                        comparisonsFound += x;
                        wordsFound++;
                    }
                    else{
                        comparisonsNotFound += x;
                        wordsNotFound++;
                    }
                    word = "";
                }
            }
            inf.close();
        }
        catch (IOException e) {
            System.out.println("oliver.txt cannot be found, check location.");
            e.printStackTrace();
        }
    }
    /*
    @pre-condition: the comparisions and words have been counted, it then 
    calculates the averages. 
    @post-condition: print outputs
    */
    public void printData() {
        System.out.printf("average number of comparisons found: %.2f", (comparisonsFound/wordsFound));
        System.out.printf("\naverage number of comparisons not found: %.2f", (comparisonsNotFound / wordsNotFound));  
        System.out.printf("\nnumber of words found: %.2f", +wordsFound);
        System.out.printf("\nnumber of words not found: %.2f", +wordsNotFound);
    }
}
