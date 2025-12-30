import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PRE_DISASTER_TIPS {
    private JFrame frame;
    private final Map<String, List<String>> disasterTips;

    public PRE_DISASTER_TIPS() {
        frame = new JFrame("Pre-Disaster Tips");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        disasterTips = new HashMap<>();
        populateDisasterTips();

        JPanel leftPanel = createLeftPanel();
        JPanel rightPanel = createRightPanel();

        frame.add(leftPanel);
        frame.add(rightPanel);
        frame.setVisible(true);
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 300, 600);
        leftPanel.setBackground(new Color(34, 167, 240));
        leftPanel.setLayout(null);

        JLabel title = new JLabel("Pre-Disaster Tips");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(20, 50, 260, 50);

        JLabel motivationalText1 = new JLabel("Stay Prepared!");
        motivationalText1.setFont(new Font("SansSerif", Font.BOLD, 18));
        motivationalText1.setForeground(Color.WHITE);
        motivationalText1.setHorizontalAlignment(SwingConstants.CENTER);
        motivationalText1.setBounds(20, 150, 260, 30);

        JLabel motivationalText2 = new JLabel("Plan Ahead for Safety.");
        motivationalText2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        motivationalText2.setForeground(Color.WHITE);
        motivationalText2.setHorizontalAlignment(SwingConstants.CENTER);
        motivationalText2.setBounds(20, 180, 260, 30);

        JButton backButton = new JButton("Back to Home");
        backButton.setBounds(50, 500, 200, 40);
        styleButton(backButton);
        backButton.addActionListener(e -> {
            frame.dispose();
            new HOME_PAGE();
        });

        leftPanel.add(title);
        leftPanel.add(motivationalText1);
        leftPanel.add(motivationalText2);
        leftPanel.add(backButton);

        return leftPanel;
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(300, 0, 600, 600);
        rightPanel.setBackground(new Color(44, 62, 80));
        rightPanel.setLayout(null);

        JLabel dropdownLabel = new JLabel("Select a Disaster:");
        dropdownLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        dropdownLabel.setForeground(Color.WHITE);
        dropdownLabel.setBounds(50, 30, 500, 30);

        JComboBox<String> disasterDropdown = new JComboBox<>(disasterTips.keySet().toArray(new String[0]));
        disasterDropdown.setFont(new Font("SansSerif", Font.PLAIN, 14));
        disasterDropdown.setBounds(50, 80, 500, 30);

        JTextArea tipArea = new JTextArea();
        tipArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tipArea.setForeground(Color.WHITE);
        tipArea.setBackground(new Color(44, 62, 80));
        tipArea.setEditable(false);
        tipArea.setLineWrap(true);
        tipArea.setWrapStyleWord(true);

        JScrollPane tipScrollPane = new JScrollPane(tipArea);
        tipScrollPane.setBounds(50, 130, 500, 380);
        tipScrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        disasterDropdown.addActionListener(e -> {
            String selectedDisaster = (String) disasterDropdown.getSelectedItem();
            String tips = String.join("\n\n", disasterTips.getOrDefault(selectedDisaster, List.of("No tips available.")));
            tipArea.setText(tips);
        });

        rightPanel.add(dropdownLabel);
        rightPanel.add(disasterDropdown);
        rightPanel.add(tipScrollPane);

        return rightPanel;
    }

    private void populateDisasterTips() {
        disasterTips.put("Earthquake", List.of(
                "Secure heavy furniture to walls.",
                "Identify safe spots in your home, such as under sturdy tables.",
                "Prepare an emergency kit with food, water, and first aid."
        ));
        disasterTips.put("Flood", List.of(
                "Elevate appliances and utilities in flood-prone areas.",
                "Have a plan to evacuate to higher ground if needed.",
                "Keep important documents in waterproof containers."
        ));
        disasterTips.put("Hurricane", List.of(
                "Board up windows to prevent breakage.",
                "Secure outdoor objects that could become projectiles.",
                "Stock up on emergency supplies and medications."
        ));
        disasterTips.put("Tornado", List.of(
                "Identify a safe room or storm shelter in your home.",
                "Remove outdoor items that could become projectiles.",
                "Keep a weather radio for updates."
        ));
        disasterTips.put("Wildfire", List.of(
                "Clear flammable vegetation around your property.",
                "Have an evacuation plan and practice it with your family.",
                "Prepare a go-bag with essentials for quick evacuation."
        ));
        disasterTips.put("Blizzard", List.of(
                "Stock up on warm clothing and blankets.",
                "Have extra food and water stored for emergencies.",
                "Keep a battery-powered radio for weather updates."
        ));
        disasterTips.put("Heatwave", List.of(
                "Stay hydrated and avoid outdoor activities during peak heat.",
                "Use fans and air conditioning to stay cool.",
                "Check on vulnerable neighbors and family members."
        ));
        disasterTips.put("Drought", List.of(
                "Conserve water by fixing leaks and using water-saving appliances.",
                "Limit outdoor watering to early mornings or late evenings.",
                "Store rainwater for gardening and other needs."
        ));
        disasterTips.put("Volcanic Eruption", List.of(
                "Prepare masks to avoid inhaling ash.",
                "Have an evacuation plan ready.",
                "Stay indoors and seal windows and doors to prevent ash entry."
        ));
        disasterTips.put("Tsunami", List.of(
                "Know evacuation routes to higher ground.",
                "Never ignore tsunami warnings or sirens.",
                "Stay informed about local emergency procedures."
        ));
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(new Color(30, 139, 195));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(41, 128, 185));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(30, 139, 195));
            }
        });
    }
}
