package Models;

import UTILS.ApplicationException;
import UTILS.HttpUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import mgr.common.entities.Pytania;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PytanieModel {
    private ObjectProperty<PytanieFX> pytanieFXObjectProperty = new SimpleObjectProperty<>(new PytanieFX());
    private ObjectProperty<TextField> TextFieldProperty = new SimpleObjectProperty<>();

    public TextField getTextFieldProperty() {
        return TextFieldProperty.get();
    }

    public ObjectProperty<TextField> textFieldPropertyProperty() {
        return TextFieldProperty;
    }

    public void setTextFieldProperty(TextField textFieldProperty) {
        this.TextFieldProperty.set(textFieldProperty);
    }

    private ObjectProperty<PytanieFX> pytanieFXObjectPropertyEdit = new SimpleObjectProperty<>(new PytanieFX());

    private ObjectProperty<PytanieFX> TABLEEdit = new SimpleObjectProperty<>(new PytanieFX());
    private ObservableList<PytanieFX> pytanieFXObservableList = FXCollections.observableArrayList();
    private ObservableList<PytanieFX> WymieszanaListaOdpowiedzi = FXCollections.observableArrayList();
    private ObservableList<KategoryComboModel> Kategorie_FXObservableList = FXCollections.observableArrayList();
    public ObservableList<PytanieFX> getWymieszanaListaOdpowiedzi() {
        return WymieszanaListaOdpowiedzi;
    }

    public PytanieFX getTABLEEdit() {
        return TABLEEdit.get();
    }

    public ObjectProperty<PytanieFX> TABLEEditProperty() {
        return TABLEEdit;
    }

    public void setTABLEEdit(PytanieFX TABLEEdit) {
        this.TABLEEdit.set(TABLEEdit);
    }


    public void updateQuestions() {


        Pytania pytanie = new Pytania();
        pytanie.setId(getTABLEEdit().getId_FX());
        pytanie.setPytanie(getTABLEEdit().getPytanie_FX());


        pytanie.setOdpA(getTABLEEdit().getOdp_A_FX());
        pytanie.setOdpB(getTABLEEdit().getOdp_B_FX());
        pytanie.setOdpC(getTABLEEdit().getOdp_C_FX());
        pytanie.setOdpD(getTABLEEdit().getOdp_D_FX());
        pytanie.setOdpE(getTABLEEdit().getOdp_E_FX());
        pytanie.setOdpPopr(getTABLEEdit().getPopr_FX());
        pytanie.setKategoria(getTABLEEdit().getKategoria_FX());
        pytanie.setImages(getTABLEEdit().getObraz_Fx());


        HttpUtils.updateQuestion(pytanie);

        try {
            RefreshTable();
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void setWymieszanaListaOdpowiedzi(ObservableList<PytanieFX> wymieszanaListaOdpowiedzi) {
        WymieszanaListaOdpowiedzi = wymieszanaListaOdpowiedzi;
    }

    public void init() throws ApplicationException, IOException {

/////// Tworzenie Kategori
        List<Pytania> listaPytań = HttpUtils.getAllQuestions(); //pytaniaDao.queryForAll(Pytania.class);

        List<String> kategorie = listaPytań.stream()
                .filter(x -> x.getKategoria() != null)
                .map(Pytania::getKategoria)
                .distinct()
                .collect(Collectors.toList());

        this.pytanieFXObservableList.clear();
        this.Kategorie_FXObservableList.clear();


        for (String c : kategorie) {
            KategoryComboModel kategoryComboModel = new KategoryComboModel();
            kategoryComboModel.setKategoryName(c.toString());
            this.Kategorie_FXObservableList.add(kategoryComboModel);
        }


        RefreshTable();
    }

    public void RefreshTable() throws ApplicationException, IOException {
        this.pytanieFXObservableList.clear();
///////////////////////////  Conwertowania z Bazdy Danych na Propertisy/  UPDATE Z BAZY DACNYH DO PROGRAMU
        List<Pytania> listaPytań = HttpUtils.getAllQuestions(); //
        for (Pytania c : listaPytań) {
            PytanieFX pytanieFX = new PytanieFX();
            pytanieFX.setId_FX(c.getId());
            pytanieFX.setPytanie_FX(c.getPytanie());
            pytanieFX.setOdp_A_FX(c.getOdpA());
            pytanieFX.setOdp_B_FX(c.getOdpB());
            pytanieFX.setOdp_C_FX(c.getOdpC());
            pytanieFX.setOdp_D_FX(c.getOdpD());
            pytanieFX.setOdp_E_FX(c.getOdpE());
            pytanieFX.setPopr_FX(c.getOdpPopr());
            pytanieFX.setKategoria_FX(c.getKategoria());
            if (!(c.getImages() == null)) {
                pytanieFX.setObraz_Fx(c.getImages());
            } else {
                pytanieFX.setObraz_Fx("");
            }

            /*else{
                pytanieFX.setObraz_Fx("");
            }*/

            this.pytanieFXObservableList.add(pytanieFX);

        }


    }


    public void deletePytanieInDataBase() throws ApplicationException {
        int id = this.getPytanieFXObjectPropertyEdit().getId_FX();
        HttpUtils.deleteQuestion(id);
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ObservableList<PytanieFX> getPytanieFXObservableList() {
        return pytanieFXObservableList;
    }

    public void setPytanieFXObservableList(ObservableList<PytanieFX> pytanieFXObservableList) {
        this.pytanieFXObservableList = pytanieFXObservableList;
    }

    public PytanieFX getPytanieFXObjectProperty() {
        return pytanieFXObjectProperty.get();
    }

    public ObjectProperty<PytanieFX> pytanieFXObjectPropertyProperty() {
        return pytanieFXObjectProperty;
    }

    public void setPytanieFXObjectProperty(PytanieFX pytanieFXObjectProperty) {
        this.pytanieFXObjectProperty.set(pytanieFXObjectProperty);
    }


    public ObservableList<KategoryComboModel> getKategorie_FXObservableList() {
        return Kategorie_FXObservableList;
    }

    public void setKategorie_FXObservableList(ObservableList<KategoryComboModel> kategorie_FXObservableList) {
        Kategorie_FXObservableList = kategorie_FXObservableList;
    }

    public PytanieFX getPytanieFXObjectPropertyEdit() {
        return pytanieFXObjectPropertyEdit.get();
    }

    public ObjectProperty<PytanieFX> pytanieFXObjectPropertyEditProperty() {
        return pytanieFXObjectPropertyEdit;
    }

    public void setPytanieFXObjectPropertyEdit(PytanieFX pytanieFXObjectPropertyEdit) {
        this.pytanieFXObjectPropertyEdit.set(pytanieFXObjectPropertyEdit);
    }


}
