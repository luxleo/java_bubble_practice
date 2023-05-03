package bubble.test.ex15;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundEnemyService implements Runnable {
    private BufferedImage image;
    private Enemy enemy;
    public BackgroundEnemyService (Enemy enemy){
        this.enemy = enemy;
        try {
            image = ImageIO.read(new File("image/backgroundMapService.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while(enemy.getState() == 0){
            Color leftColor = new Color(image.getRGB(enemy.getX()-10,enemy.getY()+25));
            Color rightColor = new Color(image.getRGB(enemy.getX()+50 +15,enemy.getY()+25));
            int bottomColor = image.getRGB(enemy.getX()+10,enemy.getY()+50) // 왼쪽 끝좌표
                    + image.getRGB(enemy.getX()+50-10,enemy.getY()+50); // 오른쪽 끝 좌표
            if (bottomColor != -2){
                enemy.setDown(false);
            } else {
                // 배경이 하얀색 즉 허공임을 의미힌다.
                if (!enemy.isUp() && !enemy.isDown()){
                    // Player 에서 down이 실행 되면 player.isDown => true 가 되기 때문에 단 한번만 실행된다.
                    // 그 이후 위의 분기문에 다시 걸려서 player.isDown => False가 되어 player.down() 실행 종료.
                    enemy.down();
                }
            }
            // 충돌 한번시만 방향 전환 들어가도록
            if (leftColor.getRed() == 255 && leftColor.getBlue() == 0 && leftColor.getGreen()==0 && enemy.isLeft()){
                //System.out.println("왼쪽 벽에 충돌함");
                enemy.setLeft(false);
                enemy.right();
            }else if (rightColor.getRed() == 255 && rightColor.getBlue() == 0 && rightColor.getGreen()==0 && enemy.isRight()){
                //System.out.println("오른쪽 벽에 충돌함");
                enemy.setRight(false);
                enemy.left();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
