
import javax.swing.*;
import java.awt.event.MouseListener;

public class Main {


    public static void main(String[] args) {
        new Main();
    }

    public Main(){

        JFrame frame = new JFrame();
        frame.setTitle("Sierpinski Triangle Visualization");
        frame.addMouseListener(Contents.listener);
        frame.setSize(900, 600);
        frame.setLocation(100, 50);
        frame.add(new Contents());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}