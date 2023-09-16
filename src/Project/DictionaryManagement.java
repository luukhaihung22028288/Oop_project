package Project;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
public class DictionaryManagement {
    private ArrayList<Word> Dictionary = new ArrayList<>();
     int number_of_words=0;
    public void add_word_to_dictionary  (Word new_word)
    {
        Dictionary.add(new_word);
    }
    public String fixing_input  (String input)
    {
        input = input.toLowerCase();
        char firstChar = input.charAt(0);
        char upperFirstChar = Character.toUpperCase(firstChar);
        return upperFirstChar + input.substring(1);
    }

    public void insertFromCommandline()
    {
        Scanner input=new Scanner(System.in);
        int n;
        do {
            System.out.println("How many words you want to insert?");
            n = input.nextInt();
            number_of_words+=n;
            if (number_of_words <= 0) {
                System.out.println("Invalid input. Please enter valid number.");
            }
        } while (n<= 0);
        input.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Word " + (i + 1) + " target-explain: ");
            String inputLine = input.nextLine();

            String[] parts = inputLine.split("-",2);

            if (parts.length >= 2) {
                String Word_target = fixing_input(parts[0]);

                String Word_explain = fixing_input(parts[1]);
                Word New_word = new Word(Word_target, Word_explain);
                add_word_to_dictionary(New_word);
            } else {
                System.out.println("Invalid input. Please enter both target and explain separated by a hyphen.");
            }
        }
    }
    public void showAllWords()
    {
        System.out.printf("%-4s | %-7s | %-10s%n", "No", "English", "Vietnamese");
        int i=1;
        for (Word word : Dictionary) {
            System.out.printf("%-2d | %-6s | %-9s%n", i, word.getWord_target(), word.getWord_explain());
            i++;
        }
    }
}
