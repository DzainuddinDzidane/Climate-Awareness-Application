import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class REGISTRATION_PAGE {
    private static final int TEXT_FIELD_WIDTH = 25;

    public REGISTRATION_PAGE() {
        IMAGE_DESIGN imageDesign = new IMAGE_DESIGN();
        JFrame registrationFrame = new JFrame("Registration Page");
        registrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registrationFrame.setSize(720, 400);
        registrationFrame.setLocationRelativeTo(null);
        registrationFrame.setLayout(null);
        registrationFrame.setResizable(false);

        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(360, 0, 360, 400);
        rightPanel.setBackground(new Color(34, 167, 240));
        rightPanel.setLayout(null);

        BufferedImage earthImage = null;
        try {
            earthImage = ImageIO.read(new File("src/earth-icon.png"));
            earthImage = imageDesign.resizeImageWithHighQuality(earthImage, 220, 220);
            imageDesign.recolorImageToWhite(earthImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageIcon earthIcon = new ImageIcon(earthImage);
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(earthIcon);
        iconLabel.setBounds(70, 30, 220, 220);

        JLabel motivationalText1 = new JLabel("Join the journey!");
        motivationalText1.setFont(new Font("SansSerif", Font.BOLD, 18));
        motivationalText1.setForeground(Color.WHITE);
        motivationalText1.setHorizontalAlignment(SwingConstants.CENTER);
        motivationalText1.setBounds(60, 240, 240, 30);

        JLabel motivationalText2 = new JLabel("Make a difference!");
        motivationalText2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        motivationalText2.setForeground(Color.WHITE);
        motivationalText2.setHorizontalAlignment(SwingConstants.CENTER);
        motivationalText2.setBounds(120, 270, 120, 30);

        rightPanel.add(iconLabel);
        rightPanel.add(motivationalText1);
        rightPanel.add(motivationalText2);

        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 360, 400);
        leftPanel.setBackground(new Color(44, 62, 80));
        leftPanel.setLayout(null);

        JLabel title = new JLabel("Register");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(90, 30, 180, 30);

        JLabel usernameLabel = new JLabel("USERNAME");
        usernameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(50, 80, 100, 30);

        JTextField usernameInput = new JTextField(TEXT_FIELD_WIDTH);
        usernameInput.setBounds(50, 110, 260, 30);
        usernameInput.setOpaque(false);
        usernameInput.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        usernameInput.setForeground(Color.WHITE);
        usernameInput.setFont(new Font("SansSerif", Font.PLAIN, 14));
        usernameInput.setCaretColor(Color.WHITE);

        JLabel usernameErrorLabel = new JLabel();
        usernameErrorLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        usernameErrorLabel.setForeground(Color.RED);
        usernameErrorLabel.setBounds(50, 140, 260, 20);

        JLabel passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(50, 170, 100, 30);

        JPasswordField passwordInput = new JPasswordField(TEXT_FIELD_WIDTH);
        passwordInput.setBounds(50, 200, 260, 30);
        passwordInput.setOpaque(false);
        passwordInput.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        passwordInput.setForeground(Color.WHITE);
        passwordInput.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordInput.setCaretColor(Color.WHITE);

        JLabel passwordErrorLabel = new JLabel();
        passwordErrorLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        passwordErrorLabel.setForeground(Color.RED);
        passwordErrorLabel.setBounds(50, 230, 260, 20);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(50, 270, 120, 30);
        registerButton.setBackground(new Color(30, 139, 195));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);

        JButton backButton = new JButton("Back");
        backButton.setBounds(190, 270, 120, 30);
        backButton.setBackground(new Color(34, 49, 63));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);

        JLabel registrationErrorLabel = new JLabel();
        registrationErrorLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        registrationErrorLabel.setForeground(Color.RED);
        registrationErrorLabel.setBounds(50, 320, 260, 20); // Moved below the buttons

        leftPanel.add(title);
        leftPanel.add(usernameLabel);
        leftPanel.add(usernameInput);
        leftPanel.add(usernameErrorLabel);
        leftPanel.add(passwordLabel);
        leftPanel.add(passwordInput);
        leftPanel.add(passwordErrorLabel);
        leftPanel.add(registerButton);
        leftPanel.add(backButton);
        leftPanel.add(registrationErrorLabel);

        registrationFrame.add(leftPanel);
        registrationFrame.add(rightPanel);

        new FOCUS_HANDLER(usernameInput, usernameErrorLabel);
        new FOCUS_HANDLER(passwordInput, passwordErrorLabel);

        registerButton.addActionListener(e -> {
            String username = usernameInput.getText().trim();
            char[] password = passwordInput.getPassword();

            if (username.isEmpty()) {
                usernameErrorLabel.setText("Username cannot be empty");
            } else {
                usernameErrorLabel.setText("");
            }

            if (password.length == 0) {
                passwordErrorLabel.setText("Password cannot be empty");
            } else {
                passwordErrorLabel.setText("");
            }

            if (!username.isEmpty() && password.length > 0) {
                new REGISTRATION_HANDLER(username, password, registrationErrorLabel);
            }
        });

        backButton.addActionListener(e -> {
            registrationFrame.dispose();
            new LOGIN_PAGE();
        });

        registrationFrame.setVisible(true);
    }
}
