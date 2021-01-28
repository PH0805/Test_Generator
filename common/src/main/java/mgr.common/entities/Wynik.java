package mgr.common.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Wynik {

    @Id
    @GeneratedValue
    private Integer id;

    private String imie;

    private String nazwisko;

    private String numerIndexu;

    private String test_Name;

    private String score;

    private String sCORE_PROCENT;

    private String className;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNumerIndexu() {
        return numerIndexu;
    }

    public void setNumerIndexu(String numerIndexu) {
        this.numerIndexu = numerIndexu;
    }

    public String getTest_Name() {
        return test_Name;
    }

    public void setTest_Name(String test_Name) {
        this.test_Name = test_Name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getsCORE_PROCENT() {
        return sCORE_PROCENT;
    }

    public void setsCORE_PROCENT(String sCORE_PROCENT) {
        this.sCORE_PROCENT = sCORE_PROCENT;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

