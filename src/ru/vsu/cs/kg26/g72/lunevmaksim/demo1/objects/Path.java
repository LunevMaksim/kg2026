package ru.vsu.cs.kg26.g72.lunevmaksim.demo1.objects;

import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Drawable;
import java.awt.*;

public class Path implements Drawable {
    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(210, 180, 140));
        int[] xPath = {130, 210, 260, 120};
        int[] yPath = {825, 825, 1000, 1000};
        g.fillPolygon(xPath, yPath, 4);
    }
}