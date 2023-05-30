package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameWindow extends JFrame {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    private static final int NUM_STARS = 50;
    private List<Star> stars;
    public GameWindow() {
        setTitle("Falling Stars");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Get your stars
        stars = new ArrayList<>();
        generateStars();

        JPanel panel = new JPanel() {
            //draws using java graphics 2d using render
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                render((Graphics2D) g);
            }
        };
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(panel);

        startAnimationLoop();
    }

    private void generateStars() {
        Random random = new Random();
        //generates stars at random with properties differing
        for (int i = 0; i < NUM_STARS; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            int size = random.nextInt(3) + 1;
            int velocity = random.nextInt(5) + 1;
            stars.add(new Star(x, y, size, velocity));
        }
    }

    private void startAnimationLoop() {
        new Thread(() -> {
            while (true) {
                //after every 0.02s change pos and redraw
                updateStars();
                repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void updateStars() {
        for (Star star : stars) {
            star.updatePosition();
        }
    }

    private void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.BLUE);
        for (Star star : stars) {
            g.fillOval(star.getX(), star.getY(), star.getSize(), star.getSize());
        }
    }

    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        gameWindow.setVisible(true);
    }
}