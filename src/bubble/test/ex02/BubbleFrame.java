package bubble.test.ex02;

import javax.swing.*;

public class BubbleFrame extends JFrame {
    private JLabel backgroundMap;
    private Player player;
    public BubbleFrame() {
        initSetting();
        initObj();
        setVisible(true);
    }
    private void initSetting(){
        setSize(1000, 640);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void initObj(){
        backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
        setContentPane(backgroundMap);
        player = new Player();
        add(player);
    }
    public static void main(String[] args) {
        new BubbleFrame();
    }


}
