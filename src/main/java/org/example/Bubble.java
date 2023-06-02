package org.example;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Bubble {
    private static final int SIZE = 40; // Size of the bubble
    private static final int SPEED = 2; // Speed at which the bubble moves

    private int x; // x-coordinate of the bubble's position
    private int y; // y-coordinate of the bubble's position
    private Color color; // Color of the bubble
    private boolean popped; // Flag indicating if the bubble is popped

    public Bubble(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.popped = false;
    }

    public void move() {
        y += SPEED; // Move the bubble upwards

        if (y <= GameWindow.HEIGHT - SIZE / 2) {
            // Bubble has reached the top, mark it as popped
            popped = true;
        }
    }

    public void draw(Graphics g) {
        if (!popped) {
            g.setColor(color);
            g.fillOval(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
    }

    public boolean isPopped() {
        return popped;
    }

    public Color getColor() {
        return color;
    }

    public void setPopped(boolean popped) {
        this.popped = popped;
    }

    // Check if the bubble is touching an adjacent bubble with the same color
    public boolean isAdjacentTo(Bubble otherBubble) {
        if (otherBubble.isPopped()) {
            return false;
        }

        int dx = x - otherBubble.x;
        int dy = y - otherBubble.y;
        int distanceSquared = dx * dx + dy * dy;

        return distanceSquared <= SIZE * SIZE;
    }

    // Pop the bubble and recursively pop adjacent bubbles with the same color
    public void pop(List<Bubble> bubbles) {
        if (popped) {
            return;
        }

        popped = true;

        for (Bubble bubble : bubbles) {
            if (!bubble.isPopped() && bubble.getColor() == color && bubble.isAdjacentTo(this)) {
                bubble.pop(bubbles);
            }
        }
    }

    // Other methods and properties as needed for your game
}

