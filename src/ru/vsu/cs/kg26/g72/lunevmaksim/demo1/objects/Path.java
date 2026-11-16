package ru.vsu.cs.kg26.g72.lunevmaksim.demo1.objects;

import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Drawable;
import java.awt.*;

public class Path implements Drawable {
    private final int x;
    private final int y;

    public Path(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(210, 180, 140));
        int[] xPath = {x, x + 80, x + 130, x - 10};
        int[] yPath = {y, y, y + 175, y + 175};
        g.fillPolygon(xPath, yPath, 4);
    }
}