package ru.vsu.cs.kg26.g72.lunevmaksim.demo1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawPanel extends JPanel {

    // Вспомогательный класс для частицы дыма
    private static class SmokeParticle {
        double x, y, size, alpha;

        public SmokeParticle(double x, double y, double size, double alpha) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.alpha = alpha;
        }
    }

    // Вспомогательный класс для анимированной птицы
    private static class Bird {
        double x, baseY, speed, wingPhase;

        public Bird(double x, double baseY, double speed, double wingPhase) {
            this.x = x;
            this.baseY = baseY;
            this.speed = speed;
            this.wingPhase = wingPhase;
        }
    }

    // Класс для параметров случайного дерева
    private static class TreeSpec {
        int x, y;
        Color foliageColor;
        Color fruitColor;
        int fruitCount;

        public TreeSpec(int x, int y, Color foliageColor, Color fruitColor, int fruitCount) {
            this.x = x;
            this.y = y;
            this.foliageColor = foliageColor;
            this.fruitColor = fruitColor;
            this.fruitCount = fruitCount;
        }
    }

    // Класс для параметров случайного куста
    private static class BushSpec {
        int x, y, size;
        Color bushColor;
        Color berryColor;

        public BushSpec(int x, int y, int size, Color bushColor, Color berryColor) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.bushColor = bushColor;
            this.berryColor = berryColor;
        }
    }

    private final List<SmokeParticle> smokeList = new ArrayList<>();
    private final List<Bird> birdList = new ArrayList<>();
    private final List<TreeSpec> treeList = new ArrayList<>();
    private final List<BushSpec> bushList = new ArrayList<>();

    private final Random random = new Random();
    private int cloudOffsetX = 0;
    private double animTime = 0;

    public DrawPanel() {
        // 1. Создаем начальные частицы дыма
        for (int i = 0; i < 6; i++) {
            smokeList.add(new SmokeParticle(260, 310 - i * 35, 30 + i * 8, 200 - i * 30));
        }

        // 2. Создаем птиц
        birdList.add(new Bird(100, 150, 2.5, 0.0));
        birdList.add(new Bird(150, 130, 2.7, 0.5));
        birdList.add(new Bird(220, 170, 2.3, 1.0));
        birdList.add(new Bird(600, 120, 3.0, 0.2));
        birdList.add(new Bird(660, 140, 2.8, 0.8));

        // 3. РАНДОМНАЯ ГЕНЕРАЦИЯ ДЕРЕВЬЕВ В САДУ (3-4 дерева)
        Color[] foliageColors = {
                new Color(34, 139, 34),   // Лесная зелень
                new Color(46, 139, 87),   // Морская зелень
                new Color(60, 179, 113),  // Светло-зеленый
                new Color(0, 128, 0)      // Классический зеленый
        };

        Color[] fruitColors = {
                Color.RED,                // Яблоко
                new Color(255, 215, 0),   // Груша (желтая)
                new Color(255, 140, 0),   // Апельсин
                new Color(147, 112, 219)  // Слива (фиолетовая)
        };

        int numberOfTrees = 3 + random.nextInt(2); // от 3 до 4 деревьев
        for (int i = 0; i < numberOfTrees; i++) {
            // Равномерно распределяем зоны по X справа от дома (от 480 до 1050)
            int x = 480 + (i * 200) + random.nextInt(40);
            int y = 420 + random.nextInt(60); // Небольшой случайный сдвиг по высоте
            Color foliage = foliageColors[random.nextInt(foliageColors.length)];
            Color fruit = fruitColors[random.nextInt(fruitColors.length)];
            int count = 8 + random.nextInt(5);

            treeList.add(new TreeSpec(x, y, foliage, fruit, count));
        }

        // 4. РАНДОМНАЯ ГЕНЕРАЦИЯ КУСТОВ (4-6 кустов)
        Color[] berryColors = {
                Color.RED,
                new Color(75, 0, 130),   // Темно-синяя ягода
                new Color(220, 20, 60),  // Малиновая
                new Color(0, 0, 128)     // Черника
        };

        int numberOfBushes = 4 + random.nextInt(3); // от 4 до 6 кустов
        for (int i = 0; i < numberOfBushes; i++) {
            int x = 450 + random.nextInt(650); // На пространстве сада
            int y = 700 + random.nextInt(80);  // На переднем/среднем плане травы
            int size = 90 + random.nextInt(40);
            Color bushColor = foliageColors[random.nextInt(foliageColors.length)];
            Color berryColor = berryColors[random.nextInt(berryColors.length)];

            bushList.add(new BushSpec(x, y, size, bushColor, berryColor));
        }

        // Таймер перерисовки (~30 FPS)
        Timer timer = new Timer(33, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animTime += 0.15;

                // Дым
                for (SmokeParticle particle : smokeList) {
                    particle.y -= 2.0;
                    particle.x += 1.5;
                    particle.size += 0.4;
                    particle.alpha -= 1.8;

                    if (particle.alpha <= 0 || particle.y < 50) {
                        particle.x = 255 + random.nextInt(10);
                        particle.y = 310;
                        particle.size = 25;
                        particle.alpha = 210;
                    }
                }

                // Облака
                cloudOffsetX += 1;
                if (cloudOffsetX > 1200) {
                    cloudOffsetX = -400;
                }

                // Птицы
                for (Bird bird : birdList) {
                    bird.x += bird.speed;
                    if (bird.x > 1250) {
                        bird.x = -60;
                        bird.baseY = 100 + random.nextInt(120);
                    }
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

        // --- 3. Облака ---
        drawCloud(g, 100 + cloudOffsetX, 100, 1.0);
        drawCloud(g, -500 + cloudOffsetX, 150, 0.8);
        drawCloud(g, 600 + cloudOffsetX, 80, 1.2);

        // --- 4. Птицы ---
        for (Bird bird : birdList) {
            double currentY = bird.baseY + Math.sin(animTime + bird.wingPhase) * 5.0;
            double wingFlap = Math.sin(animTime * 2 + bird.wingPhase) * 10.0;
            drawAnimatedBird(g, (int) bird.x, (int) currentY, wingFlap);
        }

        // --- 5. Трава ---
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

        // --- 8. Анимация дыма ---
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

        // Дверь
        g.setColor(new Color(139, 69, 19));
        g.fillRect(130, 725, 80, 100);
        g.setColor(Color.BLACK);
        g.drawRect(130, 725, 80, 100);
        g.setColor(Color.YELLOW);
        g.fillOval(135, 775, 8, 8);

        // --- 11. Забор перед домом ---
        drawFence(g, 0, 780, 450);

        // --- 12. ОТРИСОВКА СГЕНЕРИРОВАННЫХ ДЕРЕВЬЕВ ---
        for (TreeSpec tree : treeList) {
            drawDetailedTree(g, tree.x, tree.y, tree.foliageColor);
            drawFruit(g, tree.x, tree.y, tree.fruitColor, tree.fruitCount);
        }

        // --- 13. ОТРИСОВКА СГЕНЕРИРОВАННЫХ КУСТОВ ---
        for (BushSpec bush : bushList) {
            drawDetailedBush(g, bush.x, bush.y, bush.size, bush.bushColor);
            drawFruitOnBush(g, bush.x, bush.y, bush.berryColor);
        }
    }

    private void drawAnimatedBird(Graphics2D g, int x, int y, double wingFlap) {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        int wingHeight = (int) Math.max(5, 15 + wingFlap);
        g.drawArc(x, y, 20, wingHeight, 0, 180);
        g.drawArc(x + 20, y, 20, wingHeight, 0, 180);
    }

    private void drawWindow(Graphics2D g, int x, int y) {
        g.setColor(new Color(173, 216, 230));
        g.fillRect(x, y, 80, 90);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 80, 90);
        g.drawLine(x + 40, y, x + 40, y + 90);
        g.drawLine(x, y + 45, x + 80, y + 45);
    }

    private void drawCloud(Graphics2D g, int x, int y, double scale) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, (int)(150 * scale), (int)(50 * scale));
        g.fillOval(x + (int)(20 * scale), y - (int)(20 * scale), (int)(60 * scale), (int)(60 * scale));
        g.fillOval(x + (int)(60 * scale), y - (int)(30 * scale), (int)(70 * scale), (int)(70 * scale));
    }

    private void drawDetailedTree(Graphics2D g, int x, int y, Color foliageColor) {
        g.setColor(new Color(101, 67, 33));
        g.fillRect(x + 35, y + 100, 40, 150);

        g.setColor(foliageColor);
        int[][] leafOffsets = {
                {-30, 0}, {0, -40}, {40, -30}, {70, 10}, {50, 50},
                {10, 60}, {-30, 40}, {0, 0}, {20, 20}, {-10, -20}
        };

        for (int[] offset : leafOffsets) {
            g.fillOval(x + offset[0], y + offset[1], 70, 70);
        }
    }

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

    private void drawDetailedBush(Graphics2D g, int x, int y, int size, Color color) {
        g.setColor(color);
        g.fillOval(x, y, size, size / 2 + 10);
        g.fillOval(x - 20, y + 10, size / 2 + 20, size / 2);
        g.fillOval(x + size / 2, y + 10, size / 2 + 20, size / 2);
    }

    private void drawFruitOnBush(Graphics2D g, int x, int y, Color color) {
        g.setColor(color);
        g.fillOval(x + 15, y + 15, 12, 12);
        g.fillOval(x + 45, y + 10, 12, 12);
        g.fillOval(x + 75, y + 20, 12, 12);
        g.fillOval(x + 30, y + 30, 12, 12);
    }

    private void drawFence(Graphics2D g, int startX, int y, int width) {
        g.setColor(new Color(222, 184, 135));
        int plankWidth = 15;
        int gap = 10;

        g.fillRect(startX, y + 20, width, 8);
        g.fillRect(startX, y + 50, width, 8);

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