import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Book {
    static int book_index = 0;
    int book_pos;
    ArrayList<String> lines;
    HashMap<String, Integer> word_frequency_dict;

    String[] stopwords;

    public Book(boolean actual){
        if(actual)
            book_index++;

        this.book_pos = book_index;

        lines = new ArrayList<String>();
        word_frequency_dict = new HashMap<String, Integer>();

        String current_line = "";

        try{
            File target = new File("stopWords.txt");
            Scanner fileReader = new Scanner(target);

            while(fileReader.hasNextLine()) {
                current_line = fileReader.nextLine();
            }
        }catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        stopwords = current_line.split(",");
    }

    public void addLine(String line){
        String current_word = "";

        int size = line.length();
        char character;

        int index = 0;
        int previous_value;

        //read the whole line character by character
        while(index < size){
            character = line.charAt(index);
            if(Character.isAlphabetic(character)){  //only read the actual alphabetical characters
                character = Character.toUpperCase(character);
                current_word += character;
            }
            else {
                if (!((Arrays.asList(stopwords)).contains(current_word.toLowerCase()))) { //check if the current word is not th list of stopwords. Only continue if that is the case
                    if ((this.word_frequency_dict).containsKey(current_word)) {
                        previous_value = word_frequency_dict.get(current_word);
                        word_frequency_dict.put(current_word, previous_value + 1);
                    } else {
                        word_frequency_dict.put(current_word, 1);
                    }

                }

                current_word = "";
            }


            index++;
        }



        lines.add(line);
    }

    public void display(){

        System.out.println("Book " + this.book_pos);
        System.out.println();

        for(String line:lines){
            System.out.println(line);
        }
    }


}
