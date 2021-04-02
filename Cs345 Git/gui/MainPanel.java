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
    private GridBagConstraints constraints;
    private JTextField inputField;
    private ImageIcon logo;
    private JLabel rimpLogo;

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
        north = new JPanel(new GridLayout(2,1));
        east = new JPanel();
        west = new JPanel();

        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();
        inputField = new JTextField();
        logo = new ImageIcon("logoRimplex.png");
        rimpLogo = new JLabel(logo);



    } // method createComponents

    protected void setParameters()
    {



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
        north.add((rimpLogo));
        north.add(inputField);
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
        panel.add(new JButton("/"), constraints);
        panel.add(new JButton(")"), constraints);

        constraints.gridy++;
        constraints.gridwidth = 2;
        panel.add(new JButton("0"), constraints);
        constraints.gridwidth = 1;
        panel.add(new JButton("i"), constraints);
        panel.add(new JButton("="), constraints);
        panel.add(new JButton("."), constraints);



    }

    protected void setListeners()
    {

    }


}
