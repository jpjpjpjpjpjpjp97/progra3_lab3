package Lab3Views;

import javax.swing.*;
import java.awt.*;

public class AboutView extends JPanel {
    private JLabel aboutLabel;

    public AboutView() {
        this.setLayout(new BorderLayout());
        aboutLabel = new JLabel("© 2022, All Rights Reserved");
        aboutLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(aboutLabel, BorderLayout.SOUTH);
    }
}
