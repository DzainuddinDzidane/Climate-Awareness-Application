import javax.swing.*;
import java.io.*;

public class REGISTRATION_HANDLER {
    public REGISTRATION_HANDLER(String user, char[] pass, JLabel errorLabelRegister) {
        String filePath = "src/credentials.txt";

        String passwordString = new String(pass);
        String formattedCredentials = user + "/" + passwordString;

        if (user.isEmpty() || passwordString.isEmpty()) {
            errorLabelRegister.setText("Invalid Username or Password.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean userExists = false;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("/");
                if (parts.length > 0 && parts[0].equals(user)) {
                    userExists = true;
                    break;
                }
            }

            if (userExists) {
                errorLabelRegister.setText("Username already exists.");
            } else {
                appendUserWithNewline(filePath, formattedCredentials);
                errorLabelRegister.setText("Registration successful! Please login!");
            }
        } catch (IOException e) {
            errorLabelRegister.setText("Error processing file: " + e.getMessage());
        }
    }

    private void appendUserWithNewline(String filePath, String newUserInfo) {
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            String action;

            if (file.length() == 0) {
                action = "FILE_EMPTY";
            } else {
                try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
                    raf.seek(file.length() - 1);
                    action = (raf.readByte() == '\n') ? "LINE_EXISTS" : "ADD_NEWLINE";
                }
            }

            switch (action) {
                case "ADD_NEWLINE":
                    writer.newLine();
                    break;
                case "LINE_EXISTS":
                    break;
                case "FILE_EMPTY":
                    break;
                default:
                    throw new IllegalStateException("Unexpected action: " + action);
            }

            writer.write(newUserInfo);
        } catch (IOException e) {
            throw new RuntimeException("Error writing user to file: " + e.getMessage());
        }
    }
}
