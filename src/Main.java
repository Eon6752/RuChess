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
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");
        JButton button0 = new JButton("0");
        setLayout(null);
        //JTextField textField = new JTextField(20);
        //textField.setText("touch the butten !");
        //textField.setBounds(900,400, 200, 50);
        //button1.setBounds(800, 500, 100, 20);
        //button2.setBounds(1100, 500, 100, 20);
        add(button1);
        add(button2);

        //add(textField);


//        button1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                count++;
//            }
//        });
//        button2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                textField.setText("do it");
//                System.out.println(count);
//            }
//        });
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

        Color c1 = new Color(0,0,0);
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
    }
}