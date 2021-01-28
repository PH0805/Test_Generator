package UTILS;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DialogUtils {

    public static void dialogAboutApplication(){

        Alert informamationAlert = new Alert(Alert.AlertType.INFORMATION);
        informamationAlert.setTitle("O Aplikacji");
        informamationAlert.setHeaderText("Version ALPHA 1.00");
      //  informamationAlert.setHeaderText("Główne funkcjonalności projektu jakie chciałbym uwzględnić to:\n" +
   //             " losowanie kolejności pytań i odpowiedzi według zadanych parametrów, drukowanie formularzy testów pytań i tabeli odpowiedzi, drukowanie samych pytań bez odpowiedzi etc…");
        informamationAlert.setContentText("Główne funkcjonalności projektu jakie chciałbym uwzględnić to:\n" +
                " losowanie kolejności pytań i odpowiedzi według zadanych parametrów, drukowanie formularzy testów pytań i tabeli odpowiedzi, drukowanie samych pytań bez odpowiedzi etc…");
        informamationAlert.showAndWait();
    }

    public static Optional<ButtonType> confirmExit(){

        Alert conffirmExit = new Alert(Alert.AlertType.CONFIRMATION);
        conffirmExit.setTitle("Okno wyboru");
        conffirmExit.setHeaderText("Czy na pewno chcesz zakończyć działanie aplikacji?");
        Optional<ButtonType> result = conffirmExit.showAndWait();

        return result;

    }


    public static Optional<ButtonType> deleteCategory() {

        Alert conffirmExit = new Alert(Alert.AlertType.CONFIRMATION);
        conffirmExit.setTitle("Okno wyboru");
        conffirmExit.setHeaderText("Czy na pewno chcesz usunąć kategorie?");
        Optional<ButtonType> result = conffirmExit.showAndWait();

        return result;

    }


    public static void errrorAlert_Values() {
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setTitle("Błąd");
        errorAlert.setHeaderText("Błędnie uzupełniony formularz");
        errorAlert.showAndWait();
    }


    public static void errrorAlert_Passowrd() {
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setTitle("Błąd");
        errorAlert.setHeaderText("Błędne hasło - prawdopodobnie wpisano błędny indetyfikator testu");
        errorAlert.showAndWait();
    }

    public static void errrorAlert_Passowrd_Haslo() {
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setTitle("Błąd");
        errorAlert.setHeaderText("Błędne hasło logowania");
        errorAlert.showAndWait();
    }


    public static void errrorAlert_wart(){
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setTitle("Błąd");
        errorAlert.setHeaderText("Błąd generacji. Sprawdź wartości wpisane w arkuszu");
        errorAlert.showAndWait();
    }


    public static void MakeTest() {

        Alert informamationAlert = new Alert(Alert.AlertType.INFORMATION);
        informamationAlert.setTitle("Gratulacje");
        informamationAlert.setHeaderText("Stworzyłeś nowy test");
        informamationAlert.showAndWait();
    }


}
