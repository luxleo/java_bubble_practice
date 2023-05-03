package bubble.prod;

import bubble.prod.component.Enemy;
import bubble.prod.component.Player;
import bubble.prod.component.StageController;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BubbleFrame extends JFrame {
    private BubbleFrame mainContext=this;
    private JLabel backgroundMap;
    private Player player;
    //TODO: enemy list 로 만들어 관리하기
    //private Enemy enemy;
    private List<Enemy> enemyList = new ArrayList<>();
    private StageController stageController;

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
        // 맵 Frame에 등록
        setContentPane(backgroundMap);
//        player = new Player(mainContext);
//        add(player);
//        enemy = new Enemy(mainContext);
//        add(enemy);
        StageController initialStageController = new StageController(this);
        this.stageController = initialStageController;
    }

    private void initListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_D:
                        if (!player.isRight() && !player.isRightWallCrash()) {
                            player.right();
                        }
                        break;
                    case KeyEvent.VK_A:
                        if (!player.isLeft() && !player.isLeftWallCrash()) {
                            player.left();
                        }
                        break;
                    case KeyEvent.VK_W:
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
                    case KeyEvent.VK_D:
                        player.setRight(false);
                        break;
                    case KeyEvent.VK_A:
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
