import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CLIMATE_ACTION_TIPS {

    private final JFrame frame;
    private final Map<String, List<String>> tipsByCategory;
    private final Random random;
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel;

    public CLIMATE_ACTION_TIPS() {
        frame = new JFrame("Climate Action Tips");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


        tipsByCategory = new HashMap<>();
        random = new Random();

        tipsByCategory.put("Personal Lifestyle", List.of(
                "Reduce energy use: Turn off lights and unplug devices when not in use.",
                "Switch to renewable energy: Use solar panels or subscribe to a green energy provider.",
                "Conserve water: Fix leaks and use water-saving appliances.",
                "Reduce waste: Recycle, compost, and avoid single-use plastics."
        ));
        tipsByCategory.put("Transportation", List.of(
                "Use public transit, bike, or walk: Reduce reliance on personal vehicles.",
                "Drive efficiently: Choose a fuel-efficient or electric vehicle.",
                "Carpool or share rides: Reduce emissions by sharing transport.",
                "Switch to an electric vehicle (EV): Consider an EV for reduced emissions."
        ));
        tipsByCategory.put("Advocacy and Community", List.of(
                "Support green policies: Advocate for renewable energy and conservation laws.",
                "Join climate advocacy groups: Get involved with organizations working for climate action.",
                "Participate in local clean-up drives: Help improve your community.",
                "Support sustainable agriculture: Encourage local farms to adopt eco-friendly methods."
        ));
        tipsByCategory.put("Consumer Choices", List.of(
                "Buy sustainably: Choose eco-friendly and durable products.",
                "Support responsible companies: Buy from businesses with environmentally friendly practices.",
                "Buy second-hand items: Reduce waste by purchasing used products.",
                "Opt for reusable over disposable: Use reusable shopping bags, bottles, and containers."
        ));
        tipsByCategory.put("Awareness and Education", List.of(
                "Stay informed: Learn about climate issues and share knowledge with others.",
                "Encourage others: Lead by example to inspire family and friends.",
                "Host educational workshops: Teach others about climate change and ways to reduce their impact.",
                "Promote climate action on social media: Share information about sustainability."
        ));

        JPanel leftPanel = createLeftPanel();
        cardPanel = createCardPanel();

        frame.add(leftPanel);
        frame.add(cardPanel);

        frame.setVisible(true);
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 300, 600);
        leftPanel.setBackground(new Color(34, 167, 240));
        leftPanel.setLayout(null);

        JLabel title = new JLabel("Climate Action Tips");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(20, 50, 260, 50);

        JLabel motivationalText1 = new JLabel("Take Action Today!");
        motivationalText1.setFont(new Font("SansSerif", Font.BOLD, 18));
        motivationalText1.setForeground(Color.WHITE);
        motivationalText1.setHorizontalAlignment(SwingConstants.CENTER);
        motivationalText1.setBounds(20, 150, 260, 30);

        JLabel motivationalText2 = new JLabel("Every Step Counts.");
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

    private JPanel createCardPanel() {
        JPanel cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(300, 0, 600, 600);
        cardPanel.setBackground(new Color(44, 62, 80));

        JPanel mainMenu = createMainMenu();
        cardPanel.add(mainMenu, "MainMenu");

        return cardPanel;
    }

    private JPanel createMainMenu() {
        JPanel mainMenu = new JPanel(new BorderLayout());
        mainMenu.setBackground(new Color(44, 62, 80));

        JLabel heading = new JLabel("Choose a Category:", SwingConstants.CENTER);
        heading.setFont(new Font("SansSerif", Font.BOLD, 18));
        heading.setForeground(Color.WHITE);
        heading.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainMenu.add(heading, BorderLayout.NORTH);

        String[] items = {
                "Personal Lifestyle", "Transportation", "Advocacy and Community",
                "Consumer Choices", "Awareness and Education"
        };
        JComboBox<String> dropdown = new JComboBox<>(items);
        dropdown.setFont(new Font("SansSerif", Font.PLAIN, 14));
        dropdown.addActionListener(e -> {
            String selectedItem = (String) dropdown.getSelectedItem();
            showCategoryPage(selectedItem);
        });

        JPanel dropdownPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        dropdownPanel.setBackground(new Color(44, 62, 80));
        dropdownPanel.add(dropdown);

        mainMenu.add(dropdownPanel, BorderLayout.CENTER);
        return mainMenu;
    }

    private void showCategoryPage(String category) {
        JPanel categoryPage = new JPanel(new BorderLayout());
        categoryPage.setBackground(new Color(44, 62, 80));


        JLabel titleLabel = new JLabel(category + " Tips", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        categoryPage.add(titleLabel, BorderLayout.NORTH);


        JLabel tipLabel = new JLabel("Click 'Next Random Tip' to see a tip!", SwingConstants.CENTER);
        tipLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tipLabel.setForeground(Color.WHITE);
        tipLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        categoryPage.add(tipLabel, BorderLayout.CENTER);


        JButton randomButton = new JButton("Next Random Tip");
        styleButton(randomButton);
        randomButton.addActionListener(e -> {
            String randomTip = getRandomTip(category);
            tipLabel.setText("<html><center>" + randomTip + "</center></html>");
        });

        JButton backButton = new JButton("Go Back");
        styleButton(backButton);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "MainMenu"));

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(44, 62, 80));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(randomButton, gbc);

        gbc.gridy = 1;
        buttonPanel.add(backButton, gbc);

        categoryPage.add(buttonPanel, BorderLayout.SOUTH);

        cardPanel.add(categoryPage, category);
        cardLayout.show(cardPanel, category);
    }


    private String getRandomTip(String category) {
        List<String> tips = tipsByCategory.get(category);
        return tips.get(random.nextInt(tips.size()));
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
