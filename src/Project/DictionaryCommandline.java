package Project;
import java.io.IOException;
import java.util.Scanner;
public class DictionaryCommandline {

    public static void main(String[] args) throws IOException {
        DictionaryManagement d = new DictionaryManagement();
       // d.dictionaryinsertFromFile();
        //d.showAllWords();
       // d.insertFromCommandline();
        Scanner input=new Scanner(System.in);
        String s=input.nextLine();

       // d.dictionaryExportToFile();
       d.dictionaryTrans(s);
    }

}
