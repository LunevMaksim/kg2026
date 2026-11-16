package ru.vsu.cs.kg26.g72.lunevmaksim.demo1.objects;

import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Drawable;
import java.awt.*;

public class House implements Drawable {
    private final int x;
    private final int y;

    public House(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D g) {
        // 1. Дымоход
        g.setColor(new Color(178, 34, 34));
        g.fillRect(x + 210, y - 200, 45, 150);
        g.fillRect(x + 200, y - 215, 65, 15);
        g.setColor(Color.BLACK);
        g.drawRect(x + 210, y - 200, 45, 150);
        g.drawRect(x + 200, y - 215, 65, 15);

        // 2. Крыша
        g.setColor(new Color(178, 34, 34));
        int[] xRoof = {x - 10, x + 150, x + 310};
        int[] yRoof = {y, y - 165, y};
        g.fillPolygon(xRoof, yRoof, 3);
        g.setColor(Color.BLACK);
        g.drawPolygon(xRoof, yRoof, 3);

        // 3. Основание дома
        g.setColor(new Color(255, 239, 213));
        g.fillRect(x, y, 300, 300);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 300, 300);

        // 4. Окна
        drawWindow(g, x + 35, y + 40);
        drawWindow(g, x + 175, y + 40);

        // 5. Дверь
        g.setColor(new Color(139, 69, 19));
        g.fillRect(x + 105, y + 200, 80, 100);
        g.setColor(Color.BLACK);
        g.drawRect(x + 105, y + 200, 80, 100);

        // Ручка двери
        g.setColor(Color.YELLOW);
        g.fillOval(x + 110, y + 250, 8, 8);
    }

    private void drawWindow(Graphics2D g, int winX, int winY) {
        g.setColor(new Color(173, 216, 230));
        g.fillRect(winX, winY, 80, 90);
        g.setColor(Color.BLACK);
        g.drawRect(winX, winY, 80, 90);
        g.drawLine(winX + 40, winY, winX + 40, winY + 90);
        g.drawLine(winX, winY + 45, winX + 80, winY + 45);
    }
}