package Controller;

import Models.PytanieFX;
import Models.TestFilterFX;
import Models.TestFilterModel;
import UTILS.ApplicationException;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

public class PokazTestyController {


    public TableView<TestFilterFX> TABELA_ID;
    public TableColumn<TestFilterFX, String> Name_COLUMN;
    public TableColumn<TestFilterFX, String> OPIS_COLUMN;
    public TableColumn<TestFilterFX, String> KATEGORIA_COLUMN;
    public TableColumn<TestFilterFX, String> ILOŚĆPYTAŃ_COLUMKN;
    public TableColumn<TestFilterFX, String> NOTSHOWANWSER_COLUMN;
    public TableColumn<TestFilterFX, String> RANDOM_QUESTION_COLUMN;
    public TableColumn<TestFilterFX, String> RANDOM_ANWSERS_COLUMN;
    public TableColumn<TestFilterFX, String> TIME_OF_QUESTION_COLUMN;
    public TableColumn<TestFilterFX, String> PASSWORDID_COLUMN;

    TestFilterModel testFilterModel;

    public void initialize() throws ApplicationException {
testFilterModel = new TestFilterModel();

testFilterModel.init();



        this.TABELA_ID.setItems(this.testFilterModel.getTestFilterFXXESFXObservableList());

        this.Name_COLUMN.setCellValueFactory(cellData -> cellData.getValue().test_Name_FXProperty());
        this.OPIS_COLUMN.setCellValueFactory(cellData -> cellData.getValue().opisTESTU_FXProperty());
        this.KATEGORIA_COLUMN.setCellValueFactory(cellData -> cellData.getValue().kategoria_FXProperty());
        this.ILOŚĆPYTAŃ_COLUMKN.setCellValueFactory(cellData -> cellData.getValue().iloscPytan_FXProperty());
        this.NOTSHOWANWSER_COLUMN.setCellValueFactory(cellData -> cellData.getValue().notShowAnwser_FXProperty());
        this.RANDOM_ANWSERS_COLUMN.setCellValueFactory(cellData -> cellData.getValue().randomAwser_FXProperty());
        this.RANDOM_QUESTION_COLUMN.setCellValueFactory(cellData -> cellData.getValue().randomQuestion_FXProperty());
        this.TIME_OF_QUESTION_COLUMN.setCellValueFactory(cellData -> cellData.getValue().tune_of_Question_FXProperty());
        this.PASSWORDID_COLUMN.setCellValueFactory(cellData -> cellData.getValue().password_FXProperty());

        this.Name_COLUMN.setCellFactory(TextFieldTableCell.forTableColumn());
        this.OPIS_COLUMN.setCellFactory(TextFieldTableCell.forTableColumn());
        this.KATEGORIA_COLUMN.setCellFactory(TextFieldTableCell.forTableColumn());
        this.ILOŚĆPYTAŃ_COLUMKN.setCellFactory(TextFieldTableCell.forTableColumn());
        this.NOTSHOWANWSER_COLUMN.setCellFactory(TextFieldTableCell.forTableColumn());
        this.RANDOM_ANWSERS_COLUMN.setCellFactory(TextFieldTableCell.forTableColumn());
        this.RANDOM_QUESTION_COLUMN.setCellFactory(TextFieldTableCell.forTableColumn());
        this.TIME_OF_QUESTION_COLUMN.setCellFactory(TextFieldTableCell.forTableColumn());
        this.PASSWORDID_COLUMN.setCellFactory(TextFieldTableCell.forTableColumn());


        this.TABELA_ID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.testFilterModel.setTestFilterFXObjectProperty(newValue);
        });


    }

    public void testDelete(ActionEvent actionEvent) {
        try {
            testFilterModel.deleteTestFilterInDataBase();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
}
