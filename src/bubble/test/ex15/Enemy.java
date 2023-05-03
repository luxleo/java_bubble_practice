package bubble.test.ex15;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class Enemy extends JLabel implements Movable {
    //위치 상태
    private BubbleFrame mainContext;
    private int x;
    private int y;
    //바라보고 있는 상태
    private EnemyFace enemyFace;
    // 움직임 상태
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    // speed
    private final int SPEED = 4;
    private final int UPSPEED = 3;
    private final int DOWNSPEED = 4;
    // 뚜드려 맞은 상태
    private int state; // 0(안맞음) 1(맞음)
    private ImageIcon enemyR,enemyL;
    public Enemy(BubbleFrame mainconText){
        this.mainContext = mainconText;
        initObj();
        initSetting();
        initBackgroundEnemyService();
        // 생성 되면 오른쪽으로 이동한다.
        right();
    }
    private void initObj(){
        enemyR = new ImageIcon("image/enemyR.png");
        enemyL = new ImageIcon("image/enemyL.png");
    }
    private void initSetting(){
        x= 480;
        y = 178;

        left=false;
        right=false;
        up=false;
        down=false;

        state = 0;
        enemyFace = EnemyFace.RIGHT;
        setIcon(enemyR);
        setSize(50,50);
        setLocation(x,y);
    }
    private void initBackgroundEnemyService(){
        new Thread(new BackgroundEnemyService(this)).start();
    }
    @Override
    public void left() {
        System.out.println("left is exected");
        left = true;
        enemyFace = EnemyFace.LEFT;
        setIcon(enemyL);
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
        enemyFace = EnemyFace.RIGHT;
        setIcon(enemyR);
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
