package ru.vsu.cs.kg26.g72.lunevmaksim.demo1.objects;

import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Drawable;
import java.awt.*;

public class Ground implements Drawable {
    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(124, 252, 0));
        g.fillRect(0, 650, 1200, 350);
    }
}