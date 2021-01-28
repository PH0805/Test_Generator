package mgr.common.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TestFilter {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    public String nameTest;

    public String opisTestu;

    private int iloscPytan;

    private String startQuestionID;

    private Boolean notShowAnwser;

    private Boolean random_Question;

    private Boolean random_Anwser;

    private int time_of_Question;

    public String password;

    private String kategoria;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameTest() {
        return nameTest;
    }

    public void setNameTest(String nameTest) {
        this.nameTest = nameTest;
    }

    public String getOpisTestu() {
        return opisTestu;
    }

    public void setOpisTestu(String opisTestu) {
        this.opisTestu = opisTestu;
    }

    public int getIloscPytan() {
        return iloscPytan;
    }

    public void setIloscPytan(int iloscPytan) {
        this.iloscPytan = iloscPytan;
    }

    public String getStartQuestionID() {
        return startQuestionID;
    }

    public void setStartQuestionID(String startQuestionID) {
        this.startQuestionID = startQuestionID;
    }

    public Boolean getNotShowAnwser() {
        return notShowAnwser;
    }

    public void setNotShowAnwser(Boolean notShowAnwser) {
        this.notShowAnwser = notShowAnwser;
    }

    public Boolean getRandom_Question() {
        return random_Question;
    }

    public void setRandom_Question(Boolean random_Question) {
        this.random_Question = random_Question;
    }

    public Boolean getRandom_Anwser() {
        return random_Anwser;
    }

    public void setRandom_Anwser(Boolean random_Anwser) {
        this.random_Anwser = random_Anwser;
    }

    public int getTime_of_Question() {
        return time_of_Question;
    }

    public void setTime_of_Question(int time_of_Question) {
        this.time_of_Question = time_of_Question;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }
}

