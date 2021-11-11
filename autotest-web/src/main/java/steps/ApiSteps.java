package steps;

import io.cucumber.java.ru.И;
import io.restassured.path.json.JsonPath;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.api.testcontext.ContextHolder;

import static io.restassured.RestAssured.given;

public class ApiSteps {

    private static final Logger LOG = LoggerFactory.getLogger(ApiSteps.class);

    @И("получить Token для юзера {string} с паролем {string}")
    public void getToken(String username,String password) {
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
        LOG.info("Токен для авторизации - {}", ContextHolder.getValue("TOTP").toString());
    }
}
