import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;// [2](https://reintech.io/blog/java-2d-graphics-drawing-shapes-text-images)
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
     int dx=-100,dy=-100,dxl,dyl,ax,ay;
     int f=0;
     String last;
     String[][] arr =
             {{"brook", "b", "0", "0", "0", "0", "w", "wrook"},
                     {"bponi", "b", "0", "0", "0", "0", "w", "wponi"},
                     {"bofficer", "b", "0", "0", "0", "0", "w", "wofficer"},
                     {"bqween", "b", "0", "0", "0", "0", "w", "wqween"},
                     {"bking", "b", "0", "0", "0", "0", "w", "wking"},
                     {"bofficer", "b", "0", "0", "0", "0", "w", "wofficer"},
                     {"bponi", "b", "0", "0", "0", "0", "w", "wponi"},
                     {"brook", "b", "0", "0", "0", "0", "w", "wrook"}};
     int[][] dop = {{27, 13}, {33, 7}, {27, 13}, {33, 7}, {30, 5}, {33, 7}};

     public Game() {

         super("title");
         setSize(1920, 1080);
         setLayout(null);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         //setLocationRelativeTo(null);
         JPanel panel = new JPanel();
         JLabel label = new JLabel("Привет, мир!");
         Font newFont = new Font("Arial", Font.BOLD, 20);
         label.setFont(newFont);panel.add(label);
         setVisible(true);


         //ImageIcon spriteIcon = new ImageIcon("src/images/full.png");
         //JLabel spriteLabel = new JLabel(spriteIcon);
         //panel.add(spriteLabel);
         //panel.setLayout(null);
        // spriteLabel.setBounds(50, 50, 500, 500);


//         JPanel panel = new JPanel()
//         {@Override
//         protected void paintComponent(Graphics g) {
//             super.paintComponent(g);
//             g.setColor(Color.RED);
//             // Рисуем область: x=50, y=50, width=100, height=100
//             g.fillRect(50, 50, 100, 100);
//         }
//         };
         add(panel);
         panel.setBounds(0, 0, 1920, 1080);

         panel.setOpaque(false);

         panel.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 int x = e.getX();

                 int y = e.getY()+50;

                 if((x>600)&&(x<1400)&&(y>100)&&(y<900)){
                     if ((f == 0)) {
                         dxl = dx;

                         dyl = dy;
                         dx = x - x % 100;
                         dy = y - y % 100;
                         if(!arr[(dx-600)/100][(dy-100)/100].equals("0")) {
                             f = 1;
                             repaint(dx, dy, 100, 100);
                             repaint(dxl, dyl, 100, 100);
                         }
                         else{
                             repaint(dxl, dyl, 100, 100);
                         }
                     }
                     else{
                         ax = x - x % 100;
                         ay = y - y % 100;
                         f=0;
                         last=(arr[(ax-600)/100][(ay-100)/100]+"");
                         arr[(ax-600)/100][(ay-100)/100]= arr[(dx-600)/100][(dy-100)/100];
                         arr[(dx-600)/100][(dy-100)/100]="0";
                         repaint(ax,ay,100,100);
                         repaint(dxl, dyl, 100, 100);
                         repaint(dx, dy, 100, 100);
                     }
                 }
                 else{
                     f=0;
                     repaint(dxl, dyl, 100, 100);
                     repaint(dx, dy, 100, 100);
//                     dx=-100;
//                     dy=-100;
                 }
                 //System.out.print(dx+" "+dy);
                 if((x>1500)&&(x<1800)&&(y>200)&&(y<300)){
                     arr[(dx-600)/100][(dy-100)/100]=arr[(ax-600)/100][(ay-100)/100];
                     arr[(ax-600)/100][(ay-100)/100]= last;

                     repaint(ax,ay,100,100);
                     repaint(dxl, dyl, 100, 100);
                     repaint(dx, dy, 100, 100);
                     System.out.println("zzzzzzz");
                 }





             }
         });
         Timer timer = new Timer(2000, e -> updateTime());
         timer.start();
         updateTime();
         setVisible(true);
     }

     private void updateTime() {
//         dx=700;
//         dy=700;


     }





     private Image image;



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
                if (f % 2 == 1) {
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
        c1 = new Color(100, 228, 100,125);
        g.setColor(c1);
        g.fillRect(dx, dy, 100, 100);
        c1 = new Color(100, 228, 100);
        g.setColor(c1);
        g.fillRect(1500, 200, 300, 100);
        c1 = new Color(50, 8, 50);
        Font newFont = new Font("Arial", Font.BOLD, 20);
        g.setColor(c1);

        g.drawString("Remove", 1600, 250);
        for(int j=7;j>=0;j--){
            for(int i=0;i<8;i++){
                if(arr[i][j].equals("b")){
                    g.drawImage(image2, i*100+600+dop[0][0], j*100+100+dop[0][1], 23*2, 40*2, null);
                }
                if(arr[i][j].equals("w")){
                    g.drawImage(wimage2, i*100+600+dop[0][0], j*100+100+dop[0][1], 23*2, 40*2, null);
                }
                if(arr[i][j].equals("brook")){
                    g.drawImage(image6, i*100+600+dop[1][0], j*100+100+dop[1][1], 34, 90, null);
                }
                if(arr[i][j].equals("wrook")){
                    g.drawImage(wimage6, i*100+600+dop[1][0], j*100+100+dop[1][1], 34, 90, null);
                }
                if(arr[i][j].equals("bponi")){
                    g.drawImage(image5, i*100+600+dop[2][0], j*100+100+dop[2][1], 23*2, 40*2, null);
                }
                if(arr[i][j].equals("wponi")){
                    g.drawImage(wimage5, i*100+600+dop[2][0], j*100+100+dop[2][1], 23*2, 40*2, null);
                }
                if(arr[i][j].equals("bofficer")){
                    g.drawImage(image4, i*100+600+dop[3][0], j*100+100+dop[3][1], 34, 90, null);
                }
                if(arr[i][j].equals("wofficer")){
                    g.drawImage(wimage4, i*100+600+dop[3][0], j*100+100+dop[3][1], 34, 90, null);
                }
                if(arr[i][j].equals("bqween")){
                    g.drawImage(image3, i*100+600+dop[4][0], j*100+100+dop[4][1], (int)(23*1.7), (int)(40*2.3),null);
                }
                if(arr[i][j].equals("wqween")){
                    g.drawImage(wimage3, i*100+600+dop[4][0], j*100+100+dop[4][1], (int)(23*1.7), (int)(40*2.3), null);
                }
                if(arr[i][j].equals("bking")){
                    g.drawImage(image1, i*100+600+dop[5][0], j*100+100+dop[5][1], 34, 90, null);
                }
                if(arr[i][j].equals("wking")){
                    g.drawImage(wimage1, i*100+600+dop[5][0], j*100+100+dop[5][1], 34, 90, null);
                }
            }

        }
        //Image spriteIcon = new Image("src/images/full.png");
//        g.drawImage(image, 1000, 500, 500, 500, null);
//        g.drawImage(image1, 1033, 105, 34, 90, null);
//        g.drawImage(image3, 933, 105, 34, 90, null);
//        g.drawImage(image4, 833, 105, 34, 90, null);
//        g.drawImage(image4, 1133, 105, 34, 90, null);
//        g.drawImage(image5, 733, 105, 34, 90, null);
//        g.drawImage(image5, 1233, 105, 34, 90, null);
//        g.drawImage(image6, 633, 105, 34, 90, null);
//        g.drawImage(image6, 1333, 105, 34, 90, null);
//
//
//        g.drawImage(image, 1000, 500, 500, 500, null);
//        g.drawImage(wimage1, 1033, 805, 34, 90, null);
//        g.drawImage(wimage3, 933, 805, 34, 90, null);
//        g.drawImage(wimage4, 833, 805, 34, 90, null);
//        g.drawImage(wimage4, 1133, 805, 34, 90, null);
//        g.drawImage(wimage5, 733, 805, 34, 90, null);
//        g.drawImage(wimage5, 1233, 805, 34, 90, null);
//        g.drawImage(wimage6, 633, 805, 34, 90, null);
//        g.drawImage(wimage6, 1333, 805, 34, 90, null);
//        for (int i = 600; i < 1400; i += 100) {
//            g.drawImage(image2, i + 27, 210, 23*2, 40*2, null);
//
//        }
//        for (int i = 600; i < 1400; i += 100) {
//            g.drawImage(wimage2, i + 27, 710, 23*2, 40*2, null);
//
//        }
    }

}