package ru.vsu.cs.kg26.g72.lunevmaksim.demo1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 1200 x 1000
public class DrawPanel extends JPanel {
    // Массив для хранения высоты (Y) трех клубов дыма
    private final int[] smokeY = new int[]{310, 260, 210};
    // Массив для горизонтального смещения (X) каждого клуба дыма
    private final int[] smokeX = new int[]{245, 235, 255};
    // Массив для размеров (диаметра) клубов дыма
    private final int[] smokeSize = new int[]{40, 50, 65};

    public DrawPanel() {
        // Таймер обновляет анимацию каждые 40 миллисекунд (~25 FPS)
        Timer timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < smokeY.length; i++) {
                    smokeY[i] -= 2; // Дым поднимается вверх

                    // Небольшое покачивание влево-вправо от ветра
                    if (smokeY[i] % 10 == 0) {
                        smokeX[i] += (Math.random() > 0.5) ? 3 : -3;
                    }

                    // Если дым поднялся слишком высоко, возвращаем его к дымоходу
                    if (smokeY[i] < 50) {
                        smokeY[i] = 255;
                        smokeX[i] = 245 + (int)(Math.random() * 15 - 7); // Сброс позиции X с небольшим разбросом
                    }
                }
                repaint(); // Перерисовываем всю панель
            }
        });
        timer.start(); // Запуск таймера при создании панели
    }
    @Override
    public void paint(Graphics gr) {
        AnimationExample panel = new AnimationExample();
        Graphics2D g = (Graphics2D) gr;
        super.paint(g);

        // Земля
        gr.setColor(new Color(154, 205, 50));
        gr.fillRect(-1, 700, 1201, 501);

        // Небо
        gr.setColor(new Color(0, 191, 255));
        gr.fillRect(-1, 0, 1201, 700);

        // Облака
        gr.setColor(Color.WHITE);
        gr.fillOval(75, 100, 300, 75);
        gr.fillOval(130, 75, 90, 90);
        gr.fillOval(200, 60, 100, 100);

        gr.fillOval(500, 200, 200, 50);
        gr.fillOval(550, 180, 60, 60);
        gr.fillOval(585, 175, 80, 70);

        // Основание дома
        gr.setColor(new Color(255, 239, 213));
        gr.fillRect(25, 525, 300, 300);
        gr.setColor(Color.BLACK);
        gr.drawRect(25, 525, 300, 300);

        // Дымоход
        gr.setColor(Color.RED);
        gr.fillRect(235, 325, 50, 200);
        gr.fillRect(217, 310, 85, 15);
        gr.setColor(Color.BLACK);
        gr.drawRect(235, 325, 50, 200);
        gr.drawRect(217, 310, 85, 15);

        // Анимация дыма из дымохода
        gr.setColor(new Color(220, 220, 220));
        for (int i = 0; i < smokeY.length; i++) {
            // Центрируем круги по мере их увеличения
            int currentX = smokeX[i] - (smokeSize[i] / 2) + 15;
            gr.fillOval(currentX, smokeY[i], smokeSize[i], smokeSize[i]);
        }

        // Крыша дома
        gr.setColor(Color.RED);
        int[] xPoints = {25, 175, 325};
        int[] yPoints = {525, 350, 525};
        gr.fillPolygon(xPoints, yPoints, 3);
        gr.setColor(Color.BLACK);
        gr.drawLine(175, 350, 25, 525);
        gr.drawLine(175, 350, 325, 525);

        // Окна дома
        gr.setColor(Color.CYAN);
        gr.fillRect(60, 565, 80, 100);
        gr.fillRect(200, 565, 80, 100);
        gr.setColor(Color.BLACK);
        gr.drawRect(60, 565, 80, 100);
        gr.drawRect(200, 565, 80, 100);

        gr.drawLine(100, 565, 100, 665);
        gr.drawLine(60, 615, 140, 615);
        gr.drawLine(240, 565, 240, 665);
        gr.drawLine(200, 615, 280, 615);


        // Дверь дома
        gr.setColor(new Color(139, 69, 19));
        gr.fillRect(130, 725, 80, 100);
        gr.setColor(Color.BLACK);
        gr.drawRect(130, 725, 80, 100);

        // Ручка двери дома
        gr.fillRect(135, 775, 10, 10);

        // Солнце
        gr.setColor(Color.YELLOW);
        ((Graphics2D) gr).setStroke(new BasicStroke(2));
        for (int i = 0; i < 21; i++) {
            double angle = i * (2 * Math.PI / 21);
            int endX = 950 + (int) (90 * Math.cos(angle));
            int endY = 200 + (int) (90 * Math.sin(angle));
            gr.drawLine(950, 200, endX, endY);
        }
        ((Graphics2D) gr).setStroke(new BasicStroke(1));
        gr.setColor(Color.YELLOW);
        gr.fillOval(900, 150, 100, 100);

        // Ствол дерева
        gr.setColor(new Color(139, 69, 19));
        gr.fillRect(1000, 550, 75, 200);
        gr.setColor(Color.BLACK);
        gr.drawRect(1000, 550, 75, 200);

        // Листва дерева
        gr.setColor(new Color(0, 128, 0));
        gr.fillOval(955, 500, 165, 165);
        gr.setColor(Color.BLACK);
        gr.drawOval(955, 500, 165, 165);

        // Яблоки на дереве
        gr.setColor(Color.RED);
        gr.fillOval(985, 545, 20, 20);
        gr.fillOval(1025, 525, 20, 20);
        gr.fillOval(1075, 540, 20, 20);
        gr.fillOval(1030, 570, 20, 20);
        gr.fillOval(985, 600, 20, 20);
        gr.fillOval(1030, 620, 20, 20);
        gr.fillOval(1070, 595, 20, 20);

        // Кустик
        gr.setColor(new Color(0, 128, 0));
        gr.fillOval(950, 850, 80, 80);
        gr.fillOval(1000, 850, 80, 80);
        gr.fillOval(930, 880, 100, 100);
        gr.fillOval(960, 880, 100, 100);
        gr.fillOval(990, 880, 100, 100);

        // Ягоды на кустиках
        gr.setColor(new Color(0, 0, 128));
        gr.fillOval(980, 870, 20, 20);
        gr.fillOval(1030, 870, 20, 20);
        gr.fillOval(960, 925, 20, 20);
        gr.fillOval(1000, 925, 20, 20);
        gr.fillOval(1040, 925, 20, 20);
    }
}
