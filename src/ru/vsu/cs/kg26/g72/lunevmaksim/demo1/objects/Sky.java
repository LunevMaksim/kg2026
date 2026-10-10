package ru.vsu.cs.kg26.g72.lunevmaksim.demo1.objects;

import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Drawable;
import java.awt.*;

public class Sky implements Drawable {
    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, 1200, 700);
    }
}