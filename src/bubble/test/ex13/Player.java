package bubble.test.ex13;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

// new 로 생성할수 있는 class 의 경우 abstract method 를 가질수 없다.
@Getter
@Setter
public class Player extends JLabel implements Movable {
    //위치 상태
    private int x;
    private int y;
    //바라보고 있는 상태
    private PlayerFace playerFace;
    // 움직임 상태
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    //벽 충돌 상태
    private boolean leftWallCrash;
    private boolean rightWallCrash;


    // speed
    private final int SPEED = 5;
    private final int UPSPEED = 5;
    private final int DOWNSPEED = 4;
    private ImageIcon playerR,playerL;
    public Player(){
        initObj();
        initSetting();
        initBackgroundPlayerService();
    }
    private void initObj(){
        playerR = new ImageIcon("image/playerR.png");
        playerL = new ImageIcon("image/playerL.png");
    }
    private void initSetting(){
        x= 55;
        y = 540;

        left=false;
        right=false;
        up=false;
        down=false;

        leftWallCrash=false;
        rightWallCrash=false;

        playerFace = PlayerFace.RIGHT;
        setIcon(playerR);
        setSize(50,50);
        setLocation(x,y);
    }
    private void initBackgroundPlayerService(){
        new Thread(new BackgroundPlayerService(this)).start();
    }
    @Override
    public void left() {
        System.out.println("left is exected");
        left = true;
        playerFace = PlayerFace.LEFT;
        setIcon(playerL);
        new Thread(()->{
            while (left){

                x -= SPEED;
                setLocation(x,y);
                try {
                    Thread.sleep(10);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void right() {
        right= true;
        playerFace = PlayerFace.RIGHT;
        setIcon(playerR);
        new Thread(()->{
            while (right){

                x +=SPEED;
                setLocation(x,y);
                try {
                    Thread.sleep(10);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();


    }

    @Override
    public void up() {
        up = true;
        new Thread(()->{
            for (int i=0; i<130/UPSPEED;i++){
                y -= UPSPEED;
                setLocation(x,y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            up = false;
            down();
        }).start();
    }

    @Override
    public void down() {
        down = true;
        new Thread(()->{
            while (down){
                y += DOWNSPEED;
                setLocation(x,y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            down=false;
        }).start();
    }
}
