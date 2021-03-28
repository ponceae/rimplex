package gui;

import javax.swing.*;
import java.awt.*;
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
  private JButton equalButton;
  private JButton divButton;
  private JButton multiButton;
  private JButton minusButton;
  private JButton plusButton;
  private JButton clearButton;
  private JButton resetButton;
  private JTextField inputField;
  private JTextField display;
  // private JLabel label;
  private JLabel rimpLogo;

  private ImageIcon logo;

  private JPanel northPanel;
  private JPanel midPanel;
  private JPanel southPanel;
  private JPanel temp;

  public MainPanel()
  {
    createComponents();
    setParameters();
    setPanel();
    addComponents();
    setListeners();
  }

  protected void createComponents()
  {

    internalBorder = new CompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder());
    
    Listener theListener = Listener.getInstance();

    // added this for logo
    logo = new ImageIcon("logoRimplex.png");
    rimpLogo = new JLabel(logo);

    // label = new JLabel("test");
    inputField = new JTextField();
    inputField.setSize(2000, 10);
    inputField.setActionCommand("=");
    inputField.addActionListener(theListener);
    // when this is called, it will most likely make use of the TextField's getText() method and
    // then for everything that needs to be italicized (if anything), it will put code in which will
    // italicize it and then put that in the display somehow depending on what TextComponent we use
    // for the display (I'm thinking probably JTextPane)
    // however, we will also have to figure out how to parse the expressions that the user enters
    // and store it as an actual numeric value (or I guess a ComplexNumber), and on the topic of
    // that, we need to figure out what to do when the user enters an invalid expression

    display = new JTextField();
    display.setSize(1000, 25);
    display.setEditable(false);

    // panels
    northPanel = new JPanel();
    midPanel = new JPanel();
    southPanel = new JPanel();
    temp = new JPanel();

    // buttons below
    equalButton = new JButton("=");
    equalButton.setActionCommand("=");

    divButton = new JButton("/");
    divButton.setActionCommand("/");

    multiButton = new JButton("*");
    multiButton.setActionCommand("*");

    minusButton = new JButton("-");
    minusButton.setActionCommand("-");

    plusButton = new JButton("+");
    plusButton.setActionCommand("+");

    clearButton = new JButton("C");
    clearButton.setActionCommand("C");

    resetButton = new JButton("R");
    resetButton.setActionCommand("R");

  } // method createComponents

  protected void setParameters()
  {

    midPanel.setBorder(internalBorder);

  } // method setParameters

  protected void setPanel()
  {

    setLayout(new BorderLayout());
    // added this for the logo
    northPanel.setPreferredSize(new Dimension(logo.getIconWidth(), logo.getIconHeight()));
    northPanel.setLayout(new BorderLayout());
    midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
    temp.setLayout(new GridLayout(2, 1));
  } // method setPanel

  protected void addComponents()
  {
    // added this for the logo
    northPanel.add(rimpLogo, BorderLayout.CENTER);
    add(northPanel, BorderLayout.NORTH); // top panel
    northPanel.paintComponents(getGraphics());

    midPanel.add(display);
    add(midPanel, BorderLayout.CENTER); // mid panel

    southPanel.add(resetButton);
    southPanel.add(clearButton);
    southPanel.add(plusButton);
    southPanel.add(minusButton);
    southPanel.add(multiButton);
    southPanel.add(divButton);
    southPanel.add(equalButton);
    temp.add(inputField);
    temp.add(southPanel);
    add(temp, BorderLayout.SOUTH);

  }

  protected void setListeners()
  {

  }

}
