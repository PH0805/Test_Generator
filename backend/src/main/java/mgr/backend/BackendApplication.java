package mgr.backend;

import mgr.backend.repository.PytaniaRepository;
import mgr.backend.repository.TestFilterRepository;
import mgr.backend.repository.WynikRepository;
import mgr.common.entities.Pytania;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories
@EnableTransactionManagement
@EntityScan("mgr.common.entities")
@SpringBootApplication
public class BackendApplication {

    /// Komentarz
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
/*
    @Bean
    @Profile("dev")
    public CommandLineRunner commandLineRunner(PytaniaRepository pytaniaRepository, TestFilterRepository testFilterRepository, WynikRepository wynikRepository) {
        return args -> {
            Pytania pytania = new Pytania();
            pytania.setKategoria("Historia");
            pytania.setPytanie("Którego dnia tygodnia miała miejsce tzw. panika giełdowa na nowojorskiej Wall Street, która uważana jest za początek wielkiego kryzysu w XX wieku?\n");


            pytania.setOdpA("W piątek");
            pytania.setOdpB("w poniedziałek");
            pytania.setOdpC("we wtorek");
            pytania.setOdpD("w środe");
            pytania.setOdpE("w czwartek");
            pytania.setOdpPopr("w czwartek");

            Pytania saved = pytaniaRepository.save(pytania);
            System.out.println("saved entity: " + saved);

            Pytania p2 = new Pytania();
            p2.setKategoria("Historia");
            p2.setPytanie("Kiedy Cesarstwo Wielkiej Japonii podpisało akt bezwarunkowej kapitulacji i tym samym zakończyło swój udział w II wojnie światowej?");
            p2.setOdpA("8 Maja 1945");
            p2.setOdpB("9 Maja 1945");
            p2.setOdpC("22 Czerwca 1941");
            p2.setOdpD("2 Września 1945");
            p2.setOdpE("8 Września 1945");

            p2.setOdpPopr("2 Września 1945");

            saved = pytaniaRepository.save(p2);
            System.out.println("saved entity: " + saved);

            Pytania p3 = new Pytania();
            p3.setKategoria("Historia");
            p3.setPytanie("Jak potocznie nazywał się sąd, w trakcie którego obywatele ateńscy decydowali, kogo należy wydalić z Aten, w obawie przed dążeniem do tyranii?\n");
            p3.setOdpA("Demokratyczny");
            p3.setOdpB("Skorupkowy");
            p3.setOdpC("Szlachecki");
            p3.setOdpD("Ateński");
            p3.setOdpE("Odłamkowy");

            p3.setOdpPopr("Skorupkowy");

            saved = pytaniaRepository.save(p3);
            System.out.println("saved entity: " + saved);

            /*
            Pytania p4 = new Pytania();
            p4.setKategoria("Historia");
            p4.setPytanie("W którym roku wybuchło powstanie kozaków Ukraińskich pod dowództwem Bohdana Chmielnickiego");
            p4.setOdpA("1655");
            p4.setOdpB("1610");
      //  p3.setOdpC("");
            p4.setOdpD("1648");
         //   p3.setOdpE("");

            p4.setOdpPopr("1648");

            saved = pytaniaRepository.save(p4);
            System.out.println("saved entity: " + saved);




            Pytania p5 = new Pytania();
            p5.setKategoria("Historia");
            p5.setPytanie("Na ternenach jakiego dzisiejszego państwa zginął Ferdynand Magellan?");
            p5.setOdpA("Ekwador");
            p5.setOdpB("Polinezjia Francuska");
            p5.setOdpC("Madagaskar");
            p5.setOdpD("Wietnam");
            p5.setOdpE("Filipiny");

            p5.setOdpPopr("Filipiny");

            saved = pytaniaRepository.save(p5);
            System.out.println("saved entity: " + saved);


            Pytania p6 = new Pytania();
            p6.setKategoria("Historia");
            p6.setPytanie("Między jakim krajem Państwo Włoskie toczyło tzw. wojne włosko-abisyńską?");
            p6.setOdpA("z Tunezją");
            p6.setOdpB("z Etiopią");
            p6.setOdpC("z Portugalią");
            p6.setOdpD("była to wojna domowa");
            p6.setOdpE("");

            p6.setOdpPopr("z Etiopią");

            saved = pytaniaRepository.save(p6);
            System.out.println("saved entity: " + saved);

            /*

            Pytania p7 = new Pytania();
            p7.setKategoria("Historia");
            p7.setPytanie("Kto to jest?");
            p7.setOdpA("Czurcil");
            p7.setOdpB("Commrad Gorbaczow");
            p7.setOdpC("z Portugalią");
            p7.setOdpD("była to wojna domowa");
            p7.setOdpE("");
            p7.setImages
            p7.setOdpPopr("Commrad Gorbaczow");

            saved = pytaniaRepository.save(p7);
            System.out.println("saved entity: " + saved);

*/


            /////////////////////////////////// TEST FILTER

       /*

          TestFilter testFilter = new TestFilter();
            testFilter.setRandom_Anwser(true);
            estFilter.setNotShowAnwser(true);
            testFilter.setStartQuestionID("2");
            testFilter.setOpisTestu("to jest opis Tekstu");
            testFilter.setIloscPytan(2);
            testFilter.setNameTest("Historia");
            testFilter.setPassword("historia1");
            testFilter.setTime_of_Question(30);
            testFilter.setKategoria("histoira");
            testFilter.setRandom_Question(true);

            TestFilter saved1 = testFilterRepository.save(testFilter);

            System.out.println("saved entity: " + saved1);

              Wynik wynik = new Wynik();

            wynik.setClassName("GRUPA IIIC");
            wynik.setImie("Mateusz");
            wynik.setScore("15");
            wynik.setNumerIndexu("2566");
            wynik.setTest_Name("Historia");
            wynik.setNazwisko("OCHWAT");
            wynik.setsCORE_PROCENT("50/20");


            Wynik saved3 = wynikRepository.save(wynik);

            System.out.println("saved entity: " + saved3);





            ///////////////// Wynik


        };
          */

    }


