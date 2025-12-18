// package touhou;
// import java.io.*;
// import java.util.*;
// public class Main {
    
//     public static void main( String[] args){
//         UI ui = new UI();
//         ui.start();
//     }
// }

package touhou;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Touhou: Procha");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);

        frame.add(new IntroPanel(frame)); // show intro first

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
