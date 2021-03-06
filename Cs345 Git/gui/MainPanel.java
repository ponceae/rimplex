package gui;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;

/**
 * MainPanel - .
 *
 * @author Ulises Fernandez, Andrew Elbert, and Ian Lips
 * @version (3/31/21)
 */

public class MainPanel extends JPanel
{
  
  /**
   * Default UID since I'm not sure what it should be instantiated to.
   */
  private static final long serialVersionUID = 1L;

  private static MainPanel main;

  // Thinking of doing two TextPanes in one Panel, one for running and/or previous calculations and
  // one for user input
  private static JTextPane input;
  private static JTextPane display;
  private static JTextArea output;
  
  
  private static String dash = "-";
  private static String code1 = "\u00D7";  
  private static String code2 = "\u00F7";  
  private static String html = "<html><i>i</i></html>";  
  private static String right = ">";
  
  private Color background;


  private JPanel panel;
  private JPanel east;
  private JPanel west;
  private JPanel north;

  private Listener theListener;
  private GridBagConstraints constraints;

  private ImageIcon logo;
  private JLabel rimpLogo;
  


  /**
   * Default Constructor.
   */
  private MainPanel()  throws IOException 
  {
    createComponents();
    setParameters();
    setPanel();
    addComponents();
    setListenersAndActions();
  }

  /**
   * Creates the Components.
   */
  protected void createComponents()  throws IOException 
  {
    // RimplexFileIO rF = new RimplexFileIO();
    // int[] rgb = rF.getBackground(0);
    // background = new Color(rgb[0],rgb[1],rgb[2]);
    background = new Color(150,0,0);

    north = new JPanel(new GridLayout(3, 1));
    east = new JPanel();
    west = new JPanel();
    panel = new JPanel(new GridBagLayout());

    north.setBackground(Color.WHITE);
    east.setBackground(background);
    west.setBackground(background);
    panel.setBackground(background);

    theListener = Listener.getInstance();
    constraints = new GridBagConstraints();

    input = new JTextPane();
    display = new JTextPane();
    output = new JTextArea();

    display.setBackground(background);
    display.setForeground(Color.WHITE);
    display.setEditable(false);

    // logo = new ImageIcon(getClass().getClassLoader().getResource("logoRimplex.png"));
    rimpLogo = new JLabel(logo);

  }

  /**
   * Sets the Parameters.
   */
  protected void setParameters()
  {
    for (Component theButton : panel.getComponents())
    {
      JButton button = (JButton) theButton;
      button.setBackground(Color.WHITE);
    }

  }

  /**
   * Sets the Panels.
   */
  protected void setPanel()
  {
    setLayout((new BorderLayout()));

    add(north, BorderLayout.NORTH);
    add(panel, BorderLayout.CENTER);
    add(west, BorderLayout.WEST);
    add(east, BorderLayout.EAST);

  }

  /**
   * Adds Components.
   */
  protected void addComponents()
  {
    display.add(output);
    north.add((rimpLogo));
    north.add(display);
    north.add(input);
    constraints.weightx = 1;
    constraints.weighty = 1;
    constraints.fill = GridBagConstraints.BOTH;
    constraints.gridy = 0; // Starting the first row

    panel.add(new JButton("\u00B1"), constraints);
    panel.add(new JButton("C"), constraints);
    panel.add(new JButton("\u232B"), constraints);
    panel.add(new JButton("+"), constraints);
    panel.add(new JButton("R"), constraints);
    constraints.gridy++; // Switching to next row
    panel.add(new JButton("1"), constraints);
    panel.add(new JButton("2"), constraints);
    panel.add(new JButton("3"), constraints);
    panel.add(new JButton(dash), constraints);
    panel.add(new JButton("Inv"), constraints);

    constraints.gridy++; // Switching to next row
    panel.add(new JButton("4"), constraints);
    panel.add(new JButton("5"), constraints);
    panel.add(new JButton("6"), constraints);
    panel.add(new JButton(code1), constraints);
    panel.add(new JButton("("), constraints);

    constraints.gridy++;
    panel.add(new JButton("7"), constraints);
    panel.add(new JButton("8"), constraints);
    panel.add(new JButton("9"), constraints);
    panel.add(new JButton(code2), constraints);
    panel.add(new JButton(")"), constraints);

    constraints.gridy++;
    constraints.gridwidth = 2;
    panel.add(new JButton("0"), constraints);
    constraints.gridwidth = 1;
    panel.add(new JButton(html), constraints);
    panel.add(new JButton("="), constraints);
    panel.add(new JButton("."), constraints);

    JButton historyButton = new JButton(right);
    historyButton.setActionCommand(right);
    historyButton.addActionListener(theListener);
    east.add(historyButton);

  }

  /**
   * Sets the ActionListeners.
   */
  protected void setListenersAndActions()
  {
    for (Component theButton : panel.getComponents())
    {
      JButton button = (JButton) theButton;
      String text = button.getText();
      if (text.equals(code2))
      {
        button.setActionCommand("/");
      }
      else if (text.equals(code1))
      {
        button.setActionCommand("*");
      }
      else
      {
        button.setActionCommand(text);
      }
      button.addActionListener(theListener);
    }
  }

  

  /**
   * "Backspaces" by setting the display text to a substring of the current text.
   * 
   * @return the backspace
   */
  static char backspace()
  {
    char toReturn = 'n';
    if (!input.getText().isEmpty())
    {
      toReturn = input.getText().charAt(input.getText().length() - 1);
      input.setText(input.getText().substring(0, input.getText().length() - 1));
    }
    return toReturn;
  }

  /**
   * Clears the display, but doesn't reset.
   */
  static void clearDisplay()
  {
    display.setText("");
  }
  
  static void clearInput()
  {
    input.setText("");
  }

  /**
   * Getter for the display.
   * 
   * @return the display
   */
  static JTextPane getDisplay()
  {
    return display;
  }
  
  static JTextPane getInput()
  {
    return input;
  }

  /**
   * .
   * 
   * @param text
   *          the text to set
   * @return the output
   *
  static JTextArea setOutput(String text)
  {
    output.setText(text);
    return output;
  }
  */

  /**
   * Appends the given text to the display text.
   * 
   * @param text
   *          the text to add
   */
  static void appendDisplay(final String text)
  {
    display.setText(display.getText().concat(text));
  }
  
  static void appendInput(final String text)
  {
    input.setText(input.getText().concat(text));
  }
  
  
  static void appendItalicI() 
  {
    appendInput(html);
  }

  /**
   * Sets the display text to the given text.
   * 
   * @param text
   *          the text to set
   */
  static void setDisplay(final String text)
  {
    display.setText(text);
  }
  
  static void setInput(final String text)
  {
    input.setText(text);
  }

  static void toggleSign()
  {
    String theText = input.getText();
    if (!theText.isEmpty())
    {
      if (theText.charAt(0) != '-')
      {
        setInput(dash + theText);
      }
      else
      {
        setInput(theText.substring(1));
      }
    }
    else
    {
      appendInput(dash);
    }
  }

  static MainPanel getInstance() throws IOException
  {
    if (main == null)
    {
      main = new MainPanel();
    }
    return main;
  }

  static Point getPosition()
  {
    return main.getLocationOnScreen();
  }

}
