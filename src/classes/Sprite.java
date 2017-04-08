package classes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by New on 4/7/2017.
 */
public class Sprite {
    Image image; //изображение
    HashMap<String, Image> mapOfImages;
    String position = "LEFT";
    int imageWidth = 50;
    int imageHeight = 50;
    float coordX = 10;
    float coordY = 10;
    String ident;
    boolean dead = false; // признак того что танк убит
    boolean intercept = false; // признако того что спрайт в пересечении.
    public String host = null; // выпустивший пулю

    float coordX1() {
        return coordX + imageWidth;
    }

    float coordY2() {
        return coordY + imageHeight;
    }

    public String getPosition() {
        return position;
    }

    public float getX() {
        return coordX;
    }

    public float getY() {
        return coordY;
    }


    public Sprite() {
    }

    public Sprite(String path) {
        BufferedImage sourceImage = null;
        try {
            URL url = this.getClass().getClassLoader().getResource(path);
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.image = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
    }

    public Image getImageFromString(String path) {
        BufferedImage sourceImage = null;
        try {
            URL url = this.getClass().getClassLoader().getResource(path);
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
    }

    public int getWidth() { //получаем ширину картинки
        return image.getWidth(null);
    }

    public int getHeight() { //получаем высоту картинки
        return image.getHeight(null);
    }

    public void draw(Graphics g, int x, int y) {
        g.drawImage(image, x, y, imageWidth, imageHeight, null);
    }

    public void draw(Graphics g) {
        Image currentImage = mapOfImages.get(position);
        g.drawImage(currentImage, (int) coordX, (int) coordY, imageWidth, imageHeight, null);
    }

    public void drawSimple(Graphics g) {
        g.drawImage(image, (int) coordX, (int) coordY, imageWidth, imageHeight, null);
    }

}
