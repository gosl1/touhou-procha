package touhou;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IntroPanel extends JPanel {
    private JButton startButton;
    private JLabel textBubble;
    private int clickCount = 0;
    private String[] messages = {
        "Welcome to Touhou: Procha!",
        "This game will help you organize your tasks.",
        "Click start to begin your adventure!"
    };

    public IntroPanel(JFrame frame) {
        setLayout(new BorderLayout());

        textBubble = new JLabel(messages[0], SwingConstants.CENTER);
        textBubble.setFont(new Font("Arial", Font.PLAIN, 18));
        add(textBubble, BorderLayout.CENTER);

        startButton = new JButton("Start Program");
        startButton.setEnabled(false); // enabled after all clicks/messages
        add(startButton, BorderLayout.SOUTH);

        // Click anywhere to progress messages
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (clickCount < messages.length - 1) {
                    clickCount++;
                    textBubble.setText(messages[clickCount]);
                } else {
                    startButton.setEnabled(true);
                }
            }
        });

        startButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new UI()); // your main UI panel
            frame.revalidate();
            frame.repaint();
        });
    }
}
