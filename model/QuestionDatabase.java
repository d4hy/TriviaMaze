package model;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public final class QuestionDatabase {

    /**
     * Arraylist stores collection of Question object with respective information
     * needed to distribute and use in Trivia Maze.
     */
    private static final ArrayList<Question> myQuestions = new ArrayList<>();

    /**
     * Private, empty constructor to ensure this remains a utility class.
     */
    private QuestionDatabase() {

    }

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
                if (rs.getString("SECOND_OPTION") != null) {
                    secondOption = rs.getString("SECOND_OPTION");
                } else {
                    secondOption = "";
                }

                if (rs.getString("THIRD_OPTION") != null) {
                    thirdOption = rs.getString("THIRD_OPTION");
                } else {
                    thirdOption = "";
                }

                if (rs.getString("FOURTH_OPTION") != null) {
                    fourthOption = rs.getString("FOURTH_OPTION");
                } else {
                    fourthOption = "";
                }

                final Question question = new Question(questionText, questionType, correctAnswer,
                        secondOption, thirdOption, fourthOption);

                myQuestions.add(question);
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
    public static ArrayList<Question> getQuestions() {
        return myQuestions;
    }
}
