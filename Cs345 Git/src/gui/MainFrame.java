package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.*;

/**
 * MainFrame - .
 * 
 * @author Prof. David Bernstein, James Madison University
 * 
 * @version 1.0
 */
public class MainFrame extends JFrame
{

  private static MainFrame frame;
  private JPanel mainPanel;

  private MainFrame()
  {

    createComponents(); // create needed objects
    setSize(600, 250);
    getContentPane().add(mainPanel, BorderLayout.CENTER);
    // getContentPane().add(new JLabel(new ImageIcon("iconRimplex.png")), BorderLayout.NORTH);
    setTitle("Rimplex");
    setVisible(true); // display this
    centerForm(); // center is on the screen to begin
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // make exit button terminate the
                                                             // Rimplex program

  } // constructor

  /**
   * Puts the Frame in the center of the screen.
   * 
   */
  private void centerForm()
  {

    Dimension dimScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension dimFrameSize = getSize();

    if (dimFrameSize.height > dimScreenSize.height)
    {
      dimFrameSize.height = dimScreenSize.height;
    }
    if (dimFrameSize.width > dimScreenSize.width)
    {
      dimFrameSize.width = dimScreenSize.width;
    }

    setLocation((dimScreenSize.width - dimFrameSize.width) / 2,
        (dimScreenSize.height - dimFrameSize.height) / 2);

  }

  /**
   * the panel to be displayed. simply instantiate to view.
   */
  private void createComponents()
  {

    // create a panel and implement here.

    mainPanel = new MainPanel();
  }

  /**
   * get instance method.
   *
   * @return frame the new MainFrame instance
   */
  public static MainFrame getInstance()
  {
    if (frame == null)
    {
      frame = new MainFrame();
    }

    return frame;
  }

}
