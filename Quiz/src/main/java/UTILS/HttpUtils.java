package UTILS;

import com.google.gson.Gson;
import mgr.common.entities.Pytania;
import mgr.common.entities.TestFilter;
import mgr.common.entities.Wynik;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class HttpUtils {

    private static final Logger log = Logger.getLogger("http-utils");

    private static final String BASE_URL = "http://localhost:8080"; // todo: read from config file
    // http://51.254.115.19:8080 ///
    private static final Gson gson = new Gson();

    private static String adminPassword;

    public static void setAdminPassword(String adminPassword) {
        String credentials = "admin:" + adminPassword;
        HttpUtils.adminPassword = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }
///////////  WYNIKI

    public static void createWyniki(Wynik p) {
        String json = gson.toJson(p);

        log.info("create Wynik payload: " + json);

        int statusCode = 0;
        try {
            statusCode = Request.Post(BASE_URL + "/score/create")
                    .bodyString(json, ContentType.APPLICATION_JSON)
                    .execute()
                    .returnResponse()
                    .getStatusLine()
                    .getStatusCode();
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
        log.info("status of create Wynik operation: " + statusCode);

    }

    public static List<Wynik> getAllWynik() {
        try {
            String json = Request.Get(BASE_URL + "/score/all")
                    .execute()
                    .returnContent()
                    .asString();

            log.info("all score: " + json);

            Wynik[] wyniks = gson.fromJson(json, Wynik[].class);
            List<Wynik> testfilterlist = Arrays.asList(wyniks);

            log.info("socres mapped to list: " + testfilterlist);

            return testfilterlist;
        } catch (IOException e) {
            log.warning(e.getMessage());
            return Collections.emptyList();
        }
    }

    public static void deleteWynik(int filterId) {
        try {
            Request.Delete(BASE_URL + "/score/delete/" + filterId)
                    .addHeader(getAdminCredentials())
                    .execute();
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
    }


    //////////////////////////////  Test Filters

    public static void createTestFilter(TestFilter p) {
        String json = gson.toJson(p);

        log.info("create TestFilter payload: " + json);

        int statusCode = 0;
        try {
            statusCode = Request.Post(BASE_URL + "/test-filter/create")
                    .addHeader(getAdminCredentials())
                    .bodyString(json, ContentType.APPLICATION_JSON)
                    .execute()
                    .returnResponse()
                    .getStatusLine()
                    .getStatusCode();
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
        log.info("status of create test-filter operation: " + statusCode);

    }

    public static void deleteFilter(int filterId) {
        try {
            Request.Delete(BASE_URL + "/test-filter/delete/" + filterId)
                    .addHeader(getAdminCredentials())
                    .execute();
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
    }




    public static List<TestFilter> getAllTestFilters() {
        try {
            String json = Request.Get(BASE_URL + "/test-filter/all")
                    .execute()
                    .returnContent()
                    .asString();

            log.info("all test-filters: " + json);

            TestFilter[] testFilters = gson.fromJson(json, TestFilter[].class);
            List<TestFilter> testfilterlist = Arrays.asList(testFilters);

            log.info("test-filters mapped to list: " + testfilterlist);

            return testfilterlist;
        } catch (IOException e) {
            log.warning(e.getMessage());
            return Collections.emptyList();
        }
    }

    ///// Pytanie Section
    public static void createQuestion(Pytania p) {
        String json = gson.toJson(p);
        log.info("create question payload: " + json);
        int statusCode = 0;
        try {
            statusCode = Request.Post(BASE_URL + "/question/create")
                    .addHeader(getAdminCredentials())
                    .bodyString(json, ContentType.APPLICATION_JSON)
                    .execute()
                    .returnResponse()
                    .getStatusLine()
                    .getStatusCode();
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
        log.info("status of create question operation: " + statusCode);
    }

    public static List<Pytania> getAllQuestions() {
        try {
            String json = Request.Get(BASE_URL + "/question/all")
                    .execute()
                    .returnContent()
                    .asString();

            log.info("all questions: " + json);

            Pytania[] pytanias = gson.fromJson(json, Pytania[].class);
            List<Pytania> pytaniaList = Arrays.asList(pytanias);

            log.info("Questions mapped to list: " + pytaniaList);

            return pytaniaList;
        } catch (IOException e) {
            log.warning(e.getMessage());
            return Collections.emptyList();
        }
    }

    public static void deleteQuestion(int questionId) {
        try {
            Request.Delete(BASE_URL + "/question/delete/" + questionId)
                    .addHeader(getAdminCredentials())
                    .execute();
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
    }

    public static void updateQuestion(Pytania p) {
        try {
            Request.Put(BASE_URL + "/question/update")
                    .addHeader(getAdminCredentials())
                    .bodyString(gson.toJson(p), ContentType.APPLICATION_JSON)
                    .execute();
        } catch (Exception e) {
            log.warning(e.getMessage());
        }
    }

    public static boolean isAdminPasswordValid() {
        try {
            int statusCode = Request.Get(BASE_URL + "/admin/ping")
                    .addHeader(getAdminCredentials())
                    .execute()
                    .returnResponse()
                    .getStatusLine()
                    .getStatusCode();

            log.info("admin ping status: " + statusCode);

            return statusCode == HttpStatus.SC_ACCEPTED;
        } catch (IOException e) {
            log.warning(e.getMessage());
            return false;
        }
    }

    private static Header getAdminCredentials() {
        return new BasicHeader("Authorization", adminPassword);
    }
}
