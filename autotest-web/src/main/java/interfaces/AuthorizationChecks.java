package interfaces;

public interface AuthorizationChecks {

    void checkWelcomeHeaderText(String username);

    void checkAppearField();

    void checkDisappearField();

    void checkAppearCheckbox();

    void checkErrorMessageText();

    void checkPopUpErrorMessageText();
}
