package bubble.test.ex15;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Getter
@Setter
public class BubbleFrame extends JFrame {
    private BubbleFrame mainContext=this;
    private JLabel backgroundMap;
    private Player player;
    //TODO: enemy list 로 만들어 관리하기
    private Enemy enemy;

    public BubbleFrame() {
        initSetting();
        initObj();
        initListener();
        setVisible(true);
    }

    private void initSetting() {
        setSize(1000, 640);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initObj() {
        backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
        setContentPane(backgroundMap);
        player = new Player(mainContext);
        add(player);
        enemy = new Enemy(mainContext);
        add(enemy);
    }

    private void initListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        if (!player.isRight() && !player.isRightWallCrash()) {
                            player.right();
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (!player.isLeft() && !player.isLeftWallCrash()) {
                            player.left();
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if(!player.isUp() && !player.isDown()){
                            player.up();
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        //Bubble bubble = new Bubble(mainContext);
                        //add(bubble);
                        player.attack();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        player.setRight(false);
                        break;
                    case KeyEvent.VK_LEFT:
                        player.setLeft(false);
                        break;

                }
            }
        });
    }

    public static void main(String[] args) {
        new BubbleFrame();
    }


}
