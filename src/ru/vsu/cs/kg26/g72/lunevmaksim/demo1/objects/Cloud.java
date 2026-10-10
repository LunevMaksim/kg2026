package ru.vsu.cs.kg26.g72.lunevmaksim.demo1.objects;

import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Drawable;
import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Updatable;
import java.awt.*;

public class Cloud implements Drawable, Updatable {
    private double x;
    private final double y;
    private final double scale;
    private final double speed;

    public Cloud(double x, double y, double scale, double speed) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.speed = speed;
    }

    @Override
    public void update() {
        x += speed;
        if (x > 1250) {
            x = -200 * scale;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillOval((int) x, (int) y, (int) (150 * scale), (int) (50 * scale));
        g.fillOval((int) (x + 20 * scale), (int) (y - 20 * scale), (int) (60 * scale), (int) (60 * scale));
        g.fillOval((int) (x + 60 * scale), (int) (y - 30 * scale), (int) (70 * scale), (int) (70 * scale));
    }
}