
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Contents extends JPanel implements ActionListener {

    int currentX, currentY;

    List<Integer> coordinates = new ArrayList<>();

    private Timer t;

    public Contents(){
        super.setDoubleBuffered(true);
        t = new Timer(7, this);
        t.start();

    }

    public double area(int x1, int y1, int x2, int y2, int x3, int y3){
        return Math.abs((x1*(y2-y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
    }

    public boolean isInside(int x1, int y1, int x2, int y2, int x3, int y3, int x, int y){

        double A = area(x1, y1, x2, y2, x3, y3);

        double A1 = area(x, y, x2, y2, x3, y3);

        double A2 = area(x1, y1, x, y, x3, y3);

        double A3 = area(x1, y1, x2, y2, x, y);

        return (A == A1 + A2 + A3);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.fillOval(450, 25, 5, 5);
        g2d.fillOval(225, 525, 5, 5);
        g2d.fillOval(675, 525, 5, 5);
        for(int i = 0; i < coordinates.size() - 1; i += 2){
            g2d.fillOval(coordinates.get(i), coordinates.get(i + 1), 3, 3);
        }

    }




    public void actionPerformed(ActionEvent e) {
        if(coordinates.isEmpty()) {
            while(!isInside(450, 25, 225, 525, 675, 525, currentX, currentY)) {
                currentX = (int) ((Math.random() * 450) + 226);
                currentY = (int) ((Math.random() * 500) + 26);
            }
        }


        int randomDot = (int)((Math.random() * 3) + 1);
        int y = 0;
        int x = 0;
        if(randomDot == 1){
            x = 450;
            y = 25;
        } else if (randomDot == 2){
            x = 225;
            y = 525;
        } else if (randomDot == 3){
            x = 675;
            y = 525;
        }
        coordinates.add((currentX + x) / 2);
        coordinates.add((currentY + y) / 2);

        currentX = (currentX + x) / 2;
        currentY = (currentY + y) / 2;

        repaint();
    }
}