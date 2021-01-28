package Controller;

import Models.PytanieFX;
import Models.PytanieModel;
import Models.TestFilterModel;
import UTILS.ApplicationException;
import UTILS.DialogUtils;
import UTILS.HttpUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import mgr.common.entities.TestFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class QuizPropertiesDrukowanieController {

    public ComboBox TestNameCombo;
    public Label wybranyTest;
    public TextField TestNumbertoPrints;
    public TextField FileName;
    public TextField placeForNotAnwser;

    PytanieModel pytanieModel;
    private MainViewController mainViewController;

    private static  int  LiczbaPytan;
    private  static boolean Random_Answer;

    private static int StartQuestionId;

    private  static boolean Random_Question ;

    private static  boolean NotShowAwser ;

    private static int TimeOfQestion ;

    private static boolean isTime ;

    private static String TestName;

    private List lista;
    private static String PrintFileName;
    private static int PrintFileNumber = 0;
    private static int NumberOFTests;
    private static int HowManyFreeSpace;


    public static String getPrintFileName() {
        return PrintFileName;
    }

    public static void setPrintFileName(String printFileName) {
        PrintFileName = printFileName;
    }

    public static int getPrintFileNumber() {
        return PrintFileNumber;
    }

    public static void setPrintFileNumber(int printFileNumber) {
        PrintFileNumber = printFileNumber;
    }

    public static int getNumberOFTestsToPrint() {
        return NumberOFTests;
    }

    public static void setNumberOFTests(int numberOFTests) {
        NumberOFTests = numberOFTests;
    }

    public static List getList() {
        return list;
    }

    public static void setList(List list) {
        QuizPropertiesDrukowanieController.list = list;
    }

    private static List list;

    public static int getLiczbaPytan() {
        return LiczbaPytan;
    }

    public static void setLiczbaPytan(int liczbaPytan) {
        LiczbaPytan = liczbaPytan;
    }

    public static boolean isRandom_Answer() {
        return Random_Answer;
    }

    public static void setRandom_Answer(boolean random_Answer) {
        Random_Answer = random_Answer;
    }

    public static int getHowManyFreeSpace() {
        return HowManyFreeSpace;
    }

    public static void setHowManyFreeSpace(int howManyFreeSpace) {
        HowManyFreeSpace = howManyFreeSpace;
    }

    public MainViewController getMainViewController() {
        return mainViewController;
    }


    public static int getStartQuestionId() {
        return StartQuestionId;
    }

    public static void setStartQuestionId(int startQuestionId) {
        StartQuestionId = startQuestionId;
    }

    public static boolean isRandom_Question() {
        return Random_Question;
    }

    public static void setRandom_Question(boolean random_Question) {
        Random_Question = random_Question;
    }

    public static boolean isNotShowAwser() {
        return NotShowAwser;
    }

    public static void setNotShowAwser(boolean notShowAwser) {
        NotShowAwser = notShowAwser;
    }

    public static int getTimeOfQestion() {
        return TimeOfQestion;
    }

    public static void setTimeOfQestion(int timeOfQestion) {
        TimeOfQestion = timeOfQestion;
    }

    public static boolean isIsTime() {
        return isTime;
    }

    public static void setIsTime(boolean isTime) {
        QuizPropertiesDrukowanieController.isTime = isTime;
    }

    public static String getTestName() {
        return TestName;
    }

    public static void setTestName(String testName) {
        TestName = testName;
    }

    public TestFilterModel getTestFilterModel() {
        return testFilterModel;
    }

    public void setTestFilterModel(TestFilterModel testFilterModel) {
        this.testFilterModel = testFilterModel;
    }

    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    private TestFilterModel testFilterModel;

    public void initialize(){


        pytanieModel = new PytanieModel();
        testFilterModel = new TestFilterModel();



        try {
            pytanieModel.init();
            testFilterModel.init();
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.TestNameCombo.getItems().addAll(testFilterModel.getTestFilterNAME_TEST());


    }







    public void Print(ActionEvent actionEvent) {


        setPrintFileName(this.FileName.textProperty().getValue());


        try {
            Double.parseDouble(this.TestNumbertoPrints.textProperty().getValue());
            Double.parseDouble(this.placeForNotAnwser.getText());

        } catch (NumberFormatException e) {
            DialogUtils.errrorAlert_Values();
        }


        setNumberOFTests(Integer.parseInt(this.TestNumbertoPrints.textProperty().getValue()));
        setHowManyFreeSpace(Integer.parseInt(this.placeForNotAnwser.getText()));

      for(int i = 0; i< getNumberOFTestsToPrint(); i++)
      {
          PrintingTime();
      }



    }

    private void PrintingTime() {
        List<PytanieFX> wyfiltrowanalista2 =  this.pytanieModel.getWymieszanaListaOdpowiedzi();


        if (Random_Question == true) {

            Collections.shuffle(wyfiltrowanalista2);

        }

        //////////////////////////////////////////////////////////////

        int losweOpowedzi;


        //   List<PytanieFX> wyfiltrowanalista2 = wyfiltrowanalista;

        System.out.println( wyfiltrowanalista2.size()+ " LICZBA PYTAN 3");
        if (Random_Answer == true) {
            this.pytanieModel.getPytanieFXObservableList().clear();
               double randomDouble = Math.random();
               randomDouble = randomDouble * 7 + 1;
               losweOpowedzi = ((int) randomDouble);

               if (losweOpowedzi == 1) {
         //         this.pytanieModel.getPytanieFXObservableList().clear();

                   wyfiltrowanalista2.forEach(c -> {

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

             //      this.pytanieModel.getPytanieFXObservableList().clear();
                   wyfiltrowanalista2.forEach(c -> {

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

            //       this.pytanieModel.getPytanieFXObservableList().clear();
                   wyfiltrowanalista2.forEach(c -> {

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
             //      this.pytanieModel.getPytanieFXObservableList().clear();

                   wyfiltrowanalista2.forEach(c -> {

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
              //     this.pytanieModel.getPytanieFXObservableList().clear();

                   wyfiltrowanalista2.forEach(c -> {

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
               //   this.pytanieModel.getPytanieFXObservableList().clear();

                   wyfiltrowanalista2.forEach(c -> {

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
             //     this.pytanieModel.getPytanieFXObservableList().clear();

                   wyfiltrowanalista2.forEach(c -> {

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


        System.out.println( this.pytanieModel.getPytanieFXObservableList()+ " LICZBA PYTAN 1");

        for(int i = 0; i<this.pytanieModel.getPytanieFXObservableList().size(); i++){

            ToTheFirst(i);
        }

        setList(this.pytanieModel.getPytanieFXObservableList());
        System.out.println( getList().size()+ " LICZBA PYTAN 2");

        PRINTING();
    }

    private void PRINTING() {
        //  Predicate<PytanieFX> mNazwa = pytanieFX1 -> pytanieFX1.getKategoria_FX().equals("Geologia");


        setPrintFileNumber(getPrintFileNumber()+1);

        List<PytanieFX> wyfiltorwanaLista = getList();


        Document document = new Document();
        try {



            PdfWriter.getInstance(document, new FileOutputStream(getPrintFileName()+getPrintFileNumber()+
                    ".pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();

        BaseFont helvetica = null;
        try {
            helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Font helvetica16=new Font(helvetica,10);
        new Paragraph("Śćźół:",helvetica16);
        //    Font font = FontFactory.getFont(FontFactory.TIMES, 8, BaseColor.BLACK);
        //  Chunk chunk = new Chunk("Hello World", font)

        float fixed = 2.4f;
        float multipled = 2.0f;


        AtomicInteger count = new AtomicInteger(1);
        int count1 = 0;

        Font font = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        try {
            Paragraph chunk = new Paragraph("Nazwa testu: " + getPrintFileName() + getPrintFileNumber());
            document.add(chunk);
            document.add(new Paragraph("\n"));

            chunk = new Paragraph("Imię i nazwisko:", font);
            document.add(chunk);
            document.add(new Paragraph("\n"));

            chunk = new Paragraph("Numer indeksu:", font);
            document.add(chunk);
            document.add(new Paragraph("\n"));

            // Font bold = Font font = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
            chunk = new Paragraph("Grupa:", font);
            document.add(chunk);
            document.add(new Paragraph("\n"));

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        for (PytanieFX c : wyfiltorwanaLista) {
            try {

                Phrase phrase = new Phrase(count + ".  " + c.getPytanie_FX(), helvetica16);
                document.add(phrase);
                document.add(new Paragraph("\n"));


                if (!c.getObraz_Fx().equals("")) {
                    byte[] image = Base64.getDecoder().decode(c.getObraz_Fx());
                    if (image != null) {
                        //
                        //             String s = Base64.getEncoder().encodeToString(getObraz());

                        InputStream bis = new ByteArrayInputStream(image);

                        // BufferedImage img = null;

                        try {
                            BufferedImage img = ImageIO.read(new ByteArrayInputStream(image));
                            Image image1 = Image.getInstance(img, null);
                            image1.scaleToFit(150f, 150f);
                            image1.setAlignment(Image.MIDDLE);
                            document.add(image1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                }


                if (c.getOdp_A_FX().equals("") && c.getOdp_B_FX().equals("") && c.getOdp_C_FX().equals("") && c.getOdp_D_FX().equals("") && c.getOdp_E_FX().equals("")) {
                   for(int i = 0; i<getHowManyFreeSpace(); i++)
                   {
                       document.add(new Paragraph("\n"));
                   }



                } else {

                    if (!(c.getOdp_A_FX().equals(""))) {
                        phrase = new Phrase("A) " + c.getOdp_A_FX(), helvetica16);
                        document.add(phrase);
                        document.add(new Paragraph("\n"));
                    }
                    if (!(c.getOdp_B_FX().equals(""))) {
                        phrase = new Phrase("B) " + c.getOdp_B_FX(), helvetica16);
                        document.add(phrase);
                        document.add(new Paragraph("\n"));
                    }

                    if (!(c.getOdp_C_FX().equals(""))) {
                        phrase = new Phrase("C) " + c.getOdp_C_FX(), helvetica16);
                        document.add(phrase);
                        document.add(new Paragraph("\n"));
                    }

                    if (!(c.getOdp_D_FX().equals(""))) {
                        phrase = new Phrase("D) " + c.getOdp_D_FX(), helvetica16);
                        document.add(phrase);
                        document.add(new Paragraph("\n"));
                    }
                    if (!(c.getOdp_E_FX().equals(""))) {
                        phrase = new Phrase("E) " + c.getOdp_E_FX(), helvetica16);
                        document.add(phrase);
                        document.add(new Paragraph("\n"));
                    }


                }

                count.getAndIncrement();
                count1++;

                if(getLiczbaPytan()  == count1 ){
                    break;
                }


            } catch (DocumentException e) {
                e.printStackTrace();
            }



            /*
                if( !(c.getOdp_A_FX().equals(""))){
                        document.add(phrase);
                        document.add(new Paragraph("\n"));
                        phrase = new Phrase("A) "+ c.getOdp_A_FX(), helvetica16);
                    }
                if( !(c.getOdp_B_FX().equals(""))){
                    document.add(phrase);
                    document.add(new Paragraph("\n"));
                    phrase = new Phrase("B) "+ c.getOdp_B_FX(), helvetica16);

                }
                if( !(c.getOdp_C_FX().equals(""))){
                    document.add(phrase);
                    document.add(new Paragraph("\n"));
                    phrase = new Phrase("C) "+ c.getOdp_C_FX(), helvetica16);

                }
                if( !(c.getOdp_D_FX().equals(""))){
                    document.add(phrase);
                    document.add(new Paragraph("\n"));
                    phrase = new Phrase("D) "+ c.getOdp_D_FX(), helvetica16);

                }
                if( !(c.getOdp_E_FX().equals(""))){
                    document.add(phrase);
                    document.add(new Paragraph("\n"));
                    phrase = new Phrase("E) "+ c.getOdp_E_FX(), helvetica16);

                }
             */

        }


        document.close();
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

        mainViewController.loadMenu();



    }

    public void pokazTesty(ActionEvent actionEvent) {

        Stage userStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = null;
        try {
            root = loader.load(getClass().getResource("/PokazTesty.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        PokazTestyController pokazTestyController = loader.getController();

        Scene scene = new Scene(root);
        userStage.setScene(scene);
        userStage.setTitle("Baza Testów");
        userStage.setResizable(false);
        userStage.show();
    }

    public void SelectedItem(ActionEvent actionEvent) {

        this.wybranyTest.setText(this.TestNameCombo.getSelectionModel().getSelectedItem().toString());

        Predicate<TestFilter> filtrowaniePoNaziwe = testFilterPass -> testFilterPass.getNameTest().equals(this.TestNameCombo.getSelectionModel().getSelectedItem().toString());
        List<TestFilter> testFilters = HttpUtils.getAllTestFilters().stream().filter(filtrowaniePoNaziwe).collect(Collectors.toList());

        System.out.println(testFilters.size());

        Predicate<PytanieFX> mNazwa = pytanieFX1 -> pytanieFX1.getKategoria_FX().equals(testFilters.get(0).getKategoria());
        List<PytanieFX> wyfiltrowanalista = this.pytanieModel.getPytanieFXObservableList().stream().filter(mNazwa).collect(Collectors.toList());

    setLiczbaPytan(testFilters.get(0).getIloscPytan());
        setRandom_Answer(testFilters.get(0).getRandom_Anwser());
    setStartQuestionId(Integer.parseInt(testFilters.get(0).getStartQuestionID()));
    setRandom_Question(testFilters.get(0).getRandom_Question());
    setNotShowAwser(testFilters.get(0).getNotShowAnwser());
    setTimeOfQestion(testFilters.get(0).getTime_of_Question());
    setIsTime(false);
    setTestName(testFilters.get(0).getNameTest());


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


        this.pytanieModel.getWymieszanaListaOdpowiedzi().addAll( this.pytanieModel.getPytanieFXObservableList());


    }

    private void ToTheFirst(int count) {

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






}



