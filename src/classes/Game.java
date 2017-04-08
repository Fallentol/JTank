package classes;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    public static boolean running;

    public static String NAME = "TANKS V1.0"; //заголовок окна
    public Tank myTank;
    public ArrayList<Tank> tanksList = new ArrayList<>();
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean firePressed = false;
    ArrayList<Sprite> allSpites = new ArrayList<>();


    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        long delta;
        init();
        while (running) {
            delta = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            update(delta);
            render();
        }
    }

    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void update(long delta) {

        if (leftPressed == true) {
            myTank.move("LEFT");
        }
        if (rightPressed == true) {
            myTank.move("RIGHT");
        }
        if (upPressed == true) {
            myTank.move("UP");
        }
        if (downPressed == true) {
            myTank.move("DOWN");
        }
        if (firePressed == true) {
            if (!myTank.weapon.isFly()) {
                myTank.startFire();
            }
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            requestFocus();
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (Tank t : tanksList) {
            if (t.dead) continue;
            allSpites.add(t);
        }
        allSpites.add(myTank);
        if (myTank.weapon.isFly()) {
            Bullet b = (Bullet) myTank.weapon;
            allSpites.add(b);
        }
        Controller.manageInterceptions(allSpites);

        myTank.drawTank(g);
        for (Tank t : tanksList) {
            t.drawTank(g);
        }
        outLOG(g, myTank, null);
        g.dispose();
        bs.show(); //показать
        allSpites = new ArrayList<>();
    }

    public void init() {
        myTank = new Tank();
        addKeyListener(new KeyInputHandler());
        Tank enemy = new Tank();
        enemy.coordX = 200;
        enemy.coordY = 200;
        tanksList.add(enemy);
        enemy = new Tank();
        enemy.coordX = 250;
        enemy.coordY = 250;
        tanksList.add(enemy);
        enemy = new Tank();
        enemy.coordX = 100;
        enemy.coordY = 250;
        tanksList.add(enemy);
        enemy = new Tank();
        enemy.coordX = 150;
        enemy.coordY = 380;
        tanksList.add(enemy);

    }

    private void outLOG(Graphics g, Tank t, String s) {
        String log = "Coordinate X = " + t.getX() + " Coordinate Y =" + t.getY();
        int fontSize = 15;
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        g.setColor(Color.BLACK);
        g.drawString("TANK V1.0 LOG:", 10, 20);
        g.drawString(log, 10, 40);
        if (s != null) {
            g.drawString(s, 10, 60);
        }
    }


    public class KeyInputHandler extends KeyAdapter {

        public void keyPressed(KeyEvent e) { //клавиша нажата
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                firePressed = true;
            }

        }

        public void keyReleased(KeyEvent e) { //клавиша отпущена
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                upPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                downPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                firePressed = false;
            }
        }
    }

}
