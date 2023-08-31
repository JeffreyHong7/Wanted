package Mario64;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Target;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameComponent extends JPanel implements MouseListener {

    // Class properties
    private Target target = new Target();
    /** duration of a character shown (will change throughout the game) */
    private int targetTime = 10000;
    /** whether a game is being played or not */
    private boolean gameOn = false;
    /** Timer to check if game is over  */
    private Timer timer;
    /**score of the game */
    private int score = 0;

    // Game methods:
    public GameComponent() {
        timer = new Timer(targetTime, (ActionEvent e) -> timeOut());
        timer.setInitialDelay(0);
        timer.setCoalesce(true);
        addMouseListener(this);
        setPreferredSize(new Dimension(480, 480));
    }

    public void startGame() {
        setScore(0);
        gameOn = true;
        timer.restart();
        repaint();
    }

    public void stopGame() {
        timer.stop();
        gameOn = false;
        repaint();
    }


    private void timeOut() {
        if (gameOn) {
            repaint();
        }
    }

    public int getScore() {
        return score;
    }
    public void setScore(int current) {
        int previous = score;
        score = current;
        firePropertyChange("GameScore", previous, current);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paint(g);
        if (gameOn = false) {
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            repaint();
        } else {
            target.createWanted(g);
        }
    }

    // MouseListener Methods
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (gameOn) {
            setScore(score + 1);
            repaint();
        }
    }

    private static class Target {
        /** x-coordinate of the center of a target */
        int x;
        /** y-coordinate of the center of a target */
        int y;
        /** radius of the characters */
        int radius;

        /** whether a player hit the proper character or not */
        private boolean isHit;
        /** Generate random numbers to displace characters randomly and have target be chosen at
         * random */
        private Random rng = new Random();

        BufferedImage produceImage(int i)  {
            try {
                if (i == 0) {
                    return ImageIO.read(new File("C:\\Downloads\\Wanted\\mario.png"));
                } else if (i == 1) {
                    return ImageIO.read(new File("C:\\Downloads\\Wanted\\mario.png"));
                } else if (i == 2) {
                    return ImageIO.read(new File("C:\\Downloads\\Wanted\\mario.png"));
                } else {
                    return ImageIO.read(new File("C:\\Downloads\\Wanted\\mario.png"));
                }
            } catch (IOException e) {
                return null;
            }
        }

        void createWanted(Graphics g)  {
            int target = rng.nextInt(3);
            if (target == 0)  {
                String name = "Mario";
                g.drawImage(produceImage(target), 0, 0, 100, 100,null);
            } else if (target == 1) {
                String name = "Luigi";
                g.drawImage(produceImage(target), 0, 0, 100, 100,null);
            } else if (target == 2) {
                String name = "Wario";
                g.drawImage(produceImage(target), 0, 0, 100, 100,null);
            } else {
                String name = "Yoshi";
                g.drawImage(produceImage(target), 0, 0, 100, 100,null);
            }
        }



    }

}
