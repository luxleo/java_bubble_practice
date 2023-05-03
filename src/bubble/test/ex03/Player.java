package bubble.test.ex03;

import javax.swing.*;

// new 로 생성할수 있는 class 의 경우 abstract method 를 가질 수 없다.
public class Player extends JLabel implements Movable{
    //위치 상태
    private int x;
    private int y;
    // 움직임 상태
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    private ImageIcon playerR,playerL;
    public Player(){
        initObj();
        initSetting();
    }
    private void initObj(){
        playerR = new ImageIcon("image/playerR.png");
        playerL = new ImageIcon("image/playerL.png");
    }
    private void initSetting(){
        x= 55;
        y = 535;

        left=false;
        right=false;
        up=false;
        down=false;

        setIcon(playerR);
        setSize(50,50);
        setLocation(x,y);
    }

    @Override
    public void left() {
        setIcon(playerL);
        x -=10;
        setLocation(x,y);
    }

    @Override
    public void right() {
        setIcon(playerR);
        x +=10;
        setLocation(x,y);

    }

    @Override
    public void up() {

    }

    @Override
    public void down() {

    }
}
