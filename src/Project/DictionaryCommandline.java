package Project;
import java.util.Scanner;
public class DictionaryCommandline {

    public static void main(String[] args) {
        DictionaryManagement d = new DictionaryManagement();
        d.dictionaryinsertFromFile();
        d.showAllWords();
        d.insertFromCommandline();
       // Scanner input=new Scanner(System.in);
       // String s=input.nextLine();

        d.dictionaryExportToFile();

    }
}
