import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;// [2](https://reintech.io/blog/java-2d-graphics-drawing-shapes-text-images)
//
//public class Main extends JPanel {
//    private Image image;
//    public Main() {
//        try {
//            image = ImageIO.read(new File("image/black/king.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }



import javax.swing.*;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

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
    private Image image;
    public void DrawingPanel() {
        try {
            image = ImageIO.read(new File("src/image/black/king.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void paint(Graphics g)
    {
        int f=0;
        Image image1,image2;
        try {
            image1 = ImageIO.read(new File("src/image/black/king.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            image2 = ImageIO.read(new File("src/image/black/pon.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        //Image spriteIcon = new Image("src/images/full.png");
        g.drawImage(image, 1000, 500, 500, 500, null);
        g.drawImage(image1, 1033, 105, 34, 90, null);
        for(int i=600;i<1400;i+=100){
            g.drawImage(image2, i+38, 230, 23, 40, null);

        }
    }

}