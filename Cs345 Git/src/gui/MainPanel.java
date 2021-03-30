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
  /**
   * Default UID since I'm not sure what it should be instantiated to
   */
  private static final long serialVersionUID = 1L;
  private CompoundBorder internalBorder;
  
  // all buttons
  private JButton equalButton;
  private JButton divButton;
  private JButton multiButton;
  private JButton minusButton;
  private JButton plusButton;
  private JButton clearButton;
  private JButton resetButton;
  private ArrayList<JButton> theButtons;
  
  // input and output
  private static JTextField inputField;
  private static JTextArea output;
  
  // making this package visible so that the Listener can access it
  static JTextPane display;

  // Label and Icon used for displaying the logo on the calculator
  private JLabel rimpLogo;
  private ImageIcon logo;

  private JPanel logoPanel;
  private JPanel displayPanel;
  private JPanel inputPanel;
  private JPanel buttonsAndInput;

  public MainPanel()
  {
    createComponents();
    setParameters();
    setPanel();
    addComponents();
    setListenersAndCommands();
  }

  protected void createComponents()
  {
    internalBorder = new CompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder());

    // added this for logo
    logo = new ImageIcon("logoRimplex.png");
    rimpLogo = new JLabel(logo);

    // label = new JLabel("test");
    inputField = new JTextField();
    inputField.setSize(2000, 10);
    // when this is called, it will most likely make use of the TextField's getText() method and
    // then for everything that needs to be italicized (if anything), it will put code in which will
    // italicize it and then put that in the display somehow depending on what TextComponent we use
    // for the display (I'm thinking probably JTextPane)
    // however, we will also have to figure out how to parse the expressions that the user enters
    // and store it as an actual numeric value (or I guess a ComplexNumber), and on the topic of
    // that, we need to figure out what to do when the user enters an invalid expression

    display = new JTextPane();
    display.setSize(1000, 25);
    display.setEditable(false);

    // panels
    logoPanel = new JPanel();
    displayPanel = new JPanel();
    inputPanel = new JPanel();
    buttonsAndInput = new JPanel();

    // buttons below
    equalButton = new JButton("=");
    divButton = new JButton("/");
    multiButton = new JButton("*");
    minusButton = new JButton("-");
    plusButton = new JButton("+");
    clearButton = new JButton("C");
    resetButton = new JButton("R");
    
    theButtons = new ArrayList<JButton>();
    theButtons.add(clearButton);
    theButtons.add(equalButton);
    theButtons.add(resetButton);
    theButtons.add(divButton);
    theButtons.add(minusButton);
    theButtons.add(multiButton);
    theButtons.add(plusButton);

  } // method createComponents

  protected void setParameters()
  {

    displayPanel.setBorder(internalBorder);

  } // method setParameters

  protected void setPanel()
  {
    setLayout(new BorderLayout());
    // added this for the logo
    logoPanel.setPreferredSize(new Dimension(logo.getIconWidth(), logo.getIconHeight()));
    logoPanel.setLayout(new BorderLayout());
    displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
    buttonsAndInput.setLayout(new GridLayout(2, 1));
  } // method setPanel

  /**
   * Adds all Panels to this MainPanel.
   */
  protected void addComponents()
  {
    // added this for the logo
    logoPanel.add(rimpLogo, BorderLayout.CENTER);
    add(logoPanel, BorderLayout.NORTH); // top panel
    logoPanel.paintComponents(getGraphics());

    displayPanel.add(display);
    add(displayPanel, BorderLayout.CENTER); // mid panel

    inputPanel.add(resetButton);
    inputPanel.add(clearButton);
    inputPanel.add(plusButton);
    inputPanel.add(minusButton);
    inputPanel.add(multiButton);
    inputPanel.add(divButton);
    inputPanel.add(equalButton);
    buttonsAndInput.add(inputField);
    buttonsAndInput.add(inputPanel);
    add(buttonsAndInput, BorderLayout.SOUTH);

  }

  /**
   * Adds theListener to the inputField and all Buttons
   */
  protected void setListenersAndCommands()
  {
    Listener theListener = Listener.getInstance();
    for (JButton button : theButtons) {
      button.addActionListener(theListener);
      button.setActionCommand(button.getText());
    }
    inputField.setActionCommand("text");
    inputField.addActionListener(theListener);
  }
  
  static JTextArea getDisplayOutput(String text) {
    if (output == null) {
      output = new JTextArea();
      output.setEditable(false);
    }
    output.append(text);
    return output;
  }
  
  static void clear() {
    inputField.setText("");
  }
  
  static void reset() {
    clear();
    display.setText("");
  }
  
  static JTextField getInput() {
    return inputField;
  }

}
