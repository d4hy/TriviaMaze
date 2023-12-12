import view.TriviaMaze;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * The driver class for this demonstration code.
 * @author David Hoang, Faith Capito.
 * @version Fall 2023
 *
 */
public final class Driver {

    /**
     * Private empty constructor to avoid accidental instantiation.
     */
    private Driver() {
    }
    /**
     * Creates a JFrame to demonstrate this panel.
     *
     * @param theArgs Command line arguments, ignored.
     */
    public static void main(final String[] theArgs) throws UnsupportedAudioFileException,
            LineUnavailableException, IOException {
        final TriviaMaze game = new TriviaMaze();

    }
}
