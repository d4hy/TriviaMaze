package controller;

/**
 * Question interface outlines the basic methods each type of subclass
 * of Question will have.
 *
 * @author Faith Capito
 * @author David Hoang
 * @version Fall 2023
 */
public interface Question {

    String getQuestionText();

    String getAnswerText();

    String getSecondOption();

    String getThirdOption();

    String getFourthOption();


}
