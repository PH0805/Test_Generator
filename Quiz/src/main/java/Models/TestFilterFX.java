package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TestFilterFX {

    IntegerProperty id_FX = new SimpleIntegerProperty();
    StringProperty Test_Name_FX =new SimpleStringProperty();
    StringProperty OpisTESTU_FX =new SimpleStringProperty();
    StringProperty IloscPytan_FX =new SimpleStringProperty();
    StringProperty StartQuestionID_FX =new SimpleStringProperty();
    StringProperty notShowAnwser_FX =new SimpleStringProperty();
    StringProperty RandomQuestion_FX =new SimpleStringProperty();
    StringProperty RandomAwser_FX =new SimpleStringProperty();
    StringProperty Tune_of_Question_FX =new SimpleStringProperty();
    StringProperty Password_FX =new SimpleStringProperty();

    StringProperty Kategoria_FX =new SimpleStringProperty();

    public int getId_FX() {
        return id_FX.get();
    }

    public IntegerProperty id_FXProperty() {
        return id_FX;
    }

    public void setId_FX(int id_FX) {
        this.id_FX.set(id_FX);
    }

    public String getTest_Name_FX() {
        return Test_Name_FX.get();
    }

    public StringProperty test_Name_FXProperty() {
        return Test_Name_FX;
    }

    public void setTest_Name_FX(String test_Name_FX) {
        this.Test_Name_FX.set(test_Name_FX);
    }

    public String getOpisTESTU_FX() {
        return OpisTESTU_FX.get();
    }

    public StringProperty opisTESTU_FXProperty() {
        return OpisTESTU_FX;
    }

    public void setOpisTESTU_FX(String opisTESTU_FX) {
        this.OpisTESTU_FX.set(opisTESTU_FX);
    }

    public String getIloscPytan_FX() {
        return IloscPytan_FX.get();
    }

    public StringProperty iloscPytan_FXProperty() {
        return IloscPytan_FX;
    }

    public void setIloscPytan_FX(String iloscPytan_FX) {
        this.IloscPytan_FX.set(iloscPytan_FX);
    }

    public String getStartQuestionID_FX() {
        return StartQuestionID_FX.get();
    }

    public StringProperty startQuestionID_FXProperty() {
        return StartQuestionID_FX;
    }

    public void setStartQuestionID_FX(String startQuestionID_FX) {
        this.StartQuestionID_FX.set(startQuestionID_FX);
    }

    public String getNotShowAnwser_FX() {
        return notShowAnwser_FX.get();
    }

    public StringProperty notShowAnwser_FXProperty() {
        return notShowAnwser_FX;
    }

    public void setNotShowAnwser_FX(String notShowAnwser_FX) {
        this.notShowAnwser_FX.set(notShowAnwser_FX);
    }

    public String getRandomQuestion_FX() {
        return RandomQuestion_FX.get();
    }

    public StringProperty randomQuestion_FXProperty() {
        return RandomQuestion_FX;
    }

    public void setRandomQuestion_FX(String randomQuestion_FX) {
        this.RandomQuestion_FX.set(randomQuestion_FX);
    }

    public String getRandomAwser_FX() {
        return RandomAwser_FX.get();
    }

    public StringProperty randomAwser_FXProperty() {
        return RandomAwser_FX;
    }

    public void setRandomAwser_FX(String randomAwser_FX) {
        this.RandomAwser_FX.set(randomAwser_FX);
    }

    public String getTune_of_Question_FX() {
        return Tune_of_Question_FX.get();
    }

    public StringProperty tune_of_Question_FXProperty() {
        return Tune_of_Question_FX;
    }

    public void setTune_of_Question_FX(String tune_of_Question_FX) {
        this.Tune_of_Question_FX.set(tune_of_Question_FX);
    }

    public String getPassword_FX() {
        return Password_FX.get();
    }

    public StringProperty password_FXProperty() {
        return Password_FX;
    }

    public void setPassword_FX(String password_FX) {
        this.Password_FX.set(password_FX);
    }

    public String getKategoria_FX() {
        return Kategoria_FX.get();
    }

    public StringProperty kategoria_FXProperty() {
        return Kategoria_FX;
    }

    public void setKategoria_FX(String kategoria_FX) {
        this.Kategoria_FX.set(kategoria_FX);
    }
}
