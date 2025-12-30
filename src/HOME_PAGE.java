import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

public class HOME_PAGE {

    public HOME_PAGE() {
        JFrame homepage = new JFrame("Eco-Mantap");
        homepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homepage.setSize(800, 500);
        homepage.setLocationRelativeTo(null);
        homepage.setLayout(null);
        homepage.setResizable(false);

        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 400, 500);
        leftPanel.setBackground(new Color(34, 167, 240));
        leftPanel.setLayout(null);

        JLabel title = new JLabel("Eco-Mantap");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(100, 50, 200, 50);

        JLabel motivationalText1 = new JLabel("Take Action Today!");
        motivationalText1.setFont(new Font("SansSerif", Font.BOLD, 18));
        motivationalText1.setForeground(Color.WHITE);
        motivationalText1.setHorizontalAlignment(SwingConstants.CENTER);
        motivationalText1.setBounds(100, 150, 200, 30);

        JLabel motivationalText2 = new JLabel("Save the Planet for Tomorrow.");
        motivationalText2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        motivationalText2.setForeground(Color.WHITE);
        motivationalText2.setHorizontalAlignment(SwingConstants.CENTER);
        motivationalText2.setBounds(60, 180, 280, 30);

        JLabel lastResultLabel = new JLabel("Last Carbon Footprint:");
        lastResultLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        lastResultLabel.setForeground(Color.WHITE);
        lastResultLabel.setBounds(60, 250, 280, 30);

        JLabel lastResultValue = new JLabel(CARBON_RESULT_GEN.lastResult + " kg CO₂");
        lastResultValue.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lastResultValue.setForeground(Color.WHITE);
        lastResultValue.setHorizontalAlignment(SwingConstants.CENTER);
        lastResultValue.setBounds(100, 280, 200, 30);

        leftPanel.add(title);
        leftPanel.add(motivationalText1);
        leftPanel.add(motivationalText2);
        leftPanel.add(lastResultLabel);
        leftPanel.add(lastResultValue);

        JLabel progressLabel = new JLabel("Today's Climate Action Progress:");
        progressLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        progressLabel.setForeground(Color.WHITE);
        progressLabel.setBounds(60, 310, 280, 30);

        JProgressBar progressBar;
        progressBar = new JProgressBar(0, 100) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.BLACK);
                String text = getString();
                int width = getWidth();
                int height = getHeight();
                FontMetrics fontMetrics = g2d.getFontMetrics(getFont());
                int stringWidth = fontMetrics.stringWidth(text);
                int stringHeight = fontMetrics.getAscent();
                g2d.drawString(text, (width - stringWidth) / 2, (height + stringHeight) / 2 - 2);
            }
        };
        progressBar.setBounds(60, 350, 280, 30);
        progressBar.setValue(CLIMATE_ACTION_TRACKER.progress);
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("SansSerif", Font.BOLD, 14));
        progressBar.setForeground(new Color(255, 255, 255));
        progressBar.setBackground(new Color(30, 139, 195));
        leftPanel.add(progressLabel);
        leftPanel.add(progressBar);

        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(400, 0, 400, 500);
        rightPanel.setBackground(new Color(44, 62, 80));
        rightPanel.setLayout(null);

        String username = CREDENTIALS_CHECK.loggedInUsername != null ? CREDENTIALS_CHECK.loggedInUsername : "User";
        JLabel greetingLabel = new JLabel("Welcome, " + username + ".");
        greetingLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        greetingLabel.setForeground(Color.WHITE);
        greetingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        greetingLabel.setBounds(200, 10, 180, 30);

        JButton updatesButton = createAnimatedButton("View Updates", 50);
        JButton adminPanelButton = createAnimatedButton("Admin Panel", 110);
        JButton environmentalQuizButton = createAnimatedButton("Environmental Quiz", 170);
        JButton simTipsButton = createAnimatedButton("Early-Warning SimTips", 230);
        JButton footprintCalculatorButton = createAnimatedButton("Carbon Footprint Calculator", 290);
        JButton actionTipsButton = createAnimatedButton("Climate Action Tips", 350);
        JButton actionTrackerButton = createAnimatedButton("Climate Action Tracker", 410);

        adminPanelButton.setVisible(CREDENTIALS_CHECK.isAdmin);

        rightPanel.add(greetingLabel);
        rightPanel.add(updatesButton);
        rightPanel.add(adminPanelButton);
        rightPanel.add(environmentalQuizButton);
        rightPanel.add(simTipsButton);
        rightPanel.add(footprintCalculatorButton);
        rightPanel.add(actionTipsButton);
        rightPanel.add(actionTrackerButton);


        homepage.add(leftPanel);
        homepage.add(rightPanel);

        updatesButton.addActionListener(e -> {
            new UPDATE_PAGE();
            homepage.dispose();
        });
        adminPanelButton.addActionListener(e -> new ADMIN_PANEL(homepage));
        environmentalQuizButton.addActionListener(e -> {
            new QUIZ_PAGE();
            homepage.dispose();
        });
        simTipsButton.addActionListener(e -> {
            new PRE_DISASTER_TIPS();
            homepage.dispose();
        });
        footprintCalculatorButton.addActionListener(e -> {
            new CARBON_CALCULATOR();
            homepage.dispose();
        });
        actionTipsButton.addActionListener(e -> {
            new CLIMATE_ACTION_TIPS();
            homepage.dispose();
        });
        actionTrackerButton.addActionListener(e -> {
            new CLIMATE_ACTION_TRACKER();
            homepage.dispose();

        });

        homepage.setVisible(true);
    }

    private JButton createAnimatedButton(String text, int yPosition) {
        JButton button = new JButton(text);
        button.setBounds(50, yPosition, 300, 40);
        button.setFont(new Font("SansSerif", Font.PLAIN, 14));
        button.setBackground(new Color(30, 139, 195));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(30, 139, 195), 1, true));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

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
