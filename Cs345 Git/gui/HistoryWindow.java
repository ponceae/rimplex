package gui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class HistoryWindow extends JFrame
{
  private static final long serialVersionUID = 1L;
  private static HistoryWindow histWindow;
  private Point location;
  private History theHistory;
  private JPanel thisMain;
  private JTextArea text;
  private JButton closeButton;
  
  private HistoryWindow() {
    createComponents(); // create needed objects
    setSize(250, 350);
    getContentPane().add(thisMain, BorderLayout.CENTER);
    placeFrame();
    setVisible(true); // display this
  }
  
  /**
   * Creates the panel to be displayed.
   */
  private void createComponents()
  {
    closeButton = new JButton("<");
    closeButton.setActionCommand("<");
    closeButton.addActionListener(Listener.getInstance());
    
    theHistory = History.getInstance();
    
    text = new JTextArea();
    text.setText(theHistory.toString());
    text.setEditable(false);
    
    thisMain = new JPanel();
    thisMain.setLayout(new BorderLayout());
    thisMain.add(text, BorderLayout.CENTER);
    thisMain.add(closeButton, BorderLayout.EAST);
  }
  
  /**
   * Puts the Frame in the center of the screen.
   */
  private void placeFrame()
  {
    this.setVisible(false);
    location = MainPanel.getPosition();
    int x = location.x;
    int y = location.y;
    this.setLocation(x + 300, y + 25);
    this.setVisible(true);
    
  }
    
  
  public static HistoryWindow getInstance() {
    if (histWindow == null) {
      histWindow = new HistoryWindow();
    }
    return histWindow;
  }
  
  void close() {
    setVisible(false);
  }

}
