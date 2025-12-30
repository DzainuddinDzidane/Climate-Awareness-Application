import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FOCUS_HANDLER {
    public FOCUS_HANDLER(JTextField input, JLabel errorLabel) {
        input.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                errorLabel.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (input.getText().trim().isEmpty()) {
                    errorLabel.setText("Must be filled");
                }
            }
        });
    }
}
