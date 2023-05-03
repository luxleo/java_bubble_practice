package bubble.test.ex12;




import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
            Color rightColor = new Color(image.getRGB(player.getX()+50 +15,player.getY()+25));
            int bottomColor = image.getRGB(player.getX()+10,player.getY()+50) // 왼쪽 끝좌표
                + image.getRGB(player.getX()+50-10,player.getY()+50); // 오른쪽 끝 좌표
            if (bottomColor != -2){
                player.setDown(false);
            } else {
                // 배경이 하얀색 즉 허공임을 의미힌다.
                if (!player.isUp() && !player.isDown()){
                    // Player 에서 down이 실행 되면 player.isDown => true 가 되기 때문에 단 한번만 실행된다.
                    // 그 이후 위의 분기문에 다시 걸려서 player.isDown => False가 되어 player.down() 실행 종료.
                    player.down();
                }
            }

            if (leftColor.getRed() == 255 && leftColor.getBlue() == 0 && leftColor.getGreen()==0){
                //System.out.println("왼쪽 벽에 충돌함");
                player.setLeftWallCrash(true);
                player.setLeft(false);
            }else if (rightColor.getRed() == 255 && rightColor.getBlue() == 0 && rightColor.getGreen()==0){
                //System.out.println("오른쪽 벽에 충돌함");
                player.setRightWallCrash(true);
                player.setRight(false);
            }else {
                player.setLeftWallCrash(false);
                player.setRightWallCrash(false);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
