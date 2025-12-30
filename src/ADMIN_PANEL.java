import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ADMIN_PANEL {

    private JFrame adminFrame;
    private JTextArea userList;
    private JTextField searchField;
    private ArrayList<String> allUsers;

    public ADMIN_PANEL(JFrame homepage) {
        homepage.dispose();
        adminFrame = new JFrame("Admin Panel");
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setSize(900, 600);
        adminFrame.setLayout(null);
        adminFrame.setResizable(false);
        adminFrame.setLocationRelativeTo(null);

        JPanel leftPanel = createLeftPanel();
        JPanel rightPanel = createRightPanel(homepage);

        adminFrame.add(leftPanel);
        adminFrame.add(rightPanel);
        adminFrame.setVisible(true);
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 400, 600);
        leftPanel.setBackground(new Color(34, 167, 240));
        leftPanel.setLayout(null);

        JLabel title = new JLabel("Admin Panel");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(50, 50, 300, 50);

        JLabel motivationalText1 = new JLabel("Manage Users Efficiently!");
        motivationalText1.setFont(new Font("SansSerif", Font.BOLD, 18));
        motivationalText1.setForeground(Color.WHITE);
        motivationalText1.setHorizontalAlignment(SwingConstants.CENTER);
        motivationalText1.setBounds(50, 150, 300, 30);

        JLabel motivationalText2 = new JLabel("Keep Your System Clean.");
        motivationalText2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        motivationalText2.setForeground(Color.WHITE);
        motivationalText2.setHorizontalAlignment(SwingConstants.CENTER);
        motivationalText2.setBounds(50, 180, 300, 30);

        leftPanel.add(title);
        leftPanel.add(motivationalText1);
        leftPanel.add(motivationalText2);

        return leftPanel;
    }

    private JPanel createRightPanel(JFrame homepage) {
        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(400, 0, 500, 600);
        rightPanel.setBackground(new Color(44, 62, 80));
        rightPanel.setLayout(null);

        JLabel userListLabel = new JLabel("Registered Users:");
        userListLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        userListLabel.setForeground(Color.WHITE);
        userListLabel.setBounds(50, 20, 400, 30);
        rightPanel.add(userListLabel);


        searchField = new JTextField();
        searchField.setBounds(50, 60, 400, 30);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.setForeground(Color.BLACK);
        searchField.setToolTipText("Search by username...");
        searchField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        searchField.addCaretListener(e -> filterUsers(searchField.getText().trim()));
        rightPanel.add(searchField);

        userList = new JTextArea();
        userList.setEditable(false);
        userList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        userList.setForeground(Color.WHITE);
        userList.setBackground(new Color(44, 62, 80));
        userList.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        JScrollPane scrollPane = new JScrollPane(userList);
        scrollPane.setBounds(50, 100, 400, 210);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        rightPanel.add(scrollPane);


        allUsers = loadUsers();
        allUsers.forEach(user -> userList.append(user + "\n"));

        JButton deleteUserButton = createStyledButton("Delete User", 330);
        JButton updateUserButton = createStyledButton("Update User Info", 390);
        JButton addUserButton = createStyledButton("Add New User", 450);
        JButton backButton = createStyledButton("Back to Homepage", 510);

        deleteUserButton.addActionListener(e -> {
            String userToDelete = JOptionPane.showInputDialog(adminFrame, "Enter username to delete:");
            if (userToDelete != null && userToDelete.equals("Admin")) {
                JOptionPane.showMessageDialog(null, "Cannot delete the Admin user!");
            } else if (userToDelete != null && deleteUser(userToDelete)) {
                JOptionPane.showMessageDialog(adminFrame, "User deleted successfully!");
                refreshUserList();
            } else {
                JOptionPane.showMessageDialog(adminFrame, "User not found!");
            }
        });

        updateUserButton.addActionListener(e -> {
            String userToUpdate = JOptionPane.showInputDialog(adminFrame, "Enter username to update:");
            if (userToUpdate == null || userToUpdate.isEmpty()) return;

            if (userToUpdate.equals("Admin")) {
                JOptionPane.showMessageDialog(adminFrame, "Cannot update the Admin user!");
                return;
            }

            String newUsername = JOptionPane.showInputDialog(adminFrame, "Enter new username for " + userToUpdate + ":");
            String newPassword = JOptionPane.showInputDialog(adminFrame, "Enter new password for " + userToUpdate + ":");

            if (newUsername != null && newPassword != null && updateUser(userToUpdate, newUsername + "/" + newPassword)) {
                JOptionPane.showMessageDialog(adminFrame, "User updated successfully!");
                refreshUserList();
            } else {
                JOptionPane.showMessageDialog(adminFrame, "User not found or operation canceled!");
            }
        });

        addUserButton.addActionListener(e -> {
            String newUsername = JOptionPane.showInputDialog(adminFrame, "Enter new username:");
            String newPassword = JOptionPane.showInputDialog(adminFrame, "Enter new password:");

            if (newUsername != null && newPassword != null && !isUsernameDuplicate(newUsername) && addUser(newUsername + "/" + newPassword)) {
                JOptionPane.showMessageDialog(adminFrame, "User added successfully!");
                refreshUserList();
            } else {
                JOptionPane.showMessageDialog(adminFrame, "Failed to add user! Ensure the username is unique and inputs are valid.");
            }
        });

        backButton.addActionListener(e -> {
            adminFrame.dispose();
            new HOME_PAGE();
        });

        rightPanel.add(deleteUserButton);
        rightPanel.add(updateUserButton);
        rightPanel.add(addUserButton);
        rightPanel.add(backButton);

        return rightPanel;
    }

    private ArrayList<String> loadUsers() {
        ArrayList<String> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/credentials.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                users.add(line.split("/")[0]);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(adminFrame, "Error loading users: " + e.getMessage());
        }
        return users;
    }

    private void filterUsers(String query) {
        userList.setText("");
        allUsers.stream()
                .filter(user -> user.toLowerCase().contains(query.toLowerCase()))
                .forEach(user -> userList.append(user + "\n"));
    }

    private void refreshUserList() {
        allUsers = loadUsers();
        filterUsers(searchField.getText().trim());
    }

    private boolean deleteUser(String username) {
        File file = new File("src/credentials.txt");
        ArrayList<String> users = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(username + "/")) {
                    users.add(line);
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(adminFrame, "Error reading users: " + e.getMessage());
        }

        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String user : users) {
                    writer.write(user);
                    writer.newLine();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(adminFrame, "Error updating user file: " + e.getMessage());
            }
        }

        return found;
    }

    private boolean updateUser(String username, String newInfo) {
        File file = new File("src/credentials.txt");
        ArrayList<String> users = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(username + "/")) {
                    users.add(line);
                } else {
                    users.add(newInfo);
                    found = true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(adminFrame, "Error reading users: " + e.getMessage());
        }

        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String user : users) {
                    writer.write(user);
                    writer.newLine();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(adminFrame, "Error updating user file: " + e.getMessage());
            }
        }

        return found;
    }

    private boolean addUser(String newUserInfo) {
        File file = new File("src/credentials.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (file.length() > 0) writer.newLine();
            writer.write(newUserInfo);
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(adminFrame, "Error adding user: " + e.getMessage());
        }
        return false;
    }

    private boolean isUsernameDuplicate(String username) {
        ArrayList<String> users = loadUsers();
        return users.contains(username);
    }

    private JButton createStyledButton(String text, int yPosition) {
        JButton button = new JButton(text);
        button.setBounds(150, yPosition, 200, 40);
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
}
