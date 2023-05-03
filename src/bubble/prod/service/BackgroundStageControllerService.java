package bubble.prod.service;

import bubble.prod.component.StageController;

public class BackgroundStageControllerService implements Runnable{
    private StageController stage;
    public BackgroundStageControllerService(StageController stage){
        this.stage = stage;
    }
    @Override
    public void run() {
        while (true){
            if (stage.getStage() == 5){
                stage.endGame();
            }
            if (stage.getMainContext().getEnemyList().size() ==0 ){
                stage.setStageState(stage.getStateCleared());
            }
            if (stage.getStageState() == stage.getStateCleared()){
                stage.setNextStage();
                stage.setStageState(stage.getStateGaming());
            } else if (stage.getStageState() == stage.getStatePlayerDead()) {
                stage.restartStage();
            } else if (stage.getStageState() == stage.getStateAllCleared()) {
                System.exit(0);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
