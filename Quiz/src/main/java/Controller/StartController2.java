package Controller;

import Models.GoodQuestionList;
import Models.PytanieFX;
import Models.PytanieModel;
import UTILS.ApplicationException;
import UTILS.HttpUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import mgr.common.entities.TestFilter;
import mgr.common.entities.Wynik;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StartController2 {
    @FXML
    public Label IdQuestion;

    @FXML
    public RadioButton OdpB;

    @FXML
    public
    RadioButton OdpA;
    @FXML
    public Label Pytanie;

    @FXML
    public RadioButton OdpC;

    @FXML
    public Label Wynik;

    @FXML
    public Button Powrót, NastepnePytanieButton;

    @FXML
    public Label PytanieLabel;

    @FXML
    public Button backButton;

    @FXML
    public RadioButton OdpD;

    @FXML
    public RadioButton OdpE;

    public ToggleGroup OdpGRP;

    public ImageView ImageID;

    private PytanieModel pytanieModel;

    private String epmptyString = "";

    private MainViewController mainViewController = new MainViewController();

    public StartController2() {
    }

    public MainViewController getMainViewController() {
        return mainViewController;
    }

    private static int count = 0;

    Thread t;

    private ArrayList<GoodQuestionList> goodQuestionLists = new ArrayList<>();

    ///////// wybranie testu po Passwordzie

    Predicate<TestFilter> filtrowaniePoHasile = testFilterPass -> testFilterPass.getPassword().equals(StudentStartTestController.getPassword());
    List<TestFilter> testFilters = HttpUtils.getAllTestFilters().stream().filter(filtrowaniePoHasile).collect(Collectors.toList());

    //// /////////

    private int LiczbaPytan = testFilters.get(0).getIloscPytan();


    private boolean Random_Answer = testFilters.get(0).getRandom_Anwser();

    private int StartQuestionId = Integer.parseInt(testFilters.get(0).getStartQuestionID());


    private boolean Random_Question = testFilters.get(0).getRandom_Question();

    private boolean NotShowAwser = testFilters.get(0).getNotShowAnwser();

    private int TimeOfQestion = testFilters.get(0).getTime_of_Question();

    private boolean isTime = false;

    private String TestName = testFilters.get(0).getNameTest();


    public boolean isTime() {
        return isTime;
    }

    public void setTime(boolean time) {
        isTime = time;
    }

    public int getTimeOfQestion() {
        return TimeOfQestion;
    }

    public void setTimeOfQestion(int timeOfQestion) {
        TimeOfQestion = timeOfQestion;
    }


    //    int zacznijPytanie = Integer.parseInt(testFilters.get(0).getStartQuestionID());

    public void initialize() throws ApplicationException, IOException {

        if(StartQuestionId != 1){
            StartQuestionId = StartQuestionId -1;
        }


        this.Pytanie.setWrapText(true);

        this.OdpA.setWrapText(true);
        this.OdpB.setWrapText(true);
        this.OdpC.setWrapText(true);
        this.OdpD.setWrapText(true);
        this.OdpE.setWrapText(true);

        /*
        this.OdpA.setTextAlignment(TextAlignment.JUSTIFY);
        this.OdpB.setTextAlignment(TextAlignment.JUSTIFY);
        this.OdpC.setTextAlignment(TextAlignment.JUSTIFY);
        this.OdpD.setTextAlignment(TextAlignment.JUSTIFY);
        this.OdpE.setTextAlignment(TextAlignment.JUSTIFY);

         */

        pytanieModel = new PytanieModel();

        if (getTimeOfQestion() != 99999999) {
            setTime(true);
            startTimer();
        }
        try {
            pytanieModel.init();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

        this.NastepnePytanieButton.setVisible(true);
        ///////////////////////  Filtrowanie po Kategorii

        Predicate<PytanieFX> mNazwa = pytanieFX1 -> pytanieFX1.getKategoria_FX().equals(testFilters.get(0).getKategoria());
        List<PytanieFX> wyfiltrowanalista = this.pytanieModel.getPytanieFXObservableList().stream().filter(mNazwa).collect(Collectors.toList());

        /////// Tworzenie Tablcy Wyników testFilters.get(2).getIloscPytan()


/*

for (int i = StartQuestionId - 1; i < LiczbaPytan; i++) {  // todo
            goodQuestionLists.add(new GoodQuestionList());
        }
          for (int i =0 ; i < StartQuestionId -1; i++) {  // todo
            wyfiltrowanalista.remove(i);
        }


 */


        /// Sumaryczna liczb apytan - ktyre nie chcemy = tyle zostaje
        for (int i = 0; i < LiczbaPytan; i++) {  // todo
            goodQuestionLists.add(new GoodQuestionList());
        }

        this.pytanieModel.getPytanieFXObservableList().clear();


        wyfiltrowanalista.forEach(c -> {

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

            this.pytanieModel.getPytanieFXObservableList().add(pytanieFX);

        });

        for (int i =0 ; i < StartQuestionId -1; i++) {  // todo
            this.pytanieModel.getPytanieFXObservableList().remove(i);
            System.out.println(this.pytanieModel.getPytanieFXObservableList().size());
        }

        System.out.println(this.pytanieModel.getPytanieFXObservableList().size());
        for (int i =0 ; i < 3 ; i++) {  // todo
          //  this.pytanieModel.getPytanieFXObservableList().remove(i);
            System.out.println( this.pytanieModel.getPytanieFXObservableList().get(i).getPytanie_FX());
    }


        if (Random_Question == true) {
          //  this.pytanieModel.getPytanieFXObservableList().remove(0, StartQuestionId);
            Collections.shuffle(this.pytanieModel.getPytanieFXObservableList());

        }

        ///////////////// Randomowe          Odpowiedzi

        int losweOpowedzi;
        if (Random_Answer == true) {

            double randomDouble = Math.random();
            randomDouble = randomDouble * 7 + 1;
            losweOpowedzi = ((int) randomDouble);

            if (losweOpowedzi == 1) {
                this.pytanieModel.getPytanieFXObservableList().clear();

                wyfiltrowanalista.forEach(c -> {

                    PytanieFX pytanieFX = new PytanieFX();
                    pytanieFX.setId_FX(c.getId_FX());
                    pytanieFX.setPytanie_FX(c.getPytanie_FX());
                    pytanieFX.setOdp_A_FX(c.getOdp_E_FX());
                    pytanieFX.setOdp_B_FX(c.getOdp_A_FX());
                    pytanieFX.setOdp_C_FX(c.getOdp_B_FX());
                    pytanieFX.setOdp_D_FX(c.getOdp_C_FX());
                    pytanieFX.setOdp_E_FX(c.getOdp_D_FX());
                    pytanieFX.setPopr_FX(c.getPopr_FX());
                    pytanieFX.setKategoria_FX(c.getKategoria_FX());
                    pytanieFX.setObraz_Fx(c.getObraz_Fx());
                    this.pytanieModel.getPytanieFXObservableList().add(pytanieFX);

                });

            }

            if (losweOpowedzi == 2) {

                this.pytanieModel.getPytanieFXObservableList().clear();
                wyfiltrowanalista.forEach(c -> {

                    PytanieFX pytanieFX = new PytanieFX();
                    pytanieFX.setId_FX(c.getId_FX());
                    pytanieFX.setPytanie_FX(c.getPytanie_FX());
                    pytanieFX.setOdp_A_FX(c.getOdp_B_FX());
                    pytanieFX.setOdp_B_FX(c.getOdp_E_FX());
                    pytanieFX.setOdp_C_FX(c.getOdp_A_FX());
                    pytanieFX.setOdp_D_FX(c.getOdp_D_FX());
                    pytanieFX.setOdp_E_FX(c.getOdp_C_FX());
                    pytanieFX.setPopr_FX(c.getPopr_FX());
                    pytanieFX.setKategoria_FX(c.getKategoria_FX());
                    pytanieFX.setObraz_Fx(c.getObraz_Fx());
                    this.pytanieModel.getPytanieFXObservableList().add(pytanieFX);

                });

            }

            if (losweOpowedzi == 3) {

                this.pytanieModel.getPytanieFXObservableList().clear();
                wyfiltrowanalista.forEach(c -> {

                    PytanieFX pytanieFX = new PytanieFX();
                    pytanieFX.setId_FX(c.getId_FX());
                    pytanieFX.setPytanie_FX(c.getPytanie_FX());
                    pytanieFX.setOdp_A_FX(c.getOdp_E_FX());
                    pytanieFX.setOdp_B_FX(c.getOdp_A_FX());
                    pytanieFX.setOdp_C_FX(c.getOdp_B_FX());
                    pytanieFX.setOdp_D_FX(c.getOdp_C_FX());
                    pytanieFX.setOdp_E_FX(c.getOdp_D_FX());
                    pytanieFX.setPopr_FX(c.getPopr_FX());
                    pytanieFX.setKategoria_FX(c.getKategoria_FX());
                    pytanieFX.setObraz_Fx(c.getObraz_Fx());
                    this.pytanieModel.getPytanieFXObservableList().add(pytanieFX);

                });

            }


            if (losweOpowedzi == 4) {
                this.pytanieModel.getPytanieFXObservableList().clear();

                wyfiltrowanalista.forEach(c -> {

                    PytanieFX pytanieFX = new PytanieFX();
                    pytanieFX.setId_FX(c.getId_FX());
                    pytanieFX.setPytanie_FX(c.getPytanie_FX());
                    pytanieFX.setOdp_A_FX(c.getOdp_B_FX());
                    pytanieFX.setOdp_B_FX(c.getOdp_C_FX());
                    pytanieFX.setOdp_C_FX(c.getOdp_E_FX());
                    pytanieFX.setOdp_D_FX(c.getOdp_A_FX());
                    pytanieFX.setOdp_E_FX(c.getOdp_D_FX());
                    pytanieFX.setPopr_FX(c.getPopr_FX());
                    pytanieFX.setKategoria_FX(c.getKategoria_FX());
                    pytanieFX.setObraz_Fx(c.getObraz_Fx());
                    this.pytanieModel.getPytanieFXObservableList().add(pytanieFX);

                });

            }

            if (losweOpowedzi == 5) {
                this.pytanieModel.getPytanieFXObservableList().clear();

                wyfiltrowanalista.forEach(c -> {

                    PytanieFX pytanieFX = new PytanieFX();
                    pytanieFX.setId_FX(c.getId_FX());
                    pytanieFX.setPytanie_FX(c.getPytanie_FX());
                    pytanieFX.setOdp_A_FX(c.getOdp_D_FX());
                    pytanieFX.setOdp_B_FX(c.getOdp_E_FX());
                    pytanieFX.setOdp_C_FX(c.getOdp_C_FX());
                    pytanieFX.setOdp_D_FX(c.getOdp_A_FX());
                    pytanieFX.setOdp_E_FX(c.getOdp_B_FX());
                    pytanieFX.setPopr_FX(c.getPopr_FX());
                    pytanieFX.setKategoria_FX(c.getKategoria_FX());
                    pytanieFX.setObraz_Fx(c.getObraz_Fx());
                    this.pytanieModel.getPytanieFXObservableList().add(pytanieFX);

                });

            }

            if (losweOpowedzi == 6) {
                this.pytanieModel.getPytanieFXObservableList().clear();

                wyfiltrowanalista.forEach(c -> {

                    PytanieFX pytanieFX = new PytanieFX();
                    pytanieFX.setId_FX(c.getId_FX());
                    pytanieFX.setPytanie_FX(c.getPytanie_FX());
                    pytanieFX.setOdp_A_FX(c.getOdp_C_FX());
                    pytanieFX.setOdp_B_FX(c.getOdp_A_FX());
                    pytanieFX.setOdp_C_FX(c.getOdp_D_FX());
                    pytanieFX.setOdp_D_FX(c.getOdp_E_FX());
                    pytanieFX.setOdp_E_FX(c.getOdp_B_FX());
                    pytanieFX.setPopr_FX(c.getPopr_FX());
                    pytanieFX.setKategoria_FX(c.getKategoria_FX());
                    pytanieFX.setObraz_Fx(c.getObraz_Fx());
                    this.pytanieModel.getPytanieFXObservableList().add(pytanieFX);

                });

            }

            if (losweOpowedzi == 7) {
                this.pytanieModel.getPytanieFXObservableList().clear();

                wyfiltrowanalista.forEach(c -> {

                    PytanieFX pytanieFX = new PytanieFX();
                    pytanieFX.setId_FX(c.getId_FX());
                    pytanieFX.setPytanie_FX(c.getPytanie_FX());
                    pytanieFX.setOdp_A_FX(c.getOdp_B_FX());
                    pytanieFX.setOdp_B_FX(c.getOdp_C_FX());
                    pytanieFX.setOdp_C_FX(c.getOdp_A_FX());
                    pytanieFX.setOdp_D_FX(c.getOdp_E_FX());
                    pytanieFX.setOdp_E_FX(c.getOdp_D_FX());
                    pytanieFX.setPopr_FX(c.getPopr_FX());
                    pytanieFX.setKategoria_FX(c.getKategoria_FX());
                    pytanieFX.setObraz_Fx(c.getObraz_Fx());
                    this.pytanieModel.getPytanieFXObservableList().add(pytanieFX);

                });

            }







        }

        ///////////////////////////////////////////////////  LosowePytania

        if (Random_Question == true) {

            Collections.shuffle(this.pytanieModel.getPytanieFXObservableList());

        }

        ShowQuestions();

        if (NotShowAwser) {
            setInVisibleButton();
        } else {
            ifAnwserIsEmpty();
        }

        backButton.setVisible(false);

    }

    private void ShowQuestions() throws IOException, ApplicationException {
        ToTheFirst();

        if((LiczbaPytan - StartQuestionId+1)==count){
            this.NastepnePytanieButton.setText("Zakończ Test");
        }
        else
        {
            this.NastepnePytanieButton.setText("Dalej");
        }

        String IlosćPytan = Integer.toString(goodQuestionLists.size()-StartQuestionId+1);
         String NumberofQuestin = Integer.toString(count+1);

        this.Pytanie.setText(pytanieModel.getPytanieFXObservableList().get(count).getPytanie_FX());
        this.IdQuestion.setText(NumberofQuestin+"/"+IlosćPytan);
        this.OdpA.setText(pytanieModel.getPytanieFXObservableList().get(count).getOdp_A_FX());
        this.OdpB.setText(pytanieModel.getPytanieFXObservableList().get(count).getOdp_B_FX());
        this.OdpC.setText(pytanieModel.getPytanieFXObservableList().get(count).getOdp_C_FX());
        this.OdpD.setText(pytanieModel.getPytanieFXObservableList().get(count).getOdp_D_FX());
        this.OdpE.setText(pytanieModel.getPytanieFXObservableList().get(count).getOdp_E_FX());


        if (!this.pytanieModel.getPytanieFXObservableList().get(count).getObraz_Fx().equals(epmptyString)) {
            byte[] image = Base64.getDecoder().decode(this.pytanieModel.getPytanieFXObservableList().get(count).getObraz_Fx());
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
                this.ImageID.setImage(new Image(bis));
                this.ImageID.setVisible(true);
            }

        }

    }

    private void ToTheFirst() {

        if (pytanieModel.getPytanieFXObservableList().get(count).getOdp_A_FX().isEmpty() && !pytanieModel.getPytanieFXObservableList().get(count).getOdp_E_FX().isEmpty()) {
            pytanieModel.getPytanieFXObservableList().get(count).setOdp_A_FX(pytanieModel.getPytanieFXObservableList().get(count).getOdp_E_FX());
            pytanieModel.getPytanieFXObservableList().get(count).setOdp_E_FX("");
        }

        if (pytanieModel.getPytanieFXObservableList().get(count).getOdp_A_FX().isEmpty() && !pytanieModel.getPytanieFXObservableList().get(count).getOdp_D_FX().isEmpty()) {
            pytanieModel.getPytanieFXObservableList().get(count).setOdp_A_FX(pytanieModel.getPytanieFXObservableList().get(count).getOdp_D_FX());
            pytanieModel.getPytanieFXObservableList().get(count).setOdp_D_FX("");

        }

        if (pytanieModel.getPytanieFXObservableList().get(count).getOdp_A_FX().isEmpty() && !pytanieModel.getPytanieFXObservableList().get(count).getOdp_C_FX().isEmpty()) {
            pytanieModel.getPytanieFXObservableList().get(count).setOdp_A_FX(pytanieModel.getPytanieFXObservableList().get(count).getOdp_C_FX());
            pytanieModel.getPytanieFXObservableList().get(count).setOdp_C_FX("");
        }

        if (

                pytanieModel.getPytanieFXObservableList().get(count).getOdp_A_FX().isEmpty() && !pytanieModel.getPytanieFXObservableList()
                        .get(count)
                        .getOdp_B_FX()
                        .isEmpty()) {
            pytanieModel.getPytanieFXObservableList().get(count).setOdp_A_FX(pytanieModel.getPytanieFXObservableList().get(count).getOdp_B_FX());
            pytanieModel.getPytanieFXObservableList().get(count).setOdp_B_FX("");

        }

        ///////////////

        if (pytanieModel.getPytanieFXObservableList().get(count).getOdp_B_FX().isEmpty() && !pytanieModel.getPytanieFXObservableList().get(count).getOdp_E_FX().isEmpty()) {
            pytanieModel.getPytanieFXObservableList().get(count).setOdp_B_FX(pytanieModel.getPytanieFXObservableList().get(count).getOdp_E_FX());
            pytanieModel.getPytanieFXObservableList().get(count).setOdp_E_FX("");
        }

        if (pytanieModel.getPytanieFXObservableList().get(count).getOdp_B_FX().isEmpty() && !pytanieModel.getPytanieFXObservableList().get(count).getOdp_D_FX().isEmpty()) {
            pytanieModel.getPytanieFXObservableList().get(count).setOdp_B_FX(pytanieModel.getPytanieFXObservableList().get(count).getOdp_D_FX());
            pytanieModel.getPytanieFXObservableList().get(count).setOdp_D_FX("");

        }

        if (pytanieModel.getPytanieFXObservableList().get(count).getOdp_B_FX().isEmpty() && !pytanieModel.getPytanieFXObservableList().get(count).getOdp_C_FX().isEmpty()) {
            pytanieModel.getPytanieFXObservableList().get(count).setOdp_B_FX(pytanieModel.getPytanieFXObservableList().get(count).getOdp_C_FX());
            pytanieModel.getPytanieFXObservableList().get(count).setOdp_C_FX("");
        }

        if (pytanieModel.getPytanieFXObservableList().get(count).getOdp_C_FX().isEmpty() && !pytanieModel.getPytanieFXObservableList().get(count).getOdp_E_FX().isEmpty()) {
            pytanieModel.getPytanieFXObservableList().get(count).setOdp_C_FX(pytanieModel.getPytanieFXObservableList().get(count).getOdp_E_FX());
            pytanieModel.getPytanieFXObservableList().get(count).setOdp_E_FX("");
        }

        if (pytanieModel.getPytanieFXObservableList().get(count).getOdp_C_FX().isEmpty() && !pytanieModel.getPytanieFXObservableList().get(count).getOdp_D_FX().isEmpty()) {
            pytanieModel.getPytanieFXObservableList().get(count).setOdp_C_FX(pytanieModel.getPytanieFXObservableList().get(count).getOdp_D_FX());
            pytanieModel.getPytanieFXObservableList().get(count).setOdp_D_FX("");

        }

        if (pytanieModel.getPytanieFXObservableList().get(count).getOdp_D_FX().isEmpty() && !pytanieModel.getPytanieFXObservableList().get(count).getOdp_E_FX().isEmpty()) {
            pytanieModel.getPytanieFXObservableList().get(count).setOdp_D_FX(pytanieModel.getPytanieFXObservableList().get(count).getOdp_E_FX());
            pytanieModel.getPytanieFXObservableList().get(count).setOdp_E_FX("");
        }

    }

    public void NextQuestion() throws InterruptedException, IOException, ApplicationException {
        this.ImageID.setImage(null);
        if (!isTime) {

            if (count == 0) {
                backButton.setVisible(false);
            } else {
                backButton.setVisible(true);
            }
        }


        selectedFalseButton();

        ShowQuestions();

        if (NotShowAwser) {

            setInVisibleButton();
        } else {
            ifAnwserIsEmpty();
        }

    }

    private void ifAnwserIsEmpty() {
        if (OdpC.getText().equals(epmptyString))
            OdpC.setVisible(false);
        else {
            OdpC.setVisible(true);
        }
        if (OdpA.getText().equals(epmptyString)) {
            OdpA.setVisible(false);
        } else {
            OdpA.setVisible(true);
        }
        if (OdpB.getText().equals(epmptyString)) {
            OdpB.setVisible(false);
        } else {
            OdpB.setVisible(true);
        }
        if (OdpD.getText().equals(epmptyString)) {
            OdpD.setVisible(false);
        } else {
            OdpD.setVisible(true);
        }
        if (OdpE.getText().equals(epmptyString)) {
            OdpE.setVisible(false);
        } else {
            OdpE.setVisible(true);
        }

    }

    private void selectedFalseButton() {
        OdpA.setSelected(false);
        OdpB.setSelected(false);
        OdpC.setSelected(false);
        OdpD.setSelected(false);
        OdpE.setSelected(false);
    }

    public void CheckQuestion(ActionEvent actionEvent) throws InterruptedException, SQLException, ApplicationException, IOException {

        if (OdpA.isSelected()) {
            CheckODP_A();
        }

        if (OdpB.isSelected()) {
            CheckODP_B();
        }

        if (OdpC.isSelected()) {
            CheckODP_C();
        }

        if (OdpD.isSelected()) {

            CheckODP_D();
        }
        if (OdpE.isSelected()) {
            CheckODP_E();
        }

        selectedFalseButton();

        if (isTime) t.interrupt();
        count++;


        if ((LiczbaPytan - StartQuestionId+1) != (count)) {
            NextQuestion();
        } else {
            if (isTime) t.stop();
            if (isTime) t.join();
            KoniecTestu();
        }

    }

    public void onPoprzedniePytanie() throws InterruptedException, IOException, ApplicationException {

        count--;

        NextQuestion();

    }

    public void KoniecTestu() throws SQLException, ApplicationException {

        goodQuestionLists.stream().filter(GoodQuestionList::isGood).map(GoodQuestionList::getNazwaPytania).forEach(System.out::print);
        Predicate<GoodQuestionList> mNazwa = GoodQuestionList::isGood;
        List<GoodQuestionList> lista = goodQuestionLists.stream().filter(mNazwa).collect(Collectors.toList());

        Wynik.setText("Zdobyto " + lista.size() + " z " + (LiczbaPytan -StartQuestionId+1)+ " możliwych punktów");
        IdQuestion.setVisible(false);
        OdpB.setVisible(false);
        OdpA.setVisible(false);

        OdpC.setVisible(false);
        OdpE.setVisible(false);
        OdpD.setVisible(false);
        Pytanie.setVisible(false);
        Powrót.setVisible(true);
        Wynik.setVisible(true);
        this.ImageID.setImage(null);
        NastepnePytanieButton.setVisible(false);
        backButton.setVisible(false);
        this.PytanieLabel.setVisible(false);

        if (isTime) t.stop();
        if (isTime) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        goodQuestionLists.forEach(e -> {
            System.out.println(e.toString());
        });

        /////////////////////////////////////////////
        Wynik wynik = new Wynik();

        wynik.setImie(StudentStartTestController.getNameStudent());
        wynik.setNazwisko(StudentStartTestController.getSurnameStudent());
        wynik.setNumerIndexu(StudentStartTestController.getIndexIdStudent());
        wynik.setTest_Name(TestName);
        wynik.setClassName(StudentStartTestController.getClassNameStudent());
        wynik.setScore((lista.size()) + "/" + (LiczbaPytan-StartQuestionId+1));

        double Procent = (double) ((lista.size())) * 100 / (double) (LiczbaPytan-StartQuestionId+1);
        int scoreIntProcent = (int)Procent;

        wynik.setsCORE_PROCENT(String.valueOf(scoreIntProcent));

        HttpUtils.createWyniki(wynik);

    }

    public void onPowrótdoMenu(ActionEvent actionEvent) {
        count = 0;

        Stage stage = (Stage) mainViewController.BorderPaneMainView.getScene().getWindow();
        stage.setWidth(459);
        stage.setHeight(317);

        stage.setResizable(false);
        stage.setTitle("Okno główne");
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);


        mainViewController.loadMenu();
    }

    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    private void CheckODP_C() {
        if (OdpC.getText().equals(pytanieModel.getPytanieFXObservableList().get(count).getPopr_FX())) {

            goodQuestionLists.set(count, new GoodQuestionList(this.Pytanie.getText(), this.OdpC.getText(), true));

        } else {
            goodQuestionLists.set(count, new GoodQuestionList(this.Pytanie.getText(), this.OdpC.getText(), false));
        }
    }

    private void CheckODP_B() {
        if (OdpB.getText().equals(pytanieModel.getPytanieFXObservableList().get(count).getPopr_FX())) {

            goodQuestionLists.set(count, new GoodQuestionList(this.Pytanie.getText(), this.OdpB.getText(), true));

        } else {
            goodQuestionLists.set(count, new GoodQuestionList(this.Pytanie.getText(), this.OdpB.getText(), false));

        }
    }

    private void CheckODP_A() {
        if (OdpA.getText().equals(pytanieModel.getPytanieFXObservableList().get(count).getPopr_FX())) {

            goodQuestionLists.set(count, new GoodQuestionList(this.Pytanie.getText(), this.OdpA.getText(), true));

        } else {
            goodQuestionLists.set(count, new GoodQuestionList(this.Pytanie.getText(), this.OdpA.getText(), false));


        }
    }

    private void CheckODP_D() {
        if (OdpD.getText().equals(pytanieModel.getPytanieFXObservableList().get(count).getPopr_FX())) {

            //  if (goodQuestionLists.size() == count) {
            goodQuestionLists.set(count, new GoodQuestionList(this.Pytanie.getText(), this.OdpD.getText(), true));
            //   }

        } else {
            goodQuestionLists.set(count, new GoodQuestionList(this.Pytanie.getText(), this.OdpD.getText(), false));
        }
    }

    private void CheckODP_E() {
        if (OdpE.getText().equals(pytanieModel.getPytanieFXObservableList().get(count).getPopr_FX())) {

            //   if (goodQuestionLists.size() == count) {
            goodQuestionLists.set(count, new GoodQuestionList(this.Pytanie.getText(), this.OdpE.getText(), true));
            //    }

        } else {
            goodQuestionLists.set(count, new GoodQuestionList(this.Pytanie.getText(), this.OdpE.getText(), false));

        }
    }

    public void setInVisibleButton() {

        OdpA.setVisible(false);
        OdpB.setVisible(false);
        OdpC.setVisible(false);
        OdpD.setVisible(false);
        OdpE.setVisible(false);

    }

    public void startTimer() {
        final int[] tempt = new int[1];
        final boolean[] zmienna = { true };
        this.t = new Thread(() -> {
            while (zmienna[0]) {

                try {

                    Thread.sleep(TimeOfQestion * 1000);

                    Platform.runLater(() -> NastepnePytanieButton.fire());

                } catch (InterruptedException e) {

                }

                if (pytanieModel.getPytanieFXObservableList().size() == (count)) {

                    zmienna[0] = false;
                }
            }
        });

        t.setDaemon(true);
        t.start();

    }

}



