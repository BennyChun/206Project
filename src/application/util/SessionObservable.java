package application.util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SessionObservable {
    private StringProperty equation, answer, numberOfAttempts, correct;

    public SessionObservable(String equation, int answer, int numberOfAttempts, int correct) {
        this.equation = new SimpleStringProperty(equation);
        this.answer = new SimpleStringProperty(Integer.toString(answer));
        this.numberOfAttempts = new SimpleStringProperty(Integer.toString(numberOfAttempts));

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

    public String getAnswer() {
        return answer.get();
    }

    public StringProperty answerProperty() {
        return answer;
    }

    public String getNumberOfAttempts() {
        return numberOfAttempts.get();
    }

    public StringProperty numberOfAttemptsProperty() {
        return numberOfAttempts;
    }

    public String getCorrect() {
        return correct.get();
    }

    public StringProperty correctProperty() {
        return correct;
    }
}
