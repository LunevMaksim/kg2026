package ru.vsu.cs.kg26.g72.lunevmaksim.demo1.objects;

import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Drawable;
import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Updatable;

import java.awt.*;
import java.util.Random;

public class SmokeParticle implements Drawable, Updatable {
    private double x, y, size, alpha;
    private static final Random random = new Random();

    public SmokeParticle(double x, double y, double size, double alpha) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.alpha = alpha;
    }

    @Override
    public void update() {
        y -= 2.0;
        x += 1.5;
        size += 0.4;
        alpha -= 1.8;

        if (alpha <= 0 || y < 50) {
            x = 255 + random.nextInt(10);
            y = 310;
            size = 25;
            alpha = 210;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        int a = (int) Math.max(0, Math.min(255, alpha));
        g.setColor(new Color(230, 230, 230, a));
        g.fillOval((int) x, (int) y, (int) size, (int) size);
    }
}