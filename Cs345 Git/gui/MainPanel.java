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
        
        logo = new ImageIcon("logoRimplex.png");
        rimpLogo = new JLabel(logo);
        divSymbol = new ImageIcon("divisionSymbol.png");
        

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
    
    static void clearDisplay() {
      display.setText("");
    }

    static JTextPane getDisplay() {
      return display;
    }
    
    static JTextArea appendOutput(String text) {
      output.setText(output.getText().concat(text));
      return output;
    }
    
    static JTextArea setOutput(String text) {
      output.setText(text);
      return output;
    }

}
