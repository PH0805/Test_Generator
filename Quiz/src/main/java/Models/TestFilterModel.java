package Models;

import UTILS.ApplicationException;
import UTILS.HttpUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mgr.common.entities.Pytania;
import mgr.common.entities.TestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TestFilterModel {

    private ObservableList<TestFilterFX> testFilterFXXESFXObservableList = FXCollections.observableArrayList();




    private ObservableList<TestNameComboModel> testFilterNAME_TEST= FXCollections.observableArrayList();

    public ObservableList<TestNameComboModel> getTestFilterNAME_TEST() {
        return testFilterNAME_TEST;
    }

    public void setTestFilterNAME_TEST(ObservableList<TestNameComboModel> testFilterNAME_TEST) {
        this.testFilterNAME_TEST = testFilterNAME_TEST;
    }

    private ObjectProperty<TestFilterFX> testFilterFXObjectProperty = new SimpleObjectProperty<>(new TestFilterFX());


    public TestFilterFX getTestFilterFXObjectProperty() {
        return testFilterFXObjectProperty.get();
    }

    public ObjectProperty<TestFilterFX> testFilterFXObjectPropertyProperty() {
        return testFilterFXObjectProperty;
    }

    public void setTestFilterFXObjectProperty(TestFilterFX testFilterFXObjectProperty) {
        this.testFilterFXObjectProperty.set(testFilterFXObjectProperty);
    }

    public ObservableList<TestFilterFX> getTestFilterFXXESFXObservableList() {
        return testFilterFXXESFXObservableList;
    }

    public void setTestFilterFXXESFXObservableList(ObservableList<TestFilterFX> testFilterFXXESFXObservableList) {
        this.testFilterFXXESFXObservableList = testFilterFXXESFXObservableList;
    }




    public void init() throws ApplicationException {

        testFilterFXXESFXObservableList.clear();
        /////// Tworzenie Kategori
        List<TestFilter> ListaTestów = HttpUtils.getAllTestFilters(); // testFilterDao.queryForAll(TestFilter.class);
        //  List<String> Kategorie  = new ArrayList<>();
        //    Kategorie.clear();

        ListaTestów.forEach(c -> {

            TestFilterFX testFilterFX = new TestFilterFX();

            testFilterFX.setId_FX(c.getId());
            testFilterFX.setIloscPytan_FX(String.valueOf(c.getIloscPytan()));
            testFilterFX.setKategoria_FX(c.getKategoria());
            testFilterFX.setOpisTESTU_FX(c.getOpisTestu());

            if((c.getNotShowAnwser().equals(true)))
            {
                testFilterFX.setNotShowAnwser_FX("Tak");
            }
            else
            {
                testFilterFX.setNotShowAnwser_FX("Nie");
            }

            testFilterFX.setTest_Name_FX(c.getNameTest());
            if((c.getRandom_Anwser()).equals(true))
            {
                testFilterFX.setRandomAwser_FX("Tak");
            }
            else
            {
                testFilterFX.setRandomAwser_FX("Nie");
            }
            testFilterFX.setPassword_FX(c.getPassword());
            testFilterFX.setTune_of_Question_FX(String.valueOf(c.getTime_of_Question()));
            testFilterFX.setStartQuestionID_FX(c.getStartQuestionID());
            testFilterFX.setRandomQuestion_FX((String.valueOf(c.getRandom_Question())));
            if(c.getRandom_Question().equals(true))
            {
                testFilterFX.setRandomQuestion_FX("Tak");
            }
            else
            {
                testFilterFX.setRandomQuestion_FX("Nie");
            }
            testFilterFXXESFXObservableList.add(testFilterFX);

        });



        List<String> kategorie = ListaTestów.stream()
                .filter(x -> x.getNameTest() != null)
                .map(TestFilter::getNameTest)
                .distinct()
                .collect(Collectors.toList());


        for (String c : kategorie) {
            TestNameComboModel testNameComboModel = new TestNameComboModel();
            testNameComboModel.setName(c);

            System.out.println(c);

            this.testFilterNAME_TEST.add(testNameComboModel);
}



    }


    public void deleteTestFilterInDataBase() throws ApplicationException {
        int id = this.getTestFilterFXObjectProperty().getId_FX();
        HttpUtils.deleteFilter(id);

    }


}








