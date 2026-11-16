package ru.vsu.cs.kg26.g72.lunevmaksim.demo1.objects;

import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Drawable;
import java.awt.*;

public class Sun implements Drawable {
    private final int x;
    private final int y;
    private final int radius;

    public Sun(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw(Graphics2D g) {
        int diameter = radius * 2;
        int centerX = x + radius;
        int centerY = y + radius;

        g.setColor(new Color(255, 223, 0));
        g.fillOval(x, y, diameter, diameter);

        g.setStroke(new BasicStroke(2));
        for (int i = 0; i < 16; i++) {
            double angle = i * (2 * Math.PI / 16);
            int startX = centerX + (int) ((radius + 10) * Math.cos(angle));
            int startY = centerY + (int) ((radius + 10) * Math.sin(angle));
            int endX = centerX + (int) ((radius + 35) * Math.cos(angle));
            int endY = centerY + (int) ((radius + 35) * Math.sin(angle));
            g.drawLine(startX, startY, endX, endY);
        }
    }
}