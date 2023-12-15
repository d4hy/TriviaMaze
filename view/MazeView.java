package view;

import controller.MazeControls;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Character;
import model.Door;
import model.Maze;
import model.Room;

/**
 *  This class serves as a screen class that displays the game.
 * @author David Hoang,
 * @author Faith Capito.
 * @version Fall 2023
 */
public class MazeView extends JPanel implements PropertyChangeListener, KeyListener {
    /**
     * The initial delay that the timer has.
     */
    private static final int TIMER_DELAY = 1000;

    /**
     * The String that will be used as the cursor.
     */
    private static final String  CURSOR_TEXT = "-";

    /**
     * File name when user wants to save and load a previous game.
     */
    private static final String FILE_NAME = "TriviaMaze.txt";

    /**
     * This variable will represent the state when we are in a playable state.
     */
    private static final int NORMAL_STATE =  1;

    /**
     * This variable will represent the state when we are in a paused state.
     */
    private static final int PAUSED_STATE =  2;

    /**
     * Settings menu option that plays/pauses the music.
     */
    private static final int PLAY_AND_PAUSE_MUSIC = 3;

    /**
     * Settings menu option that plays/pauses the music.
     */
    private static final int SKIP_MUSIC = 4;

    /**
     * Settings menu option is the exit.
     */
    private static final int EXIT = 5;

    /**
     * Settings menu option is the new game option.
     */
    private static final int NEW_GAME = 2;

    /**
     * Settings menu option is the save.
     */
    private static final int SAVE = 0;

    /**
     * Settings menu option is the play.
     */
    private static final int LOAD = 1;

    /**
     * List of music files that are playable in the background of game.
     */
    private static final List<String> MY_MUSIC_FILES = new ArrayList<>();

    /**
     * Index in music player.
     */
    private  int myCurrentMusicIndex;

    /**
     * Boolean representation if music is looping.
     */
    private static boolean myMusicPlayerIsLooping;

    /**
     * Timer that will be used for game and question functionality.
     */
    private static Timer myTimer;

    private static LineListener myLineListener;

    /**
     * Music player clip.
     */
    private Clip myClip;

    /**
     * Counter for which walking animation to choose.
     */
    private transient int mySpriteNumber = 1;

    /**
     * Long that stores position of clip when paused.
     */
    private transient long myPausedPosition;

    private static boolean myMusicHasStarted;

    /**
     *  Maze Object to be referenced.
     */
    private Maze myMaze;

    /**
     *  Character to reference.
     */
    private Character myCharacter;

    /**
     * The current room to reference.
     */
    private Room myRoom;

    /**
     * Counter for how many steps has taken the sprite alternates.
     */
    private transient int mySpriteCounter;

    /**
     * This field will represent which ui to display.
     *
     */
    private transient int myGameUI;

    /**
     * This field will represent which option they chose during the settings menu.
     */
    private transient int mySettingsMenuCommand;

    /**
     * This field will represent which sub menu option we are at.
     *
     */
    private transient int mySettingsSubMenuOption;

    private boolean mySkipSongRequest;

    private boolean myPauseSongRequest;

    /**
     * A boolean representing if enter key has been pressed
     */
    private transient boolean enterPressed;




    /**
     * These will be sprites that we use for the character.
     */
    @SuppressWarnings("checkstyle:MultipleVariableDeclarations")
    private transient BufferedImage myUp1, myUp2, myDown1,
            myDown2, myLeft1, myLeft2, myRight1, myRight2;

    MazeView(final Maze theMaze) throws UnsupportedAudioFileException,
            LineUnavailableException, IOException {
        myGameUI = NORMAL_STATE;
        mySettingsMenuCommand = 0;
        mySettingsSubMenuOption = 0;
        enterPressed = false;
        myMusicPlayerIsLooping = false;
        this.myMaze = theMaze;
        setUp();
        myMaze.addPropertyChangeListener(this);
        addKeyListener(this);
        setFocusable(true);

    }

    public void setUp() throws UnsupportedAudioFileException, LineUnavailableException,
            IOException {
        this.setPreferredSize(new Dimension(
                MazeControls.MY_SCREEN_WIDTH, MazeControls.MY_SCREEN_HEIGHT));

        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        addMusic();
        setTimer();
    }

    private void setTimer() {
        myTimer = new Timer(TIMER_DELAY, e -> {
            if (myTimer.isRunning()) {
                // Depending on implementation, show the timer during the game
                // or only use to calculate time limit for user to answer questions.
            }
        });
    }

    /**
     * Adds music files to list.
     */
    private void addMusic() throws IOException, UnsupportedAudioFileException,
            LineUnavailableException {

        myCurrentMusicIndex = 0;
        myPausedPosition = 0;
        mySkipSongRequest = false;
        myPauseSongRequest = false;
        myMusicHasStarted = false;

        MY_MUSIC_FILES.add("sound/Wrong Buzzer - Sound Effect.wav");
        MY_MUSIC_FILES.add("sound/beabadoobee - Cologne (Lyrics).wav");
        MY_MUSIC_FILES.add("sound/Beabadoobee - Last Day On Earth (Official Audio).wav");
        MY_MUSIC_FILES.add("sound/beabadoobee - the perfect pair (Official Audio).wav");
        MY_MUSIC_FILES.add("sound/beabadoobee - the way things go.wav");
        MY_MUSIC_FILES.add("sound/beabadoobee x Laufey - A Night To Remember (Official Lyric Video).wav");
        MY_MUSIC_FILES.add("sound/You’re here that’s the thing.wav");

        final File file = new File(MY_MUSIC_FILES.get(myCurrentMusicIndex));
        final AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);

        myClip = AudioSystem.getClip();
        myClip.open(audioIn);


    }

    private void initializeClip() throws LineUnavailableException, IOException,
            UnsupportedAudioFileException {

        final File file = new File(MY_MUSIC_FILES.get(myCurrentMusicIndex));
        final AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);

        myClip = AudioSystem.getClip();
        myClip.open(audioIn);


    }

    private void playNextSong() {

        if (!MY_MUSIC_FILES.isEmpty()) {
            if (myClip != null) {
                removeLineListener();
                myClip.stop();
            }

            myCurrentMusicIndex = (myCurrentMusicIndex + 1) % MY_MUSIC_FILES.size();
            final String nextMusicFile = MY_MUSIC_FILES.get(myCurrentMusicIndex);

            try {
                final AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                        new File(nextMusicFile));
                myClip = AudioSystem.getClip();
                myClip.open(audioIn);


                myClip.start();
                addLineListener();

            } catch (final Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void paintComponent(final Graphics theG) {
        super.paintComponent(theG);

        final Graphics2D g2 = (Graphics2D) theG;
        System.out.println("GAMEUI:" + myGameUI);
        System.out.println("SUBMENU-OPTIONS:" + mySettingsSubMenuOption);

        g2.setColor(Color.WHITE);

        if (myRoom != null) {
            drawRoomsFloor(g2);
            drawRoomsDoors(g2);
        }

        if (myCharacter != null) {
            drawCharacter(g2);
        }

        if (myGameUI == PAUSED_STATE) {
            try {
                drawTheSettingsMenu(g2);
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
        }


        g2.dispose();
    }

    /**
     * Draws the setting menu if the myGameUi is in a paused state.
     */
    public void drawTheSettingsMenu(final Graphics2D g2) throws IOException {

        g2.setColor(Color.white);
        final float fontSize = 30F;
        g2.setFont(g2.getFont().deriveFont(fontSize));

        //SUBWINDOW
        final int frameX = MazeControls.MY_TILE_SIZE * 6;
        final int frameY = MazeControls.MY_TILE_SIZE;
        final int frameWidth = MazeControls.MY_TILE_SIZE * 8;
        final int frameHeight = MazeControls.MY_TILE_SIZE * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight, g2);




        optionsTop(frameX, frameY, g2);

        switch (mySettingsSubMenuOption) {
            case 0: optionsTop(frameX, frameY, g2);
                    break;
            case 1: break;
            case 2: break;
            default:
                throw new IllegalStateException("Unexpected value: "
                        +
                        mySettingsSubMenuOption);
        }
        //enterPressed = false;
    }

    private void optionsTop(final int theFrameX, final int theFrameY, final Graphics2D g2) throws IOException {
//        repaint();
        int textX;
        int textY;
        String text = "Settings menu";
        if (myMaze.isGameLost()) {
            text = "You lost!";
        } else if (myMaze.isMyGameWon()) {
            text = "You Won!";
        }

            // have to adjust the x coordinate, or else the left side of
            // this screen will be drawn from the middle all the way to the right.


        textX = getXForCenteredText(text, g2);
        textY = theFrameY + MazeControls.MY_TILE_SIZE;
        // y indicates the baseline of the text.
        g2.drawString(text, textX, textY);

        text = "In Room: [" + myMaze.getCurrentRoomRow() + "]["
                + myMaze.getCurrentRoomCol() + "]";
        final int ySpace = 40;
        textY += ySpace;
        g2.drawString(text, textX, textY);


        //Save
        textX = theFrameX + MazeControls.MY_TILE_SIZE;
        textY += MazeControls.MY_TILE_SIZE;
        g2.drawString("Save", textX, textY);
        if (mySettingsMenuCommand == SAVE) {

            // this is the cursor
            final int cursorX = textX - 25;
            g2.drawString(CURSOR_TEXT, cursorX, textY);
            //TODO handle the case so you can save
            if (enterPressed) {
                myGameUI = NORMAL_STATE;
                try {

                    myMaze.save(FILE_NAME);

                } catch (final IOException exception) {
                    System.out.println("Exception: State has not been saved.");
                    exception.printStackTrace();
                }
                repaint();
                System.out.println("You clicked the enter key!");
            }

        }

        //Load
        textY += MazeControls.MY_TILE_SIZE;
        g2.drawString("Load", textX, textY);
        if (mySettingsMenuCommand == LOAD) {
            // this is the cursor
            final int cursorX = textX - 25;
            g2.drawString(CURSOR_TEXT, cursorX, textY);
            //TODO handle the case so you can load
            if (enterPressed) {
                try {

                    myMaze = myMaze.load(FILE_NAME);
                    myMaze.addPropertyChangeListener(this);
                    myRoom = myMaze.getCurrentRoom();
                    myCharacter = myMaze.getCharacter();

                } catch (final IOException exception) {
                    System.out.println("Exception: State has not been loaded.");
                    exception.printStackTrace();
                } catch (final ClassNotFoundException exception) {
                    exception.printStackTrace();
                }

                System.out.println(myMaze);
                System.out.println("You clicked the enter key!");
                requestFocus();

                myGameUI = NORMAL_STATE;
                repaint();
            }
        }
        //NewGame
        textY += MazeControls.MY_TILE_SIZE;
        g2.drawString("New Game", textX, textY);
        if (mySettingsMenuCommand == NEW_GAME) {
            // this is the cursor
            final int cursorX = textX - 25;
            g2.drawString(CURSOR_TEXT, cursorX, textY);

            if (enterPressed) {
                myMaze.newGame();
                System.out.println("You clicked the enter key!");
                myGameUI = NORMAL_STATE;
                mySettingsMenuCommand = 0;
            }
        }

        // Play and pause music.
        textY += MazeControls.MY_TILE_SIZE;
        g2.drawString("Play/Pause Music", textX, textY);
        if (mySettingsMenuCommand == PLAY_AND_PAUSE_MUSIC) {
            final int cursorX = textX - 25;
            g2.drawString(CURSOR_TEXT, cursorX, textY);

            if (enterPressed) {
                try {

                    if (myClip != null) {

                        if (myClip.isRunning()) {
                            myPausedPosition = myClip.getMicrosecondPosition();
                            mySkipSongRequest = true;
                            myPauseSongRequest = true;
                            myClip.stop();
                        } else {
                            initializeClip();
                            myClip.setMicrosecondPosition(myPausedPosition);
                            myClip.start();
                            addLineListener();
                        }

                    } else {
                        initializeClip();
                        myClip.start();
                        addLineListener();
                    }

                } catch (final LineUnavailableException | IOException
                               | UnsupportedAudioFileException exception) {
                    exception.printStackTrace();
                }

            }
        }

        // Skip music.
        textY += MazeControls.MY_TILE_SIZE;
        g2.drawString("Skip Music", textX, textY);
        if (mySettingsMenuCommand == SKIP_MUSIC) {
            final int cursorX = textX - 25;
            g2.drawString(CURSOR_TEXT, cursorX, textY);

            if (enterPressed) {
                playNextSong();
            }
        }

        //Exit
        textY += MazeControls.MY_TILE_SIZE;
        g2.drawString("Exit", textX, textY);
        if (mySettingsMenuCommand ==  EXIT) {
            // this is the cursor
            final int cursorX = textX - 25;
            g2.drawString(CURSOR_TEXT, cursorX, textY);
            //TODO handle the case so you can Exit
            if (enterPressed) {
                enterPressed = false;
                repaint();
                //System.out.println("You clicked the enter key!");
                myGameUI = NORMAL_STATE;

            }
        }

        enterPressed = false;

    }

    /**
     * Handles line events to ensure clip will play the next file in list
     * when it has finished playing.
     */
    private void addLineListener() {
        myLineListener = event -> {
            if (event.getType() == LineEvent.Type.STOP) {
                if (!mySkipSongRequest) {
                    playNextSong();
                } else if (myPauseSongRequest) {
                    removeLineListener();
                }
            }
            mySkipSongRequest = false;
        };
        myClip.addLineListener(myLineListener);
    }

    private void removeLineListener() {
        if (myLineListener != null) {
            myClip.removeLineListener(myLineListener);
        }
    }

    /**
     *  Helper method to get X coordinate of the text you are trying to get to the center of.
     * @param theText of which you are getting the coordinates for
     * @param g2 the drawing object
     * @return x coordinate for which you are trying to get centered text for.
     */
    private int getXForCenteredText(final String theText, final Graphics2D g2) {

        final int textLength = (int) g2.getFontMetrics().
                getStringBounds(theText, g2).getWidth();
        return MazeControls.MY_SCREEN_WIDTH / 2 - textLength / 2;

    }

    /**
     * draws a sub window.
     * @param theX coordinate of where to draw the sub window.
     * @param theY coordinate of where to draw the sub window.
     * @param theWidth of the sub window.
     * @param theHeight of the sub window.
     */

    private void drawSubWindow(final int theX,
                               final int theY,
                               final int theWidth, final int theHeight, final Graphics2D g2) {
        final int opacity = 210;
        Color color = new Color(0, 0, 0, opacity);
        g2.setColor(color);

        final int innerRectWidthHeight = 35;
        g2.fillRoundRect(theX, theY, theWidth, theHeight,
                innerRectWidthHeight, innerRectWidthHeight);
        final int whiteNumber = 255;
        color  = new Color(whiteNumber, whiteNumber, whiteNumber);

        //Defines the width of outlines of graphics which are rendered with a Graphics2D.
        final int strokeWidth = 5;
        g2.setColor(color);
        g2.setStroke(new BasicStroke(strokeWidth));
        final int outerRectangleX = theX + 5;
        final int outerRectangleY = theY + 5;
        final int outerRectangleWidth = theWidth - 10;
        final int outerRectangleHeight = theHeight - 10;
        final int arcWidthHeight = 25;
        g2.drawRoundRect(outerRectangleX, outerRectangleY, outerRectangleWidth,
                outerRectangleHeight, arcWidthHeight , arcWidthHeight);
    }


    /**
     * Method used to draw the room's doors.
     * @param g2 object used to draw.
     */
    private void drawRoomsDoors(final Graphics2D g2) {
        drawDoorIfNotNull(g2, myRoom.getLeftDoor(), getLeftDoorImg(),
                0, MazeControls.MY_SCREEN_HEIGHT / 2 - MazeControls.MY_TILE_SIZE / 2);
        drawDoorIfNotNull(g2, myRoom.getRightDoor(), getRightDoorImg(),
                MazeControls.MY_SCREEN_WIDTH - MazeControls.MY_TILE_SIZE,
                MazeControls.MY_SCREEN_HEIGHT / 2 - MazeControls.MY_TILE_SIZE / 2);
        drawDoorIfNotNull(g2, myRoom.getBottomDoor(), getBottomDoorImg(),
                MazeControls.MY_SCREEN_WIDTH / 2 - MazeControls.MY_TILE_SIZE / 2,
                MazeControls.MY_SCREEN_HEIGHT - MazeControls.MY_TILE_SIZE);
        drawDoorIfNotNull(g2, myRoom.getTopDoor(), getTopDoorImg(),
                MazeControls.MY_SCREEN_WIDTH / 2 - MazeControls.MY_TILE_SIZE / 2, 0);
    }

    /**
     *  Draws the Door if not null.
     * @param g2 object used to draw
     * @param theDoor object that we are drawing for.
     * @param theDoorImg that we will display.
     * @param theX Coordinate where the door will be drawn.
     * @param theY Coordinate where the door will be drawn.
     */
    private void drawDoorIfNotNull(final Graphics2D g2, final Door theDoor,
                                   final BufferedImage theDoorImg,
                                   final int theX, final int theY) {
        if (theDoor != null) {
            drawDoor(g2, theDoorImg, theX, theY);
        }
    }

    /**
     * Draws the room's doors.
     * @param g2 object used to draw.
     */
    private void drawRoomsFloor(final Graphics2D g2) {
        final BufferedImage image = getRoomTileImage();
        for (int i = 0; i < MazeControls.MY_MAX_SCREEN_ROW; i++) {
            for (int j = 0; j < MazeControls.MY_MAX_SCREEN_COL; j++) {
                final int x = i * MazeControls.MY_TILE_SIZE;
                final int y = j * MazeControls.MY_TILE_SIZE;
                g2.drawImage(image, x, y, MazeControls.MY_TILE_SIZE,
                        MazeControls.MY_TILE_SIZE, null);
            }
        }
    }
    /**
     *  Draws the Door.
     * @param g2 object used to draw
     * @param theDoorImg that we will display.
     * @param theX Coordinate where the door will be drawn.
     * @param theY Coordinate where the door will be drawn.
     */
    private void drawDoor(final Graphics2D g2, final BufferedImage theDoorImg,
                          final int theX, final int theY) {
        g2.drawImage(theDoorImg, theX, theY, MazeControls.MY_TILE_SIZE,
                MazeControls.MY_TILE_SIZE, null);
    }

    /**
     *  Method that loads the Room's tiles images.
     * @return the Image that serves as the Room's tiles.
     */
    private BufferedImage getRoomTileImage() {

        final String roomTilePath = "/res/room_tile_red.png";
        return loadImage(roomTilePath);
    }

    /**
     *  Method that loads the Door's left image.
     * @return the left Door's image.
     */
    private BufferedImage getLeftDoorImg() {

        final String leftDoorPath = "/res/left_door.png";
        return loadImage(leftDoorPath);
    }

    /**
     *  Method that loads the Door's right image.
     * @return the right Door's image.
     */
    private BufferedImage getRightDoorImg() {

        final String rightDoorPath = "/res/right_door.png";
        return loadImage(rightDoorPath);
    }

    /**
     *  Method that loads the Door's right image.
     * @return the right Door's image.
     */
    private BufferedImage getBottomDoorImg() {

        final String bottomDoorPath = "/res/bottom_door.png";
        return loadImage(bottomDoorPath);
    }
    /**
     *  Method that loads the Door's top image.
     * @return the top Door's image.
     */
    private BufferedImage getTopDoorImg() {
        final String topDoorPath = "/res/top_door.png";
        return loadImage(topDoorPath);
    }

    /**
     * Loads an image based on the specified path.
     * @param thePath to the image.
     * @return an image that's from the path.
     */
    private BufferedImage loadImage(final String thePath) {
        try {
            return ImageIO.read
                    (Objects.requireNonNull(getClass().getResourceAsStream(thePath)));
        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Draw the characters, based on their current position.
     */
    private void drawCharacter(final Graphics2D g2) {
        getPlayerImage();
        //the x and y coordinates of the character.
        final int x = myCharacter.getCurrentPosition().x;
        final int y = myCharacter.getCurrentPosition().y;



        final BufferedImage image = getSpriteImage();
        g2.drawImage(image, x, y, MazeControls.MY_TILE_SIZE, MazeControls.MY_TILE_SIZE, null);

    }
    /**
     * Get the appropriate sprite image based on the
     * character's direction and walking animation.
     * @return theBufferedImage that represents the character.
     */
    private BufferedImage getSpriteImage() {
        BufferedImage image = null;

        switch (myCharacter.getMyDirection()) {
            case "up":
                image = getSprite(myUp1, myUp2);
                break;
            case "down":
                image = getSprite(myDown1, myDown2);
                break;
            case "right":
                image = getSprite(myRight1, myRight2);
                break;
            case "left":
                image = getSprite(myLeft1, myLeft2);
                break;
            default:

        }

        return image;
    }
    /**
     * Gets the sprite image based on the current sprite number.
     *
     * @param sprite1 The first sprite image.
     * @param sprite2 The second sprite image.
     * @return The sprite image based on the current sprite number.
     */
    private BufferedImage getSprite(final BufferedImage sprite1, final BufferedImage sprite2) {
        if (mySpriteNumber == 1) {
            return sprite1;
        } else {
            return sprite2;
        }
    }
    /**
     * Alternates the walking animation of the character.
     */
    private void alternateWalkingSprite() {
        mySpriteCounter++;
        //every 10 movements, change the walking animation
        if (mySpriteCounter > 1) {
            if (mySpriteNumber == 1) {
                mySpriteNumber = 2;
            } else if (mySpriteNumber == 2) {
                mySpriteNumber = 1;
            }
            mySpriteCounter = 0;
        }
    }

    /**
     * This method will load the user images.
     */
    private void getPlayerImage() {
        try {
            //the string is the path of where the file is
            myUp1 = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/res/up_1.png")));
            myUp2 = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/res/up_2.png")));
            myDown1 = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/res/down_1.png")));
            myDown2 = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/res/down_2.png")));
            myLeft1 = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/res/left_1.png")));
            myLeft2 = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/res/left_2.png")));
            myRight1 = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/res/right_1.png")));
            myRight2 = ImageIO.read(Objects.requireNonNull(getClass().
                    getResourceAsStream("/res/right_2.png")));
        } catch (final IOException e) {
            e.printStackTrace();

        }
    }
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {
        // if the property name equals to any of the specified properties below,
        // it repaints and updates the screen.

        final String propertyName = theEvt.getPropertyName();

        if (propertyName.equals(myMaze.PROPERTY_CHARACTER_MOVE)) {
            myCharacter = (Character) theEvt.getNewValue();
            alternateWalkingSprite();
            repaint();
        } else if (propertyName.equals(myMaze.PROPERTY_ROOM_CHANGE)) {
            myRoom = (Room) theEvt.getNewValue();
            repaint();

        } else if (propertyName.equals(myMaze.PROPERTY_GAME_WON)) {
            final boolean gameWon = (Boolean) theEvt.getNewValue();
            if (gameWon) {
                myGameUI = PAUSED_STATE;
                repaint();
            }
            repaint();

        } else if (propertyName.equals(myMaze.PROPERTY_GAME_OVER)) {
            final boolean gameOver = (Boolean) theEvt.getNewValue();
            if (gameOver) {
                myGameUI = PAUSED_STATE;
                repaint();
            }


        } else if (propertyName.equals(myMaze.PROPERTY_LOAD)) {
//            myMaze = (Maze) theEvt.getNewValue();
//            myMaze.addPropertyChangeListener(this);
//            myRoom = myMaze.getCurrentRoom();
//            myCharacter = myMaze.getCharacter();
            repaint();
        }

    }

    // Implementing KeyListener methods

    @Override
    public void keyPressed(final KeyEvent theEvent) {
        //if the game isn't a paused state listen to all the other keys.
        if (myGameUI == NORMAL_STATE) {
            switch (theEvent.getKeyCode()) {
                case KeyEvent.VK_W, KeyEvent.VK_UP -> handleUpKey();
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> handleDownKey();
                case KeyEvent.VK_A, KeyEvent.VK_LEFT -> handleLeftKey();
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> handleRightKey();

                default -> {
                }
            }

        }
        //pauses the screen if it is this keycode.
        if (theEvent.getKeyCode() == KeyEvent.VK_SPACE) {
            handleSpaceKey();

        }
        if (myGameUI == PAUSED_STATE) {
            handleSettingsOptions(theEvent.getKeyCode());


        }
    }

    /**
     * Helper method that helps us display and update within the mainSettings menu.
     * @param theEventCode that we are calling for.
     */
    private void handleSettingsOptions(final  int theEventCode) {
        repaint();
        // in the main settings menu, after hitting pause

            //3 means we are at the 1st option out of 3.
        int maxCommandNum = 0;
        switch (mySettingsSubMenuOption) {
            case 0: maxCommandNum = 5;
                    break;
        }
        if (theEventCode == KeyEvent.VK_W || theEventCode == KeyEvent.VK_UP) {
            mySettingsMenuCommand--;
            if (mySettingsMenuCommand < 0) {
                mySettingsMenuCommand = maxCommandNum;
            }




        } else if (theEventCode == KeyEvent.VK_S || theEventCode == KeyEvent.VK_DOWN) {
            mySettingsMenuCommand++;
            if (mySettingsMenuCommand > maxCommandNum) {
                mySettingsMenuCommand = 0;
            }


        } else if (theEventCode == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }





    }



    @Override
    public void keyTyped(final KeyEvent theE) {

    }

    @Override
    public void keyReleased(final KeyEvent theE) {
        // Not needed for
    }
    private void handleUpKey() {
        myMaze.moveUp();


    }

    private void handleDownKey() {
        myMaze.moveDown();



    }

    private void handleLeftKey() {
        myMaze.moveLeft();


    }

    private void handleRightKey() {
        myMaze.moveRight();


    }

    private void handleEnterKey() {



    }

    /**
     * Will be used to pause and unpause the game.
     */
    private void handleSpaceKey() {
        System.out.println("Pressed Space");
        if (myGameUI == NORMAL_STATE) {

            myGameUI = PAUSED_STATE;
        } else if (myGameUI == PAUSED_STATE) {
            myGameUI = NORMAL_STATE;
            mySettingsSubMenuOption = 0;
        }
        mySettingsMenuCommand = 0;
        repaint();
    }
}