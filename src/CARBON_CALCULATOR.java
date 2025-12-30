import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CARBON_CALCULATOR implements ActionListener {
    private JFrame frame;
    private JTextField electricityBill, gasBill, oilBill, car, flightNumberOne, flightNumberTwo;
    private JRadioButton yes1, no1, yes2, no2;
    private ButtonGroup group1, group2;
    private JButton submitButton, backButton;

    public CARBON_CALCULATOR() {
        frame = new JFrame("Carbon Footprint Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);

        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 400, 700);
        leftPanel.setBackground(new Color(34, 167, 240));
        leftPanel.setLayout(null);

        JLabel title = new JLabel("Carbon Calculator");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(50, 50, 300, 50);

        JLabel motivationalText1 = new JLabel("Track Your Impact!");
        motivationalText1.setFont(new Font("SansSerif", Font.BOLD, 18));
        motivationalText1.setForeground(Color.WHITE);
        motivationalText1.setHorizontalAlignment(SwingConstants.CENTER);
        motivationalText1.setBounds(50, 150, 300, 30);

        JLabel motivationalText2 = new JLabel("Make Sustainable Choices.");
        motivationalText2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        motivationalText2.setForeground(Color.WHITE);
        motivationalText2.setHorizontalAlignment(SwingConstants.CENTER);
        motivationalText2.setBounds(50, 180, 300, 30);

        leftPanel.add(title);
        leftPanel.add(motivationalText1);
        leftPanel.add(motivationalText2);

        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(400, 0, 500, 700);
        rightPanel.setBackground(new Color(44, 62, 80));
        rightPanel.setLayout(null);

        JLabel formTitle = new JLabel("Enter Your Details Below:");
        formTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        formTitle.setForeground(Color.WHITE);
        formTitle.setBounds(50, 20, 400, 30);
        rightPanel.add(formTitle);

        addLabeledField(rightPanel, "Monthly Electric Bill ($):", 70, electricityBill = createStyledTextField(70));
        addLabeledField(rightPanel, "Monthly Gas Bill ($):", 140, gasBill = createStyledTextField(140));
        addLabeledField(rightPanel, "Monthly Oil Bill ($):", 210, oilBill = createStyledTextField(210));
        addLabeledField(rightPanel, "Yearly Car Mileage:", 280, car = createStyledTextField(280));
        addLabeledField(rightPanel, "Flights Taken (<4hrs):", 350, flightNumberOne = createStyledTextField(350));
        addLabeledField(rightPanel, "Flights Taken (>4hrs):", 420, flightNumberTwo = createStyledTextField(420));

        JLabel recycleLabel1 = new JLabel("Recycle Newspaper:");
        recycleLabel1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        recycleLabel1.setForeground(Color.WHITE);
        recycleLabel1.setBounds(50, 490, 150, 30);
        rightPanel.add(recycleLabel1);

        yes1 = createStyledRadioButton("Yes", 230, 490);
        no1 = createStyledRadioButton("No", 320, 490);
        group1 = new ButtonGroup();
        group1.add(yes1);
        group1.add(no1);
        rightPanel.add(yes1);
        rightPanel.add(no1);

        JLabel recycleLabel2 = new JLabel("Recycle Aluminum:");
        recycleLabel2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        recycleLabel2.setForeground(Color.WHITE);
        recycleLabel2.setBounds(50, 530, 150, 30);
        rightPanel.add(recycleLabel2);

        yes2 = createStyledRadioButton("Yes", 230, 530);
        no2 = createStyledRadioButton("No", 320, 530);
        group2 = new ButtonGroup();
        group2.add(yes2);
        group2.add(no2);
        rightPanel.add(yes2);
        rightPanel.add(no2);

        submitButton = createRoundedButton("Submit", 580);
        submitButton.addActionListener(this);
        rightPanel.add(submitButton);

        backButton = createRoundedButton("Back", 630);
        backButton.addActionListener(e -> {
            frame.dispose();
            new HOME_PAGE();
        });
        rightPanel.add(backButton);

        frame.add(leftPanel);
        frame.add(rightPanel);
        frame.setVisible(true);
    }

    private void addLabeledField(JPanel panel, String labelText, int yPosition, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label.setForeground(Color.WHITE);
        label.setBounds(50, yPosition, 200, 30);
        panel.add(label);
        textField.setBounds(250, yPosition, 200, 30);
        panel.add(textField);
    }

    private JTextField createStyledTextField(int yPosition) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textField.setForeground(Color.WHITE);
        textField.setBackground(new Color(44, 62, 80));
        textField.setCaretColor(Color.WHITE);
        textField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        return textField;
    }

    private JRadioButton createStyledRadioButton(String text, int xPosition, int yPosition) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setBounds(xPosition, yPosition, 80, 30);
        radioButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        radioButton.setForeground(Color.WHITE);
        radioButton.setBackground(new Color(44, 62, 80));
        radioButton.setFocusPainted(false);
        return radioButton;
    }

    private JButton createRoundedButton(String text, int yPosition) {
        JButton button = new JButton(text);
        button.setBounds(50, yPosition, 400, 40);
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

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            double ebill = Double.parseDouble(electricityBill.getText());
            double gbill = Double.parseDouble(gasBill.getText());
            double oil = Double.parseDouble(oilBill.getText());
            double mileage = Double.parseDouble(car.getText());
            double f1 = Double.parseDouble(flightNumberOne.getText());
            double f2 = Double.parseDouble(flightNumberTwo.getText());
            boolean recycleNewspaper = yes1.isSelected();
            boolean recycleAluminum = yes2.isSelected();

            new CARBON_RESULT_GEN(ebill, gbill, oil, mileage, f1, f2, recycleNewspaper, recycleAluminum);
        }
    }
}
