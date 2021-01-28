package Controller;

import UTILS.DialogUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


public class MainViewController {
    @FXML
    public Button ButtonStart;
    @FXML
    public Button ButtonPokażPytania;
    @FXML
    public Button ButtonWyjscie;
    @FXML
    public BorderPane BorderPaneMainView;
    @FXML
    public Button DrukuTest;
    LoginPanelController loginPanelController;

    MenuController menuController = new MenuController();

    public void initialize(){
        menuController = null;
        loginPanelController = null;
        loadMenu();


    }

    public void loadMenu() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/MenuOptions.fxml"));

        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

                                                                                                                                                //  BorderPan   e                                                                              MainView.getScene().getWindow();


                                                                                                                                                                                                               //




        MenuController menuController = loader.getController();
        menuController.setMainViewController(this);
      //  Stage stage1 = (Stage) BorderPaneMainView.getScene().getWindow();
        setCenterNew(parent);
    }

    public void setCenterNew(Parent parent) {
        BorderPaneMainView.setCenter(parent);
        BorderPane.setAlignment(parent, Pos.TOP_CENTER);


    }

    public void closeAplication(ActionEvent actionEvent) {

        Optional<ButtonType> result = DialogUtils.confirmExit();
            if(result.get()==ButtonType.OK)
            {
                Platform.exit();
                System.exit(0);
            }

    }

    public void setCaspian(ActionEvent actionEvent) {
        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
    }

    public void setModena(ActionEvent actionEvent) {
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
    }

    public void onTop(ActionEvent actionEvent) {
Stage stage = (Stage) BorderPaneMainView.getScene().getWindow();

//stage.getScene().getStylesheets().add(getClass().getResource("/aplication.css").toExternalForm());





       boolean value = ((CheckMenuItem) actionEvent.getSource()).selectedProperty().get();
        stage.setAlwaysOnTop(value);


    }




    public void about(ActionEvent actionEvent) {

        DialogUtils.dialogAboutApplication();
    }

    public BorderPane getBorderPaneMainView() {
        return BorderPaneMainView;
    }

    public void setBorderPaneMainView(BorderPane borderPaneMainView) {
        BorderPaneMainView = borderPaneMainView;
    }


    public void Miane_Sheet(ActionEvent actionEvent) {
        Stage stage = (Stage) BorderPaneMainView.getScene().getWindow();
        stage.getScene().getStylesheets().add(getClass().getResource("/aplication.css").toExternalForm());
    }

    public void LogOUT(ActionEvent actionEvent) throws IOException {

        Pane borderPane = FXMLLoader.load(this.getClass().getResource("/LoginPanel.fxml"));

        Scene scene = new Scene(borderPane);
        //   BorderPane borderPane = FXMLLoader.load(this.getClass().getResource("/LoginPanel.fxml"));
        //   Scene scene = new Scene(borderPane);
        Stage userStage = new Stage();


        // stage.setMaxHeight(400);
        // stage.setMaxWidth(460);

        userStage.setScene(scene);

        // stage.setWidth(250);
        //  stage.setHeight(300);

        userStage.setResizable(false);
        userStage.setTitle("Panel logowania");
        userStage.setFullScreen(false);

        userStage.show();

        BorderPaneMainView.getScene().getWindow().hide();


/*
        Stage userStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        BorderPane root = null;
        try {
            root = loader.load(getClass().getResource("/LoginPanel.fxm").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginPanelController  = loader.getController();
        Scene scene = new Scene(root);


        Platform.exit();
        System.exit(0);
*/
    }
}

/*
    Pane borderPane = FXMLLoader.load(this.getClass().getResource("/LoginPanel.fxml"));

    Scene scene = new Scene(borderPane);
//   BorderPane borderPane = FXMLLoader.load(this.getClass().getResource("/LoginPanel.fxml"));
//   Scene scene = new Scene(borderPane);







// stage.setMaxHeight(400);
// stage.setMaxWidth(460);

        stage.setScene(scene);

                // stage.setWidth(250);
                //  stage.setHeight(300);

                stage.setResizable(false);
                stage.setTitle("Panel logowania");
                stage.setFullScreen(false);

                stage.show();
                */