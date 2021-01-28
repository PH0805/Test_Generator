package Controller;

import Models.*;
import UTILS.ApplicationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PokazWynikiController {

    public VBox PokażPytaniaID;

    public TableView<WynikFX> TabelaSocre;

    public TableColumn<WynikFX, String> Numer_Indexu_Column;

    public TableColumn<WynikFX, String> Class_NAME_Grp;

    public TableColumn<WynikFX, String> Imie_Column;

    public TableColumn<WynikFX, String> NAZWISKO_Column;

    public TableColumn<WynikFX, String> Test_Name_Coulmn;

    public TableColumn<WynikFX, String> SCORE_Coulmn;

    public TableColumn<WynikFX, String> WynikProcentowy_Column;

    public Button backButtonId;

    public ComboBox<TestComboModel> SortTest;

    public ComboBox<GrupaComboModel> SrotGRUpa;

    public ComboBox<IndexComboModel> SortIndex;

    MainViewController mainViewController;

    WynikModel wynikModel;

    public void initialize() throws ApplicationException {

        wynikModel = new WynikModel();

        wynikModel.init();

        this.SortTest.setItems(this.wynikModel.getTestComboModelsobserveableList());
        this.SrotGRUpa.setItems(this.wynikModel.getGrupaComboModelsbservableList());
        this.SortIndex.setItems(this.wynikModel.getIndexComboModelsobserveableList());
        this.wynikModel.grupaFXObjectPropertyProperty().bind(this.SrotGRUpa.valueProperty());
        this.wynikModel.testFXObjectPropertyProperty().bind(this.SortTest.valueProperty());
        this.wynikModel.indexFXObjectPropertyProperty().bind(this.SortIndex.valueProperty());

        this.TabelaSocre.setItems(this.wynikModel.getWynikFXESFXXESFXObservableList());

        RefreshTable();

        this.TabelaSocre.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.wynikModel.setWynikFXObjectProperty(newValue);
        });

    }

    public MainViewController getMainViewController() {
        return mainViewController;
    }

    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    public void back(ActionEvent actionEvent) {

        Stage stage = (Stage) mainViewController.BorderPaneMainView.getScene().getWindow();
        stage.setWidth(459);
        stage.setHeight(317);
        stage.setTitle("Okno główne");
        stage.setResizable(false);

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

        mainViewController.loadMenu();

    }

    public void SortCombo(ActionEvent actionEvent) throws ApplicationException {


        ObservableList<WynikFX> wynikFXObservableListFXObservableList2 = FXCollections.observableArrayList();
        //    ObservableList<WynikFX> wynikFXObservableListFXObservableList3 = FXCollections.observableArrayList();;
        List<WynikFX> WyfiltrowanaLista = this.wynikModel.getWynikFXESFXXESFXObservableList();
        if (!(this.SortTest.getSelectionModel().isEmpty())) {

            String Selectedname = this.SortTest.getSelectionModel().getSelectedItem().getTestName();
            Predicate<WynikFX> filtracaPoNazwachTestu = testFilterPass -> testFilterPass.getTest_Name_FX().equals(Selectedname);
            WyfiltrowanaLista = WyfiltrowanaLista.stream().filter(filtracaPoNazwachTestu).collect(Collectors.toList());

        }

        if (!(this.SrotGRUpa.getSelectionModel().isEmpty())) {
            String SelectGRP = this.SrotGRUpa.getSelectionModel().getSelectedItem().getNazwaGrupy();
            Predicate<WynikFX> filtracaPoNazwachGrp = testFilterPass -> testFilterPass.getClass_Name_FX().equals(SelectGRP);
            WyfiltrowanaLista = WyfiltrowanaLista.stream().filter(filtracaPoNazwachGrp).collect(Collectors.toList());
        }

        if (!(this.SortIndex.getSelectionModel().isEmpty())) {
            String SelectIndex = this.SortIndex.getSelectionModel().getSelectedItem().getIndexName();
            Predicate<WynikFX> filtracaPoIndexie = testFilterPass -> testFilterPass.getNumerIndexu_FX().equals(SelectIndex);
            WyfiltrowanaLista = WyfiltrowanaLista.stream().filter(filtracaPoIndexie).collect(Collectors.toList());
        }

        WyfiltrowanaLista.forEach(c -> {

            WynikFX wynikFX = new WynikFX();

            wynikFX.setClass_Name_FX(c.getClass_Name_FX());
            wynikFX.setId_FX(c.getId_FX());
            wynikFX.setName_FX(c.getName_FX());
            wynikFX.setScore_FX(c.getScore_FX());
            wynikFX.setSscoreProcent_FX(c.getSscoreProcent_FX());
            wynikFX.setNazwisko_FX(c.getNazwisko_FX());
            wynikFX.setNumerIndexu_FX(c.getNumerIndexu_FX());
            wynikFX.setTest_Name_FX(c.getTest_Name_FX());

            wynikFXObservableListFXObservableList2.add(wynikFX);

        });

        this.TabelaSocre.setItems(wynikFXObservableListFXObservableList2);

        RefreshTable();





/*
        if(!(this.SrotGRUpa.getSelectionModel().isEmpty())){

            String Selectedname = this.SrotGRUpa.getSelectionModel().getSelectedItem().getNazwaGrupy().toString();
            Predicate<WynikFX> filtracaPoNazwachGrupach= testFilterPass -> testFilterPass.getTest_Name_FX().equals(Selectedname);
            List<WynikFX> WyfiltrowanaLista = wynikFXObservableListFXObservableList2.stream().filter(filtracaPoNazwachGrupach).collect(Collectors.toList());


            WyfiltrowanaLista.forEach(c -> {

                WynikFX wynikFX = new WynikFX();

                wynikFX.setClass_Name_FX(c.getClass_Name_FX());
                wynikFX.setId_FX(c.getId_FX());
                wynikFX.setName_FX(c.getName_FX());
                wynikFX.setScore_FX(c.getScore_FX());
                wynikFX.setSscoreProcent_FX(c.getSscoreProcent_FX());
                wynikFX.setNazwisko_FX(c.getNazwisko_FX());
                wynikFX.setNumerIndexu_FX(c.getNumerIndexu_FX());
                wynikFX.setTest_Name_FX(c.getTest_Name_FX());

                wynikFXObservableListFXObservableList3.add(wynikFX);


            });

            this.TabelaSocre.setItems(wynikFXObservableListFXObservableList3);

            RefreshTable();





        }
        */

    }

    private void RefreshTable() {
        this.Numer_Indexu_Column.setCellValueFactory(cellData -> cellData.getValue().numerIndexu_FXProperty());
        this.Class_NAME_Grp.setCellValueFactory(cellData -> cellData.getValue().class_Name_FXProperty());
        this.Imie_Column.setCellValueFactory(cellData -> cellData.getValue().name_FXProperty());
        this.NAZWISKO_Column.setCellValueFactory(cellData -> cellData.getValue().nazwisko_FXProperty());
        this.Test_Name_Coulmn.setCellValueFactory(cellData -> cellData.getValue().test_Name_FXProperty());
        this.SCORE_Coulmn.setCellValueFactory(cellData -> cellData.getValue().score_FXProperty());
        this.WynikProcentowy_Column.setCellValueFactory(cellData -> cellData.getValue().sscoreProcent_FXProperty());

        this.Numer_Indexu_Column.setCellFactory(TextFieldTableCell.forTableColumn());
        this.Class_NAME_Grp.setCellFactory(TextFieldTableCell.forTableColumn());
        this.Imie_Column.setCellFactory(TextFieldTableCell.forTableColumn());
        this.NAZWISKO_Column.setCellFactory(TextFieldTableCell.forTableColumn());
        this.Test_Name_Coulmn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.SCORE_Coulmn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.WynikProcentowy_Column.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public void deleteFilters(ActionEvent actionEvent) {

        try {
            this.wynikModel.getTestComboModelsobserveableList().clear();
            this.wynikModel.getIndexComboModelsobserveableList().clear();
            this.wynikModel.getGrupaComboModelsbservableList().clear();
            wynikModel.init();

        } catch (ApplicationException e) {
            e.printStackTrace();
        }


        this.SortTest.setItems(this.wynikModel.getTestComboModelsobserveableList());
        this.SrotGRUpa.setItems(this.wynikModel.getGrupaComboModelsbservableList());
        this.SortIndex.setItems(this.wynikModel.getIndexComboModelsobserveableList());
        this.SortTest.getSelectionModel().isEmpty();
        this.SortIndex.getSelectionModel().isEmpty();
        this.SrotGRUpa.getSelectionModel().isEmpty();
        this.wynikModel.grupaFXObjectPropertyProperty().bind(this.SrotGRUpa.valueProperty());
        this.wynikModel.testFXObjectPropertyProperty().bind(this.SortTest.valueProperty());
        this.wynikModel.indexFXObjectPropertyProperty().bind(this.SortIndex.valueProperty());

        this.TabelaSocre.setItems(this.wynikModel.getWynikFXESFXXESFXObservableList());

        RefreshTable();


    }

    public void deleteWynik(ActionEvent actionEvent) {

        try {
            this.wynikModel.deleteWynikInDataBase();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }

    }

/*

List<PytanieFX> WyfiltrowanaLista = firtSelectedCombo();

        System.out.println(WyfiltrowanaLista.size());
        this.pytanieModel.getPytanieFXObservableList().clear();
        WyfiltrowanaLista.forEach(c->{
            PytanieFX pytanieFX = Converter_FX(c);


            this.pytanieModel.getPytanieFXObservableList().add(pytanieFX);
        });


        ObservableList<PytanieFX> pytanieFXObservableList2 = this.pytanieModel.getPytanieFXObservableList();


        this.Tabela.setItems(pytanieFXObservableList2);
        getItemsToColumn();
    }
 */