package bubble.test.ex13;

/**
 * Adapter 패턴으로 하면 자바의 경우 다중 상속이 어려우므로
 * 많이 사용하지 않는다.
 * default로 구현 하면 interface도 구현체를 가질 수 있다.
 */

public interface Movable {
    public abstract void left() throws InterruptedException;
    public abstract void right();
    public abstract void up();
    default public void down(){};
}
