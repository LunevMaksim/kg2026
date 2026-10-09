package ru.vsu.cs.kg26.g72.lunevmaksim.demo1;

import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Drawable;
import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.models.Updatable;
import ru.vsu.cs.kg26.g72.lunevmaksim.demo1.objects.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawPanel extends JPanel {

    private final List<Drawable> drawables = new ArrayList<>();
    private final List<Updatable> updatables = new ArrayList<>();

    public DrawPanel() {
        initScene();

        // Главный таймер обновления кадров
        Timer timer = new Timer(33, e -> {
            for (Updatable u : updatables) {
                u.update();
            }
            repaint();
        });
        timer.start();
    }

    private void initScene() {
        Random random = new Random();

        // 1. Статические фоновые объекты
        // (Создай для них простые классы, реализующие Drawable, с кодом рисования)
        // addGameObject(new Sky());
        // addGameObject(new Sun());
        // addGameObject(new Ground());
        // addGameObject(new Path());
        // addGameObject(new Fence());
        // addGameObject(new House());

        // 2. Дым
        for (int i = 0; i < 6; i++) {
            addGameObject(new SmokeParticle(260, 310 - i * 35, 30 + i * 8, 200 - i * 30));
        }

        // 3. Птицы
        addGameObject(new Bird(100, 150, 2.5, 0.0));
        addGameObject(new Bird(150, 130, 2.7, 0.5));
        addGameObject(new Bird(220, 170, 2.3, 1.0));

        // 4. Генерация деревьев в саду
        Color[] foliageColors = { new Color(34, 139, 34), new Color(46, 139, 87), new Color(60, 179, 113) };
        Color[] fruitColors = { Color.RED, new Color(255, 215, 0), new Color(255, 140, 0) };

        for (int i = 0; i < 3; i++) {
            int x = 480 + (i * 200) + random.nextInt(40);
            int y = 420 + random.nextInt(60);
            addGameObject(new Tree(x, y, foliageColors[random.nextInt(3)], fruitColors[random.nextInt(3)], 10));
        }
    }

    private void addGameObject(Object object) {
        if (object instanceof Drawable) drawables.add((Drawable) object);
        if (object instanceof Updatable) updatables.add((Updatable) object);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Drawable d : drawables) {
            d.draw(g2d);
        }
    }
}