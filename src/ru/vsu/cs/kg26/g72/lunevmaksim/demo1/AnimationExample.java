package ru.vsu.cs.kg26.g72.lunevmaksim.demo1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationExample extends JPanel implements ActionListener {
    private int x = 243;
    private int y = 280;
    private Timer timer;

    public AnimationExample() {
        // Создаем таймер с интервалом 10 миллисекунд
        timer = new Timer(10, this);
        timer.start(); // Запускаем анимацию
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(128,128, 128));
        // Рисуем круг, который движется по горизонтали
        g.fillOval(x, y, 35, 35);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Изменяем координаты для следующего кадра
        y -= 1;
        if (y < getHeight()) {
            y = 280; // Возвращаем объект в начало, если он ушел за экран
        }
        repaint(); // Перерисовываем панель
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Пример анимации в Swing");
        AnimationExample panel = new AnimationExample();

        frame.add(panel);
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

