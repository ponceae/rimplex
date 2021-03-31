package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/**
 * MainPanel - .
 * 
 * @author Ulises Fernandez, Andrew Elbert
 * @version (3/31/21)
 */

public class MainPanel extends JPanel
{
  // JTextComponents for user input and displaying the output
  private static JTextField inputField;
  private static JTextArea output;

  // Actual display Component, package visible for Listener access
  static JTextPane display;

  /**
   * Default UID since I'm not sure what it should be instantiated to.
   */
  private static final long serialVersionUID = 1L;
  private CompoundBorder internalBorder;

  // All JButtons and an ArrayList of type JButton used for convenience.
  private JButton equalButton;
  private JButton divButton;
  private JButton multiButton;
  private JButton minusButton;
  private JButton plusButton;
  private JButton clearButton;
  private JButton resetButton;
  private ArrayList<JButton> theButtons;

  // Label and Icon used for displaying the logo on the calculator
  private JLabel rimpLogo;
  private ImageIcon logo;

  // JPanels used for the
  private JPanel logoPanel;
  private JPanel displayPanel;
  private JPanel buttonPanel;
  private JPanel inputPanel;

  /**
   * Default (and only) constructor for the MainPanel. Creates all Components and Containers and
   * adds all Listeners and Buttons to said Components and Containers.
   */
  public MainPanel()
  {
    createComponents();
    setParameters();
    setPanel();
    addComponents();
    setListenersAndCommands();
  }

  /**
   * Creates all JButtons and JTextComponents as well as instantiating all JPanels.
   */
  protected void createComponents()
  {
    internalBorder = new CompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder());

    // Creates Components used to add the Rimplex logo
    logo = new ImageIcon("logoRimplex.png");
    rimpLogo = new JLabel(logo);

    // Creating the inputField
    inputField = new JTextField();
    inputField.setSize(2000, 10);
    // however, we will also have to figure out how to parse the expressions that the user enters
    // and store it as an actual numeric value (or I guess a ComplexNumber), and on the topic of
    // that, we need to figure out what to do when the user enters an invalid expression

    // Sets the display to a certain size limit and such that it cannot be edited by the user
    display = new JTextPane();
    display.setSize(1000, 25);
    display.setEditable(false);

    // Instantiation of all Panels
    logoPanel = new JPanel();
    displayPanel = new JPanel();
    buttonPanel = new JPanel();
    inputPanel = new JPanel();

    // Instantiation of all Buttons
    equalButton = new JButton("=");
    divButton = new JButton("/");
    multiButton = new JButton("*");
    minusButton = new JButton("-");
    plusButton = new JButton("+");
    clearButton = new JButton("C");
    resetButton = new JButton("R");

    // Adds all the Buttons to a JButtons ArrayList for ease of use
    theButtons = new ArrayList<JButton>();
    theButtons.add(resetButton);
    theButtons.add(clearButton);
    theButtons.add(plusButton);
    theButtons.add(minusButton);
    theButtons.add(multiButton);
    theButtons.add(divButton);
    theButtons.add(equalButton);

  }

  /**
   * Sets the border on the display JPanel for aesthetic.
   */
  protected void setParameters()
  {
    displayPanel.setBorder(internalBorder);
  }

  /**
   * Sets the Layout for each JPanel and this MainPanel.
   */
  protected void setPanel()
  {
    setLayout(new BorderLayout());
    logoPanel.setPreferredSize(new Dimension(logo.getIconWidth(), logo.getIconHeight()));
    logoPanel.setLayout(new BorderLayout());
    displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
    inputPanel.setLayout(new GridLayout(2, 1));
  }

  /**
   * Adds all JButtons and JTextComponents to their respective Panels and the Panels to this
   * MainPanel.
   */
  protected void addComponents()
  {
    // Adds the Rimplex logo to the top of the Frame
    logoPanel.add(rimpLogo, BorderLayout.CENTER);
    add(logoPanel, BorderLayout.NORTH); // top panel
    logoPanel.paintComponents(getGraphics());

    // Adds the display Panel to the middle or "center" of the Frame
    displayPanel.add(display);
    add(displayPanel, BorderLayout.CENTER);

    // Adds buttons to the inputPanel and adds the inputPanel
    addButtonsToPanel();
    inputPanel.add(inputField);
    inputPanel.add(buttonPanel);
    add(inputPanel, BorderLayout.SOUTH);

  }

  /**
   * Adds all JButtons to the buttonPanel.
   */
  private void addButtonsToPanel()
  {
    for (JButton button : theButtons)
    {
      buttonPanel.add(button);
    }
  }

  /**
   * Adds theListener to the inputField and all Buttons.
   */
  protected void setListenersAndCommands()
  {
    Listener theListener = Listener.getInstance();
    for (JButton button : theButtons)
    {
      button.addActionListener(theListener);
      button.setActionCommand(button.getText());
    }
    inputField.setActionCommand("text");
    inputField.addActionListener(theListener);
  }

  /**
   * Creates a new output if not already done and changes the text of the output.
   * 
   * @param text
   *          the output to display
   * @return the JTextArea called output
   */
  static JTextArea getDisplayOutput(final String text)
  {
    if (output == null)
    {
      output = new JTextArea();
      output.setEditable(false);
    }
    output.append(text);
    return output;
  }

  /**
   * Clears the imputField (sets the text to empty).
   */
  static void clear()
  {
    inputField.setText("");
  }

  /**
   * Clears the inputField and the display (sets the text to empty).
   */
  static void reset()
  {
    clear();
    display.setText("");
  }

  /**
   * Getter for the inputField so that the Listener can access it for output from JButton presses.
   * 
   * @return the inputField
   */
  static JTextField getInput()
  {
    return inputField;
  }

}
