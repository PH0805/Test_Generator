package Models;

import UTILS.ApplicationException;
import UTILS.HttpUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mgr.common.entities.Wynik;

import java.util.ArrayList;
import java.util.List;

public class WynikModel {


    private ObservableList<WynikFX> wynikFXESFXXESFXObservableList = FXCollections.observableArrayList();
    private ObservableList<WynikFX> wynikFXESFXXESFXObservableList2 = FXCollections.observableArrayList();
    private ObjectProperty<WynikFX> wynikFXObjectProperty = new SimpleObjectProperty<>(new WynikFX());
    private ObjectProperty<GrupaComboModel> GrupaFXObjectProperty = new SimpleObjectProperty<>(new GrupaComboModel());
    private ObjectProperty<IndexComboModel> IndexFXObjectProperty = new SimpleObjectProperty<>(new IndexComboModel());
    private ObjectProperty<TestComboModel> TestFXObjectProperty = new SimpleObjectProperty<>(new TestComboModel());

    private ObservableList<GrupaComboModel> grupaComboModelsbservableList = FXCollections.observableArrayList();
    private ObservableList<IndexComboModel> indexComboModelsobserveableList = FXCollections.observableArrayList();
    private ObservableList<TestComboModel> testComboModelsobserveableList = FXCollections.observableArrayList();


    public GrupaComboModel getGrupaFXObjectProperty() {
        return GrupaFXObjectProperty.get();
    }

    public ObjectProperty<GrupaComboModel> grupaFXObjectPropertyProperty() {
        return GrupaFXObjectProperty;
    }

    public void setGrupaFXObjectProperty(GrupaComboModel grupaFXObjectProperty) {
        this.GrupaFXObjectProperty.set(grupaFXObjectProperty);
    }

    public IndexComboModel getIndexFXObjectProperty() {
        return IndexFXObjectProperty.get();
    }

    public ObjectProperty<IndexComboModel> indexFXObjectPropertyProperty() {
        return IndexFXObjectProperty;
    }

    public void setIndexFXObjectProperty(IndexComboModel indexFXObjectProperty) {
        this.IndexFXObjectProperty.set(indexFXObjectProperty);
    }

    public TestComboModel getTestFXObjectProperty() {
        return TestFXObjectProperty.get();
    }

    public ObjectProperty<TestComboModel> testFXObjectPropertyProperty() {
        return TestFXObjectProperty;
    }

    public void setTestFXObjectProperty(TestComboModel testFXObjectProperty) {
        this.TestFXObjectProperty.set(testFXObjectProperty);
    }

    public WynikFX getWynikFXObjectProperty() {
        return wynikFXObjectProperty.get();
    }

    public ObjectProperty<WynikFX> wynikFXObjectPropertyProperty() {
        return wynikFXObjectProperty;
    }

    public void setWynikFXObjectProperty(WynikFX wynikFXObjectProperty) {
        this.wynikFXObjectProperty.set(wynikFXObjectProperty);
    }

    public ObservableList<GrupaComboModel> getGrupaComboModelsbservableList() {
        return grupaComboModelsbservableList;
    }

    public void setGrupaComboModelsbservableList(ObservableList<GrupaComboModel> grupaComboModelsbservableList) {
        this.grupaComboModelsbservableList = grupaComboModelsbservableList;
    }

    public ObservableList<IndexComboModel> getIndexComboModelsobserveableList() {
        return indexComboModelsobserveableList;
    }

    public void setIndexComboModelsobserveableList(ObservableList<IndexComboModel> indexComboModelsobserveableList) {
        this.indexComboModelsobserveableList = indexComboModelsobserveableList;
    }

    public ObservableList<TestComboModel> getTestComboModelsobserveableList() {
        return testComboModelsobserveableList;
    }

    public void setTestComboModelsobserveableList(ObservableList<TestComboModel> testComboModelsobserveableList) {
        this.testComboModelsobserveableList = testComboModelsobserveableList;
    }

    public ObservableList<WynikFX> getWynikFXESFXXESFXObservableList() {
        return wynikFXESFXXESFXObservableList;
    }

    public void setWynikFXESFXXESFXObservableList(ObservableList<WynikFX> wynikFXESFXXESFXObservableList) {
        this.wynikFXESFXXESFXObservableList = wynikFXESFXXESFXObservableList;
    }


    public ObservableList<WynikFX> getWynikFXESFXXESFXObservableList2() {
        return wynikFXESFXXESFXObservableList2;
    }

    public void setWynikFXESFXXESFXObservableList2(ObservableList<WynikFX> wynikFXESFXXESFXObservableList2) {
        this.wynikFXESFXXESFXObservableList2 = wynikFXESFXXESFXObservableList2;
    }

    public void init() throws ApplicationException {



        wynikFXESFXXESFXObservableList.clear();
/////// Tworzenie Kategori
        List<Wynik> ListaWynikow = HttpUtils.getAllWynik();







        ListaWynikow.forEach(c -> {

            WynikFX wynikFX = new WynikFX();

            wynikFX.setClass_Name_FX(c.getClassName());
            wynikFX.setId_FX(c.getId());
            wynikFX.setName_FX(c.getImie());
            wynikFX.setScore_FX(c.getScore());
            wynikFX.setSscoreProcent_FX(c.getsCORE_PROCENT());
            wynikFX.setNazwisko_FX(c.getNazwisko());
            wynikFX.setNumerIndexu_FX(c.getNumerIndexu());
            wynikFX.setTest_Name_FX(c.getTest_Name());

            wynikFXESFXXESFXObservableList.add(wynikFX);


        });

    ////////// Index
        List<String> Indexy  = new ArrayList<>();
        Indexy.clear();

        ListaWynikow.forEach(c->{
            if(Indexy.contains(c.getNumerIndexu()))
                return;
            Indexy.add(c.getNumerIndexu());
        });

        Indexy.forEach(c->{
            IndexComboModel indexComboModel = new IndexComboModel();
            indexComboModel.setIndexName(c.toString());
            this.indexComboModelsobserveableList.add(indexComboModel);

        });

    //// Grupa
        List<String>  Grupy  = new ArrayList<>();
        Grupy.clear();

        ListaWynikow.forEach(c->{
            if(Grupy.contains(c.getClassName()))
                return;
            Grupy.add(c.getClassName());
        });

        Grupy.forEach(c->{
            GrupaComboModel grupaComboModel = new GrupaComboModel();
            grupaComboModel.setNazwaGrupy(c.toString());
            this.grupaComboModelsbservableList.add(grupaComboModel);

        });



        /// Test

        List<String>  Testy  = new ArrayList<>();
        Testy.clear();

        ListaWynikow.forEach(c->{
            if(Testy.contains(c.getTest_Name()))
                return;
            Testy.add(c.getTest_Name());
        });

        Testy.forEach(c->{
            TestComboModel testComboModel = new TestComboModel();
            testComboModel.setTestName(c.toString());
            this.testComboModelsobserveableList.add(testComboModel);

        });




    }

    public void deleteWynikInDataBase() throws ApplicationException {
        int id = this.getWynikFXObjectProperty().getId_FX();
        HttpUtils.deleteWynik(id);

    }
}





