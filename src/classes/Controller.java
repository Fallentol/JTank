package classes;

import java.util.ArrayList;

/**
 * Created bcoordY New on 4/8/2017.
 */
public class Controller {

    public static void manageInterceptions(ArrayList<Sprite> sprites) {
        Sprite comparedSprite;
        for (int i = 0; i < sprites.size(); i++) {
            comparedSprite = sprites.get(i);
            for (int j = i; j < sprites.size(); j++) {
                if (sprites.get(j) == comparedSprite) continue;
                calculateInterception(comparedSprite, sprites.get(j));
            }
        }

    }

    private static void calculateInterception(Sprite a, Sprite b) {
        if (checkInterception(a, b)) {
            a.intercept = true;
            b.intercept = true;
            if ((a.ident.contains("Weapon") || b.ident.contains("Weapon"))
                    && !(a.ident.equals(b.host) || b.ident.equals(a.host))) {
                a.dead = true;
                b.dead = true;
            }
        } else {
            a.intercept = false;
            b.intercept = false;
        }
    }

    private static boolean checkInterception(Sprite a, Sprite b) {
        return ((((a.coordX >= b.coordX && a.coordX <= b.coordX1()) || (a.coordX1() >= b.coordX && a.coordX1() <= b.coordX1())
        ) && ((a.coordY >= b.coordY && a.coordY <= b.coordY2()) || (a.coordY2() >= b.coordY && a.coordY2() <= b.coordY2()))
        ) || (((b.coordX >= a.coordX && b.coordX <= a.coordX1()) || (b.coordX1() >= a.coordX && b.coordX1() <= a.coordX1())
        ) && ((b.coordY >= a.coordY && b.coordY <= a.coordY2()) || (b.coordY2() >= a.coordY && b.coordY2() <= a.coordY2())))
        ) || ((((a.coordX >= b.coordX && a.coordX <= b.coordX1()) || (a.coordX1() >= b.coordX && a.coordX1() <= b.coordX1())
        ) && ((b.coordY >= a.coordY && b.coordY <= a.coordY2()) || (b.coordY2() >= a.coordY && b.coordY2() <= a.coordY2()))
        ) || (((b.coordX >= a.coordX && b.coordX <= a.coordX1()) || (b.coordX1() >= a.coordX && b.coordX1() <= a.coordX1())
        ) && ((a.coordY >= b.coordY && a.coordY <= b.coordY2()) || (a.coordY2() >= b.coordY && a.coordY2() <= b.coordY2()))));
    }

}
