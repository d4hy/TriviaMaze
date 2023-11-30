package model;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Database helper class that creates Question objects from information that is
 * stored in the database file using SQLite.
 *
 * @author Faith Capito
 * @author David Hoang
 * @version Fall 2023
 */
public final class QuestionDatabase {

    /**
     * Constant String that is used for comparisons.
     */
    private static final String OPTION2 = "SECOND_OPTION";

    /**
     * Constant String that is used for comparisons.
     */
    private static final String OPTION3 = "THIRD_OPTION";

    /**
     * Constant String that is used for comparisons.
     */
    private static final String OPTION4 = "FOURTH_OPTION";

    /**
     * Arraylist stores collection of Question object with respective information
     * needed to distribute and use in Trivia Maze.
     */
    private static ArrayList<AbstractQuestion> myAbstractQuestions = new ArrayList<>();

    /**
     * Private, empty constructor to ensure this remains a utility class.
     */
    private QuestionDatabase() { }

    /**
     * Method retrieves data from database and creates objects of Question to use for rest
     * of program.
     */
    public static void connectToDatabase() {

        // Create DataSource object.
        final SQLiteDataSource ds = new SQLiteDataSource();

        // Set DataSource URL.
        ds.setUrl("jdbc:sqlite:Questions.db");

        final String query = "SELECT * FROM QuestionsAnswers";


        // Set up the connection.
        try (Connection conn = ds.getConnection();
             Statement statement = conn.createStatement()) {
            final ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                final String questionText = rs.getString("QUESTION_TEXT");
                final String questionType = rs.getString("QUESTION_TYPE");
                final String correctAnswer = rs.getString("CORRECT_ANSWER");

                final String secondOption;
                final String thirdOption;
                final String fourthOption;
                if (rs.getString(OPTION2) != null) {
                    secondOption = rs.getString(OPTION2);
                } else {
                    secondOption = "";
                }

                if (rs.getString(OPTION3) != null) {
                    thirdOption = rs.getString(OPTION3);
                } else {
                    thirdOption = "";
                }

                if (rs.getString(OPTION4) != null) {
                    fourthOption = rs.getString(OPTION4);
                } else {
                    fourthOption = "";
                }

                // Depending on the type of question, QuestionFactory will create a specified
                // question with its applicable fields.
                final AbstractQuestion abstractQuestion =
                        QuestionFactory.createQuestion(questionText, questionType,
                                correctAnswer, secondOption, thirdOption, fourthOption);

                myAbstractQuestions.add(abstractQuestion);
            }
        } catch (final SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Returns completed list of questions extracted from database file
     * using SQLite.
     * @return arraylist of Question objects.
     */
    public static ArrayList<AbstractQuestion> getQuestions() {
        return myAbstractQuestions;
    }
}
