package ru.job4j.bomberman;

public class HeroThread extends Thread {
    private static final int PAUSE = 1000;
    private static final int COUNT_THRESHOLD = 15;
    private final Board board;
    private Hero hero;
    private final Direction startDirection;

    public HeroThread(Board board, int x, int y, Direction startDirection) throws OutOfBoundsException {
        this.board = board;
        this.startDirection = startDirection;
        if (x > 0 && x < this.board.getWidth() && y > 0 && y < this.board.getHeight()) {
            hero = new Hero(new Cell(x, y));
        } else {
            throw new OutOfBoundsException();
        }
    }
    @Override
    public void run() {
        Direction direction = startDirection;
        int count = 0;
        try {
            this.board.init(hero.getPosition());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            Cell source = hero.getPosition();
            Cell dest = new Cell(source.getX() + direction.getDeltaX(), source.getY() + direction.getDeltaY());
            try {
                if (!board.move(source, dest)) {
                    direction = DirectionHelper.clockwiseNext(direction);
                } else {
                    System.out.println(
                            String.format(
                                    "thread: %s, from:%s,%s to: %s,%s",
                                    Thread.currentThread().getName(),
                                    source.getX(),
                                    source.getY(),
                                    dest.getX(),
                                    dest.getY()
                            )
                    );
                    hero = new Hero(dest);
                }
                Thread.sleep(PAUSE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            if (count > COUNT_THRESHOLD) {
                break;
            }
        }
    }
}
