package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * MainFrame - .
 * 
 * @author Ulises Fernandez, Andrew Elbert, Ian Lips
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

  /**
   * Default constructor for the MainFrame.
   */
  private MainFrame()
  {
    JMenuBar menuBar = new JMenuBar();
    JMenu about = new JMenu("About");
    JMenu help = new JMenu("Help");
    JMenu language = new JMenu("Language");

    language.add(new JMenuItem("English"));
    language.add(new JMenuItem("French"));
    language.add(new JMenuItem("German"));

    menuBar.add(about);
    menuBar.add(help);
    menuBar.add(language);

    about.addMouseListener(mouse);
    help.addMouseListener(mouse);
        //language.addMouseListener(mouse);
    createComponents(); // create needed objects
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
    mainPanel = MainPanel.getInstance();
  }

  /**
   * Getter for a single instance of the MainFrame to implement the Singleton pattern so that only
   * one instance of the Frame can be active.
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
