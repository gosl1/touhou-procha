package touhou;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IntroPanel extends ImagePanel {

    private JButton startButton;
    private JLabel textBubble;

    private BubbleButton yesButton;
    private BubbleButton dotButton;

    private boolean waitingForChoice = false;

    private int clickCount = 0;

    private String[] messages = {
        "<html>I know you're grateful and all but... Are you really doing this?<html>",
        "<html>Fine, welcome to Gensokyo.<html>",
        "<html>The shrine's falling apart—not just the building, but the divine power too.<html>",
        "<html>Without enough faith from visitors, the barriers weaken, and all the youkai around just make things worse.<html>",
        "<html>You don't have to do anything grand. All you have to do is pray to the shrine and offer your faith.<html>"
    };

    public IntroPanel(JFrame frame) {
        super("/touhou/Assets/IntroBG.png");
        setLayout(new BorderLayout());

        BubblePanel bubblePanel = new BubblePanel("Assets/DialogueBG.png");
        bubblePanel.setLayout(new BorderLayout());

        textBubble = new JLabel(messages[0], SwingConstants.CENTER);
        textBubble.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
        textBubble.setOpaque(false);
        bubblePanel.add(textBubble, BorderLayout.CENTER);

        // ===== Choice buttons panel =====
        JPanel choicePanel = new JPanel(new FlowLayout());
        choicePanel.setOpaque(false);

        yesButton = new BubbleButton("Yes");
        dotButton = new BubbleButton("...");

        yesButton.setVisible(false);
        dotButton.setVisible(false);

        choicePanel.add(yesButton);
        choicePanel.add(dotButton);

        bubblePanel.add(choicePanel, BorderLayout.SOUTH);

        // ===== Start button =====
        startButton = new BubbleButton("Start Program");
        startButton.setEnabled(false);

        ImageIcon icon = new ImageIcon(getClass().getResource("Assets/icon.png"));
        frame.setIconImage(icon.getImage());

        ImageIcon reimu = new ImageIcon(getClass().getResource("Assets/reimuintro.png"));
        JLabel reimuicon = new JLabel(reimu, SwingConstants.CENTER);
        reimuicon.setVerticalAlignment(SwingConstants.BOTTOM);

        JPanel southPanel = new JPanel(new GridBagLayout());
        southPanel.setOpaque(false);
        southPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        southPanel.add(reimuicon, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        southPanel.add(startButton, gbc);

        add(bubblePanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        // ===== Mouse click logic =====
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if (waitingForChoice) return;

                if (clickCount < messages.length - 1) {
                    clickCount++;

                    if (clickCount == 1) {
                        waitingForChoice = true;
                        yesButton.setVisible(true);
                        return;
                    }

                    if (clickCount == 4) {
                        waitingForChoice = true;
                        dotButton.setVisible(true);
                        return;
                    }

                    textBubble.setText(messages[clickCount]);
                } else {
                    startButton.setEnabled(true);
                }
            }
        });

        // ===== Choice button logic =====
        yesButton.addActionListener(e -> {
            yesButton.setVisible(false);
            waitingForChoice = false;
            textBubble.setText(messages[clickCount]);
        });

        dotButton.addActionListener(e -> {
            dotButton.setVisible(false);
            waitingForChoice = false;
            textBubble.setText(messages[clickCount]);
        });

        // ===== Start program =====
        startButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new UI());
            frame.revalidate();
            frame.repaint();
        });
    }
}