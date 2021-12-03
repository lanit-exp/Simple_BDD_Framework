package authorization;

public class AuthValues {

    private String login;
    private String password;
    private Boolean token;

    public AuthValues() {
    }

    public AuthValues(String login, String password, Boolean token) {
        this.login = login;
        this.password = password;
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Boolean isToken() {
        return token;
    }

    @Override
    public String toString() {
        return "AuthValues{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", token=" + token +
                '}';
    }
}
