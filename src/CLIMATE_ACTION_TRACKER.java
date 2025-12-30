import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CLIMATE_ACTION_TRACKER {
    private JFrame frame;
    public JProgressBar progressBar;
    public static int progress = 0;
    private JButton[] actionButtons;
    private static boolean[] buttonState;

    public CLIMATE_ACTION_TRACKER() {
        frame = new JFrame("Climate Action Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);

        String[] actions = {
                "Plant a Tree", "Carpool Today", "Turn Off Unused Lights",
                "Use Reusable Bags", "Recycle Plastic", "Switch to LED Bulbs",
                "Use Public Transport", "Avoid Single-Use Plastics"
        };
        if (buttonState == null) {
            buttonState = new boolean[actions.length];
            for (int i = 0; i < buttonState.length; i++) {
                buttonState[i] = true;
            }
        }

        actionButtons = new JButton[actions.length];

        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 400, 600);
        leftPanel.setBackground(new Color(34, 167, 240));
        leftPanel.setLayout(null);

        JLabel title = new JLabel("Climate Action Tracker");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(50, 50, 300, 50);

        JLabel motivationalText1 = new JLabel("Take Action for Change!");
        motivationalText1.setFont(new Font("SansSerif", Font.BOLD, 18));
        motivationalText1.setForeground(Color.WHITE);
        motivationalText1.setHorizontalAlignment(SwingConstants.CENTER);
        motivationalText1.setBounds(50, 150, 300, 30);

        JLabel motivationalText2 = new JLabel("Every Step Counts.");
        motivationalText2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        motivationalText2.setForeground(Color.WHITE);
        motivationalText2.setHorizontalAlignment(SwingConstants.CENTER);
        motivationalText2.setBounds(50, 180,    300, 30);

        leftPanel.add(title);
        leftPanel.add(motivationalText1);
        leftPanel.add(motivationalText2);

        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(400, 0, 500, 600);
        rightPanel.setBackground(new Color(44, 62, 80));
        rightPanel.setLayout(null);

        JLabel instructions = new JLabel("Select Actions You Completed:");
        instructions.setFont(new Font("SansSerif", Font.BOLD, 18));
        instructions.setForeground(Color.WHITE);
        instructions.setBounds(50, 20, 400, 30);
        rightPanel.add(instructions);

        int[] increments = {13, 13, 13, 13, 12, 12, 12, 12};

        for (int i = 0; i < actions.length; i++) {
            createActionButton(actions[i], 70 + (i * 40), rightPanel, increments[i], i);
        }

        JLabel progressLabel = new JLabel("Daily Progress:");
        progressLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        progressLabel.setForeground(Color.WHITE);
        progressLabel.setBounds(50, 380, 400, 30);
        rightPanel.add(progressLabel);

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
        progressBar.setBounds(50, 420, 400, 30);
        progressBar.setValue(progress);
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("SansSerif", Font.BOLD, 14));
        progressBar.setForeground(new Color(255, 255, 255));
        progressBar.setBackground(new Color(30, 139, 195));
        rightPanel.add(progressBar);

        JButton backButton = new JButton("Back to Home");
        backButton.setBounds(150, 500, 200, 40);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBackground(new Color(30, 139, 195));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                backButton.setBackground(new Color(41, 128, 185));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                backButton.setBackground(new Color(30, 139, 195));
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            new HOME_PAGE();
        });

        rightPanel.add(backButton);

        frame.add(leftPanel);
        frame.add(rightPanel);
        frame.setVisible(true);
    }

    private void createActionButton(String text, int yPosition, JPanel panel, int increment, int index) {
        JButton button = new JButton(text);
        button.setBounds(50, yPosition, 400, 30);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setBackground(new Color(30, 139, 195));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(30, 139, 195), 2, true),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        if (!buttonState[index]) {
            button.setEnabled(false);
        }

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (button.isEnabled()) {
                    button.setBackground(new Color(41, 128, 185));
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (button.isEnabled()) {
                    button.setBackground(new Color(30, 139, 195));
                }
            }
        });

        button.addActionListener(e -> {
            if (buttonState[index]) {
                incrementProgress(increment);
                button.setEnabled(false);
                buttonState[index] = false;
            }
        });

        actionButtons[index] = button;
        panel.add(button);
    }

    private void incrementProgress(int amount) {
        int target = progress + amount;
        Timer timer = new Timer(10, e -> {
            if (progress < target && progress <= 100) {
                progress++;
                progressBar.setValue(progress);
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }
}
