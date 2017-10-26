package application.util;

import application.model.EquationQuestion;
import application.model.EquationQuestionAdapter;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * this is an adapterclass for SaveCreationHelper class
 * this class takes everything from SaveCreationHelp and turns it into observable properties
 */
public class SavedCreationObservable {

    private ObservableList<EquationQuestion> customEquations = FXCollections.observableArrayList();
    private StringProperty creationName;
    private StringProperty theDate;
    private StringProperty theTime;
    private LongProperty unixTimeStamp;


    public SavedCreationObservable(ArrayList<EquationQuestionAdapter> equations , String creationName , String theDate , String theTime , Long unixTimeStamp){
        this.creationName = new SimpleStringProperty(creationName);
        this.theDate = new SimpleStringProperty(theDate);
        this.theTime = new SimpleStringProperty(theTime);
        this.unixTimeStamp = new SimpleLongProperty(unixTimeStamp);

        //need to convert all those EquationQuestionAdapter to EquationQuestions
        //and put them into an ObservableList<T>
        for (int i = 0 ; i < 10 ; i++){
            EquationQuestionAdapter temp = equations.get(i);
            EquationQuestion converted = new EquationQuestion(temp.getTheAnswer() , temp.getTheAnswerInMaori() , temp.getTheEquation() , temp.getCurrentAttempts() , temp.isCorrect());
            customEquations.add(converted);
        }



    }
    //======================================================================================
    //                                   getters and setter
    //=====================================================================================
    public ObservableList<EquationQuestion> getCustomEquations() {
        return customEquations;
    }

    public String getCreationName() {
        return creationName.get();
    }

    public StringProperty creationNameProperty() {
        return creationName;
    }

    public String getTheDate() {
        return theDate.get();
    }

    public StringProperty theDateProperty() {
        return theDate;
    }

    public String getTheTime() {
        return theTime.get();
    }

    public StringProperty theTimeProperty() {
        return theTime;
    }

    public long getUnixTimeStamp() {
        return unixTimeStamp.get();
    }

    public LongProperty unixTimeStampProperty() {
        return unixTimeStamp;
    }
}
