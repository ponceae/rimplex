package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MouseList implements MouseListener
{

  private static int language;
  private final int ENGLISH = 1;
  private final int FRENCH = 2;
  private final int SPANISH = 3;

  @Override
  public void mouseClicked(MouseEvent e)
  {

  }

  @Override
  public void mousePressed(MouseEvent e)
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
        if (language == ENGLISH)
        {
          PopUp.infoBox("Information", "About");
        }
        else if (language == SPANISH)
        {
          PopUp.infoBox("Informaci�n", "Sobre");
        }
        else if (language == FRENCH)
        {
          PopUp.infoBox("L'information", "Environ");
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
      case "Fran�ais":
        Language.setLanguage(Language.FRENCH);
        PopUp.infoBox(Language.getDialog(Language.SELECTION), "Langue");
        break;
      case "Espa�ol":
        Language.setLanguage(Language.SPANISH);
        PopUp.infoBox(Language.getDialog(Language.SELECTION), "Idioma");
        break;
      default:
        break;
    }

  }

  public static int getLanguage()
  {
    return language;
  }

  @Override
  public void mouseReleased(MouseEvent e)
  {

  }

  @Override
  public void mouseEntered(MouseEvent e)
  {

  }

  @Override
  public void mouseExited(MouseEvent e)
  {

  }

  private void getHelp() throws IOException, URISyntaxException
  {
    Desktop desktop = Desktop.getDesktop();
    // can be changed to any address
    URI uri = new URI(
        "https://docs.google.com/document/d/1d7B4iWx-vDPFH64_n2Jnr7i6E0APZuD24ZHs8OEGb24/edit?usp=sharing");
    desktop.browse(uri);
  }
}
