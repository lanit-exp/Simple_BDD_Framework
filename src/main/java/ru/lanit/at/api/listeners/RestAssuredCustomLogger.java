package ru.lanit.at.api.listeners;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.UrlDecoder;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.nio.charset.Charset;

import static ru.lanit.at.utils.ContextHolder.replaceVarsIfPresent;


/**
 * Created by Sorokin Boris on 21.01.2022.
 */
public class RestAssuredCustomLogger implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec
            , FilterableResponseSpecification responseSpec
            , FilterContext context) {
        Response response = context.next(requestSpec, responseSpec);

        String uri = UrlDecoder.urlDecode(requestSpec.getURI(),
                Charset.forName(requestSpec
                        .getConfig()
                        .getEncoderConfig()
                        .defaultQueryParameterCharset()),
                true);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("------------- REQUEST -------------\n");
        stringBuilder.append("URL: ")
                .append(uri).append("\n")
                .append("Method: ").append(requestSpec.getMethod()).append("\n");
        requestSpec.getHeaders().asList().forEach(header -> stringBuilder
                .append("Header: ")
                .append(replaceVarsIfPresent(header.getName()))
                .append("=")
                .append(replaceVarsIfPresent(header.getValue()))
                .append("\n"));
        requestSpec.getQueryParams().forEach((k, v) -> stringBuilder
                .append("Query: ")
                .append(replaceVarsIfPresent(k))
                .append("=")
                .append(replaceVarsIfPresent(v))
                .append("\n"));
        stringBuilder.append("Request Body: \n")
                .append(replaceVarsIfPresent(requestSpec.getBody()))
                .append("\n");
        stringBuilder.append("------------- RESPONSE -------------\n");
        stringBuilder.append("Status code: ")
                .append(response.statusCode())
                .append("\n");
        stringBuilder.append("Response Body: \n")
                .append(response.getBody().asPrettyString());
        System.out.println(stringBuilder);
        return response;
    }
}
