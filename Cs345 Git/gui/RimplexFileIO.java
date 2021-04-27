package gui;

import java.io.*;

public class RimplexFileIO {
    private final File myFile;

    public RimplexFileIO() {
        myFile = new File("/Users/ulisesfernandez/IdeaProjects/CS345/src/ColorScheme.txt");
    }
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
