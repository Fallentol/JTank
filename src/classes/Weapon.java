package classes;

import java.awt.*;

public interface Weapon {
    void loadTrunk (int x, int y, String d);
    void renderFire(Graphics g);
    boolean isFly();
}
