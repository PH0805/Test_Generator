package Controller;

import Models.PytanieModel;
import UTILS.ApplicationException;
import UTILS.HttpUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import mgr.common.entities.Pytania;
import mgr.common.entities.TestFilter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class QuizPropertiesController2 {

    /////////////////////////////////////////////////STATIC FIELDS ////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    public ComboBox WybierzButtonCombo;

    @FXML
    public CheckBox wielokrotnegoWyboruCheckBuutton;

    @FXML
    public Button StartButton;

    @FXML
    public CheckBox wyświetlOdpCheckBuutton;

    @FXML
    public CheckBox losowaKolejnośćPytańCheckBuutton;

    @FXML
    public CheckBox odpowiedziLosowaCheckBuutton;

    @FXML
    public TextField TimeTextField;

    public PokazTestyController pokazTestyController;

    public MainViewController mainViewController;

    public PytanieModel pytanieModel;

    public TextField NumberOfQuestion;

    public Button BackButton;

    public Button startTest;

    public TextField passwordId;

    static String Password;

    public TextField setPasswordId;

    public TextField testNameid;

    public TextArea OpisTextID;

    public TextField StartQuestionID;
    public CheckBox setTimeCheck;
    public Label liczbapytan;
    public Label QuestionNumbers;
    public int numberOfQuestionInTest = 0;

    public static String getPassword() {
        return Password;
    }

    public static void setPassword(String password) {
        Password = password;
    }

    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    public void initialize() throws ApplicationException, IOException {

        pytanieModel = new PytanieModel();

        pytanieModel.init();

        this.WybierzButtonCombo.getItems().addAll(pytanieModel.getKategorie_FXObservableList());
        this.StartQuestionID.setText("1");
        this.NumberOfQuestion.setText("0");
        this.TimeTextField.disableProperty().setValue(true);




    }

    public void backToMenu(ActionEvent actionEvent) {

        Stage stage = (Stage) mainViewController.BorderPaneMainView.getScene().getWindow();
        stage.setWidth(459);
        stage.setHeight(317);
        stage.setMinWidth(459);
        stage.setMinHeight(317);


        stage.setResizable(false);

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.setTitle("Okno główne");
        mainViewController.loadMenu();
    }

    public void onWybierzKategorie(ActionEvent actionEvent) {

        Predicate<Pytania> mNazwa = pytanieFX1 -> pytanieFX1.getKategoria().equals(this.WybierzButtonCombo.getSelectionModel().selectedItemProperty().getValue().toString());
        List<Pytania> listaPytań = HttpUtils.getAllQuestions().stream().filter(mNazwa).collect(Collectors.toList());

        this.liczbapytan.setText(String.valueOf(listaPytań.size()));
        //this.NumberOfQuestion.setText(this.liczbapytan.getText()); todo


    }

    public void MakeTest(ActionEvent actionEvent) throws ApplicationException, SQLException {

        if ((this.NumberOfQuestion.textProperty().getValue().matches("\\-?\\d+"))) {
            int LiczbapytańWTescie = Integer.parseInt(this.QuestionNumbers.textProperty().getValue());   /// todo
            int LiczbapytańwKategori = Integer.parseInt(this.liczbapytan.textProperty().getValue());

            if (this.testNameid.textProperty().getValue().equals("") ||
                    (this.NumberOfQuestion.textProperty().getValue().equals("") ||
                            this.setPasswordId.textProperty().getValue().equals("")) ||
                    this.WybierzButtonCombo.getSelectionModel().isEmpty() || this.NumberOfQuestion.textProperty().getValue().equals("0")) {


                UTILS.DialogUtils.errrorAlert_wart();
            } else if (this.setTimeCheck.selectedProperty().getValue() && TimeTextField.textProperty().getValue().equals("")) {

                UTILS.DialogUtils.errrorAlert_wart();
            }
            else if(numberOfQuestionInTest < Integer.parseInt(this.NumberOfQuestion.textProperty().getValue()) || Integer.parseInt(this.StartQuestionID.textProperty().getValue()) > numberOfQuestionInTest ){
                UTILS.DialogUtils.errrorAlert_wart();
            }
            else if (LiczbapytańwKategori - LiczbapytańWTescie < 0) {
                UTILS.DialogUtils.errrorAlert_wart();
            } else {
                UTILS.DialogUtils.MakeTest();
                TestFilter testFilter = new TestFilter();
                testFilter.setNameTest(this.testNameid.textProperty().getValue());


                testFilter.setIloscPytan(Integer.parseInt(this.NumberOfQuestion.textProperty().getValue()));

                testFilter.setRandom_Anwser(this.odpowiedziLosowaCheckBuutton.isSelected());
                testFilter.setRandom_Question(this.losowaKolejnośćPytańCheckBuutton.isSelected());
                testFilter.setNotShowAnwser(this.wyświetlOdpCheckBuutton.isSelected());
                if (this.setTimeCheck.selectedProperty().getValue()) {
                    testFilter.setTime_of_Question(Integer.parseInt(this.TimeTextField.textProperty().getValue()));
                } else testFilter.setTime_of_Question(99999999);

                testFilter.setStartQuestionID(this.StartQuestionID.textProperty().getValue());
                testFilter.setPassword(this.setPasswordId.textProperty().getValue());
                testFilter.setOpisTestu(this.OpisTextID.textProperty().getValue());
                testFilter.setKategoria(this.WybierzButtonCombo.getSelectionModel().selectedItemProperty().getValue().toString());


                HttpUtils.createTestFilter(testFilter);


            }


        } else {

            UTILS.DialogUtils.errrorAlert_Passowrd();
        }





    }

    public void StartTest(ActionEvent actionEvent) throws ApplicationException {

        List<TestFilter> listaPytań = HttpUtils.getAllTestFilters(); // todo query

        final boolean[] valide = { false };

        listaPytań.forEach(c -> {
            if (c.getPassword().equals(this.passwordId.textProperty().getValue())) {

                setPassword(c.getPassword());
                valide[0] = true;

            }
        });

        if (valide[0]) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/Start2.fxml"));
            Pane gridPane = null;
            try {
                gridPane = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            StartController2 startController = loader.getController();
            startController.setMainViewController(mainViewController);
            mainViewController.setCenterNew(gridPane);

        }

    }

    public void pokazTesty(ActionEvent actionEvent) throws IOException {

        Stage userStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("/PokazTesty.fxml").openStream());
        PokazTestyController pokazTestyController = loader.getController();

        Scene scene = new Scene(root);
        userStage.setScene(scene);
        userStage.setTitle("Baza Testów");
        userStage.setResizable(false);
        userStage.show();

    }


    public void setTime(ActionEvent actionEvent) {
        if (this.setTimeCheck.selectedProperty().getValue()) {
            this.TimeTextField.clear();
            this.TimeTextField.disableProperty().setValue(false);
        }
        if (this.setTimeCheck.selectedProperty().getValue() == false) {
            this.TimeTextField.clear();
            this.TimeTextField.disableProperty().setValue(true);
        }
    }


    public void CalculateNumber(MouseEvent actionEvent) {



       /// numberOfQuestionInTest = (Integer.parseInt(this.NumberOfQuestion.textProperty().getValue()) - Integer.parseInt(this.StartQuestionID.textProperty().getValue()));
        numberOfQuestionInTest = (Integer.parseInt(this.liczbapytan.textProperty().getValue()) - Integer.parseInt(this.StartQuestionID.textProperty().getValue())+1);






       this.QuestionNumbers.setText(String.valueOf(numberOfQuestionInTest));

      //  NumberOfQuestion.setText(String.valueOf(numberOfQuestionInTest));
    }


}



