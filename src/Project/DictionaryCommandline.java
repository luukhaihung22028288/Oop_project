package Project;

public class DictionaryCommandline {
    public void dictionaryBasic()
    {
        DictionaryManagement d= new DictionaryManagement();
        d.insertFromCommandline();
        d.showAllWords();
    }
}
