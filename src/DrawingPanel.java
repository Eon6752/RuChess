import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;// [2](https://reintech.io/blog/java-2d-graphics-drawing-shapes-text-images)
//
//public class Game extends JPanel {
//    private Image image;
//    public Game() {
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

 class Game extends JFrame {
    int count = 0;

    public Game() {

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

   

    private Image image;

    public void DrawingPanel() {
        try {
            image = ImageIO.read(new File("src/image/black/king.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        int f = 0;
        Image image1, image2,image3,image4,image5,image6;
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
        try {
            image3 = ImageIO.read(new File("src/image/black/qween.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            image4 = ImageIO.read(new File("src/image/black/officer.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            image5 = ImageIO.read(new File("src/image/black/poni.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            image6 = ImageIO.read(new File("src/image/black/rook.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image wimage1,wimage2,wimage3,wimage4,wimage5,wimage6;
        try {
            wimage1 = ImageIO.read(new File("src/image/white/king.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            wimage2 = ImageIO.read(new File("src/image/white/pon.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            wimage3 = ImageIO.read(new File("src/image/white/qween.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            wimage4 = ImageIO.read(new File("src/image/white/officer.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            wimage5 = ImageIO.read(new File("src/image/white/poni.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            wimage6 = ImageIO.read(new File("src/image/white/rook.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Color c1 = new Color(255, 0, 0);
        for (int i = 0; i < 800; i += 100) {
            for (int j = 0; j < 800; j += 100) {
                if (f % 2 == 0) {
                    c1 = new Color(210, 105, 30);
                } else {
                    c1 = new Color(255, 228, 181);
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
        g.drawImage(image3, 933, 105, 34, 90, null);
        g.drawImage(image4, 833, 105, 34, 90, null);
        g.drawImage(image4, 1133, 105, 34, 90, null);
        g.drawImage(image5, 733, 105, 34, 90, null);
        g.drawImage(image5, 1233, 105, 34, 90, null);
        g.drawImage(image6, 633, 105, 34, 90, null);
        g.drawImage(image6, 1333, 105, 34, 90, null);


        g.drawImage(image, 1000, 500, 500, 500, null);
        g.drawImage(wimage1, 1033, 805, 34, 90, null);
        g.drawImage(wimage3, 933, 805, 34, 90, null);
        g.drawImage(wimage4, 833, 805, 34, 90, null);
        g.drawImage(wimage4, 1133, 805, 34, 90, null);
        g.drawImage(wimage5, 733, 805, 34, 90, null);
        g.drawImage(wimage5, 1233, 805, 34, 90, null);
        g.drawImage(wimage6, 633, 805, 34, 90, null);
        g.drawImage(wimage6, 1333, 805, 34, 90, null);
        for (int i = 600; i < 1400; i += 100) {
            g.drawImage(image2, i + 27, 210, 23*2, 40*2, null);

        }
        for (int i = 600; i < 1400; i += 100) {
            g.drawImage(wimage2, i + 27, 710, 23*2, 40*2, null);

        }
    }

}