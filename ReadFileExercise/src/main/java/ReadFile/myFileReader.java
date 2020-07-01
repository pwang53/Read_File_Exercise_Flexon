
package ReadFile;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Contributor: Peiqi Wang
 * Github: https://github.com/pwang53
 * Date: 06/30/2020
 *
 * Description:
 *      1. Write a program to read a file from a location
 *      2. Find all the words that appeared more than 5 times and are more than 3 characters in length.
 *      3. Populate a arraylist with all those words after reversing the words
 *      4. Iterate the arraylist and print.
 **/

public class myFileReader {
    private ArrayList<String> list;
    private String message;
    private String URL;

    public myFileReader(String url) {
        URL = url;
    }

    public void openFile() {
        // Initialize the list
        list = new ArrayList<>();

        // Get the file
        File file = new File(URL);

        // Create HashMap to store the number of appearance
        HashMap<String, Integer> map = new HashMap<>();

        // Design a pattern to remove if find a string contains ',' and '.'
        // It will be faster if use pattern
        // ******
        // ** https://stackoverflow.com/questions/6262397/string-replaceall-is-considerably-slower-than-doing-the-job-yourself
        // ** Note that using String.replaceAll() will compile the regular expression each time you call it.
        // ** You can avoid that by explicitly using a Pattern object
        // ******
        Pattern p = Pattern.compile("[,.']+");

        // Handle the IOException
        try {
            // Read the file
            FileReader reader = new FileReader(file);
            message = "Successfully Read the File\n";
            System.out.println(message);
            // Buffer read
            BufferedReader bf = new BufferedReader(reader);

            // Declare string to store the line
            String line;

            // count the appearance of each word in each line
            while ((line = bf.readLine()) != null){
                // Use pattern to remove , . and '
                line = p.matcher(line).replaceAll(" ");

                // Count the appearance
                countAppearTimes(line, map);
            }

            populateWords(map);
        } catch (IOException e){
            message = "There has no \"myText.txt\" file in the same directory with .jar file, please try again\n";
            System.out.println(message);
        }

    }

    private void countAppearTimes(String line, HashMap<String, Integer> map) {
        String[] words = line.split(" ");
        for (String str : words) {
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str, 1);
            }
        }
    }

    private void populateWords(HashMap<String, Integer> map) {
        for (String str : map.keySet()) {
            if (map.get(str) > 5 && str.length() > 3) {
                list.add(reverseWords(str));
            }
        }
    }
    private String reverseWords(String word) {
        StringBuilder sb = new StringBuilder();
        for (int i = word.length() - 1; i >= 0; i--) {
            sb.append(word.charAt(i));
        }
        return sb.toString();
    }

    public ArrayList<String> getWordList() {
        return list;
    }

    public String getMessage() {
        return message;
    }
}
