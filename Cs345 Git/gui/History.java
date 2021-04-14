package gui;
import java.util.ArrayList;

public class History {
    private ArrayList<String> history;

    public History(){
        history = new ArrayList<String>();
    }
    public void add(String str){
        history.add(str);
    }
    public void reset() {
        history = new ArrayList<>();
    }
    public String toString() {
        if(history == null || history.size() == 0){
            return "";
        }
        String str = "";
        for(String s:history) {
            str += s +"\n";
        }
        return str;
    }
}
