import java.util.*;
import java.io.*;

/*
 * Time complexity : O(n) where n is number of lines in text file
 * Space complexity: O(2n + m) where n represents hashmap space and m represents hashset space
 */

public class Question1{
    public static void main(String[] args) throws Exception {
        String line = "";  
        String splitBy = ",";  
        BufferedReader br = new BufferedReader(new FileReader("C://Users//sjsrg//Downloads//Compressed//Exeter//french_dictionary.csv"));  
        HashMap<String, String> h = new HashMap<>();
        while ((line = br.readLine()) != null) {  
            String[] values = line.split(splitBy);  
            h.put(values[0], values[1]);
        }  

        Scanner sc = new Scanner(new File("C://Users//sjsrg//Downloads//Compressed//Exeter//t8.shakespeare.txt"));
        StringBuffer buffer = new StringBuffer();
        while (sc.hasNextLine()) {
            buffer.append(sc.nextLine()+System.lineSeparator());
        }
        String fileContents = buffer.toString();

        HashSet<String> words = new HashSet<>();  // unique words
        HashMap<String, Integer> numberOfReplaced = new HashMap<>(); // number of times replaced

        sc = new Scanner(new File("C://Users//sjsrg//Downloads//Compressed//Exeter//find_words.txt"));
        while(sc.hasNextLine()){
            String temp = sc.nextLine();
            if(fileContents.contains(temp)){
                words.add(temp);
                numberOfReplaced.put(temp, numberOfReplaced.getOrDefault(temp, 0) + 1);
            }
            fileContents = fileContents.replaceAll(temp, h.get(temp));
        }

        FileWriter fw = new FileWriter("C://Users//sjsrg//Downloads//Compressed//Exeter//t8.shakespeare.txt");
        fw.write(fileContents);
        System.out.println("Done");

        fw = new FileWriter("C://Users//sjsrg//Downloads//Compressed//Exeter//frequency.txt");
        for(Map.Entry<String, Integer> m : numberOfReplaced.entrySet()){
            fw.write(m.getKey() + ", " + h.get(m.getKey()) + ", " +  m.getValue());
            System.out.println(m.getKey() + ", " + h.get(m.getKey()) + ", " +  m.getValue());
        }

        System.out.println("\nUnique words replaced: ");
        System.out.println(words);
    }   
}
