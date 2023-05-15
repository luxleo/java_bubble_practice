## 버블 클론코딩으로 자바 멀티스레드 학습
<a href="https://www.youtube.com/watch?v=5Zh7P-9qFO0&list=PL93mKxaRDidGqGOsNQ1DqTwB0xA_ON-nY"> 참조 유튜브 url </a>

## 구성:
```text
root:src/bubble/prod/BubbleFrame이 메인 스레드로 이벤트리스너 객체를 통하여 키 조작을 담당하며
Bubble,Enemy,Player,StageController등의 객체를 포함하고 있다.
각 클래스 들의 백그라운드 스레드를 통하여
Bubble: 적과 충돌 하는지 JFrame내의 위치를 기반으로 감지, 충돌시 적의 상태를 변화시킴
Player: player의 위치와 JFrame의 배경 사진의 RGB 값을 이용하여 허공인 경우 떨어지고
    아닌 경우 점프시 올라가는 등의 위치에 따른 움직임을 구현 (Movable 인터페이스 상속)
Enemy: 플레이어와 충돌 하였는지 체크하고 충돌한 경우 플레이어 상태를 죽음으로 바뀌게한다.
    player 와 마찬가지로 위치에 따른 움직임을 구현 하였다(Movable 인터페이스 상속)
StageController:
    유튜브 강의 이외에 직접 구현 한 클래스로 기존의 플레이어가 죽는 로직, 스테이지에 비례한 
    적 소환, 적 모두 죽었을 경우 스테이지 클리어 등의 기능을 구현하였다.
```
## What I Learn
```text
메인 스레드와 그 내부의 객체에 스레드를 할당하여 백그라운드 서비스를 구현함으로
하나의 프로세스 내에서 독립적인 서로 다른 작업을 동시적으로 처리하는 과정을 배웠다.
```
