package gui;

import javax.swing.*;

import java.awt.*;


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

    public MainPanel()
    {
        createComponents();
        setParameters();
        setPanel();
        addComponents();
        setListenersAndActions();
    }

    protected void createComponents()
    {
        north = new JPanel(new GridLayout(2,1));
        east = new JPanel();
        west = new JPanel();
        panel = new JPanel(new GridBagLayout());
        
        theListener = Listener.getInstance();
        constraints = new GridBagConstraints();
        
        display = new JTextPane();
        output = new JTextArea();
        
        logo = new ImageIcon("CS345 Git/resources/logoRimplex.png");
        rimpLogo = new JLabel(logo);
        divSymbol = new ImageIcon("CS345 Git/resources/divisionSymbol.png");
        

    } // method createComponents

    protected void setParameters()
    {
      for (Component theButton : panel.getComponents()) {
        JButton button = (JButton)theButton;
        button.setBackground(Color.WHITE);
      }

    } // method setParameters

    protected void setPanel()
    {
       setLayout((new BorderLayout()));

       add(north, BorderLayout.NORTH);
       add(panel, BorderLayout.CENTER);
       add(west, BorderLayout.WEST);
       add(east, BorderLayout.EAST);

    } // method setPanel

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
        panel.add(new JButton("x"), constraints);
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

    protected void setListenersAndActions()
    {
      for (Component theButton : panel.getComponents()) {
        JButton button = (JButton)theButton;
        String text = button.getText();
        if (text.equals(""))
        {
          button.setActionCommand("/");
        } else {
          button.setActionCommand(text);
        }
        button.addActionListener(theListener);
      }
    }
    
    /**
     * "Backspaces" by setting the display text to a substring of the current text.
     */
    static char backspace() {
      char toReturn = 'n';
      if (!display.getText().isEmpty()) {
        toReturn = display.getText().charAt(display.getText().length() - 1);
        display.setText(display.getText().substring(0, display.getText().length() - 1));
      }
      return toReturn;
    }
    
    /**
     * Clears the display, but doesn't reset.
     */
    static void clearDisplay() {
      display.setText("");
    }

    /**
     * Getter for the display.
     * 
     * @return the display
     */
    static JTextPane getDisplay() {
      return display;
    }
    
    /**
     * Returns an italicized i. (Not sure if actually used yet).
     * 
     * @param text the text to set
     * @return the output
     */
    static JTextArea setOutput(String text) {
      output.setText(text);
      return output;
    }
    
    /**
     * Appends the given text to the display text.
     * 
     * @param text the text to add
     */
    static void appendDisplay(String text) {
      display.setText(display.getText().concat(text));
    }
    
    /**
     * Sets the display text to the given text.
     * 
     * @param text the text to set
     */
    static void setDisplay(String text) {
      display.setText(text);
    }
    
    static void toggleSign() {
      String theText = display.getText();
      if (!theText.isEmpty())
      {
        if (theText.charAt(0) != '-') {
          setDisplay("-" + theText);
        } else {
          setDisplay(theText.substring(1));
        }
      } else {
        appendDisplay("-");
      }
    }

}
