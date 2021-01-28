package Sample;

import UTILS.ApplicationException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {

    public static void main(String[] args) throws IOException, SQLException, ApplicationException {
/*
        BufferedImage bImage = ImageIO.read(new File("D:\\PRACA_MGR\\Od Moniki\\Quiz\\src\\main\\resources\\obraz.jpg"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos );
        byte [] data = bos.toByteArray();

        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        System.out.println(data);

        BufferedImage bImage2 = ImageIO.read(bis);
        ImageIO.write(bImage2, "jpg", new File("output1.jpg") );
        System.out.println("image created");


        ImageDao imageDao = new ImageDao(DBConnection.getConnectionSource());
        TableUtils.createTableIfNotExists(DBConnection.getConnectionSource(), ImageTest.class);
        ImageTest imageTest = new ImageTest();
        imageTest.setImages(data);
        imageDao.creatOrUpdate(imageTest);


        DBConnection.closeConnectionSource();
*/ //DBConnection.initDataBase();

launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {


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


    }




}
