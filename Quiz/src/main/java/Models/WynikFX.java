package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WynikFX {

    IntegerProperty id_FX = new SimpleIntegerProperty();
    StringProperty Name_FX =new SimpleStringProperty();
    StringProperty Nazwisko_FX =new SimpleStringProperty();
    StringProperty NumerIndexu_FX =new SimpleStringProperty();
    StringProperty Test_Name_FX =new SimpleStringProperty();
    StringProperty Score_FX =new SimpleStringProperty();
    StringProperty SscoreProcent_FX =new SimpleStringProperty();
    StringProperty Class_Name_FX =new SimpleStringProperty();

    public int getId_FX() {
        return id_FX.get();
    }

    public IntegerProperty id_FXProperty() {
        return id_FX;
    }

    public void setId_FX(int id_FX) {
        this.id_FX.set(id_FX);
    }

    public String getName_FX() {
        return Name_FX.get();
    }

    public StringProperty name_FXProperty() {
        return Name_FX;
    }

    public void setName_FX(String name_FX) {
        this.Name_FX.set(name_FX);
    }

    public String getNazwisko_FX() {
        return Nazwisko_FX.get();
    }

    public StringProperty nazwisko_FXProperty() {
        return Nazwisko_FX;
    }

    public void setNazwisko_FX(String nazwisko_FX) {
        this.Nazwisko_FX.set(nazwisko_FX);
    }

    public String getNumerIndexu_FX() {
        return NumerIndexu_FX.get();
    }

    public StringProperty numerIndexu_FXProperty() {
        return NumerIndexu_FX;
    }

    public void setNumerIndexu_FX(String numerIndexu_FX) {
        this.NumerIndexu_FX.set(numerIndexu_FX);
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

    public String getScore_FX() {
        return Score_FX.get();
    }

    public StringProperty score_FXProperty() {
        return Score_FX;
    }

    public void setScore_FX(String score_FX) {
        this.Score_FX.set(score_FX);
    }

    public String getSscoreProcent_FX() {
        return SscoreProcent_FX.get();
    }

    public StringProperty sscoreProcent_FXProperty() {
        return SscoreProcent_FX;
    }

    public void setSscoreProcent_FX(String sscoreProcent_FX) {
        this.SscoreProcent_FX.set(sscoreProcent_FX);
    }

    public String getClass_Name_FX() {
        return Class_Name_FX.get();
    }

    public StringProperty class_Name_FXProperty() {
        return Class_Name_FX;
    }

    public void setClass_Name_FX(String class_Name_FX) {
        this.Class_Name_FX.set(class_Name_FX);
    }
}
