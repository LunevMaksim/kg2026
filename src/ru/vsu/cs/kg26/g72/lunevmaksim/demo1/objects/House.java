package ru.vsu.cs.kg26.g72.lunevmaksim.demo1.objects;

import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Drawable;
import java.awt.*;

public class House implements Drawable {
    @Override
    public void draw(Graphics2D g) {
        // Дымоход
        g.setColor(new Color(178, 34, 34));
        g.fillRect(235, 325, 45, 150);
        g.fillRect(225, 310, 65, 15);
        g.setColor(Color.BLACK);
        g.drawRect(235, 325, 45, 150);
        g.drawRect(225, 310, 65, 15);

        // Крыша
        g.setColor(new Color(178, 34, 34));
        int[] xRoof = {15, 175, 335};
        int[] yRoof = {525, 360, 525};
        g.fillPolygon(xRoof, yRoof, 3);
        g.setColor(Color.BLACK);
        g.drawPolygon(xRoof, yRoof, 3);

        // Основание
        g.setColor(new Color(255, 239, 213));
        g.fillRect(25, 525, 300, 300);
        g.setColor(Color.BLACK);
        g.drawRect(25, 525, 300, 300);

        // Окна
        drawWindow(g, 60, 565);
        drawWindow(g, 200, 565);

        // Дверь
        g.setColor(new Color(139, 69, 19));
        g.fillRect(130, 725, 80, 100);
        g.setColor(Color.BLACK);
        g.drawRect(130, 725, 80, 100);
        g.setColor(Color.YELLOW);
        g.fillOval(135, 775, 8, 8);
    }

    private void drawWindow(Graphics2D g, int x, int y) {
        g.setColor(new Color(173, 216, 230));
        g.fillRect(x, y, 80, 90);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 80, 90);
        g.drawLine(x + 40, y, x + 40, y + 90);
        g.drawLine(x, y + 45, x + 80, y + 45);
    }
}