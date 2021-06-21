package ru.lanit.at.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import io.restassured.response.Response;
import org.junit.Assert;
import ru.lanit.at.api.ApiRequest;
import ru.lanit.at.api.models.RequestModel;
import ru.lanit.at.api.testcontext.ContextHolder;
import ru.lanit.at.utils.CompareUtil;
import ru.lanit.at.utils.DataGenerator;
import ru.lanit.at.utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

import static ru.lanit.at.api.StorageKeys.RESPONSE;
import static ru.lanit.at.api.StorageKeys.RESPONSE_BODY;

public class ApiSteps {

    private ApiRequest apiRequest;

    @И("создать запрос")
    public void createRequest(RequestModel requestModel) {
        apiRequest = new ApiRequest(requestModel);
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
    }

    @И("статус код {int}")
    public void expectStatusCode(int code) {
        Response response = ContextHolder.getValue(RESPONSE);
        Assert.assertEquals(code, response.statusCode());
    }

    @И("извлечь данные")
    public void extractVariables(Map<String, String> vars) {
        String responseBody = ContextHolder.getValue(RESPONSE_BODY);
        vars.forEach((k, jsonPath) -> {
            String extractedValue = JsonUtil.getFieldFromJson(responseBody, jsonPath);
            ContextHolder.put(k, extractedValue);
        });
    }

    @И("снегерировать переменные")
    public void generateVariables(Map<String, String> table) {
        table.forEach((k, v) -> {
            String value = DataGenerator.generateValueByMask(ContextHolder.replaceVarsIfPresent(v));
            ContextHolder.put(k, value);
        });
    }

    @И("сравнить значения")
    public void compareVars(DataTable table) {
        table.asLists().forEach(it -> {
            String expect = ContextHolder.replaceVarsIfPresent(it.get(0));
            String actual = ContextHolder.replaceVarsIfPresent(it.get(2));
            boolean compareResult = CompareUtil.compare(actual, expect, it.get(1));
            Assert.assertTrue(String.format("Ожидаемое: '%s'\nФактическое: '%s'\nОператор сравнения: '%s'\n", expect, actual, it.get(1)), compareResult);
        });
    }
}
