package classes;

import java.awt.*;

class Bullet extends Sprite implements Weapon {

    private String direction;
    private int velocity;
    private boolean nowFlying = false;


    public boolean isFly() {
        return nowFlying;
    }

    public Bullet(String h) {
        this.host = h;
        this.velocity = 1;
        this.imageHeight = 15;
        this.imageWidth = 15;
        this.image = getImageFromString("\\images\\Cannonball.png");
        this.ident = Configurator.getUniqueWeaponIdent();
    }

    private void calculatingFly() {
        if (direction.equals("UP")) {
            coordY -= velocity;
        } else if (direction.equals("DOWN")) {
            coordY += velocity;
        } else if (direction.equals("RIGHT")) {
            coordX += velocity;
        } else if (direction.equals("LEFT")) {
            coordX -= velocity;
        }
        if (coordY > Configurator.HEIGHT || coordY < 0 || coordX < 0 || coordX > Configurator.WIDTH) nowFlying = false;
    }

    public void loadTrunk(int x, int y, String d) {
        coordX = x;
        coordY = y;
        direction = d;
        nowFlying = true;
        Thread soundThread = new Sound();
        soundThread.start();
    }

    public void renderFire(Graphics g) {
        if (dead) nowFlying = false;
        dead = false;
        calculatingFly();
        drawSimple(g);
    }
}
