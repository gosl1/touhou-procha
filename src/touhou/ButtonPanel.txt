package touhou;

import javax.swing.*;        // JPanel, ImageIcon
import java.awt.*;

class BubblePanel extends JPanel {
    private Image bubbleImage;

    public BubblePanel(String imagePath) {
        try {
            bubbleImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setOpaque(false);
        setLayout(new GridBagLayout()); // centers the text
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bubbleImage != null) {
            g.drawImage(bubbleImage, 0, 0, getWidth(), getHeight(), this); // scale to panel size
        }
    }
}