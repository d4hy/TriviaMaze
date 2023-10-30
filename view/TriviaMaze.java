
package view;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;

/**
 * This class will serve as the container class where everything will be displayed and ran.
 * @author David Hoang
 * @author Faith Capito
 * @version Fall 2023
 */
public  final class TriviaMaze implements PropertyChangeListener {

    /**
     * Static variable used to contain the gui components.
     */
    private static JFrame myWindow;

    /**
     * Constructs the JFrame where everything will be contained and displays
     * the game.
     */
    public TriviaMaze() {
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
        myWindow.addKeyListener(new ControlKeyListener());
    }
    private static void addMazeView() {
        final MazeView mazeview = new MazeView();
        myWindow.add(mazeview);
        //Window will be sized to fit the preferred size
        // and layouts of its subcomponents.
        myWindow.pack();

        mazeview.startGameThread();

    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {

    }

    /**
     * ControlKeyListener is responsible to read key input from the
     * user and move the tetris piece according to the key pressed.
     */
    static class ControlKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            switch (theEvent.getKeyCode()) {
                case KeyEvent.VK_W, KeyEvent.VK_UP -> handleUpKey();
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> handleDownKey();
                case KeyEvent.VK_A, KeyEvent.VK_LEFT -> handleLeftKey();
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> handleRightKey();
                case KeyEvent.VK_SPACE -> handleSpaceKey();

                default -> {
                }
            }
        }

        private void handleUpKey() {
            System.out.println("Pressed Up");

        }

        private void handleDownKey() {
            System.out.println("Pressed Down");


        }

        private void handleLeftKey() {

            System.out.println("Pressed Left");

        }

        private void handleRightKey() {
            System.out.println("Pressed Right");

        }

        private void handleSpaceKey() {
            System.out.println("Pressed Space");

        }





    }

}
