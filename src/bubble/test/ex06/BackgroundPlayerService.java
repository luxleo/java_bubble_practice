package bubble.test.ex06;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;

public class BackgroundPlayerService implements Runnable {
    private BufferedImage image;
    private Player player;

    public BackgroundPlayerService(Player player) {
        this.player = player;
        try{
        image = ImageIO.read(new File("image/backgroundMapService.png"));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void run() {
        while(true){
            Color leftColor = new Color(image.getRGB(player.getX()-10,player.getY()+25));
            Color rightColor = new Color(image.getRGB(player.getX()+10+15,player.getY()+25));
            if (leftColor.getRed() == 255 && leftColor.getBlue() == 0 && leftColor.getGreen()==0){
                System.out.println("왼쪽 벽에 충돌함");
            }else if (rightColor.getRed() == 255 && rightColor.getBlue() == 0 && rightColor.getGreen()==0){
                System.out.println("오른쪽 벽에 충돌함");
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
