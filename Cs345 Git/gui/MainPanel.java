package gui;

import javax.swing.*;

import java.awt.*;

/**
 * MainPanel - .
 *
 * @author Ulises Fernandez, Andrew Elbert, and Ian Lips
 * @version (3/31/21)
 */

public class MainPanel extends JPanel
{
  /**
   * Default UID since I'm not sure what it should be instantiated to
   */
  private static final long serialVersionUID = 1L;

  private Color darkRed;

  private JPanel panel;
  private JPanel east;
  private JPanel west;
  private JPanel north;

  private Listener theListener;
  private GridBagConstraints constraints;

  private static JTextPane display;
  private static JTextArea output;

  private ImageIcon logo;
  private JLabel rimpLogo;
  private ImageIcon divSymbol;

  /**
   * Default Constructor.
   */
  public MainPanel()
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
  protected void createComponents()
  {
    darkRed = new Color(139, 0, 0);

    north = new JPanel(new GridLayout(2, 1));
    east = new JPanel();
    west = new JPanel();
    panel = new JPanel(new GridBagLayout());

    north.setBackground(Color.WHITE);
    east.setBackground(darkRed);
    west.setBackground(darkRed);
    panel.setBackground(darkRed);

    theListener = Listener.getInstance();
    constraints = new GridBagConstraints();

    display = new JTextPane();
    output = new JTextArea();

    display.setBackground(darkRed);
    display.setForeground(Color.WHITE);

    logo = new ImageIcon("Cs345 Git/resources/logoRimplex.png");
    rimpLogo = new JLabel(logo);
    divSymbol = new ImageIcon("Cs345 Git/resources/divisionSymbol.png");

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
    constraints.weightx = 1;
    constraints.weighty = 1;
    constraints.fill = GridBagConstraints.BOTH;
    constraints.gridy = 0; // Starting the first row

    panel.add(new JButton("+/-"), constraints);
    panel.add(new JButton("C"), constraints);
    panel.add(new JButton("<-"), constraints);
    panel.add(new JButton("+"), constraints);
    panel.add(new JButton("R"), constraints);
    constraints.gridy++; // Switching to next row
    panel.add(new JButton("1"), constraints);
    panel.add(new JButton("2"), constraints);
    panel.add(new JButton("3"), constraints);
    panel.add(new JButton("-"), constraints);
    panel.add(new JButton("Inv"), constraints);

    constraints.gridy++; // Switching to next row
    panel.add(new JButton("4"), constraints);
    panel.add(new JButton("5"), constraints);
    panel.add(new JButton("6"), constraints);
    panel.add(new JButton("X"), constraints);
    panel.add(new JButton("("), constraints);

    constraints.gridy++;
    panel.add(new JButton("7"), constraints);
    panel.add(new JButton("8"), constraints);
    panel.add(new JButton("9"), constraints);
    panel.add(new JButton(divSymbol), constraints);
    panel.add(new JButton(")"), constraints);

    constraints.gridy++;
    constraints.gridwidth = 2;
    panel.add(new JButton("0"), constraints);
    constraints.gridwidth = 1;
    panel.add(new JButton("i"), constraints);
    panel.add(new JButton("="), constraints);
    panel.add(new JButton("."), constraints);

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
      if (text.equals(""))
      {
        button.setActionCommand("/'");
      }
      else if (text.equals("X"))
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
   * Used to display errors to the user.
   * 
   * @param text
   *          The Error Message
   */
  static void displayError(String text)
  {
    JOptionPane.showMessageDialog(display, text, "Rimplex Error", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * "Backspaces" by setting the display text to a substring of the current text.
   */
  static char backspace()
  {
    char toReturn = 'n';
    if (!display.getText().isEmpty())
    {
      toReturn = display.getText().charAt(display.getText().length() - 1);
      display.setText(display.getText().substring(0, display.getText().length() - 1));
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

  /**
   * Getter for the display.
   * 
   * @return the display
   */
  static JTextPane getDisplay()
  {
    return display;
  }

  /**
   * Returns an italicized i. (Not sure if actually used yet).
   * 
   * @param text
   *          the text to set
   * @return the output
   */
  static JTextArea setOutput(String text)
  {
    output.setText(text);
    return output;
  }

  /**
   * Appends the given text to the display text.
   * 
   * @param text
   *          the text to add
   */
  static void appendDisplay(String text)
  {
    display.setText(display.getText().concat(text));
  }

  /**
   * Sets the display text to the given text.
   * 
   * @param text
   *          the text to set
   */
  static void setDisplay(String text)
  {
    display.setText(text);
  }

  static void toggleSign()
  {
    String theText = display.getText();
    if (!theText.isEmpty())
    {
      if (theText.charAt(0) != '-')
      {
        setDisplay("-" + theText);
      }
      else
      {
        setDisplay(theText.substring(1));
      }
    }
    else
    {
      appendDisplay("-");
    }
  }

}
