package touhou;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IntroPanel extends ImagePanel {
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
		super("/touhou/Assets/IntroBG.png");
        setLayout(new BorderLayout());
		
		BubblePanel bubblePanel = new BubblePanel("Assets/DialogueBG.png");
		
        textBubble = new JLabel(messages[0], SwingConstants.CENTER);
		textBubble.setHorizontalAlignment(SwingConstants.CENTER);
        textBubble.setFont(new Font("Impact", Font.PLAIN, 18));
		textBubble.setOpaque(false);
		bubblePanel.add(textBubble);
		
        startButton = new JButton("Start Program");
        startButton.setEnabled(false); 					// will be enabled after all clicks/messages
		ImageIcon icon = new ImageIcon(getClass().getResource("Assets/icon.png"));
		frame.setIconImage(icon.getImage());
        ImageIcon reimu = new ImageIcon(getClass().getResource("Assets/reimuintro.png"));
        JLabel reimuicon = new JLabel(reimu, SwingConstants.CENTER);
        reimuicon.setVerticalAlignment(SwingConstants.BOTTOM);
        JPanel southPanel = new JPanel(new GridBagLayout());
		southPanel.setOpaque(false);
		southPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
		GridBagConstraints gbc = new GridBagConstraints();

		// Reimu icon on the left
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		southPanel.add(reimuicon, gbc);

		// Start button on the bottom-right
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.weightx = 1.0;  // push button to the right
		gbc.weighty = 1.0;  // push button to the bottom
		gbc.fill = GridBagConstraints.NONE;
		southPanel.add(startButton, gbc);

		// Add southPanel to the bottom of main panel
		add(bubblePanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);

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
