package Controller;

import UTILS.ApplicationException;
import UTILS.HttpUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import mgr.common.entities.TestFilter;

import java.io.IOException;
import java.util.List;



public class StudentStartTestController {
    public Button StartButton;

    public TextField PasswordId;

    public TextField SurnameStudentId;

    public Button BackButton;

    public TextField nameStudentId;

    public TextField IndexStudentNumber;

    public TextField ClassNameStudentId;

    private static String nameStudent;

    private static String surnameStudent;

    private static String indexIdStudent;

    private static String ClassNameStudent;

    private static String Password;

    public static String getPassword() {
        return Password;
    }

    public static void setPassword(String password) {
        Password = password;
    }

    public static String getNameStudent() {
        return nameStudent;
    }

    public static void setNameStudent(String nameStudent) {
        StudentStartTestController.nameStudent = nameStudent;
    }

    public static String getSurnameStudent() {
        return surnameStudent;
    }

    public static void setSurnameStudent(String surnameStudent) {
        StudentStartTestController.surnameStudent = surnameStudent;
    }

    public static String getIndexIdStudent() {
        return indexIdStudent;
    }

    public static void setIndexIdStudent(String indexIdStudent) {
        StudentStartTestController.indexIdStudent = indexIdStudent;
    }

    public static String getClassNameStudent() {
        return ClassNameStudent;
    }

    public static void setClassNameStudent(String classNameStudent) {
        ClassNameStudent = classNameStudent;
    }

    public MainViewController mainViewController;

    public MainViewController getMainViewController() {
        return mainViewController;
    }

    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }


    public void ValidateTimeFormat(ActionEvent actionEvent) throws ApplicationException {

        List<TestFilter> listaPytań = HttpUtils.getAllTestFilters();

        final boolean[] valide = { false };

        listaPytań.forEach(c -> {
            if (c.getPassword().equals(this.PasswordId.textProperty().getValue())) {
                setPassword(c.getPassword());
                valide[0] = true;

            }
        });

        if (valide[0]) {

            setClassNameStudent(this.ClassNameStudentId.textProperty().getValue());
            setNameStudent(this.nameStudentId.textProperty().getValue());
            setSurnameStudent(this.SurnameStudentId.textProperty().getValue());
            setIndexIdStudent(this.IndexStudentNumber.textProperty().getValue());

            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/Start2.fxml"));
            Pane gridPane = null;
            try {
                gridPane = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage stage = (Stage) this.SurnameStudentId.getScene().getWindow();


            stage.setResizable(true);
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setHeight(primScreenBounds.getHeight());
            stage.setWidth(primScreenBounds.getWidth());
            // stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            //  stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);

            stage.setX(0);
            stage.setY(0);
            //  stage.setY(3);
            //  stage.setY(3);

            //   stage.setHeight(primScreenBounds.getHeight()-5);
            //   stage.setWidth(primScreenBounds.getWidth()-5);
            // stage.setFullScreen(true);

            StartController2 startController = loader.getController();
            startController.setMainViewController(mainViewController);
            mainViewController.setCenterNew(gridPane);

        } else {
            UTILS.DialogUtils.errrorAlert_Passowrd();
        }

    }

    public void backToMenu(ActionEvent actionEvent) {

        Stage stage = (Stage) mainViewController.BorderPaneMainView.getScene().getWindow();
        stage.setWidth(459);
        stage.setHeight(317);
        stage.setMaxWidth(459);
        stage.setMaxHeight(317);
        stage.setResizable(false);
        stage.setTitle("Okno główne");
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

        mainViewController.loadMenu();


    }
}
