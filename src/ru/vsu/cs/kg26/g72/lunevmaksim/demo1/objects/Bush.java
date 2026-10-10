package ru.vsu.cs.kg26.g72.lunevmaksim.demo1.objects;

import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Drawable;
import java.awt.*;

public class Bush implements Drawable {
    private final int x, y, size;
    private final Color bushColor;
    private final Color berryColor;

    public Bush(int x, int y, int size, Color bushColor, Color berryColor) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.bushColor = bushColor;
        this.berryColor = berryColor;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(bushColor);
        g.fillOval(x, y, size, size / 2 + 10);
        g.fillOval(x - 20, y + 10, size / 2 + 20, size / 2);
        g.fillOval(x + size / 2, y + 10, size / 2 + 20, size / 2);

        // Ягоды
        g.setColor(berryColor);
        g.fillOval(x + 15, y + 15, 12, 12);
        g.fillOval(x + 45, y + 10, 12, 12);
        g.fillOval(x + 75, y + 20, 12, 12);
        g.fillOval(x + 30, y + 30, 12, 12);
    }
}