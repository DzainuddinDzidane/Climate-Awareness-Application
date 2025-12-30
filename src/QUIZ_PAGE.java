import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class QUIZ_PAGE {
    private JFrame frame;
    private JTextArea questionArea;
    private JRadioButton[] options;
    private ButtonGroup optionGroup;
    private JButton nextButton;
    private JButton returnButton;
    private JLabel scoreLabel;
    private JLabel livesLabel;
    private List<String> questions;
    private int currentQuestionIndex;
    private int score;
    private int lives;
    private String correctAnswer;

    public QUIZ_PAGE() {
        frame = new JFrame("Quiz Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);

        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 400, 500);
        leftPanel.setBackground(new Color(34, 167, 240));
        leftPanel.setLayout(null);

        JLabel title = new JLabel("Quiz Time!");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(100, 50, 200, 50);

        JLabel motivationalText1 = new JLabel("Test Your Knowledge!");
        motivationalText1.setFont(new Font("SansSerif", Font.BOLD, 18));
        motivationalText1.setForeground(Color.WHITE);
        motivationalText1.setHorizontalAlignment(SwingConstants.CENTER);
        motivationalText1.setBounds(100, 150, 200, 30);

        JLabel motivationalText2 = new JLabel("Good Luck!");
        motivationalText2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        motivationalText2.setForeground(Color.WHITE);
        motivationalText2.setHorizontalAlignment(SwingConstants.CENTER);
        motivationalText2.setBounds(150, 180, 100, 30);

        leftPanel.add(title);
        leftPanel.add(motivationalText1);
        leftPanel.add(motivationalText2);

        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(400, 0, 400, 500);
        rightPanel.setBackground(new Color(44, 62, 80));
        rightPanel.setLayout(null);

        questionArea = new JTextArea(3, 3);
        questionArea.setEditable(false);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setFont(new Font("SansSerif", Font.BOLD, 16));
        questionArea.setForeground(Color.WHITE);
        questionArea.setBackground(new Color(44, 62, 80));
        questionArea.setBounds(50, 30, 300, 60);

        rightPanel.add(questionArea);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1, 10, 10));
        optionsPanel.setBounds(50, 100, 300, 120);
        optionsPanel.setBackground(new Color(44, 62, 80));
        options = new JRadioButton[4];
        optionGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = createStyledRadioButton();
            optionGroup.add(options[i]);
            optionsPanel.add(options[i]);
        }

        rightPanel.add(optionsPanel);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(50, 230, 100, 20);

        livesLabel = new JLabel("Lives: 3");
        livesLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        livesLabel.setForeground(Color.WHITE);
        livesLabel.setBounds(250, 230, 100, 20);

        rightPanel.add(scoreLabel);
        rightPanel.add(livesLabel);

        nextButton = createRoundedButton("Next Question");
        nextButton.setBounds(50, 300, 300, 40);
        nextButton.addActionListener(new NextButtonListener());
        rightPanel.add(nextButton);

        returnButton = createRoundedButton("Return to Homepage");
        returnButton.setBounds(50, 360, 300, 40);
        returnButton.addActionListener(e -> {
            frame.dispose();
            new HOME_PAGE();
        });

        rightPanel.add(returnButton);

        frame.add(leftPanel);
        frame.add(rightPanel);

        lives = 3;
        loadQuestions();
        Collections.shuffle(questions);
        showNextQuestion();

        frame.setVisible(true);
    }

    private void loadQuestions() {
        questions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/questions.txt"))) {
            String line;
            do {
                line = reader.readLine();
                if (line != null) {
                    questions.add(line);
                }
            } while (line != null);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error loading questions: " + e.getMessage());
            System.exit(1);
        }
    }


    private void showNextQuestion() {
        if (lives <= 0) {
            JOptionPane.showMessageDialog(frame, "You have lost all your lives. Quiz Over! Your final score is: " + score);
            frame.dispose();
            new HOME_PAGE();
        }

        if (currentQuestionIndex >= questions.size()) {
            JOptionPane.showMessageDialog(frame, "Quiz Over! Your final score is: " + score);
            frame.dispose();
            new HOME_PAGE();
        }

        String[] parts = questions.get(currentQuestionIndex).split("/");
        if (parts.length < 5) {
            JOptionPane.showMessageDialog(frame, "Invalid question format.");
            System.exit(1);
        }

        questionArea.setText(parts[0]);
        List<String> optionsList = Arrays.asList(parts[1], parts[2], parts[3], parts[4]);
        Collections.shuffle(optionsList);

        for (int i = 0; i < 4; i++) {
            options[i].setText((char) ('A' + i) + ". " + optionsList.get(i));
            if (optionsList.get(i).equals(parts[4])) {
                correctAnswer = options[i].getText();
            }
        }
        currentQuestionIndex++;
    }

    private JRadioButton createStyledRadioButton() {
        JRadioButton radioButton = new JRadioButton();
        radioButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        radioButton.setForeground(Color.WHITE);
        radioButton.setBackground(new Color(44, 62, 80));
        radioButton.setFocusPainted(false);
        return radioButton;
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

    private class NextButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean correct = false;
            for (JRadioButton option : options) {
                if (option.isSelected() && option.getText().equals(correctAnswer)) {
                    score++;
                    correct = true;
                    break;
                }
            }

            if (!correct) {
                lives--;
            }

            scoreLabel.setText("Score: " + score);
            livesLabel.setText("Lives: " + lives);
            optionGroup.clearSelection();
            showNextQuestion();
        }
    }
}
