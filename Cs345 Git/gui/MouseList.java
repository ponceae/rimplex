package gui;


import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MouseList implements MouseListener {

    private static int language;
    private final int ENGLISH = 1;
    private final int FRENCH = 2;
    private final int SPANISH = 3;
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        JMenu menu = (JMenu) e.getSource();
        String name = menu.getText();
        switch(name) {
            case "About":
                PopUp.infoBox("Info","About");
                break;
            case "Help":
                try {
                    getHelp();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (URISyntaxException uriSyntaxException) {
                    uriSyntaxException.printStackTrace();
                }
                break;
            case "English":
              language = ENGLISH;
                break;
            case "French":
              language = FRENCH;
                break;
            case "Spanish":
              language = SPANISH;
                break;
            default:
                break;
        }
        
    }
    
    public static int getLanguage() {
      return language;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
     private void getHelp() throws IOException, URISyntaxException {
        Desktop desktop = Desktop.getDesktop();
         // can be changed to any address
        URI     uri     = new URI("https://www.google.com/webhp?hl=en&ictx=2&sa=X&ved=0ahUKEwjW67yRyJzwAhViUd8KHRncCjoQPQgI");
        desktop.browse(uri);
    }
}
