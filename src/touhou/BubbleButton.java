package touhou;

import javax.swing.*;
import java.awt.*;

public class BubbleButton extends JButton {
    private Image bubbleImage;

    public BubbleButton(String text) {
        super(text);
        try {
            bubbleImage = new ImageIcon(getClass().getResource("Assets/DialogueBG.png")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Style the button text like faith counter
        setFont(new Font("Lucida Sans", Font.PLAIN, 16));
        setForeground(Color.BLACK);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setPreferredSize(new Dimension(180, 60));
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Draw the bubble background
        if (bubbleImage != null) {
            g.drawImage(bubbleImage, 0, 0, getWidth(), getHeight(), this);
        }
        // Draw the button text on top
        super.paintComponent(g);
    }
}