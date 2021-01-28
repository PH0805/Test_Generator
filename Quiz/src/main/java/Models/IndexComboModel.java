package Models;

import javafx.beans.property.SimpleStringProperty;

public class IndexComboModel {

    SimpleStringProperty indexName = new SimpleStringProperty();

    public String getIndexName() {
        return indexName.get();
    }

    public SimpleStringProperty indexNameProperty() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName.set(indexName);
    }

    @Override
    public String toString() {
        return
                indexName.get().toString();

    }
}
