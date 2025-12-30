import javax.swing.*;
import java.awt.*;

public class UPDATE_PAGE {

    public UPDATE_PAGE() {
        JFrame updatePage = new JFrame("Updates");
        updatePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updatePage.setSize(900, 600);
        updatePage.setLocationRelativeTo(null);
        updatePage.setLayout(null);
        updatePage.setResizable(false);

        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 400, 600);
        leftPanel.setBackground(new Color(34, 167, 240));
        leftPanel.setLayout(null);

        JLabel title = new JLabel("Updates");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(50, 50, 300, 50);

        JLabel motivationalText1 = new JLabel("Stay Informed!");
        motivationalText1.setFont(new Font("SansSerif", Font.BOLD, 18));
        motivationalText1.setForeground(Color.WHITE);
        motivationalText1.setHorizontalAlignment(SwingConstants.CENTER);
        motivationalText1.setBounds(50, 150, 300, 30);

        JLabel motivationalText2 = new JLabel("Check the latest updates.");
        motivationalText2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        motivationalText2.setForeground(Color.WHITE);
        motivationalText2.setHorizontalAlignment(SwingConstants.CENTER);
        motivationalText2.setBounds(50, 180, 300, 30);

        leftPanel.add(title);
        leftPanel.add(motivationalText1);
        leftPanel.add(motivationalText2);

        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(400, 0, 500, 600);
        rightPanel.setBackground(new Color(44, 62, 80));
        rightPanel.setLayout(null);

        JLabel updatesTitle = new JLabel("Recent Updates:");
        updatesTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        updatesTitle.setForeground(Color.WHITE);
        updatesTitle.setBounds(50, 20, 400, 30);
        rightPanel.add(updatesTitle);

        JPanel updatesContainer = new JPanel();
        updatesContainer.setLayout(new GridLayout(5, 1, 10, 10));
        updatesContainer.setBounds(50, 70, 400, 400);
        updatesContainer.setBackground(new Color(44, 62, 80));

        String[] updateTitles = {
                "Update 1", "Update 2", "Update 3", "Update 4", "Update 5"
        };
        String[] updateContents = {
                "Added Environmental Quiz to engage users in learning.",
                "Added Disaster Tips for emergency preparedness.",
                "Added Carbon Footprint Calculator to track emissions.",
                "Added Climate Action Tips for impactful actions.",
                "Added Action Tracker to monitor progress daily."
        };

        Font titleFont = new Font("SansSerif", Font.BOLD, 16);
        Font contentFont = new Font("SansSerif", Font.PLAIN, 14);

        for (int i = 0; i < updateTitles.length; i++) {
            JPanel updatePanel = new JPanel();
            updatePanel.setLayout(new BorderLayout());
            updatePanel.setBackground(new Color(44, 62, 80));

            JLabel updateTitle = new JLabel(updateTitles[i]);
            updateTitle.setFont(titleFont);
            updateTitle.setForeground(Color.WHITE);

            JLabel updateContent = new JLabel("<html>" + updateContents[i] + "</html>");
            updateContent.setFont(contentFont);
            updateContent.setForeground(Color.LIGHT_GRAY);

            updatePanel.add(updateTitle, BorderLayout.NORTH);
            updatePanel.add(updateContent, BorderLayout.CENTER);
            updatesContainer.add(updatePanel);
        }

        rightPanel.add(updatesContainer);

        JButton backButton = new JButton("Back to Homepage");
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
            updatePage.dispose();
            new HOME_PAGE();
        });

        rightPanel.add(backButton);

        updatePage.add(leftPanel);
        updatePage.add(rightPanel);
        updatePage.setVisible(true);
    }
}
