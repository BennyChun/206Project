package application.util;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SessionObservable {
    private StringProperty equation, correct;
    private IntegerProperty answer, numberOfAttempts;

    public SessionObservable(String equation, int answer, int numberOfAttempts, int correct) {
        this.equation = new SimpleStringProperty(equation);
        this.answer = new SimpleIntegerProperty(answer);
        this.numberOfAttempts = new SimpleIntegerProperty(numberOfAttempts);

        if (correct == 1) {
            this.correct = new SimpleStringProperty("Correct :)");
        } else {
            this.correct = new SimpleStringProperty("Wrong lol :(");
        }
    }

    public String getEquation() {
        return equation.get();
    }

    public StringProperty equationProperty() {
        return equation;
    }

    public String getCorrect() {
        return correct.get();
    }

    public StringProperty correctProperty() {
        return correct;
    }

    public int getAnswer() {
        return answer.get();
    }

    public IntegerProperty answerProperty() {
        return answer;
    }

    public int getNumberOfAttempts() {
        return numberOfAttempts.get();
    }

    public IntegerProperty numberOfAttemptsProperty() {
        return numberOfAttempts;
    }
}
