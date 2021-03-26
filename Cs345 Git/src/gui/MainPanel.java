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
  private CompoundBorder internalBorder;
  private JButton equalButton;
  private JButton divButton;
  private JButton multiButton;
  private JButton minusButton;
  private JButton plusButton;
  private JButton clearButton;
  private JButton resetButton;
  private JTextField inputField;
  private JTextField inputField2;
  private JLabel label;
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

    //added this for logo
    logo = new ImageIcon("logoRimplex.png");
    rimpLogo = new JLabel(logo);

    label = new JLabel("test");
    inputField = new JTextField();
    inputField.setSize(2000, 10);
    inputField2 = new JTextField();
    inputField2.setSize(1000, 25);

    // panels
    northPanel = new JPanel();
    midPanel = new JPanel();
    southPanel = new JPanel();
    temp = new JPanel();
    // buttons below
    equalButton = new JButton("=");
    divButton = new JButton("/");
    multiButton = new JButton("*");
    minusButton = new JButton("-");
    plusButton = new JButton("+");

    clearButton = new JButton("C");
    resetButton = new JButton("R");

  } // method createComponents

  protected void setParameters()
  {

    midPanel.setBorder(internalBorder);

  } // method setParameters

  protected void setPanel()
  {

    setLayout(new BorderLayout());
    //added this for the logo
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

    midPanel.add(inputField2);
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
