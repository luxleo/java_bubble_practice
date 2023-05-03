package bubble.prod.component;

import bubble.prod.BubbleFrame;
import bubble.prod.Movable;
import bubble.prod.service.BackgroundPlayerService;
import bubble.prod.state.PlayerFace;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

// new 로 생성할수 있는 class 의 경우 abstract method 를 가질수 없다.
@Getter
@Setter
public class Player extends JLabel implements Movable {
    private BubbleFrame mainContext;
    private List<Bubble> bubbleList;
    // 위치상태
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
    public Player(BubbleFrame mainconText){
        this.mainContext = mainconText;
        initObj();
        initSetting();
        initBackgroundPlayerService();
    }
    private void initObj(){
        playerR = new ImageIcon("image/playerR.png");
        playerL = new ImageIcon("image/playerL.png");
        bubbleList = new ArrayList<>();
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
    public void regeneratePlayer(){
        x = 55;
        y = 540;
        setIcon(playerR);
        setLocation(x,y);
    }
    @Override
    public void left() {
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

    @Override
    public void attack() {
        new Thread(()->{
            Bubble bubble = new Bubble(mainContext);
            bubbleList.add(bubble);
            // main context(JFrame) 에 더해주어야 한다.
            mainContext.add(bubble);
            if (playerFace == PlayerFace.LEFT){
                bubble.left();
            }else{
                bubble.right();
            }
        }).start();

    }
}
