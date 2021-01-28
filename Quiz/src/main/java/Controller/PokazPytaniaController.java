package Controller;

import Models.KategoryComboModel;
import Models.PytanieFX;
import Models.PytanieModel;
import UTILS.ApplicationException;
import UTILS.DialogUtils;
import UTILS.HttpUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import mgr.common.entities.Pytania;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PokazPytaniaController {
    @FXML
    public Button DodajPytanie;

    @FXML
    public TextArea TrescPytaniaID;

    @FXML
    public TextArea OdpowiedzA;

    @FXML
    public TextArea OdpowiedzB;

    @FXML
    public TextArea OdpowiedzC;

    @FXML
    public TextArea OdpowiedzD;

    @FXML
    public TextArea OdpowiedzE;

    @FXML
    public TextField PoprawnaODP;

    @FXML
    public ComboBox<KategoryComboModel> WyberzKategorieButton;

    @FXML
    public TableColumn<PytanieFX, String> TreśćPytaniaColumn;

    @FXML
    public TableColumn<PytanieFX, String> Odpowiedz_A_Column;

    @FXML
    public TableColumn<PytanieFX, String> Odpowiedz_B_Coulmn;

    @FXML
    public TableColumn<PytanieFX, String> Odpowiedz_C_Coulmn;

    @FXML
    public TableColumn<PytanieFX, String> Odpowiedz_D_Coulmn;

    @FXML
    public TableColumn<PytanieFX, String> Odpowiedz_E__Coulmn;

    @FXML
    public TableColumn<PytanieFX, String> Poprawna_ODP_Column;

    public TableColumn<PytanieFX, String> Kategoria_column;

    public TableColumn<PytanieFX, String> isObraz_Column;


    @FXML
    public TableView<PytanieFX> Tabela;

    @FXML
    public TextField KategoriaTextField;

    @FXML
    public Button AddCategoryButton;

    @FXML
    public Button DeleteCategoryButoon;

    @FXML
    public MenuItem deletemenuItem;

    public PytanieModel pytanieModel;

    public PytanieFX pytanieFX;

    public Button addImage;
    public ToggleGroup WybierzPoprawna_ODP;
    public RadioButton ButtonOdpA;
    public RadioButton ButtonOdpB;
    public RadioButton ButtonOdpC;
    public RadioButton ButtonOdpD;
    public RadioButton ButtonOdp_E;
    public Label IsImage;
    public ImageView ImgPodglad;


    MainViewController mainViewController;

    String eptyString = "";

    File file;

    static byte[] obraz;

    public static byte[] getObraz() {
        return obraz;
    }

    public static void setObraz(byte[] obraz) {
        PokazPytaniaController.obraz = obraz;
    }

    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    //  Stage stage1 = (Stage) mainViewController.getBorderPaneMainView().getScene().getWindow();
    //

    public void initialize() throws ApplicationException, IOException {

        mainViewController = new MainViewController();
        pytanieModel = new PytanieModel();
        pytanieFX = new PytanieFX();

        // stage1.setMaxWidth(1000);
        //   stage1.setMaxHeight(1000);
        this.TrescPytaniaID.setWrapText(true);
        this.OdpowiedzA.setWrapText(true);
        this.OdpowiedzB.setWrapText(true);
        this.OdpowiedzC.setWrapText(true);
        this.OdpowiedzD.setWrapText(true);
        this.OdpowiedzE.setWrapText(true);
        pytanieModel.init();
        this.AddCategoryButton.disableProperty().bind(this.KategoriaTextField.textProperty().isEmpty());
        this.DodajPytanie.disableProperty().bind(this.TrescPytaniaID.textProperty().isEmpty());

        this.WyberzKategorieButton.getItems().addAll(pytanieModel.getKategorie_FXObservableList());
        InitTable();

        this.WyberzKategorieButton.getSelectionModel().selectFirst();

        this.Tabela.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.pytanieModel.setPytanieFXObjectPropertyEdit(newValue);

        });

        this.Tabela.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.pytanieModel.setTABLEEdit(newValue);

        });

        try {
            RefreshTableWithCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void InitTable() {
        this.Tabela.setEditable(true);
        this.Tabela.setItems(this.pytanieModel.getPytanieFXObservableList());
        getItemsToColumn();

    }

    public void onDodajPytanie(ActionEvent actionEvent) throws Exception {


        this.pytanieFX.pytanie_FXProperty().bind(TrescPytaniaID.textProperty());
        this.pytanieFX.odp_A_FXProperty().bind(OdpowiedzA.textProperty());
        this.pytanieFX.odp_B_FXProperty().bind(OdpowiedzB.textProperty());
        this.pytanieFX.odp_C_FXProperty().bind(OdpowiedzC.textProperty());
        //  this.pytanieFX.popr_FXProperty().bind(PoprawnaODP.textProperty());
        this.pytanieFX.odp_D_FXProperty().bind(OdpowiedzD.textProperty());
        this.pytanieFX.odp_E_FXProperty().bind(OdpowiedzE.textProperty());
        this.pytanieFX.kategoria_FXProperty().bind(WyberzKategorieButton.getSelectionModel().getSelectedItem().kategoryNameProperty());

        if (this.ButtonOdpA.isSelected()) {
            this.pytanieFX.popr_FXProperty().bind(OdpowiedzA.textProperty());
        }
        if (this.ButtonOdpB.isSelected()) {
            this.pytanieFX.popr_FXProperty().bind(OdpowiedzB.textProperty());
        }
        if (this.ButtonOdpC.isSelected()) {
            this.pytanieFX.popr_FXProperty().bind(OdpowiedzC.textProperty());
        }
        if (this.ButtonOdpD.isSelected()) {
            this.pytanieFX.popr_FXProperty().bind(OdpowiedzD.textProperty());
        }
        if (this.ButtonOdp_E.isSelected()) {
            this.pytanieFX.popr_FXProperty().bind(OdpowiedzE.textProperty());
        }

        Pytania pytanie = new Pytania();

        pytanie.setPytanie(this.pytanieFX.getPytanie_FX());
        pytanie.setOdpA(this.pytanieFX.getOdp_A_FX());
        pytanie.setOdpB(this.pytanieFX.getOdp_B_FX());
        pytanie.setOdpC(this.pytanieFX.getOdp_C_FX());
        pytanie.setOdpPopr(this.pytanieFX.getPopr_FX());
        pytanie.setOdpD(this.pytanieFX.getOdp_D_FX());
        pytanie.setOdpE(this.pytanieFX.getOdp_E_FX());
        pytanie.setKategoria(this.pytanieFX.getKategoria_FX());
        if (getObraz() != null) {

            String s = Base64.getEncoder().encodeToString(getObraz());
            pytanie.setImages(s);

        } else {


            pytanie.setImages(eptyString);

        }




        HttpUtils.createQuestion(pytanie);

        this.pytanieModel.getPytanieFXObservableList().clear();
        this.pytanieModel.init();
        RefreshTableWithCategory();


        this.TrescPytaniaID.clear();
        this.OdpowiedzA.clear();
        this.OdpowiedzB.clear();
        this.OdpowiedzC.clear();
        this.OdpowiedzD.clear();
        this.OdpowiedzE.clear();
        //   this.PoprawnaODP.clear();


        deleteObrazFunction();

    }

    public void onMainView(ActionEvent actionEvent) {

        Stage stage = (Stage) mainViewController.BorderPaneMainView.getScene().getWindow();
        stage.setWidth(459);
        stage.setHeight(317);

        stage.setResizable(false);

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

        stage.setTitle("Okno główne");
        mainViewController.loadMenu();
    }

    public void WybranyTest(ActionEvent actionEvent) throws Exception {
        //  pytanieModel.init();  /// <----------------- NULL POINTER

        this.ImgPodglad.setImage(null);
        RefreshTableWithCategory();

    }

    private void RefreshTableWithCategory() throws Exception {

        pytanieModel.RefreshTable();

       /* System.out.println(WyberzKategorieButton.getSelectionModel()
                .getSelectedItem()
                .getKategoryName());
*/
        List<PytanieFX> WyfiltrowanaLista = firtSelectedCombo();


        this.pytanieModel.getPytanieFXObservableList().clear();
        WyfiltrowanaLista.forEach(c -> {
            PytanieFX pytanieFX = Converter_FX(c);

            this.pytanieModel.getPytanieFXObservableList().add(pytanieFX);
        });

        ObservableList<PytanieFX> pytanieFXObservableList2 = this.pytanieModel.getPytanieFXObservableList();

        this.Tabela.setItems(pytanieFXObservableList2);
        getItemsToColumn();
    }


    private void getItemsToColumn() {


        this.TreśćPytaniaColumn.setCellValueFactory(cellData -> cellData.getValue().pytanie_FXProperty());
        this.Odpowiedz_A_Column.setCellValueFactory(cellData -> cellData.getValue().odp_A_FXProperty());
        this.Odpowiedz_B_Coulmn.setCellValueFactory(cellData -> cellData.getValue().odp_B_FXProperty());
        this.Odpowiedz_C_Coulmn.setCellValueFactory(cellData -> cellData.getValue().odp_C_FXProperty());
        this.Odpowiedz_D_Coulmn.setCellValueFactory(cellData -> cellData.getValue().odp_D_FXProperty());
        this.Odpowiedz_E__Coulmn.setCellValueFactory(cellData -> cellData.getValue().odp_E_FXProperty());

        this.Poprawna_ODP_Column.setCellValueFactory(cellData -> cellData.getValue().popr_FXProperty());
        this.Kategoria_column.setCellValueFactory(cellData -> cellData.getValue().kategoria_FXProperty());
        this.isObraz_Column.setCellValueFactory(cellData -> cellData.getValue().obraz_FxProperty());


        this.TreśćPytaniaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.Odpowiedz_A_Column.setCellFactory(TextFieldTableCell.forTableColumn());
        this.Odpowiedz_B_Coulmn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.Odpowiedz_C_Coulmn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.Odpowiedz_D_Coulmn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.Odpowiedz_E__Coulmn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.Poprawna_ODP_Column.setCellFactory(TextFieldTableCell.forTableColumn());
        this.Kategoria_column.setCellFactory(TextFieldTableCell.forTableColumn());
        this.isObraz_Column.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private PytanieFX Converter_FX(PytanieFX c) {
        PytanieFX pytanieFX = new PytanieFX();
        pytanieFX.setId_FX(c.getId_FX());
        pytanieFX.setPytanie_FX(c.getPytanie_FX());
        pytanieFX.setOdp_A_FX(c.getOdp_A_FX());
        pytanieFX.setOdp_B_FX(c.getOdp_B_FX());
        pytanieFX.setOdp_C_FX(c.getOdp_C_FX());
        pytanieFX.setOdp_D_FX(c.getOdp_D_FX());
        pytanieFX.setOdp_E_FX(c.getOdp_E_FX());
        pytanieFX.setPopr_FX(c.getPopr_FX());
        pytanieFX.setKategoria_FX(c.getKategoria_FX());
        pytanieFX.setObraz_Fx(c.getObraz_Fx());
        return pytanieFX;
    }

    public void deletePytanie(ActionEvent actionEvent) throws Exception {

        pytanieModel.deletePytanieInDataBase();
        RefreshTableWithCategory();

    }

    public void onAddCategorry(ActionEvent actionEvent) {

        KategoryComboModel kategoryComboModel = new KategoryComboModel();

        kategoryComboModel.setKategoryName(this.KategoriaTextField.textProperty().getValue());

        this.pytanieModel.getKategorie_FXObservableList().add(kategoryComboModel);

        this.WyberzKategorieButton.getItems().clear();
        this.WyberzKategorieButton.getItems().addAll(pytanieModel.getKategorie_FXObservableList());

        this.WyberzKategorieButton.getSelectionModel().selectFirst();

        this.KategoriaTextField.clear();

    }

    public void onDeleteCategoryButton(ActionEvent actionEvent) throws ApplicationException {

        Optional<ButtonType> result = DialogUtils.deleteCategory();
        if (result.get() == ButtonType.OK) {
            List<PytanieFX> WyfiltrowanaLista = firtSelectedCombo();
            WyfiltrowanaLista.forEach(e -> {
                int id = e.getId_FX();
                HttpUtils.deleteQuestion(id);
            });
        }

        try {
            this.WyberzKategorieButton.getItems().clear();
            this.pytanieModel.init();
            this.WyberzKategorieButton.getItems().addAll(pytanieModel.getKategorie_FXObservableList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.WyberzKategorieButton.getSelectionModel().selectFirst();
    }

    private List<PytanieFX> firtSelectedCombo() {
        String s = WyberzKategorieButton.getSelectionModel()
                .getSelectedItem()
                .getKategoryName();

        Predicate<PytanieFX> mNazwa = pytanieFX1 -> pytanieFX1.getKategoria_FX().equals(s);

        return this.pytanieModel.getPytanieFXObservableList().stream().filter(mNazwa).collect(Collectors.toList());
    }

    public void OnAddImage(ActionEvent actionEvent) throws IOException {

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

            this.IsImage.setText(file.getName());

            BufferedImage bImage = ImageIO.read(new File((file.getAbsolutePath())));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", bos);
            byte[] data = bos.toByteArray();
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            BufferedImage bImage2 = ImageIO.read(bis);
            ImageIO.write(bImage2, "png", new File("outputXX.png"));


            InputStream bisI = new ByteArrayInputStream(data);

            this.ImgPodglad.setImage(new Image(bisI));
            setObraz(data);


        }
        // ByteArrayInputStream bis = new ByteArrayInputStream(data);





        /*
        BufferedImage bImage2 = ImageIO.read(bis);
        ImageIO.write(bImage2, "png", new File("output1.jpg") );
        System.out.println("image created");


        ImageDao imageDao = new ImageDao(DBConnection.getConnectionSource());
        TableUtils.createTableIfNotExists(DBConnection.getConnectionSource(), ImageTest.class);
        ImageTest imageTest = new ImageTest();
        imageTest.setImages(data);
        imageDao.creatOrUpdate(imageTest);


        DBConnection.closeConnectionSource();
*/

    }

    public void EdycjaPytania(TableColumn.CellEditEvent<PytanieFX, String> pytanieFXStringCellEditEvent) {
        this.pytanieModel.getTABLEEdit().setPytanie_FX(pytanieFXStringCellEditEvent.getNewValue());


        try {
            pytanieModel.updateQuestions();
            RefreshTableWithCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Edycja_ODPA(TableColumn.CellEditEvent<PytanieFX, String> pytanieFXStringCellEditEvent) {
        this.pytanieModel.getTABLEEdit().setOdp_A_FX(pytanieFXStringCellEditEvent.getNewValue());

        try {
            pytanieModel.updateQuestions();
            RefreshTableWithCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Edycja_ODPB(TableColumn.CellEditEvent<PytanieFX, String> pytanieFXStringCellEditEvent) {
        this.pytanieModel.getTABLEEdit().setOdp_B_FX(pytanieFXStringCellEditEvent.getNewValue());

        try {
            pytanieModel.updateQuestions();
            RefreshTableWithCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Edycja_ODPC(TableColumn.CellEditEvent<PytanieFX, String> pytanieFXStringCellEditEvent) {
        this.pytanieModel.getTABLEEdit().setOdp_C_FX(pytanieFXStringCellEditEvent.getNewValue());

        try {
            pytanieModel.updateQuestions();
            RefreshTableWithCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Edycja_ODPD(TableColumn.CellEditEvent<PytanieFX, String> pytanieFXStringCellEditEvent) {
        this.pytanieModel.getTABLEEdit().setOdp_D_FX(pytanieFXStringCellEditEvent.getNewValue());

        try {
            pytanieModel.updateQuestions();
            RefreshTableWithCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Edycja_ODPE(TableColumn.CellEditEvent<PytanieFX, String> pytanieFXStringCellEditEvent) {
        this.pytanieModel.getTABLEEdit().setOdp_E_FX(pytanieFXStringCellEditEvent.getNewValue());

        try {
            pytanieModel.updateQuestions();
            RefreshTableWithCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Edycja_Poprawna(TableColumn.CellEditEvent<PytanieFX, String> pytanieFXStringCellEditEvent) {
        this.pytanieModel.getTABLEEdit().setPopr_FX(pytanieFXStringCellEditEvent.getNewValue());

        try {
            pytanieModel.updateQuestions();
            RefreshTableWithCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void Edycja_Kategoria(TableColumn.CellEditEvent<PytanieFX, String> pytanieFXStringCellEditEvent) {
        this.pytanieModel.getTABLEEdit().setPopr_FX(pytanieFXStringCellEditEvent.getNewValue());

        try {
            pytanieModel.updateQuestions();
            RefreshTableWithCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void deleteObrazFunction() {
        file = null;

        this.ImgPodglad.setImage(null);

        this.IsImage.setText("Brak Obrazu");
        setObraz(null);
    }

    public void deleteObraz(ActionEvent actionEvent) {

        deleteObrazFunction();
    }

    public void ChangeImage(MouseEvent mouseEvent) {

        this.ImgPodglad.setImage(null);

        if (!this.pytanieModel.getTABLEEdit().getObraz_Fx().equals("")) {
            byte[] image = Base64.getDecoder().decode(this.pytanieModel.getTABLEEdit().getObraz_Fx());

            if (image != null) {
                //
                //             String s = Base64.getEncoder().encodeToString(getObraz());

                InputStream bis = new ByteArrayInputStream(image);
/*
                BufferedImage bImage2 = ImageIO.read(bis);
                ImageIO.write(bImage2, "png", new File("Atak.png") );
                System.out.println("image created");
*/
                //  List<Pytania> listaPytań = HttpUtils.getAllQuestions();

                // ByteArrayInputStream bis1 = new ByteArrayInputStream(listaPytań.get(count).getImages());

                //  InputStream inputStream = getClass().getResourceAsStream("/Quiz/Mały.png");

                File f = new File("D:\\PRACA_MGR\\mgr-reczu\\outpu52.png");
                // Image img = new Image(f.toURI().toString());

                String path = "outpu55.jpg";
                //  Image img = new Image((InputStream)bImage2, 200, 200, false, true);
                // Image img = new Image(f.toURI().toString());
                this.ImgPodglad.setImage(new Image(bis));
                this.ImgPodglad.setVisible(true);
            }


        }


    }


}
