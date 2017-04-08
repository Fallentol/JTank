package classes;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound extends Thread {

    public static void gunShot() {
        try {
            File soundFile = new File("C:\\Tanks 1.0\\src\\sounds\\shoot.wav"); //Звуковой файл

            //Получаем AudioInputStream
            //Вот тут могут полететь IOException и UnsupportedAudioFileException
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);

            //Получаем реализацию интерфейса Clip
            //Может выкинуть LineUnavailableException
            Clip clip = AudioSystem.getClip();

            //Загружаем наш звуковой поток в Clip
            //Может выкинуть IOException и LineUnavailableException
            clip.open(ais);

            clip.setFramePosition(0); //устанавливаем указатель на старт
            clip.start(); //Поехали!!!
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() < startTime + 2500) {
            }
            clip.stop(); //Останавливаем
            clip.close(); //Закрываем
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void run() {
        gunShot();
    }
}
