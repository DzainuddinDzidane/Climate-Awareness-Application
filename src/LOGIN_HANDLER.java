import javax.swing.*;

public class LOGIN_HANDLER {
    private boolean loginSuccessful;

    public LOGIN_HANDLER(String usernameInput, char[] passwordInput, JLabel errorLabelLogin, JFrame loginFrame) {
        CREDENTIALS_CHECK reader = new CREDENTIALS_CHECK("src/credentials.txt");

        loginSuccessful = reader.credential(usernameInput, passwordInput);

        if (loginSuccessful) {
            errorLabelLogin.setText("");
            new HOME_PAGE();
            loginFrame.dispose();
        } else {
            errorLabelLogin.setText("Invalid username or password.");
        }
    }
}
