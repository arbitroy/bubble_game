package org.example;

import java.util.Random;

public class Star {
    private int x;
    private int y;
    private int size;
    private int velocity;
    public Star(int x, int y, int size, int velocity) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.velocity = velocity;
    }
    public void updatePosition() {
        //when star reaches bottom a new position is generated otherwise the distance travelled by its velocity is added to its position
        y += velocity;
        if (y > GameWindow.HEIGHT) {
            y = 0;
            Random random = new Random();
            x = random.nextInt(GameWindow.WIDTH);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }
}
