package bubble.prod.component;


import bubble.prod.BubbleFrame;
import bubble.prod.Movable;
import bubble.prod.service.BackgroundBubbleService;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.List;

@Setter
@Getter
public class Bubble extends JLabel implements Movable {
    // 의존성 컴포지션
    private BubbleFrame mainContext;
    private Player player;
    private Enemy enemy;
    private List<Enemy> enemyList;
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
    private static int removeIdx = 0;

    public Bubble(BubbleFrame mainContext){
        this.mainContext = mainContext;
        this.player = mainContext.getPlayer();
        //this.enemy = mainContext.getEnemy();
        this.enemyList = mainContext.getEnemyList();
        initObj();
        initSetting();
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

    @Override
    public void left() {
        left=true;
        for (int i=0; i<400; i++){
            x--;
            setLocation(x,y);
            if (backgroundBubbleService.leftWall()){
                left=false;
                break;
            }
//            for (Enemy enemy: enemyList) {
//                if (Math.abs(x - enemy.getX()) < 50 && Math.abs(y - enemy.getY()) < 50 && enemy.getState() == 0) {
//                    hitEnemy(enemy);
//                    up();
//                    return;
//                }
//            }
            for (int j=0; j< enemyList.size(); j++){
                if (Math.abs(x - enemyList.get(j).getX()) < 50 && Math.abs(y - enemyList.get(j).getY()) < 50 && enemyList.get(j).getState() == 0){
                    hitEnemy(enemyList.get(j));
                    up();
                    return;
                }
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
                right=false;
                break;
            }
            for (int j=0; j< enemyList.size(); j++){
                if (Math.abs(x - enemyList.get(j).getX()) < 50 && Math.abs(y - enemyList.get(j).getY()) < 50 && enemyList.get(j).getState() == 0){
                    hitEnemy(enemyList.get(j));
                    up();
                    return;
                }
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
                up=false;
                break;
            }
            y--;
            setLocation(x,y);
            if (state ==0) {
                for (int j=0; j< enemyList.size(); j++){
                    if (Math.abs(x - enemyList.get(j).getX()) < 50 && Math.abs(y - enemyList.get(j).getY()) < 50 && enemyList.get(j).getState() == 0){
                        hitEnemy(enemyList.get(j));
                    }
                }
            }
            try {
                if (state ==1){
                    // 물방울 맞았으면 느리게 올라가게
                    Thread.sleep(10);
                }else {
                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if(state == 0){
            // 적 못 맞춘 물방울 만 터트린다
            clearBubble();
        }

    }

    private void clearBubble(){
        try {
            Thread.sleep(2000);
            setIcon(bomb);
            Thread.sleep(500);
            mainContext.remove(this); // BubbleFrame의 메모리(context)에서 지금 Bubble삭제
            player.getBubbleList().remove(this);
            mainContext.repaint(); // 메모리에 남아 있는 객체들만 다시 그린다.

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void clearBubbled(){
        new Thread(()->{
            try {
                up = false;
                 setIcon(bomb);
                Thread.sleep(300);
                mainContext.remove(this);
                player.getBubbleList().remove(this);
                mainContext.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    private void hitEnemy(Enemy enemy){
        setIcon(bubbled);
        state=1;
        enemy.setState(1);
        mainContext.getEnemyList().remove(enemy);
        mainContext.remove(enemy);
        mainContext.repaint();
    }
}
