package view;





import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *  This class serves as a screen class that displays the game.
 * @author David Hoang, Faith Capito.
 * @version Fall 2023
 */
public class MazeView extends JPanel {

    //Screen settings.
    /**
     *  16x 16 tiles, will represent size of characters,and parts of the map.
     */
    private final int myOriginalTileSize = 16;
    /**
     *  This variable will be used to upscale the tile size otherwise it's too small to see.
     */
    private final int myScale = 3;

    /**
     * The tile size up-scaled to fit on the screen.
     */
    private final int myTileSize = myOriginalTileSize * myScale;

    /**
     *The amount of tiles that will be represented as the rows of the screen.
     *
     */
    private final int myMaxScreenRow = 16;
    /**
     *The amount of tiles that will be represented as the columns of the screen.
     *
     */
    private final int myMaxScreenCol = 12;

    /**
     * The up-scaled amount of tiles that will be represented as the width of the screen.
     */
    private final int myScreenWidth = myTileSize * myMaxScreenRow; //768 pixels.

    /**
     * The up-scaled amount of tiles that will be represented as the height of the screen.
     */
    private final int myScreenHeight = myTileSize * myMaxScreenRow; // 576 pixels.

    MazeView() {
        //sets the size of the jPanel.
        this.setPreferredSize(new Dimension(myScreenWidth, myScreenHeight));
        this.setBackground(Color.black);

        //will improve game's rendering performance.
        this.setDoubleBuffered(true);
    }

}
