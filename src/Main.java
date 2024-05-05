
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
    //1. read all lines in the ParadiseLost file
        /*
        try {
            File myObj = new File("ParadiseLost.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
*/

        //2. divide the file into books
        Book current_book = new Book(false);
        ArrayList<Book> books = new ArrayList<Book>();
        try{
            File target = new File("ParadiseLost.txt");
            Scanner fileReader = new Scanner(target);

            while(fileReader.hasNextLine()){
                String current_line = fileReader.nextLine();
                if(current_line != "\n" && current_line != ""){
                    if(current_line.contains("Book ") && current_line.indexOf("Book ") == 0){
                        current_book = new Book(true);
                        books.add(current_book);

                    }
                    else{
                        current_book.addLine(current_line);
                    }
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Book target_book = books.get(0);

        target_book.display();

        System.out.println();

        for(Map.Entry<String, Integer> entry : (target_book.word_frequency_dict).entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }
}
