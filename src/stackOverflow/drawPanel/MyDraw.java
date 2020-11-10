package stackOverflow.drawPanel;

import javax.swing.*;
import java.awt.*;

class MyDrawPanel extends JPanel {
    int x = 20;

    public void paintComponent(Graphics g) {
        for (int i = 0; i < 5; i++) {
            g.setColor(Color.black);
            g.fillRect(x, 20, 100, 100);
            x += 120;
        }
    }
}

public class MyDraw {

    MyDrawPanel panel = new MyDrawPanel();
    JFrame frame = new JFrame();

    public void createGuiPanel() {
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        MyDraw draw = new MyDraw();
        draw.createGuiPanel();
    }
}