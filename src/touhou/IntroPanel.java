package touhou;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IntroPanel extends JPanel {
    private JButton startButton;
    private JLabel textBubble;
    private int clickCount = 0;
    private String[] messages = {
		"<html>I know you're grateful and all but... Are you really doing this?<html>",
		"Yes",
		"<html>Fine, welcome to Gensokyo.<html>",
		"<html>The shrine's falling apart—not just the building, but the divine power too.<html>",
		"<html>Without enough faith from visitors, the barriers weaken, and all the youkai around just make things worse.<html>",
		"...",
		"<html>You don't have to do anything grand. All you have to do is pray to the shrine and offer your faith.<html>"
    };

    public IntroPanel(JFrame frame) {
        setLayout(new BorderLayout());

        textBubble = new JLabel(messages[0], SwingConstants.CENTER);
		textBubble.setHorizontalAlignment(SwingConstants.CENTER);
        textBubble.setFont(new Font("Impact", Font.PLAIN, 18));
        add(textBubble, BorderLayout.CENTER);

        startButton = new JButton("Start Program");
        startButton.setEnabled(false); 					// will be enabled after all clicks/messages
        add(startButton, BorderLayout.SOUTH);
		ImageIcon icon = new ImageIcon(getClass().getResource("Assets/icon.png"));
		frame.setIconImage(icon.getImage());
        ImageIcon reimu = new ImageIcon(getClass().getResource("Assets/reimuintro.png"));
        JLabel reimuicon = new JLabel(reimu, SwingConstants.CENTER);
        reimuicon.setPreferredSize(new Dimension(200, 100));
        add(reimuicon, BorderLayout.EAST);

        // Click anywhere to progress messages
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (clickCount < messages.length - 1) {
                    clickCount++;
                    if(clickCount == 1){
                        //newjbutton that says yes and can only proceed if this is pressed
                    }
                    if(clickCount == 4){
                        //newjbutton that says ... and can only proceed if this is pressed
                    }
                    textBubble.setText(messages[clickCount]);	// iterates through all the messages in the intro messages array
                } else {										// once all the dialogue has been displayed, lets the player click the start button to start the program
                    startButton.setEnabled(true);
                }
            }
        });

        startButton.addActionListener(e -> {
            frame.getContentPane().removeAll();		// Remove all intro components from the JFrame
            frame.getContentPane().add(new UI()); 	// Add UI components to the Jframe
            frame.revalidate();
            frame.repaint();
        });
    }
}
