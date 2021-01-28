package Models;

import javafx.beans.property.SimpleStringProperty;

public class TestNameComboModel {

    SimpleStringProperty Name = new  SimpleStringProperty();

    public String getName() {
        return Name.get();
    }

    public SimpleStringProperty nameProperty() {
        return Name;
    }

    public void setName(String name) {
        this.Name.set(name);
    }

    @Override
    public String toString() {
        return Name.get();

    }
}
