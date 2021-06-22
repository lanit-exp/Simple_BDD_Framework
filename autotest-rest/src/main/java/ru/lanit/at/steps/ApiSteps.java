package ru.lanit.at.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import ru.lanit.at.api.ApiRequest;
import ru.lanit.at.api.models.RequestModel;
import ru.lanit.at.api.testcontext.ContextHolder;
import ru.lanit.at.utils.*;

import java.util.HashMap;
import java.util.Map;

import static ru.lanit.at.api.StorageKeys.RESPONSE;
import static ru.lanit.at.api.StorageKeys.RESPONSE_BODY;
import static ru.lanit.at.api.testcontext.ContextHolder.replaceVarsIfPresent;
import static ru.lanit.at.utils.JsonUtil.getFieldFromJson;

public class ApiSteps {

    private static final Logger LOG = LoggerFactory.getLogger(ApiSteps.class);
    private ApiRequest apiRequest;

    @И("создать запрос")
    public void createRequest(RequestModel requestModel) {
        apiRequest = new ApiRequest(requestModel);
        LOG.info("Запрос с параметрами: {}", requestModel.toString());
    }

    @И("добавить header")
    public void addHeaders(DataTable dataTable) {
        Map<String, String> headers = new HashMap<>();
        dataTable.asLists().forEach(it -> headers.put(it.get(0), it.get(1)));
        apiRequest.setHeaders(headers);
    }

    @И("добавить query параметры")
    public void addQuery(DataTable dataTable) {
        Map<String, String> query = new HashMap<>();
        dataTable.asLists().forEach(it -> query.put(it.get(0), it.get(1)));
        apiRequest.setQuery(query);
    }

    @И("отправить запрос")
    public void send() {
        Response response = apiRequest.sendRequest();
        String json = JsonUtil.jsonToUtf(response.body().asPrettyString());
        ContextHolder.put(RESPONSE, response);
        ContextHolder.put(RESPONSE_BODY, json);
        LOG.info("Тело ответа:\n{}", json);
    }

    @И("отправлять запрос пока не вернется статус {int}")
    public void sendUntilStatus(int status) {
        int iteration = 0;
        int pause = 2;
        Response response = null;
        for (int i = 0; i < 10; i++) {
            response = apiRequest.sendRequest();
            if (response.statusCode() == status) {
                String json = JsonUtil.jsonToUtf(response.body().asPrettyString());
                ContextHolder.put(RESPONSE, response);
                ContextHolder.put(RESPONSE_BODY, json);
                LOG.info("Тело ответа:\n{}", json);
                break;
            } else {
                LOG.info("Status code != {}, pause {} sec", status, pause);
                Sleep.pauseSec(pause);
            }
            iteration++;
        }
        Assert.assertEquals(response.statusCode(), status, String.format("По прошествии %s секунд полученный статус код = %s", iteration * pause, response.statusCode()));
    }

    @И("статус код {int}")
    public void expectStatusCode(int code) {
        Response response = ContextHolder.getValue(RESPONSE);
        Assert.assertEquals(response.statusCode(), code);
        LOG.info("Статус код: {}", response.statusCode());
    }

    @И("извлечь данные")
    public void extractVariables(Map<String, String> vars) {
        String responseBody = ContextHolder.getValue(RESPONSE_BODY);
        vars.forEach((k, jsonPath) -> {
            String extractedValue = VariableUtil.extractBrackets(getFieldFromJson(responseBody, jsonPath));
            ContextHolder.put(k, extractedValue);
            LOG.info("Извлечены данные: {}={}", k, extractedValue);
        });
    }

    @И("снегерировать переменные")
    public void generateVariables(Map<String, String> table) {
        table.forEach((k, v) -> {
            String value = DataGenerator.generateValueByMask(replaceVarsIfPresent(v));
            ContextHolder.put(k, value);
            LOG.info("Сгенерирована переменная: {}={}", k, value);
        });
    }

    @И("сравнить значения")
    public void compareVars(DataTable table) {
        table.asLists().forEach(it -> {
            String expect = replaceVarsIfPresent(it.get(0));
            String actual = replaceVarsIfPresent(it.get(2));
            boolean compareResult = CompareUtil.compare(expect, actual, it.get(1));
            Assert.assertTrue(compareResult, String.format("Ожидаемое: '%s'\nФактическое: '%s'\nОператор сравнения: '%s'\n", expect, actual, it.get(1)));
            LOG.info("Сравнение значений: {} {} {}", expect, it.get(1), actual);
        });
    }

    @И("подождать {int} сек")
    public void waitSeconds(int timeout) {
        Sleep.pauseSec(timeout);
    }
}
