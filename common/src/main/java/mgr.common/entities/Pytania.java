package mgr.common.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pytania {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 500)
    private String pytanie;
    @Column(length = 500)
    private String odpA;
    @Column(length = 500)
    private String odpB;
    @Column(length = 500)
    private String odpC;
    @Column(length = 500)
    private String odpD;
    @Column(length = 500)
    private String odpE;
    @Column(length = 500)
    private String odpPopr;
    //    @Column(nullable = false)
    private String kategoria;
    @Column(length = 999999999)
    private String images;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPytanie() {
        return pytanie;
    }

    public void setPytanie(String pytanie) {
        this.pytanie = pytanie;
    }

    public String getOdpA() {
        return odpA;
    }

    public void setOdpA(String odpA) {
        this.odpA = odpA;
    }

    public String getOdpB() {
        return odpB;
    }

    public void setOdpB(String odpB) {
        this.odpB = odpB;
    }

    public String getOdpC() {
        return odpC;
    }

    public void setOdpC(String odpC) {
        this.odpC = odpC;
    }

    public String getOdpD() {
        return odpD;
    }

    public void setOdpD(String odpD) {
        this.odpD = odpD;
    }

    public String getOdpE() {
        return odpE;
    }

    public void setOdpE(String odpE) {
        this.odpE = odpE;
    }

    public String getOdpPopr() {
        return odpPopr;
    }

    public void setOdpPopr(String odpPopr) {
        this.odpPopr = odpPopr;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Pytania{" +
                "id=" + id +
                ", Pytanie='" + pytanie + '\'' +
                ", OdpA='" + odpA + '\'' +
                ", OdpB='" + odpB + '\'' +
                ", OdpC='" + odpC + '\'' +
                ", OdpD='" + odpD + '\'' +
                ", OdpE='" + odpE + '\'' +
                ", OdpPopr='" + odpPopr + '\'' +
                ", Kategoria='" + kategoria + '\'' +
                ", images=" + images +
                '}';
    }
}

