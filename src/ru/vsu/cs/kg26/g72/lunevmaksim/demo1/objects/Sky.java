package ru.vsu.cs.kg26.g72.lunevmaksim.demo1.objects;

import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Drawable;
import java.awt.*;

public class Sky implements Drawable {
    private final int width;
    private final int height;

    public Sky(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, width, height);
    }
}