package ru.vsu.cs.kg26.g72.lunevmaksim.demo1.objects;

import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Drawable;
import java.awt.*;

public class Ground implements Drawable {
    private final int y;
    private final int width;
    private final int height;

    public Ground(int y, int width, int height) {
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(124, 252, 0));
        g.fillRect(0, y, width, height);
    }
}