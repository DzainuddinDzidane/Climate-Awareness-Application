import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LOGIN_PAGE {
    private static final int TEXT_FIELD_WIDTH = 25;

    public LOGIN_PAGE() {
        IMAGE_DESIGN imageDesign = new IMAGE_DESIGN();
        JFrame loginFrame = new JFrame("Login Page");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(720, 400);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setLayout(null);
        loginFrame.setResizable(false);

        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 360, 400);
        leftPanel.setBackground(new Color(34, 167, 240));
        leftPanel.setLayout(null);

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

        JLabel motivationalText1 = new JLabel("ECO-MANTAP");
        motivationalText1.setFont(new Font("SansSerif", Font.BOLD, 18));
        motivationalText1.setForeground(Color.WHITE);
        motivationalText1.setHorizontalAlignment(SwingConstants.CENTER);
        motivationalText1.setBounds(60, 240, 240, 30);

        JLabel motivationalText2 = new JLabel("For a Better Earth!");
        motivationalText2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        motivationalText2.setForeground(Color.WHITE);
        motivationalText2.setHorizontalAlignment(SwingConstants.CENTER);
        motivationalText2.setBounds(120, 270, 120, 30);

        leftPanel.add(iconLabel);
        leftPanel.add(motivationalText1);
        leftPanel.add(motivationalText2);

        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(360, 0, 360, 400);
        rightPanel.setBackground(new Color(44, 62, 80));
        rightPanel.setLayout(null);

        JLabel title = new JLabel("Sign In");
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

        JLabel errorLabelUsername = new JLabel();
        errorLabelUsername.setFont(new Font("SansSerif", Font.PLAIN, 12));
        errorLabelUsername.setForeground(Color.RED);
        errorLabelUsername.setBounds(50, 140, 260, 20);

        JLabel passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(50, 150, 100, 30);

        JPasswordField passwordInput = new JPasswordField(TEXT_FIELD_WIDTH);
        passwordInput.setBounds(50, 180, 260, 30);
        passwordInput.setOpaque(false);
        passwordInput.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        passwordInput.setForeground(Color.WHITE);
        passwordInput.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordInput.setCaretColor(Color.WHITE);

        JLabel errorLabelPassword = new JLabel();
        errorLabelPassword.setFont(new Font("SansSerif", Font.PLAIN, 12));
        errorLabelPassword.setForeground(Color.RED);
        errorLabelPassword.setBounds(50, 210, 260, 20);

        JLabel errorLabelLogin = new JLabel();
        errorLabelLogin.setFont(new Font("SansSerif", Font.PLAIN, 12));
        errorLabelLogin.setForeground(Color.RED);
        errorLabelLogin.setBounds(50, 320, 260, 20);

        new FOCUS_HANDLER(usernameInput, errorLabelUsername);
        new FOCUS_HANDLER(passwordInput, errorLabelPassword);

        JButton signInButton = createRoundedButton("Sign In");
        signInButton.setBounds(50, 270, 120, 40);

        signInButton.addActionListener(e -> {
            String username = usernameInput.getText().trim();
            char[] password = passwordInput.getPassword();
            new LOGIN_HANDLER(username, password, errorLabelLogin, loginFrame);
        });

        JButton registerButton = createRoundedButton("Register");
        registerButton.setBounds(190, 270, 120, 40);

        registerButton.addActionListener(e -> {
            loginFrame.dispose();
            new REGISTRATION_PAGE();
        });

        rightPanel.add(title);
        rightPanel.add(usernameLabel);
        rightPanel.add(usernameInput);
        rightPanel.add(errorLabelUsername);
        rightPanel.add(passwordLabel);
        rightPanel.add(passwordInput);
        rightPanel.add(errorLabelPassword);
        rightPanel.add(signInButton);
        rightPanel.add(registerButton);
        rightPanel.add(errorLabelLogin);

        loginFrame.add(leftPanel);
        loginFrame.add(rightPanel);

        loginFrame.setVisible(true);
    }

    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(new Color(30, 139, 195));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(30, 139, 195), 2, true),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(41, 128, 185));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(30, 139, 195));
            }
        });

        return button;
    }

}
