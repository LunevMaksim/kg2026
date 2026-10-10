package ru.vsu.cs.kg26.g72.lunevmaksim.demo1.objects;

import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Drawable;
import java.awt.*;

public class Sun implements Drawable {
    private final int x = 950;
    private final int y = 80;

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(255, 223, 0));
        g.fillOval(x, y, 100, 100);

        g.setStroke(new BasicStroke(2));
        for (int i = 0; i < 16; i++) {
            double angle = i * (2 * Math.PI / 16);
            int startX = x + 50 + (int) (60 * Math.cos(angle));
            int startY = y + 50 + (int) (60 * Math.sin(angle));
            int endX = x + 50 + (int) (85 * Math.cos(angle));
            int endY = y + 50 + (int) (85 * Math.sin(angle));
            g.drawLine(startX, startY, endX, endY);
        }
    }
}