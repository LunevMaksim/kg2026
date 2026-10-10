package ru.vsu.cs.kg26.g72.lunevmaksim.demo1.objects;

import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Drawable;
import java.awt.*;

public class Fence implements Drawable {
    private final int startX = 0;
    private final int y = 780;
    private final int width = 450;

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(222, 184, 135));
        int plankWidth = 15;
        int gap = 10;

        // Поперечные планки
        g.fillRect(startX, y + 20, width, 8);
        g.fillRect(startX, y + 50, width, 8);

        // Вертикальные штакетники
        for (int px = startX; px < startX + width; px += plankWidth + gap) {
            int[] xP = {px, px + plankWidth / 2, px + plankWidth, px + plankWidth, px};
            int[] yP = {y + 10, y, y + 10, y + 70, y + 70};
            g.fillPolygon(xP, yP, 5);
            g.setColor(Color.BLACK);
            g.drawPolygon(xP, yP, 5);
            g.setColor(new Color(222, 184, 135));
        }
    }
}