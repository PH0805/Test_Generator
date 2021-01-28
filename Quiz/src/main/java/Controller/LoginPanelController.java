package Controller;

import UTILS.DialogUtils;
import UTILS.HttpUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class LoginPanelController {
    public TextField LoginTextView;

    public RadioButton AdminRadioButton;
    public RadioButton StudentRadioButton;

    public MainViewController mainViewController;

    public ImageView imageVieW;
    private static boolean isStudent = false;
    String password = "ala";

    public void initialize() {

        isStudent = false;
        this.LoginTextView.disableProperty().bind(this.StudentRadioButton.selectedProperty());
    }

    public void goToMainView(KeyEvent actionEvent) throws IOException {


        if (actionEvent.getCode() == KeyCode.ENTER) {
            LouchApilcation();
        }




    }
   // private static boolean isStudent = false;
    private void LouchApilcation() throws IOException {
        if (this.StudentRadioButton.selectedProperty().getValue()) {
            isStudent = true;
            OpenProgram();
            return;
        }


        password = this.LoginTextView.textProperty().getValue();
        HttpUtils.setAdminPassword(password);
        if (HttpUtils.isAdminPasswordValid()) {
            OpenProgram();
        } else {
            DialogUtils.errrorAlert_Passowrd_Haslo();
        }
    }

    private void OpenProgram() throws IOException {
        Stage userStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        BorderPane root = loader.load(getClass().getResource("/MainView.fxml").openStream());
        MainViewController mainViewController = loader.getController();
        Scene scene = new Scene(root);
        userStage.setScene(scene);
        userStage.setTitle("Okno główne");
        userStage.setHeight(317);
        userStage.setWidth(459);
        userStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                Optional<ButtonType> result = DialogUtils.confirmExit();
                if (result.get() == ButtonType.OK) {
                    Platform.exit();
                    System.exit(0);
                }


                event.consume();
            }
        });

        userStage.setResizable(false);
        userStage.show();
        LoginTextView.getScene().getWindow().hide();
    }

    public static boolean isIsStudent() {
        return isStudent;
    }

    public static void setIsStudent(boolean isStudent) {
        LoginPanelController.isStudent = isStudent;
    }

    public void Obraz(ActionEvent actionEvent) {

        File file;
        Image image;
        Desktop desktop = Desktop.getDesktop();
        Stage userStage1 = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(

        );
        file = fileChooser.showOpenDialog(userStage1);
        if (file != null) {

            image = new Image(file.toURI().toString(), 500, 290, false, false);
            imageVieW.setImage(image);
            image.getHeight();


        }

    }

    public void goToMainViewMouse(ActionEvent actionEvent) {
        try {
            LouchApilcation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
