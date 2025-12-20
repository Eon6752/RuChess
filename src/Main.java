import javax.swing.*;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
class Main extends JFrame {
    int count=0;

    public Main(){

        super("title");
        setSize(1920, 1080);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        add(panel);
        setVisible(true);
        ImageIcon spriteIcon = new ImageIcon("src/images/full.png");
        JLabel spriteLabel = new JLabel(spriteIcon);
        panel.add(spriteLabel);
        panel.setLayout(null);
        spriteLabel.setBounds(50, 50, 500, 500);

    }

    public static void main(String[] args) {
        Main app = new Main();
        app.setVisible(true);
        Main m = new Main();
       m.repaint();




    }
    @Override
    public void paint(Graphics g)
    {
        int f=0;

        Color c1 = new Color(255, 0, 0);
        for(int i=0;i<800;i+=100){
            for(int j=0;j<800;j+=100){
                if (f%2==0) {
                    c1 =new Color(210,105,30);
                }
                else{
                    c1 = new Color(255,228,181);
                }
                f++;
                g.setColor(c1);
                g.fillRect(600 + i, 100 + j, 100, 100);
            }
            f++;
        }
        Image spriteIcon = new Image("src/images/full.png");
        g.drawImage(Image,"src/images/full.png", 0, 0, 500, 500, null);
    }

}