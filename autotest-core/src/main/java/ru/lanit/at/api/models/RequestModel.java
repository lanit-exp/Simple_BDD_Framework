package ru.lanit.at.api.models;

public class RequestModel {

    private String method;
    private String body;
    private String bodyFromFile;
    private String path;

    public RequestModel(String method, String path, String bodyFromFile, String body) {
        this.method = method;
        this.path = path;
        this.bodyFromFile = bodyFromFile;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getBody() {
        return body;
    }

    public String getPath() {
        return path;
    }

    public String getBodyFromFile() {
        return bodyFromFile;
    }

    @Override
    public String toString() {
        return printBody();
    }

    private String printBody() {
        String text = body;
        if (body == null) {
            text = bodyFromFile;
        }
        return "\nЗапрос:\n" +
                "Method: " + method + "\n" +
                "Path: " + path + "\n" +
                "Body: " + text;
    }
}
