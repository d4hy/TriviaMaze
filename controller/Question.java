/*
 * TriviaMaze
 * Fall 2023
 */
package controller;

import java.io.Serializable;

/**
 * Question interface outlines the basic methods each type of subclass
 * of Question will have.
 *
 * @author Faith Capito
 * @author David Hoang
 * @version Fall 2023
 */
public interface Question extends Serializable {

    String getQuestionText();

    String getAnswerText();

    String getSecondOption();

    String getThirdOption();

    String getFourthOption();


}
