package ru.vsu.cs.kg26.g72.lunevmaksim.demo1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawPanel extends JPanel {

    // Класс для частицы дыма с прозрачностью и увеличением
    private static class SmokeParticle {
        double x, y;
        double size;
        double alpha; // 0.0 ... 255.0

        public SmokeParticle(double x, double y, double size, double alpha) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.alpha = alpha;
        }
    }

    private final List<SmokeParticle> smokeList = new ArrayList<>();
    private final Random random = new Random();
    private int cloudOffsetX = 0; // Для анимации облаков и ветра

    public DrawPanel() {
        // Инициализируем начальные частицы дыма
        for (int i = 0; i < 6; i++) {
            smokeList.add(new SmokeParticle(260, 310 - i * 35, 30 + i * 8, 200 - i * 30));
        }

        // Таймер обновления кадра (~30 FPS)
        Timer timer = new Timer(33, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Анимация дыма
                for (SmokeParticle particle : smokeList) {
                    particle.y -= 2.0;            // Дым поднимается
                    particle.x += 1.5;            // Снос ветром вправо
                    particle.size += 0.4;         // Увеличение в размере
                    particle.alpha -= 1.8;        // Постепенное исчезновение

                    // Сброс частицы при выходе за пределы или полная прозрачность
                    if (particle.alpha <= 0 || particle.y < 50) {
                        particle.x = 255 + random.nextInt(10);
                        particle.y = 310;
                        particle.size = 25;
                        particle.alpha = 210;
                    }
                }

                // Анимация облаков (ветер)
                cloudOffsetX += 1;
                if (cloudOffsetX > 1200) {
                    cloudOffsetX = -400;
                }

                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;

        // Включаем сглаживание (Anti-aliasing) для красивой графики
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // --- 1. Небо ---
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, 1200, 700);

        // --- 2. Солнце ---
        g.setColor(new Color(255, 223, 0));
        g.fillOval(950, 80, 100, 100);
        g.setStroke(new BasicStroke(2));
        for (int i = 0; i < 16; i++) {
            double angle = i * (2 * Math.PI / 16);
            int startX = 1000 + (int) (60 * Math.cos(angle));
            int startY = 130 + (int) (60 * Math.sin(angle));
            int endX = 1000 + (int) (85 * Math.cos(angle));
            int endY = 130 + (int) (85 * Math.sin(angle));
            g.drawLine(startX, startY, endX, endY);
        }

        // --- 3. Облака (движущиеся) ---
        drawCloud(g, 100 + cloudOffsetX, 100, 1.0);
        drawCloud(g, -500 + cloudOffsetX, 150, 0.8);
        drawCloud(g, 600 + cloudOffsetX, 80, 1.2);

        // --- 4. Птицы в небе ---
        drawBird(g, 400, 150);
        drawBird(g, 430, 135);
        drawBird(g, 470, 160);
        drawBird(g, 750, 200);

        // --- 5. Земля / Трава ---
        g.setColor(new Color(124, 252, 0));
        g.fillRect(0, 650, 1200, 350);

        // --- 6. Дорожка к дому ---
        g.setColor(new Color(210, 180, 140));
        int[] xPath = {130, 210, 260, 120};
        int[] yPath = {825, 825, 1000, 1000};
        g.fillPolygon(xPath, yPath, 4);

        // --- 7. Дымоход ---
        g.setColor(new Color(178, 34, 34));
        g.fillRect(235, 325, 45, 150);
        g.fillRect(225, 310, 65, 15);
        g.setColor(Color.BLACK);
        g.drawRect(235, 325, 45, 150);
        g.drawRect(225, 310, 65, 15);

        // --- 8. Полупрозрачный анимационный дым ---
        for (SmokeParticle particle : smokeList) {
            int a = (int) Math.max(0, Math.min(255, particle.alpha));
            g.setColor(new Color(230, 230, 230, a));
            g.fillOval((int) particle.x, (int) particle.y, (int) particle.size, (int) particle.size);
        }

        // --- 9. Крыша дома ---
        g.setColor(new Color(178, 34, 34));
        int[] xRoof = {15, 175, 335};
        int[] yRoof = {525, 360, 525};
        g.fillPolygon(xRoof, yRoof, 3);
        g.setColor(Color.BLACK);
        g.drawPolygon(xRoof, yRoof, 3);

        // --- 10. Основание дома ---
        g.setColor(new Color(255, 239, 213));
        g.fillRect(25, 525, 300, 300);
        g.setColor(Color.BLACK);
        g.drawRect(25, 525, 300, 300);

        // Окна
        drawWindow(g, 60, 565);
        drawWindow(g, 200, 565);

        // Дверь и ручка
        g.setColor(new Color(139, 69, 19));
        g.fillRect(130, 725, 80, 100);
        g.setColor(Color.BLACK);
        g.drawRect(130, 725, 80, 100);
        g.setColor(Color.YELLOW);
        g.fillOval(135, 775, 8, 8);

        // --- 11. Низкий заборчик перед домом ---
        drawFence(g, 0, 780, 450);

        // --- 12. Фруктовый сад (несколько деревьев) ---
        // Дерево 1 (Яблоня)
        drawDetailedTree(g, 650, 450, new Color(34, 139, 34));
        drawFruit(g, 650, 450, Color.RED, 12); // Яблоки

        // Дерево 2 (Груша)
        drawDetailedTree(g, 880, 420, new Color(46, 139, 87));
        drawFruit(g, 880, 420, new Color(255, 215, 0), 10); // Груши/Желтые фрукты

        // Дерево 3 (Слива/Апельсин)
        drawDetailedTree(g, 1050, 480, new Color(60, 179, 113));
        drawFruit(g, 1050, 480, new Color(255, 140, 0), 10); // Апельсины

        // --- 13. Несколько детализированных кустов ---
        drawDetailedBush(g, 480, 700, 100, new Color(0, 100, 0));
        drawFruitOnBush(g, 480, 700, Color.RED);

        drawDetailedBush(g, 580, 730, 120, new Color(34, 139, 34));
        drawFruitOnBush(g, 580, 730, new Color(75, 0, 130)); // Синие ягоды

        drawDetailedBush(g, 920, 750, 110, new Color(0, 128, 0));
        drawFruitOnBush(g, 920, 750, Color.RED);
    }

    // Вспомогательный метод: Рисование окна
    private void drawWindow(Graphics2D g, int x, int y) {
        g.setColor(new Color(173, 216, 230));
        g.fillRect(x, y, 80, 90);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 80, 90);
        g.drawLine(x + 40, y, x + 40, y + 90);
        g.drawLine(x, y + 45, x + 80, y + 45);
    }

    // Вспомогательный метод: Рисование облака
    private void drawCloud(Graphics2D g, int x, int y, double scale) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, (int)(150 * scale), (int)(50 * scale));
        g.fillOval(x + (int)(20 * scale), y - (int)(20 * scale), (int)(60 * scale), (int)(60 * scale));
        g.fillOval(x + (int)(60 * scale), y - (int)(30 * scale), (int)(70 * scale), (int)(70 * scale));
    }

    // Вспомогательный метод: Рисование птицы
    private void drawBird(Graphics2D g, int x, int y) {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        g.drawArc(x, y, 20, 15, 0, 180);
        g.drawArc(x + 20, y, 20, 15, 0, 180);
    }

    // Вспомогательный метод: Детализированное дерево с кучей листиков
    private void drawDetailedTree(Graphics2D g, int x, int y, Color foliageColor) {
        // Ствол
        g.setColor(new Color(101, 67, 33));
        g.fillRect(x + 35, y + 100, 40, 150);

        // Крона из множества накладывающихся кругов (имитация листвы)
        g.setColor(foliageColor);
        int[][] leafOffsets = {
                {-30, 0}, {0, -40}, {40, -30}, {70, 10}, {50, 50},
                {10, 60}, {-30, 40}, {0, 0}, {20, 20}, {-10, -20}
        };

        for (int[] offset : leafOffsets) {
            g.fillOval(x + offset[0], y + offset[1], 70, 70);
        }
    }

    // Вспомогательный метод: Фрукты на дереве
    private void drawFruit(Graphics2D g, int x, int y, Color color, int count) {
        g.setColor(color);
        int[][] fruitPositions = {
                {0, 10}, {30, -10}, {60, 20}, {10, 40}, {40, 50},
                {-10, 30}, {70, 50}, {20, 70}, {50, 80}, {0, 80}
        };

        for (int i = 0; i < Math.min(count, fruitPositions.length); i++) {
            g.fillOval(x + fruitPositions[i][0] + 15, y + fruitPositions[i][1] + 10, 16, 16);
        }
    }

    // Вспомогательный метод: Детализированный куст
    private void drawDetailedBush(Graphics2D g, int x, int y, int size, Color color) {
        g.setColor(color);
        g.fillOval(x, y, size, size / 2 + 10);
        g.fillOval(x - 20, y + 10, size / 2 + 20, size / 2);
        g.fillOval(x + size / 2, y + 10, size / 2 + 20, size / 2);
    }

    // Вспомогательный метод: Ягоды на кусте
    private void drawFruitOnBush(Graphics2D g, int x, int y, Color color) {
        g.setColor(color);
        g.fillOval(x + 15, y + 15, 12, 12);
        g.fillOval(x + 45, y + 10, 12, 12);
        g.fillOval(x + 75, y + 20, 12, 12);
        g.fillOval(x + 30, y + 30, 12, 12);
    }

    // Вспомогательный метод: Заборчик
    private void drawFence(Graphics2D g, int startX, int y, int width) {
        g.setColor(new Color(222, 184, 135)); // Деревянный цвет
        int plankWidth = 15;
        int gap = 10;

        // Поперечные перекладины
        g.fillRect(startX, y + 20, width, 8);
        g.fillRect(startX, y + 50, width, 8);

        // Вертикальные дощечки
        for (int px = startX; px < startX + width; px += plankWidth + gap) {
            int[] xP = {px, px + plankWidth / 2, px + plankWidth, px + plankWidth, px};
            int[] yP = {y + 10, y, y + 10, y + 70, y + 70};
            g.fillPolygon(xP, yP, 5);
            g.setColor(Color.BLACK);
            g.drawPolygon(xP, yP, 5);
            g.setColor(new Color(222, 184, 135));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Сад и дом - Задание по КГ");
        DrawPanel panel = new DrawPanel();

        frame.add(panel);
        frame.setSize(1200, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}