package application;

import engine.Engine;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game Engine");

        Engine engine = new Engine();
        window.add(engine);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        engine.setup();
        engine.startEngineThread();
    }

}
