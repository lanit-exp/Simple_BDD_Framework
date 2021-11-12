package steps;

import io.cucumber.java.ru.И;
import io.restassured.path.json.JsonPath;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.api.testcontext.ContextHolder;
import ru.lanit.at.web.pagecontext.PageManager;

import static io.restassured.RestAssured.given;

public class ApiSteps {

    private static String token = "";
    private PageManager pageManager;
    private static final Logger LOG = LoggerFactory.getLogger(ApiSteps.class);

    public ApiSteps(PageManager pageManager) {
        this.pageManager = pageManager;
    }

    public static String getCurrentToken() {
        return token;
    }

    @И("получить Token для юзера {string} с паролем {string}")
    public static void getToken(String username, String password) {
        JSONObject innerBody = new JSONObject();
        innerBody.put("username", username);
        innerBody.put("password", password);
        JsonPath tokenJson = given()
                .baseUri("http://178.154.246.238:58082/")
                .contentType("application/json")
                .body(innerBody)
                .when()
                .post("api/otp_token/")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
        ContextHolder.put("TOTP", tokenJson.get("otp_token").toString());
        token = ContextHolder.getValue("TOTP").toString();
        LOG.info("Токен для авторизации - {}", ContextHolder.getValue("TOTP").toString());
    }
}
