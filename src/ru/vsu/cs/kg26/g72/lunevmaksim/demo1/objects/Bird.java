package ru.vsu.cs.kg26.g72.lunevmaksim.demo1.objects;

import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Drawable;
import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Updatable;

import java.awt.*;
import java.util.Random;

public class Bird implements Drawable, Updatable {
    private double x, baseY, speed, wingPhase;
    private double animTime = 0;
    private static final Random random = new Random();

    public Bird(double x, double baseY, double speed, double wingPhase) {
        this.x = x;
        this.baseY = baseY;
        this.speed = speed;
        this.wingPhase = wingPhase;
    }

    @Override
    public void update() {
        x += speed;
        animTime += 0.15;
        if (x > 1250) {
            x = -60;
            baseY = 100 + random.nextInt(120);
        }
    }

    @Override
    public void draw(Graphics2D g) {
        double currentY = baseY + Math.sin(animTime + wingPhase) * 5.0;
        double wingFlap = Math.sin(animTime * 2 + wingPhase) * 10.0;
        int wingHeight = (int) Math.max(5, 15 + wingFlap);

        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        g.drawArc((int) x, (int) currentY, 20, wingHeight, 0, 180);
        g.drawArc((int) x + 20, (int) currentY, 20, wingHeight, 0, 180);
    }
}