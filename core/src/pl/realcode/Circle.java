package pl.realcode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Circle {
    private double x;
    private double y;
    private int radius;
    private double vy;
    private double vx;
    private double ax;
    private double ay;

    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;

        vy = Utils.rand(-20, 20);
        vx = Utils.rand(-20, 20);

        if (x < Constants.WIDTH / 2) {
            ax = Utils.rand(0, 15);
        } else {
            ax = Utils.rand(-15, 0);
        }

        if (y < Constants.HEIGHT / 2) {
            ay = Utils.rand(0, 15);
        } else {
            ay = Utils.rand(-15, 0);
        }
    }

    public void checkCollisions() {
        checkTopCollision();
        checkRightCollision();
        checkBottomCollision();
        checkLeftCollision();
    }

    private void checkTopCollision() {
        if (y + radius > Constants.HEIGHT) {
            y = Constants.HEIGHT - radius - 1;
            vy = -vy;
            vy *= 0.5f;
        }
    }

    private void checkRightCollision() {
        if (x + radius >= Constants.WIDTH) {
            x = Constants.WIDTH - radius - 1;
            vx = -vx;
            vx *= 0.5f;
        }
    }

    private void checkBottomCollision() {
        if (y - radius < 0) {
            y = radius + 1;
            vy = -vy;
            vy *= 0.5f;
        }
    }

    private void checkLeftCollision() {
        if (x - radius < 0) {
            x = radius + 1;
            vx = -vx;
            vx *= 0.5f;
        }
    }

    public void render(ShapeRenderer renderer) {
        renderer.circle(getX(), getY(),
                radius);
    }

    public void render(ShapeRenderer renderer, float deltaTime) {
        render(renderer);
        updatePosition(deltaTime);
    }

    private int getY() {
        return (int) y;
    }

    private int getX() {
        return (int) x;
    }

    public void updatePosition(float deltaTime) {
        if (Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)) {
            this.ax = -Gdx.input.getAccelerometerX() * 12 % 15;
            this.ay = -Gdx.input.getAccelerometerY() * 12 % 15;
        }

        // Adding acceleration to velocity
        this.vx += this.ax;
        this.vy += this.ay;

        // Updating position based on the current speed and time
        this.x += this.vx * deltaTime;
        this.y += this.vy * deltaTime;

        checkCollisions();
    }
}
