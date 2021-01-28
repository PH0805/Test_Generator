package Controller;

import UTILS.HttpUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;


public class MenuController {


    public Button StartQuizButton;
    public Button PokazPytaniaButton;
    public Button OpenWynikiButton;
    public Button DrukujTestButton;
    public Button QuitButton;
    public Button TestGeneratorID;
    MainViewController mainViewController;

    Rectangle2D primaryScreen = Screen.getPrimary().getVisualBounds();
    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    public void initialize(){





        if(LoginPanelController.isIsStudent() == true){


            this.TestGeneratorID.disableProperty().setValue(true);
          this.PokazPytaniaButton.disableProperty().setValue(true);
          this.OpenWynikiButton.disableProperty().set(true);
          this.DrukujTestButton.disableProperty().setValue(true);
           this.PokazPytaniaButton.disableProperty().setValue(true);

        }
        if (LoginPanelController.isIsStudent() == false) {

            this.TestGeneratorID.disableProperty().setValue(false);
            this.PokazPytaniaButton.disableProperty().setValue(false);
            this.OpenWynikiButton.disableProperty().set(false);
            this.DrukujTestButton.disableProperty().setValue(false);
            this.PokazPytaniaButton.disableProperty().setValue(false);
            this.StartQuizButton.disableProperty().setValue(true); // todo

        }

       
    }





    public void openGenrator() throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/QuizProperties2.fxml"));
        Pane gridPane = null;
        try {
            gridPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage1 = (Stage) mainViewController.getBorderPaneMainView().getScene().getWindow();

        stage1.setWidth(800);
        stage1.setHeight(500);
        stage1.setMinHeight(500);
        stage1.setMinWidth(800);
        stage1.setResizable(false);
        stage1.setTitle("Generator testów");

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage1.setX((primScreenBounds.getWidth() - stage1.getWidth()) / 2);
        stage1.setY((primScreenBounds.getHeight() - stage1.getHeight()) / 2);

        QuizPropertiesController2 quizPropertiesController = loader.getController();
        quizPropertiesController.setMainViewController(mainViewController);
        mainViewController.setCenterNew(gridPane);

    }






    public void openPokażPytania() {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/PokazPytania.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PokazPytaniaController pokazPytaniaController = loader.getController();
        pokazPytaniaController.setMainViewController(mainViewController);

        Stage stage1 = (Stage) mainViewController.getBorderPaneMainView().getScene().getWindow();
        stage1.setMaxHeight(222222);
        stage1.setMaxWidth(222222);
        stage1.setResizable(false);
        //  stage1.setFullScreen(true);
        stage1.setTitle("Edytor pytań");

        stage1.setX(0);
stage1.setY(0);
        stage1.setHeight(primaryScreen.getHeight());
        stage1.setWidth(primaryScreen.getWidth());
        mainViewController.setCenterNew(pane);


    }

    public void OpenWyjscie() {

        Platform.exit();
    }




    public void openStartQuiz(ActionEvent actionEvent) {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/StudentStartTest.fxml"));
        Pane gridPane = null;
        try {
            gridPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage1 = (Stage) mainViewController.getBorderPaneMainView().getScene().getWindow();
        stage1.setMaxHeight(222222);
        stage1.setMaxWidth(222222);
        stage1.setWidth(800);
        stage1.setHeight(500);
        stage1.setTitle("Quiz - panel studenta");
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage1.setX((primScreenBounds.getWidth() - stage1.getWidth()) / 2);
        stage1.setY((primScreenBounds.getHeight() - stage1.getHeight()) / 2);

        StudentStartTestController studentStartTestController = loader.getController();
        studentStartTestController.setMainViewController(mainViewController);
        mainViewController.setCenterNew(gridPane);



    }

    public void openWyniki(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/PokazWyniki.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PokazWynikiController pokazWynikiControllerr = loader.getController();
        pokazWynikiControllerr.setMainViewController(mainViewController);

        Stage stage1 = (Stage) mainViewController.getBorderPaneMainView().getScene().getWindow();

        stage1.setMaxHeight(222222);
        stage1.setMaxWidth(222222);

        stage1.setX(0);
        stage1.setY(0);

        stage1.setTitle("Wyniki");
        stage1.setHeight(primaryScreen.getHeight());
        stage1.setWidth(primaryScreen.getWidth());
        mainViewController.setCenterNew(pane);

    }

    public void OpenPrintTest(ActionEvent actionEvent) {


        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/QuizPropertiesDrukowanie.fxml"));
        Pane gridPane = null;
        try {
            gridPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage1 = (Stage) mainViewController.getBorderPaneMainView().getScene().getWindow();

        stage1.setWidth(338);
        stage1.setHeight(400);
        stage1.setMinHeight(338);
        stage1.setMinWidth(501);
        stage1.setResizable(false);
        stage1.setTitle("Drukowanie testu");

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage1.setX((primScreenBounds.getWidth() - stage1.getWidth()) / 2);
        stage1.setY((primScreenBounds.getHeight() - stage1.getHeight()) / 2);

        QuizPropertiesDrukowanieController quizPropertiesDrukowanieController = loader.getController();
        quizPropertiesDrukowanieController.setMainViewController(mainViewController);
        mainViewController.setCenterNew(gridPane);

    }
}
