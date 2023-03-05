
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Contents extends JPanel implements ActionListener {

    static int aX = 450;
    static int aY = 25;
    static int bX = 225;
    static int bY = 525;
    static int cX = 675;
    static int cY = 525;
    int currentX;
    int currentY;

    static boolean pointAWasClicked = false;
    static boolean pointBWasClicked = false;
    static boolean pointCWasClicked = false;
    static boolean wasPaused = false;

    static List<Integer> coordinates = new ArrayList<>();

    private Timer t;

    public Contents(){
        super.setDoubleBuffered(true);
        t = new Timer(7, this);
        t.start();

    }

    public static MouseListener listener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getX() >= 20 && e.getX() <= 65 && e.getY() >= 45 && e.getY() <= 65){
                wasPaused = !wasPaused;
            }

            if(e.getX() >= 75 && e.getX() <= 125 && e.getY() >= 45 && e.getY() <= 65){
                coordinates.clear();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("x: " + e.getX() + "y: " + e.getY());
            if((e.getX() >= (aX - 40) && e.getX() <= (aX + 40)) && (e.getY() >= (aY - 40) && e.getY() <= (aY + 40))){
                pointAWasClicked = true;
            }
            if((e.getX() >= (bX - 40) && e.getX() <= (bX + 40)) && (e.getY() >= (bY - 40) && e.getY() <= (bY + 40))){
                pointBWasClicked = true;
            }
            if((e.getX() >= (cX - 40) && e.getX() <= (cX + 40)) && (e.getY() >= (cY - 40) && e.getY() <= (cY + 40))){
                pointCWasClicked = true;
            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(pointAWasClicked) {
                pointAWasClicked = false;
                aX = e.getX() - 9;
                aY = e.getY() - 32;
            }
            if(pointBWasClicked) {
                pointBWasClicked = false;
                bX = e.getX() - 9;
                bY = e.getY() - 32;
            }
            if(pointCWasClicked) {
                pointCWasClicked = false;
                cX = e.getX() - 9;
                cY = e.getY() - 32;
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

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
        if(wasPaused) {
            g2d.drawString("Start", 10, 10);
            g2d.setColor(Color.green);
        }
        else {
            g2d.drawString("Stop", 10, 10);
            g2d.setColor(Color.red);
        }
        g2d.fillRect(10, 15, 50, 20);
        g2d.setColor(Color.blue);
        g2d.fillRect(70, 15, 50, 20);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Clear", 65, 10);

        if(pointAWasClicked) {
            g2d.fillOval(getMousePosition().x, getMousePosition().y, 10, 10);
        } else {
            g2d.fillOval(aX, aY, 5, 5);
        }

        if(pointBWasClicked) {
            g2d.fillOval(getMousePosition().x, getMousePosition().y, 10, 10);
        } else {
            g2d.fillOval(bX, bY, 5, 5);
        }

        if(pointCWasClicked) {
            g2d.fillOval(getMousePosition().x, getMousePosition().y, 10, 10);
        } else {
            g2d.fillOval(cX, cY, 5, 5);
        }

        for(int i = 0; i < coordinates.size() - 1; i += 2){
            g2d.fillOval(coordinates.get(i), coordinates.get(i + 1), 3, 3);
        }

    }




    public void actionPerformed(ActionEvent e) {
        if(!wasPaused) {
            if (coordinates.isEmpty()) {
                while (!isInside(aX, aY, bX, bY, cX, cY, currentX, currentY)) {
                    currentX = (int) ((Math.random() * 450) + 226);
                    currentY = (int) ((Math.random() * 500) + 26);
                }
            }


            int randomDot = (int) ((Math.random() * 3) + 1);
            int y = 0;
            int x = 0;
            if (randomDot == 1) {
                x = aX;
                y = aY;
            } else if (randomDot == 2) {
                x = bX;
                y = bY;
            } else if (randomDot == 3) {
                x = cX;
                y = cY;
            }
            coordinates.add((currentX + x) / 2);
            coordinates.add((currentY + y) / 2);

            currentX = (currentX + x) / 2;
            currentY = (currentY + y) / 2;
        }

        repaint();
    }
}