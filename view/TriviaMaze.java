
package view;

import javax.swing.JFrame;

/**
 * This class will serve as the container class where everything will be displayed and ran.
 * @author David Hoang, Faith Capito
 * @version Fall 2023
 */
public  final class TriviaMaze {

    /**
     * Static variable used to contain the gui components.
     */
    private static JFrame myWindow;

     // So there can't be a constructor for this driver class.
    private TriviaMaze() {
        throw new UnsupportedOperationException();
    }
    public static void main(final String[] theArgs) {
        myWindow = new JFrame();
        setUp();
        addMazeView();
    }

    private static void setUp() {

        //This lets the window properly close when user clicks X button.
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setResizable(false);
        myWindow.setTitle("Beabadobee Trivia Maze");
        //Doesn't specify the location of the window, since it is null
        // it wil be displayed in the center.
        myWindow.setLocationRelativeTo(null);
        myWindow.setVisible(true);
    }
    private static void addMazeView() {
        final MazeView mazeview = new MazeView();
        myWindow.add(mazeview);
        //Window will be sized to fit the preferred size
        // and layouts of its subcomponents.
        myWindow.pack();

    }
}
