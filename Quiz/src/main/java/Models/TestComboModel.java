package Models;

import javafx.beans.property.SimpleStringProperty;

public class TestComboModel {

    SimpleStringProperty testName = new SimpleStringProperty();

    public String getTestName() {
        return testName.get();
    }

    public SimpleStringProperty testNameProperty() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName.set(testName);
    }

    @Override
    public String toString() {
        return
                testName.get().toString();

    }
}
