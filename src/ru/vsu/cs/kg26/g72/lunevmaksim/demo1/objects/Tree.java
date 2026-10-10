package ru.vsu.cs.kg26.g72.lunevmaksim.demo1.objects;

import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Drawable;

import java.awt.*;

public class Tree implements Drawable {
    private final int x, y;
    private final Color foliageColor;
    private final Color fruitColor;
    private final int fruitCount;

    public Tree(int x, int y, Color foliageColor, Color fruitColor, int fruitCount) {
        this.x = x;
        this.y = y;
        this.foliageColor = foliageColor;
        this.fruitColor = fruitColor;
        this.fruitCount = fruitCount;
    }

    @Override
    public void draw(Graphics2D g) {
        // Ствол
        g.setColor(new Color(101, 67, 33));
        g.fillRect(x + 35, y + 100, 40, 150);

        // Листва
        g.setColor(foliageColor);
        int[][] leafOffsets = {
                {-30, 0}, {0, -40}, {40, -30}, {70, 10}, {50, 50},
                {10, 60}, {-30, 40}, {0, 0}, {20, 20}, {-10, -20}
        };
        for (int[] offset : leafOffsets) {
            g.fillOval(x + offset[0], y + offset[1], 70, 70);
        }

        // Фрукты
        g.setColor(fruitColor);
        int[][] fruitPositions = {
                {0, 10}, {30, -10}, {60, 20}, {10, 40}, {40, 50},
                {-10, 30}, {70, 50}, {20, 70}, {50, 80}, {0, 80}
        };
        for (int i = 0; i < Math.min(fruitCount, fruitPositions.length); i++) {
            g.fillOval(x + fruitPositions[i][0] + 15, y + fruitPositions[i][1] + 10, 16, 16);
        }
    }
}