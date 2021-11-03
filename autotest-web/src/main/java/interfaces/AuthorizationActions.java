package interfaces;

public interface AuthorizationActions {

    void openUrl(String url);

    void clickOnCheckbox(String checkbox);

    void fillField(String field, String value);

    String getToken(String username, String password);

    void clickSignInButton();
}
