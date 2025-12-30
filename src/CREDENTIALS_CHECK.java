import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CREDENTIALS_CHECK {
    private final String filePath;
    public static boolean isAdmin = false;
    public static String loggedInUsername = null;

    public CREDENTIALS_CHECK(String filePath) {
        this.filePath = filePath;
    }

    public boolean credential(String username, char[] password) {
        boolean isValid = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("/");
                if (parts.length == 2) {
                    String savedUsername = parts[0].trim();
                    String savedPassword = parts[1].trim();

                    String passwordString = new String(password);

                    if (username.equals("Admin") && passwordString.equals("1234")) {
                        isValid = true;
                        isAdmin = true;
                        loggedInUsername = username;
                        break;
                    }
                    else if (savedUsername.equals(username) && savedPassword.equals(passwordString)) {
                        isValid = true;
                        isAdmin = false;
                        loggedInUsername = username;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isValid;
    }
}
