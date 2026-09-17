package ru.vsu.cs.kg26.g72.lunevmaksim.demo1;

import javax.swing.*;

public class MainWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel paintArea;
    private DrawPanel dp;

    public MainWindow() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        dp = new DrawPanel();
        paintArea.add(dp);
    }

    public static void main(String[] args) {
        MainWindow dialog = new MainWindow();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
