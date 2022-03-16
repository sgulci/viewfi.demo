package net.sahin.springvw.campaign;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class InteractiveQuestion implements Serializable {
    public String question;
    public String cAnswer;
    public String wAnswerOne;
    public String wAnswerTwo;

    public InteractiveQuestion() {
    }

    public InteractiveQuestion( String question, String cAnswer, String wAnswerOne, String wAnswerTwo ) {
        this.question = question;
        this.cAnswer = cAnswer;
        this.wAnswerOne = wAnswerOne;
        this.wAnswerTwo = wAnswerTwo;
    }
}