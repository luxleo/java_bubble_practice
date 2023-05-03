package bubble.prod.service;

import bubble.prod.component.Bubble;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundBubbleService {
    private BufferedImage image;
    private Bubble bubble;
    public BackgroundBubbleService(Bubble bubble) {
        this.bubble = bubble;
        try{
        image = ImageIO.read(new File("image/backgroundMapService.png"));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
    public boolean leftWall(){
        Color leftColor = new Color(image.getRGB(bubble.getX()-10,bubble.getY()+25));
        if (leftColor.getRed() == 255 && leftColor.getBlue() == 0 && leftColor.getGreen()==0){
            return true;
        }
        return false;
    }
    public boolean rightWall(){
        Color rightColor = new Color(image.getRGB(bubble.getX()+50 +15,bubble.getY()+25));
        if (rightColor.getRed() == 255 && rightColor.getBlue() == 0 && rightColor.getGreen()==0){
            return true;
        }
        return false;
    }
    public boolean topWall(){
        Color topColor = new Color(image.getRGB(bubble.getX()+25,bubble.getY()-10));
        if (topColor.getRed()== 255 && topColor.getBlue() == 0 && topColor.getGreen()==0){
            return true;
        }
        return false;
    }
}
