package ru.lanit.at.api.models;

public class RequestModel {

    private String method;
    private String body;
    private String bodyFromFile;
    private String path;

    public RequestModel(String method, String path, String bodyFromFile) {
        this.method = method;
        this.path = path;
        this.bodyFromFile = bodyFromFile;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBodyFromFile() {
        return bodyFromFile;
    }

    public void setBodyFromFile(String bodyFromFile) {
        this.bodyFromFile = bodyFromFile;
    }
}
