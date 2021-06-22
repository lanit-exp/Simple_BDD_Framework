package ru.lanit.at.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.api.models.RequestModel;
import ru.lanit.at.api.properties.RestConfigurations;
import ru.lanit.at.utils.FileUtil;

import java.net.URI;
import java.util.Map;

import static ru.lanit.at.api.testcontext.ContextHolder.replaceVarsIfPresent;

public class ApiRequest {

    private final static Logger LOG = LoggerFactory.getLogger(ApiRequest.class);
    private final static RestConfigurations CONFIGURATIONS = ConfigFactory.create(RestConfigurations.class,
            System.getProperties(),
            System.getenv());

    private String baseUrl;
    private String path;
    private String method;
    private String body;
    private String bodyFromFile;
    private String fullUrl;

    private RequestSpecBuilder builder;

    public ApiRequest(RequestModel requestModel) {
        this.builder = new RequestSpecBuilder();

        this.baseUrl = CONFIGURATIONS.getBaseUrl();
        this.path = replaceVarsIfPresent(requestModel.getPath());
        this.method = requestModel.getMethod();
        this.body = requestModel.getBody();
        this.bodyFromFile = requestModel.getBodyFromFile();
        this.fullUrl = requestModel.getUrl();

        URI uri;

        if (fullUrl != null) {
            uri = URI.create(fullUrl);
        } else {
            uri = URI.create(baseUrl);
            builder.setBasePath(path);
        }

        this.builder.setBaseUri(uri);
        setBodyFromFile();
    }


    public void setHeaders(Map<String, String> headers) {
        headers.forEach((k, v) -> {
            builder.addHeader(k, v);
            LOG.info("Header: {}={}", k, v);
        });
    }

    public void setQuery(Map<String, String> query) {
        query.forEach((k, v) -> {
            builder.addQueryParam(k, v);
            LOG.info("Query: {}={}", k, v);
        });
    }

    public Response sendRequest() {
        RequestSpecification requestSpecification = builder.build();
        return RestAssured
                .given()
                .spec(requestSpecification)
                .request(Method.valueOf(this.method));
    }

    private void setBodyFromFile() {
        if (bodyFromFile != null) {
            body = replaceVarsIfPresent(FileUtil.readBodyFromJsonDir(bodyFromFile));
            builder.setBody(body);
        }
        LOG.info("Body: \n{}", body);
    }
}
