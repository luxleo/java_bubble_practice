package bubble.test.ex12;


import javax.swing.*;

public class Bubble extends JLabel implements Movable{
    // 의존성 컴포지션
    private Player player;
    private BackgroundBubbleService backgroundBubbleService;
    private int x;
    private int y;
    // 움직임 상태
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    //적군을 맞춘 상태
    private int state; // 0:그냥 물방울 ,1: 적을 가둔 물방울

    private ImageIcon bubble; // 물방울
    private ImageIcon bubbled; // 적 가둔 물방울
    private ImageIcon bomb; // 터진 물방울
    // speed
    private final int SPEED = 5;
    private final int UPSPEED = 5;
    private final int DOWNSPEED = 4;

    public Bubble(Player player){
        this.player = player;
        initObj();
        initSetting();
        initLaunch();
    }
    private void initObj(){
        bubble = new ImageIcon("image/bubble.png");
        bubbled = new ImageIcon("image/bubbled.png");
        bomb = new ImageIcon("image/bomb.png");
        backgroundBubbleService = new BackgroundBubbleService(this);
    }
    private void initSetting(){
        left=false;
        right=false;
        up=false;
        down=false;
        // player 위치를 시작점으로
        x = player.getX();
        y = player.getY();
        setIcon(bubble);
        setSize(50,50);
        setLocation(x,y);
        state= 0;
    }
    private void initLaunch(){
        // 물방울 발사는 스레드 하나만 필요하다.
        new Thread(()->{
            if (player.getPlayerFace() == PlayerFace.LEFT){
                left();
            }else{
                right();
            }
        }).start();
    }
    @Override
    public void left() {
        left=true;
        for (int i=0; i<400; i++){
            x--;
            setLocation(x,y);
            if (backgroundBubbleService.leftWall()){
                break;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        up();
    }

    @Override
    public void right() {
        right=true;
        for (int i=0; i<400; i++){
            x++;
            setLocation(x,y);
            if (backgroundBubbleService.rightWall()){
                break;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        up();
    }

    @Override
    public void up() {
        up = true;
        while(up){
            if (backgroundBubbleService.topWall()){
                break;
            }
            y--;
            setLocation(x,y);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
