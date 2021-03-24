package gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
/**
 * main frame.
 * 
 * @author Prof. David Bernstein, James Madison University
 * 
 * @version 1.0
 */
public class MainFrame extends JFrame   {
    private static MainFrame frame;
    private JPanel mainPanel;
    private MainFrame() {

            createComponents(); // create needed objects
            setSize( 600, 450 );
            getContentPane().add( mainPanel, BorderLayout.CENTER );
            setTitle( "Rimplex" );
            setVisible( true ); // display this
            centerForm();

    } // constructor
        /**
         * centerForm.
         *
         * center form on screen
         */
        private void centerForm() {

            Dimension dimScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension dimFrameSize = getSize();

            if ( dimFrameSize.height > dimScreenSize.height ) {
                dimFrameSize.height = dimScreenSize.height;
            }
            if ( dimFrameSize.width > dimScreenSize.width ) {
                dimFrameSize.width = dimScreenSize.width;
            }

            setLocation( ( dimScreenSize.width - dimFrameSize.width ) / 2,
                    ( dimScreenSize.height - dimFrameSize.height ) / 2 );

        }

    /**
     * the panel to be displayed.
     * simply instantiate to view.
     */
    private void createComponents() {

        // create a panel and implement here.

        mainPanel = new MainPanel();
    }
        /**
         * get instance method.
         *
         * @return frame
         */
        public static MainFrame getInstance() {

            if ( frame == null ) {
                frame = new MainFrame();

            }

            return frame;
        }



}
