import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Main extends JFrame {
    int count=0;

    public Main(){

        super("title");
        setSize(1920, 1080);
        JButton button1 = new JButton("№1");
        JButton button2 = new JButton("№2");
        setLayout(null);
        JTextField textField = new JTextField(20);
        textField.setText("touch the butten !");
        textField.setBounds(900,400, 200, 50);
        button1.setBounds(800, 500, 100, 20);
        button2.setBounds(1100, 500, 100, 20);
        add(button1);
        add(button2);
        add(textField);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("do it");
                System.out.println(count);
            }
        });
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.setVisible(true);
    }
}