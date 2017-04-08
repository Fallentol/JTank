package classes;

import java.awt.*;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;


class Tank extends Sprite {
    public Weapon weapon;
    final float speed = 0.1f;

    public Tank() {
        mapOfImages = new HashMap<>();
        mapOfImages.put("LEFT", getImageFromString("\\images\\Tank.png"));
        mapOfImages.put("UP", getImageFromString("\\images\\TankUp.png"));
        mapOfImages.put("DOWN", getImageFromString("\\images\\TankDown.png"));
        mapOfImages.put("RIGHT", getImageFromString("\\images\\TankLeft.png"));
        ident = Configurator.getUniqueTankIdent();
        weapon = new Bullet(ident);
    }

    public void move(String s) {
        position = s;
        if (intercept && s.equals("UP")) s = "DOWN";
        if (intercept && s.equals("DOWN")) s = "UP";
        if (intercept && s.equals("RIGHT")) s = "LEFT";
        if (intercept && s.equals("LEFT")) s = "RIGHT";
        if (s.equals("UP")) {
            if (coordY - speed <= 0) {
                coordY = 0;
            } else {
                coordY -= speed;
            }
        }
        if (s.equals("DOWN")) {
            if (coordY + speed >= Configurator.HEIGHT - 40) {
                coordY = Configurator.HEIGHT - 40;
            } else {
                coordY += speed;
            }
        }
        if (s.equals("LEFT")) {
            if (coordX - speed <= 0) {
                coordX = 0;
            } else {
                coordX -= speed;
            }
        }
        if (s.equals("RIGHT")) {
            if (coordX + speed > Configurator.WIDTH - 40) {
                coordX = Configurator.WIDTH - 40;
            } else {
                coordX += speed;
            }
        }
    }

    public void startFire() {
        weapon.loadTrunk((int) coordX + 20, (int) coordY + 20, position);
    }

    public void drawTank(Graphics g) {
        if (dead) return;
        if (!ident.equals("Tank 0")) {
            calculateMoving();
        }
        draw(g);
        if (weapon.isFly()) {
            weapon.renderFire(g);
        }
    }

    int similarSteps = 0;
    String currentMoving = "UP";
    int stepOfChanging = 1000; // через сколько шагов менять направление двжения

    private void calculateMoving() {

        if (similarSteps == 0 || similarSteps == stepOfChanging) {
            int dir = (int) (Math.random() * 4);
            if (dir == 0) {
                currentMoving = "UP";
            } else if (dir == 1) {
                currentMoving = "DOWN";
            } else if (dir == 2) {
                currentMoving = "LEFT";
            } else {
                currentMoving = "RIGHT";
            }
        }
        float oldX = coordX;
        float oldY = coordY;
        if (currentMoving.equals("UP")) {
            move("UP");
        } else if (currentMoving.equals("DOWN")) {
            move("DOWN");
        } else if (currentMoving.equals("LEFT")) {
            move("LEFT");
        } else {
            move("RIGHT");
        }
        boolean stack = (coordX == oldX && coordY == oldY); // признак того что такн застраял
        similarSteps++;
        if (similarSteps > stepOfChanging || stack) {
            similarSteps = 0;
            stepOfChanging = ThreadLocalRandom.current().nextInt(1000, 8_000);
        }
    }
}
