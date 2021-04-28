package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Mouse listener.
 * 
 * @author z
 * @version 4/28
 */
public class MouseList implements MouseListener
{

  private static String about = "about";
  
  private static int language;
  private final int english = 1;
  private final int french = 2;
  private final int spanish = 3;

  @Override
  public void mouseClicked(final MouseEvent e)
  {

  }

  @Override
  public void mousePressed(final MouseEvent e)
  {
    String name;
    if (e.getSource() instanceof JMenu)
    {
      JMenu menu = (JMenu) e.getSource();
      name = menu.getText();
    }
    else
    {
      JMenuItem item = (JMenuItem) e.getSource();
      name = item.getText();
    }

    switch (name)
    {     
      case "Help":
        if (language == english)
        {
          PopUp.infoBox("Information", about);
        }
        else if (language == spanish)
        {
          PopUp.infoBox("Informacion", "Sobre");
        }
        else if (language == french)
        {
          PopUp.infoBox("L information", "Environ");
        }
        break;       
      case "About":
        try
        {
          getHelp();
        }
        catch (IOException ioException)
        {
          ioException.printStackTrace();
        }
        catch (URISyntaxException uriSyntaxException)
        {
          uriSyntaxException.printStackTrace();
        }
        break;
      case "English":
        Language.setLanguage(Language.ENGLISH);
        PopUp.infoBox(Language.getDialog(Language.SELECTION), "Language");
        break;
      case "Francais":
        Language.setLanguage(Language.FRENCH);
        PopUp.infoBox(Language.getDialog(Language.SELECTION), "Langue");
        break;
      case "Espanol":
        Language.setLanguage(Language.SPANISH);
        PopUp.infoBox(Language.getDialog(Language.SELECTION), "Idioma");
        break;
      default:
        break;
    }

  }

  /**
   * @return the language
   */
  public static int getLanguage()
  {
    return language;
  }

  @Override
  public void mouseReleased(final MouseEvent e)
  {

  }

  @Override
  public void mouseEntered(final MouseEvent e)
  {

  }

  @Override
  public void mouseExited(final MouseEvent e)
  {

  }

  private void getHelp() throws IOException, URISyntaxException
  {
    Desktop desktop = Desktop.getDesktop();
    // can be changed to any address
    URI uri = new URI(
        "http://w3stu.cs.jmu.edu/clevelcd/Home.html");
    desktop.browse(uri);
  }
}
