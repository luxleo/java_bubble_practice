package bubble.prod.component;

import bubble.prod.BubbleFrame;
import bubble.prod.service.BackgroundStageControllerService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StageController {
    private int stage=1;
    private BubbleFrame mainContext;
    private int stageState;
    private int stateGaming =0; // 초기 상태로 게임 진행중
    private int stateCleared = 1; // 모든 적 죽였을 때
    private int statePlayerDead =2; // 플레이어 죽었을 때
    private int stateAllCleared =3; // 모든 스테이지 끝났을 때

    public StageController (BubbleFrame mainContext){
        this.mainContext = mainContext;
        initSetting();
        initBackgroundStageController();
    }
    private void initSetting(){
        // player 초기 셋팅
        Player player = new Player(mainContext);
        mainContext.setPlayer(player);
        mainContext.add(player);
        // 적 초기 세팅
        Enemy firstEnemy = new Enemy(mainContext);
        mainContext.add(firstEnemy);
        mainContext.getEnemyList().add(firstEnemy);
        //상태 run으로 바꾸기
        stageState=0;
    }
    private void initBackgroundStageController(){
        new Thread(new BackgroundStageControllerService(this)).start();
    }
    /**
     * stage 다음 스테이지로 업그레이
     * 적 새로 재생성한다.
     * */
    public void setNextStage(){
        if (mainContext.getEnemyList().size() > 0){
            return;
        }
        stage++;
        for (int i = 0; i <stage; i++ ){
            Enemy enemy = new Enemy(mainContext);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            mainContext.add(enemy);
            mainContext.getEnemyList().add(enemy);
        }
    }
    public void restartStage(){
        // 플레이어 죽었을때 위치 초기화
        mainContext.getPlayer().regeneratePlayer();
    }
    public void endGame(){
        System.exit(0);
    }
}
