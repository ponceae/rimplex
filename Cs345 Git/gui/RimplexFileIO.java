package gui;

import java.io.*;
/**
 * Rimplex file io.
 *
 */
public class RimplexFileIO {
    private final File myFile;
     /**
      * default constructor.
      *
      */
    public RimplexFileIO() {
        myFile = new File("ColorScheme.txt");
    }
     /**
      * read a file and place lines into a string array.
      *
      */
    public String[] getEntries() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(myFile));
        String str = "";
        String line = reader.readLine();
        while ( line != null ) {
            str += line + "@@@@";
            line = reader.readLine();
        }
        String[] lines = str.split( "@@@@" );
        return lines;
    }
     /**
      * return an int[] of the numbers in a line.
      *
      */
    public int[] getBackground(int lineNum) throws IOException {
        int [] color = {0,0,0};
        String[] entries = getEntries();
        String[] line = entries[lineNum].split("#");

        int j = 0;
        for(String s: line){
            System.out.println(s);
            try {
                int i = Integer.parseInt(s);
                if(i >= 0 && i <= 255) {
                    color[j] = i;
                    j++;
                }
            }catch (NumberFormatException n) {

                System.out.println("h");
                return color;
            }

        }
        return color;
    }

}
