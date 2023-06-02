package org.example;
import java.awt.*;

public class Shooter {
    private static final int WIDTH = 40; // Width of the shooter
    private static final int HEIGHT = 40; // Height of the shooter
    private static final Color COLOR = Color.BLUE; // Color of the shooter

    private int x; // x-coordinate of the shooter's position
    private int y; // y-coordinate of the shooter's position
    private Polygon pointer; // Pointer indicating the shooting direction

    public Shooter(int x, int y) {
        this.x = x;
        this.y = y;

            // Create the pointer polygon
            int[] xPoints = { x, x - 8, x + 8 };
            int[] yPoints = { y - HEIGHT / 2-30 ,y - HEIGHT / 2 - 5, y - HEIGHT / 2 - 5};
            pointer = new Polygon(xPoints, yPoints, 3);
    }

    public void draw(Graphics g) {
        // Draw the shooter
        g.setColor(COLOR);
        g.fillOval(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);

        // Draw the pointer
        g.setColor(COLOR);
        g.fillPolygon(pointer);
    }


    public void trackMouse(int mouseX, int mouseY, int height) {
        // Calculate the angle between the anchor circle's center and the mouse position
        double angle = Math.atan2(y - mouseY, mouseX - x);

        // Calculate the radius of the anchor circle
        int anchorRadius = HEIGHT / 2 + 30;

        // Calculate the position of the pointer based on the angle and the anchor circle's center
        int pointerX = x + (int) (Math.cos(angle) * anchorRadius);
        int pointerY = y - (int) (Math.sin(angle) * anchorRadius);

        // Perform bounds checking to prevent the pointer from going below the window's size
        int windowHeight = height;
        if (pointerY > windowHeight) {
            pointerY = windowHeight;
        }

        // Update the pointer polygon
        int pointerSize = 20; // Adjust the size of the pointer polygon
        int[] xPoints = { pointerX, pointerX - pointerSize / 2, pointerX + pointerSize / 2 };
        int[] yPoints = { pointerY, pointerY - pointerSize, pointerY - pointerSize };
        pointer = new Polygon(xPoints, yPoints, 3);

    }

    public Rectangle getBounds() {
        return new Rectangle(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
    }

    // Other methods and properties as needed for your game
}