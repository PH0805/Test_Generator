package Models;

import javafx.beans.property.SimpleStringProperty;

public class GrupaComboModel {

    SimpleStringProperty nazwaGrupy = new  SimpleStringProperty();

    public String getNazwaGrupy() {
        return nazwaGrupy.get();
    }

    public SimpleStringProperty nazwaGrupyProperty() {
        return nazwaGrupy;
    }

    public void setNazwaGrupy(String nazwaGrupy) {
        this.nazwaGrupy.set(nazwaGrupy);
    }


    @Override
    public String toString() {
        return
                nazwaGrupy.get().toString();

    }
}
