package gui;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.io.IOException;

/**
 * MainFrame - .
 * 
 * @author Ulises Fernandez, Andrew Elbert, Ian Lips, Adrien Ponce
 * @version (3/31/21)
 */
public class MainFrame extends JFrame
{
  /**
   * Default UID since I'm not sure what it should be instantiated to.
   */
  private static final long serialVersionUID = 1L;
  private static MainFrame frame;
  private JPanel mainPanel;
  private MouseList mouse = new MouseList();
  
  private JMenuBar menuBar;
  private JMenu help;
  private JMenu language;
  private JMenuItem english;
  private JMenuItem spanish;
  private JMenuItem french;
  private JMenuItem about;

  /**
   * Default constructor for the MainFrame.
   */
  private MainFrame() throws IOException 
  {
    createComponents(); // create needed objects
    
    language.add(english);
    language.add(spanish);
    language.add(french);
    
    help.add(about);

    menuBar.add(help);
    menuBar.add(language);

    about.addMouseListener(mouse);
    help.addMouseListener(mouse);
    language.addMouseListener(mouse);
    english.addMouseListener(mouse);
    spanish.addMouseListener(mouse);
    french.addMouseListener(mouse);
    
    setJMenuBar(menuBar);
    
    setSize(350, 450);
    getContentPane().add(mainPanel, BorderLayout.CENTER);
    setTitle("Rimplex");
    setVisible(true); // display this
    centerForm(); // center is on the screen to begin
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // make exit button terminate the
                                                             // Rimplex program

  } // constructor

  /**
   * Puts the Frame in the center of the screen.
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
   * Creates the panel to be displayed.
   */
  private void createComponents()
  {
    try {
    mainPanel = MainPanel.getInstance();
    } catch (IOException ioe) {
      System.err.print("Color text file not found");
    }
    menuBar = new JMenuBar();
    help = new JMenu("Help");
    language = new JMenu("Language");
    about = new JMenuItem("About");
    english = new JMenuItem(Language.ENGLISH.getToken());
    spanish = new JMenuItem(Language.SPANISH.getToken());
    french = new JMenuItem(Language.FRENCH.getToken());
  }

  /**
   * Getter for a single instance of the MainFrame to implement the Singleton pattern so that only
   * one instance of the Frame can be active.
   *
   * @return frame the new MainFrame instance
   */
  public static MainFrame getInstance() throws IOException
  {
    if (frame == null)
    {
      frame = new MainFrame();
    }

    return frame;
  }

}
