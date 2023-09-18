package Project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;


public class DictionaryManagement {
    private static ArrayList<Word> Dictionary = new ArrayList<>();
    int number_of_words = 0;
    Scanner input = new Scanner(System.in);
    public static void sortWordList() {
        Collections.sort(Dictionary, new Comparator<Word>() {
            @Override
            public int compare(Word word1, Word word2) {
                // So sánh theo thuộc tính target
                return word1.getWord_target().compareTo(word2.getWord_target());
            }
        });
    }

    public void add_word_to_dictionary(Word new_word) {
        Dictionary.add(new_word);
    }

    public String fixing_input(String input) {
        input = input.toLowerCase();
        input = input.trim();
        char firstChar = input.charAt(0);
        char upperFirstChar = Character.toUpperCase(firstChar);
        return upperFirstChar + input.substring(1);

    }

    public void insertFromCommandline() {

        int n;
        do {
            System.out.println("How many words you want to insert?");
            n = input.nextInt();
            number_of_words += n;
            if (n <= 0) {
                System.out.println("Invalid input. Please enter valid number.");
            }

        } while (n <= 0);
        input.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Word " + (i + 1) + " target-explain: ");
            String inputLine = input.nextLine();

            String[] parts = inputLine.split("-", 2);

            if (parts.length >= 2) {
                String Word_target = fixing_input(parts[0]);
                String Word_explain = fixing_input(parts[1]);
                Word New_word = new Word(Word_target, Word_explain);
                add_word_to_dictionary(New_word);
            } else {
                System.out.println("Invalid input. Please enter both target and explain separated by a hyphen.");
                i--;
            }
        }
    }

    public void showAllWords() {
        System.out.printf("%-7s | %-20s | %-30s%n", "N0", "English", "Vietnamese");
        int i = 1;

        sortWordList();
        for (Word word : Dictionary) {
            System.out.printf("%-7d | %-20s | %-30s%n", i, word.getWord_target(), word.getWord_explain());
            i++;
        }
    }

    public void removeFromCommandline(String remove_word) {
        Iterator<Word> iterator = Dictionary.iterator();
        while (iterator.hasNext()) {
            Word word = iterator.next();
            if (word.getWord_target().equals(remove_word)) {
                iterator.remove(); // Xóa đối tượng nếu target trùng khớp
                return;
            }
        }
        System.out.println("There is no word");

    }
    public void dictionaryinsertFromFile()
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String inputLine = line;

                String[] parts = inputLine.split("\t",2);

                if (parts.length >= 2) {
                    String Word_target = fixing_input(parts[0]);

                    String Word_explain = fixing_input(parts[1]);
                    Word New_word = new Word(Word_target, Word_explain);
                    add_word_to_dictionary(New_word);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void dictionaryExportToFile()
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            writer.write(String.format("%-4s | %-7s | %-10s%n", "No", "English", "Vietnamese"));
            int i=1;
            for (Word word : Dictionary) {
                writer.write(String.format("%-2d | %-6s | %-9s%n", i, word.getWord_target(), word.getWord_explain()));
                i++;
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void dictionaryUpdate(String update_word)
    {
        Iterator<Word> iterator = Dictionary.iterator();
        while (iterator.hasNext()) {
            Word word = iterator.next();
            if (word.getWord_target().equals(fixing_input(update_word))) {
                System.out.println("Input new target-explain");
                String inputLine = input.nextLine();

                String[] parts = inputLine.split("-",2);

                if (parts.length >= 2) {
                    String Word_target = fixing_input(parts[0]);

                    String Word_explain = fixing_input(parts[1]);

                    word.setWord_target(Word_target);

                    word.setWord_explain(Word_explain);


                } else {
                    System.out.println("Invalid input. Please enter both target and explain separated by a hyphen.");
                }
            }
        }

    }
    public void dictionaryLookup(String look_up_word) {
        Iterator<Word> iterator = Dictionary.iterator();
        while (iterator.hasNext()) {
            Word word = iterator.next();
            if (word.getWord_target().equals(fixing_input(look_up_word))) {
                System.out.println(word.getWord_target() + ": " + word.getWord_explain());
                return;
            }
        }
        System.out.println("There is no word");
    }

    private static String translate(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbz5XOgKRoSF8p45rgNUyMpvEIyiY7wTrLJypLVsuQDm7PbCap-y9yu8N0tu_EVeNOhU/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
    public void dictionaryTrans(String text) throws IOException {
        System.out.println("Translated text: " + translate("en", "vi", text));
    }



}
