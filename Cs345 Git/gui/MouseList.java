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
      case "About":
        PopUp.infoBox("Info", "About");
        break;
      case "Help":
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
        language = ENGLISH;
        PopUp.infoBox("English selected", "Language");
        break;
      case "Español":
        language = SPANISH;
        PopUp.infoBox("Español seleccionado", "Idioma");
        break;
      case "Français":
        language = FRENCH;
        PopUp.infoBox("Français sélectionné", "Langue");
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
